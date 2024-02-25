package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.UserRepository;

public interface Command {
    public SendMessage apply(Update update, UserRepository users);
    public String describe();
}
