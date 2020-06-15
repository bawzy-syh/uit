package com.syh.uit.user_connection_center.config.user_state;

import com.syh.uit.user_connection_center.module.inner.Endpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ucc.user-online-state")
public class UserOnlineStateProperties {
    private List<Endpoint> endpoints;

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }


}
