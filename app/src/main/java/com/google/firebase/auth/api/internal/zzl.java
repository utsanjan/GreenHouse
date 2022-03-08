package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzgc;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzl implements zzfp<zzgc> {
    private final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzgc zzgcVar) {
        zzgc zzgcVar2 = zzgcVar;
        this.zzb.zza(new zzff(zzgcVar2.zzc(), zzgcVar2.zzb(), Long.valueOf(zzgcVar2.zzd()), "Bearer"), null, null, Boolean.valueOf(zzgcVar2.zze()), null, this.zza, this);
    }
}
