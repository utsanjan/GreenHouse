package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzec {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzeb zzebVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza(b, bArr, i2, zzebVar);
        }
        zzebVar.zza = b;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, zzeb zzebVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzebVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzebVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzebVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzebVar.zza = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzebVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i, zzeb zzebVar) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzebVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (b & Byte.MAX_VALUE) << i4;
            b2 = bArr[i3];
            i3 = i5;
        }
        zzebVar.zzb = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzb(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzc(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzb(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzd(byte[] bArr, int i) {
        return Float.intBitsToFloat(zza(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int i, zzeb zzebVar) throws zzfw {
        int zza = zza(bArr, i, zzebVar);
        int i2 = zzebVar.zza;
        if (i2 < 0) {
            throw zzfw.zzb();
        } else if (i2 == 0) {
            zzebVar.zzc = "";
            return zza;
        } else {
            zzebVar.zzc = new String(bArr, zza, i2, zzfr.zza);
            return zza + i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(byte[] bArr, int i, zzeb zzebVar) throws zzfw {
        int zza = zza(bArr, i, zzebVar);
        int i2 = zzebVar.zza;
        if (i2 < 0) {
            throw zzfw.zzb();
        } else if (i2 == 0) {
            zzebVar.zzc = "";
            return zza;
        } else {
            zzebVar.zzc = zzip.zzb(bArr, zza, i2);
            return zza + i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(byte[] bArr, int i, zzeb zzebVar) throws zzfw {
        int zza = zza(bArr, i, zzebVar);
        int i2 = zzebVar.zza;
        if (i2 < 0) {
            throw zzfw.zzb();
        } else if (i2 > bArr.length - zza) {
            throw zzfw.zza();
        } else if (i2 == 0) {
            zzebVar.zzc = zzeg.zza;
            return zza;
        } else {
            zzebVar.zzc = zzeg.zza(bArr, zza, i2);
            return zza + i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzhp zzhpVar, byte[] bArr, int i, int i2, zzeb zzebVar) throws IOException {
        byte b;
        int i3;
        int i4 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            i3 = zza(b2, bArr, i4, zzebVar);
            b = zzebVar.zza;
        } else {
            i3 = i4;
            b = b2;
        }
        if (b < 0 || b > i2 - i3) {
            throw zzfw.zza();
        }
        Object zza = zzhpVar.zza();
        int i5 = (b == 1 ? 1 : 0) + i3;
        zzhpVar.zza(zza, bArr, i3, i5, zzebVar);
        zzhpVar.zzc(zza);
        zzebVar.zzc = zza;
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzhp zzhpVar, byte[] bArr, int i, int i2, int i3, zzeb zzebVar) throws IOException {
        zzha zzhaVar = (zzha) zzhpVar;
        Object zza = zzhaVar.zza();
        int zza2 = zzhaVar.zza((zzha) zza, bArr, i, i2, i3, zzebVar);
        zzhaVar.zzc((zzha) zza);
        zzebVar.zzc = zza;
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3, zzfx<?> zzfxVar, zzeb zzebVar) {
        zzfp zzfpVar = (zzfp) zzfxVar;
        int zza = zza(bArr, i2, zzebVar);
        zzfpVar.zzd(zzebVar.zza);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzebVar);
            if (i != zzebVar.zza) {
                break;
            }
            zza = zza(bArr, zza2, zzebVar);
            zzfpVar.zzd(zzebVar.zza);
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int i, zzfx<?> zzfxVar, zzeb zzebVar) throws IOException {
        zzfp zzfpVar = (zzfp) zzfxVar;
        int zza = zza(bArr, i, zzebVar);
        int i2 = zzebVar.zza + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, zzebVar);
            zzfpVar.zzd(zzebVar.zza);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzfw.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzhp<?> zzhpVar, int i, byte[] bArr, int i2, int i3, zzfx<?> zzfxVar, zzeb zzebVar) throws IOException {
        int zza = zza(zzhpVar, bArr, i2, i3, zzebVar);
        zzfxVar.add(zzebVar.zzc);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, zzebVar);
            if (i != zzebVar.zza) {
                break;
            }
            zza = zza(zzhpVar, bArr, zza2, i3, zzebVar);
            zzfxVar.add(zzebVar.zzc);
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3, zzig zzigVar, zzeb zzebVar) throws zzfw {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzb = zzb(bArr, i2, zzebVar);
                zzigVar.zza(i, Long.valueOf(zzebVar.zzb));
                return zzb;
            } else if (i4 == 1) {
                zzigVar.zza(i, Long.valueOf(zzb(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zza = zza(bArr, i2, zzebVar);
                int i5 = zzebVar.zza;
                if (i5 < 0) {
                    throw zzfw.zzb();
                } else if (i5 <= bArr.length - zza) {
                    if (i5 == 0) {
                        zzigVar.zza(i, zzeg.zza);
                    } else {
                        zzigVar.zza(i, zzeg.zza(bArr, zza, i5));
                    }
                    return zza + i5;
                } else {
                    throw zzfw.zza();
                }
            } else if (i4 == 3) {
                zzig zzb2 = zzig.zzb();
                int i6 = (i & (-8)) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zza2 = zza(bArr, i2, zzebVar);
                    int i8 = zzebVar.zza;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = zza2;
                        break;
                    }
                    i7 = i8;
                    i2 = zza(i8, bArr, zza2, i3, zzb2, zzebVar);
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzfw.zzg();
                }
                zzigVar.zza(i, zzb2);
                return i2;
            } else if (i4 == 5) {
                zzigVar.zza(i, Integer.valueOf(zza(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzfw.zzd();
            }
        } else {
            throw zzfw.zzd();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i, byte[] bArr, int i2, int i3, zzeb zzebVar) throws zzfw {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzb(bArr, i2, zzebVar);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zza(bArr, i2, zzebVar) + zzebVar.zza;
            }
            if (i4 == 3) {
                int i5 = (i & (-8)) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zza(bArr, i2, zzebVar);
                    i6 = zzebVar.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzebVar);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzfw.zzg();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzfw.zzd();
            }
        } else {
            throw zzfw.zzd();
        }
    }
}
