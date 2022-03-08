package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzal {
    final String zza;
    final String zzb;
    final long zzc;
    final long zzd;
    final zzan zze;
    private final String zzf;

    private zzal(zzfy zzfyVar, String str, String str2, String str3, long j, long j2, zzan zzanVar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzanVar);
        this.zza = str2;
        this.zzb = str3;
        this.zzf = TextUtils.isEmpty(str) ? null : str;
        this.zzc = j;
        this.zzd = j2;
        if (j2 != 0 && j2 > j) {
            zzfyVar.zzr().zzi().zza("Event created with reverse previous/current timestamps. appId, name", zzeu.zza(str2), zzeu.zza(str3));
        }
        this.zze = zzanVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(zzfy zzfyVar, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzan zzanVar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzf = TextUtils.isEmpty(str) ? null : str;
        this.zzc = j;
        this.zzd = j2;
        if (j2 != 0 && j2 > j) {
            zzfyVar.zzr().zzi().zza("Event created with reverse previous/current timestamps. appId", zzeu.zza(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzanVar = new zzan(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzfyVar.zzr().zzf().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zza = zzfyVar.zzi().zza(next, bundle2.get(next));
                    if (zza == null) {
                        zzfyVar.zzr().zzi().zza("Param value can't be null", zzfyVar.zzj().zzb(next));
                        it.remove();
                    } else {
                        zzfyVar.zzi().zza(bundle2, next, zza);
                    }
                }
            }
            zzanVar = new zzan(bundle2);
        }
        this.zze = zzanVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzal zza(zzfy zzfyVar, long j) {
        return new zzal(zzfyVar, this.zzf, this.zza, this.zzb, this.zzc, j, this.zze);
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String valueOf = String.valueOf(this.zze);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }
}
