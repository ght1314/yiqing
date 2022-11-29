package com.demo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Salt {
    public static void  main(String[] args){
        String hashAlgorithName = "MD5";//加密算法
        String password = "11111";//登陆时的密码
        int hashIterations =1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("1001");//使用登录名做为salt
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println("ok "+simpleHash);
    }
}
