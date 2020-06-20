package com.syh.uit.relation_group_info.model;

public class GroupInfo {
    private final Long groupID;
    private String title;
    private final int userID;
    private int order;

    public GroupInfo(long groupID, String title, int userID, int order) {
        this.groupID = groupID;
        this.title = title;
        this.userID = userID;
        this.order = order;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Long getGroupID() {
        return groupID;
    }

    public String getTitle() {
        return title;
    }

    public int getUserID() {
        return userID;
    }

    public int getOrder() {
        return order;
    }
}
