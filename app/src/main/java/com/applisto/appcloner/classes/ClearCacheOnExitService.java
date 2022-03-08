package com.applisto.appcloner.classes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/* loaded from: classes2.dex */
public class ClearCacheOnExitService extends Service {
    private static final String TAG = ClearCacheOnExitService.class.getSimpleName();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand; ");
        return 2;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        Log.i(TAG, "onTaskRemoved; ");
        ClearCacheOnExitProvider.clearCache(getApplicationContext());
    }
}
