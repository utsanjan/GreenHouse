package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzab implements Runnable {
    final /* synthetic */ zzac zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(zzac zzacVar, String str) {
        this.zza = zzacVar;
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    @Override // java.lang.Runnable
    public final void run() {
        Logger logger;
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzb));
        if (instance.getCurrentUser() != null) {
            Task<GetTokenResult> accessToken = instance.getAccessToken(true);
            logger = zzac.zzc;
            logger.v("Token refreshing started", new Object[0]);
            accessToken.addOnFailureListener(new zzae(this));
        }
    }
}
