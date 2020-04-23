package com.elcom.model.enums;

public enum MenuObjectType {

	FUNCTION("F"),
	ROLE("R"),
	SERVICE("S");
	private final String value;

	MenuObjectType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
	
}
