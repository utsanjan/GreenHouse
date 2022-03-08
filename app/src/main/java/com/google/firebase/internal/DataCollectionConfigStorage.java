package com.google.firebase.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes.dex */
public class DataCollectionConfigStorage {
    public static final String DATA_COLLECTION_DEFAULT_ENABLED = "firebase_data_collection_default_enabled";
    private static final String FIREBASE_APP_PREFS = "com.google.firebase.common.prefs:";
    private final Context applicationContext;
    private final AtomicBoolean dataCollectionDefaultEnabled = new AtomicBoolean(readAutoDataCollectionEnabled());
    private final Publisher publisher;
    private final SharedPreferences sharedPreferences;

    public DataCollectionConfigStorage(Context applicationContext, String persistenceKey, Publisher publisher) {
        this.applicationContext = directBootSafe(applicationContext);
        this.sharedPreferences = applicationContext.getSharedPreferences(FIREBASE_APP_PREFS + persistenceKey, 0);
        this.publisher = publisher;
    }

    private static Context directBootSafe(Context applicationContext) {
        if (Build.VERSION.SDK_INT < 24 || ContextCompat.isDeviceProtectedStorage(applicationContext)) {
            return applicationContext;
        }
        return ContextCompat.createDeviceProtectedStorageContext(applicationContext);
    }

    public boolean isEnabled() {
        return this.dataCollectionDefaultEnabled.get();
    }

    public void setEnabled(boolean enabled) {
        if (this.dataCollectionDefaultEnabled.compareAndSet(!enabled, enabled)) {
            this.sharedPreferences.edit().putBoolean(DATA_COLLECTION_DEFAULT_ENABLED, enabled).apply();
            this.publisher.publish(new Event<>(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(enabled)));
        }
    }

    private boolean readAutoDataCollectionEnabled() {
        ApplicationInfo applicationInfo;
        if (this.sharedPreferences.contains(DATA_COLLECTION_DEFAULT_ENABLED)) {
            return this.sharedPreferences.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED, true);
        }
        try {
            PackageManager packageManager = this.applicationContext.getPackageManager();
            if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(DATA_COLLECTION_DEFAULT_ENABLED))) {
                return applicationInfo.metaData.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return true;
    }
}
