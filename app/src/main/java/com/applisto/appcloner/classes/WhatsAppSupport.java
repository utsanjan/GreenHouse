package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class WhatsAppSupport extends AbstractActivityContentProvider {
    private static final String TAG = WhatsAppSupport.class.getSimpleName();
    private Handler mHandler = new Handler();
    private String mOriginalPackageName;
    private String mVersionName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(String originalPackageName) {
        this.mOriginalPackageName = originalPackageName;
        onCreate();
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityCreated(Activity activity) {
        install(activity);
    }

    private void install(final Context context) {
        String str = TAG;
        Log.i(str, "install; context: " + context);
        this.mVersionName = Utils.getVersionName(context);
        if (this.mVersionName == null) {
            this.mVersionName = "";
        }
        try {
            Field baseField = ContextWrapper.class.getDeclaredField("mBase");
            baseField.setAccessible(true);
            Context oldContext = context;
            while (context instanceof ContextWrapper) {
                oldContext = context;
                context = (Context) baseField.get(context);
            }
            ContextWrapper contextWrapper = new ContextWrapper(context) { // from class: com.applisto.appcloner.classes.WhatsAppSupport.1
                @Override // android.content.ContextWrapper, android.content.Context
                public String getPackageCodePath() {
                    try {
                        PackageInfo packageInfo = getPackageManager().getPackageInfo(WhatsAppSupport.this.mOriginalPackageName, 0);
                        if (!WhatsAppSupport.this.mVersionName.equals(packageInfo.versionName)) {
                            WhatsAppSupport.this.showOriginalWhatsAppRequiredMessage(context);
                        }
                        String publicSourceDir = packageInfo.applicationInfo.publicSourceDir;
                        String str2 = WhatsAppSupport.TAG;
                        Log.i(str2, "getPackageCodePath; publicSourceDir: " + publicSourceDir);
                        return publicSourceDir;
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.w(WhatsAppSupport.TAG, e);
                        WhatsAppSupport.this.showOriginalWhatsAppRequiredMessage(context);
                        return super.getPackageCodePath();
                    } catch (Exception e2) {
                        Log.w(WhatsAppSupport.TAG, e2);
                        return super.getPackageCodePath();
                    }
                }
            };
            baseField.set(oldContext, contextWrapper);
            Log.i(TAG, "installed; installed context wrapper");
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOriginalWhatsAppRequiredMessage(final Context context) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.WhatsAppSupport.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Context context2 = context;
                    Utils.showDialog(context2, "WhatsApp", "The original WhatsApp app " + WhatsAppSupport.this.mVersionName + " must be installed during the registration process.");
                } catch (Exception e) {
                    Log.w(WhatsAppSupport.TAG, e);
                }
            }
        }, 2000L);
    }
}
