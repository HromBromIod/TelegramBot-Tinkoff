package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.User;
import edu.java.bot.users.Users;

public class Start implements Command {
    @Override
    public SendMessage apply(Update update, Users users) {
        if (users.find(update.message().chat().id()) != null) {
            return new SendMessage(update.message().chat().id(), "You have already been registered");
        }
        users.add(new User(update.message().chat().id(), update.message().chat().username()));
        return new SendMessage(update.message().chat().id(), "Registration was successful\nWelcome!");
    }
}
