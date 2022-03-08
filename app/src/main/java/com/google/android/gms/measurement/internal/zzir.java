package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzw;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzir extends zzg {
    private zzem zzb;
    private volatile Boolean zzc;
    private final zzag zzd;
    private final zzkc zze;
    private final zzag zzg;
    private final List<Runnable> zzf = new ArrayList();
    private final zzji zza = new zzji(this);

    /* JADX INFO: Access modifiers changed from: protected */
    public zzir(zzfy zzfyVar) {
        super(zzfyVar);
        this.zze = new zzkc(zzfyVar.zzm());
        this.zzd = new zziq(this, zzfyVar);
        this.zzg = new zzja(this, zzfyVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zzz() {
        return false;
    }

    public final boolean zzab() {
        zzd();
        zzw();
        return this.zzb != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzac() {
        zzd();
        zzw();
        zza(new zzjd(this, zza(true)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzem zzemVar, AbstractSafeParcelable abstractSafeParcelable, zzn zznVar) {
        int i;
        List<AbstractSafeParcelable> zza;
        zzd();
        zzb();
        zzw();
        boolean zzaj = zzaj();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            if (!zzaj || (zza = zzj().zza(100)) == null) {
                i = 0;
            } else {
                arrayList.addAll(zza);
                i = zza.size();
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzao) {
                    try {
                        zzemVar.zza((zzao) abstractSafeParcelable2, zznVar);
                    } catch (RemoteException e) {
                        zzr().zzf().zza("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkq) {
                    try {
                        zzemVar.zza((zzkq) abstractSafeParcelable2, zznVar);
                    } catch (RemoteException e2) {
                        zzr().zzf().zza("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzw) {
                    try {
                        zzemVar.zza((zzw) abstractSafeParcelable2, zznVar);
                    } catch (RemoteException e3) {
                        zzr().zzf().zza("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    zzr().zzf().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzao zzaoVar, String str) {
        Preconditions.checkNotNull(zzaoVar);
        zzd();
        zzw();
        boolean zzaj = zzaj();
        zza(new zzjc(this, zzaj, zzaj && zzj().zza(zzaoVar), zzaoVar, zza(true), str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzw zzwVar) {
        Preconditions.checkNotNull(zzwVar);
        zzd();
        zzw();
        zzu();
        zza(new zzjf(this, true, zzj().zza(zzwVar), new zzw(zzwVar), zza(true), zzwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzw>> atomicReference, String str, String str2, String str3) {
        zzd();
        zzw();
        zza(new zzje(this, atomicReference, str, str2, str3, zza(false)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzw zzwVar, String str, String str2) {
        zzd();
        zzw();
        zza(new zzjh(this, str, str2, zza(false), zzwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzkq>> atomicReference, String str, String str2, String str3, boolean z) {
        zzd();
        zzw();
        zza(new zzjg(this, atomicReference, str, str2, str3, z, zza(false)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzw zzwVar, String str, String str2, boolean z) {
        zzd();
        zzw();
        zza(new zzjj(this, str, str2, z, zza(false), zzwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzkq zzkqVar) {
        zzd();
        zzw();
        zza(new zzit(this, zzaj() && zzj().zza(zzkqVar), zzkqVar, zza(true)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzkq>> atomicReference, boolean z) {
        zzd();
        zzw();
        zza(new zzis(this, atomicReference, zza(false), z));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzad() {
        zzd();
        zzb();
        zzw();
        zzn zza = zza(false);
        if (zzaj()) {
            zzj().zzab();
        }
        zza(new zziv(this, zza));
    }

    private final boolean zzaj() {
        zzu();
        return true;
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzd();
        zzw();
        zza(new zziu(this, atomicReference, zza(false)));
    }

    public final void zza(zzw zzwVar) {
        zzd();
        zzw();
        zza(new zzix(this, zza(false), zzwVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzae() {
        zzd();
        zzw();
        zzn zza = zza(true);
        zzj().zzac();
        zza(new zziw(this, zza));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzij zzijVar) {
        zzd();
        zzw();
        zza(new zziz(this, zzijVar));
    }

    public final void zza(Bundle bundle) {
        zzd();
        zzw();
        zza(new zziy(this, bundle, zza(false)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzak() {
        zzd();
        this.zze.zza();
        this.zzd.zza(zzaq.zzai.zza(null).longValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzaf() {
        zzd();
        zzw();
        if (!zzab()) {
            if (zzal()) {
                this.zza.zzb();
            } else if (!zzt().zzy()) {
                zzu();
                List<ResolveInfo> queryIntentServices = zzn().getPackageManager().queryIntentServices(new Intent().setClassName(zzn(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices != null && queryIntentServices.size() > 0) {
                    Intent intent = new Intent("com.google.android.gms.measurement.START");
                    Context zzn = zzn();
                    zzu();
                    intent.setComponent(new ComponentName(zzn, "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zza.zza(intent);
                    return;
                }
                zzr().zzf().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzag() {
        return this.zzc;
    }

    private final boolean zzal() {
        boolean z;
        zzd();
        zzw();
        if (this.zzc == null) {
            zzd();
            zzw();
            Boolean zzj = zzs().zzj();
            boolean z2 = true;
            if (zzj == null || !zzj.booleanValue()) {
                zzu();
                boolean z3 = false;
                if (zzg().zzag() == 1) {
                    z = true;
                } else {
                    zzr().zzx().zza("Checking service availability");
                    int zza = zzp().zza(12451000);
                    if (zza == 0) {
                        zzr().zzx().zza("Service available");
                        z = true;
                    } else if (zza == 1) {
                        zzr().zzx().zza("Service missing");
                        z = true;
                        z2 = false;
                    } else if (zza == 2) {
                        zzr().zzw().zza("Service container out of date");
                        if (zzp().zzj() < 17443) {
                            z = true;
                            z2 = false;
                        } else {
                            if (zzj != null) {
                                z2 = false;
                            }
                            z = false;
                        }
                    } else if (zza == 3) {
                        zzr().zzi().zza("Service disabled");
                        z = false;
                        z2 = false;
                    } else if (zza == 9) {
                        zzr().zzi().zza("Service invalid");
                        z = false;
                        z2 = false;
                    } else if (zza != 18) {
                        zzr().zzi().zza("Unexpected service status", Integer.valueOf(zza));
                        z = false;
                        z2 = false;
                    } else {
                        zzr().zzi().zza("Service updating");
                        z = true;
                    }
                }
                if (z2 || !zzt().zzy()) {
                    z3 = z;
                } else {
                    zzr().zzf().zza("No way to upload. Consider using the full version of Analytics");
                }
                if (z3) {
                    zzs().zza(z2);
                }
            }
            this.zzc = Boolean.valueOf(z2);
        }
        return this.zzc.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(zzem zzemVar) {
        zzd();
        Preconditions.checkNotNull(zzemVar);
        this.zzb = zzemVar;
        zzak();
        zzan();
    }

    public final void zzah() {
        zzd();
        zzw();
        this.zza.zza();
        try {
            ConnectionTracker.getInstance().unbindService(zzn(), this.zza);
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        }
        this.zzb = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(ComponentName componentName) {
        zzd();
        if (this.zzb != null) {
            this.zzb = null;
            zzr().zzx().zza("Disconnected from device MeasurementService", componentName);
            zzd();
            zzaf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzam() {
        zzd();
        if (zzab()) {
            zzr().zzx().zza("Inactivity, disconnecting from the service");
            zzah();
        }
    }

    private final void zza(Runnable runnable) throws IllegalStateException {
        zzd();
        if (zzab()) {
            runnable.run();
        } else if (this.zzf.size() >= 1000) {
            zzr().zzf().zza("Discarding data. Max runnable queue size reached");
        } else {
            this.zzf.add(runnable);
            this.zzg.zza(60000L);
            zzaf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzan() {
        zzd();
        zzr().zzx().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable runnable : this.zzf) {
            try {
                runnable.run();
            } catch (Exception e) {
                zzr().zzf().zza("Task exception while flushing queue", e);
            }
        }
        this.zzf.clear();
        this.zzg.zzc();
    }

    private final zzn zza(boolean z) {
        zzu();
        return zzg().zza(z ? zzr().zzy() : null);
    }

    public final void zza(zzw zzwVar, zzao zzaoVar, String str) {
        zzd();
        zzw();
        if (zzp().zza(12451000) != 0) {
            zzr().zzi().zza("Not bundling data. Service unavailable or out of date");
            zzp().zza(zzwVar, new byte[0]);
            return;
        }
        zza(new zzjb(this, zzaoVar, str, zzwVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzai() {
        zzd();
        zzw();
        return !zzal() || zzp().zzj() >= 200900;
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
