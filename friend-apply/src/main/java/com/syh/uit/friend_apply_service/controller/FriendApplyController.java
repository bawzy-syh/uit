package com.syh.uit.friend_apply_service.controller;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.friend_apply_service.model.FriendApply;
import com.syh.uit.friend_apply_service.model.request.Creat_FriendApply;
import com.syh.uit.friend_apply_service.model.request.Reply_FriendApply;
import com.syh.uit.friend_apply_service.model.request.Update_FriendApply;
import com.syh.uit.friend_apply_service.model.response.FriendApplyList;
import com.syh.uit.friend_apply_service.service.FriendApply_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@EnableGlobalMethodSecurity
@RestController
public class FriendApplyController {
    private FriendApply_Service friendApplyService;

    @Autowired
    public void setFriendApplyService(FriendApply_Service friendApplyService) {
        this.friendApplyService = friendApplyService;
    }

    /**
     * @apiNote 获取一项好友请求
     * @param id applicationID to get
     * @return FriendApply
     * @throws Exception exception
     */
    @RequestMapping(value = "/{id}",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private FriendApply getDetail(@PathVariable String id) throws Exception {
        long long_id;
        try{
            long_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new BadRequestException(id+" is not legal applicationID");
        }
        return friendApplyService.getDetail(long_id);
    }

    /**
     * @apiNote 删除好友请求
     * @param id applicationID for delete
     * @throws Exception exception
     */
    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteResource(@PathVariable String id) throws Exception {
        long long_id;
        try{
            long_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new BadRequestException(id+" is not legal applicationID");
        }
        friendApplyService.deleteApply(long_id);
    }

    /**
     * @apiNote 获取好友请求列表
     * @return 发送的和接受的好友请求
     */
    //@PathVariable是路径上的变量,@RequestParam是地址后？之后的变量
    @RequestMapping(value = "/",method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    private FriendApplyList getList() throws APIGeneralException {
        return friendApplyService.getList();
    }

    /**
     * @apiNote 创建新的请求
     * @param apply FriendApply to creat
     * @return the Apply just been created
     * @throws Exception exception
     */
    @RequestMapping(value = "/",method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    private void creatResource(@Valid@RequestBody Creat_FriendApply apply) throws Exception{
        friendApplyService.creatApply(apply);
    }

    /**
     * @apiNote 修改已有的请求
     * @param apply modified FriendApply
     * @param id applicationID to modify
     * @throws Exception exception
     */
    @RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
    @ResponseStatus(HttpStatus.CREATED)
    private void updateResource(@Valid@RequestBody Update_FriendApply apply, @PathVariable String id) throws Exception{
        long long_id;
        try{
            long_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new BadRequestException(id+" is not legal applicationID");
        }
        apply.setApplicationID(long_id);
        friendApplyService.updateApply(apply);
    }

    /**
     * @apiNote 回复好友请求
     * @param apply reply
     * @throws Exception exception
     */
    @RequestMapping(value = "/reply",method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    private void postReply(@Valid@RequestBody Reply_FriendApply apply) throws Exception{
        friendApplyService.postReply(apply);
    }

/*
    @Pointcut("execution(private FriendApplyController com.syh.uit.friend_apply_service.controller.FriendApplyController.getDetail(String))")
    private void parseJSONbyAuth() {

    }
    */

}
