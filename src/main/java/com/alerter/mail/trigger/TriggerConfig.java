package com.alerter.mail.trigger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Configuration
public class TriggerConfig {

  @Bean
  public RestTemplate rest() {
    return new RestTemplate();
  }
}