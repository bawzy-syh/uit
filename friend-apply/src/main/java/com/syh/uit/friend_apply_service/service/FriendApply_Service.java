package com.syh.uit.friend_apply_service.service;

import com.syh.uit.exception.exception.*;
import com.syh.uit.friend_apply_service.dao.ApplyMapper;
import com.syh.uit.friend_apply_service.model.FriendApply;
import com.syh.uit.friend_apply_service.model.request.Creat_FriendApply;
import com.syh.uit.friend_apply_service.model.request.Reply_FriendApply;
import com.syh.uit.friend_apply_service.model.request.Update_FriendApply;
import com.syh.uit.friend_apply_service.model.response.FriendApplyList;
import com.syh.uit.friend_apply_service.model.response.FriendApply_ResponseForApplicant;
import com.syh.uit.friend_apply_service.model.response.FriendApply_ResponseForTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendApply_Service {
    private ApplyMapper applyMapper;
    private ApplicationID_Service applicationIDService;
    @Autowired
    public void setApplicationIDService(ApplicationID_Service applicationIDService){
        this.applicationIDService = applicationIDService;
    }
    @Autowired
    public void setApplyMapper(ApplyMapper applyMapper){
        this.applyMapper = applyMapper;
    }
    private int getAuth() throws ResourceNoAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return Integer.parseInt(String.valueOf(authentication.getPrincipal()));
        }catch (NumberFormatException|NullPointerException e){
            throw new ResourceNoAuthException("Bad Auth");
        }

    }

    public FriendApply getDetail(long id) throws APIGeneralException {
        FriendApply apply = applyMapper.getApplyByApplyID(id);

        if (apply==null)throw new ResourceNotFoundException("Resource:"+id+" Not Found");

        int requestID = getAuth();

        if (requestID == apply.getApplyID()){
            return new FriendApply_ResponseForApplicant(apply);
        } else if (requestID == apply.getTargetID()) {
            return new FriendApply_ResponseForTarget(apply);
        }else{
            throw new ResourceNoAuthException("No Auth To Access This Resource");
        }
    }

    public FriendApplyList getList()throws APIGeneralException{
        int requestID = getAuth();
        List<FriendApply> post_primary = applyMapper.getApplyByApplicantID(requestID);
        List<FriendApply> received_primary = applyMapper.getApplyByTargetID(requestID);
        
        ArrayList<FriendApply> post = new ArrayList<>();
        for (FriendApply item: post_primary){
            FriendApply_ResponseForApplicant temp = new FriendApply_ResponseForApplicant(item);
            if (temp.getState()==FriendApply.Status.NO_RESPONSE.getCode()){
                post.add(temp);
            }
        }
        
        ArrayList<FriendApply> received = new ArrayList<>();
        for (FriendApply item: received_primary){
            received.add(new FriendApply_ResponseForTarget(item));
        }
        return new FriendApplyList(post,received);
    }

    public void deleteApply(long id) throws APIGeneralException{
        FriendApply apply = applyMapper.getApplyByApplyID(id);
        if (apply==null){
            throw new ResourceNotFoundException("Resource:"+id+" Not Found");
        }
        if (getAuth()!=apply.getApplyID()){
            throw new ResourceNoAuthException("No Auth to do this Operate");
        }
        applyMapper.deleteApply(id);
    }

    public void updateApply(Update_FriendApply apply) throws APIGeneralException{
        FriendApply applyStored = applyMapper.getApplyByApplyID(apply.getApplicationID());
        if (applyStored==null){
            throw new ResourceNotFoundException("Resource:"+apply.getApplicationID()+" Not Found");
        }
        if (getAuth()!=applyStored.getApplyID()){
            throw new ResourceNoAuthException("No Auth to do this Operate");
        }
        if (applyStored.getState()!=FriendApply.Status.NO_RESPONSE.getCode()){
            throw new UnprocessableEntityException("this apply is done");
        }
        applyStored.setComment(apply.getComment());
        applyStored.setGroup(apply.getGroup());
        applyStored.setPetName(apply.getPetName());

        applyMapper.updateApply(applyStored);
    }

    public void creatApply(Creat_FriendApply apply) throws APIGeneralException{
        long newID = applicationIDService.getNewApplicationID();
        int applicantID = getAuth();

        if (apply.getTargetID()==applicantID){
            throw new UnprocessableEntityException("self can not be target");
        }
        if (applyMapper.getUnDoneApplyCountByUserID(applicantID,apply.getTargetID())!=0){
            throw new UnprocessableEntityException("has undone friend apply");
        }
        if (false/*是好友*/){
            //throw 422 Unprocessable Entity
        }
        if (false/*group不合理*/){
            //throw 422 Unprocessable Entity
        }
        FriendApply friendApply = new FriendApply
                (newID,applicantID,apply.getTargetID(),apply.getPetName(),apply.getGroup(),apply.getComment(),System.currentTimeMillis()/1000,FriendApply.Status.NO_RESPONSE.getCode());

        applyMapper.insertApply(friendApply);
    }

    public void postReply(Reply_FriendApply reply) throws APIGeneralException{
        FriendApply applyStored = applyMapper.getApplyByApplyID(reply.getApplicationID());
        if (applyStored==null){
            throw new ResourceNotFoundException("Resource:"+reply.getApplicationID()+" Not Found");
        }
        if (getAuth()!=applyStored.getTargetID()){
            throw new ResourceNoAuthException("No Auth to do this Operate");
        }
        if (applyStored.getState()!=FriendApply.Status.NO_RESPONSE.getCode()){
            throw new UnprocessableEntityException("this apply is done");
        }
        FriendApply.Status reply_status;
        try{
            reply_status = FriendApply.Status.getByCode(reply.getState());
        }catch (IllegalArgumentException e){
            throw new BadRequestException("state: "+reply.getState()+" is illegal");
        }
        if (reply_status == FriendApply.Status.NO_RESPONSE){
            throw new BadRequestException("state: "+reply.getState()+" is illegal");
        }
        //回执不是同意,保存state,返回
        if (reply_status != FriendApply.Status.PERMIT){
            applyStored.setState(reply_status.getCode());
            applyMapper.updateApply(applyStored);
            return;
        }
        if (false/*group 合法性*/){

        }
        if (reply.getPetName()==null||reply.getPetName().equals("")){
            throw new BadRequestException("petName can not be blank");
        }
        //添加好友关系
    }
}
