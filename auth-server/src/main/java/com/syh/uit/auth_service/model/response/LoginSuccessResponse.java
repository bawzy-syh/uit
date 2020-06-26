package com.syh.uit.auth_service.model.response;

public class LoginSuccessResponse {
    private final String access_token;
    private final int expires_in;

    public LoginSuccessResponse(String access_token, int expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
