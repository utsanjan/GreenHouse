package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzha<T> implements zzhp<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzin.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzgw zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzhe zzo;
    private final zzgg zzp;
    private final zzih<?, ?> zzq;
    private final zzfd<?> zzr;
    private final zzgt zzs;

    private zzha(int[] iArr, Object[] objArr, int i, int i2, zzgw zzgwVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzhe zzheVar, zzgg zzggVar, zzih<?, ?> zzihVar, zzfd<?> zzfdVar, zzgt zzgtVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzgwVar instanceof zzfo;
        this.zzj = z;
        this.zzh = zzfdVar != null && zzfdVar.zza(zzgwVar);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzheVar;
        this.zzp = zzggVar;
        this.zzq = zzihVar;
        this.zzr = zzfdVar;
        this.zzg = zzgwVar;
        this.zzs = zzgtVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03af A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> com.google.android.gms.internal.measurement.zzha<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.measurement.zzgu r34, com.google.android.gms.internal.measurement.zzhe r35, com.google.android.gms.internal.measurement.zzgg r36, com.google.android.gms.internal.measurement.zzih<?, ?> r37, com.google.android.gms.internal.measurement.zzfd<?> r38, com.google.android.gms.internal.measurement.zzgt r39) {
        /*
            Method dump skipped, instructions count: 1074
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzha.zza(java.lang.Class, com.google.android.gms.internal.measurement.zzgu, com.google.android.gms.internal.measurement.zzhe, com.google.android.gms.internal.measurement.zzgg, com.google.android.gms.internal.measurement.zzih, com.google.android.gms.internal.measurement.zzfd, com.google.android.gms.internal.measurement.zzgt):com.google.android.gms.internal.measurement.zzha");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final T zza() {
        return (T) this.zzo.zza(this.zzg);
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final boolean zza(T t, T t2) {
        int length = this.zzc.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                int zzd = zzd(i);
                long j = zzd & 1048575;
                switch ((zzd & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzin.zze(t, j)) != Double.doubleToLongBits(zzin.zze(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzin.zzd(t, j)) != Float.floatToIntBits(zzin.zzd(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zzin.zzb(t, j) != zzin.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zzin.zzb(t, j) != zzin.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zzin.zza(t, j) != zzin.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zzin.zzb(t, j) != zzin.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zzin.zza(t, j) != zzin.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zzin.zzc(t, j) != zzin.zzc(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zzin.zza(t, j) != zzin.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zzin.zza(t, j) != zzin.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zzin.zza(t, j) != zzin.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zzin.zzb(t, j) != zzin.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zzin.zza(t, j) != zzin.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zzin.zzb(t, j) != zzin.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        z = zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j));
                        break;
                    case 50:
                        z = zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long zze = zze(i) & 1048575;
                        if (zzin.zza(t, zze) != zzin.zza(t2, zze) || !zzhr.zza(zzin.zzf(t, j), zzin.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                }
                if (!z) {
                    return false;
                }
                i += 3;
            } else if (!this.zzq.zzb(t).equals(this.zzq.zzb(t2))) {
                return false;
            } else {
                if (this.zzh) {
                    return this.zzr.zza(t).equals(this.zzr.zza(t2));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final int zza(T t) {
        int length = this.zzc.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzd = zzd(i2);
            int i3 = this.zzc[i2];
            long j = 1048575 & zzd;
            int i4 = 37;
            switch ((zzd & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzfr.zza(Double.doubleToLongBits(zzin.zze(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzin.zzd(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzfr.zza(zzin.zzb(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzfr.zza(zzin.zzb(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzin.zza(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzfr.zza(zzin.zzb(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzin.zza(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzfr.zza(zzin.zzc(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzin.zzf(t, j)).hashCode();
                    break;
                case 9:
                    Object zzf = zzin.zzf(t, j);
                    if (zzf != null) {
                        i4 = zzf.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzin.zzf(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzin.zza(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzin.zza(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzin.zza(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzfr.zza(zzin.zzb(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzin.zza(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzfr.zza(zzin.zzb(t, j));
                    break;
                case 17:
                    Object zzf2 = zzin.zzf(t, j);
                    if (zzf2 != null) {
                        i4 = zzf2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzin.zzf(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzin.zzf(t, j).hashCode();
                    break;
                case 51:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(Double.doubleToLongBits(zzb(t, j)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + Float.floatToIntBits(zzc(t, j));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(zzf(t, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + ((String) zzin.zzf(t, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzin.zzf(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzin.zzf(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzfr.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzha<T>) t, i3, i2)) {
                        i = (i * 53) + zzin.zzf(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzq.zzb(t).hashCode();
        if (this.zzh) {
            return (hashCode * 53) + this.zzr.zza(t).hashCode();
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zzb(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzd = zzd(i);
                long j = 1048575 & zzd;
                int i2 = this.zzc[i];
                switch ((zzd & 267386880) >>> 20) {
                    case 0:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza(t, j, zzin.zze(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zzd(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zzb(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zzb(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zza(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zzb(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zza(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza(t, j, zzin.zzc(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza(t, j, zzin.zzf(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza(t, j, zzin.zzf(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zza(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zza(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zza(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zzb(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zza(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzha<T>) t2, i)) {
                            zzin.zza((Object) t, j, zzin.zzb(t2, j));
                            zzb((zzha<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzp.zza(t, t2, j);
                        break;
                    case 50:
                        zzhr.zza(this.zzs, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (zza((zzha<T>) t2, i2, i)) {
                            zzin.zza(t, j, zzin.zzf(t2, j));
                            zzb((zzha<T>) t, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (zza((zzha<T>) t2, i2, i)) {
                            zzin.zza(t, j, zzin.zzf(t2, j));
                            zzb((zzha<T>) t, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzhr.zza(this.zzq, t, t2);
            if (this.zzh) {
                zzhr.zza(this.zzr, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    private final void zza(T t, T t2, int i) {
        long zzd = zzd(i) & 1048575;
        if (zza((zzha<T>) t2, i)) {
            Object zzf = zzin.zzf(t, zzd);
            Object zzf2 = zzin.zzf(t2, zzd);
            if (zzf != null && zzf2 != null) {
                zzin.zza(t, zzd, zzfr.zza(zzf, zzf2));
                zzb((zzha<T>) t, i);
            } else if (zzf2 != null) {
                zzin.zza(t, zzd, zzf2);
                zzb((zzha<T>) t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzd = zzd(i);
        int i2 = this.zzc[i];
        long j = zzd & 1048575;
        if (zza((zzha<T>) t2, i2, i)) {
            Object zzf = zzin.zzf(t, j);
            Object zzf2 = zzin.zzf(t2, j);
            if (zzf != null && zzf2 != null) {
                zzin.zza(t, j, zzfr.zza(zzf, zzf2));
                zzb((zzha<T>) t, i2, i);
            } else if (zzf2 != null) {
                zzin.zza(t, j, zzf2);
                zzb((zzha<T>) t, i2, i);
            }
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final int zzb(T t) {
        int i;
        int i2;
        long j;
        int i3;
        int i4 = 267386880;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.zzc.length) {
                int zzd = zzd(i5);
                int i7 = (zzd & i4) >>> 20;
                int i8 = this.zzc[i5];
                long j2 = zzd & 1048575;
                if (i7 < zzfj.DOUBLE_LIST_PACKED.zza() || i7 > zzfj.SINT64_LIST_PACKED.zza()) {
                    i3 = 0;
                } else {
                    i3 = this.zzc[i5 + 2] & 1048575;
                }
                switch (i7) {
                    case 0:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzb(i8, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzb(i8, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzd(i8, zzin.zzb(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zze(i8, zzin.zzb(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzf(i8, zzin.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzg(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzi(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzb(i8, true);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzha<T>) t, i5)) {
                            Object zzf = zzin.zzf(t, j2);
                            if (zzf instanceof zzeg) {
                                i6 += zzev.zzc(i8, (zzeg) zzf);
                                break;
                            } else {
                                i6 += zzev.zzb(i8, (String) zzf);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzhr.zza(i8, zzin.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzc(i8, (zzeg) zzin.zzf(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzg(i8, zzin.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzk(i8, zzin.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzj(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzh(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzh(i8, zzin.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzf(i8, zzin.zzb(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza((zzha<T>) t, i5)) {
                            i6 += zzev.zzc(i8, (zzgw) zzin.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        i6 += zzhr.zzi(i8, zza(t, j2), false);
                        break;
                    case 19:
                        i6 += zzhr.zzh(i8, zza(t, j2), false);
                        break;
                    case 20:
                        i6 += zzhr.zza(i8, (List<Long>) zza(t, j2), false);
                        break;
                    case 21:
                        i6 += zzhr.zzb(i8, (List<Long>) zza(t, j2), false);
                        break;
                    case 22:
                        i6 += zzhr.zze(i8, zza(t, j2), false);
                        break;
                    case 23:
                        i6 += zzhr.zzi(i8, zza(t, j2), false);
                        break;
                    case 24:
                        i6 += zzhr.zzh(i8, zza(t, j2), false);
                        break;
                    case 25:
                        i6 += zzhr.zzj(i8, zza(t, j2), false);
                        break;
                    case 26:
                        i6 += zzhr.zza(i8, zza(t, j2));
                        break;
                    case 27:
                        i6 += zzhr.zza(i8, zza(t, j2), zza(i5));
                        break;
                    case 28:
                        i6 += zzhr.zzb(i8, zza(t, j2));
                        break;
                    case 29:
                        i6 += zzhr.zzf(i8, zza(t, j2), false);
                        break;
                    case 30:
                        i6 += zzhr.zzd(i8, zza(t, j2), false);
                        break;
                    case 31:
                        i6 += zzhr.zzh(i8, zza(t, j2), false);
                        break;
                    case 32:
                        i6 += zzhr.zzi(i8, zza(t, j2), false);
                        break;
                    case 33:
                        i6 += zzhr.zzg(i8, zza(t, j2), false);
                        break;
                    case 34:
                        i6 += zzhr.zzc(i8, zza(t, j2), false);
                        break;
                    case 35:
                        int zzi = zzhr.zzi((List) unsafe.getObject(t, j2));
                        if (zzi <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzi);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzi) + zzi;
                            break;
                        }
                    case 36:
                        int zzh = zzhr.zzh((List) unsafe.getObject(t, j2));
                        if (zzh <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzh);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzh) + zzh;
                            break;
                        }
                    case 37:
                        int zza2 = zzhr.zza((List) unsafe.getObject(t, j2));
                        if (zza2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zza2);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zza2) + zza2;
                            break;
                        }
                    case 38:
                        int zzb2 = zzhr.zzb((List) unsafe.getObject(t, j2));
                        if (zzb2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzb2);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzb2) + zzb2;
                            break;
                        }
                    case 39:
                        int zze = zzhr.zze((List) unsafe.getObject(t, j2));
                        if (zze <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zze);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zze) + zze;
                            break;
                        }
                    case 40:
                        int zzi2 = zzhr.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzi2);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzi2) + zzi2;
                            break;
                        }
                    case 41:
                        int zzh2 = zzhr.zzh((List) unsafe.getObject(t, j2));
                        if (zzh2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzh2);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzh2) + zzh2;
                            break;
                        }
                    case 42:
                        int zzj = zzhr.zzj((List) unsafe.getObject(t, j2));
                        if (zzj <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzj);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzj) + zzj;
                            break;
                        }
                    case 43:
                        int zzf2 = zzhr.zzf((List) unsafe.getObject(t, j2));
                        if (zzf2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzf2);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzf2) + zzf2;
                            break;
                        }
                    case 44:
                        int zzd2 = zzhr.zzd((List) unsafe.getObject(t, j2));
                        if (zzd2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzd2);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzd2) + zzd2;
                            break;
                        }
                    case 45:
                        int zzh3 = zzhr.zzh((List) unsafe.getObject(t, j2));
                        if (zzh3 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzh3);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzh3) + zzh3;
                            break;
                        }
                    case 46:
                        int zzi3 = zzhr.zzi((List) unsafe.getObject(t, j2));
                        if (zzi3 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzi3);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzi3) + zzi3;
                            break;
                        }
                    case 47:
                        int zzg = zzhr.zzg((List) unsafe.getObject(t, j2));
                        if (zzg <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzg);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzg) + zzg;
                            break;
                        }
                    case 48:
                        int zzc = zzhr.zzc((List) unsafe.getObject(t, j2));
                        if (zzc <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzc);
                            }
                            i6 += zzev.zze(i8) + zzev.zzg(zzc) + zzc;
                            break;
                        }
                    case 49:
                        i6 += zzhr.zzb(i8, (List<zzgw>) zza(t, j2), zza(i5));
                        break;
                    case 50:
                        i6 += this.zzs.zza(i8, zzin.zzf(t, j2), zzb(i5));
                        break;
                    case 51:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzb(i8, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzb(i8, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzd(i8, zze(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zze(i8, zze(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzf(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzg(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzi(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzb(i8, true);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzha<T>) t, i8, i5)) {
                            Object zzf3 = zzin.zzf(t, j2);
                            if (zzf3 instanceof zzeg) {
                                i6 += zzev.zzc(i8, (zzeg) zzf3);
                                break;
                            } else {
                                i6 += zzev.zzb(i8, (String) zzf3);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzhr.zza(i8, zzin.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzc(i8, (zzeg) zzin.zzf(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzg(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzk(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzj(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzh(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzh(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzf(i8, zze(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzha<T>) t, i8, i5)) {
                            i6 += zzev.zzc(i8, (zzgw) zzin.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                }
                i5 += 3;
                i4 = 267386880;
            }
            return i6 + zza((zzih) this.zzq, (Object) t);
        }
        Unsafe unsafe2 = zzb;
        int i9 = 0;
        int i10 = 1048575;
        int i11 = 0;
        for (int i12 = 0; i12 < this.zzc.length; i12 += 3) {
            int zzd3 = zzd(i12);
            int[] iArr = this.zzc;
            int i13 = iArr[i12];
            int i14 = (zzd3 & 267386880) >>> 20;
            if (i14 <= 17) {
                int i15 = iArr[i12 + 2];
                int i16 = i15 & 1048575;
                i = 1 << (i15 >>> 20);
                if (i16 != i10) {
                    i11 = unsafe2.getInt(t, i16);
                    i10 = i16;
                }
                i2 = i15;
            } else if (!this.zzk || i14 < zzfj.DOUBLE_LIST_PACKED.zza() || i14 > zzfj.SINT64_LIST_PACKED.zza()) {
                i2 = 0;
                i = 0;
            } else {
                i2 = this.zzc[i12 + 2] & 1048575;
                i = 0;
            }
            long j3 = zzd3 & 1048575;
            switch (i14) {
                case 0:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzb(i13, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzb(i13, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzd(i13, unsafe2.getLong(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzev.zze(i13, unsafe2.getLong(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzf(i13, unsafe2.getInt(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i11 & i) != 0) {
                        j = 0;
                        i9 += zzev.zzg(i13, 0L);
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 6:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzi(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzb(i13, true);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i11 & i) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzeg) {
                            i9 += zzev.zzc(i13, (zzeg) object);
                            j = 0;
                            break;
                        } else {
                            i9 += zzev.zzb(i13, (String) object);
                            j = 0;
                            break;
                        }
                    } else {
                        j = 0;
                        break;
                    }
                case 9:
                    if ((i11 & i) != 0) {
                        i9 += zzhr.zza(i13, unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzc(i13, (zzeg) unsafe2.getObject(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzg(i13, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzk(i13, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzj(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzh(i13, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzh(i13, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzf(i13, unsafe2.getLong(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i11 & i) != 0) {
                        i9 += zzev.zzc(i13, (zzgw) unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 18:
                    i9 += zzhr.zzi(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 19:
                    i9 += zzhr.zzh(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 20:
                    i9 += zzhr.zza(i13, (List<Long>) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 21:
                    i9 += zzhr.zzb(i13, (List<Long>) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 22:
                    i9 += zzhr.zze(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 23:
                    i9 += zzhr.zzi(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 24:
                    i9 += zzhr.zzh(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 25:
                    i9 += zzhr.zzj(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 26:
                    i9 += zzhr.zza(i13, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 27:
                    i9 += zzhr.zza(i13, (List<?>) unsafe2.getObject(t, j3), zza(i12));
                    j = 0;
                    break;
                case 28:
                    i9 += zzhr.zzb(i13, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 29:
                    i9 += zzhr.zzf(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 30:
                    i9 += zzhr.zzd(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 31:
                    i9 += zzhr.zzh(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 32:
                    i9 += zzhr.zzi(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 33:
                    i9 += zzhr.zzg(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 34:
                    i9 += zzhr.zzc(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzi4 = zzhr.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzi4);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzi4) + zzi4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 36:
                    int zzh4 = zzhr.zzh((List) unsafe2.getObject(t, j3));
                    if (zzh4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzh4);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzh4) + zzh4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 37:
                    int zza3 = zzhr.zza((List) unsafe2.getObject(t, j3));
                    if (zza3 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zza3);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zza3) + zza3;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 38:
                    int zzb3 = zzhr.zzb((List) unsafe2.getObject(t, j3));
                    if (zzb3 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzb3);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzb3) + zzb3;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 39:
                    int zze2 = zzhr.zze((List) unsafe2.getObject(t, j3));
                    if (zze2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zze2);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zze2) + zze2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 40:
                    int zzi5 = zzhr.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi5 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzi5);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzi5) + zzi5;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 41:
                    int zzh5 = zzhr.zzh((List) unsafe2.getObject(t, j3));
                    if (zzh5 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzh5);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzh5) + zzh5;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 42:
                    int zzj2 = zzhr.zzj((List) unsafe2.getObject(t, j3));
                    if (zzj2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzj2);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzj2) + zzj2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 43:
                    int zzf4 = zzhr.zzf((List) unsafe2.getObject(t, j3));
                    if (zzf4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzf4);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzf4) + zzf4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 44:
                    int zzd4 = zzhr.zzd((List) unsafe2.getObject(t, j3));
                    if (zzd4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzd4);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzd4) + zzd4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 45:
                    int zzh6 = zzhr.zzh((List) unsafe2.getObject(t, j3));
                    if (zzh6 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzh6);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzh6) + zzh6;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 46:
                    int zzi6 = zzhr.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi6 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzi6);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzi6) + zzi6;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 47:
                    int zzg2 = zzhr.zzg((List) unsafe2.getObject(t, j3));
                    if (zzg2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzg2);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzg2) + zzg2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 48:
                    int zzc2 = zzhr.zzc((List) unsafe2.getObject(t, j3));
                    if (zzc2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzc2);
                        }
                        i9 += zzev.zze(i13) + zzev.zzg(zzc2) + zzc2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 49:
                    i9 += zzhr.zzb(i13, (List) unsafe2.getObject(t, j3), zza(i12));
                    j = 0;
                    break;
                case 50:
                    i9 += this.zzs.zza(i13, unsafe2.getObject(t, j3), zzb(i12));
                    j = 0;
                    break;
                case 51:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzb(i13, 0.0d);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 52:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzb(i13, 0.0f);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 53:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzd(i13, zze(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 54:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zze(i13, zze(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 55:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzf(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 56:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzg(i13, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 57:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzi(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 58:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzb(i13, true);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 59:
                    if (zza((zzha<T>) t, i13, i12)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzeg) {
                            i9 += zzev.zzc(i13, (zzeg) object2);
                            j = 0;
                            break;
                        } else {
                            i9 += zzev.zzb(i13, (String) object2);
                            j = 0;
                            break;
                        }
                    } else {
                        j = 0;
                        break;
                    }
                case 60:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzhr.zza(i13, unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 61:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzc(i13, (zzeg) unsafe2.getObject(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 62:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzg(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 63:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzk(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 64:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzj(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 65:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzh(i13, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 66:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzh(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 67:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzf(i13, zze(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 68:
                    if (zza((zzha<T>) t, i13, i12)) {
                        i9 += zzev.zzc(i13, (zzgw) unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                default:
                    j = 0;
                    break;
            }
        }
        int i17 = 0;
        int zza4 = i9 + zza((zzih) this.zzq, (Object) t);
        if (!this.zzh) {
            return zza4;
        }
        zzfe<?> zza5 = this.zzr.zza(t);
        for (int i18 = 0; i18 < zza5.zza.zzc(); i18++) {
            Map.Entry<?, Object> zzb4 = zza5.zza.zzb(i18);
            i17 += zzfe.zza((zzfg) zzb4.getKey(), zzb4.getValue());
        }
        for (Map.Entry<?, Object> entry : zza5.zza.zzd()) {
            i17 += zzfe.zza((zzfg) entry.getKey(), entry.getValue());
        }
        return zza4 + i17;
    }

    private static <UT, UB> int zza(zzih<UT, UB> zzihVar, T t) {
        return zzihVar.zzf(zzihVar.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzin.zzf(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0b5e  */
    @Override // com.google.android.gms.internal.measurement.zzhp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r14, com.google.android.gms.internal.measurement.zzja r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzha.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzja):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0559  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(T r18, com.google.android.gms.internal.measurement.zzja r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzha.zzb(java.lang.Object, com.google.android.gms.internal.measurement.zzja):void");
    }

    private final <K, V> void zza(zzja zzjaVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzjaVar.zza(i, this.zzs.zzb(zzb(i2)), this.zzs.zzc(obj));
        }
    }

    private static <UT, UB> void zza(zzih<UT, UB> zzihVar, T t, zzja zzjaVar) throws IOException {
        zzihVar.zza((zzih<UT, UB>) zzihVar.zzb(t), zzjaVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zza(T t, zzhm zzhmVar, zzfb zzfbVar) throws IOException {
        Object obj;
        zzfe<?> zzfeVar;
        if (zzfbVar != null) {
            zzih zzihVar = this.zzq;
            zzfd<?> zzfdVar = this.zzr;
            zzfe<?> zzfeVar2 = null;
            Object obj2 = null;
            while (true) {
                try {
                    int zza2 = zzhmVar.zza();
                    int zzg = zzg(zza2);
                    if (zzg >= 0) {
                        int zzd = zzd(zzg);
                        switch ((267386880 & zzd) >>> 20) {
                            case 0:
                                zzin.zza(t, zzd & 1048575, zzhmVar.zzd());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 1:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zze());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 2:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzg());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 3:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzf());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 4:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzh());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 5:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzi());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 6:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzj());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 7:
                                zzin.zza(t, zzd & 1048575, zzhmVar.zzk());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 8:
                                zza(t, zzd, zzhmVar);
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 9:
                                if (zza((zzha<T>) t, zzg)) {
                                    long j = zzd & 1048575;
                                    zzin.zza(t, j, zzfr.zza(zzin.zzf(t, j), zzhmVar.zza(zza(zzg), zzfbVar)));
                                    break;
                                } else {
                                    zzin.zza(t, zzd & 1048575, zzhmVar.zza(zza(zzg), zzfbVar));
                                    zzb((zzha<T>) t, zzg);
                                    break;
                                }
                            case 10:
                                zzin.zza(t, zzd & 1048575, zzhmVar.zzn());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 11:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzo());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 12:
                                int zzp = zzhmVar.zzp();
                                zzfs zzc = zzc(zzg);
                                if (zzc != null && !zzc.zza(zzp)) {
                                    obj2 = zzhr.zza(zza2, zzp, obj2, zzihVar);
                                    break;
                                }
                                zzin.zza((Object) t, zzd & 1048575, zzp);
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 13:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzq());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 14:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzr());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 15:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzs());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 16:
                                zzin.zza((Object) t, zzd & 1048575, zzhmVar.zzt());
                                zzb((zzha<T>) t, zzg);
                                break;
                            case 17:
                                if (zza((zzha<T>) t, zzg)) {
                                    long j2 = zzd & 1048575;
                                    zzin.zza(t, j2, zzfr.zza(zzin.zzf(t, j2), zzhmVar.zzb(zza(zzg), zzfbVar)));
                                    break;
                                } else {
                                    zzin.zza(t, zzd & 1048575, zzhmVar.zzb(zza(zzg), zzfbVar));
                                    zzb((zzha<T>) t, zzg);
                                    break;
                                }
                            case 18:
                                zzhmVar.zza(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 19:
                                zzhmVar.zzb(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 20:
                                zzhmVar.zzd(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 21:
                                zzhmVar.zzc(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 22:
                                zzhmVar.zze(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 23:
                                zzhmVar.zzf(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 24:
                                zzhmVar.zzg(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 25:
                                zzhmVar.zzh(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 26:
                                if (zzf(zzd)) {
                                    zzhmVar.zzj(this.zzp.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    zzhmVar.zzi(this.zzp.zza(t, zzd & 1048575));
                                    break;
                                }
                            case 27:
                                zzhmVar.zza(this.zzp.zza(t, zzd & 1048575), zza(zzg), zzfbVar);
                                break;
                            case 28:
                                zzhmVar.zzk(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 29:
                                zzhmVar.zzl(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 30:
                                List<Integer> zza3 = this.zzp.zza(t, zzd & 1048575);
                                zzhmVar.zzm(zza3);
                                obj2 = zzhr.zza(zza2, zza3, zzc(zzg), obj2, zzihVar);
                                break;
                            case 31:
                                zzhmVar.zzn(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 32:
                                zzhmVar.zzo(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 33:
                                zzhmVar.zzp(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 34:
                                zzhmVar.zzq(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 35:
                                zzhmVar.zza(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 36:
                                zzhmVar.zzb(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 37:
                                zzhmVar.zzd(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 38:
                                zzhmVar.zzc(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 39:
                                zzhmVar.zze(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 40:
                                zzhmVar.zzf(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 41:
                                zzhmVar.zzg(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 42:
                                zzhmVar.zzh(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 43:
                                zzhmVar.zzl(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 44:
                                List<Integer> zza4 = this.zzp.zza(t, zzd & 1048575);
                                zzhmVar.zzm(zza4);
                                obj2 = zzhr.zza(zza2, zza4, zzc(zzg), obj2, zzihVar);
                                break;
                            case 45:
                                zzhmVar.zzn(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 46:
                                zzhmVar.zzo(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 47:
                                zzhmVar.zzp(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 48:
                                zzhmVar.zzq(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 49:
                                zzhmVar.zzb(this.zzp.zza(t, zzd & 1048575), zza(zzg), zzfbVar);
                                break;
                            case 50:
                                Object zzb2 = zzb(zzg);
                                long zzd2 = zzd(zzg) & 1048575;
                                Object zzf = zzin.zzf(t, zzd2);
                                if (zzf == null) {
                                    zzf = this.zzs.zzf(zzb2);
                                    zzin.zza(t, zzd2, zzf);
                                } else if (this.zzs.zzd(zzf)) {
                                    Object zzf2 = this.zzs.zzf(zzb2);
                                    this.zzs.zza(zzf2, zzf);
                                    zzin.zza(t, zzd2, zzf2);
                                    zzf = zzf2;
                                }
                                zzhmVar.zza(this.zzs.zza(zzf), this.zzs.zzb(zzb2), zzfbVar);
                                break;
                            case 51:
                                zzin.zza(t, zzd & 1048575, Double.valueOf(zzhmVar.zzd()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 52:
                                zzin.zza(t, zzd & 1048575, Float.valueOf(zzhmVar.zze()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 53:
                                zzin.zza(t, zzd & 1048575, Long.valueOf(zzhmVar.zzg()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 54:
                                zzin.zza(t, zzd & 1048575, Long.valueOf(zzhmVar.zzf()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 55:
                                zzin.zza(t, zzd & 1048575, Integer.valueOf(zzhmVar.zzh()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 56:
                                zzin.zza(t, zzd & 1048575, Long.valueOf(zzhmVar.zzi()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 57:
                                zzin.zza(t, zzd & 1048575, Integer.valueOf(zzhmVar.zzj()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 58:
                                zzin.zza(t, zzd & 1048575, Boolean.valueOf(zzhmVar.zzk()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 59:
                                zza(t, zzd, zzhmVar);
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 60:
                                if (zza((zzha<T>) t, zza2, zzg)) {
                                    long j3 = zzd & 1048575;
                                    zzin.zza(t, j3, zzfr.zza(zzin.zzf(t, j3), zzhmVar.zza(zza(zzg), zzfbVar)));
                                } else {
                                    zzin.zza(t, zzd & 1048575, zzhmVar.zza(zza(zzg), zzfbVar));
                                    zzb((zzha<T>) t, zzg);
                                }
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 61:
                                zzin.zza(t, zzd & 1048575, zzhmVar.zzn());
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 62:
                                zzin.zza(t, zzd & 1048575, Integer.valueOf(zzhmVar.zzo()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 63:
                                int zzp2 = zzhmVar.zzp();
                                zzfs zzc2 = zzc(zzg);
                                if (zzc2 != null && !zzc2.zza(zzp2)) {
                                    obj2 = zzhr.zza(zza2, zzp2, obj2, zzihVar);
                                    break;
                                }
                                zzin.zza(t, zzd & 1048575, Integer.valueOf(zzp2));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 64:
                                zzin.zza(t, zzd & 1048575, Integer.valueOf(zzhmVar.zzq()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 65:
                                zzin.zza(t, zzd & 1048575, Long.valueOf(zzhmVar.zzr()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 66:
                                zzin.zza(t, zzd & 1048575, Integer.valueOf(zzhmVar.zzs()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 67:
                                zzin.zza(t, zzd & 1048575, Long.valueOf(zzhmVar.zzt()));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            case 68:
                                zzin.zza(t, zzd & 1048575, zzhmVar.zzb(zza(zzg), zzfbVar));
                                zzb((zzha<T>) t, zza2, zzg);
                                break;
                            default:
                                if (obj2 == null) {
                                    try {
                                        obj2 = zzihVar.zza();
                                    } catch (zzfz e) {
                                        zzihVar.zza(zzhmVar);
                                        if (obj2 == null) {
                                            obj2 = zzihVar.zzc(t);
                                        }
                                        if (zzihVar.zza((zzih) obj2, zzhmVar)) {
                                            break;
                                        } else {
                                            for (int i = this.zzm; i < this.zzn; i++) {
                                                obj2 = zza((Object) t, this.zzl[i], (int) obj2, (zzih<UT, int>) zzihVar);
                                            }
                                            if (obj2 != null) {
                                                zzihVar.zzb((Object) t, (T) obj2);
                                                return;
                                            }
                                            return;
                                        }
                                    }
                                }
                                if (zzihVar.zza((zzih) obj2, zzhmVar)) {
                                    break;
                                } else {
                                    for (int i2 = this.zzm; i2 < this.zzn; i2++) {
                                        obj2 = zza((Object) t, this.zzl[i2], (int) obj2, (zzih<UT, int>) zzihVar);
                                    }
                                    if (obj2 != null) {
                                        zzihVar.zzb((Object) t, (T) obj2);
                                        return;
                                    }
                                    return;
                                }
                        }
                    } else if (zza2 == Integer.MAX_VALUE) {
                        for (int i3 = this.zzm; i3 < this.zzn; i3++) {
                            obj2 = zza((Object) t, this.zzl[i3], (int) obj2, (zzih<UT, int>) zzihVar);
                        }
                        if (obj2 != null) {
                            zzihVar.zzb((Object) t, (T) obj2);
                            return;
                        }
                        return;
                    } else {
                        if (!this.zzh) {
                            obj = null;
                        } else {
                            obj = zzfdVar.zza(zzfbVar, this.zzg, zza2);
                        }
                        if (obj != null) {
                            if (zzfeVar2 == null) {
                                zzfeVar = zzfdVar.zzb(t);
                            } else {
                                zzfeVar = zzfeVar2;
                            }
                            obj2 = zzfdVar.zza(zzhmVar, obj, zzfbVar, zzfeVar, obj2, zzihVar);
                            zzfeVar2 = zzfeVar;
                        } else {
                            zzihVar.zza(zzhmVar);
                            if (obj2 == null) {
                                obj2 = zzihVar.zzc(t);
                            }
                            if (!zzihVar.zza((zzih) obj2, zzhmVar)) {
                                for (int i4 = this.zzm; i4 < this.zzn; i4++) {
                                    obj2 = zza((Object) t, this.zzl[i4], (int) obj2, (zzih<UT, int>) zzihVar);
                                }
                                if (obj2 != null) {
                                    zzihVar.zzb((Object) t, (T) obj2);
                                    return;
                                }
                                return;
                            }
                        }
                    }
                } catch (Throwable th) {
                    for (int i5 = this.zzm; i5 < this.zzn; i5++) {
                        obj2 = zza((Object) t, this.zzl[i5], (int) obj2, (zzih<UT, int>) zzihVar);
                    }
                    if (obj2 != null) {
                        zzihVar.zzb((Object) t, (T) obj2);
                    }
                    throw th;
                }
            }
        } else {
            throw null;
        }
    }

    private static zzig zze(Object obj) {
        zzfo zzfoVar = (zzfo) obj;
        zzig zzigVar = zzfoVar.zzb;
        if (zzigVar != zzig.zza()) {
            return zzigVar;
        }
        zzig zzb2 = zzig.zzb();
        zzfoVar.zzb = zzb2;
        return zzb2;
    }

    private static int zza(byte[] bArr, int i, int i2, zziu zziuVar, Class<?> cls, zzeb zzebVar) throws IOException {
        switch (zzhd.zza[zziuVar.ordinal()]) {
            case 1:
                int zzb2 = zzec.zzb(bArr, i, zzebVar);
                zzebVar.zzc = Boolean.valueOf(zzebVar.zzb != 0);
                return zzb2;
            case 2:
                return zzec.zze(bArr, i, zzebVar);
            case 3:
                zzebVar.zzc = Double.valueOf(zzec.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzebVar.zzc = Integer.valueOf(zzec.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzebVar.zzc = Long.valueOf(zzec.zzb(bArr, i));
                return i + 8;
            case 8:
                zzebVar.zzc = Float.valueOf(zzec.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zza2 = zzec.zza(bArr, i, zzebVar);
                zzebVar.zzc = Integer.valueOf(zzebVar.zza);
                return zza2;
            case 12:
            case 13:
                int zzb3 = zzec.zzb(bArr, i, zzebVar);
                zzebVar.zzc = Long.valueOf(zzebVar.zzb);
                return zzb3;
            case 14:
                return zzec.zza(zzhl.zza().zza((Class) cls), bArr, i, i2, zzebVar);
            case 15:
                int zza3 = zzec.zza(bArr, i, zzebVar);
                zzebVar.zzc = Integer.valueOf(zzes.zze(zzebVar.zza));
                return zza3;
            case 16:
                int zzb4 = zzec.zzb(bArr, i, zzebVar);
                zzebVar.zzc = Long.valueOf(zzes.zza(zzebVar.zzb));
                return zzb4;
            case 17:
                return zzec.zzd(bArr, i, zzebVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzeb zzebVar) throws IOException {
        int i8;
        zzfx zzfxVar = (zzfx) zzb.getObject(t, j2);
        if (!zzfxVar.zza()) {
            int size = zzfxVar.size();
            zzfxVar = zzfxVar.zza(size == 0 ? 10 : size << 1);
            zzb.putObject(t, j2, zzfxVar);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzfa zzfaVar = (zzfa) zzfxVar;
                    int zza2 = zzec.zza(bArr, i, zzebVar);
                    int i9 = zzebVar.zza + zza2;
                    while (zza2 < i9) {
                        zzfaVar.zza(zzec.zzc(bArr, zza2));
                        zza2 += 8;
                    }
                    if (zza2 == i9) {
                        return zza2;
                    }
                    throw zzfw.zza();
                } else if (i5 == 1) {
                    zzfa zzfaVar2 = (zzfa) zzfxVar;
                    zzfaVar2.zza(zzec.zzc(bArr, i));
                    int i10 = i + 8;
                    while (i10 < i2) {
                        int zza3 = zzec.zza(bArr, i10, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return i10;
                        }
                        zzfaVar2.zza(zzec.zzc(bArr, zza3));
                        i10 = zza3 + 8;
                    }
                    return i10;
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzfk zzfkVar = (zzfk) zzfxVar;
                    int zza4 = zzec.zza(bArr, i, zzebVar);
                    int i11 = zzebVar.zza + zza4;
                    while (zza4 < i11) {
                        zzfkVar.zza(zzec.zzd(bArr, zza4));
                        zza4 += 4;
                    }
                    if (zza4 == i11) {
                        return zza4;
                    }
                    throw zzfw.zza();
                } else if (i5 == 5) {
                    zzfk zzfkVar2 = (zzfk) zzfxVar;
                    zzfkVar2.zza(zzec.zzd(bArr, i));
                    int i12 = i + 4;
                    while (i12 < i2) {
                        int zza5 = zzec.zza(bArr, i12, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return i12;
                        }
                        zzfkVar2.zza(zzec.zzd(bArr, zza5));
                        i12 = zza5 + 4;
                    }
                    return i12;
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzgk zzgkVar = (zzgk) zzfxVar;
                    int zza6 = zzec.zza(bArr, i, zzebVar);
                    int i13 = zzebVar.zza + zza6;
                    while (zza6 < i13) {
                        zza6 = zzec.zzb(bArr, zza6, zzebVar);
                        zzgkVar.zza(zzebVar.zzb);
                    }
                    if (zza6 == i13) {
                        return zza6;
                    }
                    throw zzfw.zza();
                } else if (i5 == 0) {
                    zzgk zzgkVar2 = (zzgk) zzfxVar;
                    int zzb2 = zzec.zzb(bArr, i, zzebVar);
                    zzgkVar2.zza(zzebVar.zzb);
                    while (zzb2 < i2) {
                        int zza7 = zzec.zza(bArr, zzb2, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return zzb2;
                        }
                        zzb2 = zzec.zzb(bArr, zza7, zzebVar);
                        zzgkVar2.zza(zzebVar.zzb);
                    }
                    return zzb2;
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzec.zza(bArr, i, zzfxVar, zzebVar);
                }
                if (i5 == 0) {
                    return zzec.zza(i3, bArr, i, i2, zzfxVar, zzebVar);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzgk zzgkVar3 = (zzgk) zzfxVar;
                    int zza8 = zzec.zza(bArr, i, zzebVar);
                    int i14 = zzebVar.zza + zza8;
                    while (zza8 < i14) {
                        zzgkVar3.zza(zzec.zzb(bArr, zza8));
                        zza8 += 8;
                    }
                    if (zza8 == i14) {
                        return zza8;
                    }
                    throw zzfw.zza();
                } else if (i5 == 1) {
                    zzgk zzgkVar4 = (zzgk) zzfxVar;
                    zzgkVar4.zza(zzec.zzb(bArr, i));
                    int i15 = i + 8;
                    while (i15 < i2) {
                        int zza9 = zzec.zza(bArr, i15, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return i15;
                        }
                        zzgkVar4.zza(zzec.zzb(bArr, zza9));
                        i15 = zza9 + 8;
                    }
                    return i15;
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzfp zzfpVar = (zzfp) zzfxVar;
                    int zza10 = zzec.zza(bArr, i, zzebVar);
                    int i16 = zzebVar.zza + zza10;
                    while (zza10 < i16) {
                        zzfpVar.zzd(zzec.zza(bArr, zza10));
                        zza10 += 4;
                    }
                    if (zza10 == i16) {
                        return zza10;
                    }
                    throw zzfw.zza();
                } else if (i5 == 5) {
                    zzfp zzfpVar2 = (zzfp) zzfxVar;
                    zzfpVar2.zzd(zzec.zza(bArr, i));
                    int i17 = i + 4;
                    while (i17 < i2) {
                        int zza11 = zzec.zza(bArr, i17, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return i17;
                        }
                        zzfpVar2.zzd(zzec.zza(bArr, zza11));
                        i17 = zza11 + 4;
                    }
                    return i17;
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzee zzeeVar = (zzee) zzfxVar;
                    int zza12 = zzec.zza(bArr, i, zzebVar);
                    int i18 = zzebVar.zza + zza12;
                    while (zza12 < i18) {
                        zza12 = zzec.zzb(bArr, zza12, zzebVar);
                        zzeeVar.zza(zzebVar.zzb != 0);
                    }
                    if (zza12 == i18) {
                        return zza12;
                    }
                    throw zzfw.zza();
                } else if (i5 == 0) {
                    zzee zzeeVar2 = (zzee) zzfxVar;
                    int zzb3 = zzec.zzb(bArr, i, zzebVar);
                    zzeeVar2.zza(zzebVar.zzb != 0);
                    while (zzb3 < i2) {
                        int zza13 = zzec.zza(bArr, zzb3, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return zzb3;
                        }
                        zzb3 = zzec.zzb(bArr, zza13, zzebVar);
                        zzeeVar2.zza(zzebVar.zzb != 0);
                    }
                    return zzb3;
                }
                break;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        int zza14 = zzec.zza(bArr, i, zzebVar);
                        int i19 = zzebVar.zza;
                        if (i19 >= 0) {
                            if (i19 == 0) {
                                zzfxVar.add("");
                            } else {
                                zzfxVar.add(new String(bArr, zza14, i19, zzfr.zza));
                                zza14 += i19;
                            }
                            while (zza14 < i2) {
                                int zza15 = zzec.zza(bArr, zza14, zzebVar);
                                if (i3 != zzebVar.zza) {
                                    return zza14;
                                }
                                zza14 = zzec.zza(bArr, zza15, zzebVar);
                                int i20 = zzebVar.zza;
                                if (i20 < 0) {
                                    throw zzfw.zzb();
                                } else if (i20 == 0) {
                                    zzfxVar.add("");
                                } else {
                                    zzfxVar.add(new String(bArr, zza14, i20, zzfr.zza));
                                    zza14 += i20;
                                }
                            }
                            return zza14;
                        }
                        throw zzfw.zzb();
                    }
                    int zza16 = zzec.zza(bArr, i, zzebVar);
                    int i21 = zzebVar.zza;
                    if (i21 >= 0) {
                        if (i21 == 0) {
                            zzfxVar.add("");
                        } else {
                            int i22 = zza16 + i21;
                            if (zzip.zza(bArr, zza16, i22)) {
                                zzfxVar.add(new String(bArr, zza16, i21, zzfr.zza));
                                zza16 = i22;
                            } else {
                                throw zzfw.zzh();
                            }
                        }
                        while (zza16 < i2) {
                            int zza17 = zzec.zza(bArr, zza16, zzebVar);
                            if (i3 != zzebVar.zza) {
                                return zza16;
                            }
                            zza16 = zzec.zza(bArr, zza17, zzebVar);
                            int i23 = zzebVar.zza;
                            if (i23 < 0) {
                                throw zzfw.zzb();
                            } else if (i23 == 0) {
                                zzfxVar.add("");
                            } else {
                                int i24 = zza16 + i23;
                                if (zzip.zza(bArr, zza16, i24)) {
                                    zzfxVar.add(new String(bArr, zza16, i23, zzfr.zza));
                                    zza16 = i24;
                                } else {
                                    throw zzfw.zzh();
                                }
                            }
                        }
                        return zza16;
                    }
                    throw zzfw.zzb();
                }
                break;
            case 27:
                if (i5 == 2) {
                    return zzec.zza(zza(i6), i3, bArr, i, i2, zzfxVar, zzebVar);
                }
                break;
            case 28:
                if (i5 == 2) {
                    int zza18 = zzec.zza(bArr, i, zzebVar);
                    int i25 = zzebVar.zza;
                    if (i25 < 0) {
                        throw zzfw.zzb();
                    } else if (i25 <= bArr.length - zza18) {
                        if (i25 == 0) {
                            zzfxVar.add(zzeg.zza);
                        } else {
                            zzfxVar.add(zzeg.zza(bArr, zza18, i25));
                            zza18 += i25;
                        }
                        while (zza18 < i2) {
                            int zza19 = zzec.zza(bArr, zza18, zzebVar);
                            if (i3 != zzebVar.zza) {
                                return zza18;
                            }
                            zza18 = zzec.zza(bArr, zza19, zzebVar);
                            int i26 = zzebVar.zza;
                            if (i26 < 0) {
                                throw zzfw.zzb();
                            } else if (i26 > bArr.length - zza18) {
                                throw zzfw.zza();
                            } else if (i26 == 0) {
                                zzfxVar.add(zzeg.zza);
                            } else {
                                zzfxVar.add(zzeg.zza(bArr, zza18, i26));
                                zza18 += i26;
                            }
                        }
                        return zza18;
                    } else {
                        throw zzfw.zza();
                    }
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    i8 = zzec.zza(bArr, i, zzfxVar, zzebVar);
                } else if (i5 == 0) {
                    i8 = zzec.zza(i3, bArr, i, i2, zzfxVar, zzebVar);
                }
                zzfo zzfoVar = (zzfo) t;
                zzig zzigVar = zzfoVar.zzb;
                if (zzigVar == zzig.zza()) {
                    zzigVar = null;
                }
                zzig zzigVar2 = (zzig) zzhr.zza(i4, zzfxVar, zzc(i6), zzigVar, this.zzq);
                if (zzigVar2 != null) {
                    zzfoVar.zzb = zzigVar2;
                }
                return i8;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzfp zzfpVar3 = (zzfp) zzfxVar;
                    int zza20 = zzec.zza(bArr, i, zzebVar);
                    int i27 = zzebVar.zza + zza20;
                    while (zza20 < i27) {
                        zza20 = zzec.zza(bArr, zza20, zzebVar);
                        zzfpVar3.zzd(zzes.zze(zzebVar.zza));
                    }
                    if (zza20 == i27) {
                        return zza20;
                    }
                    throw zzfw.zza();
                } else if (i5 == 0) {
                    zzfp zzfpVar4 = (zzfp) zzfxVar;
                    int zza21 = zzec.zza(bArr, i, zzebVar);
                    zzfpVar4.zzd(zzes.zze(zzebVar.zza));
                    while (zza21 < i2) {
                        int zza22 = zzec.zza(bArr, zza21, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return zza21;
                        }
                        zza21 = zzec.zza(bArr, zza22, zzebVar);
                        zzfpVar4.zzd(zzes.zze(zzebVar.zza));
                    }
                    return zza21;
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzgk zzgkVar5 = (zzgk) zzfxVar;
                    int zza23 = zzec.zza(bArr, i, zzebVar);
                    int i28 = zzebVar.zza + zza23;
                    while (zza23 < i28) {
                        zza23 = zzec.zzb(bArr, zza23, zzebVar);
                        zzgkVar5.zza(zzes.zza(zzebVar.zzb));
                    }
                    if (zza23 == i28) {
                        return zza23;
                    }
                    throw zzfw.zza();
                } else if (i5 == 0) {
                    zzgk zzgkVar6 = (zzgk) zzfxVar;
                    int zzb4 = zzec.zzb(bArr, i, zzebVar);
                    zzgkVar6.zza(zzes.zza(zzebVar.zzb));
                    while (zzb4 < i2) {
                        int zza24 = zzec.zza(bArr, zzb4, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return zzb4;
                        }
                        zzb4 = zzec.zzb(bArr, zza24, zzebVar);
                        zzgkVar6.zza(zzes.zza(zzebVar.zzb));
                    }
                    return zzb4;
                }
                break;
            case 49:
                if (i5 == 3) {
                    zzhp zza25 = zza(i6);
                    int i29 = (i3 & (-8)) | 4;
                    int zza26 = zzec.zza(zza25, bArr, i, i2, i29, zzebVar);
                    zzfxVar.add(zzebVar.zzc);
                    while (zza26 < i2) {
                        int zza27 = zzec.zza(bArr, zza26, zzebVar);
                        if (i3 != zzebVar.zza) {
                            return zza26;
                        }
                        zza26 = zzec.zza(zza25, bArr, zza27, i2, i29, zzebVar);
                        zzfxVar.add(zzebVar.zzc);
                    }
                    return zza26;
                }
                break;
        }
        return i;
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzeb zzebVar) throws IOException {
        int i4;
        int i5;
        Unsafe unsafe = zzb;
        Object zzb2 = zzb(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzs.zzd(object)) {
            Object zzf = this.zzs.zzf(zzb2);
            this.zzs.zza(zzf, object);
            unsafe.putObject(t, j, zzf);
            object = zzf;
        }
        zzgr<?, ?> zzb3 = this.zzs.zzb(zzb2);
        Map<?, ?> zza2 = this.zzs.zza(object);
        int zza3 = zzec.zza(bArr, i, zzebVar);
        int i6 = zzebVar.zza;
        if (i6 < 0 || i6 > i2 - zza3) {
            throw zzfw.zza();
        }
        int i7 = i6 + zza3;
        Object obj = (K) zzb3.zzb;
        Object obj2 = (V) zzb3.zzd;
        while (zza3 < i7) {
            int i8 = zza3 + 1;
            byte b = bArr[zza3];
            if (b < 0) {
                i5 = zzec.zza(b, bArr, i8, zzebVar);
                i4 = zzebVar.zza;
            } else {
                i5 = i8;
                i4 = b;
            }
            int i9 = (i4 == 1 ? 1 : 0) >>> 3;
            int i10 = (i4 == 1 ? 1 : 0) & 7;
            if (i9 != 1) {
                if (i9 == 2 && i10 == zzb3.zzc.zzb()) {
                    zza3 = zza(bArr, i5, i2, zzb3.zzc, zzb3.zzd.getClass(), zzebVar);
                    obj2 = (V) zzebVar.zzc;
                }
                zza3 = zzec.zza(i4, bArr, i5, i2, zzebVar);
            } else if (i10 == zzb3.zza.zzb()) {
                zza3 = zza(bArr, i5, i2, zzb3.zza, (Class<?>) null, zzebVar);
                obj = (K) zzebVar.zzc;
            } else {
                zza3 = zzec.zza(i4, bArr, i5, i2, zzebVar);
            }
        }
        if (zza3 == i7) {
            zza2.put(obj, obj2);
            return i7;
        }
        throw zzfw.zzg();
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzeb zzebVar) throws IOException {
        int i9;
        Object obj;
        Object obj2;
        Unsafe unsafe = zzb;
        long j2 = this.zzc[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzec.zzc(bArr, i)));
                    i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzec.zzd(bArr, i)));
                    i9 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    i9 = zzec.zzb(bArr, i, zzebVar);
                    unsafe.putObject(t, j, Long.valueOf(zzebVar.zzb));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    i9 = zzec.zza(bArr, i, zzebVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzebVar.zza));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzec.zzb(bArr, i)));
                    i9 = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzec.zza(bArr, i)));
                    i9 = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    i9 = zzec.zzb(bArr, i, zzebVar);
                    unsafe.putObject(t, j, Boolean.valueOf(zzebVar.zzb != 0));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int zza2 = zzec.zza(bArr, i, zzebVar);
                    int i10 = zzebVar.zza;
                    if (i10 == 0) {
                        unsafe.putObject(t, j, "");
                    } else if ((i6 & 536870912) == 0 || zzip.zza(bArr, zza2, zza2 + i10)) {
                        unsafe.putObject(t, j, new String(bArr, zza2, i10, zzfr.zza));
                        zza2 += i10;
                    } else {
                        throw zzfw.zzh();
                    }
                    unsafe.putInt(t, j2, i4);
                    return zza2;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    int zza3 = zzec.zza(zza(i8), bArr, i, i2, zzebVar);
                    if (unsafe.getInt(t, j2) == i4) {
                        obj = unsafe.getObject(t, j);
                    } else {
                        obj = null;
                    }
                    if (obj == null) {
                        unsafe.putObject(t, j, zzebVar.zzc);
                    } else {
                        unsafe.putObject(t, j, zzfr.zza(obj, zzebVar.zzc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return zza3;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    i9 = zzec.zze(bArr, i, zzebVar);
                    unsafe.putObject(t, j, zzebVar.zzc);
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zza4 = zzec.zza(bArr, i, zzebVar);
                    int i11 = zzebVar.zza;
                    zzfs zzc = zzc(i8);
                    if (zzc == null || zzc.zza(i11)) {
                        unsafe.putObject(t, j, Integer.valueOf(i11));
                        i9 = zza4;
                        unsafe.putInt(t, j2, i4);
                        return i9;
                    }
                    zze(t).zza(i3, Long.valueOf(i11));
                    return zza4;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    i9 = zzec.zza(bArr, i, zzebVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzes.zze(zzebVar.zza)));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    i9 = zzec.zzb(bArr, i, zzebVar);
                    unsafe.putObject(t, j, Long.valueOf(zzes.zza(zzebVar.zzb)));
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    i9 = zzec.zza(zza(i8), bArr, i, i2, (i3 & (-8)) | 4, zzebVar);
                    if (unsafe.getInt(t, j2) == i4) {
                        obj2 = unsafe.getObject(t, j);
                    } else {
                        obj2 = null;
                    }
                    if (obj2 == null) {
                        unsafe.putObject(t, j, zzebVar.zzc);
                    } else {
                        unsafe.putObject(t, j, zzfr.zza(obj2, zzebVar.zzc));
                    }
                    unsafe.putInt(t, j2, i4);
                    return i9;
                }
                return i;
            default:
                return i;
        }
    }

    private final zzhp zza(int i) {
        int i2 = (i / 3) << 1;
        zzhp zzhpVar = (zzhp) this.zzd[i2];
        if (zzhpVar != null) {
            return zzhpVar;
        }
        zzhp<T> zza2 = zzhl.zza().zza((Class) ((Class) this.zzd[i2 + 1]));
        this.zzd[i2] = zza2;
        return zza2;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzfs zzc(int i) {
        return (zzfs) this.zzd[((i / 3) << 1) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x05a0, code lost:
        if (r6 == 1048575) goto L_0x05a8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x05a2, code lost:
        r26.putInt(r12, r6, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x05a8, code lost:
        r1 = null;
        r2 = r9.zzm;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x05ad, code lost:
        if (r2 >= r9.zzn) goto L_0x05be;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x05af, code lost:
        r1 = (com.google.android.gms.internal.measurement.zzig) r9.zza((java.lang.Object) r12, r9.zzl[r2], (int) r1, (com.google.android.gms.internal.measurement.zzih<UT, int>) r9.zzq);
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x05be, code lost:
        if (r1 == null) goto L_0x05c5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x05c0, code lost:
        r9.zzq.zzb((java.lang.Object) r12, (T) r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x05c5, code lost:
        if (r7 != 0) goto L_0x05d1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x05c9, code lost:
        if (r0 != r31) goto L_0x05cc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x05d0, code lost:
        throw com.google.android.gms.internal.measurement.zzfw.zzg();
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x05d3, code lost:
        if (r0 > r31) goto L_0x05d8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x05d5, code lost:
        if (r3 != r7) goto L_0x05d8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x05d7, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x05dd, code lost:
        throw com.google.android.gms.internal.measurement.zzfw.zzg();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(T r28, byte[] r29, int r30, int r31, int r32, com.google.android.gms.internal.measurement.zzeb r33) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1544
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzha.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.measurement.zzeb):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zza(T t, byte[] bArr, int i, int i2, zzeb zzebVar) throws IOException {
        byte b;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        zzha<T> zzhaVar = this;
        T t2 = t;
        byte[] bArr2 = bArr;
        int i9 = i2;
        zzeb zzebVar2 = zzebVar;
        if (zzhaVar.zzj) {
            Unsafe unsafe = zzb;
            int i10 = -1;
            int i11 = i;
            int i12 = -1;
            int i13 = 0;
            int i14 = 0;
            int i15 = 1048575;
            while (i11 < i9) {
                int i16 = i11 + 1;
                byte b2 = bArr2[i11];
                if (b2 < 0) {
                    i3 = zzec.zza(b2, bArr2, i16, zzebVar2);
                    b = zzebVar2.zza;
                } else {
                    b = b2;
                    i3 = i16;
                }
                int i17 = b >>> 3;
                int i18 = b & 7;
                if (i17 > i12) {
                    i4 = zzhaVar.zza(i17, i13 / 3);
                } else {
                    i4 = zzhaVar.zzg(i17);
                }
                if (i4 == i10) {
                    i5 = i3;
                    i12 = i17;
                    unsafe = unsafe;
                    i13 = 0;
                } else {
                    int[] iArr = zzhaVar.zzc;
                    int i19 = iArr[i4 + 1];
                    int i20 = (i19 & 267386880) >>> 20;
                    long j = i19 & 1048575;
                    if (i20 <= 17) {
                        int i21 = iArr[i4 + 2];
                        int i22 = 1 << (i21 >>> 20);
                        int i23 = i21 & 1048575;
                        if (i23 != i15) {
                            if (i15 != 1048575) {
                                i6 = i4;
                                unsafe.putInt(t2, i15, i14);
                            } else {
                                i6 = i4;
                            }
                            if (i23 != 1048575) {
                                i14 = unsafe.getInt(t2, i23);
                            }
                            i15 = i23;
                        } else {
                            i6 = i4;
                        }
                        switch (i20) {
                            case 0:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 1) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    zzin.zza(t2, j, zzec.zzc(bArr2, i7));
                                    i11 = i7 + 8;
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 1:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 5) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    zzin.zza((Object) t2, j, zzec.zzd(bArr2, i7));
                                    i11 = i7 + 4;
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 2:
                            case 3:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 0) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    int zzb2 = zzec.zzb(bArr2, i7, zzebVar2);
                                    unsafe.putLong(t, j, zzebVar2.zzb);
                                    i14 |= i22;
                                    i11 = zzb2;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 4:
                            case 11:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 0) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    i11 = zzec.zza(bArr2, i7, zzebVar2);
                                    unsafe.putInt(t2, j, zzebVar2.zza);
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 5:
                            case 14:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 1) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    unsafe.putLong(t, j, zzec.zzb(bArr2, i7));
                                    i11 = i7 + 8;
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 6:
                            case 13:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 5) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    unsafe.putInt(t2, j, zzec.zza(bArr2, i7));
                                    i11 = i7 + 4;
                                    i14 |= i22;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    i13 = i13;
                                    break;
                                }
                            case 7:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 0) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    int zzb3 = zzec.zzb(bArr2, i7, zzebVar2);
                                    zzin.zza(t2, j, zzebVar2.zzb != 0);
                                    i14 |= i22;
                                    i11 = zzb3;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    i13 = i13;
                                    break;
                                }
                            case 8:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 2) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    if ((i19 & 536870912) == 0) {
                                        i11 = zzec.zzc(bArr2, i7, zzebVar2);
                                    } else {
                                        i11 = zzec.zzd(bArr2, i7, zzebVar2);
                                    }
                                    unsafe.putObject(t2, j, zzebVar2.zzc);
                                    i14 |= i22;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    i13 = i13;
                                    break;
                                }
                            case 9:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 2) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    i11 = zzec.zza(zzhaVar.zza(i13), bArr2, i7, i2, zzebVar2);
                                    Object object = unsafe.getObject(t2, j);
                                    if (object == null) {
                                        unsafe.putObject(t2, j, zzebVar2.zzc);
                                    } else {
                                        unsafe.putObject(t2, j, zzfr.zza(object, zzebVar2.zzc));
                                    }
                                    i14 |= i22;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    i13 = i13;
                                    break;
                                }
                            case 10:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 2) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    i11 = zzec.zze(bArr2, i7, zzebVar2);
                                    unsafe.putObject(t2, j, zzebVar2.zzc);
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 12:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 0) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    i11 = zzec.zza(bArr2, i7, zzebVar2);
                                    unsafe.putInt(t2, j, zzebVar2.zza);
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 15:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                if (i18 != 0) {
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    i11 = zzec.zza(bArr2, i7, zzebVar2);
                                    unsafe.putInt(t2, j, zzes.zze(zzebVar2.zza));
                                    i14 |= i22;
                                    i13 = i13;
                                    i12 = i12;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            case 16:
                                if (i18 != 0) {
                                    i7 = i3;
                                    i13 = i6;
                                    i12 = i17;
                                    i5 = i7;
                                    unsafe = unsafe;
                                    break;
                                } else {
                                    int zzb4 = zzec.zzb(bArr2, i3, zzebVar2);
                                    unsafe.putLong(t, j, zzes.zza(zzebVar2.zzb));
                                    i14 |= i22;
                                    i11 = zzb4;
                                    i13 = i6;
                                    i12 = i17;
                                    i10 = -1;
                                    i9 = i2;
                                    break;
                                }
                            default:
                                i7 = i3;
                                i13 = i6;
                                i12 = i17;
                                i5 = i7;
                                unsafe = unsafe;
                                break;
                        }
                    } else {
                        i12 = i17;
                        if (i20 == 27) {
                            if (i18 == 2) {
                                zzfx zzfxVar = (zzfx) unsafe.getObject(t2, j);
                                if (!zzfxVar.zza()) {
                                    int size = zzfxVar.size();
                                    zzfxVar = zzfxVar.zza(size == 0 ? 10 : size << 1);
                                    unsafe.putObject(t2, j, zzfxVar);
                                }
                                i11 = zzec.zza(zzhaVar.zza(i4), b, bArr, i3, i2, zzfxVar, zzebVar);
                                i14 = i14;
                                i13 = i4;
                                i12 = i12;
                                i10 = -1;
                                i9 = i2;
                            } else {
                                i15 = i15;
                                i14 = i14;
                                unsafe = unsafe;
                                i8 = i3;
                                i13 = i4;
                                i5 = i8;
                            }
                        } else if (i20 <= 49) {
                            unsafe = unsafe;
                            i13 = i4;
                            i11 = zza((zzha<T>) t, bArr, i3, i2, b, i12, i18, i4, i19, i20, j, zzebVar);
                            if (i11 == i3) {
                                i5 = i11;
                                i15 = i15;
                                i14 = i14;
                            } else {
                                zzhaVar = this;
                                t2 = t;
                                bArr2 = bArr;
                                i9 = i2;
                                zzebVar2 = zzebVar;
                                i15 = i15;
                                i12 = i12;
                                i13 = i13;
                                i14 = i14;
                                unsafe = unsafe;
                                i10 = -1;
                            }
                        } else {
                            i14 = i14;
                            i15 = i15;
                            unsafe = unsafe;
                            i8 = i3;
                            i13 = i4;
                            if (i20 != 50) {
                                i11 = zza((zzha<T>) t, bArr, i8, i2, b, i12, i18, i19, i20, j, i13, zzebVar);
                                if (i11 == i8) {
                                    i5 = i11;
                                    i15 = i15;
                                    i14 = i14;
                                } else {
                                    zzhaVar = this;
                                    t2 = t;
                                    bArr2 = bArr;
                                    i9 = i2;
                                    zzebVar2 = zzebVar;
                                    i15 = i15;
                                    i12 = i12;
                                    i13 = i13;
                                    i14 = i14;
                                    unsafe = unsafe;
                                    i10 = -1;
                                }
                            } else if (i18 == 2) {
                                i11 = zza((zzha<T>) t, bArr, i8, i2, i13, j, zzebVar);
                                if (i11 == i8) {
                                    i5 = i11;
                                    i15 = i15;
                                    i14 = i14;
                                } else {
                                    zzhaVar = this;
                                    t2 = t;
                                    bArr2 = bArr;
                                    i9 = i2;
                                    zzebVar2 = zzebVar;
                                    i15 = i15;
                                    i12 = i12;
                                    i13 = i13;
                                    i14 = i14;
                                    unsafe = unsafe;
                                    i10 = -1;
                                }
                            } else {
                                i5 = i8;
                            }
                        }
                    }
                }
                i11 = zzec.zza(b, bArr, i5, i2, zze(t), zzebVar);
                zzhaVar = this;
                t2 = t;
                bArr2 = bArr;
                i9 = i2;
                zzebVar2 = zzebVar;
                i10 = -1;
            }
            if (i15 != 1048575) {
                unsafe.putInt(t, i15, i14);
            }
            if (i11 != i2) {
                throw zzfw.zzg();
            }
            return;
        }
        zza((zzha<T>) t, bArr, i, i2, 0, zzebVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zzc(T t) {
        int i;
        int i2 = this.zzm;
        while (true) {
            i = this.zzn;
            if (i2 >= i) {
                break;
            }
            long zzd = zzd(this.zzl[i2]) & 1048575;
            Object zzf = zzin.zzf(t, zzd);
            if (zzf != null) {
                zzin.zza(t, zzd, this.zzs.zze(zzf));
            }
            i2++;
        }
        int length = this.zzl.length;
        while (i < length) {
            this.zzp.zzb(t, this.zzl[i]);
            i++;
        }
        this.zzq.zzd(t);
        if (this.zzh) {
            this.zzr.zzc(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzih<UT, UB> zzihVar) {
        int i2 = this.zzc[i];
        Object zzf = zzin.zzf(obj, zzd(i) & 1048575);
        if (zzf == null) {
            return ub;
        }
        zzfs zzc = zzc(i);
        if (zzc == null) {
            return ub;
        }
        return (UB) zza(i, i2, this.zzs.zza(zzf), zzc, (zzfs) ub, (zzih<UT, zzfs>) zzihVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzfs zzfsVar, UB ub, zzih<UT, UB> zzihVar) {
        zzgr<?, ?> zzb2 = this.zzs.zzb(zzb(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzfsVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzihVar.zza();
                }
                zzeo zzc = zzeg.zzc(zzgo.zza(zzb2, next.getKey(), next.getValue()));
                try {
                    zzgo.zza(zzc.zzb(), zzb2, next.getKey(), next.getValue());
                    zzihVar.zza((zzih<UT, UB>) ub, i2, zzc.zza());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.gms.internal.measurement.zzhp] */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.measurement.zzhp] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // com.google.android.gms.internal.measurement.zzhp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzd(T r19) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzha.zzd(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzhp zzhpVar) {
        return zzhpVar.zzd(zzin.zzf(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzja zzjaVar) throws IOException {
        if (obj instanceof String) {
            zzjaVar.zza(i, (String) obj);
        } else {
            zzjaVar.zza(i, (zzeg) obj);
        }
    }

    private final void zza(Object obj, int i, zzhm zzhmVar) throws IOException {
        if (zzf(i)) {
            zzin.zza(obj, i & 1048575, zzhmVar.zzm());
        } else if (this.zzi) {
            zzin.zza(obj, i & 1048575, zzhmVar.zzl());
        } else {
            zzin.zza(obj, i & 1048575, zzhmVar.zzn());
        }
    }

    private final int zzd(int i) {
        return this.zzc[i + 1];
    }

    private final int zze(int i) {
        return this.zzc[i + 2];
    }

    private static boolean zzf(int i) {
        return (i & 536870912) != 0;
    }

    private static <T> double zzb(T t, long j) {
        return ((Double) zzin.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzin.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzin.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzin.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzin.zzf(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((zzha<T>) t, i) == zza((zzha<T>) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zza((zzha<T>) t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zza(T t, int i) {
        int zze = zze(i);
        long j = zze & 1048575;
        if (j == 1048575) {
            int zzd = zzd(i);
            long j2 = zzd & 1048575;
            switch ((zzd & 267386880) >>> 20) {
                case 0:
                    return zzin.zze(t, j2) != 0.0d;
                case 1:
                    return zzin.zzd(t, j2) != 0.0f;
                case 2:
                    return zzin.zzb(t, j2) != 0;
                case 3:
                    return zzin.zzb(t, j2) != 0;
                case 4:
                    return zzin.zza(t, j2) != 0;
                case 5:
                    return zzin.zzb(t, j2) != 0;
                case 6:
                    return zzin.zza(t, j2) != 0;
                case 7:
                    return zzin.zzc(t, j2);
                case 8:
                    Object zzf = zzin.zzf(t, j2);
                    if (zzf instanceof String) {
                        return !((String) zzf).isEmpty();
                    }
                    if (zzf instanceof zzeg) {
                        return !zzeg.zza.equals(zzf);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzin.zzf(t, j2) != null;
                case 10:
                    return !zzeg.zza.equals(zzin.zzf(t, j2));
                case 11:
                    return zzin.zza(t, j2) != 0;
                case 12:
                    return zzin.zza(t, j2) != 0;
                case 13:
                    return zzin.zza(t, j2) != 0;
                case 14:
                    return zzin.zzb(t, j2) != 0;
                case 15:
                    return zzin.zza(t, j2) != 0;
                case 16:
                    return zzin.zzb(t, j2) != 0;
                case 17:
                    return zzin.zzf(t, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzin.zza(t, j) & (1 << (zze >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        int zze = zze(i);
        long j = 1048575 & zze;
        if (j != 1048575) {
            zzin.zza((Object) t, j, (1 << (zze >>> 20)) | zzin.zza(t, j));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzin.zza(t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzin.zza((Object) t, zze(i2) & 1048575, i);
    }

    private final int zzg(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, 0);
    }

    private final int zza(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzb(i, i2);
    }

    private final int zzb(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
