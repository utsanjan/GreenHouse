package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzq;
import java.util.Arrays;

/* loaded from: classes.dex */
final class zzi extends zzq {
    private final long zza;
    private final Integer zzb;
    private final long zzc;
    private final byte[] zzd;
    private final String zze;
    private final long zzf;
    private final zzt zzg;

    /* loaded from: classes.dex */
    static final class zza extends zzq.zza {
        private Long zza;
        private Integer zzb;
        private Long zzc;
        private byte[] zzd;
        private String zze;
        private Long zzf;
        private zzt zzg;

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zzb(long j) {
            this.zzc = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zzc(long j) {
            this.zzf = Long.valueOf(j);
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zza(Integer num) {
            this.zzb = num;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        zzq.zza zza(byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        zzq.zza zza(String str) {
            this.zze = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq.zza zza(zzt zztVar) {
            this.zzg = zztVar;
            return this;
        }

        @Override // com.google.android.datatransport.cct.a.zzq.zza
        public zzq zza() {
            String str = "";
            if (this.zza == null) {
                str = str + " eventTimeMs";
            }
            if (this.zzc == null) {
                str = str + " eventUptimeMs";
            }
            if (this.zzf == null) {
                str = str + " timezoneOffsetSeconds";
            }
            if (str.isEmpty()) {
                return new zzi(this.zza.longValue(), this.zzb, this.zzc.longValue(), this.zzd, this.zze, this.zzf.longValue(), this.zzg, null);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }

    /* synthetic */ zzi(long j, Integer num, long j2, byte[] bArr, String str, long j3, zzt zztVar, zzh zzhVar) {
        this.zza = j;
        this.zzb = num;
        this.zzc = j2;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = j3;
        this.zzg = zztVar;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq zzqVar = (zzq) obj;
        if (this.zza == zzqVar.zzb() && ((num = this.zzb) != null ? num.equals(((zzi) zzqVar).zzb) : ((zzi) zzqVar).zzb == null) && this.zzc == zzqVar.zzc()) {
            if (Arrays.equals(this.zzd, zzqVar instanceof zzi ? ((zzi) zzqVar).zzd : zzqVar.zze()) && ((str = this.zze) != null ? str.equals(((zzi) zzqVar).zze) : ((zzi) zzqVar).zze == null) && this.zzf == zzqVar.zzg()) {
                zzt zztVar = this.zzg;
                if (zztVar == null) {
                    if (((zzi) zzqVar).zzg == null) {
                        return true;
                    }
                } else if (zztVar.equals(((zzi) zzqVar).zzg)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zza;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.zzb;
        int i2 = 0;
        int hashCode = num == null ? 0 : num.hashCode();
        long j2 = this.zzc;
        int hashCode2 = (((((i ^ hashCode) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.zzd)) * 1000003;
        String str = this.zze;
        int hashCode3 = str == null ? 0 : str.hashCode();
        long j3 = this.zzf;
        int i3 = (((hashCode2 ^ hashCode3) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        zzt zztVar = this.zzg;
        if (zztVar != null) {
            i2 = zztVar.hashCode();
        }
        return i3 ^ i2;
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.zza + ", eventCode=" + this.zzb + ", eventUptimeMs=" + this.zzc + ", sourceExtension=" + Arrays.toString(this.zzd) + ", sourceExtensionJsonProto3=" + this.zze + ", timezoneOffsetSeconds=" + this.zzf + ", networkConnectionInfo=" + this.zzg + "}";
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public Integer zza() {
        return this.zzb;
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public long zzb() {
        return this.zza;
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public long zzc() {
        return this.zzc;
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public zzt zzd() {
        return this.zzg;
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public byte[] zze() {
        return this.zzd;
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public String zzf() {
        return this.zze;
    }

    @Override // com.google.android.datatransport.cct.a.zzq
    public long zzg() {
        return this.zzf;
    }
}
