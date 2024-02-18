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
        try {
            URL isLink = new URL(link);
            return !urls.stream()
                .filter(link::startsWith)
                .toList()
                .isEmpty();
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
