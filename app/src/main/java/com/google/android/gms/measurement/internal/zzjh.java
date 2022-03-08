package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzw;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjh implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzn zzc;
    private final /* synthetic */ zzw zzd;
    private final /* synthetic */ zzir zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjh(zzir zzirVar, String str, String str2, zzn zznVar, zzw zzwVar) {
        this.zze = zzirVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zznVar;
        this.zzd = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            zzemVar = this.zze.zzb;
            if (zzemVar == null) {
                this.zze.zzr().zzf().zza("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                return;
            }
            arrayList = zzkr.zzb(zzemVar.zza(this.zza, this.zzb, this.zzc));
            this.zze.zzak();
        } catch (RemoteException e) {
            this.zze.zzr().zzf().zza("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
        } finally {
            this.zze.zzp().zza(this.zzd, arrayList);
        }
    }
}
