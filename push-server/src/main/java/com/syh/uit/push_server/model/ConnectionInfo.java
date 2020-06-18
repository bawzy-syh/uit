package com.syh.uit.push_server.model;

import java.util.Objects;

public class ConnectionInfo {
    private final Integer uid;
    private final Endpoint endpoint;

    public ConnectionInfo(Integer uid, Endpoint endpoint) {
        this.uid = uid;
        this.endpoint = endpoint;
    }

    public Integer getUid() {
        return uid;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return uid+"."+endpoint.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionInfo that = (ConnectionInfo) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(endpoint, that.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, endpoint);
    }
}
