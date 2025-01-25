package com.koohai.revdemo.ui.md5Enc;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koohai.encutils.SecurityMd5Utils;
import com.koohai.encutils.Utils;

public class Md5EncJavaViewModule extends AndroidViewModel {

    private final MutableLiveData<String> mText;

    public static String  JavaEncInvoke(Context context){

        String res = SecurityMd5Utils.stringFromJNI("koohai");
        Log.i("反射调用md5", res != null ? res : "Result was null");
        return SecurityMd5Utils.calculateMD5Invoke("koohai 反射调用md5" + res);
    }

    public static String  JavaEncInvokeCommon(){

        return SecurityMd5Utils.calculateMD5("koohai invode ");
    }

    public static String JavaEncInvokeFromc(Context context) {
        // Get the path to the shared library

        String res = SecurityMd5Utils.md5FromJni();
        Log.i("通过c层调用md5", res != null ? res : "Result was null");
        // Call the JNI function
        return res;
    }
    public Md5EncJavaViewModule(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" \n 通过普通方法 实现md5: 参数 koohai \n ");
        sb.append(JavaEncInvokeCommon());
        sb.append(" \n通过反射实现md5: 参数 koohai 反射调用md5  xx\n");
        sb.append(JavaEncInvoke(application));

        sb.append(" \n通过c层调用md5 参数 koohai \n");
        sb.append(JavaEncInvokeFromc(application));

        mText.setValue(sb.toString());
    }
    public String triggerEncryption() {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n 通过普通方法 实现md5: 参数 koohai \n ");
        sb.append(JavaEncInvokeCommon());
        sb.append(" \n通过反射实现md5: 参数 koohai 反射调用md5  xx\n");
        sb.append(JavaEncInvoke(getApplication()));

        sb.append(" \n通过c层调用md5 参数 koohai \n");
        sb.append(JavaEncInvokeFromc(getApplication()));

        // Optionally update the LiveData for observers to react
        mText.setValue(sb.toString());

        return sb.toString();
    }
    public LiveData<String> getText() {
        return mText;
    }
}