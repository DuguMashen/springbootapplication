package com.cc.dugumashen.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 * Date: 2019/9/6
 * Author：
 */
public class RSAEncryptUtil {

    public static final String PULBIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmdtM+fTxVl5MYV1Jq4Ian2QvYTqL3bwyRGaK1hFLL3QgjgTO3Pp+jZnAb749ls+l6bGetOaKMIyqi/2gWqjKK5WGSAxpfbaCUDgOtY42P6J30a0ZbZmS3AXwdUHBvFti7hzvvdLY4U4ESyxR8mP+R082GM2lIFB1fYCWPcRa79WRNpCF0r+sjdz0qTKJB90+xcAarSAPU4X2ELuQ6FpxblqTayF6yLlZBE+GhwxdE902rnlMnq/mUGRqkvLGermxwRnmL9ntJs8B/jJdKoNN/3jIiFAy2MIhsZBhcOGfIKBXXzVXYGS2dlJ6QVu51bTVw3hWYHMHiBGoxd9nFoheowIDAQAB";

    public static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCZ20z59PFWXkxhXUmrghqfZC9hOovdvDJEZorWEUsvdCCOBM7c+n6NmcBvvj2Wz6XpsZ605oowjKqL/aBaqMorlYZIDGl9toJQOA61jjY/onfRrRltmZLcBfB1QcG8W2LuHO+90tjhTgRLLFHyY/5HTzYYzaUgUHV9gJY9xFrv1ZE2kIXSv6yN3PSpMokH3T7FwBqtIA9ThfYQu5DoWnFuWpNrIXrIuVkET4aHDF0T3TaueUyer+ZQZGqS8sZ6ubHBGeYv2e0mzwH+Ml0qg03/eMiIUDLYwiGxkGFw4Z8goFdfNVdgZLZ2UnpBW7nVtNXDeFZgcweIEajF32cWiF6jAgMBAAECggEAQO950ZU7toQeIInK6gQDUHpsNNEM7wza+lt9BVRPMzwD8Wy+Ei3287SMr5zPSbNiBvvcS+VxNYRpBqSI5TqP+U0mW4pBi7lXzpuaOa5H3xunDmvMa6GBhjNLhXG3XtQDMRQtayRuPMk8rq2J+I+TZm1cG6PPqY/LxqhawL760nXbrA3Zc4BF5GNpvyWPpS0yHEvN4jCjrTwB/Tp+Q2WYZPmPLpn6lEGwURAjOsotYkFHKELAlm28/ldZHIAlywsheaougroPe5UsqN0qiW4w/12aMM9dNXV9qpE1yqj7/hPbHAKfHfoPSdQE1cgaIApwyk1DfvHSJ6W45qbOwyueAQKBgQDj/94OKWb+DE3wGJt5mvgXTdFZHf1Kukcu6AbWc9awS1Z9DjdR1153ChhOWtTmS9PIlEHGa0YNa6ZGdOABkA8PHWKPCshEbrYP2pJTNURKxPM41ZgQF2A2aRbJ6HubXyOn09pAM8GASvvI7qReWXj+clfHWwfFoxqZIuy1Zr3lQQKBgQCswHAmIPtzMT+DmNonR0+iEXDdV+oI2RKCEQIR4ZRqcmhpqJygiW9GpiMKUEyQeE5ZnZYt0iNKZio12hdWrPsmr5pjbWcVP9jlt+CSbiZfBrSQRKQ8oBzYlkQ0UVwRiUpmd1k15W9xz6TksQl4gibhKFkcLiE+B9cIafm3CVKW4wKBgQDdPIXTRhd7QqNJ7AbJjDdaHZebmv8SSQkTmfAwnfzf2mrVNDs3vf0bE3+cYIUf5spdYockqykxkpio23YV7hy+tOvTE0pbJPajEZtVBGX9C3lbV9+TFau1gGmtiFqQ3FOz4V1xWQdVnu0M/4wdRUKj3H4SouE6xNbla783ErgHQQKBgQCHd2B6heKosjXcPEDWDFWmAYdAC3C6/35EAJJe80YvMwgt0ssAGMXp4N4ZeF6R68WFAM6DoAWMkhEHqb4jegJ4k7u9vEzZnPe5LDgum7H7UobFmw4vfF8ieLODU0mfdsmhEHYkjYaW+P2VWD7VATr71WgLcSEgQ4WcU34+ylC5gQKBgB5AHURX1JtmIcZvtrdVIHjLe6nh6R/ds5J05/hPBA2aOL8WMjMKL0M45p1p48maM3vrEGZMfqo86PIi+mW8zGAUva6EAHyET08KxMr67NUmu64ECc9TyK6766CRmzRkBTCITGbQy0b76Bk21yYKl7S2wsZqvRjKx/LEc8FNUgmj";

    public static final String SIGN_NAME = "RSA";

    public static final String SECURITY_SEED = "securitySeed";

    public static final String PADDING = "RSA/NONE/NoPadding";

    public static final String PROVIDER = "BC";


    /**
     * 生成公钥与私钥对
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getKeys() throws Exception {
        Map keys = new HashMap(2);
        int len = 1024;
        //获取实例
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(PADDING, PROVIDER);
        //secureRandom
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(SECURITY_SEED.getBytes("UTF-8"));
        //初始化
        keyPairGenerator.initialize(len, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        keys.put("privateKey", privateKey);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        keys.put("publicKey", publicKey);
        return keys;
    }


    public static String decrypt(String str, String privateKey) throws Exception {
        try {
            byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
            byte[] decoded = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(SIGN_NAME).
                    generatePrivate(new PKCS8EncodedKeySpec(decoded));
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            String outStr = new String(cipher.doFinal(inputByte));
            return outStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new Exception("不支持的编码格式异常63");
        } catch (InvalidKeySpecException e) {

            e.printStackTrace();
            throw new Exception("68");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("71");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new Exception("74");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new Exception("77");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("80");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("83");
        }


    }


    public static String encrypt(String str, String publicKey) throws Exception {
        try {
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(SIGN_NAME).
                    generatePublic(new X509EncodedKeySpec(decoded));
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
            return outStr;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new Exception("100");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("103");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new Exception("106");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new Exception("109");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("112");
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("115");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new Exception("118");
        }
    }

    public static Long getSecurityLong() {
        SecureRandom secureRandom = new SecureRandom();
        Long lgon = secureRandom.nextLong();
        return lgon;
    }

    public static String encrypt(String text) {
        try {
            return encrypt(text, PULBIC_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decrypt(String secStr) {
        try {
            return decrypt(secStr, PRIVATE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) {

      /*  try {
            Map map=getKeys();
            System.out.println(map.get("publicKey"));
            System.out.println(map.get("privateKey"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
      /*long lo=getSecurityLong();
        System.out.println(lo);*/
        String text = "druid";
        try {
            String secuText = encrypt(text);
            String ss = decrypt(secuText);
            System.out.println(secuText);
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
