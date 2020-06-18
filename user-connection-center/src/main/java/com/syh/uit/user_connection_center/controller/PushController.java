package com.syh.uit.user_connection_center.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.user_connection_center.module.PushRequest;
import com.syh.uit.user_connection_center.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PushController {
    private DeliverService deliverService;
    @Autowired
    public void setDeliverService(DeliverService deliverService) {
        this.deliverService = deliverService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    private void pushRequest(@Valid @RequestBody PushRequest pushRequest) throws APIGeneralException {
        deliverService.processPushRequest(pushRequest);
    }
}
