package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzft implements zzel<zzft, zzp.zzm> {
    private String zza;

    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzm> zza() {
        return zzp.zzm.zzb();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzft zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzm) {
            this.zza = ((zzp.zzm) zzjnVar).zza();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of SendVerificationCodeResponse.");
    }
}
