package com.elcom.util.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 *
 * @author anhdv
 */
public class SecurityUtil {

	private static final Logger logger = Logger.getLogger(SecurityUtil.class.getName());
	
    /**
     * From a base 64 representation, returns the corresponding byte[]
     *
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByte(String data) throws IOException {
        return Base64.getDecoder().decode(data);
    }

    /**
     * From a byte[] returns a base 64 representation
     *
     * @param data byte[]
     * @return String
     * @throws IOException
     */
    public static String byteToBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // Salt generation 64 bits long
        byte[] bSalt = new byte[8];
        random.nextBytes(bSalt);
        // Digest computation
        return byteToBase64(bSalt);
    }

    public static String getHash(int iterationNb, String password, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        byte[] saltArray = SecurityUtil.base64ToByte(salt);
        byte[] hash = SecurityUtil.getHash(iterationNb, password, saltArray);
        return byteToBase64(hash);
    }

    public static boolean isMatched(String strToCompare, String strHash, String salt, int iterationNb) throws Exception {
        byte[] saltArray = SecurityUtil.base64ToByte(salt);
        byte[] hash = SecurityUtil.getHash(iterationNb, strToCompare, saltArray);
        return Arrays.equals(hash, SecurityUtil.base64ToByte(strHash));
    }

    public static void main(String[] args) throws Exception {
		
    	String salt = generateSalt();
    	System.out.println("salt:" + salt);
    	System.out.println( getHash(20, "123456", salt) );
    	
    	System.out.println( isMatched("elcom123", "+vP+5akAG8KzUhuEoyTyQeXkJpc/Yif+E3cWZRCJIjk=", "x0RVXbJcWlA=", 20) );
	}
    
    public static String getSourceIp(HttpServletRequest request) {
    	
        String curIP = "";
        
        try {
        	
        	curIP = request.getHeader("X-FORWARDED-FOR");
            
            if (curIP == null)
                curIP = request.getRemoteAddr();
            
        }catch(Exception ex) {
        	
        	logger.error("getSourceIp.ex: " + ex.toString());
        }
        
        return curIP;
    }
}
