package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzeo;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.internal.zzaa;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzac implements zzfp<zzff> {
    final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzfn zzfnVar;
        zzeo zzeoVar = new zzeo(zzffVar.zzd());
        zzfnVar = this.zzb.zzb;
        zzfnVar.zza(zzeoVar, new zzab(this, this));
    }
}
