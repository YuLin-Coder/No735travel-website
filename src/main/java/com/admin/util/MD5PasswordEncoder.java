package com.admin.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Mr.Ye
 * @Description: TODO()
 */
public class MD5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String endcodedPassword) {
        return md5Hex(rawPassword+"").equals(endcodedPassword);
    }

    private static String md5Hex(String password){
        //Java中MessageDigest类封装了MD5
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            String pwd = "";
            if (password!=null){
                byte[] byteArray = md5.digest(password.getBytes());
                //注意：MessageDigest只能将String转成byte[]
                pwd = byteArrayToHexString(byteArray);
            }
            return pwd;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        //调用MD5算法，即返回16个byte类型的值
//        try{
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] digest = md.digest(str.getBytes());
//            return new String(new Hex().encode(digest));
//        }catch (Exception e){
//            e.printStackTrace();
//            return "";
//        }
    }
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
