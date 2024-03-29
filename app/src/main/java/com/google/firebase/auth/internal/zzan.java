package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.internal.zzeh;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzan extends BroadcastReceiver {
    private final WeakReference<Activity> zza;
    private final TaskCompletionSource<AuthResult> zzb;
    private final FirebaseAuth zzc;
    private final FirebaseUser zzd;
    private final /* synthetic */ zzaf zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzan(zzaf zzafVar, Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zze = zzafVar;
        this.zza = new WeakReference<>(activity);
        this.zzb = taskCompletionSource;
        this.zzc = firebaseAuth;
        this.zzd = firebaseUser;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Activity activity = this.zza.get();
        if (activity == null) {
            Log.e("FederatedAuthReceiver", "Failed to unregister BroadcastReceiver because the Activity that launched this flow has been garbage collected; please do not finish() your Activity while performing a FederatedAuthProvider operation.");
            this.zzb.setException(zzeh.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR, "Activity that started the web operation is no longer alive; see logcat for details")));
            zzaf.zzb();
            return;
        }
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(this);
        if (intent.hasExtra("com.google.firebase.auth.internal.OPERATION")) {
            String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
            if ("com.google.firebase.auth.internal.SIGN_IN".equals(stringExtra)) {
                this.zze.zza(intent, this.zzb, this.zzc);
            } else if ("com.google.firebase.auth.internal.LINK".equals(stringExtra)) {
                this.zze.zza(intent, this.zzb, this.zzd);
            } else if ("com.google.firebase.auth.internal.REAUTHENTICATE".equals(stringExtra)) {
                this.zze.zzb(intent, this.zzb, this.zzd);
            } else {
                TaskCompletionSource<AuthResult> taskCompletionSource = this.zzb;
                StringBuilder sb = new StringBuilder(String.valueOf(stringExtra).length() + 50);
                sb.append("WEB_CONTEXT_CANCELED:Unknown operation received (");
                sb.append(stringExtra);
                sb.append(")");
                taskCompletionSource.setException(zzeh.zza(zzaa.zza(sb.toString())));
            }
        } else if (zzbc.zza(intent)) {
            this.zzb.setException(zzeh.zza(zzbc.zzb(intent)));
            zzaf.zzb();
        } else if (intent.hasExtra("com.google.firebase.auth.internal.EXTRA_CANCELED")) {
            this.zzb.setException(zzeh.zza(zzaa.zza("WEB_CONTEXT_CANCELED")));
            zzaf.zzb();
        }
    }
}
