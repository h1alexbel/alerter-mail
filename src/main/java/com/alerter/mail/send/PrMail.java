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

import com.alerter.mail.model.Mail;
import com.alerter.mail.model.MailAsText;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Prepared Mail.
 *
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
public final class PrMail implements Mail {

  private final MimeMessage message;
  private final MailAsText text;

  /**
   * Ctor.
   *
   * @param txt MailAsText original request as plain text
   * @param msg MimeMessage original mime to wrap request
   */
  public PrMail(final MailAsText txt, final MimeMessage msg) {
    this.message = msg;
    this.text = txt;
  }

  @Override
  public MimeMessage mime() throws MailProcessingException {
    try {
      final MimeMessageHelper helper =
        new MimeMessageHelper(
          this.message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name()
        );
      helper.setTo(this.text.to());
      helper.setFrom(this.text.from());
      helper.setSubject(this.text.subject());
      helper.setText(this.text.content());
      return this.message;
    } catch (final MessagingException e) {
      throw new IllegalStateException(e);
    }
  }
}