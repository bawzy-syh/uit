package com.syh.uit.push_server.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.push_server.service.ClientRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientRequestController {
    private final ClientRequestService service;

    public ClientRequestController(ClientRequestService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{uid}/{endpoint}/auth",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    void refreshAuth(@PathVariable String uid, @PathVariable String endpoint, String token) throws APIGeneralException {
        int int_uid;
        try{
            int_uid = Integer.parseInt(uid);
        }catch (NumberFormatException e){
            throw new BadRequestException(uid+" not valid uid");
        }
        service.auth(int_uid, endpoint, token);
    }

    @RequestMapping(value = "/{uid}/{endpoint}/auth",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    void flushCache(@PathVariable String uid, @PathVariable String endpoint) throws APIGeneralException {
        int int_uid;
        try{
            int_uid = Integer.parseInt(uid);
        }catch (NumberFormatException e){
            throw new BadRequestException(uid+" not valid uid");
        }
        service.flush(int_uid, endpoint);
    }
}
