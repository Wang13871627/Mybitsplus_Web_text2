package com.huayu.mybitsplus.web;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.beans.SimpleBeanInfo;

public class Text {
    public static void main(String[] args) {
    	String hashAlgorithmName="MD5";
    	String password="147258";
        Object salt=ByteSource.Util.bytes("宋帅");
        Integer hashIterations=1024;
        Object s=new SimpleHash(hashAlgorithmName,password,salt,hashIterations);



        System.out.println(s);
    }
}
