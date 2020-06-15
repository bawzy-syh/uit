package com.syh.uit.id_worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Snowflake_Generator_Application {
    public static void main(String[] args) {
        SpringApplication.run(Snowflake_Generator_Application.class, args);
    }

}
