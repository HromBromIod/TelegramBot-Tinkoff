package edu.java.service.jdbc;

import edu.java.domain.dao.repository.JdbcChatLink;
import edu.java.domain.dao.repository.JdbcLink;
import edu.java.domain.dto.jdbc.ChatLink;
import edu.java.domain.dto.jdbc.Link;
import edu.java.errors.LinkWasNotTrackedException;
import edu.java.service.LinkService;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.LinkResponse;
import org.example.dto.response.ListLinksResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcLinkService implements LinkService {
    private final JdbcLink jdbcLinkDao;
    private final JdbcChatLink jdbcChatLinkDao;

    @Override
    public void add(long tgChatId, URI url) {
        List<Link> linkDtoList = jdbcLinkDao.getByUri(url);
        if (linkDtoList.isEmpty()) {
            addLinkIfNotExist(tgChatId, url);
        } else {
            jdbcChatLinkDao.add(new ChatLink(tgChatId, linkDtoList.getFirst().getLinkId()));
        }
    }

    @Override
    public void remove(long tgChatId, URI url) {
        List<Link> linkDtoList = jdbcLinkDao.getByUri(url);
        if (linkDtoList.isEmpty()) {
            throw new LinkWasNotTrackedException("Ссылка не отслеживается!");
        }
        ChatLink chatLinkDto = new ChatLink(tgChatId, linkDtoList.getFirst().getLinkId());

        jdbcChatLinkDao.remove(chatLinkDto);
        List<ChatLink> linkDtos = jdbcChatLinkDao.getAllTgChatsByLinkId(linkDtoList.getFirst().getLinkId());
        if (linkDtos.isEmpty()) {
            jdbcLinkDao.remove(linkDtos.getFirst().getLinkId());
        }
    }

    @Override
    public ListLinksResponse listAll(long tgChatId) {
        List<Link> chatLinkDtoList = jdbcChatLinkDao.getAllLinkByChat(tgChatId);
        List<LinkResponse> linkResponses = new ArrayList<>();
        for (var link : chatLinkDtoList) {
            linkResponses.add(new LinkResponse(link.getLinkId(), link.getUrl()));
        }
        return new ListLinksResponse(linkResponses, linkResponses.size());
    }

    void addLinkIfNotExist(long tgChatId, URI url) {
        OffsetDateTime data = OffsetDateTime.now();
        Link linkDto = new Link(url, data, data);
        jdbcLinkDao.add(linkDto);

        ChatLink chatLinkDto = new ChatLink(tgChatId, jdbcLinkDao.getByUri(url).getFirst().getLinkId());
        jdbcChatLinkDao.add(chatLinkDto);
    }
}
