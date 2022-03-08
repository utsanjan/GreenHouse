package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.internal.firebase_messaging.zza;
import com.google.android.gms.internal.firebase_messaging.zzf;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class zzab {
    private static zzab zza;
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private zzac zzd = new zzac(this, null);
    private int zze = 1;

    public static synchronized zzab zza(Context context) {
        zzab zzabVar;
        synchronized (zzab.class) {
            if (zza == null) {
                zza = new zzab(context, zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), zzf.zzb));
            }
            zzabVar = zza;
        }
        return zzabVar;
    }

    private zzab(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzc = scheduledExecutorService;
        this.zzb = context.getApplicationContext();
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzak(zza(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzap(zza(), 1, bundle));
    }

    private final synchronized <T> Task<T> zza(zzan<T> zzanVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzanVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzd.zza((zzan<?>) zzanVar)) {
            zzac zzacVar = new zzac(this, null);
            this.zzd = zzacVar;
            zzacVar.zza((zzan<?>) zzanVar);
        }
        return zzanVar.zzb.getTask();
    }

    private final synchronized int zza() {
        int i;
        i = this.zze;
        this.zze = i + 1;
        return i;
    }
}
