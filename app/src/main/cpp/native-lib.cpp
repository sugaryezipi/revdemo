#include <jni.h>
#include <string>
#include <android/log.h>
#include <pthread.h>
#include <dlfcn.h>
#include "MD5.h"
#define TAG "koohai"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__);
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO , TAG, __VA_ARGS__);
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__);

// 确保 extern "C" 块正确包裹所有 JNI 函数
extern "C" {

void* myThread(void* a){

}

jstring envCheck(JNIEnv* env, jclass jclazz) {
    LOGD("开启线程检测模块 ");
    pthread_t thread;
    pthread_create(&thread, nullptr,myThread, nullptr);
    pthread_join(thread, nullptr);

    return env->NewStringUTF("checkrews ");


}


jstring md5Enc(JNIEnv* env, jclass jclazz) {


    // 加载指定的共享库
//    void* handle = dlopen("libkhsig.so", RTLD_NOW);
    void* soinfo = dlopen("libkhsig.so", RTLD_NOW);
    if (!soinfo) {
        LOGE("Failed to load SO file: %s", dlerror());
    }


    // 使用函数指针定义对应函数签名
    using Md5Func = jstring (*)(JNIEnv*, jclass, jstring);

    // 解析 `md5FromJni` 函数符号
    Md5Func md5FromJni = reinterpret_cast<Md5Func>(dlsym(soinfo, "_Z10md5FromJniP7_JNIEnvP7_jclassP8_jstring"));

    if (!md5FromJni) {
        LOGE("Failed to find function 'md5FromJni': %s", dlerror());
        dlclose(soinfo); // 卸载库后返回
        return nullptr;
    }

    // 准备测试输入，调用 `md5FromJni`
    jstring input = env->NewStringUTF("koohaifromc_md5"); // 传入的字符串
    jstring result = md5FromJni(env, jclazz, input);

    // 释放动态加载的库
    dlclose(soinfo);

    // 返回结果
    return result;
}



jstring callFuncJava(JNIEnv* env, jclass jclazz) {
    jclass Utillclazz= env->FindClass("com/koohai/encutils/SecurityMd5Utils");
    jmethodID init_ = env->GetMethodID(Utillclazz,"<init>","()V");

    jobject  utilObj = env->NewObject(Utillclazz,init_);
    LOGD("utilObj %p", utilObj);


}

jstring encodeFromC(JNIEnv* env, jclass jclazz, jstring c) {
    // Convert the Java string (SO path) to a C-style string
    const char* soPath = env->GetStringUTFChars(c, nullptr);
    if (soPath == nullptr) {
        LOGE("Failed to get SO path string");
        return nullptr;
    }

    // Load the specified shared library
//    void* soinfo = dlopen(soPath, RTLD_NOW);
    void* soinfo = dlopen("libkhsig.so", RTLD_NOW);
    if (!soinfo) {
        LOGE("Failed to load SO file: %s", dlerror());
    }
    env->ReleaseStringUTFChars(c, soPath); // Release the Java string memory
    if (!soinfo) {
        LOGE("Failed to load SO file: %s", dlerror());
        return nullptr;
    }

    // Resolve the `getSalt` function from the SO file
    using GetSaltFunc = jstring (*)(JNIEnv*, jclass);
//    void (*GetSaltFunc)(JNIEnv*, jclass) = nullptr;
    GetSaltFunc getSalt = reinterpret_cast<GetSaltFunc>(dlsym(soinfo, "_Z7getSaltP7_JNIEnvP7_jclass"));
    if (!getSalt) {
        LOGE("Failed to find function 'getSalt': %s", dlerror());
        dlclose(soinfo); // Unload the library before returning
        return nullptr;
    }

    // Call the `getSalt` function and retrieve its result
    jstring result = getSalt(env, jclazz);

    // Unload the shared library
    dlclose(soinfo);

    return result;
}




// JNI_OnLoad 函数确保 RegisterNatives 在库加载时被调用
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = nullptr;
    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK) {
        return -1; // 获取环境失败
    }
    jclass clazz = env->FindClass("com/koohai/encutils/SecurityMd5Utils");
    if (clazz == nullptr) {
        return JNI_FALSE; // 类未找到
    }
    // JNINativeMethod 数组定义
    static JNINativeMethod gMethods[] = {
            {"stringFromJNI", "(Ljava/lang/String;)Ljava/lang/String;", (void*)encodeFromC},
            {"md5FromJni", "()Ljava/lang/String;", (void*)md5Enc},
            {"env_check", "()Ljava/lang/String;", (void*)envCheck},
            {"callJniFuncJava", "()Ljava/lang/String;", (void*)callFuncJava},
    };


    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods)/sizeof(JNINativeMethod)) < 0) {
        return JNI_FALSE; // 注册失败
    }

    return JNI_VERSION_1_6;
}


}
