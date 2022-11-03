package com.alerter.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class AlerterMailApplication {

  private final RestTemplate rest;

  public static void main(String[] args) {
    SpringApplication.run(AlerterMailApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner() {
    return args -> this.ping();
  }

  @Scheduled(fixedDelay = 10000L)
  private void ping() {
    this.rest.getForEntity("https://tg-alerter.herokuapp.com/api/v1/trigger", Object.class);
  }
}