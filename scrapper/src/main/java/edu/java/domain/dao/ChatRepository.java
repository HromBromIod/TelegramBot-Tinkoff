package edu.java.domain.dao;

import edu.java.domain.dto.jdbc.Chat;
import java.util.List;

public interface ChatRepository {
    void add(Chat object);
    void remove(Long id);
    List<Chat> findAll();
}
