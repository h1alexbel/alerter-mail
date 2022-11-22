package com.alerter.mail.sender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class MappedMailRqTest {

  @Test
  void createRequestValidText() {
    final MappedMailRq request = new MappedMailRq(
        new MailRq("@alerter, release it to example@gmail.com; subject is Testing Report",
            "docUrl"));
    assertThat(request.to()).isEqualTo("example@gmail.com");
    assertThat(request.subject()).isEqualTo("Testing Report");
    assertThat(request.content()).isEqualTo("docUrl");
  }

  @Test
  void checkToInvalidText() {
    assertThrows(MailProcessingException.class,
        () -> new MappedMailRq(new MailRq("invalid text..", "docUrl")).to()
    );
  }

  @Test
  void checkSubjectInvalidText() {
    assertThrows(MailProcessingException.class,
        () -> new MappedMailRq(new MailRq("invalid text..", "docUrl")).subject()
    );
  }
}