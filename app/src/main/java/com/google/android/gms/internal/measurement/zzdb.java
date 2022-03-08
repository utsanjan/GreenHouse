package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzdb {
    final String zza;
    final Uri zzb;
    final String zzc;
    final String zzd;
    final boolean zze;
    final boolean zzf;
    final boolean zzg;
    final boolean zzh;
    @Nullable
    final zzdf<Context, Boolean> zzi;

    public zzdb(Uri uri) {
        this(null, uri, "", "", false, false, false, false, null);
    }

    private zzdb(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzdf<Context, Boolean> zzdfVar) {
        this.zza = null;
        this.zzb = uri;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = false;
        this.zzf = false;
        this.zzg = false;
        this.zzh = false;
        this.zzi = null;
    }

    public final zzcv<Long> zza(String str, long j) {
        zzcv<Long> zzb;
        zzb = zzcv.zzb(this, str, j, true);
        return zzb;
    }

    public final zzcv<Boolean> zza(String str, boolean z) {
        zzcv<Boolean> zzb;
        zzb = zzcv.zzb(this, str, z, true);
        return zzb;
    }

    public final zzcv<Double> zza(String str, double d) {
        zzcv<Double> zzb;
        zzb = zzcv.zzb(this, str, -3.0d, true);
        return zzb;
    }

    public final zzcv<String> zza(String str, String str2) {
        zzcv<String> zzb;
        zzb = zzcv.zzb(this, str, str2, true);
        return zzb;
    }
}
