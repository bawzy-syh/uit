package com.syh.uit.chat_server.service;

import com.syh.uit.chat_server.config.ServiceURLConfig;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageIDService {
    private ServiceURLConfig serviceURLConfig;
    private RestTemplate restTemplate;
    @Autowired
    private void setServiceURLConfig(ServiceURLConfig serviceURLConfig) {
        this.serviceURLConfig = serviceURLConfig;
    }
    @Autowired
    private void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public long getNewID()throws APIGeneralException{
        Long aLong;
        try{
            aLong = restTemplate.getForObject(serviceURLConfig.getIdGenerator(), Long.class);
        }catch (RestClientException e){
            throw new InternalServerException("Bad Response:"+e.getMessage());
        }
        if (aLong==null){
            throw new InternalServerException("Bad Response,can't parse2Long");
        }
        return aLong;
    }
}
