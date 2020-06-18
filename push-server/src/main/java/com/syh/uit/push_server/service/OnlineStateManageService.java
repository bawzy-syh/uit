package com.syh.uit.push_server.service;

import com.syh.uit.push_server.model.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OnlineStateManageService {
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private void setRedisTemplate(@Qualifier("online_stateRedis") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void onLine(int uid, Endpoint endpoint){
        redisTemplate.opsForSet().add(uid+"",endpoint.getName());
    }
    public void offLine(int uid, Endpoint endpoint){
        redisTemplate.opsForSet().remove(uid+"",endpoint.getName());
    }
}
