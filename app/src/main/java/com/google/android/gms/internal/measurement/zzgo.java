package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzgo<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> void zza(zzev zzevVar, zzgr<K, V> zzgrVar, K k, V v) throws IOException {
        zzfe.zza(zzevVar, zzgrVar.zza, 1, k);
        zzfe.zza(zzevVar, zzgrVar.zzc, 2, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> int zza(zzgr<K, V> zzgrVar, K k, V v) {
        return zzfe.zza(zzgrVar.zza, 1, k) + zzfe.zza(zzgrVar.zzc, 2, v);
    }
}
