package com.syh.uit.friend_apply_service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syh.uit.friend_apply_service.model.FriendApply;

@JsonIgnoreProperties()
public class FriendApply_ResponseForApplicant extends FriendApply {
    public FriendApply_ResponseForApplicant(FriendApply father) {
        super(father.getApplicationID(), father.getApplyID(), father.getTargetID(), father.getPetName(), father.getGroup(), father.getComment(), father.getApplyTime(), father.getState());
    }
}
