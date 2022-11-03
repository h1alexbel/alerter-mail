package com.alerter.mail.bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Configuration
public class MailBotConfiguration {

  @Bean
  public BotToken token() {
    return new BotToken(System.getenv("BOT_TOKEN"));
  }

  @Bean
  public RestTemplate rest() {
    return new RestTemplate();
  }
}