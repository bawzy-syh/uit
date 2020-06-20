package com.syh.uit.relation_group_info.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syh.uit.relation_group_info.model.GroupInfo;

@JsonIgnoreProperties("userID")
public class GroupInfoResponse extends GroupInfo {
    public GroupInfoResponse(GroupInfo info) {
        super(info.getGroupID(), info.getTitle(), info.getUserID(), info.getOrder());
    }
}
