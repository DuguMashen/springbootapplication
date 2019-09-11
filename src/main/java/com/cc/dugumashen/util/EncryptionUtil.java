package com.cc.dugumashen.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * @author JavaDigest
 *
 */
public class EncryptionUtil {

    /**
     * String to hold name of the encryption algorithm.
     */
    public static final String ALGORITHM = "RSA";

    /**
     * String to hold name of the encryption padding.
     */
    public static final String PADDING = "RSA/NONE/NoPadding";

    /**
     * String to hold name of the security provider.
     */
    public static final String PROVIDER = "BC";

    /**
     * String to hold the name of the private key file.
     */
    public static final String PRIVATE_KEY_FILE = "d:/private.key";

    /**
     * String to hold name of the public key file.
     */
    public static final String PUBLIC_KEY_FILE = "d:/public.key";

    /**
     * Generate key which contains a pair of private and public key using 1024
     * bytes. Store the set of keys in Prvate.key and Public.key files.
     *生成秘钥并保存在仓库中
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void generateKey() {
        try {

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(
                    ALGORITHM, PROVIDER);
            keyGen.initialize(256);
            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);

            // Create files to store public and private key创建私钥仓库
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            // Saving the Public key in a file将公钥保存在文件中
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(key.getPublic());
            publicKeyOS.close();

            // Saving the Private key in a file私钥保存在文件中
            ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(key.getPrivate());
            privateKeyOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * The method checks if the pair of public and private key has been
     * generated.
     *检查秘钥对是否已经生成
     * @return flag indicating if the pair of keys were generated.
     */
    public static boolean areKeysPresent() {

        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);

        if (privateKey.exists() && publicKey.exists()) {
            return true;
        }
        return false;
    }

    /**
     * Encrypt the plain text using public key.
     *
     * @param text
     *            : original plain text   原文
     * @param key
     *            :The public key         公钥
     * @return Encrypted text             加密的文本
     * @throws java.lang.Exception
     */
    public static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            final Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

            // encrypt the plain text using the public key使用公钥加密明文
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    /**
     * Decrypt text using private key.
     *
     * @param text
     *            :encrypted text
     * @param key
     *            :The private key
     * @return plain text
     * @throws java.lang.Exception
     */
    public static String decrypt(byte[] text, PrivateKey key) {
        byte[] dectyptedText = null;
        try {
            // get an RSA cipher object and print the provider
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            final Cipher cipher = Cipher.getInstance(PADDING, PROVIDER);

            // decrypt the text using the private key使用私钥解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }

    public static String encrypt(String plaintext) {

        String ciphertext;

        // Check if the pair of keys are present else generate those.检查秘钥对是否已经生成
        if (!areKeysPresent()) {
            //检查是否存在秘钥库，生成一对秘钥在他们各自的文件库
            generateKey();
        }
        ObjectInputStream inputStream = null;
        // Encrypt the string using the public key 使用公钥加密数据
        try {
            inputStream = new ObjectInputStream(new FileInputStream(
                    PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            final byte[] cipherText = encrypt(plaintext, publicKey);

            // use String to hold cipher binary data 使用字符串保存二进制数据
            Base64 base64 = new Base64();
            ciphertext = base64.encodeToString(cipherText);
            return ciphertext;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String decrypt(String ciphertext){

        try {
            Base64 base64 = new Base64();
            byte[] cipherTextArray = base64.decode(ciphertext);
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(
                    PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            final String plainText = decrypt(cipherTextArray, privateKey);
            return plainText;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Test the EncryptionUtil
     */
    public static void main(String[] args) {

        /*try {
            // Check if the pair of keys are present else generate those.检查秘钥对是否已经生成
            if (!areKeysPresent()) {
                // Method generates a pair of keys using the RSA algorithm and
                // stores it
                // in their respective files生成一对秘钥在他们各自的文件库
                generateKey();
            }
            final String originalText = "12345678901234567890123456789012";
            ObjectInputStream inputStream = null;
            // Encrypt the string using the public key 使用公钥加密数据
            inputStream = new ObjectInputStream(new FileInputStream(
                    PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            final byte[] cipherText = encrypt(originalText, publicKey);
            // use String to hold cipher binary data 使用字符串保存二进制数据
            Base64 base64 = new Base64();
            String cipherTextBase64 = base64.encodeToString(cipherText);
            // get cipher binary data back from String 从字符串中获取cipher二进制数据
            byte[] cipherTextArray = base64.decode(cipherTextBase64);
            // Decrypt the cipher text using the private key.  私钥解密
            inputStream = new ObjectInputStream(new FileInputStream(
                    PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            final String plainText = decrypt(cipherTextArray, privateKey);
            // Printing the Original, Encrypted and Decrypted Text 打印出原文、加密后的、解密后的
            System.out.println("Original=" + originalText);
            System.out.println("Encrypted=" + cipherTextBase64);
            System.out.println("Decrypted=" + plainText);

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*String plaintText="这是明文";
        String ciphertext=encrypt(plaintText);
        String decrypted=decrypt(ciphertext);
        System.out.println(plaintText);
        System.out.println(ciphertext);
        System.out.println(decrypted);*/

        String path="classpath:dev/securityManage.properties";
        File file=new File(path);
        System.out.println(file.exists());
    }
}
