package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzev implements zzfw<zzp.zzf> {
    private String zza;

    public zzev(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzf zza() {
        return (zzp.zzf) ((zzif) zzp.zzf.zza().zza(this.zza).zzg());
    }
}
