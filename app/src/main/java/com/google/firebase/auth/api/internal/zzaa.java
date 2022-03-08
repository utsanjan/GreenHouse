package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzev;
import com.google.android.gms.internal.firebase_auth.zzff;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzaa implements zzfp<zzff> {
    final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(com.google.firebase.auth.internal.zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzfn zzfnVar;
        zzff zzffVar2 = zzffVar;
        zzev zzevVar = new zzev(zzffVar2.zzd());
        zzfnVar = this.zzb.zzb;
        zzfnVar.zza(zzevVar, new zzz(this, this, zzffVar2));
    }
}
