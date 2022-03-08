package com.google.firebase.crashlytics;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.MissingNativeComponent;
import com.google.firebase.crashlytics.internal.Onboarding;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.ExecutorUtils;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class FirebaseCrashlytics {
    private final CrashlyticsCore core;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FirebaseCrashlytics init(FirebaseApp app, FirebaseInstanceIdInternal instanceId, CrashlyticsNativeComponent nativeComponent, AnalyticsConnector analyticsConnector) {
        CrashlyticsNativeComponent nativeComponent2;
        Context context = app.getApplicationContext();
        String appIdentifier = context.getPackageName();
        IdManager idManager = new IdManager(context, appIdentifier, instanceId);
        DataCollectionArbiter arbiter = new DataCollectionArbiter(app);
        if (nativeComponent == null) {
            nativeComponent2 = new MissingNativeComponent();
        } else {
            nativeComponent2 = nativeComponent;
        }
        final Onboarding onboarding = new Onboarding(app, context, idManager, arbiter);
        final CrashlyticsCore core = new CrashlyticsCore(app, idManager, nativeComponent2, arbiter, analyticsConnector);
        if (!onboarding.onPreExecute()) {
            Logger.getLogger().e("Unable to start Crashlytics.");
            return null;
        }
        final ExecutorService threadPoolExecutor = ExecutorUtils.buildSingleThreadExecutorService("com.google.firebase.crashlytics.startup");
        final SettingsController settingsController = onboarding.retrieveSettingsData(context, app, threadPoolExecutor);
        final boolean finishCoreInBackground = core.onPreExecute(settingsController);
        Tasks.call(threadPoolExecutor, new Callable<Void>() { // from class: com.google.firebase.crashlytics.FirebaseCrashlytics.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                Onboarding.this.doOnboarding(threadPoolExecutor, settingsController);
                if (!finishCoreInBackground) {
                    return null;
                }
                core.doBackgroundInitializationAsync(settingsController);
                return null;
            }
        });
        return new FirebaseCrashlytics(core);
    }

    private FirebaseCrashlytics(CrashlyticsCore core) {
        this.core = core;
    }

    public static FirebaseCrashlytics getInstance() {
        FirebaseApp app = FirebaseApp.getInstance();
        FirebaseCrashlytics instance = (FirebaseCrashlytics) app.get(FirebaseCrashlytics.class);
        if (instance != null) {
            return instance;
        }
        throw new NullPointerException("FirebaseCrashlytics component is not present.");
    }

    public void recordException(Throwable throwable) {
        if (throwable == null) {
            Logger.getLogger().w("Crashlytics is ignoring a request to log a null exception.");
        } else {
            this.core.logException(throwable);
        }
    }

    public void log(String message) {
        this.core.log(message);
    }

    public void setUserId(String identifier) {
        this.core.setUserId(identifier);
    }

    public void setCustomKey(String key, boolean value) {
        this.core.setCustomKey(key, Boolean.toString(value));
    }

    public void setCustomKey(String key, double value) {
        this.core.setCustomKey(key, Double.toString(value));
    }

    public void setCustomKey(String key, float value) {
        this.core.setCustomKey(key, Float.toString(value));
    }

    public void setCustomKey(String key, int value) {
        this.core.setCustomKey(key, Integer.toString(value));
    }

    public void setCustomKey(String key, long value) {
        this.core.setCustomKey(key, Long.toString(value));
    }

    public void setCustomKey(String key, String value) {
        this.core.setCustomKey(key, value);
    }

    public Task<Boolean> checkForUnsentReports() {
        return this.core.checkForUnsentReports();
    }

    public void sendUnsentReports() {
        this.core.sendUnsentReports();
    }

    public void deleteUnsentReports() {
        this.core.deleteUnsentReports();
    }

    public boolean didCrashOnPreviousExecution() {
        return this.core.didCrashOnPreviousExecution();
    }

    public void setCrashlyticsCollectionEnabled(boolean enabled) {
        this.core.setCrashlyticsCollectionEnabled(enabled);
    }
}
