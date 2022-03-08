package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/* loaded from: classes2.dex */
public class InterruptionFilterControls extends OnAppExitListener {
    private static final String TAG = InterruptionFilterControls.class.getSimpleName();
    private Integer mInterruptionFilter;
    private boolean mInterruptionFilterSet;
    private int mOldInterruptionFilter;
    private boolean mRestoreInterruptionFilterOnExit;

    public InterruptionFilterControls(CloneSettings cloneSettings) {
        this.mInterruptionFilter = cloneSettings.getInteger("interruptionFilter", null);
        this.mRestoreInterruptionFilterOnExit = cloneSettings.getBoolean("restoreInterruptionFilterOnExit", false).booleanValue();
        String str = TAG;
        Log.i(str, "WifiControls; mInterruptionFilter: " + this.mInterruptionFilter);
        String str2 = TAG;
        Log.i(str2, "WifiControls; mRestoreInterruptionFilterOnExit: " + this.mRestoreInterruptionFilterOnExit);
    }

    public void init(Context context) {
        if (this.mInterruptionFilter != null) {
            onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        if (Build.VERSION.SDK_INT >= 23) {
            NotificationManager notificationManager = (NotificationManager) activity.getSystemService("notification");
            boolean accessGranted = notificationManager.isNotificationPolicyAccessGranted();
            String str = TAG;
            Log.i(str, "onActivityCreated; accessGranted: " + accessGranted);
            if (!accessGranted) {
                Intent intent = new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS");
                activity.startActivity(intent);
                Toast.makeText(activity, "Please enable Do Not Disturb access for " + Utils.getAppName(activity) + ".", 1).show();
            } else if (!this.mInterruptionFilterSet) {
                this.mOldInterruptionFilter = notificationManager.getCurrentInterruptionFilter();
                notificationManager.setInterruptionFilter(this.mInterruptionFilter.intValue());
                String str2 = TAG;
                Log.i(str2, "onActivityCreated: interruption filter set: " + this.mInterruptionFilter);
                String str3 = TAG;
                Log.i(str3, "onActivityCreated; mOldInterruptionFilter: " + this.mOldInterruptionFilter);
                this.mInterruptionFilterSet = true;
            }
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            String str = TAG;
            Log.i(str, "onAppExit; mRestoreInterruptionFilterOnExit: " + this.mRestoreInterruptionFilterOnExit + ", mOldInterruptionFilter: " + this.mOldInterruptionFilter);
            if (this.mRestoreInterruptionFilterOnExit) {
                try {
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    notificationManager.setInterruptionFilter(this.mOldInterruptionFilter);
                    String str2 = TAG;
                    Log.i(str2, "onAppExit: interruption filter set: " + this.mOldInterruptionFilter);
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
                this.mInterruptionFilterSet = false;
            }
        }
    }
}
