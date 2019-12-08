#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_androdevlinux_percy_aegean_utils_NativeUtils_getXmrToBaseUrl(JNIEnv *env, jclass type) {
    char * returnValue = "https://xmr.to/";
    return (*env)->NewStringUTF(env, returnValue);
}

JNIEXPORT jstring JNICALL
Java_com_androdevlinux_percy_aegean_utils_NativeUtils_getChangellyApiKey(JNIEnv *env, jclass type) {

    char * apiKey = "66453fbad84a44ffbd565481addb3d16";
    return (*env)->NewStringUTF(env, apiKey);
}

JNIEXPORT jstring JNICALL
Java_com_androdevlinux_percy_aegean_utils_NativeUtils_getChangellySecretKey(JNIEnv *env, jclass type) {

    char * secretKey = "f24e94a1390bbd90d0159818648c11c15276db5c276230bcb1d271110b77f44c";
    return (*env)->NewStringUTF(env, secretKey);
}

JNIEXPORT jstring JNICALL
Java_com_androdevlinux_percy_aegean_utils_NativeUtils_getChangellyBaseUrl(JNIEnv *env, jclass type) {

    char * baseUrl = "https://api.changelly.com";
    return (*env)->NewStringUTF(env, baseUrl);
}