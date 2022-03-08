package com.google.android.datatransport.runtime.logging;

import android.util.Log;

/* loaded from: classes.dex */
public final class Logging {
    private Logging() {
    }

    private static String getTag(String tag) {
        return "TransportRuntime." + tag;
    }

    public static void d(String tag, String message) {
        Log.d(getTag(tag), message);
    }

    public static void d(String tag, String message, Object arg1) {
        Log.d(getTag(tag), String.format(message, arg1));
    }

    public static void d(String tag, String message, Object arg1, Object arg2) {
        Log.d(getTag(tag), String.format(message, arg1, arg2));
    }

    public static void d(String tag, String message, Object... args) {
        Log.d(getTag(tag), String.format(message, args));
    }

    public static void i(String tag, String message) {
        Log.i(getTag(tag), message);
    }

    public static void e(String tag, String message, Throwable e) {
        Log.e(getTag(tag), message, e);
    }

    public static void w(String tag, String message, Object arg1) {
        Log.w(getTag(tag), String.format(message, arg1));
    }
}
