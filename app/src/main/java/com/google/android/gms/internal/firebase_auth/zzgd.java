package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzgd implements zzfw<zzp.zzt> {
    private String zza;
    private String zzb;

    public zzgd(String str, String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzt zza() {
        zzp.zzt.zza zza = zzp.zzt.zza().zza(this.zza).zza(true);
        String str = this.zzb;
        if (str != null) {
            zza.zzb(str);
        }
        return (zzp.zzt) ((zzif) zza.zzg());
    }
}
