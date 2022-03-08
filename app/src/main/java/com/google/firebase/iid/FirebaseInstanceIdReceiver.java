package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    private final ExecutorService zza = zzh.zzb();

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        zzb zzbVar;
        if (intent != null) {
            Parcelable parcelableExtra = intent.getParcelableExtra("wrapped_intent");
            Intent intent2 = parcelableExtra instanceof Intent ? (Intent) parcelableExtra : null;
            if (intent2 != null) {
                intent = intent2;
            }
            intent.setComponent(null);
            intent.setPackage(context.getPackageName());
            if (Build.VERSION.SDK_INT <= 18) {
                intent.removeCategory(context.getPackageName());
            }
            if ("google.com/iid".equals(intent.getStringExtra("from"))) {
                zzbVar = new zzz(this.zza);
            } else {
                zzbVar = new zza(context, this.zza);
            }
            final boolean isOrderedBroadcast = isOrderedBroadcast();
            final BroadcastReceiver.PendingResult goAsync = goAsync();
            zzbVar.zza(intent).addOnCompleteListener(this.zza, new OnCompleteListener(isOrderedBroadcast, goAsync) { // from class: com.google.firebase.iid.zzr
                private final boolean zza;
                private final BroadcastReceiver.PendingResult zzb;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zza = isOrderedBroadcast;
                    this.zzb = goAsync;
                }

                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FirebaseInstanceIdReceiver.zza(this.zza, this.zzb, task);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ void zza(boolean z, BroadcastReceiver.PendingResult pendingResult, Task task) {
        int i;
        if (z) {
            if (task.isSuccessful()) {
                i = ((Integer) task.getResult()).intValue();
            } else {
                i = 500;
            }
            pendingResult.setResultCode(i);
        }
        pendingResult.finish();
    }
}
