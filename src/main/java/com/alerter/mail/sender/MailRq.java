package com.alerter.mail.sender;

import lombok.Getter;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Getter
public final class MailRq {

  private final String from;
  private final String to;
  private final String subject;
  private final String docLink;

  public MailRq(final String text, final String docUrl) {
    this.to = text.substring(text.indexOf("to"), text.indexOf(";"))
        .substring(3);
    this.subject = text.substring(text.indexOf("subject is"))
        .substring(11);
    this.docLink = docUrl;
    this.from = System.getenv("BOT_MAIL_NAME");
  }
}