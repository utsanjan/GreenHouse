package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzav extends zzaj {
    private final /* synthetic */ zzau zzcg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzau zzauVar) {
        this.zzcg = zzauVar;
    }

    @Override // com.google.android.gms.internal.auth.zzaj, com.google.android.gms.internal.auth.zzal
    public final void zzb(String str) {
        if (str != null) {
            this.zzcg.setResult((zzau) new zzax(str));
        } else {
            this.zzcg.setResult((zzau) zzau.zzc(new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR)));
        }
    }
}
