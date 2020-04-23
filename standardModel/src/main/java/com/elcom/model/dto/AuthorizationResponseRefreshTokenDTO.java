package com.elcom.model.dto;

public class AuthorizationResponseRefreshTokenDTO {

    private String accessToken;

    public AuthorizationResponseRefreshTokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
