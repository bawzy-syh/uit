package com.syh.uit.push_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebsocketSecurityConfig {
    //todo
    /*@Bean
    JWTVerifier jwtVerifier(){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                //.withIssuer("auth0")
                .build(); //Reusable verifier instance
        return verifier;
    }*/

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
