package com.syh.uit.friend_apply_service.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Creat_FriendApply {

    @NotNull(message = "targetID not found")
    private final Integer targetID;
    @NotBlank(message = "petName could not be blank")
    private final String petName;
    @NotNull(message = "group not found")
    private final Integer group;
    @NotNull(message = "comment not found")
    private final String comment;

    public Creat_FriendApply(Integer targetID, String petName, Integer group, String comment) {
        this.targetID = targetID;
        this.petName = petName;
        this.group = group;
        this.comment = comment;
    }

    public int getTargetID() {
        return targetID;
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
