package com.syh.uit.friend_apply_service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syh.uit.friend_apply_service.model.FriendApply;

@JsonIgnoreProperties({"petName","group"})
public class FriendApply_ResponseForTarget extends FriendApply {
    public FriendApply_ResponseForTarget(FriendApply father) {
        super(father.getApplicationID(), father.getApplyID(), father.getTargetID(), father.getPetName(), father.getGroup(), father.getComment(), father.getApplyTime(), father.getState());
    }
}
