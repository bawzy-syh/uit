package com.syh.uit.user_connection_center.service;

import com.syh.uit.user_connection_center.feign.PushServiceClient;
import com.syh.uit.user_connection_center.module.PushOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePushService {
    private PushServiceClient pushServiceClient;
    @Autowired
    public void setPushServiceClient(PushServiceClient pushServiceClient) {
        this.pushServiceClient = pushServiceClient;
    }
    void pushNow(PushOrder order){
        pushServiceClient.pushNow(order);
    }


}
