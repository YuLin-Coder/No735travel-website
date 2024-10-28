package com.wink.util;

import java.security.MessageDigest;

/**
 * @Author: Mr.Ye
 * @Description: TODO(将明文密码转成MD5密码，123456 - > e10adc3949ba59abbe56e057f20f883e)
 */
public class Md5Util {
    private Md5Util(){}
    /**
     * 将明文密码转成MD5密码
     */
    public static String encodeByMd5(String password) throws Exception {
        //Java中MessageDigest类封装了MD5
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //调用MD5算法，即返回16个byte类型的值
        String pwd = "";
        if (password!=null){
            byte[] byteArray = md5.digest(password.getBytes());
            //注意：MessageDigest只能将String转成byte[]
            pwd = byteArrayToHexString(byteArray);
        }
        return pwd;
    }

    /**
     * 将byte[]转在16进制字符串
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte b : byteArray) {
            //取出每一个byte类型，进行转换
            String hex = byteToHexString(b);
            //将转换后的值放入StringBuffer中
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将byte转在16进制字符串
     */
    private static String byteToHexString(byte b) {
        //将byte类型赋给int类型
        int n = b;
        //如果n是负数
        if(n < 0){
            //转正数
            //-31的16进制数，等价于求225的16进制数
            n = 256 + n;
        }
        //商(14)，数组的下标
        int d1 = n / 16;
        //余(1)，数组的下标
        int d2 = n % 16;
        //通过下标取值
        return hex[d1] + hex[d2];
    }

    private static String[] hex = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
}
