package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/* loaded from: classes2.dex */
public class DisableCameras extends OnAppExitListener {
    private static final String ACTION_ENABLE_CAMERAS = "com.applisto.appcloner.action.ENABLE_CAMERAS";
    private static final int NOTIFICATION_ID = 556711456;
    private static final String TAG = DisableCameras.class.getSimpleName();

    public void install() {
        Log.i(TAG, "install; ");
        try {
            onCreate();
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        maybeEnableDeviceAdmin(activity);
        disableCameras(activity);
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        enableCameras(context);
    }

    public void maybeEnableDeviceAdmin(Activity activity) {
        Log.i(TAG, "maybeEnableDeviceAdmin; ");
        try {
            DevicePolicyManager dpm = (DevicePolicyManager) activity.getSystemService("device_policy");
            if (dpm != null) {
                ComponentName deviceAdmin = new ComponentName(activity, MyDeviceAdminReceiver.class);
                if (!dpm.isAdminActive(deviceAdmin)) {
                    Intent i = new Intent("android.app.action.ADD_DEVICE_ADMIN");
                    i.putExtra("android.app.extra.DEVICE_ADMIN", deviceAdmin);
                    activity.startActivity(i);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void disableCameras(Context context) {
        Log.i(TAG, "disableCameras; ");
        try {
            DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService("device_policy");
            if (dpm != null) {
                ComponentName deviceAdmin = new ComponentName(context, MyDeviceAdminReceiver.class);
                if (dpm.isAdminActive(deviceAdmin) && !dpm.getCameraDisabled(deviceAdmin)) {
                    dpm.setCameraDisabled(deviceAdmin, true);
                    CharSequence message = Utils.getAppClonerResourceText(context, "disable_cameras_cameras_disabled_message", "Cameras disabled.");
                    Toast.makeText(context, message, 1).show();
                    showNotification(context);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void enableCameras(Context context) {
        Log.i(TAG, "enableCameras; ");
        try {
            DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService("device_policy");
            if (dpm != null) {
                ComponentName deviceAdmin = new ComponentName(context, MyDeviceAdminReceiver.class);
                if (dpm.getCameraDisabled(deviceAdmin)) {
                    dpm.setCameraDisabled(deviceAdmin, false);
                    CharSequence message = Utils.getAppClonerResourceText(context, "disable_cameras_cameras_enabled_message", "Cameras enabled.");
                    Toast.makeText(context, message, 1).show();
                    hideNotification(context);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* loaded from: classes2.dex */
    public static class MyDeviceAdminReceiver extends DeviceAdminReceiver {
        @Override // android.app.admin.DeviceAdminReceiver
        public void onEnabled(Context context, Intent intent) {
            Log.i(DisableCameras.TAG, "onEnabled; ");
            DisableCameras.disableCameras(context);
        }
    }

    private static void showNotification(Context context) {
        Log.i(TAG, "showNotification; ");
        hideNotification(context);
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                CharSequence title = Utils.getAppClonerResourceText(context, "disable_cameras_cameras_disabled_message", "Cameras disabled.");
                CharSequence message = Utils.getAppClonerResourceText(context, "disable_cameras_touch_to_enable_message", "Touch to enable cameras.");
                Intent i = new Intent(ACTION_ENABLE_CAMERAS);
                i.setPackage(context.getPackageName());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i, 0);
                Notification.Builder builder = new Notification.Builder(context).setContentTitle(title).setContentText(message).setContentIntent(pendingIntent).setWhen(0L);
                Utils.setSmallNotificationIcon(builder);
                notificationManager.notify(NOTIFICATION_ID, builder.getNotification());
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private static void hideNotification(Context context) {
        Log.i(TAG, "hideNotification; ");
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATION_ID);
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* loaded from: classes2.dex */
    public static class EnableCamerasReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = DisableCameras.TAG;
            Log.i(str, "onReceive; intent: " + intent);
            DisableCameras.enableCameras(context);
        }
    }
}
