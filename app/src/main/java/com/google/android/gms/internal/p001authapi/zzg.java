package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzg implements CredentialRequestResult {
    private final Status mStatus;
    private final Credential zzam;

    public zzg(Status status, Credential credential) {
        this.mStatus = status;
        this.zzam = credential;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }

    @Override // com.google.android.gms.auth.api.credentials.CredentialRequestResult
    public final Credential getCredential() {
        return this.zzam;
    }

    public static zzg zzc(Status status) {
        return new zzg(status, null);
    }
}
