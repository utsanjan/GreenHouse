package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class zzbh implements ServiceConnection {
    private final Context zza;
    private final Intent zzb;
    private final ScheduledExecutorService zzc;
    private final Queue<zzbg> zzd;
    private zzbc zze;
    private boolean zzf;

    public zzbh(Context context, String str) {
        this(context, str, new ScheduledThreadPoolExecutor(0, new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
    }

    private zzbh(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.zzd = new ArrayDeque();
        this.zzf = false;
        this.zza = context.getApplicationContext();
        this.zzb = new Intent(str).setPackage(this.zza.getPackageName());
        this.zzc = scheduledExecutorService;
    }

    public final synchronized Task<Void> zza(Intent intent) {
        final zzbg zzbgVar;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "new intent queued in the bind-strategy delivery");
        }
        zzbgVar = new zzbg(intent);
        ScheduledExecutorService scheduledExecutorService = this.zzc;
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable(zzbgVar) { // from class: com.google.firebase.iid.zzbj
            private final zzbg zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = zzbgVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzc();
            }
        }, 9000L, TimeUnit.MILLISECONDS);
        zzbgVar.zza().addOnCompleteListener(scheduledExecutorService, new OnCompleteListener(schedule) { // from class: com.google.firebase.iid.zzbi
            private final ScheduledFuture zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = schedule;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zza.cancel(false);
            }
        });
        this.zzd.add(zzbgVar);
        zza();
        return zzbgVar.zza();
    }

    private final synchronized void zza() {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "flush queue called");
        }
        while (!this.zzd.isEmpty()) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "found intent to be delivered");
            }
            if (this.zze == null || !this.zze.isBinderAlive()) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    boolean z = !this.zzf;
                    StringBuilder sb = new StringBuilder(39);
                    sb.append("binder is dead. start connection? ");
                    sb.append(z);
                    Log.d("FirebaseInstanceId", sb.toString());
                }
                if (!this.zzf) {
                    this.zzf = true;
                    try {
                    } catch (SecurityException e) {
                        Log.e("FirebaseInstanceId", "Exception while binding the service", e);
                    }
                    if (!ConnectionTracker.getInstance().bindService(this.zza, this.zzb, this, 65)) {
                        Log.e("FirebaseInstanceId", "binding to the service failed");
                        this.zzf = false;
                        zzb();
                    } else {
                        return;
                    }
                }
                return;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "binder is alive, sending the intent.");
            }
            this.zze.zza(this.zzd.poll());
        }
    }

    private final void zzb() {
        while (!this.zzd.isEmpty()) {
            this.zzd.poll().zzb();
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("onServiceConnected: ");
            sb.append(valueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        this.zzf = false;
        if (!(iBinder instanceof zzbc)) {
            String valueOf2 = String.valueOf(iBinder);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 28);
            sb2.append("Invalid service connection: ");
            sb2.append(valueOf2);
            Log.e("FirebaseInstanceId", sb2.toString());
            zzb();
            return;
        }
        this.zze = (zzbc) iBinder;
        zza();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(componentName);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
            sb.append("onServiceDisconnected: ");
            sb.append(valueOf);
            Log.d("FirebaseInstanceId", sb.toString());
        }
        zza();
    }
}
