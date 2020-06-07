package com.syh.uit.friend_apply_service.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Update_FriendApply {
    @Null(message = "applicationID not excepted")
    private Long applicationID;
    @NotBlank(message = "petName could not be blank")
    private String petName;
    @NotNull(message = "group not found")
    private Integer group;
    @NotNull(message = "comment not found")
    private String comment;

    public void setApplicationID(long applicationID) {
        this.applicationID = applicationID;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getApplicationID() {
        return applicationID;
    }

    public String getPetName() {
        return petName;
    }

    public int getGroup() {
        return group;
    }

    public String getComment() {
        return comment;
    }
}
