package com.applisto.appcloner.classes;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import java.io.File;

/* loaded from: classes2.dex */
public class ClearCacheOnExitProvider extends OnAppExitListener {
    private static final String TAG = ClearCacheOnExitProvider.class.getSimpleName();

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected boolean onInit(Application application) {
        String str = TAG;
        Log.i(str, "onInit; application: " + application);
        try {
            application.startService(new Intent(application, ClearCacheOnExitService.class));
            return true;
        } catch (Exception e) {
            Log.w(TAG, e);
            return true;
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        clearCache(context);
    }

    public static synchronized void clearCache(Context context) {
        File[] externalCacheDirs;
        synchronized (ClearCacheOnExitProvider.class) {
            try {
                new WebView(context).clearCache(true);
                context.deleteDatabase("webview.db");
                context.deleteDatabase("webviewCache.db");
                File cacheDir = context.getCacheDir();
                Log.i(TAG, "clearCache; cacheDir: " + cacheDir);
                Utils.deleteDirectory(cacheDir);
                if (Build.VERSION.SDK_INT >= 19 && (externalCacheDirs = context.getExternalCacheDirs()) != null) {
                    for (File externalCacheDir : externalCacheDirs) {
                        Log.i(TAG, "clearCache; externalCacheDir: " + externalCacheDir);
                        Utils.deleteDirectory(externalCacheDir);
                    }
                }
            }
        }
    }
}
