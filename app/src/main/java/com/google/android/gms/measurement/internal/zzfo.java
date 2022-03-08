package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzg;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfo implements ServiceConnection {
    final /* synthetic */ zzfp zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfo(zzfp zzfpVar, String str) {
        this.zza = zzfpVar;
        this.zzb = str;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zza.zza.zzr().zzi().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            zzd zza = zzg.zza(iBinder);
            if (zza == null) {
                this.zza.zza.zzr().zzi().zza("Install Referrer Service implementation was not found");
                return;
            }
            this.zza.zza.zzr().zzx().zza("Install Referrer Service connected");
            this.zza.zza.zzq().zza(new zzfr(this, zza, this));
        } catch (Exception e) {
            this.zza.zza.zzr().zzi().zza("Exception occurred while calling Install Referrer API", e);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzr().zzx().zza("Install Referrer Service disconnected");
    }
}
