package com.syh.uit.push_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class PushServer_Application {
    public static void main(String[] args) {
        SpringApplication.run(PushServer_Application.class,args);
    }
}
