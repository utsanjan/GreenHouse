package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzgi extends zzgg {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzgi() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzin.zzf(obj, j);
        if (list instanceof zzgh) {
            obj2 = ((zzgh) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzhi) || !(list instanceof zzfx)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzfx zzfxVar = (zzfx) list;
                if (zzfxVar.zza()) {
                    zzfxVar.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzin.zza(obj, j, obj2);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> list;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zzgh) {
                list = new zzge(i);
            } else if (!(zzc instanceof zzhi) || !(zzc instanceof zzfx)) {
                list = new ArrayList<>(i);
            } else {
                list = ((zzfx) zzc).zza(i);
            }
            zzin.zza(obj, j, list);
            return list;
        } else if (zza.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzin.zza(obj, j, arrayList);
            return arrayList;
        } else if (zzc instanceof zzii) {
            zzge zzgeVar = new zzge(zzc.size() + i);
            zzgeVar.addAll((zzii) zzc);
            zzin.zza(obj, j, zzgeVar);
            return zzgeVar;
        } else if (!(zzc instanceof zzhi) || !(zzc instanceof zzfx)) {
            return zzc;
        } else {
            zzfx zzfxVar = (zzfx) zzc;
            if (zzfxVar.zza()) {
                return zzc;
            }
            zzfx zza2 = zzfxVar.zza(zzc.size() + i);
            zzin.zza(obj, j, zza2);
            return zza2;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzgg
    final <E> void zza(Object obj, Object obj2, long j) {
        List zzc = zzc(obj2, j);
        List zza2 = zza(obj, j, zzc.size());
        int size = zza2.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza2.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza2;
        }
        zzin.zza(obj, j, zzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzin.zzf(obj, j);
    }
}
