package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzfj implements zzfl {
    private final /* synthetic */ Status zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfj(zzfg zzfgVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.firebase.auth.api.internal.zzfl
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzeh.zza(this.zza));
    }
}
