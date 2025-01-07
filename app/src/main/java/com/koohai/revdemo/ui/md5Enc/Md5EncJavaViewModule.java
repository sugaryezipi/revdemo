package com.koohai.revdemo.ui.md5Enc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.security.MessageDigest;
import com.koohai.encutils.SecurityUtils;
public class Md5EncJavaViewModule extends ViewModel {

    private final MutableLiveData<String> mText;

    public static String  JavaEncInvoke(){

        return SecurityUtils.calculateMD5Invoke("koohai456");
    }

    public static String  JavaEncInvokeCommon(){

        return SecurityUtils.calculateMD5("koohai123");
    }

    public Md5EncJavaViewModule() {
        mText = new MutableLiveData<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" \n 通过普通方法 实现md5: 参数koohai123 \n ");
        sb.append(JavaEncInvokeCommon());
        sb.append(" \n通过反射实现md5: 参数koohai456  \n");
        sb.append(JavaEncInvoke());


        mText.setValue(sb.toString());
    }

    public LiveData<String> getText() {
        return mText;
    }
}