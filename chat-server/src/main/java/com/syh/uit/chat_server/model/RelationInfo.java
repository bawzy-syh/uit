package com.syh.uit.chat_server.model;

import java.beans.ConstructorProperties;
import java.io.Serializable;

public class RelationInfo implements Serializable {
    private final long relationID;
    private final long bindingID;
    private final int uid;
    private final int targetID;

    @ConstructorProperties({"relationID", "bindingID","uid","targetID"})
    public RelationInfo(long relationID, long bindingID, int uid, int targetID) {
        this.relationID = relationID;
        this.bindingID = bindingID;
        this.uid = uid;
        this.targetID = targetID;
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
}
