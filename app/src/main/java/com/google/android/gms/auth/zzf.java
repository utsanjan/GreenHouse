package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzf implements zzj<Void> {
    private final /* synthetic */ Bundle val$extras;
    private final /* synthetic */ String zzq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(String str, Bundle bundle) {
        this.zzq = str;
        this.val$extras = bundle;
    }

    @Override // com.google.android.gms.auth.zzj
    public final /* synthetic */ Void zzb(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Object zza;
        zza = zzd.zza(com.google.android.gms.internal.auth.zzf.zza(iBinder).zza(this.zzq, this.val$extras));
        Bundle bundle = (Bundle) zza;
        String string = bundle.getString("Error");
        if (bundle.getBoolean("booleanResult")) {
            return null;
        }
        throw new GoogleAuthException(string);
    }
}
