package com.syh.uit.auth_service.model;

public class Account {
    private final String id;
    private final String encodedPassword;

    public Account(String id,String encodedPassword){
        this.id = id;
        this.encodedPassword = encodedPassword;
    }
    public String getId() {
        return id;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }
}
