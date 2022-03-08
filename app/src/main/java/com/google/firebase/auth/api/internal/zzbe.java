package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.firebase_auth.zzch;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzad;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbe extends zzfe<Void, zzad> {
    public zzbe() {
        super(5);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "delete";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, Void> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzbd
            private final zzbe zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbe zzbeVar = this.zza;
                zzef zzefVar = (zzef) obj;
                zzbeVar.zzh = new zzfo(zzbeVar, (TaskCompletionSource) obj2);
                if (zzbeVar.zzu) {
                    zzefVar.zza().zzg(zzbeVar.zze.zzf(), zzbeVar.zzc);
                } else {
                    zzefVar.zza().zza(new zzch(zzbeVar.zze.zzf()), zzbeVar.zzc);
                }
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final void zze() {
        ((zzad) this.zzf).zza();
        zzb((zzbe) null);
    }
}
