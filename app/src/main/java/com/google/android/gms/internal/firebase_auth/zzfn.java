package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfn implements zzfw<zzp.zzj> {
    private final String zza;
    private final String zzb;
    private final String zzc;

    public zzfn(String str, String str2, String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
        this.zzc = str3;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzj zza() {
        zzp.zzj.zza zza = zzp.zzj.zza().zza(this.zza);
        String str = this.zzb;
        if (str != null) {
            zza.zzb(str);
        }
        String str2 = this.zzc;
        if (str2 != null) {
            zza.zzc(str2);
        }
        return (zzp.zzj) ((zzif) zza.zzg());
    }
}
