package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzr;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

/* loaded from: classes.dex */
final class zzk extends zzr {
    private final long zza;
    private final long zzb;
    private final zzp zzc;
    private final Integer zzd;
    private final String zze;
    private final List<zzq> zzf;
    private final zzu zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class zza extends zzr.zza {
        private Long zza;
        private Long zzb;
        private zzp zzc;
        private Integer zzd;
        private String zze;
        private List<zzq> zzf;
        private zzu zzg;

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        public zzr.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        public zzr.zza zzb(long j) {
            this.zzb = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        public zzr.zza zza(zzp zzpVar) {
            this.zzc = zzpVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        zzr.zza zza(Integer num) {
            this.zzd = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        zzr.zza zza(String str) {
            this.zze = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        public zzr.zza zza(List<zzq> list) {
            this.zzf = list;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        public zzr.zza zza(zzu zzuVar) {
            this.zzg = zzuVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzr.zza
        public zzr zza() {
            String str = "";
            if (this.zza == null) {
                str = str + " requestTimeMs";
            }
            if (this.zzb == null) {
                str = str + " requestUptimeMs";
            }
            if (str.isEmpty()) {
                return new zzk(this.zza.longValue(), this.zzb.longValue(), this.zzc, this.zzd, this.zze, this.zzf, this.zzg, null);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }

    /* synthetic */ zzk(long j, long j2, zzp zzpVar, Integer num, String str, List list, zzu zzuVar, zzj zzjVar) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = zzpVar;
        this.zzd = num;
        this.zze = str;
        this.zzf = list;
        this.zzg = zzuVar;
    }

    public boolean equals(Object obj) {
        zzp zzpVar;
        Integer num;
        String str;
        List<zzq> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzr)) {
            return false;
        }
        zzr zzrVar = (zzr) obj;
        if (this.zza == zzrVar.zzg() && this.zzb == zzrVar.zzh() && ((zzpVar = this.zzc) != null ? zzpVar.equals(((zzk) zzrVar).zzc) : ((zzk) zzrVar).zzc == null) && ((num = this.zzd) != null ? num.equals(((zzk) zzrVar).zzd) : ((zzk) zzrVar).zzd == null) && ((str = this.zze) != null ? str.equals(((zzk) zzrVar).zze) : ((zzk) zzrVar).zze == null) && ((list = this.zzf) != null ? list.equals(((zzk) zzrVar).zzf) : ((zzk) zzrVar).zzf == null)) {
            zzu zzuVar = this.zzg;
            if (zzuVar == null) {
                if (((zzk) zzrVar).zzg == null) {
                    return true;
                }
            } else if (zzuVar.equals(((zzk) zzrVar).zzg)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zza;
        long j2 = this.zzb;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        zzp zzpVar = this.zzc;
        int i2 = 0;
        int hashCode = (i ^ (zzpVar == null ? 0 : zzpVar.hashCode())) * 1000003;
        Integer num = this.zzd;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.zze;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<zzq> list = this.zzf;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        zzu zzuVar = this.zzg;
        if (zzuVar != null) {
            i2 = zzuVar.hashCode();
        }
        return hashCode4 ^ i2;
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.zza + ", requestUptimeMs=" + this.zzb + ", clientInfo=" + this.zzc + ", logSource=" + this.zzd + ", logSourceName=" + this.zze + ", logEvents=" + this.zzf + ", qosTier=" + this.zzg + "}";
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    public zzp zzb() {
        return this.zzc;
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    @Encodable.Field(name = "logEvent")
    public List<zzq> zzc() {
        return this.zzf;
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    public Integer zzd() {
        return this.zzd;
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    public String zze() {
        return this.zze;
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    public zzu zzf() {
        return this.zzg;
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    public long zzg() {
        return this.zza;
    }

    @Override // com.google.android.datatransport.cct.a.zzr
    public long zzh() {
        return this.zzb;
    }
}
