package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.users.Users;

public class Help implements Command {
    @Override
    public SendMessage apply(Update update, Users users) {
        String help = "/help - list of commands\n";
        String start = "/start - authorize\n";
        String stop = "/stop - unauthorize\n";
        String track = "/track - track an url\n";
        String untrack = "/untrack - untrack an url\n";
        String list = "/list - list of tracked urls\n";
        return new SendMessage(update.message().chat().id(), help + start + stop + track + untrack + list);
    }
}
