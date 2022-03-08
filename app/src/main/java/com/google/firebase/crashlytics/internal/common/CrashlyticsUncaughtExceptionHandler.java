package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final SettingsDataProvider settingsDataProvider;

    /* loaded from: classes.dex */
    interface CrashListener {
        void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener, SettingsDataProvider settingsProvider, Thread.UncaughtExceptionHandler defaultHandler) {
        this.crashListener = crashListener;
        this.settingsDataProvider = settingsProvider;
        this.defaultHandler = defaultHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.concurrent.atomic.AtomicBoolean] */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable ex) {
        this.isHandlingException.set(true);
        String str = "Crashlytics completed exception processing. Invoking default exception handler.";
        try {
            try {
                if (thread == null) {
                    Logger.getLogger().e("Could not handle uncaught exception; null thread");
                } else if (ex == null) {
                    Logger.getLogger().e("Could not handle uncaught exception; null throwable");
                } else {
                    this.crashListener.onUncaughtException(this.settingsDataProvider, thread, ex);
                }
            } catch (Exception e) {
                Logger.getLogger().e("An error occurred in the uncaught exception handler", e);
            }
            Logger.getLogger().d(str);
            this.defaultHandler.uncaughtException(thread, ex);
            str = this.isHandlingException;
            str.set(false);
        } catch (Throwable th) {
            Logger.getLogger().d(str);
            this.defaultHandler.uncaughtException(thread, ex);
            this.isHandlingException.set(false);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }
}
