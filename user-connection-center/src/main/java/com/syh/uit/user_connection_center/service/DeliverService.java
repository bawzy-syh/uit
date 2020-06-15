package com.syh.uit.user_connection_center.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.user_connection_center.module.PushOrder;
import com.syh.uit.user_connection_center.module.PushRequest;
import com.syh.uit.user_connection_center.module.inner.Endpoint;
import com.syh.uit.user_connection_center.module.inner.UserOnlineState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverService {
    private OnlineStateService onlineStateService;
    private EndpointConvertService endpointConverter;
    private MessageTMService messageTMService;
    private MessagePushService messagePushService;
    @Autowired
    private void setOnlineStateService(OnlineStateService onlineStateService) {
        this.onlineStateService = onlineStateService;
    }
    @Autowired
    private void setMessageTMService(MessageTMService messageTMService) {
        this.messageTMService = messageTMService;
    }
    @Autowired
    private void setEndpointConverter(EndpointConvertService endpointConverter) {
        this.endpointConverter = endpointConverter;
    }
    @Autowired
    private void setMessagePushService(MessagePushService messagePushService) {
        this.messagePushService = messagePushService;
    }

    public void processPushRequest(PushRequest request) throws APIGeneralException {
        UserOnlineState userState = onlineStateService.getOnlineState(request.getUid());
        List<Endpoint> messageDest = endpointConverter.convertByNameList(request.getEndpoints());
        for (Endpoint dest: messageDest){
            PushOrder order = new PushOrder(request.getUid(),dest,request.getServiceName(),request.getMessage());
            if (userState.getStates().contains(dest)){
                //在线,发送
                messagePushService.pushNow(order);
            }else{
                //不在线,存入缓存
                messageTMService.store(order);
            }
        }
    }
}
