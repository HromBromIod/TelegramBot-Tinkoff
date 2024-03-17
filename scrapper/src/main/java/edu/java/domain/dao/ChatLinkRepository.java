package edu.java.domain.dao;

import edu.java.domain.dto.jdbc.ChatLink;
import edu.java.domain.dto.jdbc.Link;
import java.util.List;

public interface ChatLinkRepository {
    void add(ChatLink object);
    void remove(ChatLink id);
    List<ChatLink> findAll();
    List<ChatLink> getAllTgChatsByLinkId(long linkId);
    List<Link> getAllLinkByChat(long chatId);
}
