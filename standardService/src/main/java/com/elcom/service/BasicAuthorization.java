package com.elcom.service;

import javax.ws.rs.core.HttpHeaders;

import com.elcom.data.user.entity.User;
import com.elcom.sharedbiz.dto.UserDTO;
import com.elcom.sharedbiz.manager.AuthorizationManager;
import com.elcom.sharedbiz.validation.AuthorizationException;
import com.elcom.util.StringUtils;
import com.elcom.util.security.Base64Converter;

public class BasicAuthorization extends BaseAuthorization implements IAuthorization {

    /*public BasicAuthorization() {
	}*/
    public BasicAuthorization(HttpHeaders headers) {
        super(headers);
    }

    @Override
    public UserDTO authorize(String token) throws Exception {
        try (AuthorizationManager manager = new AuthorizationManager()) {

            String tempToken = Base64Converter.decode(token);
            if (StringUtils.isNullOrEmpty(tempToken)) {
                throw new AuthorizationException(exMes);
            }

            if (tempToken.indexOf(':') == -1) {
                throw new AuthorizationException(exMes);
            }

            String[] credential = tempToken.split(":");
            if (credential == null || credential.length != 2) {
                throw new AuthorizationException(exMes);
            }

            String username = credential[0];
            String password = credential[1];

            if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
                throw new AuthorizationException(exMes);
            }

            return manager.authorized(username, password);
        }
    }

    @Override
    public User authorize(String token, AuthorizationManager manager) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
