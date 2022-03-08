package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzhr {
    private static final Class<?> zza = zzd();
    private static final zzih<?, ?> zzb = zza(false);
    private static final zzih<?, ?> zzc = zza(true);
    private static final zzih<?, ?> zzd = new zzij();

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzfo.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzja zzjaVar, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzja zzjaVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzeg> list, zzja zzjaVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzja zzjaVar, zzhp zzhpVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zza(i, list, zzhpVar);
        }
    }

    public static void zzb(int i, List<?> list, zzja zzjaVar, zzhp zzhpVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzjaVar.zzb(i, list, zzhpVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgk) {
            zzgk zzgkVar = (zzgk) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zzd(zzgkVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgk) {
            zzgk zzgkVar = (zzgk) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zze(zzgkVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgk) {
            zzgk zzgkVar = (zzgk) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zzf(zzgkVar.zzb(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfp) {
            zzfp zzfpVar = (zzfp) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zzk(zzfpVar.zzc(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zzk(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfp) {
            zzfp zzfpVar = (zzfp) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zzf(zzfpVar.zzc(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zzf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfp) {
            zzfp zzfpVar = (zzfp) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zzg(zzfpVar.zzc(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zzg(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfp) {
            zzfp zzfpVar = (zzfp) list;
            i = 0;
            while (i2 < size) {
                i += zzev.zzh(zzfpVar.zzc(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += zzev.zzh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzev.zze(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(List<?> list) {
        return list.size() << 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzev.zzi(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List<?> list) {
        return list.size() << 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzev.zzg(i, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(List<?> list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzev.zzb(i, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zze = zzev.zze(i) * size;
        if (list instanceof zzgh) {
            zzgh zzghVar = (zzgh) list;
            while (i4 < size) {
                Object zzb2 = zzghVar.zzb(i4);
                if (zzb2 instanceof zzeg) {
                    i3 = zzev.zzb((zzeg) zzb2);
                } else {
                    i3 = zzev.zzb((String) zzb2);
                }
                zze += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzeg) {
                    i2 = zzev.zzb((zzeg) obj);
                } else {
                    i2 = zzev.zzb((String) obj);
                }
                zze += i2;
                i4++;
            }
        }
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, Object obj, zzhp zzhpVar) {
        if (obj instanceof zzgf) {
            return zzev.zza(i, (zzgf) obj);
        }
        return zzev.zzb(i, (zzgw) obj, zzhpVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, List<?> list, zzhp zzhpVar) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzev.zze(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzgf) {
                i2 = zzev.zza((zzgf) obj);
            } else {
                i2 = zzev.zza((zzgw) obj, zzhpVar);
            }
            zze += i2;
        }
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, List<zzeg> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = size * zzev.zze(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zze += zzev.zzb(list.get(i2));
        }
        return zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, List<zzgw> list, zzhp zzhpVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzev.zzc(i, list.get(i3), zzhpVar);
        }
        return i2;
    }

    public static zzih<?, ?> zza() {
        return zzb;
    }

    public static zzih<?, ?> zzb() {
        return zzc;
    }

    public static zzih<?, ?> zzc() {
        return zzd;
    }

    private static zzih<?, ?> zza(boolean z) {
        try {
            Class<?> zze = zze();
            if (zze == null) {
                return null;
            }
            return (zzih) zze.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zza(zzgt zzgtVar, T t, T t2, long j) {
        zzin.zza(t, j, zzgtVar.zza(zzin.zzf(t, j), zzin.zzf(t2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzfg<FT>> void zza(zzfd<FT> zzfdVar, T t, T t2) {
        zzfe<FT> zza2 = zzfdVar.zza(t2);
        if (!zza2.zza.isEmpty()) {
            zzfdVar.zzb(t).zza(zza2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zza(zzih<UT, UB> zzihVar, T t, T t2) {
        zzihVar.zza(t, zzihVar.zzc(zzihVar.zzb(t), zzihVar.zzb(t2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int i, List<Integer> list, zzfs zzfsVar, UB ub, zzih<UT, UB> zzihVar) {
        if (zzfsVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzfsVar.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zza(i, intValue, ub, zzihVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzfsVar.zza(intValue2)) {
                    ub = (UB) zza(i, intValue2, ub, zzihVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int i, int i2, UB ub, zzih<UT, UB> zzihVar) {
        if (ub == null) {
            ub = zzihVar.zza();
        }
        zzihVar.zza((zzih<UT, UB>) ub, i, i2);
        return ub;
    }
}
