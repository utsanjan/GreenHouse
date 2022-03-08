package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzt;

/* loaded from: classes.dex */
final class zzn extends zzt {
    private final zzt.zzc zza;
    private final zzt.zzb zzb;

    /* loaded from: classes.dex */
    static final class zza extends zzt.zza {
        private zzt.zzc zza;
        private zzt.zzb zzb;

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zza(zzt.zzc zzcVar) {
            this.zza = zzcVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt.zza zza(zzt.zzb zzbVar) {
            this.zzb = zzbVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzt.zza
        public zzt zza() {
            return new zzn(this.zza, this.zzb, null);
        }
    }

    /* synthetic */ zzn(zzt.zzc zzcVar, zzt.zzb zzbVar, zzm zzmVar) {
        this.zza = zzcVar;
        this.zzb = zzbVar;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzt)) {
            return false;
        }
        zzt.zzc zzcVar = this.zza;
        if (zzcVar != null ? zzcVar.equals(((zzn) obj).zza) : ((zzn) obj).zza == null) {
            zzt.zzb zzbVar = this.zzb;
            if (zzbVar == null) {
                if (((zzn) obj).zzb == null) {
                    return true;
                }
            } else if (zzbVar.equals(((zzn) obj).zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        zzt.zzc zzcVar = this.zza;
        int i = 0;
        int hashCode = ((zzcVar == null ? 0 : zzcVar.hashCode()) ^ 1000003) * 1000003;
        zzt.zzb zzbVar = this.zzb;
        if (zzbVar != null) {
            i = zzbVar.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.zza + ", mobileSubtype=" + this.zzb + "}";
    }

    @Override // com.google.android.datatransport.cct.a.zzt
    public zzt.zzb zzb() {
        return this.zzb;
    }

    @Override // com.google.android.datatransport.cct.a.zzt
    public zzt.zzc zzc() {
        return this.zza;
    }
}
