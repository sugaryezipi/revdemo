package com.koohai.revdemo.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koohai.encutils.SecurityMd5Utils;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public static String  JavaEncInvokeCommon(){

        String res = SecurityMd5Utils.stringFromJNI("koohai");
        Log.i("反射调用md5", res != null ? res : "Result was null");
        return SecurityMd5Utils.calculateMD5Invoke("koohai 反射调用md5" + res);
    }
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }
    public String triggerEncryption() {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n 通过普通方法 实现md5: 参数 koohai \n ");
        sb.append(JavaEncInvokeCommon());


        // Optionally update the LiveData for observers to react
        mText.setValue(sb.toString());

        return sb.toString();
    }
    public LiveData<String> getText() {
        return mText;
    }
}