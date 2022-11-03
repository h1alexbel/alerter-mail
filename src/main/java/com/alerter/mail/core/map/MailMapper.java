package com.alerter.mail.core.map;

import javax.mail.MessagingException;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public interface MailMapper<F, R> {

  R map(R current, F from) throws MessagingException;
}