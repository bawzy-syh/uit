package com.syh.uit.user_connection_center.module.inner;

import java.util.List;

public class UserOnlineState {
    private final List<Endpoint> states;

    public UserOnlineState(List<Endpoint> states){
        this.states = states;
    }

    public List<Endpoint> getStates() {
        return states;
    }
}
