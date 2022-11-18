package com.alerter.mail.model;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public interface TgBot<T> {

  T onStart(Chat chat, Message message, TelegramBot bot);

  T onMissUnderstand(Object id);

  T onFail(Object id);

  T onProcess(Chat chat, Message message, TelegramBot bot);
}