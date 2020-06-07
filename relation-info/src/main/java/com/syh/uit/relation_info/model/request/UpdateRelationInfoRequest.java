package com.syh.uit.relation_info.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class UpdateRelationInfoRequest {
    @Null
    private Long relationID;
    @NotNull(message = "petName can not be null")
    private final String petName;
    @NotNull(message = "group can not be null")
    private final Long group;

    public UpdateRelationInfoRequest(Long relationID, String petName, Long group) {
        this.relationID = relationID;
        this.petName = petName;
        this.group = group;
    }

    public void setRelationID(Long relationID) {
        this.relationID = relationID;
    }

    public Long getRelationID() {
        return relationID;
    }

    public String getPetName() {
        return petName;
    }

    public Long getGroup() {
        return group;
    }
}
