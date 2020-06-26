package com.syh.uit.auth_service.controller;

import com.syh.uit.auth_service.model.response.LoginSuccessResponse;
import com.syh.uit.auth_service.service.UserAuthService;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserAuthService userAuthService;
    @Autowired
    private void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    private LoginSuccessResponse login(String uid, String password)throws APIGeneralException {
        try{
            return userAuthService.login(Integer.parseInt(uid), password);
        }catch (NumberFormatException e){
            throw new BadRequestException("field 'uid' required int");
        }

    }
}
