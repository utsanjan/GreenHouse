package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzo extends zzkg {
    private String zzb;
    private Set<Integer> zzc;
    private Map<Integer, zzq> zzd;
    private Long zze;
    private Long zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(zzkj zzkjVar) {
        super(zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkg
    protected final boolean zze() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x02f4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.internal.measurement.zzcb.zza> zza(java.lang.String r47, java.util.List<com.google.android.gms.internal.measurement.zzcb.zzc> r48, java.util.List<com.google.android.gms.internal.measurement.zzcb.zzk> r49, java.lang.Long r50, java.lang.Long r51) {
        /*
            Method dump skipped, instructions count: 1879
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzo.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    private final zzq zza(int i) {
        if (this.zzd.containsKey(Integer.valueOf(i))) {
            return this.zzd.get(Integer.valueOf(i));
        }
        zzq zzqVar = new zzq(this, this.zzb, null);
        this.zzd.put(Integer.valueOf(i), zzqVar);
        return zzqVar;
    }

    private final boolean zza(int i, int i2) {
        if (this.zzd.get(Integer.valueOf(i)) == null) {
            return false;
        }
        return zzq.zza(this.zzd.get(Integer.valueOf(i))).get(i2);
    }
}
