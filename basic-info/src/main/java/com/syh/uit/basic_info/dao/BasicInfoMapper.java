package com.syh.uit.basic_info.dao;

import com.syh.uit.basic_info.model.BasicInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoMapper {
    /**
     * @apiNote 获取指定用户的BasicInfo
     * @param uid 指定用户uid
     * @return 指定用户的BasicInfo,uid不存在时返回null
     */
    BasicInfo getBasicInfoByUID(int uid);

    /**
     * @apiNote 更新BasicInfo
     * @param basicInfo 要更新的BasicInfo
     */
    void updateBasicInfo(BasicInfo basicInfo);
}
