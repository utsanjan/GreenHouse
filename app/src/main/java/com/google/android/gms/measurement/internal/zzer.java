package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzlm;
import com.google.android.gms.internal.measurement.zznh;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzer extends zzg {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private long zzg;
    private List<String> zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzer(zzfy zzfyVar, long j) {
        super(zzfyVar);
        this.zzg = j;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zzz() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0232 A[Catch: IllegalStateException -> 0x0260, TryCatch #0 {IllegalStateException -> 0x0260, blocks: (B:62:0x01bb, B:64:0x01c1, B:66:0x01cd, B:67:0x01dd, B:68:0x01e1, B:72:0x01ea, B:75:0x01f4, B:77:0x0200, B:81:0x0217, B:83:0x021f, B:85:0x0225, B:87:0x022c, B:89:0x0232, B:91:0x0243, B:93:0x0257, B:94:0x025a, B:95:0x025c), top: B:122:0x01bb }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0243 A[Catch: IllegalStateException -> 0x0260, TryCatch #0 {IllegalStateException -> 0x0260, blocks: (B:62:0x01bb, B:64:0x01c1, B:66:0x01cd, B:67:0x01dd, B:68:0x01e1, B:72:0x01ea, B:75:0x01f4, B:77:0x0200, B:81:0x0217, B:83:0x021f, B:85:0x0225, B:87:0x022c, B:89:0x0232, B:91:0x0243, B:93:0x0257, B:94:0x025a, B:95:0x025c), top: B:122:0x01bb }] */
    @Override // com.google.android.gms.measurement.internal.zzg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void zzaa() {
        /*
            Method dump skipped, instructions count: 760
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzer.zzaa():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzn zza(String str) {
        String str2;
        long j;
        boolean z;
        Boolean zze;
        zzd();
        zzb();
        String zzab = zzab();
        String zzac = zzac();
        zzw();
        String str3 = this.zzb;
        long zzaf = zzaf();
        zzw();
        String str4 = this.zzd;
        long zzf = zzt().zzf();
        zzw();
        zzd();
        if (this.zzf == 0) {
            this.zzf = this.zzy.zzi().zza(zzn(), zzn().getPackageName());
        }
        long j2 = this.zzf;
        boolean zzab2 = this.zzy.zzab();
        boolean z2 = !zzs().zzq;
        zzd();
        zzb();
        if (!this.zzy.zzab()) {
            str2 = null;
        } else {
            str2 = zzai();
        }
        zzfy zzfyVar = this.zzy;
        Long valueOf = Long.valueOf(zzfyVar.zzc().zzh.zza());
        if (valueOf.longValue() == 0) {
            j = zzfyVar.zza;
            z = zzab2;
        } else {
            z = zzab2;
            j = Math.min(zzfyVar.zza, valueOf.longValue());
        }
        int zzag = zzag();
        boolean booleanValue = zzt().zzi().booleanValue();
        zzy zzt = zzt();
        zzt.zzb();
        Boolean zze2 = zzt.zze("google_analytics_ssaid_collection_enabled");
        boolean booleanValue2 = Boolean.valueOf(zze2 == null || zze2.booleanValue()).booleanValue();
        zzfg zzs = zzs();
        zzs.zzd();
        return new zzn(zzab, zzac, str3, zzaf, str4, zzf, j2, str, z, z2, str2, 0L, j, zzag, booleanValue, booleanValue2, zzs.zzg().getBoolean("deferred_analytics_collection", false), zzad(), zzt().zze("google_analytics_default_allow_ad_personalization_signals") == null ? null : Boolean.valueOf(!zze.booleanValue()), this.zzg, zzt().zza(zzaq.zzbb) ? this.zzh : null, (!zzlm.zzb() || !zzt().zza(zzaq.zzbn)) ? null : zzae());
    }

    private final String zzai() {
        if (!zznh.zzb() || !zzt().zza(zzaq.zzbq)) {
            try {
                Class<?> loadClass = zzn().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                if (loadClass == null) {
                    return null;
                }
                try {
                    Object invoke = loadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, zzn());
                    if (invoke == null) {
                        return null;
                    }
                    try {
                        return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                    } catch (Exception e) {
                        zzr().zzk().zza("Failed to retrieve Firebase Instance Id");
                        return null;
                    }
                } catch (Exception e2) {
                    zzr().zzj().zza("Failed to obtain Firebase Analytics instance");
                    return null;
                }
            } catch (ClassNotFoundException e3) {
                return null;
            }
        } else {
            zzr().zzx().zza("Disabled IID for tests.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzab() {
        zzw();
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzac() {
        zzw();
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzad() {
        zzw();
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzae() {
        zzw();
        return this.zzl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzaf() {
        zzw();
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzag() {
        zzw();
        return this.zzi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<String> zzah() {
        return this.zzh;
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhc zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzer zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzir zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzii zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzjv zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzes zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzkr zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfv zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzeu zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzfg zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
