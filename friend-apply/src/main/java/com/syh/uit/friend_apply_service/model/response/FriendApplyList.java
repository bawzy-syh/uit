package com.syh.uit.friend_apply_service.model.response;

import com.syh.uit.friend_apply_service.model.FriendApply;

import java.util.List;

public class FriendApplyList {
    private final List<FriendApply> post;
    private final List<FriendApply> received;

    /**
     * @param post 提交的好友请求列表
     * @param received 收到的好友请求列表
     */
    public FriendApplyList(List<FriendApply> post, List<FriendApply> received){
        this.post = post;
        this.received = received;
    }

    public List<FriendApply> getPost() {
        return post;
    }

    public List<FriendApply> getReceived() {
        return received;
    }
}
