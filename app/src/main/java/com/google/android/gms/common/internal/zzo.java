package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzd;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public final class zzo extends zzb implements zzn {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzn
    public final boolean zza(zzj zzjVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzjVar);
        zzd.zza(zza, iObjectWrapper);
        Parcel zza2 = zza(5, zza);
        boolean zza3 = zzd.zza(zza2);
        zza2.recycle();
        return zza3;
    }
}
