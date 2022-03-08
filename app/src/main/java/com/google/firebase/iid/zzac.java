package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.firebase_messaging.zze;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class zzac implements ServiceConnection {
    int zza;
    final Messenger zzb;
    zzal zzc;
    final Queue<zzan<?>> zzd;
    final SparseArray<zzan<?>> zze;
    final /* synthetic */ zzab zzf;

    private zzac(zzab zzabVar) {
        this.zzf = zzabVar;
        this.zza = 0;
        this.zzb = new Messenger(new zze(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.firebase.iid.zzaf
            private final zzac zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zza.zza(message);
            }
        }));
        this.zzd = new ArrayDeque();
        this.zze = new SparseArray<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zza(zzan<?> zzanVar) {
        Context context;
        ScheduledExecutorService scheduledExecutorService;
        int i = this.zza;
        if (i == 0) {
            this.zzd.add(zzanVar);
            Preconditions.checkState(this.zza == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.zza = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            ConnectionTracker instance = ConnectionTracker.getInstance();
            context = this.zzf.zzb;
            if (!instance.bindService(context, intent, this, 1)) {
                zza(0, "Unable to bind to service");
            } else {
                scheduledExecutorService = this.zzf.zzc;
                scheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.firebase.iid.zzae
                    private final zzac zza;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zza = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zza.zzc();
                    }
                }, 30L, TimeUnit.SECONDS);
            }
            return true;
        } else if (i == 1) {
            this.zzd.add(zzanVar);
            return true;
        } else if (i != 2) {
            if (!(i == 3 || i == 4)) {
                int i2 = this.zza;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i2);
                throw new IllegalStateException(sb.toString());
            }
            return false;
        } else {
            this.zzd.add(zzanVar);
            zza();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzan<?> zzanVar = this.zze.get(i);
            if (zzanVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zze.remove(i);
            zzb();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzanVar.zza(new zzam(4, "Not supported by GmsCore"));
            } else {
                zzanVar.zza(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        ScheduledExecutorService scheduledExecutorService;
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        scheduledExecutorService = this.zzf.zzc;
        scheduledExecutorService.execute(new Runnable(this, iBinder) { // from class: com.google.firebase.iid.zzah
            private final zzac zza;
            private final IBinder zzb;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzac zzacVar = this.zza;
                IBinder iBinder2 = this.zzb;
                synchronized (zzacVar) {
                    try {
                        if (iBinder2 == null) {
                            zzacVar.zza(0, "Null service connection");
                            return;
                        }
                        try {
                            zzacVar.zzc = new zzal(iBinder2);
                            zzacVar.zza = 2;
                            zzacVar.zza();
                        } catch (RemoteException e) {
                            zzacVar.zza(0, e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = this.zzf.zzc;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzag
            private final zzac zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzan<?> poll;
                ScheduledExecutorService scheduledExecutorService2;
                Context context;
                final zzac zzacVar = this.zza;
                while (true) {
                    synchronized (zzacVar) {
                        if (zzacVar.zza == 2) {
                            if (zzacVar.zzd.isEmpty()) {
                                zzacVar.zzb();
                                return;
                            }
                            poll = zzacVar.zzd.poll();
                            zzacVar.zze.put(poll.zza, poll);
                            scheduledExecutorService2 = zzacVar.zzf.zzc;
                            scheduledExecutorService2.schedule(new Runnable(zzacVar, poll) { // from class: com.google.firebase.iid.zzai
                                private final zzac zza;
                                private final zzan zzb;

                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    this.zza = zzacVar;
                                    this.zzb = poll;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.zza.zza(this.zzb.zza);
                                }
                            }, 30L, TimeUnit.SECONDS);
                        } else {
                            return;
                        }
                    }
                    if (Log.isLoggable("MessengerIpcClient", 3)) {
                        String valueOf = String.valueOf(poll);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 8);
                        sb.append("Sending ");
                        sb.append(valueOf);
                        Log.d("MessengerIpcClient", sb.toString());
                    }
                    context = zzacVar.zzf.zzb;
                    Messenger messenger = zzacVar.zzb;
                    Message obtain = Message.obtain();
                    obtain.what = poll.zzc;
                    obtain.arg1 = poll.zza;
                    obtain.replyTo = messenger;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("oneWay", poll.zza());
                    bundle.putString("pkg", context.getPackageName());
                    bundle.putBundle("data", poll.zzd);
                    obtain.setData(bundle);
                    try {
                        zzacVar.zzc.zza(obtain);
                    } catch (RemoteException e) {
                        zzacVar.zza(2, e.getMessage());
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        ScheduledExecutorService scheduledExecutorService;
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        scheduledExecutorService = this.zzf.zzc;
        scheduledExecutorService.execute(new Runnable(this) { // from class: com.google.firebase.iid.zzaj
            private final zzac zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(2, "Service disconnected");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        Context context;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        int i2 = this.zza;
        if (i2 == 0) {
            throw new IllegalStateException();
        } else if (i2 == 1 || i2 == 2) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Unbinding service");
            }
            this.zza = 4;
            ConnectionTracker instance = ConnectionTracker.getInstance();
            context = this.zzf.zzb;
            instance.unbindService(context, this);
            zzam zzamVar = new zzam(i, str);
            for (zzan<?> zzanVar : this.zzd) {
                zzanVar.zza(zzamVar);
            }
            this.zzd.clear();
            for (int i3 = 0; i3 < this.zze.size(); i3++) {
                this.zze.valueAt(i3).zza(zzamVar);
            }
            this.zze.clear();
        } else if (i2 == 3) {
            this.zza = 4;
        } else if (i2 != 4) {
            int i4 = this.zza;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Unknown state: ");
            sb.append(i4);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzb() {
        Context context;
        if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.zza = 3;
            ConnectionTracker instance = ConnectionTracker.getInstance();
            context = this.zzf.zzb;
            instance.unbindService(context, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zzc() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void zza(int i) {
        zzan<?> zzanVar = this.zze.get(i);
        if (zzanVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zze.remove(i);
            zzanVar.zza(new zzam(3, "Timed out waiting for response"));
            zzb();
        }
    }
}
