package com.applisto.appcloner.classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes2.dex */
public class PenEventReceiver extends StartExitAppEventReceiver {
    private static final String TAG = PenEventReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        Log.i(str, "onReceive; intent: " + intent);
        try {
            String action = intent.getAction();
            if ("com.samsung.pen.INSERT".equals(action)) {
                boolean penInsert = intent.getBooleanExtra("penInsert", false);
                String str2 = TAG;
                Log.i(str2, "onReceive; penInsert: " + penInsert);
                if (penInsert) {
                    CloneSettings cloneSettings = CloneSettings.getInstance(context);
                    String penInsertedEventAction = cloneSettings.getString("penInsertedEventAction", "NONE");
                    handleEventAction(context, penInsertedEventAction);
                } else {
                    CloneSettings cloneSettings2 = CloneSettings.getInstance(context);
                    String penDetachedEventAction = cloneSettings2.getString("penDetachedEventAction", "NONE");
                    handleEventAction(context, penDetachedEventAction);
                }
            } else if ("com.sec.android.intent.action.AIR_BUTTON".equals(action) && intent.getBooleanExtra("isShow", false)) {
                CloneSettings cloneSettings3 = CloneSettings.getInstance(context);
                String penButtonPressedEventAction = cloneSettings3.getString("penButtonPressedEventAction", "NONE");
                handleEventAction(context, penButtonPressedEventAction);
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
}
