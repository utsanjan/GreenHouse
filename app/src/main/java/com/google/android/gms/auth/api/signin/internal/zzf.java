package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class zzf extends AsyncTaskLoader<Void> implements SignInConnectionListener {
    private Semaphore zzcb = new Semaphore(0);
    private Set<GoogleApiClient> zzcc;

    public zzf(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzcc = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzi */
    public final Void loadInBackground() {
        int i = 0;
        for (GoogleApiClient googleApiClient : this.zzcc) {
            if (googleApiClient.maybeSignIn(this)) {
                i++;
            }
        }
        try {
            this.zzcb.tryAcquire(i, 5L, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    @Override // androidx.loader.content.Loader
    protected final void onStartLoading() {
        this.zzcb.drainPermits();
        forceLoad();
    }

    @Override // com.google.android.gms.common.api.internal.SignInConnectionListener
    public final void onComplete() {
        this.zzcb.release();
    }
}
