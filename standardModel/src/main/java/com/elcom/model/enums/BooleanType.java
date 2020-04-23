package com.elcom.model.enums;

public enum BooleanType {

	YES("Y"),
	NO("N");
	private final String value;

	BooleanType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
	
}
