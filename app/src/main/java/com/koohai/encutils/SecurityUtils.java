package com.koohai.encutils;

import java.lang.reflect.Method;
import java.security.MessageDigest;

public class SecurityUtils {

    // MD5 加密方法
    public static String calculateMD5Invoke(String input) {
        try {
            // 创建 MessageDigest 实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 通过反射调用 update 方法
            Method updateMethod = MessageDigest.class.getDeclaredMethod("update", byte[].class);
            updateMethod.setAccessible(true); // 允许访问私有方法
            updateMethod.invoke(md, input.getBytes());

            // 通过反射调用 digest 方法
            Method digestMethod = MessageDigest.class.getDeclaredMethod("digest");
            digestMethod.setAccessible(true);
            byte[] digest = (byte[]) digestMethod.invoke(md);

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
}