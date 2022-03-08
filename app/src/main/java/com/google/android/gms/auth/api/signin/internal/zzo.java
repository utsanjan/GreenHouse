package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class zzo {
    private static zzo zzci = null;
    private Storage zzcj;
    private GoogleSignInAccount zzck;
    private GoogleSignInOptions zzcl;

    private zzo(Context context) {
        Storage instance = Storage.getInstance(context);
        this.zzcj = instance;
        this.zzck = instance.getSavedDefaultGoogleSignInAccount();
        this.zzcl = this.zzcj.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized zzo zzd(Context context) {
        zzo zze;
        synchronized (zzo.class) {
            zze = zze(context.getApplicationContext());
        }
        return zze;
    }

    private static synchronized zzo zze(Context context) {
        synchronized (zzo.class) {
            if (zzci != null) {
                return zzci;
            }
            zzo zzoVar = new zzo(context);
            zzci = zzoVar;
            return zzoVar;
        }
    }

    public final synchronized void clear() {
        this.zzcj.clear();
        this.zzck = null;
        this.zzcl = null;
    }

    public final synchronized void zzc(GoogleSignInOptions googleSignInOptions, GoogleSignInAccount googleSignInAccount) {
        this.zzcj.saveDefaultGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
        this.zzck = googleSignInAccount;
        this.zzcl = googleSignInOptions;
    }

    public final synchronized GoogleSignInAccount zzk() {
        return this.zzck;
    }

    public final synchronized GoogleSignInOptions zzl() {
        return this.zzcl;
    }
}
