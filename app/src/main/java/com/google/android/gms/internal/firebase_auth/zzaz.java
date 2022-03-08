package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzaz implements zzba {
    private final /* synthetic */ zzal zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaz(zzal zzalVar) {
        this.zza = zzalVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    public final /* synthetic */ Iterator zza(zzau zzauVar, CharSequence charSequence) {
        return new zzay(this, zzauVar, charSequence, this.zza.zza(charSequence));
    }
}
