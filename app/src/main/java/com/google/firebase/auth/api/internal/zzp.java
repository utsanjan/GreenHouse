package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfs;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzp implements zzfp<zzff> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzeg zzc;
    private final /* synthetic */ zza zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzp(zza zzaVar, String str, String str2, zzeg zzegVar) {
        this.zzd = zzaVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zzc.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzff zzffVar2 = zzffVar;
        zzfs zzfsVar = new zzfs();
        zzfsVar.zzb(zzffVar2.zzd()).zzc(this.zza).zzd(this.zzb);
        this.zzd.zza(this.zzc, zzffVar2, zzfsVar, this);
    }
}
