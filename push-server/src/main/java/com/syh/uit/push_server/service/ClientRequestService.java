package com.syh.uit.push_server.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.push_server.model.ConnectionInfo;
import com.syh.uit.push_server.model.Endpoint;
import org.springframework.stereotype.Service;

@Service
public class ClientRequestService {
    private final EndpointConvertService endpointConvertService;
    private final MessagePushService messagePushService;
    private final WebsocketRegisterService websocketRegisterService;

    public ClientRequestService(EndpointConvertService endpointConvertService, MessagePushService messagePushService, WebsocketRegisterService websocketRegisterService) {
        this.endpointConvertService = endpointConvertService;
        this.messagePushService = messagePushService;
        this.websocketRegisterService = websocketRegisterService;
    }

    public void flush(int uid, String endpointString) throws APIGeneralException {
        //todo:验证uid存在性
        Endpoint endpoint = convertByString(endpointString);
        messagePushService.flushCache(new ConnectionInfo(uid,endpoint));
    }
    public void auth(int uid, String endpointString, String token) throws APIGeneralException {
        Endpoint endpoint = convertByString(endpointString);
        websocketRegisterService.updateConnectAuth(new ConnectionInfo(uid,endpoint), token);
    }

    private Endpoint convertByString(String string) throws APIGeneralException {
        try{
            return endpointConvertService.convertByName(string);
        }catch (IllegalArgumentException e){
            throw new BadRequestException(string+" is not valid endpoint");
        }

    }
}
