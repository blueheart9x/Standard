package com.elcom.util;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

public class HexUtils {

	/*public static byte[] hexStringToByteArray(String s) {
	
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}*/
	
	/*private static int toDigit(char hexChar) {
	    int digit = Character.digit(hexChar, 16);
	    if(digit == -1) {
	        throw new IllegalArgumentException(
	          "Invalid Hexadecimal Character: "+ hexChar);
	    }
	    return digit;
	}
	
	public static byte hexToByte(String hexString) {
	    int firstDigit = toDigit(hexString.charAt(0));
	    int secondDigit = toDigit(hexString.charAt(1));
	    return (byte) ((firstDigit << 4) + secondDigit);
	}
	
	public static byte[] hexStringToByteArray(String hexString) {
	    if (hexString.length() % 2 == 1) {
	    	hexString = "0" + hexString;
	        //throw new IllegalArgumentException(
	          //"Invalid hexadecimal String supplied.");
	    }
	     
	    byte[] bytes = new byte[hexString.length() / 2];
	    for (int i = 0; i < hexString.length(); i += 2) {
	        bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
	    }
	    
	    return bytes;
	}*/
	
	/*public static byte[] hexStringToByteArray(String hexString) throws DecoderException {

		//if (hexString.length() % 2 == 1)
	    	//hexString = hexString + "0";
		
		return Hex.decodeHex(hexString.toCharArray());
	}*/
	
	public static byte[] hexStringToByteArray(String hexString) {
		
		byte[] bytes = null;
		
		try {
			
			bytes = DatatypeConverter.parseHexBinary(hexString);
		}catch(Exception ex) {
			System.out.println("HexUtils.hexStringToByteArray.ex: " + ex.toString());
		}
		
		return bytes;
	}

	/*public static String binaryToHex(String bitStream) {

		String result = "";
		
		try {
			
			int byteLength = 4;
			int bitStartPos = 0, bitPos = 0;
			String hexString = "";
			int sum = 0;

			// pad '0' to make input bit stream multiple of 4

			if (bitStream.length() % 4 != 0) {
				int tempCnt = 0;
				int tempBit = bitStream.length() % 4;
				while (tempCnt < (byteLength - tempBit)) {
					bitStream = "0" + bitStream;
					tempCnt++;
				}
			}

			// Group 4 bits, and find Hex equivalent

			while (bitStartPos < bitStream.length()) {
				while (bitPos < byteLength) {
					sum = (int) (sum + Integer.parseInt("" + bitStream.charAt(bitStream.length() - bitStartPos - 1))
							* Math.pow(2, bitPos));
					bitPos++;
					bitStartPos++;
				}
				if (sum < 10) {
					hexString = Integer.toString(sum) + hexString;
				} else {
					hexString = (char) (sum + 55) + hexString;
				}

				bitPos = 0;
				sum = 0;
			}
			
			result = hexString;
			
		}catch(Exception ex) {
			System.out.println("HexUtils.binaryToHex.ex: " + ex.toString());
		}
		
		return result;
	}*/
	
	public static String binaryToHex(String binStr) {

		while (binStr.length() % 4 != 0)
			binStr = "0" + binStr;
		
	    StringBuilder builder = new StringBuilder();
	    
	    for (int count = 0; count < binStr.length(); count += 4) {
	    	
	        String nibble = binStr.substring(count, count + 4);
	        
	        builder.append(Integer.toHexString(Integer.parseInt(nibble, 2)));
	    }
	    
	    return builder.toString();
	}
	
	public static String decToHex(int decValue, int padd) {
		
		return StringUtils.leftPad(Integer.toHexString(decValue), padd, "0");
	}
}
