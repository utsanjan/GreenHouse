package com.applisto.appcloner.classes;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.lang.Thread;

/* loaded from: classes2.dex */
public class CrashHandler {
    public static final int CRASH_HANDLER_NOTIFICATION_ID = 1621363246;
    private static final String TAG = CrashHandler.class.getSimpleName();
    private boolean mAppBundle;
    private Context mContext;
    private final boolean mIgnoreCrashes;
    private final boolean mIgnoreCrashesShowCrashMessages;
    private final Handler mHandler = new Handler();
    private Thread.UncaughtExceptionHandler mExceptionHandler = new Thread.UncaughtExceptionHandler() { // from class: com.applisto.appcloner.classes.CrashHandler.1
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable t) {
            StringBuilder sb;
            try {
                Log.w(CrashHandler.TAG, t);
                if (!CrashHandler.this.mIgnoreCrashes || CrashHandler.this.mIgnoreCrashesShowCrashMessages) {
                    String appName = Utils.getAppName(CrashHandler.this.mContext);
                    if (CrashHandler.this.mIgnoreCrashes) {
                        sb = new StringBuilder();
                        sb.append("Ignored ");
                        sb.append(appName);
                        sb.append(" crash");
                    } else {
                        sb = new StringBuilder();
                        sb.append(appName);
                        sb.append(" crashed");
                    }
                    String title = sb.toString();
                    String message = Log.getStackTraceString(t);
                    if (Build.VERSION.SDK_INT >= 28 && (message.contains("You need to use a Theme.AppCompat theme") || message.contains("requires your app theme to be Theme.AppCompat"))) {
                        message = "Please try enabling the 'Adaptive icons support' option.";
                    }
                    if (!message.contains("android.content.res.Resources$NotFoundException")) {
                        if (!message.contains("dlopen failed") && !message.contains("java.lang.UnsatisfiedLinkError")) {
                            if (message.contains("java.lang.NullPointerException: Attempt to read from field 'java.lang.String android.content.pm.PackageItemInfo.packageName' on a null object reference")) {
                                if (!CrashHandler.this.mIgnoreCrashes) {
                                    message = "Please try enabling the 'Ignore crashes' option.";
                                } else {
                                    return;
                                }
                            } else if (message.contains("java.lang.ClassNotFoundException: Didn't find class")) {
                                message = "Please try enabling the 'Increase compatibility' option or use the manifest cloning mode.";
                            }
                        }
                        message = "Please try enabling the 'Skip native libraries' under 'Cloning options'.";
                    } else if (CrashHandler.this.mAppBundle) {
                        message = "Please clone the standalone version of this app, not the Play Store version.";
                    } else if (Build.VERSION.SDK_INT >= 25) {
                        message = "Please try enabling the 'Adaptive icons support' option.";
                    }
                    NotificationManager notificationManager = (NotificationManager) CrashHandler.this.mContext.getSystemService("notification");
                    Notification.Builder builder = new Notification.Builder(CrashHandler.this.mContext).setContentTitle(title).setContentText(message).setWhen(System.currentTimeMillis());
                    Utils.setSmallNotificationIcon(builder, true);
                    if (Build.VERSION.SDK_INT >= 16) {
                        builder.setStyle(new Notification.BigTextStyle().bigText(message));
                    }
                    notificationManager.notify(CrashHandler.CRASH_HANDLER_NOTIFICATION_ID, builder.getNotification());
                }
            } catch (Exception e) {
                Log.w(CrashHandler.TAG, e);
            }
            if (!CrashHandler.this.mIgnoreCrashes) {
                System.exit(0);
            }
        }
    };

    public CrashHandler(CloneSettings cloneSettings) {
        this.mIgnoreCrashes = cloneSettings.getBoolean("ignoreCrashes", false).booleanValue();
        this.mIgnoreCrashesShowCrashMessages = cloneSettings.getBoolean("ignoreCrashesShowCrashMessages", true).booleanValue();
        String str = TAG;
        Log.i(str, "CrashHandler; mIgnoreCrashes: " + this.mIgnoreCrashes + ", mIgnoreCrashesShowCrashMessages: " + this.mIgnoreCrashesShowCrashMessages);
    }

    public void install(Context context) {
        this.mContext = context;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle metaData = applicationInfo.metaData;
            this.mAppBundle = metaData.getBoolean("com.applisto.appcloner.appBundle");
            String str = TAG;
            Log.i(str, "install; mAppBundle: " + this.mAppBundle);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        installHandler();
        this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.-$$Lambda$CrashHandler$g3sMJRWzTR_eVV6zwuOHu74AGs0
            @Override // java.lang.Runnable
            public final void run() {
                CrashHandler.this.installHandler();
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installHandler() {
        Log.i(TAG, "installHandler; ");
        try {
            Thread.setDefaultUncaughtExceptionHandler(this.mExceptionHandler);
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }
}
