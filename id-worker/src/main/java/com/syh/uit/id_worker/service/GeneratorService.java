package com.syh.uit.id_worker.service;

import com.syh.uit.id_worker.config.IdWorkerConfig;
import com.syh.uit.id_worker.util.SnowflakeIdWorker;
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
