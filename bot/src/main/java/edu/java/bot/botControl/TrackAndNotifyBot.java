package edu.java.bot.botControl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

public class TrackAndNotifyBot {
    private final TelegramBot telegramBot;

    public TrackAndNotifyBot(String botToken) {
        telegramBot = new TelegramBot(botToken);
    }

    private final BotControl control = new BotControl();

    public void run() {
        telegramBot.setUpdatesListener(updates -> {
                updates.forEach(update -> telegramBot.execute(control.handle(update)));
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        );
    }
}
