package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.3 */
/* loaded from: classes.dex */
public final class zzbc extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzt zzd;
    private final /* synthetic */ zzag zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbc(zzag zzagVar, String str, zzt zztVar) {
        super(zzagVar);
        this.zze = zzagVar;
        this.zzc = str;
        this.zzd = zztVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    final void zza() throws RemoteException {
        zzv zzvVar;
        zzvVar = this.zze.zzm;
        zzvVar.getMaxUserProperties(this.zzc, this.zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    protected final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
