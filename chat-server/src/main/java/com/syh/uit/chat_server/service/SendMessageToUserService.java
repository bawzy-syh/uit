package com.syh.uit.chat_server.service;

import com.syh.uit.exception.exception.APIGeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendMessageToUserService {
    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    void sendToUser()throws APIGeneralException {

    }
}
