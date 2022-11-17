package com.alerter.mail.refactor.service.impl;

import com.alerter.mail.refactor.service.MailService;
import com.alerter.mail.sender.MailRq;
import com.alerter.mail.refactor.PreparedMail;
import com.alerter.mail.refactor.map.MailMapper;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final MailMapper<MailRq, MimeMessageHelper> mapper;
  private final JavaMailSender sender;

  public final PreparedMail prepare(final MailRq request) {
    log.debug("Received Mail Request:{}", request);
    final MimeMessage message = this.sender.createMimeMessage();
    try {
      final MimeMessageHelper helper =
          new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
              StandardCharsets.UTF_8.name());
      this.mapper.map(helper, request);
    } catch (final MessagingException e) {
      throw new IllegalStateException("Can not prepare email due to: ", e);
    }
    return new PreparedMail(message);
  }

  @Override
  public void send(final MimeMessage message) {
    this.sender.send(message);
  }
}