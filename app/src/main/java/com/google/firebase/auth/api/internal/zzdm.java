package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzn;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzdm extends zzfe<AuthResult, zza> {
    public zzdm() {
        super(2);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "unlinkEmailCredential";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, AuthResult> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzdl
            private final zzdm zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzdm zzdmVar = this.zza;
                zzef zzefVar = (zzef) obj;
                zzdmVar.zzh = new zzfo(zzdmVar, (TaskCompletionSource) obj2);
                if (zzdmVar.zzu) {
                    zzefVar.zza().zze(zzdmVar.zze.zzf(), zzdmVar.zzc);
                } else {
                    zzefVar.zza().zza(new zzdz(zzdmVar.zze.zzf()), zzdmVar.zzc);
                }
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final void zze() {
        zzn zza = zzau.zza(this.zzd, this.zzl);
        ((zza) this.zzf).zza(this.zzk, zza);
        zzb((zzdm) new zzh(zza));
    }
}