package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzaz {
    private volatile int zza;
    private final zzac zzb;
    private volatile boolean zzc;

    public zzaz(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), new zzac(firebaseApp));
    }

    private zzaz(Context context, zzac zzacVar) {
        this.zzc = false;
        this.zza = 0;
        this.zzb = zzacVar;
        BackgroundDetector.initialize((Application) context.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzay(this));
    }

    public final void zza(int i) {
        if (i > 0 && this.zza == 0) {
            this.zza = i;
            if (zzb()) {
                this.zzb.zza();
            }
        } else if (i == 0 && this.zza != 0) {
            this.zzb.zzc();
        }
        this.zza = i;
    }

    public final void zza(zzff zzffVar) {
        if (zzffVar != null) {
            long zze = zzffVar.zze();
            if (zze <= 0) {
                zze = 3600;
            }
            zzac zzacVar = this.zzb;
            zzacVar.zza = zzffVar.zzg() + (zze * 1000);
            zzacVar.zzb = -1L;
            if (zzb()) {
                this.zzb.zza();
            }
        }
    }

    public final void zza() {
        this.zzb.zzc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzb() {
        return this.zza > 0 && !this.zzc;
    }
}
