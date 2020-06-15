package com.syh.uit.chat_server.dao;

import com.syh.uit.chat_server.model.ChatMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageMapper {
    void insertChatMessage(ChatMessage message);
}
