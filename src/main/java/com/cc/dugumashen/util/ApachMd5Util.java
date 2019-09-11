package com.cc.dugumashen.util;

import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 * Date: 2019/9/6
 * Author：
 */
public class ApachMd5Util {

    private static final String ENDWITHONE = "317114103432683981";
    private static final String ENDWITHTWO = "5622744138376731573";
    private static final String ENDWITHTHREE = "6800003079019677720";
    private static final String ENDWITHFOUR = "9012332637318282354";
    private static final String ENDWITHFIVE = "2510073107703379110";
    private static final String ENDWITHSIX = "1202392806763915818";
    private static final String ENDWITHSEVEN = "7804356946154381255";
    private static final String ENDWITHEIGHT = "1180063319077631189";
    private static final String ENDWITHNINE = "6299230077007965051";
    private static final String ENDWITHZERO = "8405550456452435246";


    /**
     * @param text 明文密码
     * @param key  秘钥
     * @return 密文
     */
    public static String md5Encrypt(String text, String key) {

        StringBuilder strBuilder = new StringBuilder(text + key);
        String encryptStr = DigestUtils.md5DigestAsHex(strBuilder.toString().getBytes());
        return encryptStr;

    }


    public static boolean verify(String text, String key, String md5) {
        String md5Text = md5Encrypt(text, key);
        if (md5Text.equals(md5)) {
            return true;
        }
        return false;
    }

    /**
     * @param text 明文
     * @return
     * @Description本身盐值加密
     */
    public static String randomSaltEncrypt(String text) {

        int textHash = text.hashCode();
        String textHashStr = String.valueOf(textHash);
        String salt = textHashStr.substring(5);

        String secretText = md5Encrypt(text, salt);

        return secretText;
    }

    /**
     * @param text
     * @param secretText
     * @return
     * @Description 本身盐值验证密码
     */
    public static boolean randomSaltEncryptVerify(String text, String secretText) {

        int textHash = text.hashCode();
        String textHashStr = String.valueOf(textHash);
        String salt = textHashStr.substring(5);

        return verify(text, salt, secretText);
    }

    /**
     * 根据账号对密码加密
     *
     * @param account  账号
     * @param password 密码
     * @return 加密后的账号及秘钥
     */
    public static Map upEncrypt(String account, String password) {
        Map<String, String> map = new HashMap<>();
        int textHash = account.hashCode();
        String textHashStr = String.valueOf(textHash);
        String salt = textHashStr.substring(5);

        String secretText = md5Encrypt(password, salt);
        map.put("account", account);
        map.put("password", secretText);

        return map;
    }

    /**
     * 匹配账号和密码
     *
     * @param account  账号
     * @param password 密码
     * @param pwd      密文密码
     * @return
     */
    public static boolean matchAccAndPwd(String account, String password, String pwd) {

        String encryptPwd = (String) upEncrypt(account, password).get("password");
        if (encryptPwd.equals(pwd)) {
            return true;
        }
        return false;

    }


}
