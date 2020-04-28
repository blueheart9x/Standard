package com.elcom.service;

import com.elcom.data.entity.User;
import com.elcom.business.manager.AuthorizationManager;

public interface IAuthorization {

    User authorize(String token) throws Exception;

    User authorize(String token, AuthorizationManager manager) throws Exception;
}
