package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfs;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzw implements zzfp<zzff> {
    private final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzff zzffVar2 = zzffVar;
        zzfs zzfsVar = new zzfs();
        zzfsVar.zzb(zzffVar2.zzd()).zzc(null).zzd(null);
        this.zzb.zza(this.zza, zzffVar2, zzfsVar, this);
    }
}
