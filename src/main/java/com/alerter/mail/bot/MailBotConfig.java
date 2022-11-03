package com.alerter.mail.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Configuration
public class MailBotConfig {

  @Bean
  public BotToken token() {
    return new BotToken(System.getenv("BOT_TOKEN"));
  }
}