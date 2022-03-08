package com.google.firebase.auth.api.internal;

import com.google.firebase.auth.PhoneAuthProvider;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzfk implements zzfl {
    private final /* synthetic */ String zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfk(zzfg zzfgVar, String str) {
        this.zza = str;
    }

    @Override // com.google.firebase.auth.api.internal.zzfl
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeAutoRetrievalTimeOut(this.zza);
    }
}
