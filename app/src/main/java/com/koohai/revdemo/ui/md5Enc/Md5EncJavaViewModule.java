package com.koohai.revdemo.ui.md5Enc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Md5EncJavaViewModule extends ViewModel {

    private final MutableLiveData<String> mText;

    public Md5EncJavaViewModule() {
        mText = new MutableLiveData<>();
        mText.setValue("This is java md5 enc  fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}