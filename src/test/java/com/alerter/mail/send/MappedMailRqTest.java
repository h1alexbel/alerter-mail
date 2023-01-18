//package com.alerter.mail.send;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.test.context.ActiveProfiles;
//
//@ActiveProfiles("test")
//class MappedMailRqTest {
//
//  @Test
//  void createRequestValidText() {
//    final MpMailAsText request = new MpMailAsText(
//        new RqMessage("@alerter, release it to example@gmail.com; subject is Testing Report));
//    assertThat(request.to()).isEqualTo("example@gmail.com");
//    assertThat(request.subject()).isEqualTo("Testing Report");
//    assertThat(request.content()).isEqualTo("docUrl");
//  }
//
//  @Test
//  void checkToInvalidText() {
//    assertThrows(MailProcessingException.class,
//        () -> new MpMailAsText(new RqMessage("invalid text..", "docUrl")).to()
//    );
//  }
//
//  @Test
//  void checkSubjectInvalidText() {
//    assertThrows(MailProcessingException.class,
//        () -> new MpMailAsText(new RqMessage("invalid text..", "docUrl")).subject()
//    );
//  }
//}