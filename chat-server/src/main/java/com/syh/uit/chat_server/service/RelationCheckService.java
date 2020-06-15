package com.syh.uit.chat_server.service;

import com.syh.uit.chat_server.config.ServiceURLConfig;
import com.syh.uit.chat_server.model.RelationInfo;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.InternalServerException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class RelationCheckService {
    private ServiceURLConfig urlConfig;
    private RestTemplate restTemplate;
    private RedisTemplate<String,Object> redisTemplate;
    private final long keyExpireSeconds = 60*60;
    @Autowired
    private void setUrlConfig(ServiceURLConfig urlConfig) {
        this.urlConfig = urlConfig;
    }
    @Autowired
    private void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    private void setRedisTemplate(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @param relationID relationID to inquire
     * @return relationID对应的relationInfo 不存在时返回null
     * @throws APIGeneralException exception
     */
    RelationInfo getRelationInfo(long relationID)throws APIGeneralException {
        RelationInfo inCache;
        try{
             inCache = getFromRedis(relationID);
        }catch (Exception e){
            LoggerFactory.getLogger(RelationCheckService.class).error(e.getMessage());
            return getFromService(relationID);
        }
        return inCache!=null ? inCache : getFromService(relationID);
    }

    private RelationInfo getFromRedis(long relationID) {
        RelationInfo info = (RelationInfo) redisTemplate.opsForValue().get(relationID+"");
        if (info==null)return null;
        redisTemplate.expire(relationID+"",keyExpireSeconds,TimeUnit.SECONDS);
        return info;
    }

    private RelationInfo getFromService(long relationID)throws APIGeneralException {
        ResponseEntity<RelationInfo> resp;
        try{
             resp = restTemplate.getForEntity(urlConfig.getRelationManager()+"/{relationID}",RelationInfo.class,relationID);
        }catch (RestClientException e){
            throw new InternalServerException("get RelationInfo failed:"+e.getMessage());
        }
        //不存在的资源返回null
        if (resp.getStatusCode() == HttpStatus.NOT_FOUND)return null;
        else if (resp.getStatusCode()!=HttpStatus.OK){
            throw new InternalServerException("get RelationInfo failed:"+resp.getStatusCode()+resp.getBody());
        }
        RelationInfo result = resp.getBody();
        //response转化失败
        if (result==null)throw new InternalServerException("bad response when GET "+urlConfig.getRelationManager()+relationID);
        //保存到缓存
        save2Redis(result);
        return result;
    }

    private void save2Redis(RelationInfo info){
        redisTemplate.opsForValue().set(info.getRelationID()+"",info);
        redisTemplate.expire(info.getRelationID()+"",keyExpireSeconds, TimeUnit.SECONDS);
    }
}
