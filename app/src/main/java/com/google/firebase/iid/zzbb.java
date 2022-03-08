package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class zzbb implements Runnable {
    private final long zza;
    private final PowerManager.WakeLock zzb;
    private final FirebaseInstanceId zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbb(FirebaseInstanceId firebaseInstanceId, long j) {
        this.zzc = firebaseInstanceId;
        this.zza = j;
        PowerManager.WakeLock newWakeLock = ((PowerManager) zza().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.zzb = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (zzaw.zza().zza(zza())) {
            this.zzb.acquire();
        }
        try {
            try {
                this.zzc.zza(true);
                if (!this.zzc.zzf()) {
                    this.zzc.zza(false);
                    if (zzaw.zza().zza(zza())) {
                        this.zzb.release();
                    }
                } else if (!zzaw.zza().zzb(zza()) || zzb()) {
                    if (zzc()) {
                        this.zzc.zza(false);
                    } else {
                        this.zzc.zza(this.zza);
                    }
                    if (zzaw.zza().zza(zza())) {
                        this.zzb.release();
                    }
                } else {
                    new zzba(this).zza();
                    if (zzaw.zza().zza(zza())) {
                        this.zzb.release();
                    }
                }
            } catch (IOException e) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 93);
                sb.append("Topic sync or token retrieval failed on hard failure exceptions: ");
                sb.append(message);
                sb.append(". Won't retry the operation.");
                Log.e("FirebaseInstanceId", sb.toString());
                this.zzc.zza(false);
                if (zzaw.zza().zza(zza())) {
                    this.zzb.release();
                }
            }
        } catch (Throwable th) {
            if (zzaw.zza().zza(zza())) {
                this.zzb.release();
            }
            throw th;
        }
    }

    private final boolean zzc() throws IOException {
        zzay zzb = this.zzc.zzb();
        boolean z = true;
        if (!this.zzc.zza(zzb)) {
            return true;
        }
        try {
            String zzc = this.zzc.zzc();
            if (zzc == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if ((zzb == null || (zzb != null && !zzc.equals(zzb.zza))) && FirebaseApp.DEFAULT_APP_NAME.equals(this.zzc.zza().getName())) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(this.zzc.zza().getName());
                    Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Invoking onNewToken for app: ".concat(valueOf) : new String("Invoking onNewToken for app: "));
                }
                Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                intent.putExtra("token", zzc);
                Context zza = zza();
                Intent intent2 = new Intent(zza, FirebaseInstanceIdReceiver.class);
                intent2.setAction("com.google.firebase.MESSAGING_EVENT");
                intent2.putExtra("wrapped_intent", intent);
                zza.sendBroadcast(intent2);
            }
            return true;
        } catch (IOException e) {
            String message = e.getMessage();
            if (!"SERVICE_NOT_AVAILABLE".equals(message) && !"INTERNAL_SERVER_ERROR".equals(message) && !"InternalServerError".equals(message)) {
                z = false;
            }
            if (z) {
                String message2 = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message2).length() + 52);
                sb.append("Token retrieval failed: ");
                sb.append(message2);
                sb.append(". Will retry token retrieval");
                Log.w("FirebaseInstanceId", sb.toString());
                return false;
            } else if (e.getMessage() == null) {
                Log.w("FirebaseInstanceId", "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            } else {
                throw e;
            }
        } catch (SecurityException e2) {
            Log.w("FirebaseInstanceId", "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context zza() {
        return this.zzc.zza().getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzb() {
        ConnectivityManager connectivityManager = (ConnectivityManager) zza().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
