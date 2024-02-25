package edu.java.bot.users;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public UserRepository() {
    }

    public Map<Long, User> mapOfUsers = new HashMap<>();

    public User find(Long id) {
        return mapOfUsers.getOrDefault(id, null);
    }

    public void add(User newUser) {
        if (!mapOfUsers.containsKey(newUser.getId())) {
            mapOfUsers.put(newUser.getId(), newUser);
        }
    }

    public void remove(Long id) {
        mapOfUsers.remove(id);
    }
}
