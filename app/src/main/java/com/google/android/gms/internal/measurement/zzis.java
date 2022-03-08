package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzis extends zzir {
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0063, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00be, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.measurement.zzir
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zza(int r16, byte[] r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzis.zza(int, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzir
    public final String zza(byte[] bArr, int i, int i2) throws zzfw {
        boolean zzd;
        boolean zzd2;
        boolean zze;
        boolean zzf;
        boolean zzd3;
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte zza = zzin.zza(bArr, i);
                zzd3 = zzio.zzd(zza);
                if (!zzd3) {
                    break;
                }
                i++;
                zzio.zzb(zza, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte zza2 = zzin.zza(bArr, i);
                zzd = zzio.zzd(zza2);
                if (zzd) {
                    int i7 = i5 + 1;
                    zzio.zzb(zza2, cArr, i5);
                    while (i6 < i3) {
                        byte zza3 = zzin.zza(bArr, i6);
                        zzd2 = zzio.zzd(zza3);
                        if (!zzd2) {
                            break;
                        }
                        i6++;
                        zzio.zzb(zza3, cArr, i7);
                        i7++;
                    }
                    i = i6;
                    i5 = i7;
                } else {
                    zze = zzio.zze(zza2);
                    if (!zze) {
                        zzf = zzio.zzf(zza2);
                        if (zzf) {
                            if (i6 < i3 - 1) {
                                int i8 = i6 + 1;
                                i = i8 + 1;
                                i5++;
                                zzio.zzb(zza2, zzin.zza(bArr, i6), zzin.zza(bArr, i8), cArr, i5);
                            } else {
                                throw zzfw.zzh();
                            }
                        } else if (i6 < i3 - 2) {
                            int i9 = i6 + 1;
                            int i10 = i9 + 1;
                            i = i10 + 1;
                            zzio.zzb(zza2, zzin.zza(bArr, i6), zzin.zza(bArr, i9), zzin.zza(bArr, i10), cArr, i5);
                            i5 = i5 + 1 + 1;
                        } else {
                            throw zzfw.zzh();
                        }
                    } else if (i6 < i3) {
                        i5++;
                        zzio.zzb(zza2, zzin.zza(bArr, i6), cArr, i5);
                        i = i6 + 1;
                    } else {
                        throw zzfw.zzh();
                    }
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzir
    public final int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        int i3;
        char charAt;
        long j2 = i;
        long j3 = i2 + j2;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzin.zza(bArr, j2, (byte) charAt);
            i4++;
            j2 = 1 + j2;
        }
        if (i4 == length) {
            return (int) j2;
        }
        while (i4 < length) {
            char charAt3 = charSequence.charAt(i4);
            if (charAt3 < c && j2 < j3) {
                long j4 = j2 + j;
                zzin.zza(bArr, j2, (byte) charAt3);
                j = j;
                j2 = j4;
            } else if (charAt3 < 2048 && j2 <= j3 - 2) {
                long j5 = j2 + j;
                zzin.zza(bArr, j2, (byte) ((charAt3 >>> 6) | 960));
                zzin.zza(bArr, j5, (byte) ((charAt3 & '?') | 128));
                j2 = j5 + j;
                j = j;
            } else if ((charAt3 < 55296 || 57343 < charAt3) && j2 <= j3 - 3) {
                long j6 = j2 + j;
                zzin.zza(bArr, j2, (byte) ((charAt3 >>> '\f') | 480));
                long j7 = j6 + j;
                zzin.zza(bArr, j6, (byte) (((charAt3 >>> 6) & 63) | 128));
                j2 = j7 + 1;
                zzin.zza(bArr, j7, (byte) ((charAt3 & '?') | 128));
                j = 1;
            } else if (j2 <= j3 - 4) {
                int i5 = i4 + 1;
                if (i5 != length) {
                    char charAt4 = charSequence.charAt(i5);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j8 = j2 + 1;
                        zzin.zza(bArr, j2, (byte) ((codePoint >>> 18) | 240));
                        long j9 = j8 + 1;
                        zzin.zza(bArr, j8, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j10 = j9 + 1;
                        zzin.zza(bArr, j9, (byte) (((codePoint >>> 6) & 63) | 128));
                        j = 1;
                        j2 = j10 + 1;
                        zzin.zza(bArr, j10, (byte) ((codePoint & 63) | 128));
                        i4 = i5;
                    } else {
                        i4 = i5;
                    }
                }
                throw new zzit(i4 - 1, length);
            } else if (55296 > charAt3 || charAt3 > 57343 || ((i3 = i4 + 1) != length && Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed writing ");
                sb2.append(charAt3);
                sb2.append(" at index ");
                sb2.append(j2);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            } else {
                throw new zzit(i4, length);
            }
            i4++;
            c = 128;
        }
        return (int) j2;
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        int zzb;
        int zzb2;
        int zzb3;
        if (i2 == 0) {
            zzb = zzip.zzb(i);
            return zzb;
        } else if (i2 == 1) {
            zzb2 = zzip.zzb(i, zzin.zza(bArr, j));
            return zzb2;
        } else if (i2 == 2) {
            zzb3 = zzip.zzb(i, zzin.zza(bArr, j), zzin.zza(bArr, j + 1));
            return zzb3;
        } else {
            throw new AssertionError();
        }
    }
}
