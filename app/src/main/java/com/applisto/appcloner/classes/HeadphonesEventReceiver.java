package com.applisto.appcloner.classes;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/* loaded from: classes2.dex */
public class HeadphonesEventReceiver extends StartExitAppEventReceiver {
    private static final String TAG = HeadphonesEventReceiver.class.getSimpleName();

    public static void init(Context context) {
        Log.i(TAG, "init; ");
        StartExitAppEventReceiver.init();
        try {
            context.registerReceiver(new HeadphonesEventReceiver(), new IntentFilter("android.intent.action.HEADSET_PLUG"));
            context.startService(new Intent(context, PersistentAppService.class));
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        Log.i(str, "onReceive; intent: " + intent);
        try {
            if (!isInitialStickyBroadcast()) {
                String action = intent.getAction();
                if ("android.intent.action.HEADSET_PLUG".equals(action)) {
                    int state = intent.getIntExtra("state", -1);
                    boolean plugged = state != 0;
                    String str2 = TAG;
                    Log.i(str2, "onReceive; state: " + state + ", plugged: " + plugged);
                    if (plugged) {
                        CloneSettings cloneSettings = CloneSettings.getInstance(context);
                        String headphonesPluggedEventAction = cloneSettings.getString("headphonesPluggedEventAction", "NONE");
                        handleEventAction(context, headphonesPluggedEventAction);
                    } else {
                        CloneSettings cloneSettings2 = CloneSettings.getInstance(context);
                        String headphonesUnpluggedEventAction = cloneSettings2.getString("headphonesUnpluggedEventAction", "NONE");
                        handleEventAction(context, headphonesUnpluggedEventAction);
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
}
