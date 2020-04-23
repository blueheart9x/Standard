package com.elcom.model.enums;

public enum RecordStatus {
	
	ACTIVE("A"),
	CLOSED("C");
	private final String value;

	RecordStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
    
}
