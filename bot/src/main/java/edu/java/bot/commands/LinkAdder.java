package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.Status;
import edu.java.bot.users.UserRepository;

public class LinkAdder implements Command {
    @Override
    public SendMessage apply(Update update, UserRepository users) {
        users.find(update.message().chat().id()).setStatus(Status.NONE);
        return new SendMessage(update.message().chat().id(), users.find(update.message().chat().id()).addUrl(update.message().text()));
    }

    @Override
    public String describe() {
        return "Body of \"/Track\"\n";
    }
}
