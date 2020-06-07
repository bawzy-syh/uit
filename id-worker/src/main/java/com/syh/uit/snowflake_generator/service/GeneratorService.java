package com.syh.uit.snowflake_generator.service;

import com.syh.uit.snowflake_generator.config.IdWorkerConfig;
import com.syh.uit.snowflake_generator.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class GeneratorService {
    private IdWorkerConfig idWorkerConfig;

    @Autowired
    public void setIDWorkerConfig(IdWorkerConfig idWorkerConfig) {
        this.idWorkerConfig = idWorkerConfig;
    }

    @Bean
    private SnowflakeIdWorker getWorker(){
        return new SnowflakeIdWorker(idWorkerConfig.getWorker(),idWorkerConfig.getDataCenter());
    }
    public long newID(){
        return getWorker().nextId();
    }
}
