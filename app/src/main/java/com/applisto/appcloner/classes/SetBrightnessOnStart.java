package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/* loaded from: classes2.dex */
public class SetBrightnessOnStart extends OnAppExitListener {
    private static final int MAX_BRIGHTNESS = 255;
    private static final String SCREEN_AUTO_BRIGHTNESS_ADJ = "screen_auto_brightness_adj";
    private static final String TAG = SetBrightnessOnStart.class.getSimpleName();
    private boolean mBrightnessSet;
    private boolean mOldAutoBrightness;
    private Integer mOldBrightness;
    private boolean mRestoreBrightnessOnExit;
    private Float mSetBrightnessOnStart;

    public SetBrightnessOnStart(CloneSettings cloneSettings) {
        this.mSetBrightnessOnStart = cloneSettings.getFloat("setBrightnessOnStart", null);
        this.mRestoreBrightnessOnExit = cloneSettings.getBoolean("restoreBrightnessOnExit", false).booleanValue();
        String str = TAG;
        Log.i(str, "SetBrightnessOnStart; mSetBrightnessOnStart: " + this.mSetBrightnessOnStart);
        String str2 = TAG;
        Log.i(str2, "SetBrightnessOnStart; mRestoreBrightnessOnExit: " + this.mRestoreBrightnessOnExit);
    }

    public void init(Context context) {
        if (this.mSetBrightnessOnStart != null) {
            onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        if (!this.mBrightnessSet) {
            this.mOldAutoBrightness = isAutoBrightness(activity);
            this.mOldBrightness = getBrightness(activity, this.mOldAutoBrightness);
            String str = TAG;
            Log.i(str, "onActivityCreated; mOldAutoBrightness: " + this.mOldAutoBrightness + ", mOldBrightness: " + this.mOldBrightness);
            setBrightness(activity, Math.round(this.mSetBrightnessOnStart.floatValue() * 255.0f), false);
            this.mBrightnessSet = true;
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        Integer num;
        String str = TAG;
        Log.i(str, "onAppExit; mRestoreBrightnessOnExit: " + this.mRestoreBrightnessOnExit + ", mOldAutoBrightness: " + this.mOldAutoBrightness);
        if (this.mRestoreBrightnessOnExit && (num = this.mOldBrightness) != null) {
            setBrightness(context, num.intValue(), this.mOldAutoBrightness);
            setAutoBrightness(context, this.mOldAutoBrightness);
            this.mBrightnessSet = false;
        }
    }

    private boolean isAutoBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness_mode", 0) == 1;
        } catch (Throwable t) {
            Log.w(TAG, t);
            return false;
        }
    }

    private void setAutoBrightness(Context context, boolean autoBrightness) {
        try {
            Settings.System.putInt(context.getContentResolver(), "screen_brightness_mode", autoBrightness ? 1 : 0);
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    private Integer getBrightness(Context context, boolean autoBrightness) {
        int oldBrightness;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (autoBrightness) {
                oldBrightness = Math.round((Settings.System.getFloat(contentResolver, SCREEN_AUTO_BRIGHTNESS_ADJ) * 128.0f) + 128.0f);
            } else {
                oldBrightness = Settings.System.getInt(contentResolver, "screen_brightness");
            }
            return Integer.valueOf(oldBrightness);
        } catch (Throwable t) {
            Log.w(TAG, t);
            return null;
        }
    }

    private void setBrightness(Context context, int newBrightness, boolean autoBrightness) {
        String str = TAG;
        Log.i(str, "setBrightness; newBrightness: " + newBrightness + ", autoBrightness: " + autoBrightness);
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (autoBrightness) {
                try {
                    Settings.System.putFloat(contentResolver, SCREEN_AUTO_BRIGHTNESS_ADJ, (newBrightness - 128) / 128.0f);
                } catch (Exception e) {
                    Log.w(TAG, e);
                    Settings.System.putInt(contentResolver, "screen_brightness_mode", 0);
                    Settings.System.putInt(contentResolver, "screen_brightness", newBrightness);
                }
            } else {
                Settings.System.putInt(contentResolver, "screen_brightness_mode", 0);
                Settings.System.putInt(contentResolver, "screen_brightness", newBrightness);
            }
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }
}
