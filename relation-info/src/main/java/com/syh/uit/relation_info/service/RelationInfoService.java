package com.syh.uit.relation_info.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.BadRequestException;
import com.syh.uit.exception.exception.ResourceNoAuthException;
import com.syh.uit.exception.exception.ResourceNotFoundException;
import com.syh.uit.relation_info.config.RelationInfoServiceConfig;
import com.syh.uit.relation_info.dao.RelationInfoMapper;
import com.syh.uit.relation_info.model.RelationInfo;
import com.syh.uit.relation_info.model.request.UpdateRelationInfoRequest;
import com.syh.uit.relation_info.model.response.RelationInfoList;
import com.syh.uit.relation_info.model.response.RelationInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationInfoService {
    private RelationInfoMapper relationInfoMapper;
    private RelationInfoServiceConfig config;
    @Autowired
    public void setConfig(RelationInfoServiceConfig config) {
        this.config = config;
    }
    @Autowired
    public void setRelationInfoMapper(RelationInfoMapper relationInfoMapper) {
        this.relationInfoMapper = relationInfoMapper;
    }
    private int getAuth() throws ResourceNoAuthException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
            return Integer.parseInt(String.valueOf(authentication.getPrincipal()));
        }catch (NumberFormatException|NullPointerException e){
            throw new ResourceNoAuthException("Bad Auth");
        }

    }

    public RelationInfoList getRelationInfoList()throws APIGeneralException {
        List<RelationInfo> list = relationInfoMapper.getRelationInfoListByUID(getAuth());
        //todo:查询对方基本信息
        return new RelationInfoList(list);
    }
    public RelationInfoResponse updateRelationInfo(UpdateRelationInfoRequest request)throws APIGeneralException{
        RelationInfo stored = relationInfoMapper.getRelationInfoByRelationID(request.getRelationID());
        if (stored==null){
            throw new ResourceNotFoundException(request.getRelationID()+" not found");
        }
        if (stored.getUid()!=getAuth()){
            throw new ResourceNoAuthException("No Auth to update");
        }
        if (request.getPetName()!=null){
            if (request.getPetName().length()>config.getPetNameMaxLength()){
                throw new BadRequestException("petName is too long");
            }
            stored.setPetName(request.getPetName());
        }
        if (request.getGroup()!=null){
            //todo:检验合法性
            stored.setGroup(request.getGroup());
        }
        relationInfoMapper.updateRelationInfo(stored);
        return new RelationInfoResponse(stored);
    }
    public void deleteRelation(long relationID)throws APIGeneralException{

    }
}
