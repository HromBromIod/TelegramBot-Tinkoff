package edu.java.bot.botControl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.users.Users;

public class TrackAndNotifyBot extends TelegramBot {

    public TrackAndNotifyBot(String botToken) {
        super(botToken);
    }

    BotControl control = new BotControl();
    Users users = new Users();

    public void run() {
        this.setUpdatesListener(updates -> {
                updates.forEach(update -> execute(control.handle(update, users)));
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        );
    }
}
