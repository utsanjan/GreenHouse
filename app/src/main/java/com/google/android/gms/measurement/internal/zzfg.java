package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfg extends zzgv {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private String zzaa;
    private boolean zzab;
    private long zzac;
    public zzfn zzb;
    public boolean zzq;
    private SharedPreferences zzz;
    public final zzfk zzc = new zzfk(this, "last_upload", 0);
    public final zzfk zzd = new zzfk(this, "last_upload_attempt", 0);
    public final zzfk zze = new zzfk(this, "backoff", 0);
    public final zzfk zzf = new zzfk(this, "last_delete_stale", 0);
    public final zzfk zzk = new zzfk(this, "time_before_start", 10000);
    public final zzfk zzl = new zzfk(this, "session_timeout", 1800000);
    public final zzfi zzm = new zzfi(this, "start_new_session", true);
    public final zzfk zzp = new zzfk(this, "last_pause_time", 0);
    public final zzfm zzn = new zzfm(this, "non_personalized_ads", null);
    public final zzfi zzo = new zzfi(this, "allow_remote_dynamite", false);
    public final zzfk zzg = new zzfk(this, "midnight_offset", 0);
    public final zzfk zzh = new zzfk(this, "first_open_time", 0);
    public final zzfk zzi = new zzfk(this, "app_install_time", 0);
    public final zzfm zzj = new zzfm(this, "app_instance_id", null);
    public zzfi zzr = new zzfi(this, "app_backgrounded", false);
    public zzfi zzs = new zzfi(this, "deep_link_retrieval_complete", false);
    public zzfk zzt = new zzfk(this, "deep_link_retrieval_attempts", 0);
    public final zzfm zzu = new zzfm(this, "firebase_feature_rollouts", null);
    public final zzfm zzv = new zzfm(this, "deferred_attribution_cache", null);
    public final zzfk zzw = new zzfk(this, "deferred_attribution_cache_timestamp", 0);
    public final zzfl zzx = new zzfl(this, "default_event_parameters", null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str) {
        zzd();
        long elapsedRealtime = zzm().elapsedRealtime();
        if (this.zzaa != null && elapsedRealtime < this.zzac) {
            return new Pair<>(this.zzaa, Boolean.valueOf(this.zzab));
        }
        this.zzac = elapsedRealtime + zzt().zza(str, zzaq.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzn());
            if (advertisingIdInfo != null) {
                this.zzaa = advertisingIdInfo.getId();
                this.zzab = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzaa == null) {
                this.zzaa = "";
            }
        } catch (Exception e) {
            zzr().zzw().zza("Unable to get advertising id", e);
            this.zzaa = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzaa, Boolean.valueOf(this.zzab));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzb(String str) {
        zzd();
        String str2 = (String) zza(str).first;
        MessageDigest zzi = zzkr.zzi();
        if (zzi == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, zzi.digest(str2.getBytes())));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfg(zzfy zzfyVar) {
        super(zzfyVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzgv
    protected final boolean zze() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzgv
    protected final void f_() {
        SharedPreferences sharedPreferences = zzn().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzz = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzq = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzz.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzb = new zzfn(this, "health_monitor", Math.max(0L, zzaq.zzb.zza(null).longValue()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final SharedPreferences zzg() {
        zzd();
        zzaa();
        return this.zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(String str) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzh() {
        zzd();
        return zzg().getString("gmp_app_id", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(String str) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzi() {
        zzd();
        return zzg().getString("admob_app_id", null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzj() {
        zzd();
        if (!zzg().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzg().getBoolean("use_service", false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk() {
        zzd();
        Boolean zzv = zzv();
        SharedPreferences.Editor edit = zzg().edit();
        edit.clear();
        edit.apply();
        if (zzv != null) {
            zzb(zzv.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(boolean z) {
        zzd();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzv() {
        zzd();
        if (zzg().contains("measurement_enabled")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzw() {
        zzd();
        String string = zzg().getString("previous_os_version", null);
        zzl().zzaa();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(boolean z) {
        zzd();
        zzr().zzx().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzx() {
        return this.zzz.contains("deferred_analytics_collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(long j) {
        return j - this.zzl.zza() > this.zzp.zza();
    }
}
