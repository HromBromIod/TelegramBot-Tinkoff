package edu.java.bot.botControl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

public class TrackAndNotifyBot extends TelegramBot {

    public TrackAndNotifyBot(String botToken) {
        super(botToken);
    }

    private final BotControl control = new BotControl();

    public void run() {
        this.setUpdatesListener(updates -> {
                updates.forEach(update -> execute(control.handle(update)));
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        );
    }
}
