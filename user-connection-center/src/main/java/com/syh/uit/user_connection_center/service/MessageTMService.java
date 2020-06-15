package com.syh.uit.user_connection_center.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.InternalServerException;
import com.syh.uit.user_connection_center.module.PushOrder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageTMService {
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private void setRedisTemplate(@Qualifier("messageTSRedis") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    void store(PushOrder order) throws APIGeneralException {
        try{
            redisTemplate.opsForList().leftPush(order.getTarget(),order.getMessage());
        }catch (Exception e){
            LoggerFactory.getLogger(MessageTMService.class).error("error",e);
            throw new InternalServerException("something goes wrong when store message");
        }

        //redisTemplate.expire()
    }
}
