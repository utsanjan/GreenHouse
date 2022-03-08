package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public final class zzg implements ServiceConnection {
    private ComponentName mComponentName;
    private IBinder zzdl;
    private boolean zzeq;
    private final GmsClientSupervisor.zza zzer;
    private final /* synthetic */ zze zzes;
    private final Map<ServiceConnection, ServiceConnection> zzep = new HashMap();
    private int mState = 2;

    public zzg(zze zzeVar, GmsClientSupervisor.zza zzaVar) {
        this.zzes = zzeVar;
        this.zzer = zzaVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.zzes.zzee;
        synchronized (hashMap) {
            handler = this.zzes.mHandler;
            handler.removeMessages(1, this.zzer);
            this.zzdl = iBinder;
            this.mComponentName = componentName;
            for (ServiceConnection serviceConnection : this.zzep.values()) {
                serviceConnection.onServiceConnected(componentName, iBinder);
            }
            this.mState = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        HashMap hashMap;
        Handler handler;
        hashMap = this.zzes.zzee;
        synchronized (hashMap) {
            handler = this.zzes.mHandler;
            handler.removeMessages(1, this.zzer);
            this.zzdl = null;
            this.mComponentName = componentName;
            for (ServiceConnection serviceConnection : this.zzep.values()) {
                serviceConnection.onServiceDisconnected(componentName);
            }
            this.mState = 2;
        }
    }

    public final void zzf(String str) {
        ConnectionTracker connectionTracker;
        Context context;
        Context context2;
        ConnectionTracker connectionTracker2;
        Context context3;
        Handler handler;
        Handler handler2;
        long j;
        this.mState = 3;
        connectionTracker = this.zzes.zzeg;
        context = this.zzes.zzef;
        GmsClientSupervisor.zza zzaVar = this.zzer;
        context2 = this.zzes.zzef;
        boolean zza = connectionTracker.zza(context, str, zzaVar.zzb(context2), this, this.zzer.zzq());
        this.zzeq = zza;
        if (zza) {
            handler = this.zzes.mHandler;
            Message obtainMessage = handler.obtainMessage(1, this.zzer);
            handler2 = this.zzes.mHandler;
            j = this.zzes.zzei;
            handler2.sendMessageDelayed(obtainMessage, j);
            return;
        }
        this.mState = 2;
        try {
            connectionTracker2 = this.zzes.zzeg;
            context3 = this.zzes.zzef;
            connectionTracker2.unbindService(context3, this);
        } catch (IllegalArgumentException e) {
        }
    }

    public final void zzg(String str) {
        Handler handler;
        ConnectionTracker connectionTracker;
        Context context;
        handler = this.zzes.mHandler;
        handler.removeMessages(1, this.zzer);
        connectionTracker = this.zzes.zzeg;
        context = this.zzes.zzef;
        connectionTracker.unbindService(context, this);
        this.zzeq = false;
        this.mState = 2;
    }

    public final void zza(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
        Context context;
        ConnectionTracker unused;
        Context unused2;
        unused = this.zzes.zzeg;
        unused2 = this.zzes.zzef;
        GmsClientSupervisor.zza zzaVar = this.zzer;
        context = this.zzes.zzef;
        zzaVar.zzb(context);
        this.zzep.put(serviceConnection, serviceConnection2);
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        ConnectionTracker unused;
        Context unused2;
        unused = this.zzes.zzeg;
        unused2 = this.zzes.zzef;
        this.zzep.remove(serviceConnection);
    }

    public final boolean isBound() {
        return this.zzeq;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zzep.containsKey(serviceConnection);
    }

    public final boolean zzs() {
        return this.zzep.isEmpty();
    }

    public final IBinder getBinder() {
        return this.zzdl;
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }
}
