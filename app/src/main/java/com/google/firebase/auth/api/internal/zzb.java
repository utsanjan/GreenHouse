package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfx;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzb implements zzfp<zzfx> {
    private final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzfx zzfxVar) {
        zzfx zzfxVar2 = zzfxVar;
        this.zzb.zza(new zzff(zzfxVar2.zzc(), zzfxVar2.zzb(), Long.valueOf(zzfxVar2.zzd()), "Bearer"), null, null, true, null, this.zza, this);
    }
}
