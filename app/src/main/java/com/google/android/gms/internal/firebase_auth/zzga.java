package com.google.android.gms.internal.firebase_auth;

import android.text.TextUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;
import com.google.firebase.auth.zzc;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzga implements zzel<zzga, zzp.zzs> {
    private boolean zza;
    private boolean zzb;
    private String zzc;
    private String zzd;
    private long zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private boolean zzl;
    private String zzm;
    private String zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;
    private List<zzfh> zzs;
    private String zzt;

    public final boolean zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzj;
    }

    public final String zzf() {
        return this.zzk;
    }

    public final String zzg() {
        return this.zzd;
    }

    public final long zzh() {
        return this.zze;
    }

    public final boolean zzi() {
        return this.zzl;
    }

    public final String zzj() {
        return this.zzp;
    }

    public final boolean zzk() {
        return this.zza || !TextUtils.isEmpty(this.zzp);
    }

    public final String zzl() {
        return this.zzr;
    }

    public final List<zzfh> zzm() {
        return this.zzs;
    }

    public final String zzn() {
        return this.zzt;
    }

    public final boolean zzo() {
        return !TextUtils.isEmpty(this.zzt);
    }

    public final zzc zzp() {
        if (!TextUtils.isEmpty(this.zzm) || !TextUtils.isEmpty(this.zzn)) {
            return zzc.zza(this.zzj, this.zzn, this.zzm, this.zzq, this.zzo);
        }
        return null;
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzs> zza() {
        return zzp.zzs.zzu();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzga zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzs) {
            zzp.zzs zzsVar = (zzp.zzs) zzjnVar;
            this.zza = zzsVar.zzg();
            this.zzb = zzsVar.zzi();
            this.zzc = Strings.emptyToNull(zzsVar.zzf());
            this.zzd = Strings.emptyToNull(zzsVar.zzk());
            this.zze = zzsVar.zzl();
            this.zzf = Strings.emptyToNull(zzsVar.zzd());
            this.zzg = Strings.emptyToNull(zzsVar.zzb());
            this.zzh = Strings.emptyToNull(zzsVar.zze());
            this.zzi = Strings.emptyToNull(zzsVar.zzc());
            this.zzj = Strings.emptyToNull(zzsVar.zza());
            this.zzk = Strings.emptyToNull(zzsVar.zzn());
            this.zzl = zzsVar.zzp();
            this.zzm = zzsVar.e_();
            this.zzn = zzsVar.zzm();
            this.zzp = Strings.emptyToNull(zzsVar.zzo());
            this.zzq = Strings.emptyToNull(zzsVar.zzq());
            this.zzr = Strings.emptyToNull(zzsVar.zzr());
            this.zzs = new ArrayList();
            for (zzr zzrVar : zzsVar.zzt()) {
                this.zzs.add(zzfh.zza(zzrVar));
            }
            this.zzt = Strings.emptyToNull(zzsVar.zzs());
            this.zzo = Strings.emptyToNull(zzsVar.zzj());
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of VerifyAssertionResponse.");
    }
}
