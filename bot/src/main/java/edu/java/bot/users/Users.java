package edu.java.bot.users;

import java.util.HashMap;
import java.util.Map;

public class Users {
    public Users() {
    }

    public Map<Long, User> mapOfUsers = new HashMap<>();

    public User find(Long id) {
        return mapOfUsers.getOrDefault(id, null);
    }

    public String add(User newUser) {
        try {
            mapOfUsers.put(newUser.getId(), newUser);
            return newUser.getId() + "was added";
        } catch (NullPointerException exception) {
            return exception.getMessage();
        }
    }

    public String remove(Long id) {
        mapOfUsers.remove(id);
        return id + "was removed";
    }
}
