package com.alerter.mail.bot;

import com.alerter.mail.core.MailRq;
import com.alerter.mail.core.PreparedMail;
import com.alerter.mail.service.MailService;
import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Slf4j
@BotController
@RequiredArgsConstructor
public class MailBot implements TelegramMvcController {

  private static final String LOGS = "        \n" +
      "      Document: ${info}\n" +
      "              \n" +
      "      Message ID: ${messageId}\n" +
      "              \n" +
      "      Text: ${text}\n" +
      "              \n" +
      "      Date: ${date}\n" +
      "              ";
  private static final String FROM = System.getenv("BOT_MAIL_NAME");
  private final BotToken token;
  private final MailService service;

  @BotRequest(type = MessageType.ANY)
  public BaseRequest startConversation(final Chat chat, final Message message,
                                       final TelegramBot bot) {
    final String input;
    if (message.caption() != null) {
      input = message.caption();
    } else {
      input = message.text();
    }
    if (Pattern.matches(
        "@alerter,? release it to .+@.+; subject is [a-zA-Zа-яА-Я.0-9_| ]*",
        input)) {
      if (message.document() != null) {
        return this.prepareAndSend(chat, message, bot);
      } else {
        return new SendMessage(chat.id(),
            "I can't release it. I need an artifact in PDF format to make a release." + "\n" +
                "\n" +
                " If I'm not responding trigger me please: https://tg-alerter.herokuapp.com/api/v1/trigger \uD83D\uDD14"
        );
      }
    } else {
      return new SendMessage(chat.id(),
          "I see you're talking about me, but I don't understand it. If you want to say something to me directly, message format should be: @alerter release it to example@gmail.com; subject is X" +
              "\n" + "\n" +
              "If I'm not responding trigger me please: https://tg-alerter.herokuapp.com/api/v1/trigger \uD83D\uDD14");
    }
  }

  private BaseRequest prepareAndSend(final Chat chat, final Message message,
                                     final TelegramBot bot) {
    final String logs = this.fillUpLogs(message);
    bot.execute(
        new SendMessage(chat.id(),
            "OK. I will prepare an artifact for deployment." + "\nSee Logs here:" + '\n' +
                logs)
    );
    final String url = this.takeDocumentUrl(message, bot);
    final MailRq request = this.formMailRequest(message, url);
    final PreparedMail prepared = this.service.prepare(request);
    this.service.send(prepared);
    return new SendMessage(chat.id(),
        "Artifact successfully published" + '\n' + "to: " + request.getTo() + "\uD83D\uDC4D" +
            "\n" + "\n" +
            "If I'm not responding trigger me please: https://tg-alerter.herokuapp.com/api/v1/trigger \uD83D\uDD14");
  }

  private String takeDocumentUrl(final Message message, final TelegramBot bot) {
    return bot.getFullFilePath(bot
        .execute(new GetFile(
                message
                    .document()
                    .fileId()
            )
        )
        .file()
    );
  }

  private String fillUpLogs(final Message message) {
    final String info = LOGS.replace("${info}", message.document().toString());
    final String messageId = info.replace("${messageId}", String.valueOf(message.messageId()));
    final String caption = messageId.replace("${text}", message.caption());
    return caption.replace("${date}", String.valueOf(message.date()));
  }

  private MailRq formMailRequest(final Message message, final String url) {
    final MailRq request = new MailRq()
        .fromText(message.caption());
    request.setFrom(FROM);
    request.setDocLink(url);
    return request;
  }

  @Override
  public String getToken() {
    return this.token.getToken();
  }
}