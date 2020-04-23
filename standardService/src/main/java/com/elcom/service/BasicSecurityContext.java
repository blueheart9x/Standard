package com.elcom.service;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.elcom.data.user.entity.User;

public class BasicSecurityContext implements SecurityContext {
 
    private User user;
    private boolean secure;
 
    public BasicSecurityContext(User user, boolean secure) {
    	
        this.user = user;
        this.secure = secure;
    }
 
    @Override
    public Principal getUserPrincipal() {
        return () -> user.getEmail();
    }
 
    @Override
    public boolean isUserInRole(String role) {
        return user.getRoles().contains(role);
    }
 
    @Override
    public boolean isSecure() {
        return secure;
    }
 
    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}