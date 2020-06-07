package com.syh.uit.relation_info.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.relation_info.model.RelationInfo;
import com.syh.uit.relation_info.model.request.UpdateRelationInfoRequest;
import com.syh.uit.relation_info.model.response.RelationInfoList;
import com.syh.uit.relation_info.service.RelationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RelationInfoController {
    private RelationInfoService relationInfoService;
    @Autowired
    private void setRelationInfoService(RelationInfoService relationInfoService){
        this.relationInfoService = relationInfoService;
    }
    /**
     * @apiNote 获取本人好友列表
     * @return 本人好友列表
     */
    @RequestMapping(value = "/" ,method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private RelationInfoList getSelfRelationList()throws APIGeneralException {
        return relationInfoService.getRelationInfoList();
    }

    /**
     * @param relationID 要删除的relation的ID
     * @throws APIGeneralException exception
     */
    @RequestMapping(value = "/{relationID}" ,method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteRelation(@PathVariable String relationID)throws APIGeneralException {
        long long_relationID;
        try{
            long_relationID = Long.parseLong(relationID);
        }catch (NumberFormatException e){
            throw new BadRequestException(relationID+" is not valid relationID");
        }
        relationInfoService.deleteRelation(long_relationID);
    }

    /**
     * @apiNote 更新RelationInfo
     * @param relationID relationID
     * @return 更新后的RelationInfo
     * @throws APIGeneralException exception to throw
     */
    @RequestMapping(value = "/{relationID}" ,method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.CREATED)
    private RelationInfo updateAllRelationInfo(@PathVariable String relationID, @RequestBody @Valid UpdateRelationInfoRequest request)throws APIGeneralException {
        return updateRelationInfo(relationID,request);
    }
    @RequestMapping(value = "/{relationID}" ,method = {RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.CREATED)
    private RelationInfo updateRelationInfo(@PathVariable String relationID, @RequestBody UpdateRelationInfoRequest request)throws APIGeneralException {
        long long_relationID;
        try{
            long_relationID = Long.parseLong(relationID);
        }catch (NumberFormatException e){
            throw new BadRequestException(relationID+" is not valid relationID");
        }
        request.setRelationID(long_relationID);
        return relationInfoService.updateRelationInfo(request);
    }
}
