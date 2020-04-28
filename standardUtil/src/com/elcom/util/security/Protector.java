package com.elcom.util.security;

import com.elcom.model.constant.StandardConstant;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
public class Protector {

    private static final String ALGORITHM = "AES";
    private static final int ITERATIONS = 2;

    public static String encrypt(String value, String salt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);

        String valueToEnc = null;
        String eValue = value;
        for (int i = 0; i < ITERATIONS; i++) {
            valueToEnc = salt + eValue;
            byte[] encValue = c.doFinal(valueToEnc.getBytes());
            //eValue = new BASE64Encoder().encode(encValue);
            eValue = Base64.getEncoder().encodeToString(encValue);
        }
        return eValue;
    }

    public static String decrypt(String value, String salt) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);

        String dValue = null;
        String valueToDecrypt = value;
        for (int i = 0; i < ITERATIONS; i++) {
            //byte[] decordedValue = new BASE64Decoder().decodeBuffer(valueToDecrypt);
            byte[] decordedValue = Base64.getDecoder().decode(valueToDecrypt);
            byte[] decValue = c.doFinal(decordedValue);
            dValue = new String(decValue).substring(salt.length());
            valueToDecrypt = dValue;
        }
        return dValue;
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(StandardConstant.AES_KEY_VAL, ALGORITHM);
        // SecretKeyFactory keyFactory
        // = SecretKeyFactory.getInstance(ALGORITHM);
        // key = keyFactory.generateSecret(new DESKeySpec(keyValue));
        return key;
    }

    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        // SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // Salt generation 64 bits long
        byte[] bSalt = new byte[8];
        random.nextBytes(bSalt);
        // Digest computation
        return byteToBase64(bSalt);
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

    public static boolean isMatch(String inputPassword, String passwordEncrypted, String saltValue) {
        boolean result = false;
        try {
            if (inputPassword.equals(decrypt(passwordEncrypted, saltValue))) {
                result = true;
            }
        } catch (Exception ex) {
            System.out.println("Protector.isMatch().ex: " + ex.toString());
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

        String plainPassword = "!@#$%^&* để";
        String salt = generateSalt();
        String valueEnc = encrypt(plainPassword, salt);
        String passwordDec = decrypt(valueEnc, salt);

        System.out.println("Password plaint text : " + plainPassword);
        System.out.println("Salt value : " + salt);
        System.out.println("Encrypted value : " + valueEnc);
        System.out.println("Decrypted value : " + passwordDec);

        System.out.println(isMatch("123456", "j9CLi9dDFRcED+afNZrZ+2FjK199LjnJGOhfYuQ63rfzkIbwOjScZZHdQKtQcgfUm2JB0L6WdqJa1mPWYCqsxA==", "hbMFuUEoDkY="));

        //System.out.println( decrypt("j9CLi9dDFRcED afNZrZ 2FjK199LjnJGOhfYuQ63rfzkIbwOjScZZHdQKtQcgfUm2JB0L6WdqJa1mPWYCqsxA==", "hbMFuUEoDkY=") );
        System.out.println(decrypt("j9CLi9dDFRcED+afNZrZ+2FjK199LjnJGOhfYuQ63rfzkIbwOjScZZHdQKtQcgfUm2JB0L6WdqJa1mPWYCqsxA==", "hbMFuUEoDkY="));
    }
}
