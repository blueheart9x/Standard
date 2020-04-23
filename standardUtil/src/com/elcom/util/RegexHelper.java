package com.elcom.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {

	public static boolean ValidateContractNumber10001(String s) {
		String regex = "(10001)\\d{11}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean ValidateContractNumber10003(String s) {
		String regex = "(10003)\\d{11}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}
}
