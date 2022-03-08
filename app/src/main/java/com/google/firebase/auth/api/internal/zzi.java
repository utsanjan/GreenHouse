package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfj;
import com.google.android.gms.internal.firebase_auth.zzfs;
import com.google.android.gms.internal.firebase_auth.zzfv;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzi implements zzfp<zzfv> {
    private final /* synthetic */ zzfs zza;
    private final /* synthetic */ zzew zzb;
    private final /* synthetic */ zzeg zzc;
    private final /* synthetic */ zzff zzd;
    private final /* synthetic */ zzfq zze;
    private final /* synthetic */ zza zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(zza zzaVar, zzfs zzfsVar, zzew zzewVar, zzeg zzegVar, zzff zzffVar, zzfq zzfqVar) {
        this.zzf = zzaVar;
        this.zza = zzfsVar;
        this.zzb = zzewVar;
        this.zzc = zzegVar;
        this.zzd = zzffVar;
        this.zze = zzfqVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zze.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzfv zzfvVar) {
        zzff zza;
        zzfv zzfvVar2 = zzfvVar;
        if (this.zza.zza("EMAIL")) {
            this.zzb.zza((String) null);
        } else if (this.zza.zzb() != null) {
            this.zzb.zza(this.zza.zzb());
        }
        if (this.zza.zza("DISPLAY_NAME")) {
            this.zzb.zzb(null);
        } else if (this.zza.zzd() != null) {
            this.zzb.zzb(this.zza.zzd());
        }
        if (this.zza.zza("PHOTO_URL")) {
            this.zzb.zzc(null);
        } else if (this.zza.zze() != null) {
            this.zzb.zzc(this.zza.zze());
        }
        if (!TextUtils.isEmpty(this.zza.zzc())) {
            this.zzb.zzd(Base64Utils.encode("redacted".getBytes()));
        }
        List<zzfj> zzf = zzfvVar2.zzf();
        if (zzf == null) {
            zzf = new ArrayList<>();
        }
        this.zzb.zza(zzf);
        zzeg zzegVar = this.zzc;
        zza zzaVar = this.zzf;
        zza = zza.zza(this.zzd, zzfvVar2);
        zzegVar.zza(zza, this.zzb);
    }
}
