package com.syh.uit.friend_apply_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplicationID_Service {
    private RestTemplate restTemplate;

    @Value("${serviceURL.IDGeneratorURL}")
    private String url;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    long getNewApplicationID() throws RestClientException {
        Long aLong = restTemplate.getForObject(url, Long.class);
        if (aLong==null){
            throw new RestClientException("Bad Response");
        }
        return aLong;
    }
}
