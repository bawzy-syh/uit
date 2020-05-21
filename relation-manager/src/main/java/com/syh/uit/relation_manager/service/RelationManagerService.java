package com.syh.uit.relation_manager.service;

import com.syh.uit.relation_manager.dao.RelationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationManagerService {
    private RelationInfoMapper relationInfoMapper;
    @Autowired
    public void setRelationInfoMapper(RelationInfoMapper relationInfoMapper) {
        this.relationInfoMapper = relationInfoMapper;
    }

    public void hasRelationByUIDs(int id1, int id2){
        int ifExist = relationInfoMapper.ifRelationExist(id1, id2);
        if (ifExist == 0){
            //不存在
        }
    }
}
