package com.syh.uit.relation_manager.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.relation_manager.model.RelationInfo;
import com.syh.uit.relation_manager.service.RelationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RelationManagerController {
    private RelationManagerService relationManagerService;
    @Autowired
    public void setRelationManagerService(RelationManagerService relationManagerService) {
        this.relationManagerService = relationManagerService;
    }

    /**
     * @apiNote 查询两用户间是否为好友关系
     * @param id1 第一个uid
     * @param id2 第二个uid
     * @return int型整数,0为false,1为true
     * @throws APIGeneralException 处理时发生异常
     */
    @RequestMapping(value = "/",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private int isFriend(@RequestParam String id1, @RequestParam String id2)throws APIGeneralException {
        int int_id1,int_id2;
        try{
            int_id1 = Integer.parseInt(id1);
            int_id2 = Integer.parseInt(id2);
        }catch (NumberFormatException e){
            throw new BadRequestException(id1+" or "+id2+" is not legal uid");
        }
        return relationManagerService.hasRelationByUIDs(int_id1,int_id2);
    }

    @RequestMapping(value = "/{relationID}",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private RelationInfo getRelationInfoByID(@PathVariable String relationID)throws APIGeneralException{
        int long_id;
        try{
            long_id = Integer.parseInt(relationID);
        }catch (NumberFormatException e){
            throw new BadRequestException(relationID+" is not legal uid");
        }
        return relationManagerService.getRelationInfoByID(long_id);
    }
}
