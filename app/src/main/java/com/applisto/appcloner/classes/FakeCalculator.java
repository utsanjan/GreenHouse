package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes2.dex */
public class FakeCalculator extends OnAppExitListener {
    private static final String TAG = FakeCalculator.class.getSimpleName();

    public void install() {
        Log.i(TAG, "install; ");
        onCreate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        if ((activity instanceof CalculatorActivity) || (activity instanceof SplashScreenActivity)) {
            Log.i(TAG, "onActivityCreated; ignoring");
        } else if (!CalculatorActivity.sUnlocked) {
            Intent i = new Intent(activity, CalculatorActivity.class);
            i.addFlags(65536);
            activity.startActivity(i);
            Log.i(TAG, "onActivityCreated; started CalculatorActivity");
        } else {
            Log.i(TAG, "onActivityCreated; already unlocked");
        }
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        CalculatorActivity.sUnlocked = false;
    }
}
