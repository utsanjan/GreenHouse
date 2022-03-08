package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.base.zar;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public abstract class zak extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected final GoogleApiAvailability zace;
    protected volatile boolean zadh;
    protected final AtomicReference<zam> zadi;
    private final Handler zadj;

    /* JADX INFO: Access modifiers changed from: protected */
    public zak(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zaa(ConnectionResult connectionResult, int i);

    protected abstract void zam();

    private zak(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zadi = new AtomicReference<>(null);
        this.zadj = new zar(Looper.getMainLooper());
        this.zace = googleApiAvailability;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, null), zaa(this.zadi.get()));
        zao();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onCreate(Bundle bundle) {
        zam zamVar;
        super.onCreate(bundle);
        if (bundle != null) {
            AtomicReference<zam> atomicReference = this.zadi;
            if (bundle.getBoolean("resolving_error", false)) {
                zamVar = new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            } else {
                zamVar = null;
            }
            atomicReference.set(zamVar);
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zamVar = this.zadi.get();
        if (zamVar != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zamVar.zap());
            bundle.putInt("failed_status", zamVar.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zamVar.getConnectionResult().getResolution());
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.zadh = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onActivityResult(int r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r0 = r4.zadi
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zam r0 = (com.google.android.gms.common.api.internal.zam) r0
            r1 = 1
            r2 = 0
            if (r5 == r1) goto L_0x0031
            r6 = 2
            if (r5 == r6) goto L_0x0011
            goto L_0x0062
        L_0x0011:
            com.google.android.gms.common.GoogleApiAvailability r5 = r4.zace
            android.app.Activity r6 = r4.getActivity()
            int r5 = r5.isGooglePlayServicesAvailable(r6)
            if (r5 != 0) goto L_0x001e
            goto L_0x001f
        L_0x001e:
            r1 = 0
        L_0x001f:
            if (r0 != 0) goto L_0x0022
            return
        L_0x0022:
            com.google.android.gms.common.ConnectionResult r6 = r0.getConnectionResult()
            int r6 = r6.getErrorCode()
            r7 = 18
            if (r6 != r7) goto L_0x0063
            if (r5 != r7) goto L_0x0063
            return
        L_0x0031:
            r5 = -1
            if (r6 != r5) goto L_0x0035
            goto L_0x0063
        L_0x0035:
            if (r6 != 0) goto L_0x0062
            r5 = 13
            if (r7 == 0) goto L_0x0043
            java.lang.String r6 = "<<ResolutionFailureErrorDetail>>"
            int r5 = r7.getIntExtra(r6, r5)
        L_0x0043:
            com.google.android.gms.common.api.internal.zam r6 = new com.google.android.gms.common.api.internal.zam
            com.google.android.gms.common.ConnectionResult r7 = new com.google.android.gms.common.ConnectionResult
            r1 = 0
            com.google.android.gms.common.ConnectionResult r3 = r0.getConnectionResult()
            java.lang.String r3 = r3.toString()
            r7.<init>(r5, r1, r3)
            int r5 = zaa(r0)
            r6.<init>(r7, r5)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r5 = r4.zadi
            r5.set(r6)
            r0 = r6
            r1 = 0
            goto L_0x0063
        L_0x0062:
            r1 = 0
        L_0x0063:
            if (r1 == 0) goto L_0x0069
            r4.zao()
            return
        L_0x0069:
            if (r0 == 0) goto L_0x0077
            com.google.android.gms.common.ConnectionResult r5 = r0.getConnectionResult()
            int r6 = r0.zap()
            r4.zaa(r5, r6)
        L_0x0077:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zak.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zadh = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zao() {
        this.zadi.set(null);
        zam();
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zamVar = new zam(connectionResult, i);
        if (this.zadi.compareAndSet(null, zamVar)) {
            this.zadj.post(new zal(this, zamVar));
        }
    }

    private static int zaa(zam zamVar) {
        if (zamVar == null) {
            return -1;
        }
        return zamVar.zap();
    }
}
