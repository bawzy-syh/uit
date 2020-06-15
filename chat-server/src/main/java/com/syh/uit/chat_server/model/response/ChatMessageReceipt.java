package com.syh.uit.chat_server.model.response;

public class ChatMessageReceipt {
    private final long messageID;
    private final long timestamp;

    public ChatMessageReceipt(long messageID, long timestamp) {
        this.messageID = messageID;
        this.timestamp = timestamp;
    }

    public long getMessageID() {
        return messageID;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
