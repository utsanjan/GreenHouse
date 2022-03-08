package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzcf;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzn;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbc extends zzfe<AuthResult, zza> {
    final zzcf zza;

    public zzbc(String str, String str2, String str3) {
        super(2);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        Preconditions.checkNotEmpty(str2, "password cannot be null or empty");
        this.zza = new zzcf(str, str2, str3);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "createUserWithEmailAndPassword";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, AuthResult> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzbb
            private final zzbc zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzbc zzbcVar = this.zza;
                zzef zzefVar = (zzef) obj;
                zzbcVar.zzh = new zzfo(zzbcVar, (TaskCompletionSource) obj2);
                if (zzbcVar.zzu) {
                    zzefVar.zza().zzc(zzbcVar.zza.zza(), zzbcVar.zza.zzb(), zzbcVar.zzc);
                } else {
                    zzefVar.zza().zza(zzbcVar.zza, zzbcVar.zzc);
                }
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final void zze() {
        zzn zza = zzau.zza(this.zzd, this.zzl);
        ((zza) this.zzf).zza(this.zzk, zza);
        zzb((zzbc) new zzh(zza));
    }
}
