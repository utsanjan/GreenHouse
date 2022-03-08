package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
final class zzm extends zzd {
    private final /* synthetic */ zzn zzch;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(zzn zznVar) {
        this.zzch = zznVar;
    }

    @Override // com.google.android.gms.auth.api.signin.internal.zzd, com.google.android.gms.auth.api.signin.internal.zzt
    public final void zzf(Status status) throws RemoteException {
        this.zzch.setResult((zzn) status);
    }
}
