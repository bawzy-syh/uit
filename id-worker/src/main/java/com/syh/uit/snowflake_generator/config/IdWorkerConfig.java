package com.syh.uit.snowflake_generator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="id-worker")
public class IdWorkerConfig {
    private Integer worker;
    private Integer dataCenter;

    public Integer getWorker() {
        return worker;
    }

    public Integer getDataCenter() {
        return dataCenter;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

    public void setDataCenter(Integer dataCenter) {
        this.dataCenter = dataCenter;
    }
}
