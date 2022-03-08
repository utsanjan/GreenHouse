package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzgs implements zzgt {
    @Override // com.google.android.gms.internal.measurement.zzgt
    public final Map<?, ?> zza(Object obj) {
        return (zzgq) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final zzgr<?, ?> zzb(Object obj) {
        zzgo zzgoVar = (zzgo) obj;
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final Map<?, ?> zzc(Object obj) {
        return (zzgq) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final boolean zzd(Object obj) {
        return !((zzgq) obj).zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final Object zze(Object obj) {
        ((zzgq) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final Object zzf(Object obj) {
        return zzgq.zza().zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final Object zza(Object obj, Object obj2) {
        zzgq zzgqVar = (zzgq) obj;
        zzgq zzgqVar2 = (zzgq) obj2;
        if (!zzgqVar2.isEmpty()) {
            if (!zzgqVar.zzd()) {
                zzgqVar = zzgqVar.zzb();
            }
            zzgqVar.zza(zzgqVar2);
        }
        return zzgqVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzgt
    public final int zza(int i, Object obj, Object obj2) {
        zzgq zzgqVar = (zzgq) obj;
        zzgo zzgoVar = (zzgo) obj2;
        if (zzgqVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzgqVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
