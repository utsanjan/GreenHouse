package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
abstract class zzkz<T, B> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zza();

    abstract T zza(B b);

    abstract void zza(B b, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzgv zzgvVar);

    abstract void zza(B b, int i, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(T t, zzls zzlsVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzke zzkeVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzb(Object obj);

    abstract void zzb(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(T t, zzls zzlsVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzc(T t, T t2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zze(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzf(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(B b, zzke zzkeVar) throws IOException {
        int zzb = zzkeVar.zzb();
        int i = zzb >>> 3;
        int i2 = zzb & 7;
        if (i2 == 0) {
            zza((zzkz<T, B>) b, i, zzkeVar.zzg());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzkeVar.zzi());
            return true;
        } else if (i2 == 2) {
            zza((zzkz<T, B>) b, i, zzkeVar.zzn());
            return true;
        } else if (i2 == 3) {
            B zza = zza();
            int i3 = 4 | (i << 3);
            while (zzkeVar.zza() != Integer.MAX_VALUE && zza((zzkz<T, B>) zza, zzkeVar)) {
            }
            if (i3 == zzkeVar.zzb()) {
                zza((zzkz<T, B>) b, i, (int) zza((zzkz<T, B>) zza));
                return true;
            }
            throw zzin.zze();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zza((zzkz<T, B>) b, i, zzkeVar.zzj());
                return true;
            }
            throw zzin.zzf();
        }
    }
}
