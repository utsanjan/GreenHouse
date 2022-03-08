package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.firebase_auth.zzeu;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfj;
import com.google.android.gms.internal.firebase_auth.zzfl;
import com.google.firebase.auth.zzc;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzh implements zzfp<zzeu> {
    private final /* synthetic */ zzfq zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ Boolean zzd;
    private final /* synthetic */ zzc zze;
    private final /* synthetic */ zzeg zzf;
    private final /* synthetic */ zzff zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(zza zzaVar, zzfq zzfqVar, String str, String str2, Boolean bool, zzc zzcVar, zzeg zzegVar, zzff zzffVar) {
        this.zza = zzfqVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zzcVar;
        this.zzf = zzegVar;
        this.zzg = zzffVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfq
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfp
    public final /* synthetic */ void zza(zzeu zzeuVar) {
        List<zzew> zzb = zzeuVar.zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        boolean z = false;
        zzew zzewVar = zzb.get(0);
        zzfl zzk = zzewVar.zzk();
        List<zzfj> zza = zzk != null ? zzk.zza() : null;
        if (zza != null && !zza.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzb)) {
                int i = 0;
                while (true) {
                    if (i >= zza.size()) {
                        break;
                    } else if (zza.get(i).zzd().equals(this.zzb)) {
                        zza.get(i).zza(this.zzc);
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                zza.get(0).zza(this.zzc);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            zzewVar.zza(bool.booleanValue());
        } else {
            if (zzewVar.zzh() - zzewVar.zzg() < 1000) {
                z = true;
            }
            zzewVar.zza(z);
        }
        zzewVar.zza(this.zze);
        this.zzf.zza(this.zzg, zzewVar);
    }
}
