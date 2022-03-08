package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes2.dex */
public class PasswordProvider extends AbstractActivityContentProvider {
    private static final String TAG = PasswordProvider.class.getSimpleName();

    public void init(Context context) {
        Log.i(TAG, "init; ");
        onCreate();
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityCreated(Activity activity) {
        String str = TAG;
        Log.i(str, "onActivityCreated; activity: " + activity);
        if (activity instanceof PasswordActivity) {
            Log.i(TAG, "onActivityCreated; ignoring");
        } else if (!PasswordActivity.sUnlocked) {
            Intent i = new Intent(activity, PasswordActivity.class);
            i.addFlags(65536);
            activity.startActivity(i);
            Log.i(TAG, "onActivityCreated; started PasswordActivity");
        } else {
            Log.i(TAG, "onActivityCreated; already unlocked");
        }
    }
}
