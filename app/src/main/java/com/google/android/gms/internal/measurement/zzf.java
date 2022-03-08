package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzf extends zza implements zzd {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    @Override // com.google.android.gms.internal.measurement.zzd
    public final Bundle zza(Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, bundle);
        Parcel zza = zza(1, a_);
        Bundle bundle2 = (Bundle) zzb.zza(zza, Bundle.CREATOR);
        zza.recycle();
        return bundle2;
    }
}
