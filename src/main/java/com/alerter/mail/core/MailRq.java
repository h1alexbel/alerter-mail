package com.alerter.mail.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRq {

  private String from;
  private String to;
  private String subject;
  private String docLink;

  public MailRq fromText(final String text) {
    MailRq mailRq = new MailRq();
    String to = text.substring(text.indexOf("to"), text.indexOf(";"))
        .substring(3);
    String subject = text.substring(text.indexOf("subject is"))
        .substring(11);
    mailRq.setTo(to);
    mailRq.setSubject(subject);
    return mailRq;
  }
}