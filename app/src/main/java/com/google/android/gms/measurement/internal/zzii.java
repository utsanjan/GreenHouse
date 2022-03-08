package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzkt;
import com.google.android.gms.internal.measurement.zzlf;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzii extends zzg {
    protected zzij zza;
    private volatile zzij zzb;
    private zzij zzc;
    private Activity zze;
    private volatile boolean zzf;
    private volatile zzij zzg;
    private zzij zzh;
    private boolean zzi;
    private zzij zzk;
    private String zzl;
    private final Object zzj = new Object();
    private final Map<Activity, zzij> zzd = new ConcurrentHashMap();

    public zzii(zzfy zzfyVar) {
        super(zzfyVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zzz() {
        return false;
    }

    public final zzij zza(boolean z) {
        zzw();
        zzd();
        if (!zzt().zza(zzaq.zzcc) || !z) {
            return this.zza;
        }
        zzij zzijVar = this.zza;
        return zzijVar != null ? zzijVar : this.zzh;
    }

    public final void zza(Bundle bundle) {
        String str;
        String str2;
        if (!zzt().zza(zzaq.zzcc)) {
            zzr().zzk().zza("Manual screen reporting is disabled.");
            return;
        }
        synchronized (this.zzj) {
            if (!this.zzi) {
                zzr().zzk().zza("Cannot log screen view event when the app is in the background.");
                return;
            }
            String str3 = null;
            if (bundle != null) {
                String string = bundle.getString("screen_name");
                if (string == null || (string.length() > 0 && string.length() <= 100)) {
                    String string2 = bundle.getString("screen_class");
                    if (string2 == null || (string2.length() > 0 && string2.length() <= 100)) {
                        str = string;
                        str3 = string2;
                    } else {
                        zzr().zzk().zza("Invalid screen class length for screen view. Length", Integer.valueOf(string2.length()));
                        return;
                    }
                } else {
                    zzr().zzk().zza("Invalid screen name length for screen view. Length", Integer.valueOf(string.length()));
                    return;
                }
            } else {
                str = null;
            }
            if (str3 != null) {
                str2 = str3;
            } else if (this.zze != null) {
                str2 = zza(this.zze.getClass().getCanonicalName());
            } else {
                str2 = "Activity";
            }
            if (this.zzf && this.zzb != null) {
                this.zzf = false;
                boolean zzc = zzkr.zzc(this.zzb.zzb, str2);
                boolean zzc2 = zzkr.zzc(this.zzb.zza, str);
                if (zzc && zzc2) {
                    zzr().zzk().zza("Ignoring call to log screen view event with duplicate parameters.");
                    return;
                }
            }
            zzr().zzx().zza("Logging screen view with name, class", str == null ? "null" : str, str2 == null ? "null" : str2);
            zzij zzijVar = this.zzb == null ? this.zzc : this.zzb;
            zzij zzijVar2 = new zzij(str, str2, zzp().zzg(), true);
            this.zzb = zzijVar2;
            this.zzc = zzijVar;
            this.zzg = zzijVar2;
            zzq().zza(new zzil(this, bundle, zzijVar2, zzijVar, zzm().elapsedRealtime()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Bundle bundle, zzij zzijVar, zzij zzijVar2, long j) {
        if (bundle != null) {
            bundle.remove("screen_name");
            bundle.remove("screen_class");
        }
        zza(zzijVar, zzijVar2, j, true, zzp().zza((String) null, "screen_view", bundle, (List<String>) null, true, true));
    }

    public final void zza(Activity activity, String str, String str2) {
        if (!zzt().zzj().booleanValue()) {
            zzr().zzk().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
        } else if (this.zzb == null) {
            zzr().zzk().zza("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzd.get(activity) == null) {
            zzr().zzk().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zza(activity.getClass().getCanonicalName());
            }
            boolean zzc = zzkr.zzc(this.zzb.zzb, str2);
            boolean zzc2 = zzkr.zzc(this.zzb.zza, str);
            if (zzc && zzc2) {
                zzr().zzk().zza("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzr().zzk().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                zzr().zzx().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
                zzij zzijVar = new zzij(str, str2, zzp().zzg());
                this.zzd.put(activity, zzijVar);
                zza(activity, zzijVar, true);
            } else {
                zzr().zzk().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final zzij zzab() {
        zzb();
        return this.zzb;
    }

    private final void zza(Activity activity, zzij zzijVar, boolean z) {
        zzij zzijVar2;
        zzij zzijVar3 = this.zzb == null ? this.zzc : this.zzb;
        if (zzijVar.zzb == null) {
            zzijVar2 = new zzij(zzijVar.zza, activity != null ? zza(activity.getClass().getCanonicalName()) : null, zzijVar.zzc, zzijVar.zze);
        } else {
            zzijVar2 = zzijVar;
        }
        this.zzc = this.zzb;
        this.zzb = zzijVar2;
        zzq().zza(new zzik(this, zzijVar2, zzijVar3, zzm().elapsedRealtime(), z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzij zzijVar, zzij zzijVar2, long j, boolean z, Bundle bundle) {
        boolean z2;
        long j2;
        zzij zzijVar3;
        zzd();
        boolean z3 = false;
        if (zzt().zza(zzaq.zzat)) {
            z2 = z && this.zza != null;
            if (z2) {
                zza(this.zza, true, j);
            }
        } else {
            if (z && (zzijVar3 = this.zza) != null) {
                zza(zzijVar3, true, j);
            }
            z2 = false;
        }
        if (zzijVar2 == null || zzijVar2.zzc != zzijVar.zzc || !zzkr.zzc(zzijVar2.zzb, zzijVar.zzb) || !zzkr.zzc(zzijVar2.zza, zzijVar.zza)) {
            z3 = true;
        }
        if (z3) {
            Bundle bundle2 = new Bundle();
            if (zzt().zza(zzaq.zzcc)) {
                bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            }
            zza(zzijVar, bundle2, true);
            if (zzijVar2 != null) {
                if (zzijVar2.zza != null) {
                    bundle2.putString("_pn", zzijVar2.zza);
                }
                if (zzijVar2.zzb != null) {
                    bundle2.putString("_pc", zzijVar2.zzb);
                }
                bundle2.putLong("_pi", zzijVar2.zzc);
            }
            if (zzt().zza(zzaq.zzat) && z2) {
                if (!zzlf.zzb() || !zzt().zza(zzaq.zzav) || !zzkt.zzb() || !zzt().zza(zzaq.zzbz)) {
                    j2 = zzk().zzb.zzb();
                } else {
                    j2 = zzk().zza(j);
                }
                if (j2 > 0) {
                    zzp().zza(bundle2, j2);
                }
            }
            String str = "auto";
            if (zzt().zza(zzaq.zzcc)) {
                if (!zzt().zzj().booleanValue()) {
                    bundle2.putLong("_mt", 1L);
                }
                if (zzijVar.zze) {
                    str = "app";
                }
            }
            zzf().zzb(str, "_vs", bundle2);
        }
        this.zza = zzijVar;
        if (zzt().zza(zzaq.zzcc) && zzijVar.zze) {
            this.zzh = zzijVar;
        }
        zzh().zza(zzijVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzij zzijVar, boolean z, long j) {
        zze().zza(zzm().elapsedRealtime());
        if (zzk().zza(zzijVar != null && zzijVar.zzd, z, j) && zzijVar != null) {
            zzijVar.zzd = false;
        }
    }

    public static void zza(zzij zzijVar, Bundle bundle, boolean z) {
        if (bundle != null && zzijVar != null && (!bundle.containsKey("_sc") || z)) {
            if (zzijVar.zza != null) {
                bundle.putString("_sn", zzijVar.zza);
            } else {
                bundle.remove("_sn");
            }
            if (zzijVar.zzb != null) {
                bundle.putString("_sc", zzijVar.zzb);
            } else {
                bundle.remove("_sc");
            }
            bundle.putLong("_si", zzijVar.zzc);
        } else if (bundle != null && zzijVar == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    public final void zza(String str, zzij zzijVar) {
        zzd();
        synchronized (this) {
            if (this.zzl == null || this.zzl.equals(str) || zzijVar != null) {
                this.zzl = str;
                this.zzk = zzijVar;
            }
        }
    }

    private static String zza(String str) {
        String str2;
        String[] split = str.split("\\.");
        if (split.length > 0) {
            str2 = split[split.length - 1];
        } else {
            str2 = "";
        }
        if (str2.length() > 100) {
            return str2.substring(0, 100);
        }
        return str2;
    }

    private final zzij zzd(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzij zzijVar = this.zzd.get(activity);
        if (zzijVar == null) {
            zzij zzijVar2 = new zzij(null, zza(activity.getClass().getCanonicalName()), zzp().zzg());
            this.zzd.put(activity, zzijVar2);
            zzijVar = zzijVar2;
        }
        if (!zzt().zza(zzaq.zzcc)) {
            return zzijVar;
        }
        return this.zzg != null ? this.zzg : zzijVar;
    }

    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (zzt().zzj().booleanValue() && bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzd.put(activity, new zzij(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
        }
    }

    public final void zza(Activity activity) {
        if (zzt().zza(zzaq.zzcc)) {
            synchronized (this.zzj) {
                this.zzi = true;
                if (activity != this.zze) {
                    synchronized (this.zzj) {
                        this.zze = activity;
                        this.zzf = false;
                    }
                    if (zzt().zza(zzaq.zzcb) && zzt().zzj().booleanValue()) {
                        this.zzg = null;
                        zzq().zza(new zzio(this));
                    }
                }
            }
        }
        if (!zzt().zza(zzaq.zzcb) || zzt().zzj().booleanValue()) {
            zza(activity, zzd(activity), false);
            zza zze = zze();
            zze.zzq().zza(new zze(zze, zze.zzm().elapsedRealtime()));
            return;
        }
        this.zzb = this.zzg;
        zzq().zza(new zzin(this));
    }

    public final void zzb(Activity activity) {
        if (zzt().zza(zzaq.zzcc)) {
            synchronized (this.zzj) {
                this.zzi = false;
                this.zzf = true;
            }
        }
        long elapsedRealtime = zzm().elapsedRealtime();
        if (!zzt().zza(zzaq.zzcb) || zzt().zzj().booleanValue()) {
            zzij zzd = zzd(activity);
            this.zzc = this.zzb;
            this.zzb = null;
            zzq().zza(new zzip(this, zzd, elapsedRealtime));
            return;
        }
        this.zzb = null;
        zzq().zza(new zzim(this, elapsedRealtime));
    }

    public final void zzb(Activity activity, Bundle bundle) {
        zzij zzijVar;
        if (zzt().zzj().booleanValue() && bundle != null && (zzijVar = this.zzd.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong("id", zzijVar.zzc);
            bundle2.putString("name", zzijVar.zza);
            bundle2.putString("referrer_name", zzijVar.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    public final void zzc(Activity activity) {
        synchronized (this.zzj) {
            if (activity == this.zze) {
                this.zze = null;
            }
        }
        if (zzt().zzj().booleanValue()) {
            this.zzd.remove(activity);
        }
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
