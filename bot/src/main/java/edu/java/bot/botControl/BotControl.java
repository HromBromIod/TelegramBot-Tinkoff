package edu.java.bot.botControl;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.LinkAdder;
import edu.java.bot.commands.LinkRemover;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Stop;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.UnTrack;
import edu.java.bot.users.Status;
import edu.java.bot.users.UserRepository;
import java.util.Map;

@SuppressWarnings({"MultipleStringLiterals", "ReturnCount"})
public class BotControl {
    private final Map<String, Command> commands = Map.of(
        "/help", new Help(),
        "/start", new Start(),
        "/track", new Track(),
        "/untrack", new UnTrack(),
        "/list", new List(),
        "/stop", new Stop()
    );

    private final Map<Status, Command> trackingCommands = Map.of(
        Status.ADD_LINK, new LinkAdder(),
        Status.REMOVE_LINK, new LinkRemover()
    );

    private final UserRepository users;

    public BotControl() {
        users = new UserRepository();
    }

    public SendMessage handle(Update update) {
        Long id = update.message().chat().id();
        if (users.find(id) != null && !users.find(id).getStatus().equals(Status.NONE)) {
            return trackingCommands.get(users.find(id).getStatus()).apply(update, users);
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
