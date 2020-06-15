package com.syh.uit.user_connection_center.service;

import com.syh.uit.user_connection_center.module.inner.Endpoint;
import com.syh.uit.user_connection_center.module.inner.UserOnlineState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineStateService {
    private RedisTemplate<String,Object> redisTemplate;
    private EndpointConvertService endpointConverter;
    @Autowired
    private void setRedisTemplate(@Qualifier("online_stateRedis") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Autowired
    private void setEndpointConverter(EndpointConvertService endpointConverter) {
        this.endpointConverter = endpointConverter;
    }

    UserOnlineState getOnlineState(int uid){
        String[] raw_data = (String[]) redisTemplate.opsForValue().get(uid+"");
        List<Endpoint> result = new ArrayList<>();
        //不存在即没任何设备在线
        if (raw_data==null)return new UserOnlineState(result);
        for (String name: raw_data){
            try{
                result.add(endpointConverter.convertByName(name));
            }catch (IllegalArgumentException e){
                //数据库中存储出错,清除
                removeKey(uid+"");
                return new UserOnlineState(result);
            }
        }
        return new UserOnlineState(result);
    }

    private void removeKey(String key){
        redisTemplate.delete(key);
    }
}
