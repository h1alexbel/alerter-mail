package com.alerter.mail.sender;

import com.alerter.mail.model.Mail;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public final class SendMail implements Mail {

  private final MimeMessage message;
  private final JavaMailSender sender;

  public SendMail(final MimeMessage message, final JavaMailSender sender) {
    this.message = message;
    this.sender = sender;
  }

  public SendMail send() {
    this.sender.send(this.message);
    return this;
  }

  @Override
  public MimeMessage mime() {
    return this.message;
  }
}