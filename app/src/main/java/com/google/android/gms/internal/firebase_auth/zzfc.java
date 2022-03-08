package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfc implements zzel<zzfc, zzp.zzi> {
    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzi> zza() {
        return zzp.zzi.zza();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzfc zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzi) {
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of GetOobConfirmationCodeResponse.");
    }
}
