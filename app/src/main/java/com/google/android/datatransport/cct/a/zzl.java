package com.google.android.datatransport.cct.a;

/* loaded from: classes.dex */
final class zzl extends zzs {
    private final long zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(long j) {
        this.zza = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzs) && this.zza == ((zzs) obj).zza();
    }

    public int hashCode() {
        long j = this.zza;
        return 1000003 ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.zza + "}";
    }

    @Override // com.google.android.datatransport.cct.a.zzs
    public long zza() {
        return this.zza;
    }
}
