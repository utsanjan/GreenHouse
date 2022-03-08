package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzf;
import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzg implements zzj<List<AccountChangeEvent>> {
    private final /* synthetic */ String zzr;
    private final /* synthetic */ int zzs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(String str, int i) {
        this.zzr = str;
        this.zzs = i;
    }

    @Override // com.google.android.gms.auth.zzj
    public final /* synthetic */ List<AccountChangeEvent> zzb(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Object zza;
        zza = zzd.zza(zzf.zza(iBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzr).setEventIndex(this.zzs)));
        return ((AccountChangeEventsResponse) zza).getEvents();
    }
}
