package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.UserRepository;

public class Help implements Command {
    @Override
    public SendMessage apply(Update update, UserRepository users) {
        return new SendMessage(
            update.message().chat().id(),
            describe() + new Start().describe() + new Stop().describe() + new Track().describe()
                + new UnTrack().describe() + new List().describe()
        );
    }

    @Override
    public String describe() {
        return "/help - list of commands\n";
    }
}
