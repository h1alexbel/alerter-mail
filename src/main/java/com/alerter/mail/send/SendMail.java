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
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * SendMail.
 *
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
public final class SendMail implements Mail {

  private final Mail original;
  private final JavaMailSender sender;

  /**
   * Ctor.
   *
   * @param mail Mail message to send
   * @param send JavaMailSender mail sender
   */
  public SendMail(final Mail mail, final JavaMailSender send) {
    this.original = mail;
    this.sender = send;
  }

  @Override
  public MimeMessage mime() {
    try {
      this.sender.send(this.original.mime());
      return this.original.mime();
    } catch (final MailProcessingException ex) {
      throw new IllegalStateException(ex);
    }
  }
}