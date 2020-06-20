package com.syh.uit.relation_group_info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.syh.uit.relation_group_info.dao")
@EnableFeignClients
public class RelationGroupInfo_Application {
    public static void main(String[] args) {
        SpringApplication.run(RelationGroupInfo_Application.class, args);
    }
}
