package com.elcom.model.enums;

public enum MenuType {

	ROOT("R"),
	MAIN("M"),
	INTERMEDIATE("I"),
	LEAF("L");
	private final String value;

	MenuType(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
	
}
