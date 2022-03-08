package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfg;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfe<T extends zzfg<T>> {
    private static final zzfe zzd = new zzfe(true);
    final zzhq<T, Object> zza;
    private boolean zzb;
    private boolean zzc;

    private zzfe() {
        this.zza = zzhq.zza(16);
    }

    private zzfe(boolean z) {
        this(zzhq.zza(0));
        zzb();
    }

    private zzfe(zzhq<T, Object> zzhqVar) {
        this.zza = zzhqVar;
        zzb();
    }

    public static <T extends zzfg<T>> zzfe<T> zza() {
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
        if (!(obj instanceof zzfe)) {
            return false;
        }
        return this.zza.equals(((zzfe) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        if (this.zzc) {
            return new zzgc(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Iterator<Map.Entry<T, Object>> zze() {
        if (this.zzc) {
            return new zzgc(this.zza.zze().iterator());
        }
        return this.zza.zze().iterator();
    }

    private final Object zza(T t) {
        Object obj = this.zza.get(t);
        if (!(obj instanceof zzgb)) {
            return obj;
        }
        zzgb zzgbVar = (zzgb) obj;
        return zzgb.zza();
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
        if (obj instanceof zzgb) {
            this.zzc = true;
        }
        this.zza.zza((zzhq<T, Object>) t, (T) obj);
    }

    private static void zza(zziu zziuVar, Object obj) {
        zzfr.zza(obj);
        boolean z = true;
        switch (zzfh.zza[zziuVar.zza().ordinal()]) {
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
                if (!(obj instanceof zzeg) && !(obj instanceof byte[])) {
                    z = false;
                    break;
                }
                break;
            case 8:
                if (!(obj instanceof Integer) && !(obj instanceof zzfq)) {
                    z = false;
                    break;
                }
                break;
            case 9:
                if (!(obj instanceof zzgw) && !(obj instanceof zzgb)) {
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

    private static <T extends zzfg<T>> boolean zza(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.zzc() == zzjb.MESSAGE) {
            if (key.zzd()) {
                for (zzgw zzgwVar : (List) entry.getValue()) {
                    if (!zzgwVar.g_()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof zzgw) {
                    if (!((zzgw) value).g_()) {
                        return false;
                    }
                } else if (value instanceof zzgb) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void zza(zzfe<T> zzfeVar) {
        for (int i = 0; i < zzfeVar.zza.zzc(); i++) {
            zzb(zzfeVar.zza.zzb(i));
        }
        for (Map.Entry<T, Object> entry : zzfeVar.zza.zzd()) {
            zzb(entry);
        }
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzhf) {
            return ((zzhf) obj).zza();
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
        zzgw zzgwVar;
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof zzgb) {
            zzgb zzgbVar = (zzgb) value;
            value = zzgb.zza();
        }
        if (key.zzd()) {
            Object zza = zza((zzfe<T>) key);
            if (zza == null) {
                zza = new ArrayList();
            }
            for (Object obj : (List) value) {
                ((List) zza).add(zza(obj));
            }
            this.zza.zza((zzhq<T, Object>) key, (T) zza);
        } else if (key.zzc() == zzjb.MESSAGE) {
            Object zza2 = zza((zzfe<T>) key);
            if (zza2 == null) {
                this.zza.zza((zzhq<T, Object>) key, (T) zza(value));
                return;
            }
            if (zza2 instanceof zzhf) {
                zzgwVar = key.zza((zzhf) zza2, (zzhf) value);
            } else {
                zzgwVar = key.zza(((zzgw) zza2).zzbq(), (zzgw) value).zzv();
            }
            this.zza.zza((zzhq<T, Object>) key, (T) zzgwVar);
        } else {
            this.zza.zza((zzhq<T, Object>) key, (T) zza(value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzev zzevVar, zziu zziuVar, int i, Object obj) throws IOException {
        if (zziuVar == zziu.GROUP) {
            zzgw zzgwVar = (zzgw) obj;
            zzfr.zza(zzgwVar);
            zzevVar.zza(i, 3);
            zzgwVar.zza(zzevVar);
            zzevVar.zza(i, 4);
            return;
        }
        zzevVar.zza(i, zziuVar.zzb());
        switch (zzfh.zzb[zziuVar.ordinal()]) {
            case 1:
                zzevVar.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzevVar.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzevVar.zza(((Long) obj).longValue());
                return;
            case 4:
                zzevVar.zza(((Long) obj).longValue());
                return;
            case 5:
                zzevVar.zza(((Integer) obj).intValue());
                return;
            case 6:
                zzevVar.zzc(((Long) obj).longValue());
                return;
            case 7:
                zzevVar.zzd(((Integer) obj).intValue());
                return;
            case 8:
                zzevVar.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzgw) obj).zza(zzevVar);
                return;
            case 10:
                zzevVar.zza((zzgw) obj);
                return;
            case 11:
                if (obj instanceof zzeg) {
                    zzevVar.zza((zzeg) obj);
                    return;
                } else {
                    zzevVar.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzeg) {
                    zzevVar.zza((zzeg) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzevVar.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzevVar.zzb(((Integer) obj).intValue());
                return;
            case 14:
                zzevVar.zzd(((Integer) obj).intValue());
                return;
            case 15:
                zzevVar.zzc(((Long) obj).longValue());
                return;
            case 16:
                zzevVar.zzc(((Integer) obj).intValue());
                return;
            case 17:
                zzevVar.zzb(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzfq) {
                    zzevVar.zza(((zzfq) obj).zza());
                    return;
                } else {
                    zzevVar.zza(((Integer) obj).intValue());
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
        if (key.zzc() != zzjb.MESSAGE || key.zzd() || key.zze()) {
            return zza((zzfg<?>) key, value);
        }
        if (value instanceof zzgb) {
            return zzev.zzb(entry.getKey().zza(), (zzgb) value);
        }
        return zzev.zzb(entry.getKey().zza(), (zzgw) value);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zziu zziuVar, int i, Object obj) {
        int zze = zzev.zze(i);
        if (zziuVar == zziu.GROUP) {
            zzfr.zza((zzgw) obj);
            zze <<= 1;
        }
        return zze + zzb(zziuVar, obj);
    }

    private static int zzb(zziu zziuVar, Object obj) {
        switch (zzfh.zzb[zziuVar.ordinal()]) {
            case 1:
                return zzev.zzb(((Double) obj).doubleValue());
            case 2:
                return zzev.zzb(((Float) obj).floatValue());
            case 3:
                return zzev.zzd(((Long) obj).longValue());
            case 4:
                return zzev.zze(((Long) obj).longValue());
            case 5:
                return zzev.zzf(((Integer) obj).intValue());
            case 6:
                return zzev.zzg(((Long) obj).longValue());
            case 7:
                return zzev.zzi(((Integer) obj).intValue());
            case 8:
                return zzev.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzev.zzc((zzgw) obj);
            case 10:
                if (obj instanceof zzgb) {
                    return zzev.zza((zzgb) obj);
                }
                return zzev.zzb((zzgw) obj);
            case 11:
                if (obj instanceof zzeg) {
                    return zzev.zzb((zzeg) obj);
                }
                return zzev.zzb((String) obj);
            case 12:
                if (obj instanceof zzeg) {
                    return zzev.zzb((zzeg) obj);
                }
                return zzev.zzb((byte[]) obj);
            case 13:
                return zzev.zzg(((Integer) obj).intValue());
            case 14:
                return zzev.zzj(((Integer) obj).intValue());
            case 15:
                return zzev.zzh(((Long) obj).longValue());
            case 16:
                return zzev.zzh(((Integer) obj).intValue());
            case 17:
                return zzev.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzfq) {
                    return zzev.zzk(((zzfq) obj).zza());
                }
                return zzev.zzk(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int zza(zzfg<?> zzfgVar, Object obj) {
        zziu zzb = zzfgVar.zzb();
        int zza = zzfgVar.zza();
        if (!zzfgVar.zzd()) {
            return zza(zzb, zza, obj);
        }
        int i = 0;
        if (zzfgVar.zze()) {
            for (Object obj2 : (List) obj) {
                i += zzb(zzb, obj2);
            }
            return zzev.zze(zza) + i + zzev.zzl(i);
        }
        for (Object obj3 : (List) obj) {
            i += zza(zzb, zza, obj3);
        }
        return i;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzfe zzfeVar = new zzfe();
        for (int i = 0; i < this.zza.zzc(); i++) {
            Map.Entry<T, Object> zzb = this.zza.zzb(i);
            zzfeVar.zzb((zzfe) zzb.getKey(), zzb.getValue());
        }
        for (Map.Entry<T, Object> entry : this.zza.zzd()) {
            zzfeVar.zzb((zzfe) entry.getKey(), entry.getValue());
        }
        zzfeVar.zzc = this.zzc;
        return zzfeVar;
    }
}
