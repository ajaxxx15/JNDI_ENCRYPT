package com.ibm.demo1.configuration;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

//import java.util.*;

public class CipherEncrypte{
    Cipher ecipher;
    Cipher dcipher;

    byte[] salt = {
            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
            (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };

    int iterationCount = 19;

    CipherEncrypte(String passPhrase) {
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
            
            return new String(utf8, "UTF8");
        } catch (javax.crypto.BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (java.io.IOException e) {
        }
        return null;
    }

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
