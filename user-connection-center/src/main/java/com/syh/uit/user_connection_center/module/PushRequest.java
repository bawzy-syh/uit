package com.syh.uit.user_connection_center.module;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PushRequest {
    @NotNull
    private final String serviceName;
    @NotNull
    private final Integer uid;
    @NotNull
    private final String message;
    @NotNull
    private final List<String> endpoints;

    public PushRequest(String serviceName, Integer uid, String message, List<String> endpoints) {
        this.serviceName = serviceName;
        this.uid = uid;
        this.message = message;
        this.endpoints = endpoints;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Integer getUid() {
        return uid;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }
}
