package com.elcom.util.security;

import java.util.Base64;

public class Base64Converter {
	
	public static String encode(String input) throws Exception {
		try {
			return Base64.getEncoder().encodeToString(input.getBytes("utf-8"));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static String decode(String base64encodedString) throws Exception {
		try {
			// Decode
			byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
			return new String(base64decodedBytes, "utf-8");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
