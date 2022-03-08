package com.google.android.gms.internal.firebase_auth;

import java.util.regex.Matcher;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzaq extends zzam {
    private final Matcher zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaq(Matcher matcher) {
        this.zza = (Matcher) zzav.zza(matcher);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzam
    public final boolean zza() {
        return this.zza.matches();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzam
    public final boolean zza(int i) {
        return this.zza.find(i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzam
    public final int zzb() {
        return this.zza.end();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzam
    public final int zzc() {
        return this.zza.start();
    }
}
