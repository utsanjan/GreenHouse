package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzax implements zzba {
    final /* synthetic */ zzaf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(zzaf zzafVar) {
        this.zza = zzafVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    public final /* synthetic */ Iterator zza(zzau zzauVar, CharSequence charSequence) {
        return new zzaw(this, zzauVar, charSequence);
    }
}
