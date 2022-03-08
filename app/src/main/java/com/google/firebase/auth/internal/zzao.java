package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.internal.firebase_auth.zzfy;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzao {
    private static final zzao zzc = new zzao();
    private final zzau zza;
    private final zzaf zzb;

    private zzao() {
        this(zzau.zza(), zzaf.zza());
    }

    private zzao(zzau zzauVar, zzaf zzafVar) {
        this.zza = zzauVar;
        this.zzb = zzafVar;
    }

    public static zzao zza() {
        return zzc;
    }

    public final Task<AuthResult> zzb() {
        return this.zza.zzb();
    }

    public final void zza(FirebaseAuth firebaseAuth) {
        this.zza.zza(firebaseAuth);
    }

    public static void zza(Context context, zzfy zzfyVar, String str, String str2) {
        zzau.zza(context, zzfyVar, str, str2);
    }

    public final void zza(Context context) {
        this.zza.zza(context);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth) {
        return this.zzb.zza(activity, taskCompletionSource, firebaseAuth);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        return this.zzb.zza(activity, taskCompletionSource, firebaseAuth, firebaseUser);
    }
}
