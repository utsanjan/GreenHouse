package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzgl extends zzgg {
    private zzgl() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    final <L> List<L> zza(Object obj, long j) {
        zzfx zzc = zzc(obj, j);
        if (zzc.zza()) {
            return zzc;
        }
        int size = zzc.size();
        zzfx zza = zzc.zza(size == 0 ? 10 : size << 1);
        zzin.zza(obj, j, zza);
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.measurement.zzfx] */
    @Override // com.google.android.gms.internal.measurement.zzgg
    final <E> void zza(Object obj, Object obj2, long j) {
        zzfx<E> zzc = zzc(obj, j);
        zzfx<E> zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        zzc2 = zzc;
        zzc2 = zzc;
        if (size > 0 && size2 > 0) {
            boolean zza = zzc.zza();
            zzfx<E> zzfxVar = zzc;
            if (!zza) {
                zzfxVar = zzc.zza(size2 + size);
            }
            zzfxVar.addAll(zzc2);
            zzc2 = zzfxVar;
        }
        if (size > 0) {
        }
        zzin.zza(obj, j, zzc2);
    }

    private static <E> zzfx<E> zzc(Object obj, long j) {
        return (zzfx) zzin.zzf(obj, j);
    }
}
