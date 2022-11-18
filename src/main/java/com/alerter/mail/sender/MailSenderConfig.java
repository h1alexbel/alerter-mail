package com.alerter.mail.sender;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Configuration
public class MailSenderConfig {

  @Bean
  public JavaMailSender sender() {
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost("smtp.gmail.com");
    sender.setPort(587);
    sender.setUsername(System.getenv("BOT_MAIL_NAME"));
    sender.setPassword(System.getenv("BOT_MAIL_PASSWORD"));
    Properties properties = sender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    return sender;
  }
}