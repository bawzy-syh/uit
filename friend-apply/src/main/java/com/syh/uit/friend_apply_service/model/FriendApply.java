package com.syh.uit.friend_apply_service.model;

public class FriendApply {
    private final long applicationID;
    private final int applyID;
    private final int targetID;
    private String petName;
    private long group;
    private String comment;
    private final long applyTime;
    private int state;

    public enum Status{
        NO_RESPONSE(-1), REFUSE(0), PERMIT(1);
        private final int code;
        Status(int code){
            this.code=code;
        }
        public int getCode() {
            return code;
        }
        public static Status getByCode(int value) throws IllegalArgumentException{
            for(Status typeEnum : Status.values()) {
                if(typeEnum.code == value) {
                    return typeEnum;
                }
            }
            throw new IllegalArgumentException("No element matches " + value);
        }
    }

    public FriendApply(long applicationID, int applyID, int targetID, String petName, long group, String comment, long applyTime, int state) {
        this.applicationID = applicationID;
        this.applyID = applyID;
        this.targetID = targetID;
        this.petName = petName;
        this.group = group;
        this.comment = comment;
        this.applyTime = applyTime;
        this.state = state;
    }


    public long getApplicationID() {
        return applicationID;
    }

    public int getApplyID() {
        return applyID;
    }

    public int getTargetID() {
        return targetID;
    }

    public String getPetName() {
        return petName;
    }

    public long getGroup() {
        return group;
    }

    public String getComment() {
        return comment;
    }

    public long getApplyTime() {
        return applyTime;
    }

    public int getState() {
        return state;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setState(int state) {
        this.state = state;
    }
}
