package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzhh {
    int zza;
    int zzb;
    int zzc;
    zzhk zzd;
    private boolean zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhh zza(byte[] bArr, int i, int i2, boolean z) {
        zzhj zzhjVar = new zzhj(bArr, 0, i2, false);
        try {
            zzhjVar.zzc(i2);
            return zzhjVar;
        } catch (zzin e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zza() throws IOException;

    public abstract void zza(int i) throws zzin;

    public abstract double zzb() throws IOException;

    public abstract boolean zzb(int i) throws IOException;

    public abstract float zzc() throws IOException;

    public abstract int zzc(int i) throws zzin;

    public abstract long zzd() throws IOException;

    public abstract void zzd(int i);

    public abstract long zze() throws IOException;

    public abstract int zzf() throws IOException;

    public abstract long zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract boolean zzi() throws IOException;

    public abstract String zzj() throws IOException;

    public abstract String zzk() throws IOException;

    public abstract zzgv zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract int zzn() throws IOException;

    public abstract int zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract int zzq() throws IOException;

    public abstract long zzr() throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long zzs() throws IOException;

    public abstract boolean zzt() throws IOException;

    public abstract int zzu();

    private zzhh() {
        this.zzb = 100;
        this.zzc = Integer.MAX_VALUE;
        this.zze = false;
    }

    public static int zze(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zza(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }
}
