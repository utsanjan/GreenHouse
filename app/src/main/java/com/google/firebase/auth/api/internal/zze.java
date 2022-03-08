package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzgh;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zze implements zzfp<zzgh> {
    private final /* synthetic */ zzeg zza;
    private final /* synthetic */ zza zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(zza zzaVar, zzeg zzegVar) {
        this.zzb = zzaVar;
        this.zza = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzgh zzghVar) {
        zzar zzarVar;
        Logger logger;
        zzgh zzghVar2 = zzghVar;
        if (zzghVar2.zzg()) {
            zzarVar = this.zzb.zzc;
            if (zzarVar.zzb()) {
                this.zza.zza(new zzej(zzghVar2.zzf(), zzghVar2.zze(), null));
                return;
            }
            logger = zza.zza;
            logger.e("Need to do multi-factor auth, but SDK does not support it.", new Object[0]);
            zza("REQUIRES_SECOND_FACTOR_AUTH");
            return;
        }
        this.zzb.zza(new zzff(zzghVar2.zzc(), zzghVar2.zzb(), Long.valueOf(zzghVar2.zzd()), "Bearer"), null, null, false, null, this.zza, this);
    }
}
