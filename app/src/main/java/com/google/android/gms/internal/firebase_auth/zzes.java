package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzlv;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzes implements zzfw<zzlv.zza> {
    private String zza = zzet.REFRESH_TOKEN.toString();
    private String zzb;

    public zzes(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzlv.zza zza() {
        return (zzlv.zza) ((zzif) zzlv.zza.zza().zza(this.zza).zzb(this.zzb).zzg());
    }
}
