package com.elcom.model.dto;

public class AuthorizationResponseDTO {

    private AuthorizationResponseDTODetails data;

    public AuthorizationResponseDTO(AuthorizationResponseDTODetails data) {
        this.data = data;
    }

    public AuthorizationResponseDTODetails getData() {
        return data;
    }

    public void setData(AuthorizationResponseDTODetails data) {
        this.data = data;
    }
}
