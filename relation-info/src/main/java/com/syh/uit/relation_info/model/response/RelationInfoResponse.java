package com.syh.uit.relation_info.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syh.uit.relation_info.model.RelationInfo;

@JsonIgnoreProperties
public class RelationInfoResponse extends RelationInfo {
    private String target_info;
    public RelationInfoResponse(RelationInfo father) {
        super(father.getRelationID(), father.getUid(), father.getTid(), father.getPetName(), father.getGroup());
    }

    public void setTarget_info(String target_info) {
        this.target_info = target_info;
    }

    public String getTarget_info() {
        return target_info;
    }
}
