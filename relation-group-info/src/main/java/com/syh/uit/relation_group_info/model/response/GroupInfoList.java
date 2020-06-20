package com.syh.uit.relation_group_info.model.response;

import com.syh.uit.relation_group_info.model.GroupInfo;

import java.util.List;

public class GroupInfoList {
    private final List<GroupInfo> groups;

    public GroupInfoList(List<GroupInfo> groups) {
        this.groups = groups;
    }

    public List<GroupInfo> getGroups() {
        return groups;
    }
}
