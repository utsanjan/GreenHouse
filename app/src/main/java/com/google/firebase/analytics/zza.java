package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzag;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.4.3 */
/* loaded from: classes.dex */
final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        String zzb;
        zzag zzagVar;
        zzb = this.zza.zzb();
        if (zzb != null) {
            return zzb;
        }
        zzagVar = this.zza.zzb;
        String zzh = zzagVar.zzh();
        if (zzh != null) {
            this.zza.zza(zzh);
            return zzh;
        }
        throw new TimeoutException();
    }
}
