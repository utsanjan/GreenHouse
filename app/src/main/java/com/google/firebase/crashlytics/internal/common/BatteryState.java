package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes.dex */
class BatteryState {
    static final int VELOCITY_CHARGING = 2;
    static final int VELOCITY_FULL = 3;
    static final int VELOCITY_UNPLUGGED = 1;
    private final Float level;
    private final boolean powerConnected;

    private BatteryState(Float level, boolean powerConnected) {
        this.powerConnected = powerConnected;
        this.level = level;
    }

    boolean isPowerConnected() {
        return this.powerConnected;
    }

    public Float getBatteryLevel() {
        return this.level;
    }

    public int getBatteryVelocity() {
        Float f;
        if (!this.powerConnected || (f = this.level) == null) {
            return 1;
        }
        if (f.floatValue() < 0.99d) {
            return 2;
        }
        return 3;
    }

    public static BatteryState get(Context context) {
        boolean powerConnected = false;
        Float level = null;
        IntentFilter ifilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        Intent batteryStatusIntent = context.registerReceiver(null, ifilter);
        if (batteryStatusIntent != null) {
            powerConnected = isPowerConnected(batteryStatusIntent);
            level = getLevel(batteryStatusIntent);
        }
        return new BatteryState(level, powerConnected);
    }

    private static boolean isPowerConnected(Intent batteryStatusIntent) {
        int status = batteryStatusIntent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        if (status == -1) {
            return false;
        }
        return status == 2 || status == 5;
    }

    private static Float getLevel(Intent batteryStatusIntent) {
        int level = batteryStatusIntent.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
        int scale = batteryStatusIntent.getIntExtra("scale", -1);
        if (level == -1 || scale == -1) {
            return null;
        }
        return Float.valueOf(level / scale);
    }
}
