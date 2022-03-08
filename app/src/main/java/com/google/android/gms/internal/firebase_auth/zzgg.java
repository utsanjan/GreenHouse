package com.google.android.gms.internal.firebase_auth;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzgg implements zzfw<zzp.zzx> {
    private boolean zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private boolean zzg;

    private zzgg() {
    }

    public static zzgg zza(String str, String str2, boolean z) {
        zzgg zzggVar = new zzgg();
        zzggVar.zza = false;
        zzggVar.zzc = Preconditions.checkNotEmpty(str);
        zzggVar.zzd = Preconditions.checkNotEmpty(str2);
        zzggVar.zzg = z;
        return zzggVar;
    }

    public static zzgg zzb(String str, String str2, boolean z) {
        zzgg zzggVar = new zzgg();
        zzggVar.zza = false;
        zzggVar.zzb = Preconditions.checkNotEmpty(str);
        zzggVar.zze = Preconditions.checkNotEmpty(str2);
        zzggVar.zzg = z;
        return zzggVar;
    }

    public final void zza(String str) {
        this.zzf = str;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzx zza() {
        zzp.zzx.zza zza = zzp.zzx.zza();
        if (!TextUtils.isEmpty(this.zze)) {
            zza.zzd(this.zze).zzb(this.zzb);
        } else {
            zza.zza(this.zzc).zzc(this.zzd);
        }
        String str = this.zzf;
        if (str != null) {
            zza.zze(str);
        }
        if (!this.zzg) {
            zza.zza(zzaa.REAUTH);
        }
        return (zzp.zzx) ((zzif) zza.zzg());
    }
}
