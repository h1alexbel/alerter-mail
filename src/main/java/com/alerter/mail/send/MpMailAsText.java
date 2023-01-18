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

import com.alerter.mail.model.MailAsText;
import com.alerter.mail.telegram.TgMessage;

/**
 * Mapped Mail as plain text.
 *
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
public final class MpMailAsText implements MailAsText {

  private static final int AFTER_TO = 3;
  private static final int AFTER_SUBJECT = 11;
  private final TgMessage request;
  private final String url;

  /**
   * Ctor.
   *
   * @param req RqMail orignal request
   */
  public MpMailAsText(final TgMessage req, final String url) {
    this.request = req;
    this.url = url;
  }

  @Override
  public String to() throws MailProcessingException {
    try {
      final String text = this.request.content();
      return text.substring(text.indexOf("to"), text.indexOf(";")).substring(AFTER_TO);
    } catch (final StringIndexOutOfBoundsException e) {
      throw new MailProcessingException("can't parse request, due to: ", e);
    }
  }

  @Override
  public String from() {
    return System.getenv("BOT_MAIL_NAME");
  }

  @Override
  public String subject() throws MailProcessingException {
    try {
      final String text = this.request.content();
      return text.substring(text.indexOf("subject is")).substring(AFTER_SUBJECT);
    } catch (final StringIndexOutOfBoundsException e) {
      throw new MailProcessingException("can't parse request, due to: ", e);
    }
  }

  @Override
  public String content() {
    return this.url;
  }
}