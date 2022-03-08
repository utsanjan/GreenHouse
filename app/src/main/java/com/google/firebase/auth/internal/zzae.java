package com.google.firebase.auth.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseNetworkException;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzae implements OnFailureListener {
    private final /* synthetic */ zzab zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzae(zzab zzabVar) {
        this.zza = zzabVar;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        Logger logger;
        if (exc instanceof FirebaseNetworkException) {
            logger = zzac.zzc;
            logger.v("Failure to refresh token; scheduling refresh after failure", new Object[0]);
            this.zza.zza.zzb();
        }
    }
}
