package com.demo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {

    public static String setSalt(String account, String pwd) {
        String hashAlgorithName = "MD5";//加密算法
        String password = pwd;//登陆时的密码
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);//使用登录名做为salt
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        return simpleHash.toHex();
    }

    public static void main(String[] args) {
        System.out.println(setSalt("1001","123456"));
        System.out.println(setSalt("1002","123456"));
        System.out.println(setSalt("1003","123456"));
    }

}
