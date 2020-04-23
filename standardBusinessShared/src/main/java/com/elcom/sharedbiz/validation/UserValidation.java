package com.elcom.sharedbiz.validation;

import com.elcom.model.dto.AuthorizationRefreshTokenDTO;
import com.elcom.model.dto.AuthorizationRequestDTO;
import com.elcom.util.StringUtils;

public final class UserValidation {

    private UserValidation() {
    }

    public static void validateAuthorization(AuthorizationRequestDTO request) throws ValidationException {
        if (request == null) {
            throw new ValidationException("Email/Mobile and Password input is required.");
        }

        if (StringUtils.isNullOrEmpty(request.getAccountName())) {
            throw new ValidationException("Email/Mobile is required.");
        }

        if (StringUtils.isNullOrEmpty(request.getPassword())) {
            throw new ValidationException("Password is required.");
        }
    }

    public static void validateRefreshToken(AuthorizationRefreshTokenDTO request) throws ValidationException {
        if (request == null) {
            throw new ValidationException("Email/Mobile and Refresh Token input is required.");
        }

        if (StringUtils.isNullOrEmpty(request.getUuid())) {
            throw new ValidationException("UUID is required.");
        }

        if (StringUtils.isNullOrEmpty(request.getRefreshToken())) {
            throw new ValidationException("Refresh Token is required.");
        }
    }
}
