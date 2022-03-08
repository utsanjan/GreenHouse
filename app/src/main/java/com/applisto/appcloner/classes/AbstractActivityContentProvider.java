package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public abstract class AbstractActivityContentProvider extends AbstractContentProvider {
    private static final String TAG = AbstractActivityContentProvider.class.getSimpleName();
    private Set<Activity> mActivities = new HashSet();
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() { // from class: com.applisto.appcloner.classes.AbstractActivityContentProvider.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!AbstractActivityContentProvider.this.mActivities.isEmpty()) {
                    for (Activity activity : AbstractActivityContentProvider.this.mActivities) {
                        try {
                            AbstractActivityContentProvider.this.onActivityTimer(activity);
                        } catch (Exception e) {
                            Log.w(AbstractActivityContentProvider.TAG, e);
                        }
                    }
                    AbstractActivityContentProvider.this.mHandler.postDelayed(this, AbstractActivityContentProvider.this.getActivityTimerDelayMillis());
                }
            } catch (Exception e2) {
                Log.w(AbstractActivityContentProvider.TAG, e2);
            }
        }
    };

    protected int getActivityTimerDelayMillis() {
        return 3000;
    }

    protected boolean onInit(Application application) {
        return true;
    }

    protected void onActivityCreated(Activity activity) {
    }

    protected void onActivityStarted(Activity activity) {
    }

    protected void onActivityResumed(Activity activity) {
    }

    protected void onActivityTimer(Activity activity) {
    }

    protected void onActivityPaused(Activity activity) {
    }

    protected void onActivityStopped(Activity activity) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // com.applisto.appcloner.classes.AbstractContentProvider, android.content.ContentProvider
    public boolean onCreate() {
        Application application = Utils.getApplication();
        if (application == null || !onInit(application)) {
            return true;
        }
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.applisto.appcloner.classes.AbstractActivityContentProvider.2
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AbstractActivityContentProvider.this.onActivityCreated(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                AbstractActivityContentProvider.this.onActivityStarted(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                AbstractActivityContentProvider.this.onActivityResumed(activity);
                AbstractActivityContentProvider.this.mActivities.add(activity);
                AbstractActivityContentProvider.this.mHandler.removeCallbacksAndMessages(null);
                AbstractActivityContentProvider.this.mHandler.post(AbstractActivityContentProvider.this.mRunnable);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                AbstractActivityContentProvider.this.onActivityPaused(activity);
                AbstractActivityContentProvider.this.mActivities.remove(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                AbstractActivityContentProvider.this.onActivityStopped(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                AbstractActivityContentProvider.this.onActivityDestroyed(activity);
            }
        });
        return true;
    }

    protected View getRootView(Activity activity) {
        return activity.getWindow().getDecorView().getRootView();
    }
}
