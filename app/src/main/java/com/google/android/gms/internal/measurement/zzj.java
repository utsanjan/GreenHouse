package com.google.android.gms.internal.measurement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.3 */
/* loaded from: classes.dex */
public interface zzj {
    ExecutorService zza(int i);

    ExecutorService zza(ThreadFactory threadFactory, int i);
}
