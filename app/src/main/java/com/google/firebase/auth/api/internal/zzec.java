package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzdd;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzec extends zzfe<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzdd zza;

    public zzec(zzfr zzfrVar) {
        super(8);
        Preconditions.checkNotNull(zzfrVar);
        this.zza = new zzdd(zzfrVar);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "verifyPhoneNumber";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, Void> zzb() {
        return TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures((this.zzu || this.zzv) ? null : new Feature[]{zze.zza}).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzeb
            private final zzec zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                this.zza.zza((zzef) obj, (TaskCompletionSource) obj2);
            }
        }).build();
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final void zze() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzef zzefVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzfo(this, taskCompletionSource);
        if (this.zzu) {
            zzefVar.zza().zza(this.zza.zza(), this.zzc);
        } else {
            zzefVar.zza().zza(this.zza, this.zzc);
        }
    }
}
