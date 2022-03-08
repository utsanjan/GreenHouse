package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzt {
    private zzcb.zzc zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzo zzd;

    private zzt(zzo zzoVar) {
        this.zzd = zzoVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzcb.zzc zza(String str, zzcb.zzc zzcVar) {
        String zzc = zzcVar.zzc();
        List<zzcb.zze> zza = zzcVar.zza();
        Long l = (Long) this.zzd.zzg().zzb(zzcVar, "_eid");
        boolean z = l != null;
        if (z && zzc.equals("_ep")) {
            zzc = (String) this.zzd.zzg().zzb(zzcVar, "_en");
            if (TextUtils.isEmpty(zzc)) {
                this.zzd.zzr().zzg().zza("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzcb.zzc, Long> zza2 = this.zzd.zzi().zza(str, l);
                if (zza2 == null || zza2.first == null) {
                    this.zzd.zzr().zzg().zza("Extra parameter without existing main event. eventName, eventId", zzc, l);
                    return null;
                }
                this.zza = (zzcb.zzc) zza2.first;
                this.zzc = ((Long) zza2.second).longValue();
                this.zzb = (Long) this.zzd.zzg().zzb(this.zza, "_eid");
            }
            long j = this.zzc - 1;
            this.zzc = j;
            if (j <= 0) {
                zzad zzi = this.zzd.zzi();
                zzi.zzd();
                zzi.zzr().zzx().zza("Clearing complex main event info. appId", str);
                try {
                    zzi.c_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    zzi.zzr().zzf().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzi().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzcb.zze zzeVar : this.zza.zza()) {
                this.zzd.zzg();
                if (zzkn.zza(zzcVar, zzeVar.zzb()) == null) {
                    arrayList.add(zzeVar);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(zza);
                zza = arrayList;
            } else {
                this.zzd.zzr().zzg().zza("No unique parameters in main event. eventName", zzc);
            }
        } else if (z) {
            this.zzb = l;
            this.zza = zzcVar;
            Long l2 = 0L;
            Object zzb = this.zzd.zzg().zzb(zzcVar, "_epc");
            if (zzb != null) {
                l2 = zzb;
            }
            long longValue = l2.longValue();
            this.zzc = longValue;
            if (longValue <= 0) {
                this.zzd.zzr().zzg().zza("Complex event with zero extra param count. eventName", zzc);
            } else {
                this.zzd.zzi().zza(str, l, this.zzc, zzcVar);
            }
        }
        return (zzcb.zzc) ((zzfo) zzcVar.zzbl().zza(zzc).zzc().zza(zza).zzv());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzt(zzo zzoVar, zzr zzrVar) {
        this(zzoVar);
    }
}
