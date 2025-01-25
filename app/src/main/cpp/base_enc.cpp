//
// Created by kooha on 2025-01-15.
//
#include "MD5.h"
#include <jni.h>
#include "base_enc.h"
#include <string>
#include <android/log.h>
#include <pthread.h>
#include <dlfcn.h>

#define TAG "koohai"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__);
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO , TAG, __VA_ARGS__);

// 确保 extern "C" 块正确包裹所有 JNI 函数


jstring md5FromJni(JNIEnv *env, jclass clazz, jstring input) {
    const char *str = env->GetStringUTFChars(input, nullptr);
    LOGI("md5FromJni before enc: %s", str)
    std::string result = md5(str);
    env->ReleaseStringUTFChars(input, str); // 释放字符串资源
    LOGI("md5FromJni after enc : %s", result.c_str())
    return env->NewStringUTF(result.c_str());
}


jstring getSalt(JNIEnv *env,jclass jclazz) {
    LOGI("getSalt FromJni: koohaiFromC ")
    return env->NewStringUTF("koohaiFromC");

}
