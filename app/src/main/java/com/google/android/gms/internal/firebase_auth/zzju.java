package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzju<T> implements zzkd<T> {
    private final zzjn zza;
    private final zzkz<?, ?> zzb;
    private final boolean zzc;
    private final zzhu<?> zzd;

    private zzju(zzkz<?, ?> zzkzVar, zzhu<?> zzhuVar, zzjn zzjnVar) {
        this.zzb = zzkzVar;
        this.zzc = zzhuVar.zza(zzjnVar);
        this.zzd = zzhuVar;
        this.zza = zzjnVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzju<T> zza(zzkz<?, ?> zzkzVar, zzhu<?> zzhuVar, zzjn zzjnVar) {
        return new zzju<>(zzkzVar, zzhuVar, zzjnVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final T zza() {
        return (T) this.zza.zzae().zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final boolean zza(T t, T t2) {
        if (!this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final int zza(T t) {
        int hashCode = this.zzb.zzb(t).hashCode();
        if (this.zzc) {
            return (hashCode * 53) + this.zzd.zza(t).hashCode();
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zzb(T t, T t2) {
        zzkf.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzkf.zza(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zza(T t, zzls zzlsVar) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd = this.zzd.zza(t).zzd();
        while (zzd.hasNext()) {
            Map.Entry<?, Object> next = zzd.next();
            zzhx zzhxVar = (zzhx) next.getKey();
            if (zzhxVar.zzc() != zzlt.MESSAGE || zzhxVar.zzd() || zzhxVar.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zziu) {
                zzlsVar.zza(zzhxVar.zza(), (Object) ((zziu) next).zza().zzc());
            } else {
                zzlsVar.zza(zzhxVar.zza(), next.getValue());
            }
        }
        zzkz<?, ?> zzkzVar = this.zzb;
        zzkzVar.zzb((zzkz<?, ?>) zzkzVar.zzb(t), zzlsVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zza(T t, zzke zzkeVar, zzhs zzhsVar) throws IOException {
        boolean z;
        zzkz<?, ?> zzkzVar = this.zzb;
        zzhu<?> zzhuVar = this.zzd;
        Object zzc = zzkzVar.zzc(t);
        zzhv<?> zzb = zzhuVar.zzb(t);
        do {
            try {
                if (zzkeVar.zza() != Integer.MAX_VALUE) {
                    int zzb2 = zzkeVar.zzb();
                    if (zzb2 == 11) {
                        int i = 0;
                        Object obj = null;
                        zzgv zzgvVar = null;
                        while (zzkeVar.zza() != Integer.MAX_VALUE) {
                            int zzb3 = zzkeVar.zzb();
                            if (zzb3 == 16) {
                                i = zzkeVar.zzo();
                                obj = zzhuVar.zza(zzhsVar, this.zza, i);
                            } else if (zzb3 == 26) {
                                if (obj != null) {
                                    zzhuVar.zza(zzkeVar, obj, zzhsVar, zzb);
                                } else {
                                    zzgvVar = zzkeVar.zzn();
                                }
                            } else if (!zzkeVar.zzc()) {
                                break;
                            }
                        }
                        if (zzkeVar.zzb() != 12) {
                            throw zzin.zze();
                        } else if (zzgvVar != null) {
                            if (obj != null) {
                                zzhuVar.zza(zzgvVar, obj, zzhsVar, zzb);
                            } else {
                                zzkzVar.zza((zzkz<?, ?>) zzc, i, zzgvVar);
                            }
                        }
                    } else if ((zzb2 & 7) == 2) {
                        Object zza = zzhuVar.zza(zzhsVar, this.zza, zzb2 >>> 3);
                        if (zza != null) {
                            zzhuVar.zza(zzkeVar, zza, zzhsVar, zzb);
                        } else {
                            z = zzkzVar.zza((zzkz<?, ?>) zzc, zzkeVar);
                            continue;
                        }
                    } else {
                        z = zzkeVar.zzc();
                        continue;
                    }
                    z = true;
                    continue;
                } else {
                    return;
                }
            } finally {
                zzkzVar.zzb((Object) t, (T) zzc);
            }
        } while (z);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkd
    public final int zzb(T t) {
        zzkz<?, ?> zzkzVar = this.zzb;
        int zze = zzkzVar.zze(zzkzVar.zzb(t)) + 0;
        if (this.zzc) {
            return zze + this.zzd.zza(t).zzg();
        }
        return zze;
    }
}
