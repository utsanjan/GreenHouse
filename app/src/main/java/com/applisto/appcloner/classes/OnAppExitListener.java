package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class OnAppExitListener extends AbstractActivityContentProvider {
    private static final String TAG = OnAppExitListener.class.getSimpleName();
    private Set<Activity> mActivities = new HashSet();
    private Handler mHandler = new Handler();

    protected abstract void onAppExit(Context context);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        String str = TAG;
        Log.i(str, "onActivityCreated; activity: " + activity);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mActivities.add(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityDestroyed(Activity activity) {
        String str = TAG;
        Log.i(str, "onActivityDestroyed; activity: " + activity);
        this.mActivities.remove(activity);
        if (this.mActivities.isEmpty()) {
            final Context context = activity.getApplicationContext();
            if ("ch.iAgentur.i20Mio.MainActivity".equals(activity.getClass().getName())) {
                onAppExit(context);
            } else {
                this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.OnAppExitListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        OnAppExitListener.this.onAppExit(context);
                    }
                }, 1000L);
            }
        }
    }
}
