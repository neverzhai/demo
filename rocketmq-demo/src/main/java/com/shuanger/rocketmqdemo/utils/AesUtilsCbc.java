package com.shuanger.rocketmqdemo.utils;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class AesUtilsCbc {

    private static final String KEY_ALGORITHMS = "AES";
    private static final String CHAR_SETS = "UTF-8";
    /**
     * AES的密钥长度
     */
    private static final Integer SECRET_KEY_LENGTHS = 128;
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHMS = "AES/ECB/PKCS5Padding";
    private static final String iv = "9375567890182463";

    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        //生成指定算法密钥的生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHMS);
        keyGenerator.init(SECRET_KEY_LENGTHS, new SecureRandom(password.getBytes()));
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //转换成AES的密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHMS);
    }


    // 加密
    public static String AESencrypt(String sSrc,String privateKey) {
        if(sSrc==null||"".equals(sSrc)){
            return null;
        }
        byte[] encrypted = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = privateKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,paramSpec);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(encrypted);
    }


    // 解密
    public static String AESdecrypt(String sSrc,String privateKey) {
        if(sSrc==null||"".equals(sSrc)){
            return null;
        }
        try {
            byte[] raw = privateKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,paramSpec);
            byte[] encrypted1 = Base64.decodeBase64(sSrc.getBytes("UTF-8"));
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
}
