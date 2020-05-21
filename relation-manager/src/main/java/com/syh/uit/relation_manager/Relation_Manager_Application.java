package com.syh.uit.relation_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Relation_Manager_Application {
    public static void main(String[] args) {
        SpringApplication.run(Relation_Manager_Application.class, args);
    }
}
