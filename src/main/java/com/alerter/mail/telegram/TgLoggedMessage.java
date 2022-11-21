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

package com.alerter.mail.telegram;

import com.alerter.mail.model.Message;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
public final class TgLoggedMessage implements Message<String> {

  private static final String LOGS = "        \n" +
      "      Document: ${info}\n" +
      "              \n" +
      "      Message ID: ${messageId}\n" +
      "              \n" +
      "      Text: ${text}\n" +
      "              \n" +
      "      Date: ${date}\n" +
      "              ";
  private final com.pengrad.telegrambot.model.Message message;

  public TgLoggedMessage(final com.pengrad.telegrambot.model.Message msg) {
    this.message = msg;
  }

  @Override
  public String read() {
    final String info = LOGS.replace("${info}", this.message.document().toString());
    final String id = info.replace("${messageId}", String.valueOf(this.message.messageId()));
    final String caption = id.replace("${text}", this.message.caption());
    final String logged = caption.replace("${date}", String.valueOf(this.message.date()));
    return "OK. I will prepare an artifact for deployment." + "\nSee Logs here:" + '\n' + logged;
  }
}