package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzfz extends Thread {
    private final BlockingQueue<zzfw<?>> zzb;
    private final /* synthetic */ zzfv zzd;
    private boolean zzc = false;
    private final Object zza = new Object();

    public zzfz(zzfv zzfvVar, String str, BlockingQueue<zzfw<?>> blockingQueue) {
        this.zzd = zzfvVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzb = blockingQueue;
        setName(str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Object obj;
        boolean z;
        Semaphore semaphore;
        boolean z2 = false;
        while (!z2) {
            try {
                semaphore = this.zzd.zzh;
                semaphore.acquire();
                z2 = true;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        try {
            int i = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzfw<?> poll = this.zzb.poll();
                if (poll == null) {
                    synchronized (this.zza) {
                        if (this.zzb.peek() == null) {
                            z = this.zzd.zzi;
                            if (!z) {
                                try {
                                    this.zza.wait(30000L);
                                } catch (InterruptedException e2) {
                                    zza(e2);
                                }
                            }
                        }
                    }
                    obj = this.zzd.zzg;
                    synchronized (obj) {
                        if (this.zzb.peek() == null) {
                            break;
                        }
                    }
                } else {
                    if (!poll.zza) {
                        i = 10;
                    }
                    Process.setThreadPriority(i);
                    poll.run();
                }
            }
            if (this.zzd.zzt().zza(zzaq.zzbx)) {
                zzb();
            }
        } finally {
            zzb();
        }
    }

    private final void zzb() {
        Object obj;
        Semaphore semaphore;
        Object obj2;
        zzfz zzfzVar;
        zzfz zzfzVar2;
        obj = this.zzd.zzg;
        synchronized (obj) {
            if (!this.zzc) {
                semaphore = this.zzd.zzh;
                semaphore.release();
                obj2 = this.zzd.zzg;
                obj2.notifyAll();
                zzfzVar = this.zzd.zza;
                if (this == zzfzVar) {
                    this.zzd.zza = null;
                } else {
                    zzfzVar2 = this.zzd.zzb;
                    if (this == zzfzVar2) {
                        this.zzd.zzb = null;
                    } else {
                        this.zzd.zzr().zzf().zza("Current scheduler thread is neither worker nor network");
                    }
                }
                this.zzc = true;
            }
        }
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzd.zzr().zzi().zza(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}
