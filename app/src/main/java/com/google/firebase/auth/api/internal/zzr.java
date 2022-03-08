package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzgi;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzr implements zzfp<zzgi> {
    private final /* synthetic */ zzfp zza;
    private final /* synthetic */ zzs zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzr(zzs zzsVar, zzfp zzfpVar) {
        this.zzb = zzsVar;
        this.zza = zzfpVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzgi zzgiVar) {
        zzgi zzgiVar2 = zzgiVar;
        if (!TextUtils.isEmpty(zzgiVar2.zzf())) {
            this.zzb.zza.zza(new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE), PhoneAuthCredential.zza(zzgiVar2.zzg(), zzgiVar2.zzf()));
            return;
        }
        this.zzb.zzb.zza(new zzff(zzgiVar2.zzc(), zzgiVar2.zzb(), Long.valueOf(zzgiVar2.zzd()), "Bearer"), null, "phone", Boolean.valueOf(zzgiVar2.zze()), null, this.zzb.zza, this.zza);
    }
}
