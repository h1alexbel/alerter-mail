package com.alerter.mail.sender;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class MailRqTest {

  @Test
  void createRequestValidText() {
    final MailRq request =
        new MailRq("@alerter, release it to example@gmail.com; subject is Testing Report",
            "docUrl");
    assertThat(request.getTo()).isEqualTo("example@gmail.com");
    assertThat(request.getSubject()).isEqualTo("Testing Report");
    assertThat(request.getDocLink()).isEqualTo("docUrl");
  }

  @Test
  void createRequestInvalidText() {
    Assertions.assertThrows(MailRqException.class,
        () -> new MailRq("invalid text..", "docUrl")
    );
  }
}