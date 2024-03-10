package bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.botControl.BotControl;
import edu.java.bot.commands.LinkAdder;
import edu.java.bot.commands.LinkRemover;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.users.User;
import edu.java.bot.users.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelegramBotTest {
    private TelegramBotTest() {
    }

    private static final long ID = 10L;
    private static final String GITHUB_LINK = "https://github.com/";
    private static final String STACKOVERFLOW_LINK = "https://stackoverflow.com/";

    @Test
    @DisplayName("not authorized test")
    void CommandsHandlerTest() {
        Message message = mock(Message.class);
        Update update = mock(Update.class);
        Chat chat = mock(Chat.class);
        BotControl control = new BotControl();
        when(update.message()).thenReturn(message);
        when(update.message().chat()).thenReturn(chat);
        when(update.message().chat().username()).thenReturn("HromBromIod");
        when(update.message().chat().id()).thenReturn(ID);
        when(update.message().text()).thenReturn("/track");
        assertThat(control.handle(update).getParameters().get("text"))
            .isEqualTo("You are not authorized");
    }

    @Test
    @DisplayName("start command test")
    public void startCommandTest() {
        UserRepository users = new UserRepository();
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("/start");
        when(update.message().chat()).thenReturn(new Chat());
        SendMessage response = new Start().apply(update, users);
        assertThat("Registration was successful\nWelcome!").isEqualTo(response.getParameters().get("text"));
    }

    @Test
    @DisplayName("track command test")
    void TrackCommandTest() {

        UserRepository users = new UserRepository();
        User user = new User(ID, "HromBromIod");
        users.mapOfUsers.put(ID, user);
        Message message = mock(Message.class);
        Update update = mock(Update.class);
        Chat chat = mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(update.message().text()).thenReturn(GITHUB_LINK);
        when(update.message().chat()).thenReturn(chat);
        when(update.message().chat().id()).thenReturn(ID);
        SendMessage response = new LinkAdder().apply(update, users);
        Assertions.assertTrue(users.mapOfUsers.get(ID).getUrls().contains(GITHUB_LINK));
    }

    @Test
    @DisplayName("untrack command test")
    void untrackCommandTest() {

        UserRepository users = new UserRepository();
        User user = new User(ID, "HromBromIod");
        user.addUrl(STACKOVERFLOW_LINK);
        users.mapOfUsers.put(ID, user);
        Message message = mock(Message.class);
        Update update = mock(Update.class);
        Chat chat = mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(update.message().text()).thenReturn(STACKOVERFLOW_LINK);
        when(update.message().chat()).thenReturn(chat);
        when(update.message().chat().id()).thenReturn(ID);
        SendMessage response = new LinkRemover().apply(update, users);
        Assertions.assertFalse(users.mapOfUsers.get(ID).getUrls().contains(STACKOVERFLOW_LINK));
    }

    @Test
    @DisplayName("list command test")
    void listCommandTest() {

        UserRepository users = new UserRepository();
        User user = new User(ID, "HromBromIod");
        users.mapOfUsers.put(ID, user);
        users.mapOfUsers.get(ID).addUrl(GITHUB_LINK);
        users.mapOfUsers.get(ID).addUrl(STACKOVERFLOW_LINK);
        Message message = mock(Message.class);
        Update update = mock(Update.class);
        Chat chat = mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(update.message().text()).thenReturn("/list");
        when(update.message().chat()).thenReturn(chat);
        when(update.message().chat().username()).thenReturn("HromBromIod");
        when(update.message().chat().id()).thenReturn(ID);
        SendMessage response = new List().apply(update, users);
        assertThat(response.getParameters().get("text")).isEqualTo(GITHUB_LINK + '\n' + STACKOVERFLOW_LINK + '\n');
    }

    @Test
    @DisplayName("empty list command test")
    void emptyListCommandTest() {

        UserRepository users = new UserRepository();
        User user = new User(ID, "HromBromIod");
        users.mapOfUsers.put(ID, user);
        Message message = mock(Message.class);
        Update update = mock(Update.class);
        Chat chat = mock(Chat.class);
        when(update.message()).thenReturn(message);
        when(update.message().text()).thenReturn("/list");
        when(update.message().chat()).thenReturn(chat);
        when(update.message().chat().username()).thenReturn("HromBromIod");
        when(update.message().chat().id()).thenReturn(ID);
        SendMessage response = new List().apply(update, users);
        assertThat(response.getParameters().get("text")).isEqualTo("You do not have tracked urls");
    }
}
