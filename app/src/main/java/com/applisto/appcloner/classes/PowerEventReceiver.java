package com.applisto.appcloner.classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes2.dex */
public class PowerEventReceiver extends StartExitAppEventReceiver {
    private static final String TAG = PowerEventReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        Log.i(str, "onReceive; intent: " + intent);
        try {
            CloneSettings cloneSettings = CloneSettings.getInstance(context);
            boolean isDocked = false;
            boolean powerEventsDockUndockEvents = cloneSettings.getBoolean("powerEventsDockUndockEvents", false).booleanValue();
            String str2 = TAG;
            Log.i(str2, "onReceive; powerEventsDockUndockEvents: " + powerEventsDockUndockEvents);
            String action = intent.getAction();
            if ("android.intent.action.ACTION_POWER_CONNECTED".equals(action) && !powerEventsDockUndockEvents) {
                Log.i(TAG, "onReceive; ACTION_POWER_CONNECTED");
                String powerConnectedEventAction = cloneSettings.getString("powerConnectedEventAction", "NONE");
                handleEventAction(context, powerConnectedEventAction);
            } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action) && !powerEventsDockUndockEvents) {
                Log.i(TAG, "onReceive; ACTION_POWER_DISCONNECTED");
                String powerDisconnectedEventAction = cloneSettings.getString("powerDisconnectedEventAction", "NONE");
                handleEventAction(context, powerDisconnectedEventAction);
            } else if ("android.intent.action.DOCK_EVENT".equals(action) && powerEventsDockUndockEvents) {
                Log.i(TAG, "onReceive; ACTION_DOCK_EVENT");
                int dockState = intent.getIntExtra("android.intent.extra.DOCK_STATE", -1);
                if (dockState != 0) {
                    isDocked = true;
                }
                String str3 = TAG;
                Log.i(str3, "onReceive; isDocked: " + isDocked);
                if (isDocked) {
                    String powerConnectedEventAction2 = cloneSettings.getString("powerConnectedEventAction", "NONE");
                    handleEventAction(context, powerConnectedEventAction2);
                } else {
                    String powerDisconnectedEventAction2 = cloneSettings.getString("powerDisconnectedEventAction", "NONE");
                    handleEventAction(context, powerDisconnectedEventAction2);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
}
