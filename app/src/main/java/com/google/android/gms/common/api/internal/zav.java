package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zac;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public final class zav implements zabr {
    private final Looper zabl;
    private final GoogleApiManager zabo;
    private final Lock zaer;
    private final Map<Api<?>, Boolean> zaew;
    private final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    private final Condition zaez;
    private final ClientSettings zafa;
    private final boolean zafb;
    private final boolean zafc;
    private boolean zafe;
    private Map<ApiKey<?>, ConnectionResult> zaff;
    private Map<ApiKey<?>, ConnectionResult> zafg;
    private zaaa zafh;
    private ConnectionResult zafi;
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaev = new HashMap();
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafd = new LinkedList();

    public zav(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zac, SignInOptions> abstractClientBuilder, ArrayList<zap> arrayList, zaaw zaawVar, boolean z) {
        boolean z2;
        this.zaer = lock;
        this.zabl = looper;
        this.zaez = lock.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaawVar;
        this.zaew = map2;
        this.zafa = clientSettings;
        this.zafb = z;
        HashMap hashMap = new HashMap();
        for (Api<?> api : map2.keySet()) {
            hashMap.put(api.getClientKey(), api);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList<zap> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            zap zapVar = arrayList2.get(i);
            i++;
            zap zapVar2 = zapVar;
            hashMap2.put(zapVar2.mApi, zapVar2);
        }
        boolean z3 = false;
        boolean z4 = true;
        boolean z5 = false;
        for (Map.Entry<Api.AnyClientKey<?>, Api.Client> entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.Client value = entry.getValue();
            if (!value.requiresGooglePlayServices()) {
                z2 = z3;
                z5 = z5;
                z4 = false;
            } else if (!this.zaew.get(api2).booleanValue()) {
                z4 = z4;
                z2 = true;
                z5 = true;
            } else {
                z4 = z4;
                z5 = z5;
                z2 = true;
            }
            zaw<?> zawVar = new zaw<>(context, api2, looper, value, (zap) hashMap2.get(api2), clientSettings, abstractClientBuilder);
            this.zaeu.put(entry.getKey(), zawVar);
            if (value.requiresSignIn()) {
                this.zaev.put(entry.getKey(), zawVar);
            }
            z3 = z2;
        }
        this.zafc = z3 && !z4 && !z5;
        this.zabo = GoogleApiManager.zaba();
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        if (this.zafb && zab((zav) t)) {
            return t;
        }
        if (!isConnected()) {
            this.zafd.add(t);
            return t;
        }
        this.zaex.zahj.zac(t);
        return (T) this.zaeu.get(t.getClientKey()).doRead((zaw<?>) t);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        Api.AnyClientKey<A> clientKey = t.getClientKey();
        if (this.zafb && zab((zav) t)) {
            return t;
        }
        this.zaex.zahj.zac(t);
        return (T) this.zaeu.get(clientKey).doWrite((zaw<?>) t);
    }

    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(T t) {
        Api.AnyClientKey<?> clientKey = t.getClientKey();
        ConnectionResult zaa = zaa(clientKey);
        if (zaa == null || zaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, null, this.zabo.zaa(this.zaeu.get(clientKey).getApiKey(), System.identityHashCode(this.zaex))));
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void connect() {
        this.zaer.lock();
        try {
            if (!this.zafe) {
                this.zafe = true;
                this.zaff = null;
                this.zafg = null;
                this.zafh = null;
                this.zafi = null;
                this.zabo.zam();
                this.zabo.zaa(this.zaeu.values()).addOnCompleteListener(new HandlerExecutor(this.zabl), new zax(this, null));
            }
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafi;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zaez.awaitNanos(nanos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult = this.zafi;
        if (connectionResult != null) {
            return connectionResult;
        }
        return new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void disconnect() {
        this.zaer.lock();
        try {
            this.zafe = false;
            this.zaff = null;
            this.zafg = null;
            if (this.zafh != null) {
                this.zafh.cancel();
                this.zafh = null;
            }
            this.zafi = null;
            while (!this.zafd.isEmpty()) {
                BaseImplementation.ApiMethodImpl<?, ?> remove = this.zafd.remove();
                remove.zaa((zacq) null);
                remove.cancel();
            }
            this.zaez.signalAll();
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final ConnectionResult getConnectionResult(Api<?> api) {
        return zaa(api.getClientKey());
    }

    private final ConnectionResult zaa(Api.AnyClientKey<?> anyClientKey) {
        this.zaer.lock();
        try {
            zaw<?> zawVar = this.zaeu.get(anyClientKey);
            if (this.zaff != null && zawVar != null) {
                return this.zaff.get(zawVar.getApiKey());
            }
            this.zaer.unlock();
            return null;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean isConnected() {
        boolean z;
        this.zaer.lock();
        try {
            if (this.zaff != null) {
                if (this.zafi == null) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean isConnecting() {
        boolean z;
        this.zaer.lock();
        try {
            if (this.zaff == null) {
                if (this.zafe) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } finally {
            this.zaer.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f A[Catch: all -> 0x0049, TryCatch #0 {all -> 0x0049, blocks: (B:3:0x0005, B:5:0x000a, B:8:0x000f, B:9:0x0019, B:11:0x001f, B:13:0x002b), top: B:24:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zaz() {
        /*
            r3 = this;
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.lock()
            boolean r0 = r3.zafe     // Catch: all -> 0x0049
            r1 = 0
            if (r0 == 0) goto L_0x0042
            boolean r0 = r3.zafb     // Catch: all -> 0x0049
            if (r0 != 0) goto L_0x000f
            goto L_0x0042
        L_0x000f:
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.internal.zaw<?>> r0 = r3.zaev     // Catch: all -> 0x0049
            java.util.Set r0 = r0.keySet()     // Catch: all -> 0x0049
            java.util.Iterator r0 = r0.iterator()     // Catch: all -> 0x0049
        L_0x0019:
            boolean r2 = r0.hasNext()     // Catch: all -> 0x0049
            if (r2 == 0) goto L_0x003a
            java.lang.Object r2 = r0.next()     // Catch: all -> 0x0049
            com.google.android.gms.common.api.Api$AnyClientKey r2 = (com.google.android.gms.common.api.Api.AnyClientKey) r2     // Catch: all -> 0x0049
            com.google.android.gms.common.ConnectionResult r2 = r3.zaa(r2)     // Catch: all -> 0x0049
            if (r2 == 0) goto L_0x0033
            boolean r2 = r2.isSuccess()     // Catch: all -> 0x0049
            if (r2 != 0) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            goto L_0x0019
        L_0x0033:
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.unlock()
            return r1
        L_0x003a:
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.unlock()
            r0 = 1
            return r0
        L_0x0042:
            java.util.concurrent.locks.Lock r0 = r3.zaer
            r0.unlock()
            return r1
        L_0x0049:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r3.zaer
            r1.unlock()
            goto L_0x0051
        L_0x0050:
            throw r0
        L_0x0051:
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zav.zaz():boolean");
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.google.android.gms.common.api.internal.zabr
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaer.lock();
        try {
            if (!this.zafe || zaz()) {
                this.zaer.unlock();
                return false;
            }
            this.zabo.zam();
            this.zafh = new zaaa(this, signInConnectionListener);
            this.zabo.zaa(this.zaev.values()).addOnCompleteListener(new HandlerExecutor(this.zabl), this.zafh);
            this.zaer.unlock();
            return true;
        } catch (Throwable th) {
            this.zaer.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void maybeSignOut() {
        this.zaer.lock();
        try {
            this.zabo.maybeSignOut();
            if (this.zafh != null) {
                this.zafh.cancel();
                this.zafh = null;
            }
            if (this.zafg == null) {
                this.zafg = new ArrayMap(this.zaev.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zaw<?> zawVar : this.zaev.values()) {
                this.zafg.put(zawVar.getApiKey(), connectionResult);
            }
            if (this.zaff != null) {
                this.zaff.putAll(this.zafg);
            }
        } finally {
            this.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zau() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaaa() {
        if (this.zafa == null) {
            this.zaex.zahe = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zafa.getRequiredScopes());
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zafa.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(optionalApiSettings.get(api).mScopes);
            }
        }
        this.zaex.zahe = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zaab() {
        while (!this.zafd.isEmpty()) {
            execute(this.zafd.remove());
        }
        this.zaex.zab((Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(zaw<?> zawVar, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaew.get(zawVar.getApi()).booleanValue() && zawVar.zaad().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConnectionResult zaac() {
        int i = 0;
        ConnectionResult connectionResult = null;
        ConnectionResult connectionResult2 = null;
        int i2 = 0;
        for (zaw<?> zawVar : this.zaeu.values()) {
            Api<?> api = zawVar.getApi();
            ConnectionResult connectionResult3 = this.zaff.get(zawVar.getApiKey());
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api).booleanValue() || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zafb) {
                    int priority = api.zah().getPriority();
                    if (connectionResult == null || i > priority) {
                        connectionResult = connectionResult3;
                        i = priority;
                    }
                } else {
                    int priority2 = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority2) {
                        connectionResult2 = connectionResult3;
                        i2 = priority2;
                    }
                }
            }
        }
        if (connectionResult == null || connectionResult2 == null || i <= i2) {
            return connectionResult;
        }
        return connectionResult2;
    }
}
