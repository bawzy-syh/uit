package com.syh.uit.relation_manager.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RelationInfoMapper {
    /**
     * @param id1 第一个UID
     * @param id2 第二个UID
     * @return 两人存在的Relation条数,必定大于等于0
     */
    int ifRelationExist(int id1, int id2);
}