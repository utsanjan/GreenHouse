package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzeu;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzz implements zzfp<zzeu> {
    private final /* synthetic */ zzfp zza;
    private final /* synthetic */ zzff zzb;
    private final /* synthetic */ zzaa zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(zzaa zzaaVar, zzfp zzfpVar, zzff zzffVar) {
        this.zzc = zzaaVar;
        this.zza = zzfpVar;
        this.zzb = zzffVar;
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
            this.zzc.zza.zza(this.zzb, zzb.get(0));
        }
    }
}
