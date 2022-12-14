/*
MIT License
Copyright (c) 2022 Aliaksei Bialiauski
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.alerter.mail.sender;

import com.alerter.mail.model.MailMappedText;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
public final class MappedMailRq implements MailMappedText {

  private static final int AFTER_TO = 3;
  private static final int AFTER_SUBJECT = 11;
  private final MailRq request;

  public MappedMailRq(final MailRq req) {
    this.request = req;
  }

  @Override
  public String to() {
    try {
      final String text = this.request.text();
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
  public String subject() {
    try {
      final String text = this.request.text();
      return text.substring(text.indexOf("subject is")).substring(AFTER_SUBJECT);
    } catch (final StringIndexOutOfBoundsException e) {
      throw new MailProcessingException("can't parse request, due to: ", e);
    }
  }

  @Override
  public String content() {
    return this.request.getDocUrl();
  }
}