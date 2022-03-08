package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class StartExitAppEventReceiver extends BroadcastReceiver {
    private static final String TAG = StartExitAppEventReceiver.class.getSimpleName();
    private static final Set<Activity> sActivities = new HashSet();
    private static boolean sInited;

    public static void init() {
        try {
            if (!sInited) {
                Log.i(TAG, "init; ");
                new AbstractActivityContentProvider() { // from class: com.applisto.appcloner.classes.StartExitAppEventReceiver.1
                    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
                    protected void onActivityCreated(Activity activity) {
                        StartExitAppEventReceiver.sActivities.add(activity);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
                    public void onActivityDestroyed(Activity activity) {
                        StartExitAppEventReceiver.sActivities.remove(activity);
                    }
                }.onCreate();
                sInited = true;
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleEventAction(Context context, String eventAction) {
        String str = TAG;
        Log.i(str, "handleAction; eventAction: " + eventAction);
        if ("START_APP".equals(eventAction)) {
            startApp(context);
        } else if ("EXIT_APP".equals(eventAction)) {
            exitApp();
        }
    }

    private void startApp(Context context) {
        Log.i(TAG, "startApp; ");
        try {
            Intent i = Utils.getLaunchIntent(context, context.getPackageName());
            if (i != null) {
                i.setFlags(335544320);
                context.startActivity(i);
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private void exitApp() {
        Log.i(TAG, "exitApp; ");
        for (Activity activity : sActivities) {
            try {
                activity.finish();
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }
}
