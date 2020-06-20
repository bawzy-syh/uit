package com.syh.uit.relation_group_info.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RelationInfoMapper {
    int getGroupUsageCount(Long groupID);
}
