package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzae;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzhd {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzae zzg;
    boolean zzh;
    Long zzi;

    public zzhd(Context context, zzae zzaeVar, Long l) {
        this.zzh = true;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzaeVar != null) {
            this.zzg = zzaeVar;
            this.zzb = zzaeVar.zzf;
            this.zzc = zzaeVar.zze;
            this.zzd = zzaeVar.zzd;
            this.zzh = zzaeVar.zzc;
            this.zzf = zzaeVar.zzb;
            if (zzaeVar.zzg != null) {
                this.zze = Boolean.valueOf(zzaeVar.zzg.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
