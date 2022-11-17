package com.alerter.mail.telegram;

import com.alerter.mail.model.Message;
import com.alerter.mail.sender.SendMail;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Arrays;
import javax.mail.MessagingException;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public final class TgPrMessage implements Message<BaseRequest> {

  private final Chat chat;
  private final SendMail send;

  public TgPrMessage(SendMail send, final Chat chat) {
    this.chat = chat;
    this.send = send;
  }

  @Override
  public BaseRequest read() {
    try {
      return new SendMessage(this.chat.id(),
          "Artifact successfully published" + '\n' + "to: " +
              Arrays.toString(this.send.mime().getRecipients(
                      javax.mail.Message.RecipientType.TO
                  )
              ) + "\uD83D\uDC4D"
      );
    } catch (final MessagingException e) {
      throw new IllegalStateException(e);
    }
  }
}
