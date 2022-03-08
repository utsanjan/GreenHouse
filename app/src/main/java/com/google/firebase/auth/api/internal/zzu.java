package com.google.firebase.auth.api.internal;

import android.content.Context;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfy;
import com.google.firebase.auth.internal.zzaa;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzu implements zzfp<zzff> {
    final /* synthetic */ zzeg zza;
    final /* synthetic */ zza zzb;
    private final /* synthetic */ zzfy zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(zza zzaVar, zzfy zzfyVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zzc = zzfyVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzar zzarVar;
        zzfn zzfnVar;
        zzff zzffVar2 = zzffVar;
        zzarVar = this.zzb.zzc;
        if (zzarVar.zza()) {
            this.zzc.zzc(true);
        }
        this.zzc.zza(zzffVar2.zzd());
        zzfnVar = this.zzb.zzb;
        zzfnVar.zza((Context) null, this.zzc, new zzt(this, this));
    }
}
