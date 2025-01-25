package com.koohai.encutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.List;

public class SecurityMd5Utils {



    static {
        System.loadLibrary("revdemo");
    }
    

    // MD5 加密方法
    @NonNull
    public static String calculateMD5Invoke(String input) {
        try {
            // 创建 MessageDigest 实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 通过反射调用 update 方法
            Method updateMethod = MessageDigest.class.getDeclaredMethod("update", byte[].class);
            updateMethod.setAccessible(true); // 允许访问私有方法
            updateMethod.invoke(md, (Object) input.getBytes());

            // 通过反射调用 digest 方法
            Method digestMethod = MessageDigest.class.getDeclaredMethod("digest");
            digestMethod.setAccessible(true);
            byte[] digest = (byte[]) digestMethod.invoke(md);

            // 转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            assert digest != null;
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }


    public static String calculateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();

            // 转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // so层的md5 直接进行修改 。

    public static native String stringFromJNI(String c);

    public static  native String md5FromJni();
    public static  native String env_check();




}