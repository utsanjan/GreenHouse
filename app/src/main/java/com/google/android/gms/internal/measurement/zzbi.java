package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.measurement.internal.zzha;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.3 */
/* loaded from: classes.dex */
public final class zzbi extends zzag.zzb {
    private final /* synthetic */ zzha zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbi(zzag zzagVar, zzha zzhaVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zzhaVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    final void zza() throws RemoteException {
        List list;
        Pair pair;
        zzv zzvVar;
        List list2;
        String str;
        List list3;
        List list4;
        int i = 0;
        while (true) {
            list = this.zzd.zzf;
            if (i >= list.size()) {
                pair = null;
                break;
            }
            zzha zzhaVar = this.zzc;
            list3 = this.zzd.zzf;
            if (zzhaVar.equals(((Pair) list3.get(i)).first)) {
                list4 = this.zzd.zzf;
                pair = (Pair) list4.get(i);
                break;
            }
            i++;
        }
        if (pair == null) {
            str = this.zzd.zzc;
            Log.w(str, "OnEventListener had not been registered.");
            return;
        }
        zzvVar = this.zzd.zzm;
        zzvVar.unregisterOnMeasurementEventListener((zzab) pair.second);
        list2 = this.zzd.zzf;
        list2.remove(pair);
    }
}
