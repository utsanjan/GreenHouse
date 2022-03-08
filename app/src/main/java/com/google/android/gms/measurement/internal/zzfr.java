package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzko;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzfr implements Runnable {
    private final /* synthetic */ zzd zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzfo zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfr(zzfo zzfoVar, zzd zzdVar, ServiceConnection serviceConnection) {
        this.zzc = zzfoVar;
        this.zza = zzdVar;
        this.zzb = serviceConnection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        zzfp zzfpVar = this.zzc.zza;
        str = this.zzc.zzb;
        zzd zzdVar = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle zza = zzfpVar.zza(str, zzdVar);
        zzfpVar.zza.zzq().zzd();
        if (zza != null) {
            long j = zza.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j == 0) {
                zzfpVar.zza.zzr().zzi().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzfpVar.zza.zzr().zzf().zza("No referrer defined in Install Referrer response");
                } else {
                    zzfpVar.zza.zzr().zzx().zza("InstallReferrer API result", string);
                    zzkr zzi = zzfpVar.zza.zzi();
                    String valueOf = String.valueOf(string);
                    Bundle zza2 = zzi.zza(Uri.parse(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")));
                    if (zza2 == null) {
                        zzfpVar.zza.zzr().zzf().zza("No campaign params defined in Install Referrer result");
                    } else {
                        String string2 = zza2.getString(FirebaseAnalytics.Param.MEDIUM);
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = zza.getLong("referrer_click_timestamp_seconds", 0L) * 1000;
                            if (j2 == 0) {
                                zzfpVar.zza.zzr().zzf().zza("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zza2.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzfpVar.zza.zzc().zzi.zza()) {
                            zzfpVar.zza.zzu();
                            zzfpVar.zza.zzr().zzx().zza("Install Referrer campaign has already been logged");
                        } else if (!zzko.zzb() || !zzfpVar.zza.zzb().zza(zzaq.zzca) || zzfpVar.zza.zzab()) {
                            zzfpVar.zza.zzc().zzi.zza(j);
                            zzfpVar.zza.zzu();
                            zzfpVar.zza.zzr().zzx().zza("Logging Install Referrer campaign from sdk with ", "referrer API");
                            zza2.putString("_cis", "referrer API");
                            zzfpVar.zza.zzh().zza("auto", "_cmp", zza2);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzfpVar.zza.zzn(), serviceConnection);
        }
    }
}
