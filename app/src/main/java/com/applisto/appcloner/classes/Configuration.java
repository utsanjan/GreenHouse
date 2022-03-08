package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/* loaded from: classes2.dex */
public class Configuration extends AbstractActivityContentProvider {
    private static final String TAG = Configuration.class.getSimpleName();
    private final float mDensityDpiScale;
    private final float mFontScale;
    private final String mLanguage;
    private int mOriginalDensityDpi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Configuration(CloneSettings cloneSettings) {
        this.mLanguage = cloneSettings.getString("language", null);
        Float valueOf = Float.valueOf(1.0f);
        this.mDensityDpiScale = cloneSettings.getFloat("densityDpiScale", valueOf).floatValue();
        this.mFontScale = cloneSettings.getFloat("fontScale", valueOf).floatValue();
        String str = TAG;
        Log.i(str, "Configuration; mLanguage: " + this.mLanguage + ", mDensityDpiScale: " + this.mDensityDpiScale + ", mFontScale: " + this.mFontScale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(Context context) {
        try {
            if (!TextUtils.isEmpty(this.mLanguage) || this.mDensityDpiScale != 1.0f || this.mFontScale != 1.0f) {
                onCreate();
                android.content.res.Configuration config = context.getResources().getConfiguration();
                if (Build.VERSION.SDK_INT >= 17) {
                    this.mOriginalDensityDpi = config.densityDpi;
                }
                setConfiguration(context);
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private void setConfiguration(Context context) {
        Locale l;
        android.content.res.Configuration config = new android.content.res.Configuration(context.getResources().getConfiguration());
        if (!TextUtils.isEmpty(this.mLanguage)) {
            String str = TAG;
            Log.i(str, "setConfiguration; mLanguage: " + this.mLanguage);
            try {
                int pos = this.mLanguage.indexOf("-");
                if (pos == -1) {
                    pos = this.mLanguage.indexOf("_");
                }
                if (pos == -1) {
                    l = new Locale(this.mLanguage);
                } else {
                    l = new Locale(this.mLanguage.substring(0, pos), this.mLanguage.substring(pos + 1));
                }
                Locale.setDefault(l);
                config.locale = l;
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        if (this.mDensityDpiScale != 1.0f && Build.VERSION.SDK_INT >= 17) {
            int densityDpi = Math.round(this.mOriginalDensityDpi * this.mDensityDpiScale);
            String str2 = TAG;
            Log.i(str2, "setConfiguration; mOriginalDensityDpi: " + this.mOriginalDensityDpi + ", mDensityDpiScale: " + this.mDensityDpiScale + ", densityDpi: " + densityDpi);
            config.densityDpi = densityDpi;
        }
        if (this.mFontScale != 1.0f) {
            String str3 = TAG;
            Log.i(str3, "setConfiguration; mFontScale: " + this.mFontScale);
            config.fontScale = this.mFontScale;
        }
        String str4 = TAG;
        Log.i(str4, "setConfiguration; config: " + config);
        Resources resources = context.getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityCreated(Activity activity) {
        Log.i(TAG, "onActivityCreated; ");
        try {
            setConfiguration(activity);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityStarted(Activity activity) {
        Log.i(TAG, "onActivityStarted; ");
        try {
            setConfiguration(activity);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityResumed(Activity activity) {
        Log.i(TAG, "onActivityResumed; ");
        try {
            setConfiguration(activity);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
}
