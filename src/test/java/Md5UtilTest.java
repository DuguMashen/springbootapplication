package com.cc.dugumashen.util;
import com.cc.dugumashen.util.ApachMd5Util;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;

import java.security.SecureRandom;

/**
 * Description：
 * Date: 2019/9/6
 * Author：
 */
public class Md5UtilTest {
    @Test
    public void test1(){
        String salt="admin";
        String text="alskj54s2384.//";
        String encr=ApachMd5Util.md5Encrypt(text,salt);
        boolean result=ApachMd5Util.verify(text,salt,encr);
        System.out.println(result);
    }

    @Test
    public void test2(){
        SecureRandom secureRandom=new SecureRandom();
        secureRandom.generateSeed(16);
        long ong=secureRandom.nextLong();
        System.out.println(ong);

    }
    @Test
    public void test3(){
        String text="druid";
        String secr=ApachMd5Util.md5Encrypt(text,text);

    }
    @Test
    public void generator(){

        String password="admin";
        String secr=ApachMd5Util.randomSaltEncrypt(password);
        System.out.println(secr);
        boolean verifypwd=ApachMd5Util.randomSaltEncryptVerify(password,secr);
        System.out.println(verifypwd);

    }



}
