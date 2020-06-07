package com.syh.uit.relation_info.dao;

import com.syh.uit.relation_info.model.RelationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationInfoMapper {
    RelationInfo getRelationInfoByRelationID(long relationID);
    List<RelationInfo> getRelationInfoListByUID(int uid);
    void updateRelationInfo(RelationInfo relationInfo);
}
