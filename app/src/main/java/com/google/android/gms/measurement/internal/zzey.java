package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzey {
    public String zza;
    public Bundle zzb;
    private String zzc;
    private long zzd;

    private zzey(String str, String str2, Bundle bundle, long j) {
        this.zza = str;
        this.zzc = str2;
        this.zzb = bundle == null ? new Bundle() : bundle;
        this.zzd = j;
    }

    public static zzey zza(zzao zzaoVar) {
        return new zzey(zzaoVar.zza, zzaoVar.zzc, zzaoVar.zzb.zzb(), zzaoVar.zzd);
    }

    public final zzao zza() {
        return new zzao(this.zza, new zzan(new Bundle(this.zzb)), this.zzc, this.zzd);
    }

    public final String toString() {
        String str = this.zzc;
        String str2 = this.zza;
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("origin=");
        sb.append(str);
        sb.append(",name=");
        sb.append(str2);
        sb.append(",params=");
        sb.append(valueOf);
        return sb.toString();
    }
}
