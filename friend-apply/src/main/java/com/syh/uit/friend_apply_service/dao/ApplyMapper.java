package com.syh.uit.friend_apply_service.dao;

import com.syh.uit.friend_apply_service.model.FriendApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface ApplyMapper {
    @Nullable
    FriendApply getApplyByApplyID(@Param("aid") long applicationID);

    /**
     * @apiNote 由好友请求对象的ID获取请求列表
     * @param targetID 好友请求对象的ID
     * @return 好友请求列表,已回复的请求不在此列
     */
    List<FriendApply> getApplyByTargetID(@Param("tid") int targetID);

    /**
     * @apiNote 由好友请求申请人的ID获取请求列表
     * @param applicantID 好友请求申请人的ID
     * @return 好友请求列表，全部返回
     */
    List<FriendApply> getApplyByApplicantID(@Param("applicantID")int applicantID);

    /**
     * @apiNote 获取两个用户间的有效的好友请求数量
     * @param id1 第一个userID
     * @param id2 第二个userID
     * @return 现存有效的好友请求数量
     */
    int getUnDoneApplyCountByUserID(@Param("id1") int id1, @Param("id2")int id2);

    void insertApply(FriendApply friendApply);
    void deleteApply(@Param("id")long id);
    void updateApply(FriendApply friendApply);
}
