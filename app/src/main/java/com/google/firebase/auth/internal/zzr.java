package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzr extends MultiFactor {
    private final zzn zza;

    public zzr(zzn zznVar) {
        Preconditions.checkNotNull(zznVar);
        this.zza = zznVar;
    }

    @Override // com.google.firebase.auth.MultiFactor
    public final Task<MultiFactorSession> getSession() {
        return this.zza.getIdToken(false).continueWithTask(new zzu(this));
    }

    @Override // com.google.firebase.auth.MultiFactor
    public final Task<Void> enroll(MultiFactorAssertion multiFactorAssertion, String str) {
        Preconditions.checkNotNull(multiFactorAssertion);
        zzn zznVar = this.zza;
        return FirebaseAuth.getInstance(zznVar.zzc()).zza(zznVar, multiFactorAssertion, str);
    }

    @Override // com.google.firebase.auth.MultiFactor
    public final List<MultiFactorInfo> getEnrolledFactors() {
        return this.zza.zzk();
    }

    @Override // com.google.firebase.auth.MultiFactor
    public final Task<Void> unenroll(MultiFactorInfo multiFactorInfo) {
        Preconditions.checkNotNull(multiFactorInfo);
        return unenroll(multiFactorInfo.getUid());
    }

    @Override // com.google.firebase.auth.MultiFactor
    public final Task<Void> unenroll(String str) {
        Preconditions.checkNotEmpty(str);
        zzn zznVar = this.zza;
        return FirebaseAuth.getInstance(zznVar.zzc()).zzd(zznVar, str);
    }
}
