package com.google.firebase.auth.api.internal;

import android.content.Context;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzgg;
import com.google.firebase.auth.internal.zzaa;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzs implements zzfp<zzff> {
    final /* synthetic */ zzeg zza;
    final /* synthetic */ zza zzb;
    private final /* synthetic */ zzgg zzc;
    private final /* synthetic */ Context zzd = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(zza zzaVar, zzgg zzggVar, Context context, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zzc = zzggVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzfn zzfnVar;
        this.zzc.zza(zzffVar.zzd());
        zzfnVar = this.zzb.zzb;
        zzfnVar.zza(this.zzd, this.zzc, new zzr(this, this));
    }
}
