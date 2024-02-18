package edu.java.bot.users;

import edu.java.bot.botControl.URLChecker;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class User {
    private String name;
    private Long id;
    private Status status;
    private List<String> urls;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        status = Status.NONE;
        urls = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getUrls() {
        if (!urls.isEmpty()) {
            return urls.toString().substring(1, urls.toString().length() - 1);
        }
        return "You do not have tracked urls";
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String addUrl(String url) {
        URLChecker checker = new URLChecker();
        if (checker.check(url)) {
            urls.add(url);
            return "You are tracking this url now";
        }
        return "Your text is not an url";
    }

    public String removeUrl(String url) {
        URLChecker checker = new URLChecker();
        if (checker.check(url)) {
            urls.remove(url);
            return "You are not tracking this url now";
        }
        return "Your text is not an url";
    }
}
