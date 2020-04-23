package com.elcom.model.object;

import com.elcom.model.enums.AuthorizationToken;

public class AuthorizationTokenResult {

	private AuthorizationToken TokenEnum;
	private String token;
	private String identity;

	public AuthorizationTokenResult(AuthorizationToken tokenEnum, String token) {
		super();
		TokenEnum = tokenEnum;
		this.token = token;
	}
	
	public AuthorizationTokenResult(AuthorizationToken tokenEnum, String token, String identity) {
		super();
		TokenEnum = tokenEnum;
		this.token = token;
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public AuthorizationToken getTokenEnum() {
		return TokenEnum;
	}

	public void setTokenEnum(AuthorizationToken tokenEnum) {
		TokenEnum = tokenEnum;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
