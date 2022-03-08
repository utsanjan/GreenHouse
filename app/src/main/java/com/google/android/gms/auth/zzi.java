package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzay;
import com.google.android.gms.internal.auth.zzf;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzi implements zzj<Boolean> {
    private final /* synthetic */ String zzt;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(String str) {
        this.zzt = str;
    }

    @Override // com.google.android.gms.auth.zzj
    public final /* synthetic */ Boolean zzb(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Object zza;
        Logger logger;
        zza = zzd.zza(zzf.zza(iBinder).zza(this.zzt));
        Bundle bundle = (Bundle) zza;
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzay zzc = zzay.zzc(string);
        if (zzay.SUCCESS.equals(zzc)) {
            return true;
        }
        if (zzay.zza(zzc)) {
            logger = zzd.zzn;
            String valueOf = String.valueOf(zzc);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
            sb.append("isUserRecoverableError status: ");
            sb.append(valueOf);
            logger.w("GoogleAuthUtil", sb.toString());
            throw new UserRecoverableAuthException(string, intent);
        }
        throw new GoogleAuthException(string);
    }
}
