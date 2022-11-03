package com.alerter.mail.core.map.impl;

import com.alerter.mail.core.MailRq;
import com.alerter.mail.core.map.MailMapper;
import javax.mail.MessagingException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
@Component
public class MailRqToMimeMessageHelperMapperImpl implements MailMapper<MailRq, MimeMessageHelper> {

  private static final String SKIP = "-------------------------------------";
  private static final String ALERTER_TEXT =
      "Automated with Alerter (https://github.com/h1alexbel/alerter-mail);";

  @Override
  public final MimeMessageHelper map(final MimeMessageHelper helper, final MailRq request)
      throws MessagingException {
    helper.setTo(request.getTo());
    helper.setFrom(request.getFrom());
    helper.setSubject(request.getSubject());
    helper.setText(request.getDocLink() + '\n' + '\n' + SKIP + '\n' + ALERTER_TEXT);
    return helper;
  }
}