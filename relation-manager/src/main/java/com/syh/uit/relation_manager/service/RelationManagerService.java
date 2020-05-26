package com.syh.uit.relation_manager.service;

import com.syh.uit.exception.exception.APIGeneralException;
import com.syh.uit.exception.exception.ResourceNotFoundException;
import com.syh.uit.relation_manager.dao.RelationInfoMapper;
import com.syh.uit.relation_manager.model.RelationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationManagerService {
    private RelationInfoMapper relationInfoMapper;
    @Autowired
    public void setRelationInfoMapper(RelationInfoMapper relationInfoMapper) {
        this.relationInfoMapper = relationInfoMapper;
    }

    public int hasRelationByUIDs(int id1, int id2)throws APIGeneralException{
        //todo:检查是否为有效ID,返回422
        int ifExist = relationInfoMapper.ifRelationExist(id1, id2);
        return ifExist==0 ? 0 : 1 ;
    }
    public RelationInfo getRelationInfoByID(long relationID)throws APIGeneralException {
        RelationInfo stored = relationInfoMapper.getRelationInfoByID(relationID);
        if (stored==null)throw new ResourceNotFoundException("relation: "+relationID+" not found");
        return stored;
    }
}
