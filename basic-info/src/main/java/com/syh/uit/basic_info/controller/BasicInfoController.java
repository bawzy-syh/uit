package com.syh.uit.basic_info.controller;

import com.syh.uit.basic_info.model.BasicInfo;
import com.syh.uit.basic_info.model.request.UpdateBasicInfoRequest;
import com.syh.uit.basic_info.service.BasicInfoService;
import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BasicInfoController {
    private BasicInfoService basicInfoService;
    @Autowired
    public void setBasicInfoService(BasicInfoService basicInfoService) {
        this.basicInfoService = basicInfoService;
    }

    /**
     * @apiNote 获取本人基本信息
     * @return 本人基本信息
     */
    @RequestMapping(value = "/self" ,method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private BasicInfo getSelfInfo()throws APIGeneralException {
        return basicInfoService.getSelfBasisInfo();
    }

    /**
     * @param uid 要获取info的用户uid
     * @return 指定的用户基本信息
     */
    @RequestMapping(value = "/{uid}" ,method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private BasicInfo getBasicInfoByID(@PathVariable String uid)throws APIGeneralException {
        int int_uid;
        try {
            int_uid = Integer.parseInt(uid);
        }catch (NumberFormatException e){
            throw new BadRequestException(uid+" is not valid uid");
        }
        return basicInfoService.getBasicInfoById(int_uid);
    }

    /**
     * @apiNote 更新部分基本信息
     * @param updateBasicInfoRequest request to update
     */
    @RequestMapping(value = "/self" ,method = {RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.CREATED)
    private void updateSelfInfo(@RequestBody UpdateBasicInfoRequest updateBasicInfoRequest)throws APIGeneralException {
        basicInfoService.updateBasicInfo(updateBasicInfoRequest);
    }
    @RequestMapping(value = "/self" ,method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.CREATED)
    private void updateAllSelfInfo(@RequestBody@Valid UpdateBasicInfoRequest updateBasicInfoRequest)throws APIGeneralException {
        this.updateSelfInfo(updateBasicInfoRequest);
    }

}
