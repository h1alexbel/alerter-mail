package com.alerter.mail.service;

import com.alerter.mail.core.MailRq;
import com.alerter.mail.core.PreparedMail;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public interface MailService {

  PreparedMail prepare(MailRq request);

  void send(PreparedMail mail);
}