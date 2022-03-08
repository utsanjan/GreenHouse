package com.google.android.gms.measurement.internal;

import androidx.exifinterface.media.ExifInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzex implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ Object zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzeu zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzex(zzeu zzeuVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzeuVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        char c;
        long j;
        char c2;
        long j2;
        zzfg zzc = this.zzf.zzy.zzc();
        if (zzc.zzz()) {
            c = this.zzf.zza;
            if (c == 0) {
                if (this.zzf.zzt().zzg()) {
                    zzeu zzeuVar = this.zzf;
                    zzeuVar.zzu();
                    zzeuVar.zza = 'C';
                } else {
                    zzeu zzeuVar2 = this.zzf;
                    zzeuVar2.zzu();
                    zzeuVar2.zza = 'c';
                }
            }
            j = this.zzf.zzb;
            if (j < 0) {
                zzeu zzeuVar3 = this.zzf;
                zzeuVar3.zzb = zzeuVar3.zzt().zzf();
            }
            char charAt = "01VDIWEA?".charAt(this.zza);
            c2 = this.zzf.zza;
            j2 = this.zzf.zzb;
            String zza = zzeu.zza(true, this.zzb, this.zzc, this.zzd, this.zze);
            StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 24);
            sb.append(ExifInterface.GPS_MEASUREMENT_2D);
            sb.append(charAt);
            sb.append(c2);
            sb.append(j2);
            sb.append(":");
            sb.append(zza);
            String sb2 = sb.toString();
            if (sb2.length() > 1024) {
                sb2 = this.zzb.substring(0, 1024);
            }
            zzc.zzb.zza(sb2, 1L);
            return;
        }
        this.zzf.zza(6, "Persisted config not initialized. Not logging error/warn");
    }
}
