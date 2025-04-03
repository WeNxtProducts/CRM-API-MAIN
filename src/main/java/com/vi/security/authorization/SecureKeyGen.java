package com.vi.security.authorization;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecureKeyGen {

    private static final String _SECURITY_ALGORITHM_ = "AES/CBC/PKCS5PADDING" ;
    private static final String _SECURITY_SALT_ ="ssdkF$HUy2A#D%kd"; //s"L`JR|;8S_D;>NV@7D7hHe!FIr$@h.Y3G8f-3$<rt?XBF&|dl/!dFke*Ta#1ZVAYd'";
    private static Cipher cipher;
    private static IvParameterSpec ivParameterSpec;
    private static SecretKeySpec secretKeySpec;

    public SecureKeyGen() {

        System.out.print(_SECURITY_SALT_);
        ivParameterSpec = new IvParameterSpec(_SECURITY_SALT_.getBytes());
        try {
            secretKeySpec = new SecretKeySpec(_SECURITY_SALT_.getBytes("UTF-8"), "AES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            cipher = Cipher.getInstance(_SECURITY_ALGORITHM_);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }
    public static String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {



        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        System.out.print(plainText);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    public static String decrypt(String encryptedKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {

        try {

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(encryptedKey));
            return new String(plainText);
        }catch (IllegalArgumentException e){
            System.out.println("inside the catch ");
            System.out.println(e);
            return null;
        }catch (InvalidKeyException e) {
            e.printStackTrace();
            return  null;
        }catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return  null;
        }catch (BadPaddingException e) {
            e.printStackTrace();
            return  null;
        }catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return  null;
        }
    }
    
    public static Boolean validatePassword(String plainPassword,String encryptedPassword) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return decrypt(encryptedPassword).equals(plainPassword);
    }
    public static SealedObject  GenerateToken(Serializable securityToken)   throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        SealedObject sealedObject = new SealedObject(securityToken, cipher);
        System.out.println(sealedObject);
        return sealedObject;
    }
    public static Serializable  decryptToken(SealedObject securityToken) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        Serializable unsealObject = (Serializable) securityToken.getObject(cipher);
        return unsealObject;
    }
}
