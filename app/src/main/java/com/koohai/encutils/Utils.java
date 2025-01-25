package com.koohai.encutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.security.MessageDigest;
import java.util.List;
public class Utils {
    public static String publicStaticStringField = "this is publicStaticStringField";
    public String publicStringField = "this is publicStringField";

    private static String privateStaticStringField = "this is privateStaticStringField";
    private String privateStringField = "this is privateStringField";
    private byte[] byteArray = new byte[]{1,2,3,4,5,6,7,8,9,10};
    public static String calculateUtilsMD5(String input) {
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
    public Utils(){
        Log.d("koohai", "this is ReflectDemo()");
    }

    public Utils(String str){
        Log.d("koohai", "this is ReflectDemo(String str)");
    }

    public Utils(String str, int i){
        Log.d("koohai", i + " " + str);
        Log.d("koohai", "this is ReflectDemo(String str, int i)");
    }

    public static void publicStaticFunc(){
        Log.d("koohai", "this is publicStaticFunc");
    }

    public void publicFunc(){
        Log.d("koohai", "this is publicFunc");
    }

    private static int[] privateStaticFunc(String[] str){
        StringBuilder retval = new StringBuilder();
        for(String i : str) {
            retval.append(i);
        }
        Log.d("koohai", "this is privateStaticFunc: " + retval.toString());
        return new int[]{0,1,2,3,4,5,6,7,8,9};
    }

    private String privateFunc(String str, int i){
        Log.d("koohai", i + " this is privateFunc: " + str);
        return "this is from java";
    }

}
