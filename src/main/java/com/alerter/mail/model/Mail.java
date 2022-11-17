package com.alerter.mail.model;

import javax.mail.internet.MimeMessage;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public interface Mail {

  MimeMessage mime();
}