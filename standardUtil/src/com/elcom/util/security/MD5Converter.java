package com.elcom.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MD5Converter 
{
    private static final String ALGORITHM = "md5";
    private static final String DIGEST_STRING = "HG58YZ3CR9";
    private static final String CHARSET_UTF_8 = "utf-8";
    private static final String SECRET_KEY_ALGORITHM = "DESede";
    private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

    /* Encryption Method */
    public static String encrypt(String message) throws Exception 
    { 
        
		try {
				
			MessageDigest md;
			md = MessageDigest.getInstance(ALGORITHM);
			final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8)); 
	        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24); 
	        for (int j = 0, k = 16; j < 8;) { 
	                keyBytes[k++] = keyBytes[j++]; 
	        } 

	        final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM); 
	        final IvParameterSpec iv = new IvParameterSpec(new byte[8]); 
	        final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING); 
	        cipher.init(Cipher.ENCRYPT_MODE, key, iv); 

	        final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8); 
	        final byte[] cipherText = cipher.doFinal(plainTextBytes); 
	        
	        return Base64Converter.encode(new String(cipherText)); 
	        
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
        
    } 
    
   /* Decryption Method */
    public static String decrypt(String message) throws Exception { 
        try {
        	message = Base64Converter.decode(message);
			final MessageDigest md = MessageDigest.getInstance(ALGORITHM); 
			final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8)); 
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24); 
			for (int j = 0, k = 16; j < 8;) { 
			        keyBytes[k++] = keyBytes[j++]; 
			} 

			final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM); 
			final IvParameterSpec iv = new IvParameterSpec(new byte[8]); 
			final Cipher decipher = Cipher.getInstance(TRANSFORMATION_PADDING); 
			decipher.init(Cipher.DECRYPT_MODE, key, iv); 

			final byte[] plainText = decipher.doFinal(message.getBytes()); 

			return new String(plainText, CHARSET_UTF_8);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
    }
    
    public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			return convertByteToHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
    
    public static String convertByteToHex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
    
    public static void main(String[] args) throws Exception { 
   
        String text = "hello";
        
        String codedtext =  MD5Converter.encrypt(text);
        System.out.println(codedtext); // this is a byte array, you'll just see a reference to an array
        
        String codedtext2 =  MD5Converter.getMD5(text);
        System.out.println(codedtext2); // this is a byte array, you'll just see a reference to an array
        
        //String decodedtext =  MD5Converter.decrypt(codedtext); 
        //System.out.println(decodedtext); // This correctly shows "TEST STRING TO ENCRYPT" 
    } 
}
