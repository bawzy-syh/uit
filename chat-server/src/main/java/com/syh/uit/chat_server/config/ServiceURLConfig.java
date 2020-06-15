package com.syh.uit.chat_server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "service-url")
public class ServiceURLConfig {
    private String idGenerator;
    private String relationManager;

    public String getIdGenerator() {
        return idGenerator;
    }

    public String getRelationManager() {
        return relationManager;
    }

    public void setIdGenerator(String idGenerator) {
        this.idGenerator = idGenerator;
    }

    public void setRelationManager(String relationManager) {
        this.relationManager = relationManager;
    }
}
