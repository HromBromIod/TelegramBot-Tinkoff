package edu.java.bot.botControl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Stop;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.UnTrack;
import edu.java.bot.users.Status;
import edu.java.bot.users.Users;
import java.util.Map;

@SuppressWarnings({"MultipleStringLiterals", "ReturnCount"})
public class BotControl {
    Map<String, Command> commands = Map.of(
        "/start", new Start(),
        "/help", new Help(),
        "/track", new Track(),
        "/untrack", new UnTrack(),
        "/list", new List(),
        "/stop", new Stop()
    );

    public BotControl() {
    }

    public SendMessage handle(Update update, Users users) {
        Long id = update.message().chat().id();
        if (users.find(id) != null && users.find(id).getStatus().equals(Status.ADD_LINK)) {
            users.find(id).setStatus(Status.NONE);
            return new SendMessage(id, users.find(id).addUrl(update.message().text()));
        }
        if (users.find(id) != null && users.find(id).getStatus().equals(Status.REMOVE_LINK)) {
            users.find(id).setStatus(Status.NONE);
            return new SendMessage(id, users.find(id).removeUrl(update.message().text()));
        }

        String message = update.message().text();
        if (users.find(update.message().chat().id()) == null
            && !message.equals("/start")) {
            return new SendMessage(update.message().chat().id(), "You are not authorized");
        }
        if (commands.containsKey(message)) {
            return commands.get(message).apply(update, users);
        }
        return new SendMessage(update.message().chat().id(), "This command is not allowed");
    }
}
