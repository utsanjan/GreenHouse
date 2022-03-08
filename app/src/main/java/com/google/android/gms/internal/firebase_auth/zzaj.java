package com.google.android.gms.internal.firebase_auth;

import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzaj extends zzak {
    static final zzaj zza = new zzaj();

    private zzaj() {
        super("CharMatcher.none()");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaf
    public final boolean zza(char c) {
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaf
    public final int zza(CharSequence charSequence, int i) {
        zzav.zza(i, charSequence.length(), FirebaseAnalytics.Param.INDEX);
        return -1;
    }
}
