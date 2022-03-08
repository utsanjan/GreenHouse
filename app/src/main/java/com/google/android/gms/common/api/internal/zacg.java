package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zak;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
final class zacg implements Runnable {
    private final /* synthetic */ zak zagu;
    private final /* synthetic */ zace zakl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacg(zace zaceVar, zak zakVar) {
        this.zakl = zaceVar;
        this.zagu = zakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakl.zac(this.zagu);
    }
}
