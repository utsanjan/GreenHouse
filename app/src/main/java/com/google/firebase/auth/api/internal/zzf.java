package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzeu;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfs;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzf implements zzfp<zzeu> {
    private final /* synthetic */ zzfq zza;
    private final /* synthetic */ zzeg zzb;
    private final /* synthetic */ zzff zzc;
    private final /* synthetic */ zzfs zzd;
    private final /* synthetic */ zza zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zza zzaVar, zzfq zzfqVar, zzeg zzegVar, zzff zzffVar, zzfs zzfsVar) {
        this.zze = zzaVar;
        this.zza = zzfqVar;
        this.zzb = zzegVar;
        this.zzc = zzffVar;
        this.zzd = zzfsVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzeu zzeuVar) {
        List<zzew> zzb = zzeuVar.zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zze.zza(this.zzb, this.zzc, zzb.get(0), this.zzd, this.zza);
        }
    }
}
