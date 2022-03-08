package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzjf<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> void zza(zzhm zzhmVar, zzji<K, V> zzjiVar, K k, V v) throws IOException {
        zzhv.zza(zzhmVar, zzjiVar.zza, 1, k);
        zzhv.zza(zzhmVar, zzjiVar.zzc, 2, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> int zza(zzji<K, V> zzjiVar, K k, V v) {
        return zzhv.zza(zzjiVar.zza, 1, k) + zzhv.zza(zzjiVar.zzc, 2, v);
    }
}
