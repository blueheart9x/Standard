package com.elcom.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class AES {
 
	private static final String SECRET_KEY = "secret";
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt) 
    {
        try
        {
            setKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt) 
    {
        try
        {
            setKey(SECRET_KEY);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec("4e5Wa71fYoT7MFEX".getBytes("UTF-8")));
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    private static final String key2 = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    public static String decrypt2(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key2.getBytes("UTF-8"), "AES");
     
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
     
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     
        return null;
    }

    
    public static void main(String[] args) 
    {
         
        //String originalString = " 00000000fe98974c";
        //String encryptedString = AES.encrypt(originalString) ;
        String decryptedString = AES.decrypt2("e0SRGIk02EjbC1m71PhoMHfafQOv6J0iYwDUV6KkwaUYnTuaJ4bkwBE23iTzf3EUmYsodm0hUkNGxJj/p9bPcQ==") ;
         
        //System.out.println(originalString2);
        //System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}
