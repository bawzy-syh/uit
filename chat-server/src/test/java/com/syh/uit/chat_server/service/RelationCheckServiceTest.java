package com.syh.uit.chat_server.service;

import com.syh.uit.chat_server.config.ServiceURLConfig;
import com.syh.uit.chat_server.model.RelationInfo;
import com.syh.uit.exception.exception.APIGeneralException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RelationCheckService.class)
class RelationCheckServiceTest {
    @Bean
    ServiceURLConfig urlConfig(){
        return new ServiceURLConfig();
    }
    @Autowired
    private RelationCheckService relationCheckService;
    @Test
    void getRelationInfo() {
        try {
            RelationInfo info = relationCheckService.getRelationInfo(1);
            System.out.println(info);
        } catch (APIGeneralException e) {
            e.printStackTrace();
        }
    }
}