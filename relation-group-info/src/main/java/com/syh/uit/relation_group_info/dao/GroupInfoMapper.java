package com.syh.uit.relation_group_info.dao;

import com.syh.uit.relation_group_info.model.GroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupInfoMapper {

    /**
     * 根据uid获取用户的GroupInfo列表
     * @param uid userID
     * @return 按order大小升序排列的结果列表
     */
    List<GroupInfo> getGroupInfoByUID(int uid);

    /**
     * 批量更新Group
     * @param list 待更新的GroupInfo的列表
     */
    void updateGroupList(List<GroupInfo> list);

    /**
     * 批量删除Group
     * @param list 待删除的groupID的列表
     */
    void deleteGroupList(List<Long> list);
}
