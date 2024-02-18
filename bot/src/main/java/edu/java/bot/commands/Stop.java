package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.Users;

public class Stop implements Command {
    @Override
    public SendMessage apply(Update update, Users users) {
        users.remove(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), "You were unauthorized");
    }
}
