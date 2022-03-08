package com.applisto.appcloner.classes;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;

/* loaded from: classes2.dex */
public class BluetoothControls extends OnAppExitListener {
    private static final String TAG = BluetoothControls.class.getSimpleName();
    private Boolean mBluetoothState;
    private boolean mBluetoothStateSet;
    private Boolean mOldBluetoothState;
    private boolean mRestoreBluetoothStateOnExit;

    public BluetoothControls(CloneSettings cloneSettings) {
        this.mBluetoothState = cloneSettings.getBoolean("bluetoothState", null);
        this.mRestoreBluetoothStateOnExit = cloneSettings.getBoolean("restoreBluetoothStateOnExit", false).booleanValue();
        String str = TAG;
        Log.i(str, "BluetoothControls; mBluetoothState: " + this.mBluetoothState);
        String str2 = TAG;
        Log.i(str2, "BluetoothControls; mRestoreBluetoothStateOnExit: " + this.mRestoreBluetoothStateOnExit);
    }

    public void init(Context context) {
        if (this.mBluetoothState != null) {
            onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        if (!this.mBluetoothStateSet) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                this.mOldBluetoothState = Boolean.valueOf(defaultAdapter.isEnabled());
                if (this.mBluetoothState.booleanValue()) {
                    defaultAdapter.enable();
                    Log.i(TAG, "onActivityCreated; enabled bluetooth");
                } else {
                    defaultAdapter.disable();
                    Log.i(TAG, "onActivityCreated; disabled bluetooth");
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            String str = TAG;
            Log.i(str, "onActivityCreated; mOldBluetoothState: " + this.mOldBluetoothState);
            this.mBluetoothStateSet = true;
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        String str = TAG;
        Log.i(str, "onAppExit; mRestoreBluetoothStateOnExit: " + this.mRestoreBluetoothStateOnExit + ", mOldBluetoothState: " + this.mOldBluetoothState);
        if (this.mRestoreBluetoothStateOnExit && this.mOldBluetoothState != null) {
            try {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (this.mOldBluetoothState.booleanValue()) {
                    defaultAdapter.enable();
                    Log.i(TAG, "onAppExit; enabled bluetooth");
                } else {
                    defaultAdapter.disable();
                    Log.i(TAG, "onAppExit; disabled bluetooth");
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            this.mBluetoothStateSet = false;
        }
    }
}
