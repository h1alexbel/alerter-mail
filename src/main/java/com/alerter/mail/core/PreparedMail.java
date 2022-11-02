package com.alerter.mail.core;

import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class PreparedMail {

  private MimeMessage message;
}