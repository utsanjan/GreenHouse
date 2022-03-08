package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhx;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzhv<T extends zzhx<T>> {
    private static final zzhv zzd = new zzhv(true);
    final zzki<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzhv() {
        this.zza = zzki.zza(16);
    }

    private zzhv(boolean z) {
        this(zzki.zza(0));
        zzb();
    }

    private zzhv(zzki<T, Object> zzkiVar) {
        this.zza = zzkiVar;
        zzb();
    }

    public static <T extends zzhx<T>> zzhv<T> zza() {
        return zzd;
    }

    public final void zzb() {
        if (!this.zzb) {
            this.zza.zza();
            this.zzb = true;
        }
    }

    public final boolean zzc() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhv)) {
            return false;
        }
        return this.zza.equals(((zzhv) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzit(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzit(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzis)) {
            return obj;
        }
        zzis zzisVar = (zzis) obj;
        return zzis.zza();
    }

    private final void zzb(T t, Object obj) {
        if (!t.zzd()) {
            zza(t.zzb(), obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList2.get(i);
                i++;
                zza(t.zzb(), obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzis) {
            this.zzc = true;
        }
        this.zza.zza((zzki<T, Object>) t, (T) obj);
    }

    private static void zza(zzlm zzlmVar, Object obj) {
        zzii.zza(obj);
        boolean z = true;
        switch (zzhy.zza[zzlmVar.zza().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if (!(obj instanceof zzgv) && !(obj instanceof byte[])) {
                    z = false;
                    break;
                }
                break;
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzih)) {
                    z = false;
                    break;
                }
                break;
            case 9:
                if (!(obj instanceof zzjn) && !(obj instanceof zzis)) {
                    z = false;
                    break;
                }
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public final boolean zzf() {
        for (int i = 0; i < this.zza.zzc(); i++) {
            if (!zza((Map.Entry) this.zza.zzb(i))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            if (!zza((Map.Entry) entry)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends zzhx<T>> boolean zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzlt.MESSAGE) {
            if (key.zzd()) {
                for (zzjn zzjnVar : (List) entry.getValue()) {
                    if (!zzjnVar.b_()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzjn) {
                    if (!((zzjn) value).b_()) {
                        return false;
                    }
                } else if (value instanceof zzis) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzhv<T> zzhvVar) {
        for (int i = 0; i < zzhvVar.zza.zzc(); i++) {
            zzb(zzhvVar.zza.zzb(i));
        }
        for (Map.Entry<T, Object> entry : zzhvVar.zza.zzd()) {
            zzb(entry);
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzjt) {
            return ((zzjt) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        zzjn zzjnVar;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzis) {
            zzis zzisVar = (zzis) value;
            value = zzis.zza();
        }
        if (key.zzd()) {
            Object zza = zza((zzhv<T>) key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zza(obj));
            }
            this.zza.zza((zzki<T, Object>) key, (T) zza);
        } else if (key.zzc() == zzlt.MESSAGE) {
            Object zza2 = zza((zzhv<T>) key);
            if (zza2 == null) {
                this.zza.zza((zzki<T, Object>) key, (T) zza(value));
                return;
            }
            if (zza2 instanceof zzjt) {
                zzjnVar = key.zza((zzjt) zza2, (zzjt) value);
            } else {
                zzjnVar = key.zza(((zzjn) zza2).zzad(), (zzjn) value).zzg();
            }
            this.zza.zza((zzki<T, Object>) key, (T) zzjnVar);
        } else {
            this.zza.zza((zzki<T, Object>) key, (T) zza(value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzhm zzhmVar, zzlm zzlmVar, int i, Object obj) throws IOException {
        if (zzlmVar == zzlm.GROUP) {
            zzjn zzjnVar = (zzjn) obj;
            zzii.zza(zzjnVar);
            zzhmVar.zza(i, 3);
            zzjnVar.zza(zzhmVar);
            zzhmVar.zza(i, 4);
            return;
        }
        zzhmVar.zza(i, zzlmVar.zzb());
        switch (zzhy.zzb[zzlmVar.ordinal()]) {
            case 1:
                zzhmVar.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzhmVar.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzhmVar.zza(((Long) obj).longValue());
                return;
            case 4:
                zzhmVar.zza(((Long) obj).longValue());
                return;
            case 5:
                zzhmVar.zza(((Integer) obj).intValue());
                return;
            case 6:
                zzhmVar.zzc(((Long) obj).longValue());
                return;
            case 7:
                zzhmVar.zzd(((Integer) obj).intValue());
                return;
            case 8:
                zzhmVar.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzjn) obj).zza(zzhmVar);
                return;
            case 10:
                zzhmVar.zza((zzjn) obj);
                return;
            case 11:
                if (obj instanceof zzgv) {
                    zzhmVar.zza((zzgv) obj);
                    return;
                } else {
                    zzhmVar.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzgv) {
                    zzhmVar.zza((zzgv) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzhmVar.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzhmVar.zzb(((Integer) obj).intValue());
                return;
            case 14:
                zzhmVar.zzd(((Integer) obj).intValue());
                return;
            case 15:
                zzhmVar.zzc(((Long) obj).longValue());
                return;
            case 16:
                zzhmVar.zzc(((Integer) obj).intValue());
                return;
            case 17:
                zzhmVar.zzb(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzih) {
                    zzhmVar.zza(((zzih) obj).zza());
                    return;
                } else {
                    zzhmVar.zza(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    public final int zzg() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzc(); i2++) {
            i += zzc(this.zza.zzb(i2));
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            i += zzc(entry);
        }
        return i;
    }

    private static int zzc(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.zzc() != zzlt.MESSAGE || key.zzd() || key.zze()) {
            return zza((zzhx<?>) key, value);
        }
        if (value instanceof zzis) {
            return zzhm.zzb(entry.getKey().zza(), (zzis) value);
        }
        return zzhm.zzb(entry.getKey().zza(), (zzjn) value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzlm zzlmVar, int i, Object obj) {
        int zze = zzhm.zze(i);
        if (zzlmVar == zzlm.GROUP) {
            zzii.zza((zzjn) obj);
            zze <<= 1;
        }
        return zze + zzb(zzlmVar, obj);
    }

    private static int zzb(zzlm zzlmVar, Object obj) {
        switch (zzhy.zzb[zzlmVar.ordinal()]) {
            case 1:
                return zzhm.zzb(((Double) obj).doubleValue());
            case 2:
                return zzhm.zzb(((Float) obj).floatValue());
            case 3:
                return zzhm.zzd(((Long) obj).longValue());
            case 4:
                return zzhm.zze(((Long) obj).longValue());
            case 5:
                return zzhm.zzf(((Integer) obj).intValue());
            case 6:
                return zzhm.zzg(((Long) obj).longValue());
            case 7:
                return zzhm.zzi(((Integer) obj).intValue());
            case 8:
                return zzhm.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzhm.zzc((zzjn) obj);
            case 10:
                if (obj instanceof zzis) {
                    return zzhm.zza((zzis) obj);
                }
                return zzhm.zzb((zzjn) obj);
            case 11:
                if (obj instanceof zzgv) {
                    return zzhm.zzb((zzgv) obj);
                }
                return zzhm.zzb((String) obj);
            case 12:
                if (obj instanceof zzgv) {
                    return zzhm.zzb((zzgv) obj);
                }
                return zzhm.zzb((byte[]) obj);
            case 13:
                return zzhm.zzg(((Integer) obj).intValue());
            case 14:
                return zzhm.zzj(((Integer) obj).intValue());
            case 15:
                return zzhm.zzh(((Long) obj).longValue());
            case 16:
                return zzhm.zzh(((Integer) obj).intValue());
            case 17:
                return zzhm.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzih) {
                    return zzhm.zzk(((zzih) obj).zza());
                }
                return zzhm.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzhx<?> zzhxVar, Object obj) {
        zzlm zzb = zzhxVar.zzb();
        int zza = zzhxVar.zza();
        if (!zzhxVar.zzd()) {
            return zza(zzb, zza, obj);
        }
        int i = 0;
        if (zzhxVar.zze()) {
            for (Object obj2 : (List) obj) {
                i += zzb(zzb, obj2);
            }
            return zzhm.zze(zza) + i + zzhm.zzl(i);
        }
        for (Object obj3 : (List) obj) {
            i += zza(zzb, zza, obj3);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzhv zzhvVar = new zzhv();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<T, Object> zzb = this.zza.zzb(i);
            zzhvVar.zzb((zzhv) zzb.getKey(), zzb.getValue());
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            zzhvVar.zzb((zzhv) entry.getKey(), entry.getValue());
        }
        zzhvVar.zzc = this.zzc;
        return zzhvVar;
    }
}
