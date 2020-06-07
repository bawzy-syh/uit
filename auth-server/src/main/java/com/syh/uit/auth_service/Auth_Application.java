package com.syh.uit.auth_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.syh.uit.auth_service.dao")
public class Auth_Application {
    public static void main(String[] args) {
        SpringApplication.run(Auth_Application.class,args);
    }
}
