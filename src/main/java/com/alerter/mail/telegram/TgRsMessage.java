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

package com.alerter.mail.telegram;

import com.alerter.mail.model.Message;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import java.util.Arrays;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Telegram response message.
 *
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
/*
 * @todo #1 create unit and intergration test.
 * decompose it to improve maintainability.
 *
 * */
public final class TgRsMessage implements Message<BaseRequest> {

  private static final String LIKE_CODE = "\uD83D\uDC4D";
  private final Chat chat;
  private final MimeMessage mime;

  /**
   * Ctor.
   *
   * @param msg MimeMessage mime
   * @param ch  Chat chat
   */
  public TgRsMessage(final MimeMessage msg, final Chat ch) {
    this.chat = ch;
    this.mime = msg;
  }

  @Override
  public BaseRequest content() {
    try {
      return new SendMessage(this.chat.id(),
        "Artifact successfully published" + '\n' + "to: " +
          Arrays.toString(this.mime.getRecipients(
              javax.mail.Message.RecipientType.TO
            )
          ) + LIKE_CODE
      );
    } catch (final MessagingException e) {
      throw new IllegalStateException(e);
    }
  }
}