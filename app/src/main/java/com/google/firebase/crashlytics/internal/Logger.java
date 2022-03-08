package com.google.firebase.crashlytics.internal;

import android.util.Log;

/* loaded from: classes.dex */
public class Logger {
    private int logLevel = 4;
    private final String tag;
    public static final String TAG = "FirebaseCrashlytics";
    static final Logger DEFAULT_LOGGER = new Logger(TAG);

    public Logger(String tag) {
        this.tag = tag;
    }

    public static Logger getLogger() {
        return DEFAULT_LOGGER;
    }

    private boolean canLog(int level) {
        return this.logLevel <= level || Log.isLoggable(this.tag, level);
    }

    public void d(String text, Throwable throwable) {
        if (canLog(3)) {
            Log.d(this.tag, text, throwable);
        }
    }

    public void v(String text, Throwable throwable) {
        if (canLog(2)) {
            Log.v(this.tag, text, throwable);
        }
    }

    public void i(String text, Throwable throwable) {
        if (canLog(4)) {
            Log.i(this.tag, text, throwable);
        }
    }

    public void w(String text, Throwable throwable) {
        if (canLog(5)) {
            Log.w(this.tag, text, throwable);
        }
    }

    public void e(String text, Throwable throwable) {
        if (canLog(6)) {
            Log.e(this.tag, text, throwable);
        }
    }

    public void d(String text) {
        d(text, null);
    }

    public void v(String text) {
        v(text, null);
    }

    public void i(String text) {
        i(text, null);
    }

    public void w(String text) {
        w(text, null);
    }

    public void e(String text) {
        e(text, null);
    }

    public void log(int priority, String msg) {
        log(priority, msg, false);
    }

    public void log(int priority, String msg, boolean forceLog) {
        if (forceLog || canLog(priority)) {
            Log.println(priority, this.tag, msg);
        }
    }
}
