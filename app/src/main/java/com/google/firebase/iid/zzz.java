package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
final class zzz implements zzb {
    private final ExecutorService zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(ExecutorService executorService) {
        this.zza = executorService;
    }

    @Override // com.google.firebase.iid.zzb
    public final Task<Integer> zza(final Intent intent) {
        return Tasks.call(this.zza, new Callable(intent) { // from class: com.google.firebase.iid.zzy
            private final Intent zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = intent;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                Intent intent2 = this.zza;
                String stringExtra = intent2.getStringExtra("CMD");
                if (stringExtra != null) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        String valueOf = String.valueOf(intent2.getExtras());
                        StringBuilder sb = new StringBuilder(String.valueOf(stringExtra).length() + 21 + String.valueOf(valueOf).length());
                        sb.append("Received command: ");
                        sb.append(stringExtra);
                        sb.append(" - ");
                        sb.append(valueOf);
                        Log.d("FirebaseInstanceId", sb.toString());
                    }
                    if ("RST".equals(stringExtra) || "RST_FULL".equals(stringExtra)) {
                        FirebaseInstanceId.getInstance().zze();
                    } else if ("SYNC".equals(stringExtra)) {
                        FirebaseInstanceId.getInstance().zzg();
                    }
                }
                return -1;
            }
        });
    }
}
