package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzge implements zzfw<zzp.zzv> {
    private String zza;
    private String zzb;
    private final String zzc;
    private boolean zzd = true;

    public zzge(String str, String str2, String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = str3;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzv zza() {
        zzp.zzv.zza zza = zzp.zzv.zza().zza(this.zza).zzb(this.zzb).zza(this.zzd);
        String str = this.zzc;
        if (str != null) {
            zza.zzc(str);
        }
        return (zzp.zzv) ((zzif) zza.zzg());
    }
}
