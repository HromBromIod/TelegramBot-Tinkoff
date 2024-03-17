package edu.java.service.jdbc;

import edu.java.domain.dao.repository.JdbcChat;
import edu.java.domain.dao.repository.JdbcChatLink;
import edu.java.domain.dao.repository.JdbcLink;
import edu.java.domain.dto.jdbc.Chat;
import edu.java.domain.dto.jdbc.ChatLink;
import edu.java.domain.dto.jdbc.Link;
import edu.java.errors.ChatAlreadyExistsException;
import edu.java.errors.ChatNotExistsException;
import edu.java.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final JdbcLink jdbcLinkDao;
    private final JdbcChatLink jdbcChatLinkDao;
    private final JdbcChat jdbcChatDao;

    @Override
    public void register(long tgChatId, String username) {
        if (jdbcChatDao.findAll().stream().anyMatch(c -> c.getChat_id() == tgChatId)) {
            throw new ChatAlreadyExistsException("Чат уже создан!");
        }
        Chat chatDto = new Chat(tgChatId, OffsetDateTime.now(), username);
        jdbcChatDao.add(chatDto);
    }

    @Override
    public void unregister(long tgChatId) {
        if (jdbcChatDao.findAll().stream().noneMatch(c -> c.getChat_id() == tgChatId)) {
            throw new ChatNotExistsException("Чат не был создал ранее!");
        }
        List<Link> links = jdbcChatLinkDao.getAllLinkByChat(tgChatId);
        for (var link : links) {
            List<ChatLink> subs = jdbcChatLinkDao.getAllTgChatsByLinkId(link.getLinkId());
            jdbcChatLinkDao.remove(new ChatLink(tgChatId, link.getLinkId()));
            if (subs.size() == 1) {
                jdbcLinkDao.remove(subs.getFirst().getLinkId());
            }
        }
        jdbcChatDao.remove(tgChatId);
    }
}
