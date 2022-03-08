package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.firebase_auth.zzfy;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzc;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzaf {
    private static zzaf zzb;
    private boolean zza = false;

    private zzaf() {
    }

    public static zzaf zza() {
        if (zzb == null) {
            zzb = new zzaf();
        }
        return zzb;
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth) {
        return zza(activity, taskCompletionSource, firebaseAuth, (FirebaseUser) null);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        if (this.zza) {
            return false;
        }
        LocalBroadcastManager.getInstance(activity).registerReceiver(new zzan(this, activity, taskCompletionSource, firebaseAuth, firebaseUser), new IntentFilter("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT"));
        this.zza = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Intent intent, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithCredential(zza(intent)).addOnSuccessListener(new zzah(this, taskCompletionSource)).addOnFailureListener(new zzai(this, taskCompletionSource));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Intent intent, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseUser firebaseUser) {
        firebaseUser.linkWithCredential(zza(intent)).addOnSuccessListener(new zzaj(this, taskCompletionSource)).addOnFailureListener(new zzak(this, taskCompletionSource));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(Intent intent, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseUser firebaseUser) {
        firebaseUser.reauthenticateAndRetrieveData(zza(intent)).addOnSuccessListener(new zzal(this, taskCompletionSource)).addOnFailureListener(new zzam(this, taskCompletionSource));
    }

    private static AuthCredential zza(Intent intent) {
        Preconditions.checkNotNull(intent);
        return zzc.zza(((zzfy) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST", zzfy.CREATOR)).zzb(true));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb() {
        zzb.zza = false;
    }
}
