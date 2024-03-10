package bot;

import edu.java.bot.botControl.URLChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class URlCheckerTest {
    private URlCheckerTest() {
    }

    @Test
    @DisplayName("url checker tests")
    public void checkUrlTests() {
        Assertions.assertTrue(new URLChecker().check("https://github.com/GitHubClient/Repository"));
        Assertions.assertTrue(new URLChecker().check("https://stackoverflow.com/Questrion_10100101"));
        Assertions.assertFalse(new URLChecker().check("https://edu.tinkoff.ru/my-activities"));
    }
}
