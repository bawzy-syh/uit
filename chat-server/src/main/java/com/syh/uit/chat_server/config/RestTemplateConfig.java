package com.syh.uit.chat_server.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nonnull;
@Configuration
public class RestTemplateConfig{
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    static class RestTemplateErrorHandler extends DefaultResponseErrorHandler{
        //private final ObjectMapper objectMapper = new ObjectMapper();
        @Override
        protected boolean hasError(@Nonnull HttpStatus statusCode) {
            return false;
        }
       /* @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            RestTemplateConfig.APIGeneralException exception = objectMapper.readValue(response.getBody(), RestTemplateConfig.APIGeneralException.class);
            throw new com.syh.uit.exception.exception.APIGeneralException(response.getStatusCode(),exception.getError(),exception.getMessage());
        }*/
    }
}
