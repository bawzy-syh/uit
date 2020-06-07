package com.syh.uit.friend_apply_service.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Reply_FriendApply {
    @NotNull(message = "applicationID not found")
    private final Long applicationID;
    @NotNull(message = "state not found")
    private final Integer state;
    @NotBlank(message = "petName could not be blank")
    private final String petName;
    @NotNull(message = "targetID not found")
    private final Integer group;
    public Reply_FriendApply(Long applicationID, Integer state, String petName, Integer group){
        this.applicationID = applicationID;
        this.state = state;
        this.petName = petName;
        this.group = group;
    }
    public long getApplicationID() {
        return applicationID;
    }

    public int getState() {
        return state;
    }

    public String getPetName() {
        return petName;
    }

    public int getGroup() {
        return group;
    }
}
