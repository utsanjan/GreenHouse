package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzen implements zzfw<zzp.zza> {
    private String zza;
    private String zzb = "http://localhost";
    private final String zzc;

    public zzen(String str, String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzc = str2;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zza zza() {
        zzp.zza.C0033zza zzb = zzp.zza.zza().zza(this.zza).zzb(this.zzb);
        String str = this.zzc;
        if (str != null) {
            zzb.zzc(str);
        }
        return (zzp.zza) ((zzif) zzb.zzg());
    }
}
