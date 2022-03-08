package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfx implements zzel<zzfx, zzp.zzq> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private long zze;

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzd;
    }

    public final long zzd() {
        return this.zze;
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzq> zza() {
        return zzp.zzq.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzfx zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzq) {
            zzp.zzq zzqVar = (zzp.zzq) zzjnVar;
            this.zza = Strings.emptyToNull(zzqVar.zza());
            this.zzb = Strings.emptyToNull(zzqVar.zzb());
            this.zzc = Strings.emptyToNull(zzqVar.zzc());
            this.zzd = Strings.emptyToNull(zzqVar.zzd());
            this.zze = zzqVar.zze();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of SignUpNewUserResponse.");
    }
}
