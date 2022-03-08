package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzh;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkf extends zzkg {
    private final AlarmManager zzb = (AlarmManager) zzn().getSystemService(NotificationCompat.CATEGORY_ALARM);
    private final zzag zzc;
    private Integer zzd;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzkf(zzkj zzkjVar) {
        super(zzkjVar);
        this.zzc = new zzke(this, zzkjVar.zzs(), zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkg
    protected final boolean zze() {
        this.zzb.cancel(zzw());
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        zzk();
        return false;
    }

    private final void zzk() {
        ((JobScheduler) zzn().getSystemService("jobscheduler")).cancel(zzv());
    }

    public final void zza(long j) {
        zzak();
        zzu();
        Context zzn = zzn();
        if (!zzfq.zza(zzn)) {
            zzr().zzw().zza("Receiver not registered/enabled");
        }
        if (!zzkr.zza(zzn, false)) {
            zzr().zzw().zza("Service not registered/enabled");
        }
        zzf();
        zzr().zzx().zza("Scheduling upload, millis", Long.valueOf(j));
        long elapsedRealtime = zzm().elapsedRealtime() + j;
        if (j < Math.max(0L, zzaq.zzw.zza(null).longValue()) && !this.zzc.zzb()) {
            this.zzc.zza(j);
        }
        zzu();
        if (Build.VERSION.SDK_INT >= 24) {
            Context zzn2 = zzn();
            ComponentName componentName = new ComponentName(zzn2, "com.google.android.gms.measurement.AppMeasurementJobService");
            int zzv = zzv();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            zzh.zza(zzn2, new JobInfo.Builder(zzv, componentName).setMinimumLatency(j).setOverrideDeadline(j << 1).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
            return;
        }
        this.zzb.setInexactRepeating(2, elapsedRealtime, Math.max(zzaq.zzr.zza(null).longValue(), j), zzw());
    }

    private final int zzv() {
        if (this.zzd == null) {
            String valueOf = String.valueOf(zzn().getPackageName());
            this.zzd = Integer.valueOf((valueOf.length() != 0 ? "measurement".concat(valueOf) : new String("measurement")).hashCode());
        }
        return this.zzd.intValue();
    }

    public final void zzf() {
        zzak();
        zzr().zzx().zza("Unscheduling upload");
        this.zzb.cancel(zzw());
        this.zzc.zzc();
        if (Build.VERSION.SDK_INT >= 24) {
            zzk();
        }
    }

    private final PendingIntent zzw() {
        Context zzn = zzn();
        return PendingIntent.getBroadcast(zzn, 0, new Intent().setClassName(zzn, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), 0);
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
