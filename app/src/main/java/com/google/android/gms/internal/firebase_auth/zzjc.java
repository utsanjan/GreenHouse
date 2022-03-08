package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzjc extends zzix {
    private zzjc() {
        super();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzix
    final <L> List<L> zza(Object obj, long j) {
        zzio zzc = zzc(obj, j);
        if (zzc.zza()) {
            return zzc;
        }
        int size = zzc.size();
        zzio zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzlf.zza(obj, j, zza);
        return zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzix
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.firebase_auth.zzio] */
    @Override // com.google.android.gms.internal.firebase_auth.zzix
    final <E> void zza(Object obj, Object obj2, long j) {
        zzio<E> zzc = zzc(obj, j);
        zzio<E> zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        zzc2 = zzc;
        zzc2 = zzc;
        if (size > 0 && size2 > 0) {
            boolean zza = zzc.zza();
            zzio<E> zzioVar = zzc;
            if (!zza) {
                zzioVar = zzc.zza(size2 + size);
            }
            zzioVar.addAll(zzc2);
            zzc2 = zzioVar;
        }
        if (size > 0) {
        }
        zzlf.zza(obj, j, zzc2);
    }

    private static <E> zzio<E> zzc(Object obj, long j) {
        return (zzio) zzlf.zzf(obj, j);
    }
}
