package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class zziw {
    private static final zzhs zza = zzhs.zza();
    private zzgv zzb;
    private volatile zzjn zzc;
    private volatile zzgv zzd;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zziw)) {
            return false;
        }
        zziw zziwVar = (zziw) obj;
        zzjn zzjnVar = this.zzc;
        zzjn zzjnVar2 = zziwVar.zzc;
        if (zzjnVar == null && zzjnVar2 == null) {
            return zzc().equals(zziwVar.zzc());
        }
        if (zzjnVar != null && zzjnVar2 != null) {
            return zzjnVar.equals(zzjnVar2);
        }
        if (zzjnVar != null) {
            return zzjnVar.equals(zziwVar.zzb(zzjnVar.h_()));
        }
        return zzb(zzjnVar2.h_()).equals(zzjnVar2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzjn zzb(zzjn zzjnVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzjnVar;
                        this.zzd = zzgv.zza;
                    } catch (zzin e) {
                        this.zzc = zzjnVar;
                        this.zzd = zzgv.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public final zzjn zza(zzjn zzjnVar) {
        zzjn zzjnVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzjnVar;
        return zzjnVar2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzaa();
        }
        return 0;
    }

    public final zzgv zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzgv.zza;
            } else {
                this.zzd = this.zzc.zzw();
            }
            return this.zzd;
        }
    }
}
