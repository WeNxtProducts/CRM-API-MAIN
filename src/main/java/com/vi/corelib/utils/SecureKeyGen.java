package com.vi.corelib.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Slf4j
public class SecureKeyGen {

    private static final String SECURITYALGORITHM = "AES/CBC/PKCS5PADDING" ;
    private static final String SECURITYSALT ="ssdkF$HUy2A#D%kd"; //s"L`JR|;8S_D;>NV@7D7hHe!FIr$@h.Y3G8f-3$<rt?XBF&|dl/!dFke*Ta#1ZVAYd'";
    private static IvParameterSpec ivParameterSpec;
    private static SecretKeySpec secretKeySpec;


    public  SecureKeyGen() {
        SecureRandom random = new SecureRandom();
        byte[] bytesIV = new byte[16];
        random.nextBytes(bytesIV);

        /* KEY + IV setting */
        //ivParameterSpec= new IvParameterSpec(bytesIV);
        ivParameterSpec = new IvParameterSpec(SECURITYSALT.getBytes());
        try {
            secretKeySpec = new SecretKeySpec(SECURITYSALT.getBytes("UTF-8"), "AES");
        } catch (UnsupportedEncodingException e) {
            log.error(String.valueOf(e));
        }

    }
    @SneakyThrows
    public  String encrypt(String plainText) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
         Cipher cipher;

        synchronized (Cipher.class)
        {
            cipher = Cipher.getInstance(SECURITYALGORITHM);
        }

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    public static byte[] getKeyBytes(String appsecretkey) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[32];
        byte[] parameterKeyBytes = appsecretkey.getBytes("UTF-8");
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }

    @SneakyThrows
    public static String encryptWithMyKey(String data, String key, String algorithm) {
        Cipher cipher = Cipher.getInstance("AES");
        byte[] seckeyBytes = getKeyBytes(key);
        SecretKeySpec AESsecretKey = new SecretKeySpec(seckeyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, AESsecretKey);
        byte[] encryptedText = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedText);
    }
    @SneakyThrows
    public static String encryptWithMyKey(String data, String key) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    @SneakyThrows
    public static String getRandomString(String key) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 32;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(key.length());
            char randomChar = key.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    @SneakyThrows
    public static String  decryptWithMyKey(String data, String key) {

        byte[] cipheredBytes = Base64.getDecoder().decode(key);
        byte[] keyBytes = getKeyBytes(data);

        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpecy = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy);
        byte[] convertedValue = cipher.doFinal(cipheredBytes);
        String convertedData= new String(convertedValue);
        return convertedData;
    }

    public static String decryptWithMyKey(String appsekkey, String encryptedKey,String mode) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeySpecException {
        try {

            byte[] cipheredBytes = Base64.getDecoder().decode(encryptedKey);
            byte[] keyBytes = getKeyBytes(appsekkey);

            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpecy = new SecretKeySpec(keyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy);
            byte[] bytebdosekdecryptbyte = cipher.doFinal(cipheredBytes);
            String bdosekdecrypt = new String(bytebdosekdecryptbyte);
            return bdosekdecrypt;
        } catch (IllegalArgumentException e) {
            log.error(String.valueOf(e));
            return null;
        } catch (InvalidKeyException e) {
            log.error(String.valueOf(e));
            return null;
        } catch (BadPaddingException e) {
            log.error(String.valueOf(e));
            return null;
        } catch (IllegalBlockSizeException e) {
            log.error(String.valueOf(e));
            return null;
        }
    }


    public  Boolean isValidAuthToken(String url,String authToken,String timeStamp) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ParseException, URISyntaxException {
        HashMap<String,String> queryParams = UrlUtility.getQueryParamsFromUrl(url);
        if(queryParams!=null || timeStamp!=null) {
            //String authToken = URLDecoder.decode(queryParams.get("authToken"),StandardCharsets.UTF_8);
            Date timestamp = new Date();
            if (timeStamp!=null){
                timestamp = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").parse(timeStamp);
            }else {
                String decodedUrl = decrypt(authToken);
                HashMap<String,String> tokenUrl = UrlUtility.getQueryParamsFromUrl(decodedUrl);
                timestamp = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").parse(URLDecoder.decode(tokenUrl.get("timestamp"), StandardCharsets.UTF_8.toString()));
            }

            Date now = new Date();
            Long diff = (now.getTime() - timestamp.getTime())/1000;
            System.out.print(now);
            System.out.print(timestamp);
            System.out.print("Diff"+diff);
            return diff>260 ? false : true;
        }
        return false;
    }
    @SneakyThrows
    public  String decrypt(String encryptedKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher;

        try {
            synchronized (Cipher.class)
            {
                cipher = Cipher.getInstance(SECURITYALGORITHM);
            }

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(encryptedKey));
            return new String(plainText);
        }catch (IllegalArgumentException e){
            log.error(String.valueOf(e));
            return null;
        }catch (InvalidKeyException e) {
            log.error(String.valueOf(e));
            return  null;
        }catch (InvalidAlgorithmParameterException e) {
            log.error(String.valueOf(e));
            return  null;
        }catch (BadPaddingException e) {
            log.error(String.valueOf(e));
            return  null;
        }catch (IllegalBlockSizeException e) {
            log.error(String.valueOf(e));
            return  null;
        }
    }
    public static SealedObject  GenerateToken(Serializable securityToken)   throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException {
        Cipher cipher;

        synchronized (Cipher.class)
        {
            cipher = Cipher.getInstance(SECURITYALGORITHM);
        }


        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        SealedObject sealedObject = new SealedObject(securityToken, cipher);
        return sealedObject;
    }
    public static Serializable  decryptToken(SealedObject securityToken) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
        Cipher cipher;

        synchronized (Cipher.class)
        {
            cipher = Cipher.getInstance(SECURITYALGORITHM);
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        Serializable unsealObject = (Serializable) securityToken.getObject(cipher);
        return unsealObject;
    }
    @SneakyThrows
    public static String getAuthToken(String url) {
        Cipher cipher;

        synchronized (Cipher.class)
        {
            cipher = Cipher.getInstance(SECURITYALGORITHM);
        }

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] cipherText = cipher.doFinal(url.getBytes());
        String authToken = Base64.getEncoder()
                .encodeToString(cipherText);
        return authToken;
    }
    public static String getSignedUrl(String url) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, URISyntaxException {
        Cipher cipher;

        synchronized (Cipher.class)
        {
            cipher = Cipher.getInstance(SECURITYALGORITHM);
        }

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        //URIBuilder.build(false).toUriString()
        //url = new URIBuilder(url).addParameter("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).build().toString();
        //url = URLDecoder.decode(url);
        url = url +"&timestamp="+new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(new Date());
        byte[] cipherText = cipher.doFinal(url.getBytes());
        String authToken = Base64.getEncoder()
                .encodeToString(cipherText);
        System.out.println(authToken);
        System.out.println(url+"&authToken="+authToken);
        return url+"&authToken="+authToken;
                //new URIBuilder(url).addParameter("authToken", authToken).build().toString();
    }
}
