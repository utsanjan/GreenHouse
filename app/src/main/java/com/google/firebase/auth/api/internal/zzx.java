package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.internal.firebase_auth.zzga;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzx implements zzfp<zzga> {
    private final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzga zzgaVar) {
        zzar zzarVar;
        Logger logger;
        zzga zzgaVar2 = zzgaVar;
        if (!zzgaVar2.zzo()) {
            this.zzb.zza(zzgaVar2, this.zza, this);
            return;
        }
        zzarVar = this.zzb.zzc;
        if (zzarVar.zzb()) {
            this.zza.zza(new zzej(zzgaVar2.zzn(), zzgaVar2.zzm(), zzgaVar2.zzp()));
            return;
        }
        logger = zza.zza;
        logger.e("Need to do multi-factor auth, but SDK does not support it.", new Object[0]);
        zza("REQUIRES_SECOND_FACTOR_AUTH");
    }
}
