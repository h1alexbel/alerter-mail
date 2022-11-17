package com.alerter.mail.sender;

import com.alerter.mail.model.Mail;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public final class PrMail implements Mail {

  private final MimeMessage start;
  private final MailRq request;

  public PrMail(final MailRq rq, final MimeMessage start) {
    this.request = rq;
    this.start = start;
  }

  @Override
  public MimeMessage mime() {
    try {
      final MimeMessageHelper helper =
          new MimeMessageHelper(
              this.start, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name()
          );
      helper.setTo(this.request.getTo());
      helper.setFrom(this.request.getFrom());
      helper.setSubject(this.request.getSubject());
      helper.setText(this.request.getDocLink());
      return this.start;
    } catch (final MessagingException e) {
      throw new IllegalStateException(e);
    }
  }
}