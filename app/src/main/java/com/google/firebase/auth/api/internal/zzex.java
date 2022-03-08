package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzex {
    private String zza;

    public zzex(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    public final zzeu zza() {
        return new zzeu(this.zza, null);
    }
}
