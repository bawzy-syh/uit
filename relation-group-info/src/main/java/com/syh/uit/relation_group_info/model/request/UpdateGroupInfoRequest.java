package com.syh.uit.relation_group_info.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateGroupInfoRequest implements Comparable<UpdateGroupInfoRequest>{
    private final Long groupID;
    @NotBlank
    private final String title;
    @NotNull
    private final Integer order;

    public UpdateGroupInfoRequest(Long groupID, String title, Integer order) {
        this.groupID = groupID;
        this.title = title;
        this.order = order;
    }

    public Long getGroupID() {
        return groupID;
    }

    public String getTitle() {
        return title;
    }

    public Integer getOrder() {
        return order;
    }

    @Override
    public int compareTo(UpdateGroupInfoRequest o) {
        return this.order - o.order;
    }
}
