package com.syh.uit.push_server.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.push_server.model.ConnectionInfo;
import com.syh.uit.push_server.model.PushOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePushService {
    private RedisTemplate<String, Object> redisTemplate;
    private WebsocketRegisterService websocketRegisterService;

    @Autowired
    private void setRedisTemplate(@Qualifier("messageTSRedis") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Autowired
    private void setWebsocketRegisterService(WebsocketRegisterService websocketRegisterService) {
        this.websocketRegisterService = websocketRegisterService;
    }

    public void processPushOrder(PushOrder pushOrder) throws APIGeneralException {
        websocketRegisterService.sendMessage(pushOrder.getTarget(),pushOrder.getMessage());
    }

    void flushCache(ConnectionInfo info) throws APIGeneralException {
        String key = info.toString();
        String message = (String) redisTemplate.opsForList().rightPop(key);
        while (message!=null){
            try{
                websocketRegisterService.sendMessage(key,message);
            }catch (APIGeneralException e){
                redisTemplate.opsForList().rightPush(key,message);
                throw e;
            }
            message = (String) redisTemplate.opsForList().rightPop(key);
        }

    }
}
