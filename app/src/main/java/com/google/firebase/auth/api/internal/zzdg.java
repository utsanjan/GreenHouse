package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzdt;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzy;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzdg extends zzfe<Void, zza> {
    private final zzdt zza;

    public zzdg(zzy zzyVar, String str, String str2, long j, boolean z, boolean z2) {
        super(8);
        Preconditions.checkNotNull(zzyVar);
        Preconditions.checkNotEmpty(str);
        this.zza = new zzdt(zzyVar.zza(), str, str2, j, z, z2);
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final String zza() {
        return "startMfaEnrollmentWithPhoneNumber";
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final TaskApiCall<zzef, Void> zzb() {
        return TaskApiCall.builder().setFeatures(zze.zzb).setAutoResolveMissingFeatures(false).run(new RemoteCall(this) { // from class: com.google.firebase.auth.api.internal.zzdf
            private final zzdg zza;

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
        zzefVar.zza().zza(this.zza, this.zzc);
    }
}
