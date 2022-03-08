package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.Logger;

/* loaded from: classes.dex */
public class DataCollectionArbiter {
    private static final String FIREBASE_CRASHLYTICS_COLLECTION_ENABLED = "firebase_crashlytics_collection_enabled";
    private volatile boolean crashlyticsDataCollectionEnabled;
    private volatile boolean crashlyticsDataCollectionExplicitlySet;
    private final FirebaseApp firebaseApp;
    private final SharedPreferences sharedPreferences;
    boolean taskResolved;
    private Object taskLock = new Object();
    TaskCompletionSource<Void> dataCollectionEnabledTask = new TaskCompletionSource<>();
    private TaskCompletionSource<Void> dataCollectionExplicitlyApproved = new TaskCompletionSource<>();

    public DataCollectionArbiter(FirebaseApp app) {
        ApplicationInfo applicationInfo;
        this.taskResolved = false;
        this.firebaseApp = app;
        Context applicationContext = app.getApplicationContext();
        if (applicationContext != null) {
            SharedPreferences sharedPrefs = CommonUtils.getSharedPrefs(applicationContext);
            this.sharedPreferences = sharedPrefs;
            boolean enabled = true;
            boolean explicitlySet = false;
            if (sharedPrefs.contains(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
                enabled = this.sharedPreferences.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, true);
                explicitlySet = true;
            } else {
                try {
                    PackageManager packageManager = applicationContext.getPackageManager();
                    if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED))) {
                        enabled = applicationInfo.metaData.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED);
                        explicitlySet = true;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Logger.getLogger().d("Unable to get PackageManager. Falling through", e);
                }
            }
            this.crashlyticsDataCollectionEnabled = enabled;
            this.crashlyticsDataCollectionExplicitlySet = explicitlySet;
            synchronized (this.taskLock) {
                if (isAutomaticDataCollectionEnabled()) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            }
            return;
        }
        throw new RuntimeException("null context");
    }

    public boolean isAutomaticDataCollectionEnabled() {
        if (this.crashlyticsDataCollectionExplicitlySet) {
            return this.crashlyticsDataCollectionEnabled;
        }
        return this.firebaseApp.isDataCollectionDefaultEnabled();
    }

    public Task<Void> waitForAutomaticDataCollectionEnabled() {
        Task<Void> task;
        synchronized (this.taskLock) {
            task = this.dataCollectionEnabledTask.getTask();
        }
        return task;
    }

    public void setCrashlyticsDataCollectionEnabled(boolean enabled) {
        this.crashlyticsDataCollectionEnabled = enabled;
        this.crashlyticsDataCollectionExplicitlySet = true;
        this.sharedPreferences.edit().putBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, enabled).commit();
        synchronized (this.taskLock) {
            if (enabled) {
                if (!this.taskResolved) {
                    this.dataCollectionEnabledTask.trySetResult(null);
                    this.taskResolved = true;
                }
            } else if (this.taskResolved) {
                this.dataCollectionEnabledTask = new TaskCompletionSource<>();
                this.taskResolved = false;
            }
        }
    }

    public Task<Void> waitForDataCollectionPermission() {
        return Utils.race(this.dataCollectionExplicitlyApproved.getTask(), waitForAutomaticDataCollectionEnabled());
    }

    public void grantDataCollectionPermission(boolean dataCollectionToken) {
        if (dataCollectionToken) {
            this.dataCollectionExplicitlyApproved.trySetResult(null);
            return;
        }
        throw new IllegalStateException("An invalid data collection token was used.");
    }
}
