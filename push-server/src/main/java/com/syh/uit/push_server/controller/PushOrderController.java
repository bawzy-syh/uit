package com.syh.uit.push_server.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.push_server.model.PushOrder;
import com.syh.uit.push_server.service.MessagePushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PushOrderController {
    private MessagePushService pushService;
    @Autowired
    private void setPushService(MessagePushService pushService) {
        this.pushService = pushService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    void getOrder(@RequestBody PushOrder pushOrder) throws APIGeneralException {
        pushService.processPushOrder(pushOrder);
    }
}
