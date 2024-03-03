package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.Status;
import edu.java.bot.users.UserRepository;

public class Track implements Command {
    @Override
    public SendMessage apply(Update update, UserRepository users) {
        users.find(update.message().chat().id()).setStatus(Status.ADD_LINK);
        return new SendMessage(update.message().chat().id(), "Enter link to add");
    }

    @Override
    public String describe() {
        return "/track - track an url\n";
    }
}
