package com.syh.uit.relation_info.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "relation-info-service")
public class RelationInfoServiceConfig {
    private Integer petNameMaxLength;

    public void setPetNameMaxLength(Integer petNameMaxLength) {
        this.petNameMaxLength = petNameMaxLength;
    }

    public int getPetNameMaxLength() {
        return petNameMaxLength;
    }
}
