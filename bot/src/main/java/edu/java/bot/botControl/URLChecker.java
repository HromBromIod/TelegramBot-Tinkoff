package edu.java.bot.botControl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public class URLChecker {
    private final List<String> urls = Arrays.asList("https://github.com", "https://stackoverflow.com");

    public boolean check(String link) {
        return checkURL(link) && urls.stream().anyMatch(link::startsWith);
    }

    private boolean checkURL(String link) {
        try {
            new URL(link);
            return true;
        } catch (MalformedURLException | IllegalArgumentException exception) {
            return false;
        }
    }
}
