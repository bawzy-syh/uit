package com.syh.uit.chat_server.model.request;

import javax.validation.constraints.NotNull;

public class ChatMessageRequest {
    @NotNull
    private final Long relationID;
    @NotNull
    private final String content;

    public ChatMessageRequest(@NotNull Long relationID, @NotNull String content) {
        this.relationID = relationID;
        this.content = content;
    }

    public Long getRelationID() {
        return relationID;
    }

    public String getContent() {
        return content;
    }
}
