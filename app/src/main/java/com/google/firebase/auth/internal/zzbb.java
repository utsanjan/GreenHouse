package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.firebase_auth.zzj;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbb implements Executor {
    private static zzbb zza = new zzbb();
    private Handler zzb = new zzj(Looper.getMainLooper());

    private zzbb() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.zzb.post(runnable);
    }

    public static zzbb zza() {
        return zza;
    }
}
