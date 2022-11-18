package com.alerter.mail.telegram;

import com.alerter.mail.model.Message;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public final class TgLoggedMessage implements Message<String> {

  private static final String LOGS = "        \n" +
      "      Document: ${info}\n" +
      "              \n" +
      "      Message ID: ${messageId}\n" +
      "              \n" +
      "      Text: ${text}\n" +
      "              \n" +
      "      Date: ${date}\n" +
      "              ";
  private final com.pengrad.telegrambot.model.Message message;

  public TgLoggedMessage(final com.pengrad.telegrambot.model.Message msg) {
    this.message = msg;
  }

  @Override
  public String read() {
    final String info = LOGS.replace("${info}", this.message.document().toString());
    final String id = info.replace("${messageId}", String.valueOf(this.message.messageId()));
    final String caption = id.replace("${text}", this.message.caption());
    final String logged = caption.replace("${date}", String.valueOf(this.message.date()));
    return "OK. I will prepare an artifact for deployment." + "\nSee Logs here:" + '\n' + logged;
  }
}