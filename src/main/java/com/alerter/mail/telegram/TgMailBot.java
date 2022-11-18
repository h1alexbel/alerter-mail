package com.alerter.mail.telegram;

import com.alerter.mail.model.TgBot;
import com.alerter.mail.model.Token;
import com.alerter.mail.sender.MailRq;
import com.alerter.mail.sender.PrMail;
import com.alerter.mail.sender.SendMail;
import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Slf4j
@BotController
@RequiredArgsConstructor
public class TgMailBot implements TelegramMvcController, TgBot<BaseRequest> {

  private static final String MESSAGE_PATTERN =
      "@alerter,? release it to .+@.+; subject is [a-zA-Zа-яА-Я.0-9_| ]*";
  private final Token token;
  private final JavaMailSender sender;

  @BotRequest(type = MessageType.ANY)
  @Override
  public BaseRequest onStart(final Chat chat, final Message message,
                             final TelegramBot bot) {
    final String input;
    if (message.caption() != null) {
      input = message.caption();
    } else {
      input = message.text();
    }
    if (Pattern.matches(MESSAGE_PATTERN, input)) {
      if (message.document() != null) {
        return this.onProcess(chat, message, bot);
      } else {
        return this.onFail(chat.id());
      }
    } else {
      return this.onMissUnderstand(chat.id());
    }
  }

  @Override
  public final BaseRequest onMissUnderstand(final Object id) {
    return new SendMessage(
        id,
        "I see you're talking about me, but I don't understand it. Message format should be: @alerter release it to example@gmail.com; subject is X"
    );
  }

  @Override
  public final BaseRequest onFail(final Object id) {
    return new SendMessage(
        id, "I can't release it. I need an artifact in PDF format to make a release."
    );
  }

  @Override
  public final BaseRequest onProcess(final Chat chat, final Message message,
                                     final TelegramBot bot) {
    bot.execute(
        new SendMessage(chat.id(),
            new TgLoggedMessage(message)
                .read()
        )
    );
    return new TgPrMessage(
        new SendMail(
            new PrMail(
                new MailRq(
                    message.caption(),
                    new TgDocumentURL(
                        message.document(),
                        bot
                    ).read()
                ), this.sender.createMimeMessage()
            ).mime(), this.sender
        ).send(), chat
    ).read();
  }

  @Override
  public String getToken() {
    return this.token.reveal();
  }
}