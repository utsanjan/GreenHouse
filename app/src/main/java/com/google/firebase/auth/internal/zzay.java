package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzay implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ zzaz zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(zzaz zzazVar) {
        this.zza = zzazVar;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        boolean zzb;
        zzac zzacVar;
        if (z) {
            this.zza.zzc = true;
            this.zza.zza();
            return;
        }
        this.zza.zzc = false;
        zzb = this.zza.zzb();
        if (zzb) {
            zzacVar = this.zza.zzb;
            zzacVar.zza();
        }
    }
}
