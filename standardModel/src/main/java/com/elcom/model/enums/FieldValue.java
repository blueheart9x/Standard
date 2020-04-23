package com.elcom.model.enums;

public enum FieldValue {
	
	NUMBER("[^0-9]"),
	WORDS("[^a-zA-Z0-9 ]"),
	LETTER("[^a-zA-Z]"),
	DATE("[^0-9/]");
	
	private final String value;

	FieldValue(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
    
	/*public static void main(String[] args) {
		String name = "Tên khách hàng 57 sửa lần 1";
			name = name.replaceAll(FieldValue.WORDS.value(), "");
		System.out.println("name: " + name);
		
	}*/
	
}
