package com.elcom.model.enums;

public enum SessionType {
	
	JTA("JTA"),
	THREAD("THREAD");
	
	private final String value;

	SessionType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
    
}
