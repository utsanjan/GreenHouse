package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zad;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public final class zal extends zab implements zam {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    @Override // com.google.android.gms.common.internal.service.zam
    public final void zaa(zak zakVar) throws RemoteException {
        Parcel zaa = zaa();
        zad.zaa(zaa, zakVar);
        zac(1, zaa);
    }
}
