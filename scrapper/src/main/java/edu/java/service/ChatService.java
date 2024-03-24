package edu.java.service;

public interface ChatService {
    void register(long tgChatId, String username);
    void unregister(long tgChatId);
}
