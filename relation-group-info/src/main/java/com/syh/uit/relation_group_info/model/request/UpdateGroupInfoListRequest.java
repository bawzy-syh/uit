package com.syh.uit.relation_group_info.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateGroupInfoListRequest {
    @Valid
    @NotNull
    private final List<UpdateGroupInfoRequest> groups;

    @JsonCreator()
    public UpdateGroupInfoListRequest( @JsonProperty("groups") List<UpdateGroupInfoRequest> groups) {
        this.groups = groups;
    }

    public List<UpdateGroupInfoRequest> getGroups() {
        return groups;
    }
}
