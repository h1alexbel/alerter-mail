package com.alerter.mail.service.impl;

import com.alerter.mail.core.MailRq;
import com.alerter.mail.core.PreparedMail;
import com.alerter.mail.service.MailService;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender sender;

  @Override
  public PreparedMail prepare(MailRq request) {
    String response;
    MimeMessage message = sender.createMimeMessage();
    try {
      MimeMessageHelper helper =
          new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      helper.setTo(request.getTo());
      helper.setFrom(request.getFrom());
      helper.setSubject(request.getSubject());
      String alerter =
          "Automated with Alerter (https://github.com/h1alexbel);";
      String rultor = "DevOps with Rultor(https://github.com/yegor256/rultor)";
      String skip = "-------------------------------------";
      helper.setText(request.getDocLink() + "\n" + "\n" + skip + "\n" + alerter + "\n" + rultor);
    } catch (final MessagingException e) {
      throw new IllegalStateException("", e);
    }
    return new PreparedMail(message);
  }

  @Override
  public void send(PreparedMail mail) {
    this.sender.send(mail.getMessage());
  }
}