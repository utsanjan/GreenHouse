package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfu implements zzfw<zzp.C0036zzp> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;

    public zzfu(String str) {
        this.zzd = str;
    }

    public zzfu(String str, String str2, String str3, String str4) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = null;
        this.zzd = str4;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.C0036zzp zza() {
        zzp.C0036zzp.zza zza = zzp.C0036zzp.zza();
        String str = this.zza;
        if (str != null) {
            zza.zza(str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            zza.zzb(str2);
        }
        String str3 = this.zzd;
        if (str3 != null) {
            zza.zzc(str3);
        }
        return (zzp.C0036zzp) ((zzif) zza.zzg());
    }
}
