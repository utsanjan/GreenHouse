package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.measurement.internal.zzhb;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.3 */
/* loaded from: classes.dex */
public final class zzbf extends zzag.zzb {
    private final /* synthetic */ zzhb zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbf(zzag zzagVar, zzhb zzhbVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zzhbVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    final void zza() throws RemoteException {
        zzv zzvVar;
        zzvVar = this.zzd.zzm;
        zzvVar.setEventInterceptor(new zzag.zza(this.zzc));
    }
}
