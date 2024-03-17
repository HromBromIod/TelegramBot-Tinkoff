package edu.java.domain.dao.repository;

import edu.java.domain.dao.ChatLinkRepository;
import edu.java.domain.dto.jdbc.ChatLink;
import edu.java.domain.dto.jdbc.Link;
import edu.java.errors.LinkWasTrackedException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class JdbcChatLink implements ChatLinkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void add(ChatLink chatLink) {
        try {
            jdbcTemplate.update("INSERT INTO chat_link (chat_id,link_id) VALUES (?,?)",
                chatLink.getChatId(), chatLink.getLinkId()
            );
        } catch (DataAccessException exception) {
            throw new LinkWasTrackedException("Данная ссылка уже отслеживается!");
        }
    }

    @Transactional
    @Override
    public void remove(ChatLink chatLink) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chat_id=? AND link_id=?",
            chatLink.getChatId(), chatLink.getLinkId()
        );
    }

    @Transactional
    @Override
    public List<ChatLink> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat_link", new BeanPropertyRowMapper<>(ChatLink.class));
    }

    @Transactional
    @Override
    public List<ChatLink> getAllTgChatsByLinkId(long linkId) {
        return jdbcTemplate.query("SELECT * FROM chat_link WHERE link_id=?", new BeanPropertyRowMapper<>(ChatLink.class),linkId);
    }

    @Override
    public List<Link> getAllLinkByChat(long chatId) {
        return jdbcTemplate.query("SELECT * FROM link WHERE link_id IN (SELECT link_id FROM chat_link WHERE chat_id=?)",
            new BeanPropertyRowMapper<>(Link.class),chatId);
    }
}
