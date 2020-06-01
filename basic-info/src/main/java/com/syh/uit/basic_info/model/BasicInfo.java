package com.syh.uit.basic_info.model;

import java.util.Date;

public class BasicInfo {
    private final Integer uid;
    private String petName;
    private String sign;
    private Integer gender;
    private Date birthday;
    private String iconPath;
    private final Long update_time;

    public BasicInfo(Integer uid, String petName, String sign, Integer gender, Date birthday, String iconPath, Long update_time) {
        this.uid = uid;
        this.petName = petName;
        this.sign = sign;
        this.gender = gender;
        this.birthday = birthday;
        this.iconPath = iconPath;
        this.update_time = update_time;
    }

    public enum Genders{
        MALE(0), FEMALE(1);
        private final int code;
        Genders(int code){
            this.code=code;
        }
        public int getCode() {
            return code;
        }
        public static Genders getByCode(int value) throws IllegalArgumentException{
            for(Genders typeEnum : Genders.values()) {
                if(typeEnum.code == value) {
                    return typeEnum;
                }
            }
            throw new IllegalArgumentException("No element matches " + value);
        }
    }
    public Integer getUid() {
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

    public Long getUpdate_time() {
        return update_time;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

}
