package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzer;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzd implements zzfp<zzff> {
    private final /* synthetic */ EmailAuthCredential zza;
    private final /* synthetic */ zzeg zzb;
    private final /* synthetic */ zza zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(zza zzaVar, EmailAuthCredential emailAuthCredential, zzeg zzegVar) {
        this.zzc = zzaVar;
        this.zza = emailAuthCredential;
        this.zzb = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zzb.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        this.zzc.zza(new zzer(this.zza, zzffVar.zzd()), this.zzb);
    }
}
