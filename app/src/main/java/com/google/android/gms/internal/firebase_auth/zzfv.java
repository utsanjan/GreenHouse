package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfv implements zzel<zzfv, zzp.zzo> {
    private String zza;
    private String zzb;
    private Boolean zzc;
    private String zzd;
    private String zze;
    private zzfl zzf;
    private String zzg;
    private String zzh;
    private long zzi;

    public final String zzb() {
        return this.zzg;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final long zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zza;
    }

    public final List<zzfj> zzf() {
        zzfl zzflVar = this.zzf;
        if (zzflVar != null) {
            return zzflVar.zza();
        }
        return null;
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzo> zza() {
        return zzp.zzo.zzj();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzfv zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzo) {
            zzp.zzo zzoVar = (zzp.zzo) zzjnVar;
            this.zza = Strings.emptyToNull(zzoVar.zza());
            this.zzb = Strings.emptyToNull(zzoVar.d_());
            this.zzc = Boolean.valueOf(zzoVar.zzi());
            this.zzd = Strings.emptyToNull(zzoVar.zzb());
            this.zze = Strings.emptyToNull(zzoVar.zze());
            this.zzf = zzfl.zza(zzoVar.zzd());
            this.zzg = Strings.emptyToNull(zzoVar.zzc());
            this.zzh = Strings.emptyToNull(zzoVar.zzf());
            this.zzi = zzoVar.zzg();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of SetAccountInfoResponse.");
    }
}
