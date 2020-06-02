package com.syh.uit.basic_info.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

public class UpdateBasicInfoRequest {
    @Null
    private Integer uid;
    @NotNull(message = "petName can not be null")
    private final String petName;
    @NotNull(message = "sign can not be null")
    private final String sign;
    @NotNull(message = "gender can not be null")
    private final Integer gender;
    @NotNull(message = "birthday can not be null")
    private final Date birthday;
    @NotNull(message = "iconPath can not be null")
    private final String iconPath;

    public UpdateBasicInfoRequest(String petName, String sign, Integer gender, Date birthday, String iconPath) {
        this.petName = petName;
        this.sign = sign;
        this.gender = gender;
        this.birthday = birthday;
        this.iconPath = iconPath;
    }

    public int getUid() {
        return uid;
    }

    public String getPetName() {
        return petName;
    }

    public String getSign() {
        return sign;
    }

    public Integer getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
