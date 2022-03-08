package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.common.zzi;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public final class zze extends GmsClientSupervisor implements Handler.Callback {
    private final Handler mHandler;
    private final Context zzef;
    private final HashMap<GmsClientSupervisor.zza, zzg> zzee = new HashMap<>();
    private final ConnectionTracker zzeg = ConnectionTracker.getInstance();
    private final long zzeh = 5000;
    private final long zzei = 300000;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zze(Context context) {
        this.zzef = context.getApplicationContext();
        this.mHandler = new zzi(context.getMainLooper(), this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    public final boolean zza(GmsClientSupervisor.zza zzaVar, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzee) {
            zzg zzgVar = this.zzee.get(zzaVar);
            if (zzgVar == null) {
                zzgVar = new zzg(this, zzaVar);
                zzgVar.zza(serviceConnection, serviceConnection, str);
                zzgVar.zzf(str);
                this.zzee.put(zzaVar, zzgVar);
            } else {
                this.mHandler.removeMessages(0, zzaVar);
                if (!zzgVar.zza(serviceConnection)) {
                    zzgVar.zza(serviceConnection, serviceConnection, str);
                    int state = zzgVar.getState();
                    if (state == 1) {
                        serviceConnection.onServiceConnected(zzgVar.getComponentName(), zzgVar.getBinder());
                    } else if (state == 2) {
                        zzgVar.zzf(str);
                    }
                } else {
                    String valueOf = String.valueOf(zzaVar);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(valueOf);
                    throw new IllegalStateException(sb.toString());
                }
            }
            isBound = zzgVar.isBound();
        }
        return isBound;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected final void zzb(GmsClientSupervisor.zza zzaVar, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzee) {
            zzg zzgVar = this.zzee.get(zzaVar);
            if (zzgVar == null) {
                String valueOf = String.valueOf(zzaVar);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            } else if (zzgVar.zza(serviceConnection)) {
                zzgVar.zza(serviceConnection, str);
                if (zzgVar.zzs()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zzaVar), this.zzeh);
                }
            } else {
                String valueOf2 = String.valueOf(zzaVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(valueOf2);
                throw new IllegalStateException(sb2.toString());
            }
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.zzee) {
                GmsClientSupervisor.zza zzaVar = (GmsClientSupervisor.zza) message.obj;
                zzg zzgVar = this.zzee.get(zzaVar);
                if (zzgVar != null && zzgVar.zzs()) {
                    if (zzgVar.isBound()) {
                        zzgVar.zzg("GmsClientSupervisor");
                    }
                    this.zzee.remove(zzaVar);
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            synchronized (this.zzee) {
                GmsClientSupervisor.zza zzaVar2 = (GmsClientSupervisor.zza) message.obj;
                zzg zzgVar2 = this.zzee.get(zzaVar2);
                if (zzgVar2 != null && zzgVar2.getState() == 3) {
                    String valueOf = String.valueOf(zzaVar2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Timeout waiting for ServiceConnection callback ");
                    sb.append(valueOf);
                    Log.e("GmsClientSupervisor", sb.toString(), new Exception());
                    ComponentName componentName = zzgVar2.getComponentName();
                    if (componentName == null) {
                        componentName = zzaVar2.getComponentName();
                    }
                    if (componentName == null) {
                        componentName = new ComponentName(zzaVar2.getPackage(), "unknown");
                    }
                    zzgVar2.onServiceDisconnected(componentName);
                }
            }
            return true;
        }
    }
}
