package com.syh.uit.relation_info.model;

public class RelationInfo {
    private final long relationID;
    private final int uid;
    private final int tid;
    private String petName;
    private long group;

    public RelationInfo(long relationID, int uid, int tid, String petName, long group) {
        this.relationID = relationID;
        this.uid = uid;
        this.tid = tid;
        this.petName = petName;
        this.group = group;
    }

    public long getRelationID() {
        return relationID;
    }

    public int getUid() {
        return uid;
    }

    public int getTid() {
        return tid;
    }

    public String getPetName() {
        return petName;
    }

    public long getGroup() {
        return group;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setGroup(long group) {
        this.group = group;
    }
}
