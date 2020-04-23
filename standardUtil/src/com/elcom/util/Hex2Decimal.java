package com.elcom.util;

import java.math.BigDecimal;

public class Hex2Decimal {

    public static int hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }


    // precondition:  d is a nonnegative integer
    public static String decimal2Hex(int d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 16;
        }
        return hex;
    }

    public static void main(String[] args) {
    	String a = BigDecimal.valueOf(Double.parseDouble("2.5E7")).toPlainString();
    	System.out.println(a);
    	
    	a = BigDecimal.valueOf(Double.parseDouble("12345678")).toPlainString();
    	System.out.println(a);
    	
    }
}
