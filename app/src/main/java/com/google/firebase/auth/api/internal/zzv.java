package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzev;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzv implements zzfp<zzff> {
    final /* synthetic */ String zza;
    final /* synthetic */ zzeg zzb;
    final /* synthetic */ zza zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(zza zzaVar, String str, zzeg zzegVar) {
        this.zzc = zzaVar;
        this.zza = str;
        this.zzb = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zzb.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzfn zzfnVar;
        zzff zzffVar2 = zzffVar;
        zzev zzevVar = new zzev(zzffVar2.zzd());
        zzfnVar = this.zzc.zzb;
        zzfnVar.zza(zzevVar, new zzy(this, this, zzffVar2));
    }
}
