package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzp;

/* loaded from: classes.dex */
final class zzg extends zzp {
    private final zzp.zzb zza;
    private final zza zzb;

    /* loaded from: classes.dex */
    static final class zza extends zzp.zza {
        private zzp.zzb zza;
        private zza zzb;

        @Override // com.google.android.datatransport.cct.a.zzp.zza
        public zzp.zza zza(zzp.zzb zzbVar) {
            this.zza = zzbVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzp.zza
        public zzp.zza zza(zza zzaVar) {
            this.zzb = zzaVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzp.zza
        public zzp zza() {
            return new zzg(this.zza, this.zzb, null);
        }
    }

    /* synthetic */ zzg(zzp.zzb zzbVar, zza zzaVar, zzf zzfVar) {
        this.zza = zzbVar;
        this.zzb = zzaVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp.zzb zzbVar = this.zza;
        if (zzbVar != null ? zzbVar.equals(((zzg) obj).zza) : ((zzg) obj).zza == null) {
            zza zzaVar = this.zzb;
            if (zzaVar == null) {
                if (((zzg) obj).zzb == null) {
                    return true;
                }
            } else if (zzaVar.equals(((zzg) obj).zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        zzp.zzb zzbVar = this.zza;
        int i = 0;
        int hashCode = ((zzbVar == null ? 0 : zzbVar.hashCode()) ^ 1000003) * 1000003;
        zza zzaVar = this.zzb;
        if (zzaVar != null) {
            i = zzaVar.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.zza + ", androidClientInfo=" + this.zzb + "}";
    }

    @Override // com.google.android.datatransport.cct.a.zzp
    public zza zzb() {
        return this.zzb;
    }

    @Override // com.google.android.datatransport.cct.a.zzp
    public zzp.zzb zzc() {
        return this.zza;
    }
}
