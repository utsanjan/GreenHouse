package com.google.firebase.iid;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final /* synthetic */ class zze implements Continuation {
    static final Continuation zza = new zze();

    private zze() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return zza.zzb(task);
    }
}
