package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public final class zaak implements zabb {
    private final Context mContext;
    private final Api.AbstractClientBuilder<? extends zac, SignInOptions> zacf;
    private final Lock zaer;
    private final Map<Api<?>, Boolean> zaew;
    private final GoogleApiAvailabilityLight zaey;
    private final ClientSettings zafa;
    private ConnectionResult zafi;
    private final zabe zafv;
    private int zaga;
    private int zagc;
    private zac zagf;
    private boolean zagg;
    private boolean zagh;
    private boolean zagi;
    private IAccountAccessor zagj;
    private boolean zagk;
    private boolean zagl;
    private int zagb = 0;
    private final Bundle zagd = new Bundle();
    private final Set<Api.AnyClientKey> zage = new HashSet();
    private ArrayList<Future<?>> zagm = new ArrayList<>();

    public zaak(zabe zabeVar, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, Api.AbstractClientBuilder<? extends zac, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        this.zafv = zabeVar;
        this.zafa = clientSettings;
        this.zaew = map;
        this.zaey = googleApiAvailabilityLight;
        this.zacf = abstractClientBuilder;
        this.zaer = lock;
        this.mContext = context;
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void begin() {
        this.zafv.zaht.clear();
        this.zagh = false;
        this.zafi = null;
        this.zagb = 0;
        this.zagg = true;
        this.zagi = false;
        this.zagk = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api<?> api : this.zaew.keySet()) {
            Api.Client client = this.zafv.zahd.get(api.getClientKey());
            z |= api.zah().getPriority() == 1;
            boolean booleanValue = this.zaew.get(api).booleanValue();
            if (client.requiresSignIn()) {
                this.zagh = true;
                if (booleanValue) {
                    this.zage.add(api.getClientKey());
                } else {
                    this.zagg = false;
                }
            }
            hashMap.put(client, new zaam(this, api, booleanValue));
        }
        if (z) {
            this.zagh = false;
        }
        if (this.zagh) {
            this.zafa.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zafv.zaeh)));
            zaar zaarVar = new zaar(this, null);
            Api.AbstractClientBuilder<? extends zac, SignInOptions> abstractClientBuilder = this.zacf;
            Context context = this.mContext;
            Looper looper = this.zafv.zaeh.getLooper();
            ClientSettings clientSettings = this.zafa;
            this.zagf = (zac) abstractClientBuilder.buildClient(context, looper, clientSettings, (ClientSettings) clientSettings.getSignInOptions(), (GoogleApiClient.ConnectionCallbacks) zaarVar, (GoogleApiClient.OnConnectionFailedListener) zaarVar);
        }
        this.zagc = this.zafv.zahd.size();
        this.zagm.add(zabf.zaaz().submit(new zaal(this, hashMap)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaam() {
        int i = this.zagc - 1;
        this.zagc = i;
        if (i > 0) {
            return false;
        }
        if (i < 0) {
            Log.w("GACConnecting", this.zafv.zaeh.zaaw());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zae(new ConnectionResult(8, null));
            return false;
        } else if (this.zafi == null) {
            return true;
        } else {
            this.zafv.zahw = this.zaga;
            zae(this.zafi);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaa(zak zakVar) {
        if (zac(0)) {
            ConnectionResult connectionResult = zakVar.getConnectionResult();
            if (connectionResult.isSuccess()) {
                ResolveAccountResponse zacv = zakVar.zacv();
                ConnectionResult connectionResult2 = zacv.getConnectionResult();
                if (!connectionResult2.isSuccess()) {
                    String valueOf = String.valueOf(connectionResult2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(valueOf);
                    Log.wtf("GACConnecting", sb.toString(), new Exception());
                    zae(connectionResult2);
                    return;
                }
                this.zagi = true;
                this.zagj = zacv.getAccountAccessor();
                this.zagk = zacv.getSaveDefaultAccount();
                this.zagl = zacv.isFromCrossClientAuth();
                zaan();
            } else if (zad(connectionResult)) {
                zaap();
                zaan();
            } else {
                zae(connectionResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaan() {
        if (this.zagc == 0) {
            if (!this.zagh || this.zagi) {
                ArrayList arrayList = new ArrayList();
                this.zagb = 1;
                this.zagc = this.zafv.zahd.size();
                for (Api.AnyClientKey<?> anyClientKey : this.zafv.zahd.keySet()) {
                    if (!this.zafv.zaht.containsKey(anyClientKey)) {
                        arrayList.add(this.zafv.zahd.get(anyClientKey));
                    } else if (zaam()) {
                        zaao();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zagm.add(zabf.zaaz().submit(new zaaq(this, arrayList)));
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void onConnected(Bundle bundle) {
        if (zac(1)) {
            if (bundle != null) {
                this.zagd.putAll(bundle);
            }
            if (zaam()) {
                zaao();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zac(1)) {
            zab(connectionResult, api, z);
            if (zaam()) {
                zaao();
            }
        }
    }

    private final void zaao() {
        this.zafv.zaay();
        zabf.zaaz().execute(new zaaj(this));
        zac zacVar = this.zagf;
        if (zacVar != null) {
            if (this.zagk) {
                zacVar.zaa(this.zagj, this.zagl);
            }
            zab(false);
        }
        for (Api.AnyClientKey<?> anyClientKey : this.zafv.zaht.keySet()) {
            this.zafv.zahd.get(anyClientKey).disconnect();
        }
        this.zafv.zahx.zab(this.zagd.isEmpty() ? null : this.zagd);
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        this.zafv.zaeh.zafd.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void connect() {
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final boolean disconnect() {
        zaaq();
        zab(true);
        this.zafv.zaf(null);
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void onConnectionSuspended(int i) {
        zae(new ConnectionResult(8, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r7 != false) goto L_0x0026;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zab(com.google.android.gms.common.ConnectionResult r5, com.google.android.gms.common.api.Api<?> r6, boolean r7) {
        /*
            r4 = this;
            com.google.android.gms.common.api.Api$BaseClientBuilder r0 = r6.zah()
            int r0 = r0.getPriority()
            r1 = 0
            r2 = 1
            if (r7 == 0) goto L_0x0026
            boolean r7 = r5.hasResolution()
            if (r7 == 0) goto L_0x0015
            r7 = 1
            goto L_0x0024
        L_0x0015:
            com.google.android.gms.common.GoogleApiAvailabilityLight r7 = r4.zaey
            int r3 = r5.getErrorCode()
            android.content.Intent r7 = r7.getErrorResolutionIntent(r3)
            if (r7 == 0) goto L_0x0023
            r7 = 1
            goto L_0x0024
        L_0x0023:
            r7 = 0
        L_0x0024:
            if (r7 == 0) goto L_0x002f
        L_0x0026:
            com.google.android.gms.common.ConnectionResult r7 = r4.zafi
            if (r7 == 0) goto L_0x0030
            int r7 = r4.zaga
            if (r0 >= r7) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            goto L_0x0031
        L_0x0030:
            r1 = 1
        L_0x0031:
            if (r1 == 0) goto L_0x0037
            r4.zafi = r5
            r4.zaga = r0
        L_0x0037:
            com.google.android.gms.common.api.internal.zabe r7 = r4.zafv
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.ConnectionResult> r7 = r7.zaht
            com.google.android.gms.common.api.Api$AnyClientKey r6 = r6.getClientKey()
            r7.put(r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaak.zab(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaap() {
        this.zagh = false;
        this.zafv.zaeh.zahe = Collections.emptySet();
        for (Api.AnyClientKey<?> anyClientKey : this.zage) {
            if (!this.zafv.zaht.containsKey(anyClientKey)) {
                this.zafv.zaht.put(anyClientKey, new ConnectionResult(17, null));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zad(ConnectionResult connectionResult) {
        return this.zagg && !connectionResult.hasResolution();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zae(ConnectionResult connectionResult) {
        zaaq();
        zab(!connectionResult.hasResolution());
        this.zafv.zaf(connectionResult);
        this.zafv.zahx.zac(connectionResult);
    }

    private final void zab(boolean z) {
        zac zacVar = this.zagf;
        if (zacVar != null) {
            if (zacVar.isConnected() && z) {
                this.zagf.zacu();
            }
            this.zagf.disconnect();
            if (this.zafa.isSignInClientDisconnectFixEnabled()) {
                this.zagf = null;
            }
            this.zagj = null;
        }
    }

    private final void zaaq() {
        ArrayList<Future<?>> arrayList = this.zagm;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Future<?> future = arrayList.get(i);
            i++;
            future.cancel(true);
        }
        this.zagm.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<Scope> zaar() {
        if (this.zafa == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zafa.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zafa.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            if (!this.zafv.zaht.containsKey(api.getClientKey())) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zac(int i) {
        if (this.zagb == i) {
            return true;
        }
        Log.w("GACConnecting", this.zafv.zaeh.zaaw());
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(valueOf);
        Log.w("GACConnecting", sb.toString());
        int i2 = this.zagc;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i2);
        Log.w("GACConnecting", sb2.toString());
        String zad = zad(this.zagb);
        String zad2 = zad(i);
        StringBuilder sb3 = new StringBuilder(String.valueOf(zad).length() + 70 + String.valueOf(zad2).length());
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(zad);
        sb3.append(" but received callback for step ");
        sb3.append(zad2);
        Log.e("GACConnecting", sb3.toString(), new Exception());
        zae(new ConnectionResult(8, null));
        return false;
    }

    private static String zad(int i) {
        if (i == 0) {
            return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
        }
        if (i != 1) {
            return "UNKNOWN";
        }
        return "STEP_GETTING_REMOTE_SERVICE";
    }
}
