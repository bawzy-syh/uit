package com.syh.uit.basic_info.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syh.uit.basic_info.model.BasicInfo;

@JsonIgnoreProperties({"update_time"})
public class BasicInfoResponse extends BasicInfo {
    private int isFriend;
    public BasicInfoResponse(BasicInfo basicInfo){
        super(basicInfo.getUid(),basicInfo.getPetName(),basicInfo.getSign(),basicInfo.getGender(),basicInfo.getBirthday(),basicInfo.getIconPath(),basicInfo.getUpdate_time());
    }

    public enum FriendStatus{
        UNKNOWN(-1), FALSE(0), TRUE(1);
        private final int code;
        FriendStatus(int code){
            this.code=code;
        }
        public int getCode() {
            return code;
        }
        public static FriendStatus getByCode(int value) throws IllegalArgumentException{
            for(FriendStatus typeEnum : BasicInfoResponse.FriendStatus.values()) {
                if(typeEnum.code == value) {
                    return typeEnum;
                }
            }
            throw new IllegalArgumentException("No element matches " + value);
        }
    }
    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }
}