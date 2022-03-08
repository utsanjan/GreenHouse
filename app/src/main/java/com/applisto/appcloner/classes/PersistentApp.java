package com.applisto.appcloner.classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes2.dex */
public class PersistentApp {
    private static final String TAG = PersistentApp.class.getSimpleName();
    private boolean mPersistentApp;

    public PersistentApp(CloneSettings cloneSettings) {
        this.mPersistentApp = cloneSettings.getBoolean("persistentApp", false).booleanValue();
        String str = TAG;
        Log.i(str, "PersistentApp; mPersistentApp: " + this.mPersistentApp);
    }

    public void init(Context context) {
        if (this.mPersistentApp) {
            try {
                context.startService(new Intent(context, PersistentAppService.class));
            } catch (Throwable t) {
                Log.w(TAG, t);
            }
        }
    }
}
