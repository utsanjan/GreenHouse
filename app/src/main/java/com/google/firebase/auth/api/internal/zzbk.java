package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzcl;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzn;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbk extends zzfe<AuthResult, zza> {
    private final zzcl zza;

    public zzbk(PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str) {
        super(2);
        Preconditions.checkNotNull(phoneMultiFactorAssertion);
        Preconditions.checkNotEmpty(str);
        this.zza = new zzcl(phoneMultiFactorAssertion.zza(), str);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "finalizeMfaSignIn";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, AuthResult> zzb() {
        return TaskApiCall.builder().setFeatures(zze.zzb).setAutoResolveMissingFeatures(false).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzbj
            private final zzbk zza;

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
        zzn zza = zzau.zza(this.zzd, this.zzl);
        if (this.zze == null || this.zze.getUid().equalsIgnoreCase(zza.getUid())) {
            ((zza) this.zzf).zza(this.zzk, zza);
            zzb((zzbk) new zzh(zza));
            return;
        }
        zza(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzef zzefVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzh = new zzfo(this, taskCompletionSource);
        zzefVar.zza().zza(this.zza, this.zzc);
    }
}
