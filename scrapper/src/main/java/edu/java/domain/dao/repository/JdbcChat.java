package edu.java.domain.dao.repository;

import edu.java.domain.dao.ChatRepository;
import edu.java.domain.dto.jdbc.Chat;
import edu.java.errors.ChatAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcChat implements ChatRepository {

    private final JdbcTemplate jdbcTemplate;
    @Transactional
    @Override
    public void add(Chat chat) {
        try {
            jdbcTemplate.update("INSERT INTO chat(chat_id,created_at,created_by) VALUES (?,?,?)",
                chat.getChat_id(), chat.getCreatedAt(), chat.getCreatedBy()
            );
        } catch (DataAccessException exception) {
            throw new ChatAlreadyExistsException("Чат уже зарегестрирован!");
        }
    }

    @Transactional
    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM chat WHERE chat_id=?", id);
    }

    @Transactional
    @Override
    public List<Chat> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat", new BeanPropertyRowMapper<>(Chat.class));
    }
}
