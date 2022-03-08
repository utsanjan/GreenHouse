package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/* loaded from: classes2.dex */
public class AutoRotateControls extends OnAppExitListener {
    private static final String TAG = AutoRotateControls.class.getSimpleName();
    private Boolean mAutoRotate;
    private boolean mAutoRotateSet;
    private int mOldAutoRotate = -1;
    private boolean mRestoreAutoRotateOnExit;

    public AutoRotateControls(CloneSettings cloneSettings) {
        this.mAutoRotate = cloneSettings.getBoolean("autoRotate", null);
        this.mRestoreAutoRotateOnExit = cloneSettings.getBoolean("restoreAutoRotateOnExit", false).booleanValue();
        String str = TAG;
        Log.i(str, "AutoRotateControls; mAutoRotate: " + this.mAutoRotate);
        String str2 = TAG;
        Log.i(str2, "AutoRotateControls; mRestoreAutoRotateOnExit: " + this.mRestoreAutoRotateOnExit);
    }

    public void init(Context context) {
        if (this.mAutoRotate != null) {
            onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        if (!this.mAutoRotateSet) {
            try {
                this.mOldAutoRotate = Settings.System.getInt(activity.getContentResolver(), "accelerometer_rotation", -1);
                Settings.System.putInt(activity.getContentResolver(), "accelerometer_rotation", this.mAutoRotate.booleanValue() ? 1 : 0);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            String str = TAG;
            Log.i(str, "onActivityCreated; mOldAutoRotate: " + this.mOldAutoRotate);
            this.mAutoRotateSet = true;
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        String str = TAG;
        Log.i(str, "onAppExit; mRestoreAutoRotateOnExit: " + this.mRestoreAutoRotateOnExit + ", mOldAutoRotate: " + this.mOldAutoRotate);
        if (this.mRestoreAutoRotateOnExit && this.mOldAutoRotate != -1) {
            try {
                Settings.System.putInt(context.getContentResolver(), "accelerometer_rotation", this.mOldAutoRotate);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            this.mAutoRotateSet = false;
        }
    }
}
