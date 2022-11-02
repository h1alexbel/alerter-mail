package com.alerter.mail.service.impl;

import com.alerter.mail.core.MailRq;
import com.alerter.mail.core.PreparedMail;
import com.alerter.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
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
    throw new UnsupportedOperationException("#prepare()");
  }

  @Override
  public void send(PreparedMail mail) {
    throw new UnsupportedOperationException("#send()");
  }
}