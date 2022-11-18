package com.alerter.mail.telegram;

import com.alerter.mail.model.Token;
import lombok.AllArgsConstructor;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@AllArgsConstructor
public class TgBotToken implements Token {

  private String token;

  @Override
  public String reveal() {
    return this.token;
  }
}