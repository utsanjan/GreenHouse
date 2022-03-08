package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.firebase_auth.zzcx;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zza;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzcm extends zzfe<Void, zza> {
    public zzcm() {
        super(2);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "reload";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, Void> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzcl
            private final zzcm zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzcm zzcmVar = this.zza;
                zzef zzefVar = (zzef) obj;
                zzcmVar.zzh = new zzfo(zzcmVar, (TaskCompletionSource) obj2);
                if (zzcmVar.zzu) {
                    zzefVar.zza().zzf(zzcmVar.zze.zzf(), zzcmVar.zzc);
                } else {
                    zzefVar.zza().zza(new zzcx(zzcmVar.zze.zzf()), zzcmVar.zzc);
                }
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final void zze() {
        ((zza) this.zzf).zza(this.zzk, zzau.zza(this.zzd, this.zzl));
        zzb((zzcm) null);
    }
}
