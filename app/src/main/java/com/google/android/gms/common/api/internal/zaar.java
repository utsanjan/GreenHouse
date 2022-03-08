package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.zac;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
final class zaar implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaak zafz;

    private zaar(zaak zaakVar) {
        this.zafz = zaakVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        ClientSettings clientSettings;
        zac zacVar;
        Lock lock;
        Lock lock2;
        zac zacVar2;
        zac zacVar3;
        clientSettings = this.zafz.zafa;
        if (clientSettings.isSignInClientDisconnectFixEnabled()) {
            lock = this.zafz.zaer;
            lock.lock();
            try {
                zacVar2 = this.zafz.zagf;
                if (zacVar2 != null) {
                    zacVar3 = this.zafz.zagf;
                    zacVar3.zaa(new zaap(this.zafz));
                }
            } finally {
                lock2 = this.zafz.zaer;
                lock2.unlock();
            }
        } else {
            zacVar = this.zafz.zagf;
            zacVar.zaa(new zaap(this.zafz));
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Lock lock;
        Lock lock2;
        boolean zad;
        lock = this.zafz.zaer;
        lock.lock();
        try {
            zad = this.zafz.zad(connectionResult);
            if (zad) {
                this.zafz.zaap();
                this.zafz.zaan();
            } else {
                this.zafz.zae(connectionResult);
            }
        } finally {
            lock2 = this.zafz.zaer;
            lock2.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zaar(zaak zaakVar, zaaj zaajVar) {
        this(zaakVar);
    }
}
