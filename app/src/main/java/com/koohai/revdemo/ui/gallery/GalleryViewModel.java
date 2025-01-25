package com.koohai.revdemo.ui.gallery;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koohai.encutils.SecurityMd5Utils;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public static String  EnvCheckByThreadFromC(){
        String tmp  = SecurityMd5Utils.env_check();
        Log.i("通过c 线程检测环境", tmp != null ? tmp : "Result was null");
        return tmp;
    }
    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(EnvCheckByThreadFromC());
//        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}