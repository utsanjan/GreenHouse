package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzhj extends zzhh {
    private final byte[] zze;
    private final boolean zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;

    private zzhj(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzl = Integer.MAX_VALUE;
        this.zze = bArr;
        this.zzg = i2 + i;
        this.zzi = i;
        this.zzj = i;
        this.zzf = z;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zza() throws IOException {
        if (zzt()) {
            this.zzk = 0;
            return 0;
        }
        int zzv = zzv();
        this.zzk = zzv;
        if ((zzv >>> 3) != 0) {
            return zzv;
        }
        throw zzin.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final void zza(int i) throws zzin {
        if (this.zzk != i) {
            throw zzin.zze();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final boolean zzb(int i) throws IOException {
        int zza;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzg - this.zzi >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zze;
                    int i4 = this.zzi;
                    this.zzi = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzin.zzc();
            }
            while (i3 < 10) {
                if (zzaa() < 0) {
                    i3++;
                }
            }
            throw zzin.zzc();
            return true;
        } else if (i2 == 1) {
            zzf(8);
            return true;
        } else if (i2 == 2) {
            zzf(zzv());
            return true;
        } else if (i2 == 3) {
            do {
                zza = zza();
                if (zza == 0) {
                    break;
                }
            } while (zzb(zza));
            zza(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzf(4);
                return true;
            }
            throw zzin.zzf();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final long zzd() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final long zze() throws IOException {
        return zzw();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzf() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final long zzg() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzh() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final boolean zzi() throws IOException {
        return zzw() != 0;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final String zzj() throws IOException {
        int zzv = zzv();
        if (zzv > 0 && zzv <= this.zzg - this.zzi) {
            String str = new String(this.zze, this.zzi, zzv, zzii.zza);
            this.zzi += zzv;
            return str;
        } else if (zzv == 0) {
            return "";
        } else {
            if (zzv < 0) {
                throw zzin.zzb();
            }
            throw zzin.zza();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final String zzk() throws IOException {
        int zzv = zzv();
        if (zzv > 0) {
            int i = this.zzg;
            int i2 = this.zzi;
            if (zzv <= i - i2) {
                String zzb = zzlh.zzb(this.zze, i2, zzv);
                this.zzi += zzv;
                return zzb;
            }
        }
        if (zzv == 0) {
            return "";
        }
        if (zzv <= 0) {
            throw zzin.zzb();
        }
        throw zzin.zza();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final zzgv zzl() throws IOException {
        byte[] bArr;
        int zzv = zzv();
        if (zzv > 0) {
            int i = this.zzg;
            int i2 = this.zzi;
            if (zzv <= i - i2) {
                zzgv zza = zzgv.zza(this.zze, i2, zzv);
                this.zzi += zzv;
                return zza;
            }
        }
        if (zzv == 0) {
            return zzgv.zza;
        }
        if (zzv > 0) {
            int i3 = this.zzg;
            int i4 = this.zzi;
            if (zzv <= i3 - i4) {
                int i5 = zzv + i4;
                this.zzi = i5;
                bArr = Arrays.copyOfRange(this.zze, i4, i5);
                return zzgv.zzb(bArr);
            }
        }
        if (zzv > 0) {
            throw zzin.zza();
        } else if (zzv == 0) {
            bArr = zzii.zzb;
            return zzgv.zzb(bArr);
        } else {
            throw zzin.zzb();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzm() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzn() throws IOException {
        return zzv();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzo() throws IOException {
        return zzx();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final long zzp() throws IOException {
        return zzy();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzq() throws IOException {
        return zze(zzv());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final long zzr() throws IOException {
        return zza(zzw());
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x006a;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzv() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 == r0) goto L_0x006d
            byte[] r2 = r5.zze
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.zzi = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L_0x006a
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r3
            goto L_0x006a
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x006a
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x006a
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x0069
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006d
            goto L_0x006a
        L_0x0069:
            r1 = r3
        L_0x006a:
            r5.zzi = r1
            return r0
        L_0x006d:
            long r0 = r5.zzs()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhj.zzv():int");
    }

    private final long zzw() throws IOException {
        long j;
        int i = this.zzi;
        int i2 = this.zzg;
        if (i2 != i) {
            byte[] bArr = this.zze;
            int i3 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                this.zzi = i3;
                return b;
            } else if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                int i5 = b ^ (bArr[i3] << 7);
                if (i5 < 0) {
                    j = i5 ^ (-128);
                } else {
                    int i6 = i4 + 1;
                    int i7 = i5 ^ (bArr[i4] << 14);
                    if (i7 >= 0) {
                        j = i7 ^ 16256;
                        i4 = i6;
                    } else {
                        i4 = i6 + 1;
                        int i8 = i7 ^ (bArr[i6] << 21);
                        if (i8 < 0) {
                            j = i8 ^ (-2080896);
                        } else {
                            long j2 = i8;
                            int i9 = i4 + 1;
                            long j3 = j2 ^ (bArr[i4] << 28);
                            if (j3 >= 0) {
                                j = 266354560 ^ j3;
                                i4 = i9;
                            } else {
                                i4 = i9 + 1;
                                long j4 = j3 ^ (bArr[i9] << 35);
                                if (j4 < 0) {
                                    j = j4 ^ (-34093383808L);
                                } else {
                                    int i10 = i4 + 1;
                                    long j5 = j4 ^ (bArr[i4] << 42);
                                    if (j5 >= 0) {
                                        j = 4363953127296L ^ j5;
                                        i4 = i10;
                                    } else {
                                        i4 = i10 + 1;
                                        long j6 = j5 ^ (bArr[i10] << 49);
                                        if (j6 < 0) {
                                            j = j6 ^ (-558586000294016L);
                                        } else {
                                            int i11 = i4 + 1;
                                            long j7 = (j6 ^ (bArr[i4] << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i4 = i11 + 1;
                                                if (bArr[i11] >= 0) {
                                                    j = j7;
                                                }
                                            } else {
                                                i4 = i11;
                                                j = j7;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.zzi = i4;
                return j;
            }
        }
        return zzs();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzaa = zzaa();
            j |= (zzaa & Byte.MAX_VALUE) << i;
            if ((zzaa & 128) == 0) {
                return j;
            }
        }
        throw zzin.zzc();
    }

    private final int zzx() throws IOException {
        int i = this.zzi;
        if (this.zzg - i >= 4) {
            byte[] bArr = this.zze;
            this.zzi = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzin.zza();
    }

    private final long zzy() throws IOException {
        int i = this.zzi;
        if (this.zzg - i >= 8) {
            byte[] bArr = this.zze;
            this.zzi = i + 8;
            return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }
        throw zzin.zza();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzc(int i) throws zzin {
        if (i >= 0) {
            int zzu = i + zzu();
            int i2 = this.zzl;
            if (zzu <= i2) {
                this.zzl = zzu;
                zzz();
                return i2;
            }
            throw zzin.zza();
        }
        throw zzin.zzb();
    }

    private final void zzz() {
        int i = this.zzg + this.zzh;
        this.zzg = i;
        int i2 = i - this.zzj;
        int i3 = this.zzl;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzh = i4;
            this.zzg = i - i4;
            return;
        }
        this.zzh = 0;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final void zzd(int i) {
        this.zzl = i;
        zzz();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhh
    public final int zzu() {
        return this.zzi - this.zzj;
    }

    private final byte zzaa() throws IOException {
        int i = this.zzi;
        if (i != this.zzg) {
            byte[] bArr = this.zze;
            this.zzi = i + 1;
            return bArr[i];
        }
        throw zzin.zza();
    }

    private final void zzf(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzg;
            int i3 = this.zzi;
            if (i <= i2 - i3) {
                this.zzi = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzin.zzb();
        }
        throw zzin.zza();
    }
}
