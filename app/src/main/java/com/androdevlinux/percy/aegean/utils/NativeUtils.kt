package com.androdevlinux.percy.aegean.utils

object NativeUtils {
    val xmrToBaseUrl: String
        @JvmStatic
        external get
    val changellySecretKey: String
        @JvmStatic
        external get
    val changellyBaseUrl: String
        @JvmStatic
        external get
    val changellyApiKey: String
        @JvmStatic
        external get
    init {
        System.loadLibrary("aegean-jni")
    }
}
