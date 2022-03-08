package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.internal.firebase_auth.zzeq;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.internal.zzaa;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzg implements zzfp<zzeq> {
    private final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzeq zzeqVar) {
        zzar zzarVar;
        Logger logger;
        zzeq zzeqVar2 = zzeqVar;
        if (zzeqVar2.zzh()) {
            zzarVar = this.zzb.zzc;
            if (zzarVar.zzb()) {
                this.zza.zza(new zzej(zzeqVar2.zzg(), zzeqVar2.zzf(), null));
                return;
            }
            logger = zza.zza;
            logger.e("Need to do multi-factor auth, but either the SDK does not support it, or the flag is disabled.", new Object[0]);
            zza("REQUIRES_SECOND_FACTOR_AUTH");
            return;
        }
        this.zzb.zza(new zzff(zzeqVar2.zzc(), zzeqVar2.zzb(), Long.valueOf(zzeqVar2.zze()), "Bearer"), null, null, Boolean.valueOf(zzeqVar2.zzd()), null, this.zza, this);
    }
}
