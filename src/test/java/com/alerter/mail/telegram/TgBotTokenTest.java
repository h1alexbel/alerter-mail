package com.alerter.mail.telegram;

import static org.assertj.core.api.Assertions.assertThat;

import com.alerter.mail.model.Token;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class TgBotTokenTest {

  @Test
  void createToken() {
    final Token token = new TgToken("ad90i23-1tio1jvkn=1241W");
    assertThat(token.reveal()).isEqualTo("ad90i23-1tio1jvkn=1241W");
  }
}