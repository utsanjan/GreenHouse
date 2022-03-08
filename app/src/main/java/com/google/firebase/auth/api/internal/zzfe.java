package com.google.firebase.auth.api.internal;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfm;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzfe<ResultT, CallbackT> implements zzap<zzef, ResultT> {
    private boolean zza;
    protected final int zzb;
    protected FirebaseApp zzd;
    protected FirebaseUser zze;
    protected CallbackT zzf;
    protected zzag zzg;
    protected zzff<ResultT> zzh;
    protected Executor zzj;
    protected zzff zzk;
    protected zzew zzl;
    protected zzem zzm;
    protected zzfm zzn;
    protected String zzo;
    protected String zzp;
    protected AuthCredential zzq;
    protected String zzr;
    protected String zzs;
    protected zzej zzt;
    protected boolean zzu;
    protected boolean zzv;
    boolean zzw;
    private ResultT zzx;
    private Status zzy;
    final zzfg zzc = new zzfg(this);
    protected final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zzi = new ArrayList();

    public zzfe(int i) {
        this.zzb = i;
    }

    public abstract void zze();

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    static class zza extends LifecycleCallback {
        private final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zza;

        public static void zza(Activity activity, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            LifecycleFragment fragment = getFragment(activity);
            if (((zza) fragment.getCallbackOrNull("PhoneAuthActivityStopCallback", zza.class)) == null) {
                new zza(fragment, list);
            }
        }

        private zza(LifecycleFragment lifecycleFragment, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("PhoneAuthActivityStopCallback", this);
            this.zza = list;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            synchronized (this.zza) {
                this.zza.clear();
            }
        }
    }

    public final zzfe<ResultT, CallbackT> zza(FirebaseApp firebaseApp) {
        this.zzd = (FirebaseApp) Preconditions.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzfe<ResultT, CallbackT> zza(FirebaseUser firebaseUser) {
        this.zze = (FirebaseUser) Preconditions.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }

    public final zzfe<ResultT, CallbackT> zza(CallbackT callbackt) {
        this.zzf = (CallbackT) Preconditions.checkNotNull(callbackt, "external callback cannot be null");
        return this;
    }

    public final zzfe<ResultT, CallbackT> zza(zzag zzagVar) {
        this.zzg = (zzag) Preconditions.checkNotNull(zzagVar, "external failure callback cannot be null");
        return this;
    }

    public final zzfe<ResultT, CallbackT> zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        synchronized (this.zzi) {
            this.zzi.add((PhoneAuthProvider.OnVerificationStateChangedCallbacks) Preconditions.checkNotNull(onVerificationStateChangedCallbacks));
        }
        if (activity != null) {
            zza.zza(activity, this.zzi);
        }
        this.zzj = (Executor) Preconditions.checkNotNull(executor);
        return this;
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final zzap<zzef, ResultT> zzc() {
        this.zzu = true;
        return this;
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final zzap<zzef, ResultT> zzd() {
        this.zzv = true;
        return this;
    }

    public final void zzb(ResultT resultt) {
        this.zza = true;
        this.zzw = true;
        this.zzx = resultt;
        this.zzh.zza(resultt, null);
    }

    public final void zza(Status status) {
        this.zza = true;
        this.zzw = false;
        this.zzy = status;
        this.zzh.zza(null, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf() {
        zze();
        Preconditions.checkState(this.zza, "no success or failure set on method implementation");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(Status status) {
        zzag zzagVar = this.zzg;
        if (zzagVar != null) {
            zzagVar.zza(status);
        }
    }
}
