package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public class zzgf {
    private static final zzfb zza = zzfb.zza();
    private zzeg zzb;
    private volatile zzgw zzc;
    private volatile zzeg zzd;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgf)) {
            return false;
        }
        zzgf zzgfVar = (zzgf) obj;
        zzgw zzgwVar = this.zzc;
        zzgw zzgwVar2 = zzgfVar.zzc;
        if (zzgwVar == null && zzgwVar2 == null) {
            return zzc().equals(zzgfVar.zzc());
        }
        if (zzgwVar != null && zzgwVar2 != null) {
            return zzgwVar.equals(zzgwVar2);
        }
        if (zzgwVar != null) {
            return zzgwVar.equals(zzgfVar.zzb(zzgwVar.h_()));
        }
        return zzb(zzgwVar2.h_()).equals(zzgwVar2);
    }

    public int hashCode() {
        return 1;
    }

    private final zzgw zzb(zzgw zzgwVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzgwVar;
                        this.zzd = zzeg.zza;
                    } catch (zzfw e) {
                        this.zzc = zzgwVar;
                        this.zzd = zzeg.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public final zzgw zza(zzgw zzgwVar) {
        zzgw zzgwVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzgwVar;
        return zzgwVar2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzbm();
        }
        return 0;
    }

    public final zzeg zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzeg.zza;
            } else {
                this.zzd = this.zzc.zzbh();
            }
            return this.zzd;
        }
    }
}
