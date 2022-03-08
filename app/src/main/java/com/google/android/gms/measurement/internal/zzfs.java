package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzby;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzjw;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfs extends zzkg implements zzaa {
    private static int zzb = 65535;
    private static int zzc = 2;
    private final Map<String, Map<String, String>> zzd = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zze = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzf = new ArrayMap();
    private final Map<String, zzby.zzb> zzg = new ArrayMap();
    private final Map<String, String> zzi = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzh = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfs(zzkj zzkjVar) {
        super(zzkjVar);
    }

    private final void zzi(String str) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        if (this.zzg.get(str) == null) {
            byte[] zzd = zzi().zzd(str);
            if (zzd == null) {
                this.zzd.put(str, null);
                this.zze.put(str, null);
                this.zzf.put(str, null);
                this.zzg.put(str, null);
                this.zzi.put(str, null);
                this.zzh.put(str, null);
                return;
            }
            zzby.zzb.zza zzbl = zza(str, zzd).zzbl();
            zza(str, zzbl);
            this.zzd.put(str, zza((zzby.zzb) ((zzfo) zzbl.zzv())));
            this.zzg.put(str, (zzby.zzb) ((zzfo) zzbl.zzv()));
            this.zzi.put(str, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzby.zzb zza(String str) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        zzi(str);
        return this.zzg.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzb(String str) {
        zzd();
        return this.zzi.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzc(String str) {
        zzd();
        this.zzi.put(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(String str) {
        zzd();
        this.zzg.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zze(String str) {
        zzd();
        zzby.zzb zza = zza(str);
        if (zza == null) {
            return false;
        }
        return zza.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzaa
    public final String zza(String str, String str2) {
        zzd();
        zzi(str);
        Map<String, String> map = this.zzd.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    private static Map<String, String> zza(zzby.zzb zzbVar) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzbVar != null) {
            for (zzby.zzc zzcVar : zzbVar.zze()) {
                arrayMap.put(zzcVar.zza(), zzcVar.zzb());
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzby.zzb.zza zzaVar) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zzaVar != null) {
            for (int i = 0; i < zzaVar.zza(); i++) {
                zzby.zza.C0038zza zzbl = zzaVar.zza(i).zzbl();
                if (TextUtils.isEmpty(zzbl.zza())) {
                    zzr().zzi().zza("EventConfig contained null event name");
                } else {
                    String zzb2 = zzgw.zzb(zzbl.zza());
                    if (!TextUtils.isEmpty(zzb2)) {
                        zzbl = zzbl.zza(zzb2);
                        zzaVar.zza(i, zzbl);
                    }
                    arrayMap.put(zzbl.zza(), Boolean.valueOf(zzbl.zzb()));
                    arrayMap2.put(zzbl.zza(), Boolean.valueOf(zzbl.zzc()));
                    if (zzbl.zzd()) {
                        if (zzbl.zze() < zzc || zzbl.zze() > zzb) {
                            zzr().zzi().zza("Invalid sampling rate. Event name, sample rate", zzbl.zza(), Integer.valueOf(zzbl.zze()));
                        } else {
                            arrayMap3.put(zzbl.zza(), Integer.valueOf(zzbl.zze()));
                        }
                    }
                }
            }
        }
        this.zze.put(str, arrayMap);
        this.zzf.put(str, arrayMap2);
        this.zzh.put(str, arrayMap3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zza(String str, byte[] bArr, String str2) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        zzby.zzb.zza zzbl = zza(str, bArr).zzbl();
        if (zzbl == null) {
            return false;
        }
        zza(str, zzbl);
        this.zzg.put(str, (zzby.zzb) ((zzfo) zzbl.zzv()));
        this.zzi.put(str, str2);
        this.zzd.put(str, zza((zzby.zzb) ((zzfo) zzbl.zzv())));
        zzi().zza(str, new ArrayList(zzbl.zzb()));
        try {
            zzbl.zzc();
            bArr = ((zzby.zzb) ((zzfo) zzbl.zzv())).zzbi();
        } catch (RuntimeException e) {
            zzr().zzi().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzeu.zza(str), e);
        }
        zzad zzi = zzi();
        Preconditions.checkNotEmpty(str);
        zzi.zzd();
        zzi.zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (zzi.c_().update("apps", contentValues, "app_id = ?", new String[]{str}) == 0) {
                zzi.zzr().zzf().zza("Failed to update remote config (got 0). appId", zzeu.zza(str));
            }
        } catch (SQLiteException e2) {
            zzi.zzr().zzf().zza("Error storing remote config. appId", zzeu.zza(str), e2);
        }
        this.zzg.put(str, (zzby.zzb) ((zzfo) zzbl.zzv()));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzb(String str, String str2) {
        Boolean bool;
        zzd();
        zzi(str);
        if (zzg(str) && zzkr.zze(str2)) {
            return true;
        }
        if (zzh(str) && zzkr.zza(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zze.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzc(String str, String str2) {
        Boolean bool;
        zzd();
        zzi(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        if (zzjw.zzb() && zzt().zza(zzaq.zzci) && (FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2))) {
            return true;
        }
        Map<String, Boolean> map = this.zzf.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzd(String str, String str2) {
        Integer num;
        zzd();
        zzi(str);
        Map<String, Integer> map = this.zzh.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zzf(String str) {
        String zza = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(zza)) {
            return 0L;
        }
        try {
            return Long.parseLong(zza);
        } catch (NumberFormatException e) {
            zzr().zzi().zza("Unable to parse timezone offset. appId", zzeu.zza(str), e);
            return 0L;
        }
    }

    private final zzby.zzb zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzby.zzb.zzj();
        }
        try {
            zzby.zzb zzbVar = (zzby.zzb) ((zzfo) ((zzby.zzb.zza) zzkn.zza(zzby.zzb.zzi(), bArr)).zzv());
            zzew zzx = zzr().zzx();
            String str2 = null;
            Long valueOf = zzbVar.zza() ? Long.valueOf(zzbVar.zzb()) : null;
            if (zzbVar.zzc()) {
                str2 = zzbVar.zzd();
            }
            zzx.zza("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzbVar;
        } catch (zzfw e) {
            zzr().zzi().zza("Unable to merge remote config. appId", zzeu.zza(str), e);
            return zzby.zzb.zzj();
        } catch (RuntimeException e2) {
            zzr().zzi().zza("Unable to merge remote config. appId", zzeu.zza(str), e2);
            return zzby.zzb.zzj();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzg(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzh(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    @Override // com.google.android.gms.measurement.internal.zzkg
    protected final boolean zze() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzkn zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzo e_() {
        return super.e_();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzad zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzfs zzj() {
        return super.zzj();
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
