package com.alerter.mail.refactor.service;

import javax.mail.internet.MimeMessage;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public interface MailService {

  void send(MimeMessage message);
}