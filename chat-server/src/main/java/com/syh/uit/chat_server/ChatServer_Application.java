package com.syh.uit.chat_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.syh.uit.chat_server.dao")
public class ChatServer_Application {
    public static void main(String[] args) {
        SpringApplication.run(ChatServer_Application.class,args);
    }

}
