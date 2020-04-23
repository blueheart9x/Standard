package com.elcom.util;

public class NumericUtil {

	public static boolean isNullOrZero(Integer input) {
		return ((input == null) || (input == 0)) ? true : false;
	}

	public static boolean isNullOrZero(Long input) {
		return ((input == null) || (input == 0)) ? true : false;
	}

}
