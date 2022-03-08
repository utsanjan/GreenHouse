package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzjr<T> implements zzkd<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzlf.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzjn zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzjw zzo;
    private final zzix zzp;
    private final zzkz<?, ?> zzq;
    private final zzhu<?> zzr;
    private final zzjk zzs;

    private zzjr(int[] iArr, Object[] objArr, int i, int i2, zzjn zzjnVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzjw zzjwVar, zzix zzixVar, zzkz<?, ?> zzkzVar, zzhu<?> zzhuVar, zzjk zzjkVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzjnVar instanceof zzif;
        this.zzj = z;
        this.zzh = zzhuVar != null && zzhuVar.zza(zzjnVar);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzjwVar;
        this.zzp = zzixVar;
        this.zzq = zzkzVar;
        this.zzr = zzhuVar;
        this.zzg = zzjnVar;
        this.zzs = zzjkVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03af A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <T> com.google.android.gms.internal.firebase_auth.zzjr<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.firebase_auth.zzjl r34, com.google.android.gms.internal.firebase_auth.zzjw r35, com.google.android.gms.internal.firebase_auth.zzix r36, com.google.android.gms.internal.firebase_auth.zzkz<?, ?> r37, com.google.android.gms.internal.firebase_auth.zzhu<?> r38, com.google.android.gms.internal.firebase_auth.zzjk r39) {
        /*
            Method dump skipped, instructions count: 1074
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjr.zza(java.lang.Class, com.google.android.gms.internal.firebase_auth.zzjl, com.google.android.gms.internal.firebase_auth.zzjw, com.google.android.gms.internal.firebase_auth.zzix, com.google.android.gms.internal.firebase_auth.zzkz, com.google.android.gms.internal.firebase_auth.zzhu, com.google.android.gms.internal.firebase_auth.zzjk):com.google.android.gms.internal.firebase_auth.zzjr");
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

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final T zza() {
        return (T) this.zzo.zza(this.zzg);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
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
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzlf.zze(t, j)) != Double.doubleToLongBits(zzlf.zze(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzlf.zzd(t, j)) != Float.floatToIntBits(zzlf.zzd(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zzlf.zzb(t, j) != zzlf.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zzlf.zzb(t, j) != zzlf.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zzlf.zza(t, j) != zzlf.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zzlf.zzb(t, j) != zzlf.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zzlf.zza(t, j) != zzlf.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zzlf.zzc(t, j) != zzlf.zzc(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j))) {
                            z = false;
                            break;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zzlf.zza(t, j) != zzlf.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zzlf.zza(t, j) != zzlf.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zzlf.zza(t, j) != zzlf.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zzlf.zzb(t, j) != zzlf.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zzlf.zza(t, j) != zzlf.zza(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zzlf.zzb(t, j) != zzlf.zzb(t2, j)) {
                            z = false;
                            break;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j))) {
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
                        z = zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j));
                        break;
                    case 50:
                        z = zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j));
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
                        if (zzlf.zza(t, zze) != zzlf.zza(t2, zze) || !zzkf.zza(zzlf.zzf(t, j), zzlf.zzf(t2, j))) {
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

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
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
                    i = (i * 53) + zzii.zza(Double.doubleToLongBits(zzlf.zze(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzlf.zzd(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzii.zza(zzlf.zzb(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzii.zza(zzlf.zzb(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzlf.zza(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzii.zza(zzlf.zzb(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzlf.zza(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzii.zza(zzlf.zzc(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzlf.zzf(t, j)).hashCode();
                    break;
                case 9:
                    Object zzf = zzlf.zzf(t, j);
                    if (zzf != null) {
                        i4 = zzf.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzlf.zzf(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzlf.zza(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzlf.zza(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzlf.zza(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzii.zza(zzlf.zzb(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzlf.zza(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzii.zza(zzlf.zzb(t, j));
                    break;
                case 17:
                    Object zzf2 = zzlf.zzf(t, j);
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
                    i = (i * 53) + zzlf.zzf(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzlf.zzf(t, j).hashCode();
                    break;
                case 51:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(Double.doubleToLongBits(zzb(t, j)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + Float.floatToIntBits(zzc(t, j));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(zzf(t, j));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + ((String) zzlf.zzf(t, j)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzlf.zzf(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzlf.zzf(t, j).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzd(t, j);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzii.zza(zze(t, j));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zza((zzjr<T>) t, i3, i2)) {
                        i = (i * 53) + zzlf.zzf(t, j).hashCode();
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

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zzb(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzd = zzd(i);
                long j = 1048575 & zzd;
                int i2 = this.zzc[i];
                switch ((zzd & 267386880) >>> 20) {
                    case 0:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza(t, j, zzlf.zze(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zzd(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zza(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zza(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza(t, j, zzlf.zzc(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza(t, j, zzlf.zzf(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza(t, j, zzlf.zzf(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zza(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zza(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zza(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zza(t2, j));
                            zzb((zzjr<T>) t, i);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzjr<T>) t2, i)) {
                            zzlf.zza((Object) t, j, zzlf.zzb(t2, j));
                            zzb((zzjr<T>) t, i);
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
                        zzkf.zza(this.zzs, t, t2, j);
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
                        if (zza((zzjr<T>) t2, i2, i)) {
                            zzlf.zza(t, j, zzlf.zzf(t2, j));
                            zzb((zzjr<T>) t, i2, i);
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
                        if (zza((zzjr<T>) t2, i2, i)) {
                            zzlf.zza(t, j, zzlf.zzf(t2, j));
                            zzb((zzjr<T>) t, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzkf.zza(this.zzq, t, t2);
            if (this.zzh) {
                zzkf.zza(this.zzr, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    private final void zza(T t, T t2, int i) {
        long zzd = zzd(i) & 1048575;
        if (zza((zzjr<T>) t2, i)) {
            Object zzf = zzlf.zzf(t, zzd);
            Object zzf2 = zzlf.zzf(t2, zzd);
            if (zzf != null && zzf2 != null) {
                zzlf.zza(t, zzd, zzii.zza(zzf, zzf2));
                zzb((zzjr<T>) t, i);
            } else if (zzf2 != null) {
                zzlf.zza(t, zzd, zzf2);
                zzb((zzjr<T>) t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzd = zzd(i);
        int i2 = this.zzc[i];
        long j = zzd & 1048575;
        if (zza((zzjr<T>) t2, i2, i)) {
            Object zzf = zzlf.zzf(t, j);
            Object zzf2 = zzlf.zzf(t2, j);
            if (zzf != null && zzf2 != null) {
                zzlf.zza(t, j, zzii.zza(zzf, zzf2));
                zzb((zzjr<T>) t, i2, i);
            } else if (zzf2 != null) {
                zzlf.zza(t, j, zzf2);
                zzb((zzjr<T>) t, i2, i);
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
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
                if (i7 < zzia.DOUBLE_LIST_PACKED.zza() || i7 > zzia.SINT64_LIST_PACKED.zza()) {
                    i3 = 0;
                } else {
                    i3 = this.zzc[i5 + 2] & 1048575;
                }
                switch (i7) {
                    case 0:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzb(i8, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzb(i8, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzd(i8, zzlf.zzb(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zze(i8, zzlf.zzb(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzf(i8, zzlf.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzg(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzi(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzb(i8, true);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zza((zzjr<T>) t, i5)) {
                            Object zzf = zzlf.zzf(t, j2);
                            if (zzf instanceof zzgv) {
                                i6 += zzhm.zzc(i8, (zzgv) zzf);
                                break;
                            } else {
                                i6 += zzhm.zzb(i8, (String) zzf);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzkf.zza(i8, zzlf.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzc(i8, (zzgv) zzlf.zzf(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzg(i8, zzlf.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzk(i8, zzlf.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzj(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzh(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzh(i8, zzlf.zza(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzf(i8, zzlf.zzb(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zza((zzjr<T>) t, i5)) {
                            i6 += zzhm.zzc(i8, (zzjn) zzlf.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        i6 += zzkf.zzi(i8, zza(t, j2), false);
                        break;
                    case 19:
                        i6 += zzkf.zzh(i8, zza(t, j2), false);
                        break;
                    case 20:
                        i6 += zzkf.zza(i8, (List<Long>) zza(t, j2), false);
                        break;
                    case 21:
                        i6 += zzkf.zzb(i8, (List<Long>) zza(t, j2), false);
                        break;
                    case 22:
                        i6 += zzkf.zze(i8, zza(t, j2), false);
                        break;
                    case 23:
                        i6 += zzkf.zzi(i8, zza(t, j2), false);
                        break;
                    case 24:
                        i6 += zzkf.zzh(i8, zza(t, j2), false);
                        break;
                    case 25:
                        i6 += zzkf.zzj(i8, zza(t, j2), false);
                        break;
                    case 26:
                        i6 += zzkf.zza(i8, zza(t, j2));
                        break;
                    case 27:
                        i6 += zzkf.zza(i8, zza(t, j2), zza(i5));
                        break;
                    case 28:
                        i6 += zzkf.zzb(i8, zza(t, j2));
                        break;
                    case 29:
                        i6 += zzkf.zzf(i8, zza(t, j2), false);
                        break;
                    case 30:
                        i6 += zzkf.zzd(i8, zza(t, j2), false);
                        break;
                    case 31:
                        i6 += zzkf.zzh(i8, zza(t, j2), false);
                        break;
                    case 32:
                        i6 += zzkf.zzi(i8, zza(t, j2), false);
                        break;
                    case 33:
                        i6 += zzkf.zzg(i8, zza(t, j2), false);
                        break;
                    case 34:
                        i6 += zzkf.zzc(i8, zza(t, j2), false);
                        break;
                    case 35:
                        int zzi = zzkf.zzi((List) unsafe.getObject(t, j2));
                        if (zzi <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzi);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzi) + zzi;
                            break;
                        }
                    case 36:
                        int zzh = zzkf.zzh((List) unsafe.getObject(t, j2));
                        if (zzh <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzh);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzh) + zzh;
                            break;
                        }
                    case 37:
                        int zza2 = zzkf.zza((List) unsafe.getObject(t, j2));
                        if (zza2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zza2);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zza2) + zza2;
                            break;
                        }
                    case 38:
                        int zzb2 = zzkf.zzb((List) unsafe.getObject(t, j2));
                        if (zzb2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzb2);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzb2) + zzb2;
                            break;
                        }
                    case 39:
                        int zze = zzkf.zze((List) unsafe.getObject(t, j2));
                        if (zze <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zze);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zze) + zze;
                            break;
                        }
                    case 40:
                        int zzi2 = zzkf.zzi((List) unsafe.getObject(t, j2));
                        if (zzi2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzi2);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzi2) + zzi2;
                            break;
                        }
                    case 41:
                        int zzh2 = zzkf.zzh((List) unsafe.getObject(t, j2));
                        if (zzh2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzh2);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzh2) + zzh2;
                            break;
                        }
                    case 42:
                        int zzj = zzkf.zzj((List) unsafe.getObject(t, j2));
                        if (zzj <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzj);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzj) + zzj;
                            break;
                        }
                    case 43:
                        int zzf2 = zzkf.zzf((List) unsafe.getObject(t, j2));
                        if (zzf2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzf2);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzf2) + zzf2;
                            break;
                        }
                    case 44:
                        int zzd2 = zzkf.zzd((List) unsafe.getObject(t, j2));
                        if (zzd2 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzd2);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzd2) + zzd2;
                            break;
                        }
                    case 45:
                        int zzh3 = zzkf.zzh((List) unsafe.getObject(t, j2));
                        if (zzh3 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzh3);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzh3) + zzh3;
                            break;
                        }
                    case 46:
                        int zzi3 = zzkf.zzi((List) unsafe.getObject(t, j2));
                        if (zzi3 <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzi3);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzi3) + zzi3;
                            break;
                        }
                    case 47:
                        int zzg = zzkf.zzg((List) unsafe.getObject(t, j2));
                        if (zzg <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzg);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzg) + zzg;
                            break;
                        }
                    case 48:
                        int zzc = zzkf.zzc((List) unsafe.getObject(t, j2));
                        if (zzc <= 0) {
                            break;
                        } else {
                            if (this.zzk) {
                                unsafe.putInt(t, i3, zzc);
                            }
                            i6 += zzhm.zze(i8) + zzhm.zzg(zzc) + zzc;
                            break;
                        }
                    case 49:
                        i6 += zzkf.zzb(i8, (List<zzjn>) zza(t, j2), zza(i5));
                        break;
                    case 50:
                        i6 += this.zzs.zza(i8, zzlf.zzf(t, j2), zzb(i5));
                        break;
                    case 51:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzb(i8, 0.0d);
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzb(i8, 0.0f);
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzd(i8, zze(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zze(i8, zze(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzf(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzg(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzi(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzb(i8, true);
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            Object zzf3 = zzlf.zzf(t, j2);
                            if (zzf3 instanceof zzgv) {
                                i6 += zzhm.zzc(i8, (zzgv) zzf3);
                                break;
                            } else {
                                i6 += zzhm.zzb(i8, (String) zzf3);
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzkf.zza(i8, zzlf.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzc(i8, (zzgv) zzlf.zzf(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzg(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzk(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzj(i8, 0);
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzh(i8, 0L);
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzh(i8, zzd(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzf(i8, zze(t, j2));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zza((zzjr<T>) t, i8, i5)) {
                            i6 += zzhm.zzc(i8, (zzjn) zzlf.zzf(t, j2), zza(i5));
                            break;
                        } else {
                            break;
                        }
                }
                i5 += 3;
                i4 = 267386880;
            }
            return i6 + zza((zzkz) this.zzq, (Object) t);
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
            } else if (!this.zzk || i14 < zzia.DOUBLE_LIST_PACKED.zza() || i14 > zzia.SINT64_LIST_PACKED.zza()) {
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
                        i9 += zzhm.zzb(i13, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzb(i13, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzd(i13, unsafe2.getLong(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zze(i13, unsafe2.getLong(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    j = 0;
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzf(i13, unsafe2.getInt(t, j3));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if ((i11 & i) != 0) {
                        j = 0;
                        i9 += zzhm.zzg(i13, 0L);
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 6:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzi(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 7:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzb(i13, true);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 8:
                    if ((i11 & i) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzgv) {
                            i9 += zzhm.zzc(i13, (zzgv) object);
                            j = 0;
                            break;
                        } else {
                            i9 += zzhm.zzb(i13, (String) object);
                            j = 0;
                            break;
                        }
                    } else {
                        j = 0;
                        break;
                    }
                case 9:
                    if ((i11 & i) != 0) {
                        i9 += zzkf.zza(i13, unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 10:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzc(i13, (zzgv) unsafe2.getObject(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 11:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzg(i13, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 12:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzk(i13, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 13:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzj(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 14:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzh(i13, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 15:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzh(i13, unsafe2.getInt(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 16:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzf(i13, unsafe2.getLong(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 17:
                    if ((i11 & i) != 0) {
                        i9 += zzhm.zzc(i13, (zzjn) unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 18:
                    i9 += zzkf.zzi(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 19:
                    i9 += zzkf.zzh(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 20:
                    i9 += zzkf.zza(i13, (List<Long>) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 21:
                    i9 += zzkf.zzb(i13, (List<Long>) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 22:
                    i9 += zzkf.zze(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 23:
                    i9 += zzkf.zzi(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 24:
                    i9 += zzkf.zzh(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 25:
                    i9 += zzkf.zzj(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 26:
                    i9 += zzkf.zza(i13, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 27:
                    i9 += zzkf.zza(i13, (List<?>) unsafe2.getObject(t, j3), zza(i12));
                    j = 0;
                    break;
                case 28:
                    i9 += zzkf.zzb(i13, (List) unsafe2.getObject(t, j3));
                    j = 0;
                    break;
                case 29:
                    i9 += zzkf.zzf(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 30:
                    i9 += zzkf.zzd(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 31:
                    i9 += zzkf.zzh(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 32:
                    i9 += zzkf.zzi(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 33:
                    i9 += zzkf.zzg(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 34:
                    i9 += zzkf.zzc(i13, (List) unsafe2.getObject(t, j3), false);
                    j = 0;
                    break;
                case 35:
                    int zzi4 = zzkf.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzi4);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzi4) + zzi4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 36:
                    int zzh4 = zzkf.zzh((List) unsafe2.getObject(t, j3));
                    if (zzh4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzh4);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzh4) + zzh4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 37:
                    int zza3 = zzkf.zza((List) unsafe2.getObject(t, j3));
                    if (zza3 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zza3);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zza3) + zza3;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 38:
                    int zzb3 = zzkf.zzb((List) unsafe2.getObject(t, j3));
                    if (zzb3 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzb3);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzb3) + zzb3;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 39:
                    int zze2 = zzkf.zze((List) unsafe2.getObject(t, j3));
                    if (zze2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zze2);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zze2) + zze2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 40:
                    int zzi5 = zzkf.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi5 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzi5);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzi5) + zzi5;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 41:
                    int zzh5 = zzkf.zzh((List) unsafe2.getObject(t, j3));
                    if (zzh5 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzh5);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzh5) + zzh5;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 42:
                    int zzj2 = zzkf.zzj((List) unsafe2.getObject(t, j3));
                    if (zzj2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzj2);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzj2) + zzj2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 43:
                    int zzf4 = zzkf.zzf((List) unsafe2.getObject(t, j3));
                    if (zzf4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzf4);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzf4) + zzf4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 44:
                    int zzd4 = zzkf.zzd((List) unsafe2.getObject(t, j3));
                    if (zzd4 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzd4);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzd4) + zzd4;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 45:
                    int zzh6 = zzkf.zzh((List) unsafe2.getObject(t, j3));
                    if (zzh6 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzh6);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzh6) + zzh6;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 46:
                    int zzi6 = zzkf.zzi((List) unsafe2.getObject(t, j3));
                    if (zzi6 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzi6);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzi6) + zzi6;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 47:
                    int zzg2 = zzkf.zzg((List) unsafe2.getObject(t, j3));
                    if (zzg2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzg2);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzg2) + zzg2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 48:
                    int zzc2 = zzkf.zzc((List) unsafe2.getObject(t, j3));
                    if (zzc2 > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i2, zzc2);
                        }
                        i9 += zzhm.zze(i13) + zzhm.zzg(zzc2) + zzc2;
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 49:
                    i9 += zzkf.zzb(i13, (List) unsafe2.getObject(t, j3), zza(i12));
                    j = 0;
                    break;
                case 50:
                    i9 += this.zzs.zza(i13, unsafe2.getObject(t, j3), zzb(i12));
                    j = 0;
                    break;
                case 51:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzb(i13, 0.0d);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 52:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzb(i13, 0.0f);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 53:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzd(i13, zze(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 54:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zze(i13, zze(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 55:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzf(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 56:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzg(i13, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 57:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzi(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 58:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzb(i13, true);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 59:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzgv) {
                            i9 += zzhm.zzc(i13, (zzgv) object2);
                            j = 0;
                            break;
                        } else {
                            i9 += zzhm.zzb(i13, (String) object2);
                            j = 0;
                            break;
                        }
                    } else {
                        j = 0;
                        break;
                    }
                case 60:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzkf.zza(i13, unsafe2.getObject(t, j3), zza(i12));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 61:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzc(i13, (zzgv) unsafe2.getObject(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 62:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzg(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 63:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzk(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 64:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzj(i13, 0);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 65:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzh(i13, 0L);
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 66:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzh(i13, zzd(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 67:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzf(i13, zze(t, j3));
                        j = 0;
                        break;
                    } else {
                        j = 0;
                        break;
                    }
                case 68:
                    if (zza((zzjr<T>) t, i13, i12)) {
                        i9 += zzhm.zzc(i13, (zzjn) unsafe2.getObject(t, j3), zza(i12));
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
        int zza4 = i9 + zza((zzkz) this.zzq, (Object) t);
        if (!this.zzh) {
            return zza4;
        }
        zzhv<?> zza5 = this.zzr.zza(t);
        for (int i18 = 0; i18 < zza5.zza.zzc(); i18++) {
            Map.Entry<?, Object> zzb4 = zza5.zza.zzb(i18);
            i17 += zzhv.zza((zzhx) zzb4.getKey(), zzb4.getValue());
        }
        for (Map.Entry<?, Object> entry : zza5.zza.zzd()) {
            i17 += zzhv.zza((zzhx) entry.getKey(), entry.getValue());
        }
        return zza4 + i17;
    }

    private static <UT, UB> int zza(zzkz<UT, UB> zzkzVar, T t) {
        return zzkzVar.zzf(zzkzVar.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzlf.zzf(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0b5e  */
    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r14, com.google.android.gms.internal.firebase_auth.zzls r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjr.zza(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzls):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0559  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzb(T r18, com.google.android.gms.internal.firebase_auth.zzls r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjr.zzb(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzls):void");
    }

    private final <K, V> void zza(zzls zzlsVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzlsVar.zza(i, this.zzs.zzb(zzb(i2)), this.zzs.zzc(obj));
        }
    }

    private static <UT, UB> void zza(zzkz<UT, UB> zzkzVar, T t, zzls zzlsVar) throws IOException {
        zzkzVar.zza((zzkz<UT, UB>) zzkzVar.zzb(t), zzlsVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zza(T t, zzke zzkeVar, zzhs zzhsVar) throws IOException {
        int i;
        Object obj;
        zzhv<?> zzhvVar;
        if (zzhsVar != null) {
            zzkz zzkzVar = this.zzq;
            zzhu<?> zzhuVar = this.zzr;
            zzhv<?> zzhvVar2 = null;
            Object obj2 = null;
            while (true) {
                try {
                    int zza2 = zzkeVar.zza();
                    if (zza2 < this.zze || zza2 > this.zzf) {
                        i = -1;
                    } else {
                        int i2 = 0;
                        int length = (this.zzc.length / 3) - 1;
                        while (true) {
                            if (i2 > length) {
                                i = -1;
                            } else {
                                int i3 = (length + i2) >>> 1;
                                int i4 = i3 * 3;
                                int i5 = this.zzc[i4];
                                if (zza2 == i5) {
                                    i = i4;
                                } else if (zza2 < i5) {
                                    length = i3 - 1;
                                } else {
                                    i2 = i3 + 1;
                                }
                            }
                        }
                    }
                    if (i >= 0) {
                        int zzd = zzd(i);
                        switch ((267386880 & zzd) >>> 20) {
                            case 0:
                                zzlf.zza(t, zzd & 1048575, zzkeVar.zzd());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 1:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zze());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 2:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzg());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 3:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzf());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 4:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzh());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 5:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzi());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 6:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzj());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 7:
                                zzlf.zza(t, zzd & 1048575, zzkeVar.zzk());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 8:
                                zza(t, zzd, zzkeVar);
                                zzb((zzjr<T>) t, i);
                                break;
                            case 9:
                                if (zza((zzjr<T>) t, i)) {
                                    long j = zzd & 1048575;
                                    zzlf.zza(t, j, zzii.zza(zzlf.zzf(t, j), zzkeVar.zza(zza(i), zzhsVar)));
                                    break;
                                } else {
                                    zzlf.zza(t, zzd & 1048575, zzkeVar.zza(zza(i), zzhsVar));
                                    zzb((zzjr<T>) t, i);
                                    break;
                                }
                            case 10:
                                zzlf.zza(t, zzd & 1048575, zzkeVar.zzn());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 11:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzo());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 12:
                                int zzp = zzkeVar.zzp();
                                zzij zzc = zzc(i);
                                if (zzc != null && !zzc.zza(zzp)) {
                                    obj2 = zzkf.zza(zza2, zzp, obj2, zzkzVar);
                                    break;
                                }
                                zzlf.zza((Object) t, zzd & 1048575, zzp);
                                zzb((zzjr<T>) t, i);
                                break;
                            case 13:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzq());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 14:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzr());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 15:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzs());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 16:
                                zzlf.zza((Object) t, zzd & 1048575, zzkeVar.zzt());
                                zzb((zzjr<T>) t, i);
                                break;
                            case 17:
                                if (zza((zzjr<T>) t, i)) {
                                    long j2 = zzd & 1048575;
                                    zzlf.zza(t, j2, zzii.zza(zzlf.zzf(t, j2), zzkeVar.zzb(zza(i), zzhsVar)));
                                    break;
                                } else {
                                    zzlf.zza(t, zzd & 1048575, zzkeVar.zzb(zza(i), zzhsVar));
                                    zzb((zzjr<T>) t, i);
                                    break;
                                }
                            case 18:
                                zzkeVar.zza(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 19:
                                zzkeVar.zzb(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 20:
                                zzkeVar.zzd(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 21:
                                zzkeVar.zzc(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 22:
                                zzkeVar.zze(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 23:
                                zzkeVar.zzf(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 24:
                                zzkeVar.zzg(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 25:
                                zzkeVar.zzh(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 26:
                                if (zzf(zzd)) {
                                    zzkeVar.zzj(this.zzp.zza(t, zzd & 1048575));
                                    break;
                                } else {
                                    zzkeVar.zzi(this.zzp.zza(t, zzd & 1048575));
                                    break;
                                }
                            case 27:
                                zzkeVar.zza(this.zzp.zza(t, zzd & 1048575), zza(i), zzhsVar);
                                break;
                            case 28:
                                zzkeVar.zzk(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 29:
                                zzkeVar.zzl(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 30:
                                List<Integer> zza3 = this.zzp.zza(t, zzd & 1048575);
                                zzkeVar.zzm(zza3);
                                obj2 = zzkf.zza(zza2, zza3, zzc(i), obj2, zzkzVar);
                                break;
                            case 31:
                                zzkeVar.zzn(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 32:
                                zzkeVar.zzo(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 33:
                                zzkeVar.zzp(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 34:
                                zzkeVar.zzq(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 35:
                                zzkeVar.zza(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 36:
                                zzkeVar.zzb(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 37:
                                zzkeVar.zzd(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 38:
                                zzkeVar.zzc(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 39:
                                zzkeVar.zze(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 40:
                                zzkeVar.zzf(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 41:
                                zzkeVar.zzg(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 42:
                                zzkeVar.zzh(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 43:
                                zzkeVar.zzl(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 44:
                                List<Integer> zza4 = this.zzp.zza(t, zzd & 1048575);
                                zzkeVar.zzm(zza4);
                                obj2 = zzkf.zza(zza2, zza4, zzc(i), obj2, zzkzVar);
                                break;
                            case 45:
                                zzkeVar.zzn(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 46:
                                zzkeVar.zzo(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 47:
                                zzkeVar.zzp(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 48:
                                zzkeVar.zzq(this.zzp.zza(t, zzd & 1048575));
                                break;
                            case 49:
                                zzkeVar.zzb(this.zzp.zza(t, zzd & 1048575), zza(i), zzhsVar);
                                break;
                            case 50:
                                Object zzb2 = zzb(i);
                                long zzd2 = zzd(i) & 1048575;
                                Object zzf = zzlf.zzf(t, zzd2);
                                if (zzf == null) {
                                    zzf = this.zzs.zzf(zzb2);
                                    zzlf.zza(t, zzd2, zzf);
                                } else if (this.zzs.zzd(zzf)) {
                                    Object zzf2 = this.zzs.zzf(zzb2);
                                    this.zzs.zza(zzf2, zzf);
                                    zzlf.zza(t, zzd2, zzf2);
                                    zzf = zzf2;
                                }
                                zzkeVar.zza(this.zzs.zza(zzf), this.zzs.zzb(zzb2), zzhsVar);
                                break;
                            case 51:
                                zzlf.zza(t, zzd & 1048575, Double.valueOf(zzkeVar.zzd()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 52:
                                zzlf.zza(t, zzd & 1048575, Float.valueOf(zzkeVar.zze()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 53:
                                zzlf.zza(t, zzd & 1048575, Long.valueOf(zzkeVar.zzg()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 54:
                                zzlf.zza(t, zzd & 1048575, Long.valueOf(zzkeVar.zzf()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 55:
                                zzlf.zza(t, zzd & 1048575, Integer.valueOf(zzkeVar.zzh()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 56:
                                zzlf.zza(t, zzd & 1048575, Long.valueOf(zzkeVar.zzi()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 57:
                                zzlf.zza(t, zzd & 1048575, Integer.valueOf(zzkeVar.zzj()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 58:
                                zzlf.zza(t, zzd & 1048575, Boolean.valueOf(zzkeVar.zzk()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 59:
                                zza(t, zzd, zzkeVar);
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 60:
                                if (zza((zzjr<T>) t, zza2, i)) {
                                    long j3 = zzd & 1048575;
                                    zzlf.zza(t, j3, zzii.zza(zzlf.zzf(t, j3), zzkeVar.zza(zza(i), zzhsVar)));
                                } else {
                                    zzlf.zza(t, zzd & 1048575, zzkeVar.zza(zza(i), zzhsVar));
                                    zzb((zzjr<T>) t, i);
                                }
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 61:
                                zzlf.zza(t, zzd & 1048575, zzkeVar.zzn());
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 62:
                                zzlf.zza(t, zzd & 1048575, Integer.valueOf(zzkeVar.zzo()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 63:
                                int zzp2 = zzkeVar.zzp();
                                zzij zzc2 = zzc(i);
                                if (zzc2 != null && !zzc2.zza(zzp2)) {
                                    obj2 = zzkf.zza(zza2, zzp2, obj2, zzkzVar);
                                    break;
                                }
                                zzlf.zza(t, zzd & 1048575, Integer.valueOf(zzp2));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 64:
                                zzlf.zza(t, zzd & 1048575, Integer.valueOf(zzkeVar.zzq()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 65:
                                zzlf.zza(t, zzd & 1048575, Long.valueOf(zzkeVar.zzr()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 66:
                                zzlf.zza(t, zzd & 1048575, Integer.valueOf(zzkeVar.zzs()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 67:
                                zzlf.zza(t, zzd & 1048575, Long.valueOf(zzkeVar.zzt()));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            case 68:
                                zzlf.zza(t, zzd & 1048575, zzkeVar.zzb(zza(i), zzhsVar));
                                zzb((zzjr<T>) t, zza2, i);
                                break;
                            default:
                                if (obj2 == null) {
                                    try {
                                        obj2 = zzkzVar.zza();
                                    } catch (zziq e) {
                                        zzkzVar.zza(zzkeVar);
                                        if (obj2 == null) {
                                            obj2 = zzkzVar.zzc(t);
                                        }
                                        if (zzkzVar.zza((zzkz) obj2, zzkeVar)) {
                                            break;
                                        } else {
                                            for (int i6 = this.zzm; i6 < this.zzn; i6++) {
                                                obj2 = zza((Object) t, this.zzl[i6], (int) obj2, (zzkz<UT, int>) zzkzVar);
                                            }
                                            if (obj2 != null) {
                                                zzkzVar.zzb((Object) t, (T) obj2);
                                                return;
                                            }
                                            return;
                                        }
                                    }
                                }
                                if (zzkzVar.zza((zzkz) obj2, zzkeVar)) {
                                    break;
                                } else {
                                    for (int i7 = this.zzm; i7 < this.zzn; i7++) {
                                        obj2 = zza((Object) t, this.zzl[i7], (int) obj2, (zzkz<UT, int>) zzkzVar);
                                    }
                                    if (obj2 != null) {
                                        zzkzVar.zzb((Object) t, (T) obj2);
                                        return;
                                    }
                                    return;
                                }
                        }
                    } else if (zza2 == Integer.MAX_VALUE) {
                        for (int i8 = this.zzm; i8 < this.zzn; i8++) {
                            obj2 = zza((Object) t, this.zzl[i8], (int) obj2, (zzkz<UT, int>) zzkzVar);
                        }
                        if (obj2 != null) {
                            zzkzVar.zzb((Object) t, (T) obj2);
                            return;
                        }
                        return;
                    } else {
                        if (!this.zzh) {
                            obj = null;
                        } else {
                            obj = zzhuVar.zza(zzhsVar, this.zzg, zza2);
                        }
                        if (obj != null) {
                            if (zzhvVar2 == null) {
                                zzhvVar = zzhuVar.zzb(t);
                            } else {
                                zzhvVar = zzhvVar2;
                            }
                            obj2 = zzhuVar.zza(zzkeVar, obj, zzhsVar, zzhvVar, obj2, zzkzVar);
                            zzhvVar2 = zzhvVar;
                        } else {
                            zzkzVar.zza(zzkeVar);
                            if (obj2 == null) {
                                obj2 = zzkzVar.zzc(t);
                            }
                            if (!zzkzVar.zza((zzkz) obj2, zzkeVar)) {
                                for (int i9 = this.zzm; i9 < this.zzn; i9++) {
                                    obj2 = zza((Object) t, this.zzl[i9], (int) obj2, (zzkz<UT, int>) zzkzVar);
                                }
                                if (obj2 != null) {
                                    zzkzVar.zzb((Object) t, (T) obj2);
                                    return;
                                }
                                return;
                            }
                        }
                    }
                } catch (Throwable th) {
                    for (int i10 = this.zzm; i10 < this.zzn; i10++) {
                        obj2 = zza((Object) t, this.zzl[i10], (int) obj2, (zzkz<UT, int>) zzkzVar);
                    }
                    if (obj2 != null) {
                        zzkzVar.zzb((Object) t, (T) obj2);
                    }
                    throw th;
                }
            }
        } else {
            throw null;
        }
    }

    private final zzkd zza(int i) {
        int i2 = (i / 3) << 1;
        zzkd zzkdVar = (zzkd) this.zzd[i2];
        if (zzkdVar != null) {
            return zzkdVar;
        }
        zzkd<T> zza2 = zzjz.zza().zza((Class) ((Class) this.zzd[i2 + 1]));
        this.zzd[i2] = zza2;
        return zza2;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzij zzc(int i) {
        return (zzij) this.zzd[((i / 3) << 1) + 1];
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zzc(T t) {
        int i;
        int i2 = this.zzm;
        while (true) {
            i = this.zzn;
            if (i2 >= i) {
                break;
            }
            long zzd = zzd(this.zzl[i2]) & 1048575;
            Object zzf = zzlf.zzf(t, zzd);
            if (zzf != null) {
                zzlf.zza(t, zzd, this.zzs.zze(zzf));
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

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzkz<UT, UB> zzkzVar) {
        int i2 = this.zzc[i];
        Object zzf = zzlf.zzf(obj, zzd(i) & 1048575);
        if (zzf == null) {
            return ub;
        }
        zzij zzc = zzc(i);
        if (zzc == null) {
            return ub;
        }
        return (UB) zza(i, i2, this.zzs.zza(zzf), zzc, ub, zzkzVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzij zzijVar, UB ub, zzkz<UT, UB> zzkzVar) {
        zzji<?, ?> zzb2 = this.zzs.zzb(zzb(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzijVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzkzVar.zza();
                }
                zzhd zzc = zzgv.zzc(zzjf.zza(zzb2, next.getKey(), next.getValue()));
                try {
                    zzjf.zza(zzc.zzb(), zzb2, next.getKey(), next.getValue());
                    zzkzVar.zza((zzkz<UT, UB>) ub, i2, zzc.zza());
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
    /* JADX WARN: Type inference failed for: r1v23, types: [com.google.android.gms.internal.firebase_auth.zzkd] */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.google.android.gms.internal.firebase_auth.zzkd] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzd(T r19) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjr.zzd(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzkd zzkdVar) {
        return zzkdVar.zzd(zzlf.zzf(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzls zzlsVar) throws IOException {
        if (obj instanceof String) {
            zzlsVar.zza(i, (String) obj);
        } else {
            zzlsVar.zza(i, (zzgv) obj);
        }
    }

    private final void zza(Object obj, int i, zzke zzkeVar) throws IOException {
        if (zzf(i)) {
            zzlf.zza(obj, i & 1048575, zzkeVar.zzm());
        } else if (this.zzi) {
            zzlf.zza(obj, i & 1048575, zzkeVar.zzl());
        } else {
            zzlf.zza(obj, i & 1048575, zzkeVar.zzn());
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
        return ((Double) zzlf.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzlf.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzlf.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzlf.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzlf.zzf(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((zzjr<T>) t, i) == zza((zzjr<T>) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zza((zzjr<T>) t, i);
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
                    return zzlf.zze(t, j2) != 0.0d;
                case 1:
                    return zzlf.zzd(t, j2) != 0.0f;
                case 2:
                    return zzlf.zzb(t, j2) != 0;
                case 3:
                    return zzlf.zzb(t, j2) != 0;
                case 4:
                    return zzlf.zza(t, j2) != 0;
                case 5:
                    return zzlf.zzb(t, j2) != 0;
                case 6:
                    return zzlf.zza(t, j2) != 0;
                case 7:
                    return zzlf.zzc(t, j2);
                case 8:
                    Object zzf = zzlf.zzf(t, j2);
                    if (zzf instanceof String) {
                        return !((String) zzf).isEmpty();
                    }
                    if (zzf instanceof zzgv) {
                        return !zzgv.zza.equals(zzf);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzlf.zzf(t, j2) != null;
                case 10:
                    return !zzgv.zza.equals(zzlf.zzf(t, j2));
                case 11:
                    return zzlf.zza(t, j2) != 0;
                case 12:
                    return zzlf.zza(t, j2) != 0;
                case 13:
                    return zzlf.zza(t, j2) != 0;
                case 14:
                    return zzlf.zzb(t, j2) != 0;
                case 15:
                    return zzlf.zza(t, j2) != 0;
                case 16:
                    return zzlf.zzb(t, j2) != 0;
                case 17:
                    return zzlf.zzf(t, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzlf.zza(t, j) & (1 << (zze >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        int zze = zze(i);
        long j = 1048575 & zze;
        if (j != 1048575) {
            zzlf.zza((Object) t, j, (1 << (zze >>> 20)) | zzlf.zza(t, j));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzlf.zza(t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzlf.zza((Object) t, zze(i2) & 1048575, i);
    }
}
