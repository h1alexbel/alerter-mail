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

package com.alerter.mail.model;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 0.0.1
 */
public interface TgBot<T> {

  /**
   * Start of the conversation
   *
   * @param chat    Telegram Chat
   * @param message Message to respond
   * @param bot     Listener, Message Executor
   * @return Message to the user
   */
  T onStart(Chat chat, Message message, TelegramBot bot);

  /**
   * Explains message format to the user
   *
   * @param id chat id
   * @return Message to the user
   */
  T onMissUnderstand(Object id);

  /**
   * Fails the conversation notifying the user
   *
   * @param id chat id
   * @return Message to the user
   */
  T onFail(Object id);

  /**
   * Processes the user request
   *
   * @param chat    Telegram Chat
   * @param message Message to respond
   * @param bot     Listener, Message Executor
   * @return Message to the user
   */
  T onProcess(Chat chat, Message message, TelegramBot bot);
}