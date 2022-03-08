package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzeu;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfs;
import com.google.firebase.auth.internal.zzaa;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzy implements zzfp<zzeu> {
    private final /* synthetic */ zzfp zza;
    private final /* synthetic */ zzff zzb;
    private final /* synthetic */ zzv zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(zzv zzvVar, zzfp zzfpVar, zzff zzffVar) {
        this.zzc = zzvVar;
        this.zza = zzfpVar;
        this.zzb = zzffVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zzc.zzb.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzeu zzeuVar) {
        List<zzew> zzb = zzeuVar.zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzew zzewVar = zzb.get(0);
        zzfs zzfsVar = new zzfs();
        zzfsVar.zzb(this.zzb.zzd()).zzg(this.zzc.zza);
        this.zzc.zzc.zza(this.zzc.zzb, this.zzb, zzewVar, zzfsVar, this.zza);
    }
}
