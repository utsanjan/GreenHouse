package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzd;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfp {
    final zzfy zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfp(zzfy zzfyVar) {
        this.zza = zzfyVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(String str) {
        if (str == null || str.isEmpty()) {
            this.zza.zzr().zzj().zza("Install Referrer Reporter was called with invalid app package name");
            return;
        }
        this.zza.zzq().zzd();
        if (!zza()) {
            this.zza.zzr().zzv().zza("Install Referrer Reporter is not available");
            return;
        }
        zzfo zzfoVar = new zzfo(this, str);
        this.zza.zzq().zzd();
        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
        intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
        PackageManager packageManager = this.zza.zzn().getPackageManager();
        if (packageManager == null) {
            this.zza.zzr().zzj().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");
            return;
        }
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            this.zza.zzr().zzv().zza("Play Service for fetching Install Referrer is unavailable on device");
            return;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        if (resolveInfo.serviceInfo != null) {
            String str2 = resolveInfo.serviceInfo.packageName;
            if (resolveInfo.serviceInfo.name == null || !"com.android.vending".equals(str2) || !zza()) {
                this.zza.zzr().zzi().zza("Play Store version 8.3.73 or higher required for Install Referrer");
                return;
            }
            try {
                this.zza.zzr().zzx().zza("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.zza.zzn(), new Intent(intent), zzfoVar, 1) ? "available" : "not available");
            } catch (Exception e) {
                this.zza.zzr().zzf().zza("Exception occurred while binding to Install Referrer Service", e.getMessage());
            }
        }
    }

    private final boolean zza() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.zza.zzn());
            if (packageManager != null) {
                return packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.zza.zzr().zzx().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e) {
            this.zza.zzr().zzx().zza("Failed to retrieve Play Store version for Install Referrer", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle zza(String str, zzd zzdVar) {
        this.zza.zzq().zzd();
        if (zzdVar == null) {
            this.zza.zzr().zzi().zza("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            Bundle zza = zzdVar.zza(bundle);
            if (zza != null) {
                return zza;
            }
            this.zza.zzr().zzf().zza("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e) {
            this.zza.zzr().zzf().zza("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }
}
