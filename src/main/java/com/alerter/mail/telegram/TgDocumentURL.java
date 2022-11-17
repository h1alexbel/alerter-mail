package com.alerter.mail.telegram;

import com.alerter.mail.model.Message;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Document;
import com.pengrad.telegrambot.request.GetFile;

/**
 * @author Aliaksei Bialiauski (abialiauski@solvd.com)
 * @since 1.0
 */
public final class TgDocumentURL implements Message<String> {

  private final Document document;
  private final TelegramBot bot;

  public TgDocumentURL(final Document doc, final TelegramBot bot) {
    this.document = doc;
    this.bot = bot;
  }

  @Override
  public String read() {
    return this.bot.getFullFilePath(
        this.bot.execute(
            new GetFile(
                this.document.fileId()
            )
        ).file()
    );
  }
}