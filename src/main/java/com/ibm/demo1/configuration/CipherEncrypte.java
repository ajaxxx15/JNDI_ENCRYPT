package com.ibm.demo1.configuration;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class CipherEncrypte {
	
	/*
	 * Encryption Algorithm is AES
	 */
    
//    private static final String ALGORITHM = "AES";
//
////    private static final String defaultSecretKey = "PHRASETOREPLACE";
//    private static final String defaultSecretKey = "CompEncryptedDataSourceFactory";
//
//    private Key secretKeySpec;
//
//    public CipherEncrypte() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
//            UnsupportedEncodingException {
//        this(null);
//    }
//
//    public CipherEncrypte(String secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
//            UnsupportedEncodingException {
//        this.secretKeySpec = generateKey(secretKey);
//    }
//
//    public String encrypt(String plainText) throws InvalidKeyException, NoSuchAlgorithmException,
//            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
//        return asHexString(encrypted);
//    }
//
//    public String decrypt(String encryptedString) throws InvalidKeyException, IllegalBlockSizeException,
//            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        byte[] original = cipher.doFinal(toByteArray(encryptedString));
//        return new String(original);
//    }
//
//    private Key generateKey(String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        if (secretKey == null) {
//            secretKey = defaultSecretKey;
//        }
//        byte[] key = (secretKey).getBytes("UTF-8");
//        MessageDigest sha = MessageDigest.getInstance("SHA-1");
//        key = sha.digest(key);
//        key = Arrays.copyOf(key, 16); // use only the first 128 bit
//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//        kgen.init(128); // 192 and 256 bits may not be available
//
//        return new SecretKeySpec(key, ALGORITHM);
//    }
//
//    private final String asHexString(byte buf[]) {
//        StringBuffer strbuf = new StringBuffer(buf.length * 2);
//        int i;
//        for (i = 0; i < buf.length; i++) {
//            if (((int) buf[i] & 0xff) < 0x10) {
//                strbuf.append("0");
//            }
//            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
//        }
//        return strbuf.toString();
//    }
//
//    private final byte[] toByteArray(String hexString) {
//        int arrLength = hexString.length() >> 1;
//        byte buf[] = new byte[arrLength];
//        for (int ii = 0; ii < arrLength; ii++) {
//            int index = ii << 1;
//            String l_digit = hexString.substring(index, index + 2);
//            buf[ii] = (byte) Integer.parseInt(l_digit, 16);
//        }
//        return buf;
//    }

////previous code
	/*
	 * Encryption Algorithm is Base64
	 */
	
	Cipher ecipher;
    Cipher dcipher;

    byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };

    int iterationCount = 19;
    private static final String passPhrase = "CompEncryptedDataSourceFactory";

    public CipherEncrypte() {
        try {
// Create the key
            PBEKeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray());
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

// Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

// Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (java.security.InvalidAlgorithmParameterException e) {
        } catch (java.security.spec.InvalidKeySpecException e) {
        } catch (javax.crypto.NoSuchPaddingException e) {
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (java.security.InvalidKeyException e) {
        }
    }

    public String encrypt(String str) {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            String base64Encoder = Base64.getEncoder().encodeToString(enc);
            return base64Encoder;
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (Exception e) {
        }
        return null;
    }
	
    public String decrypt(String str) {
        try {
// Decode base64 to get bytes

            byte[] base64DecodedData = Base64.getMimeDecoder().decode(str);
//            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

// Decrypt
            byte[] utf8 = dcipher.doFinal(base64DecodedData);

// Decode using utf-8
            String s=new String(utf8, "UTF8");
            System.out.println(s);
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

	
	
	
	/*
	 * using this you can run this class independently
	 */
//    public static void main(String[] args)
//    {
//
//        try {
//            CipherEncrypte encrypter = new CipherEncrypte("CompEncryptedDataSourceFactory"); // key for encryption
//            String encrypted = encrypter.encrypt("root@123");
//            System.out.println("encrypted :"+encrypted);
//
//            String decrypted = encrypter.decrypt(encrypted);
//            System.out.println("decrypted :"+decrypted);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }

//    }
}
