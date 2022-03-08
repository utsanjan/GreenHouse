package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzjq;
import com.google.android.gms.internal.measurement.zzmd;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzy extends zzgs {
    private Boolean zza;
    private zzaa zzb = zzab.zza;
    private Boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(zzfy zzfyVar) {
        super(zzfyVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzaa zzaaVar) {
        this.zzb = zzaaVar;
    }

    public final int zze() {
        return (!zzjq.zzb() || !zzt().zzd(null, zzaq.zzck) || zzp().zzj() < 201500) ? 25 : 100;
    }

    public final int zza(String str) {
        return zza(str, zzaq.zzah, 25, 100);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzb(String str) {
        if (!zzjq.zzb() || !zzd(null, zzaq.zzcj)) {
            return 500;
        }
        return zza(str, zzaq.zzag, 500, CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
    }

    public final int zzc(String str) {
        return zzb(str, zzaq.zzn);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzd(String str) {
        if (!zzjq.zzb() || !zzd(null, zzaq.zzcj)) {
            return 25;
        }
        return zza(str, zzaq.zzaf, 25, 100);
    }

    public final long zzf() {
        zzu();
        return 29000L;
    }

    public final boolean zzg() {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    ApplicationInfo applicationInfo = zzn().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzc = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        zzr().zzf().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzc.booleanValue();
    }

    public final long zza(String str, zzen<Long> zzenVar) {
        if (str == null) {
            return zzenVar.zza(null).longValue();
        }
        String zza = this.zzb.zza(str, zzenVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzenVar.zza(null).longValue();
        }
        try {
            return zzenVar.zza(Long.valueOf(Long.parseLong(zza))).longValue();
        } catch (NumberFormatException e) {
            return zzenVar.zza(null).longValue();
        }
    }

    public final int zzb(String str, zzen<Integer> zzenVar) {
        if (str == null) {
            return zzenVar.zza(null).intValue();
        }
        String zza = this.zzb.zza(str, zzenVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzenVar.zza(null).intValue();
        }
        try {
            return zzenVar.zza(Integer.valueOf(Integer.parseInt(zza))).intValue();
        } catch (NumberFormatException e) {
            return zzenVar.zza(null).intValue();
        }
    }

    private final int zza(String str, zzen<Integer> zzenVar, int i, int i2) {
        return Math.max(Math.min(zzb(str, zzenVar), i2), i);
    }

    public final double zzc(String str, zzen<Double> zzenVar) {
        if (str == null) {
            return zzenVar.zza(null).doubleValue();
        }
        String zza = this.zzb.zza(str, zzenVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzenVar.zza(null).doubleValue();
        }
        try {
            return zzenVar.zza(Double.valueOf(Double.parseDouble(zza))).doubleValue();
        } catch (NumberFormatException e) {
            return zzenVar.zza(null).doubleValue();
        }
    }

    public final boolean zzd(String str, zzen<Boolean> zzenVar) {
        if (str == null) {
            return zzenVar.zza(null).booleanValue();
        }
        String zza = this.zzb.zza(str, zzenVar.zza());
        if (TextUtils.isEmpty(zza)) {
            return zzenVar.zza(null).booleanValue();
        }
        return zzenVar.zza(Boolean.valueOf(Boolean.parseBoolean(zza))).booleanValue();
    }

    public final boolean zze(String str, zzen<Boolean> zzenVar) {
        return zzd(str, zzenVar);
    }

    public final boolean zza(zzen<Boolean> zzenVar) {
        return zzd(null, zzenVar);
    }

    private final Bundle zzz() {
        try {
            if (zzn().getPackageManager() == null) {
                zzr().zzf().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(zzn()).getApplicationInfo(zzn().getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            zzr().zzf().zza("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            zzr().zzf().zza("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zze(String str) {
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzr().zzf().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (!zzz.containsKey(str)) {
            return null;
        } else {
            return Boolean.valueOf(zzz.getBoolean(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<String> zzf(String str) {
        Integer num;
        Preconditions.checkNotEmpty(str);
        Bundle zzz = zzz();
        if (zzz == null) {
            zzr().zzf().zza("Failed to load metadata: Metadata bundle is null");
            num = null;
        } else if (!zzz.containsKey(str)) {
            num = null;
        } else {
            num = Integer.valueOf(zzz.getInt(str));
        }
        if (num == null) {
            return null;
        }
        try {
            String[] stringArray = zzn().getResources().getStringArray(num.intValue());
            if (stringArray == null) {
                return null;
            }
            return Arrays.asList(stringArray);
        } catch (Resources.NotFoundException e) {
            zzr().zzf().zza("Failed to load string array from metadata: resource not found", e);
            return null;
        }
    }

    public final boolean zzh() {
        zzu();
        Boolean zze = zze("firebase_analytics_collection_deactivated");
        return zze != null && zze.booleanValue();
    }

    public final Boolean zzi() {
        zzb();
        Boolean zze = zze("google_analytics_adid_collection_enabled");
        return Boolean.valueOf(zze == null || zze.booleanValue());
    }

    public final Boolean zzj() {
        zzb();
        boolean z = true;
        if (!zzmd.zzb() || !zza(zzaq.zzcb)) {
            return true;
        }
        Boolean zze = zze("google_analytics_automatic_screen_reporting_enabled");
        if (zze != null && !zze.booleanValue()) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public static long zzk() {
        return zzaq.zzac.zza(null).longValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x002d, code lost:
        if (android.text.TextUtils.isEmpty(r1) != false) goto L_0x002f;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zza(com.google.android.gms.measurement.internal.zzf r6) {
        /*
            r5 = this;
            android.net.Uri$Builder r0 = new android.net.Uri$Builder
            r0.<init>()
            java.lang.String r1 = r6.zze()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0033
            boolean r1 = com.google.android.gms.internal.measurement.zzlm.zzb()
            if (r1 == 0) goto L_0x002f
            com.google.android.gms.measurement.internal.zzy r1 = r5.zzt()
            java.lang.String r2 = r6.zzc()
            com.google.android.gms.measurement.internal.zzen<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzaq.zzbn
            boolean r1 = r1.zzd(r2, r3)
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = r6.zzg()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0033
        L_0x002f:
            java.lang.String r1 = r6.zzf()
        L_0x0033:
            com.google.android.gms.measurement.internal.zzen<java.lang.String> r2 = com.google.android.gms.measurement.internal.zzaq.zzd
            r3 = 0
            java.lang.Object r2 = r2.zza(r3)
            java.lang.String r2 = (java.lang.String) r2
            android.net.Uri$Builder r2 = r0.scheme(r2)
            com.google.android.gms.measurement.internal.zzen<java.lang.String> r4 = com.google.android.gms.measurement.internal.zzaq.zze
            java.lang.Object r3 = r4.zza(r3)
            java.lang.String r3 = (java.lang.String) r3
            android.net.Uri$Builder r2 = r2.encodedAuthority(r3)
            java.lang.String r3 = "config/app/"
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r4 = r1.length()
            if (r4 == 0) goto L_0x005d
            java.lang.String r1 = r3.concat(r1)
            goto L_0x0062
        L_0x005d:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r3)
        L_0x0062:
            android.net.Uri$Builder r1 = r2.path(r1)
            java.lang.String r6 = r6.zzd()
            java.lang.String r2 = "app_instance_id"
            android.net.Uri$Builder r6 = r1.appendQueryParameter(r2, r6)
            java.lang.String r1 = "platform"
            java.lang.String r2 = "android"
            android.net.Uri$Builder r6 = r6.appendQueryParameter(r1, r2)
            long r1 = r5.zzf()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "gmp_version"
            r6.appendQueryParameter(r2, r1)
            android.net.Uri r6 = r0.build()
            java.lang.String r6 = r6.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzy.zza(com.google.android.gms.measurement.internal.zzf):java.lang.String");
    }

    public static long zzv() {
        return zzaq.zzc.zza(null).longValue();
    }

    public final String zzw() {
        return zza("debug.firebase.analytics.app", "");
    }

    public final String zzx() {
        return zza("debug.deferred.deeplink", "");
    }

    private final String zza(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (ClassNotFoundException e) {
            zzr().zzf().zza("Could not find SystemProperties class", e);
            return str2;
        } catch (IllegalAccessException e2) {
            zzr().zzf().zza("Could not access SystemProperties.get()", e2);
            return str2;
        } catch (NoSuchMethodException e3) {
            zzr().zzf().zza("Could not find SystemProperties.get() method", e3);
            return str2;
        } catch (InvocationTargetException e4) {
            zzr().zzf().zza("SystemProperties.get() threw an exception", e4);
            return str2;
        }
    }

    public final boolean zzg(String str) {
        return "1".equals(this.zzb.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzh(String str) {
        return "1".equals(this.zzb.zza(str, "measurement.event_sampling_enabled"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzi(String str) {
        return zzd(str, zzaq.zzaj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzj(String str) {
        zzen<String> zzenVar = zzaq.zzak;
        if (str == null) {
            return zzenVar.zza(null);
        }
        return zzenVar.zza(this.zzb.zza(str, zzenVar.zza()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzy() {
        if (this.zza == null) {
            Boolean zze = zze("app_measurement_lite");
            this.zza = zze;
            if (zze == null) {
                this.zza = false;
            }
        }
        return this.zza.booleanValue() || !this.zzy.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
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
