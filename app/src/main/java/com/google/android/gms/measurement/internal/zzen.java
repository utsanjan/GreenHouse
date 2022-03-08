package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzen<V> {
    private static final Object zzf = new Object();
    private final String zza;
    private final zzel<V> zzb;
    private final V zzc;
    private final V zzd;
    private final Object zze;
    private volatile V zzg;
    private volatile V zzh;

    private zzen(String str, V v, V v2, zzel<V> zzelVar) {
        this.zze = new Object();
        this.zzg = null;
        this.zzh = null;
        this.zza = str;
        this.zzc = v;
        this.zzd = v2;
        this.zzb = zzelVar;
    }

    public final String zza() {
        return this.zza;
    }

    public final V zza(V v) {
        List<zzen> list;
        synchronized (this.zze) {
        }
        if (v != null) {
            return v;
        }
        if (zzek.zza == null) {
            return this.zzc;
        }
        zzx zzxVar = zzek.zza;
        synchronized (zzf) {
            if (zzx.zza()) {
                return this.zzh == null ? this.zzc : this.zzh;
            }
            try {
                list = zzaq.zzct;
                for (zzen zzenVar : list) {
                    if (!zzx.zza()) {
                        V v2 = null;
                        try {
                            if (zzenVar.zzb != null) {
                                v2 = zzenVar.zzb.zza();
                            }
                        } catch (IllegalStateException e) {
                        }
                        synchronized (zzf) {
                            zzenVar.zzh = v2;
                        }
                    } else {
                        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                    }
                }
            } catch (SecurityException e2) {
            }
            zzel<V> zzelVar = this.zzb;
            if (zzelVar == null) {
                zzx zzxVar2 = zzek.zza;
                return this.zzc;
            }
            try {
                return zzelVar.zza();
            } catch (IllegalStateException e3) {
                zzx zzxVar3 = zzek.zza;
                return this.zzc;
            } catch (SecurityException e4) {
                zzx zzxVar4 = zzek.zza;
                return this.zzc;
            }
        }
    }
}
