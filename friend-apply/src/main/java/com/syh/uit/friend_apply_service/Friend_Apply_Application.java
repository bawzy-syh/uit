package com.syh.uit.friend_apply_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.syh.uit.friend_apply_service.dao")
public class Friend_Apply_Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new Friend_Apply_Application()
                .configure(new SpringApplicationBuilder(Friend_Apply_Application.class))
                .run(args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
