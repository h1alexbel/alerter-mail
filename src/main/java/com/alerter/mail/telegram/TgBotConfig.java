package com.alerter.mail.telegram;

import com.alerter.mail.model.Token;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Configuration
public class TgBotConfig {

  @Bean
  public Token token() {
    return new TgBotToken(System.getenv("BOT_TOKEN"));
  }
}