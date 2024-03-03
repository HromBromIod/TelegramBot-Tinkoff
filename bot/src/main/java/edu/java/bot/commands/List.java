package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.User;
import edu.java.bot.users.UserRepository;

public class List implements Command {
    @Override
    public SendMessage apply(Update update, UserRepository users) {
        User user = users.find(update.message().chat().id());
        return new SendMessage(update.message().chat().id(), user.getUrls());
    }

    @Override
    public String describe() {
        return "/list - list of tracked urls\n";
    }
}
