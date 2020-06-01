package com.syh.uit.basic_info.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.syh.uit.basic_info.model.BasicInfo;

@JsonIgnoreProperties()
public class SelfBasicInfoResponse extends BasicInfo {
    public SelfBasicInfoResponse(BasicInfo basicInfo){
        super(basicInfo.getUid(),basicInfo.getPetName(),basicInfo.getSign(),basicInfo.getGender(),basicInfo.getBirthday(),basicInfo.getIconPath(),basicInfo.getUpdate_time());
    }
}
