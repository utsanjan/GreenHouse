package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfm {
    private final String zza;
    private final String zzb = null;
    private boolean zzc;
    private String zzd;
    private final /* synthetic */ zzfg zze;

    public zzfm(zzfg zzfgVar, String str, String str2) {
        this.zze = zzfgVar;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }

    public final String zza() {
        if (!this.zzc) {
            this.zzc = true;
            this.zzd = this.zze.zzg().getString(this.zza, null);
        }
        return this.zzd;
    }

    public final void zza(String str) {
        if (this.zze.zzt().zza(zzaq.zzbw) || !zzkr.zzc(str, this.zzd)) {
            SharedPreferences.Editor edit = this.zze.zzg().edit();
            edit.putString(this.zza, str);
            edit.apply();
            this.zzd = str;
        }
    }
}
