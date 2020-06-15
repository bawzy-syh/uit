package com.syh.uit.chat_server.model;

public class ChatMessage {
    private final long messageID;
    private final long relationID;
    private final String content;
    private final long timestamp;

    public ChatMessage(long messageID, long relationID, String content, long timestamp) {
        this.messageID = messageID;
        this.relationID = relationID;
        this.content = content;
        this.timestamp = timestamp;
    }

    public long getMessageID() {
        return messageID;
    }

    public long getRelationID() {
        return relationID;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
