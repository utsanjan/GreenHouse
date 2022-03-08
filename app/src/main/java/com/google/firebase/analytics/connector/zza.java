package com.google.firebase.analytics.connector;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-measurement-api@@17.4.3 */
/* loaded from: classes.dex */
final /* synthetic */ class zza implements Executor {
    static final Executor zza = new zza();

    private zza() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
