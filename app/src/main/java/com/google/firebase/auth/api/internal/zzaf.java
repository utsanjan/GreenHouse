package com.google.firebase.auth.api.internal;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfs;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzaa;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzaf implements zzfp<zzff> {
    private final /* synthetic */ UserProfileChangeRequest zza;
    private final /* synthetic */ zzeg zzb;
    private final /* synthetic */ zza zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaf(zza zzaVar, UserProfileChangeRequest userProfileChangeRequest, zzeg zzegVar) {
        this.zzc = zzaVar;
        this.zza = userProfileChangeRequest;
        this.zzb = zzegVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zzb.zza(zzaa.zza(str));
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzff zzffVar) {
        zzff zzffVar2 = zzffVar;
        zzfs zzfsVar = new zzfs();
        zzfsVar.zzb(zzffVar2.zzd());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzfsVar.zze(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzfsVar.zzf(this.zza.zza());
        }
        this.zzc.zza(this.zzb, zzffVar2, zzfsVar, this);
    }
}
