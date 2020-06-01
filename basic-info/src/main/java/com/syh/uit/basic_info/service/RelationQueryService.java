package com.syh.uit.basic_info.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RelationQueryService {
    private RestTemplate restTemplate;
    @Autowired
    private void setRestTemplate(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${serviceURL.relation-manager-URL}")
    private String url;

    private enum HasRelationResult{
        FALSE(0,false), TRUE(1,true);
        private final int code;
        private final boolean answer;
        HasRelationResult(int code,boolean answer){
            this.code=code;
            this.answer = answer;
        }
        public boolean getAnswer(){
            return answer;
        }
        public static HasRelationResult getByCode(int value) throws IllegalArgumentException{
            for(HasRelationResult typeEnum : HasRelationResult.values()) {
                if(typeEnum.code == value) {
                    return typeEnum;
                }
            }
            throw new IllegalArgumentException("No element matches " + value);
        }
    }
    boolean hasRelation(int id1, int id2)throws APIGeneralException {
        String request = url+"?id1="+id1+"&id2="+id2;
        Integer result;
        try {
            result = restTemplate.getForObject(request,Integer.class);
        }catch (RestClientException e){
            throw new InternalServerException(e.getMessage());
        }
        try{
            Assert.notNull(result,"bad response");
            return HasRelationResult.getByCode(result).getAnswer();
        }catch (IllegalArgumentException e){
            throw new InternalServerException(e.getMessage());
        }
    }
}
