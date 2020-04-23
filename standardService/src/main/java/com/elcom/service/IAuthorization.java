package com.elcom.service;

import com.elcom.data.user.entity.User;
import com.elcom.sharedbiz.manager.AuthorizationManager;

public interface IAuthorization {
	
	User authorize(String token) throws Exception;
	User authorize(String token, AuthorizationManager manager) throws Exception;
}
