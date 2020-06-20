package com.syh.uit.relation_group_info.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.relation_group_info.model.request.UpdateGroupInfoListRequest;
import com.syh.uit.relation_group_info.model.response.GroupInfoList;
import com.syh.uit.relation_group_info.service.GroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GroupInfoController {
    private GroupInfoService service;
    @Autowired
    public void setService(GroupInfoService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    private GroupInfoList groupInfoList() throws APIGeneralException {
        return service.getSelfGroupList();
    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    private void updateFullGroupList(@Valid@RequestBody UpdateGroupInfoListRequest request) throws APIGeneralException {
        service.updateFullGroupList(request);
    }
}
