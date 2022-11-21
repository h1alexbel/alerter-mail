package com.alerter.mail.sender;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public class MailRqException extends RuntimeException {

  public MailRqException() {
    super();
  }

  public MailRqException(String message) {
    super(message);
  }

  public MailRqException(String message, Throwable cause) {
    super(message, cause);
  }

  public MailRqException(Throwable cause) {
    super(cause);
  }
}