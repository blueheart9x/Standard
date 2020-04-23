package com.elcom.model.enums;

public enum AuthorizationToken {
	BEARER("Bearer"),
	BASIC("Basic"),
	OTT("Ott");/*one time token*/
	
	private final String value;

	AuthorizationToken(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
	
	public static AuthorizationToken from(String text) {
        for (AuthorizationToken b : AuthorizationToken.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}