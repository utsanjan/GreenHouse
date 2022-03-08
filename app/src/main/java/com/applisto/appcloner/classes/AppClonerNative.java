package com.applisto.appcloner.classes;

import android.util.Log;
import java.io.FilenameFilter;

/* loaded from: classes2.dex */
public class AppClonerNative {
    private static final String TAG = AppClonerNative.class.getSimpleName();

    public static native void androidLogBufWrite(int i, int i2, String str, String str2);

    public static native boolean disableLogcatLogging();

    public static native void registerFilenameFilter(FilenameFilter filenameFilter);

    static {
        try {
            System.loadLibrary("appcloner");
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }
}
