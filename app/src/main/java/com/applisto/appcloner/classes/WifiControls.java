package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

/* loaded from: classes2.dex */
public class WifiControls extends OnAppExitListener {
    private static final String TAG = WifiControls.class.getSimpleName();
    private Boolean mOldWifiState;
    private boolean mRestoreWifiStateOnExit;
    private Boolean mWifiState;
    private boolean mWifiStateSet;

    public WifiControls(CloneSettings cloneSettings) {
        this.mWifiState = cloneSettings.getBoolean("wifiState", null);
        this.mRestoreWifiStateOnExit = cloneSettings.getBoolean("restoreWifiStateOnExit", false).booleanValue();
        String str = TAG;
        Log.i(str, "WifiControls; mWifiState: " + this.mWifiState);
        String str2 = TAG;
        Log.i(str2, "WifiControls; mRestoreWifiStateOnExit: " + this.mRestoreWifiStateOnExit);
    }

    public void init(Context context) {
        if (this.mWifiState != null) {
            onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        if (!this.mWifiStateSet) {
            try {
                WifiManager wifiManager = (WifiManager) activity.getSystemService("wifi");
                this.mOldWifiState = Boolean.valueOf(wifiManager.isWifiEnabled());
                if (this.mWifiState.booleanValue()) {
                    wifiManager.setWifiEnabled(true);
                    Log.i(TAG, "onActivityCreated; enabled Wi-Fi");
                } else {
                    wifiManager.setWifiEnabled(false);
                    Log.i(TAG, "onActivityCreated; disabled Wi-Fi");
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            String str = TAG;
            Log.i(str, "onActivityCreated; mOldWifiState: " + this.mOldWifiState);
            this.mWifiStateSet = true;
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        String str = TAG;
        Log.i(str, "onAppExit; mRestoreWifiStateOnExit: " + this.mRestoreWifiStateOnExit + ", mOldWifiState: " + this.mOldWifiState);
        if (this.mRestoreWifiStateOnExit && this.mOldWifiState != null) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (this.mOldWifiState.booleanValue()) {
                    wifiManager.setWifiEnabled(true);
                    Log.i(TAG, "onAppExit; enabled Wi-Fi");
                } else {
                    wifiManager.setWifiEnabled(false);
                    Log.i(TAG, "onAppExit; disabled Wi-Fi");
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            this.mWifiStateSet = false;
        }
    }
}
