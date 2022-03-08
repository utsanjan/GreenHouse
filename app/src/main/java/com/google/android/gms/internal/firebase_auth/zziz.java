package com.google.android.gms.internal.firebase_auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zziz extends zzix {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zziz() {
        super();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzix
    final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzix
    final void zzb(Object obj, long j) {
        Object obj2;
        List list = (List) zzlf.zzf(obj, j);
        if (list instanceof zziy) {
            obj2 = ((zziy) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzka) || !(list instanceof zzio)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzio zzioVar = (zzio) list;
                if (zzioVar.zza()) {
                    zzioVar.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzlf.zza(obj, j, obj2);
    }

    private static <L> List<L> zza(Object obj, long j, int i) {
        List<L> list;
        List<L> zzc = zzc(obj, j);
        if (zzc.isEmpty()) {
            if (zzc instanceof zziy) {
                list = new zziv(i);
            } else if (!(zzc instanceof zzka) || !(zzc instanceof zzio)) {
                list = new ArrayList<>(i);
            } else {
                list = ((zzio) zzc).zza(i);
            }
            zzlf.zza(obj, j, list);
            return list;
        } else if (zza.isAssignableFrom(zzc.getClass())) {
            ArrayList arrayList = new ArrayList(zzc.size() + i);
            arrayList.addAll(zzc);
            zzlf.zza(obj, j, arrayList);
            return arrayList;
        } else if (zzc instanceof zzla) {
            zziv zzivVar = new zziv(zzc.size() + i);
            zzivVar.addAll((zzla) zzc);
            zzlf.zza(obj, j, zzivVar);
            return zzivVar;
        } else if (!(zzc instanceof zzka) || !(zzc instanceof zzio)) {
            return zzc;
        } else {
            zzio zzioVar = (zzio) zzc;
            if (zzioVar.zza()) {
                return zzc;
            }
            zzio zza2 = zzioVar.zza(zzc.size() + i);
            zzlf.zza(obj, j, zza2);
            return zza2;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzix
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
        zzlf.zza(obj, j, zzc);
    }

    private static <E> List<E> zzc(Object obj, long j) {
        return (List) zzlf.zzf(obj, j);
    }
}
