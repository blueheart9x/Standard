package com.elcom.model.enums;

public enum StatusConnection {
	CONNECTION_OK("OK"),
	CONNECTION_ERROR("ERROR"),
	CONNECTION_MESSEAGE_SUCCESS("Connection database is success"),
	CONNECTION_MESSAGE_ERROR("Connection database is error");
	
	private final String value;

	StatusConnection(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
