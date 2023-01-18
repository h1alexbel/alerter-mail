/*
 * MIT License
 * Copyright (c) 2022 Aliaksei Bialiauski
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.alerter.mail.send;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Mail sender configuration.
 *
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
@Configuration
public class MailSenderConfig {

  private static final String HOST = "smtp.gmail.com";
  private static final int PORT = 587;
  private static final String STARTTL_ENABLE = "mail.smtp.starttls.enable";
  private static final String SMTP_AUTH = "mail.smtp.auth";
  private static final String PROTO = "mail.transport.protocol";

  @Bean
  public JavaMailSender sender() {
    final JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost(HOST);
    sender.setPort(PORT);
    sender.setUsername(System.getenv("BOT_MAIL_NAME"));
    sender.setPassword(System.getenv("BOT_MAIL_PASSWORD"));
    final Properties properties = sender.getJavaMailProperties();
    properties.put(PROTO, "smtp");
    properties.put(SMTP_AUTH, "true");
    properties.put(STARTTL_ENABLE, "true");
    return sender;
  }
}