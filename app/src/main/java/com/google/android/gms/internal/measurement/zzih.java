package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
abstract class zzih<T, B> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zza();

    abstract T zza(B b);

    abstract void zza(B b, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int i, zzeg zzegVar);

    abstract void zza(B b, int i, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(T t, zzja zzjaVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzhm zzhmVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzb(Object obj);

    abstract void zzb(B b, int i, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(T t, zzja zzjaVar) throws IOException;

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
    public final boolean zza(B b, zzhm zzhmVar) throws IOException {
        int zzb = zzhmVar.zzb();
        int i = zzb >>> 3;
        int i2 = zzb & 7;
        if (i2 == 0) {
            zza((zzih<T, B>) b, i, zzhmVar.zzg());
            return true;
        } else if (i2 == 1) {
            zzb(b, i, zzhmVar.zzi());
            return true;
        } else if (i2 == 2) {
            zza((zzih<T, B>) b, i, zzhmVar.zzn());
            return true;
        } else if (i2 == 3) {
            B zza = zza();
            int i3 = 4 | (i << 3);
            while (zzhmVar.zza() != Integer.MAX_VALUE && zza((zzih<T, B>) zza, zzhmVar)) {
            }
            if (i3 == zzhmVar.zzb()) {
                zza((zzih<T, B>) b, i, (int) zza((zzih<T, B>) zza));
                return true;
            }
            throw zzfw.zze();
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zza((zzih<T, B>) b, i, zzhmVar.zzj());
                return true;
            }
            throw zzfw.zzf();
        }
    }
}
