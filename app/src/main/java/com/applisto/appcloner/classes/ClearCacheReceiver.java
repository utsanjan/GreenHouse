package com.applisto.appcloner.classes;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes2.dex */
public class ClearCacheReceiver extends BroadcastReceiver {
    private static final String TAG = ClearCacheReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        Log.i(str, "onReceive; intent: " + intent);
        ClearCacheOnExitProvider.clearCache(context);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            Notification.Builder builder = new Notification.Builder(context).setContentTitle(Utils.getAppName(context)).setContentText(Utils.getAppClonerResourceText(context, "cache_cleared_message", "Cache was cleared.")).setWhen(System.currentTimeMillis());
            Utils.setSmallNotificationIcon(builder);
            notificationManager.notify(474329107, builder.getNotification());
        }
    }
}
