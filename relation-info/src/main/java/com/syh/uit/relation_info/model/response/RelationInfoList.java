package com.syh.uit.relation_info.model.response;

import com.syh.uit.relation_info.model.RelationInfo;

import java.util.List;

public class RelationInfoList {
    private final List<RelationInfo> relations;
    public RelationInfoList(List<RelationInfo> relations){
        this.relations = relations;
    }

    public List<RelationInfo> getRelations() {
        return relations;
    }
}
