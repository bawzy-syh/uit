package com.syh.uit.push_server.model;

public class PushOrder {
    private final String target;
    private final String message;

    public PushOrder(String target, String message) {
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }
}
