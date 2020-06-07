package com.syh.uit.relation_info;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.syh.uit.relation_info.dao")
public class RelationInfo_Application {
    public static void main(String[] args) {
        SpringApplication.run(RelationInfo_Application.class,args);
    }
}
