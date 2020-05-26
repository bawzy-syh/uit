package com.syh.uit.relation_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//禁用SpringSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient
@MapperScan("com.syh.uit.relation_manager.dao")
public class Relation_Manager_Application {
    public static void main(String[] args) {
        SpringApplication.run(Relation_Manager_Application.class, args);
    }
}
