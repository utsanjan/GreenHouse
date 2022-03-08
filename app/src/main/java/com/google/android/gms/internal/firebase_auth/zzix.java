package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzix {
    private static final zzix zza = new zziz();
    private static final zzix zzb = new zzjc();

    private zzix() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzix zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzix zzb() {
        return zzb;
    }
}
