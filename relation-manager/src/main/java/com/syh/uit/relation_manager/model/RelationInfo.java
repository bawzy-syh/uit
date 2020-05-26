package com.syh.uit.relation_manager.model;

public class RelationInfo {
    private final long relationID;
    private final long bindingID;
    private final int uid;
    private final int targetID;
    private String petName;
    private int group;

    public RelationInfo(long relationID, long bindingID,int uid,int targetID,String petName,int group){
        this.relationID = relationID;
        this.bindingID = bindingID;
        this.uid = uid;
        this.targetID = targetID;
        this.petName = petName;
        this.group = group;
    }

    public long getRelationID() {
        return relationID;
    }

    public long getBindingID() {
        return bindingID;
    }

    public int getUid() {
        return uid;
    }

    public int getTargetID() {
        return targetID;
    }

    public String getPetName() {
        return petName;
    }

    public int getGroup() {
        return group;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
