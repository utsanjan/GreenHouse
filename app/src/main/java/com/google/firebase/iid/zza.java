package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class zza implements zzb {
    private static final Object zza = new Object();
    private static zzbh zzb;
    private final Context zzc;
    private final ExecutorService zzd;

    public zza(Context context, ExecutorService executorService) {
        this.zzc = context;
        this.zzd = executorService;
    }

    @Override // com.google.firebase.iid.zzb
    public final Task<Integer> zza(final Intent intent) {
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        boolean z = false;
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        final Context context = this.zzc;
        boolean z2 = PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26;
        if ((intent.getFlags() & 268435456) != 0) {
            z = true;
        }
        if (!z2 || z) {
            return Tasks.call(this.zzd, new Callable(context, intent) { // from class: com.google.firebase.iid.zzd
                private final Context zza;
                private final Intent zzb;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zza = context;
                    this.zzb = intent;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Integer valueOf;
                    valueOf = Integer.valueOf(zzaw.zza().zza(this.zza, this.zzb));
                    return valueOf;
                }
            }).continueWithTask(this.zzd, new Continuation(context, intent) { // from class: com.google.firebase.iid.zzc
                private final Context zza;
                private final Intent zzb;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zza = context;
                    this.zzb = intent;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zza.zza(this.zza, this.zzb, task);
                }
            });
        }
        return zzb(context, intent);
    }

    private static Task<Integer> zzb(Context context, Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Binding to service");
        }
        return zza(context, "com.google.firebase.MESSAGING_EVENT").zza(intent).continueWith(zzh.zza(), zzf.zza);
    }

    private static zzbh zza(Context context, String str) {
        zzbh zzbhVar;
        synchronized (zza) {
            if (zzb == null) {
                zzb = new zzbh(context, str);
            }
            zzbhVar = zzb;
        }
        return zzbhVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Integer zza(Task task) throws Exception {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Task zza(Context context, Intent intent, Task task) throws Exception {
        if (!PlatformVersion.isAtLeastO() || ((Integer) task.getResult()).intValue() != 402) {
            return task;
        }
        return zzb(context, intent).continueWith(zzh.zza(), zze.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Integer zzb(Task task) throws Exception {
        return 403;
    }
}
