package com.google.firebase.auth.api.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzeh;
import com.google.android.gms.internal.firebase_auth.zzen;
import com.google.android.gms.internal.firebase_auth.zzer;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzev;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzfa;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfn;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzfs;
import com.google.android.gms.internal.firebase_auth.zzfu;
import com.google.android.gms.internal.firebase_auth.zzfv;
import com.google.android.gms.internal.firebase_auth.zzfy;
import com.google.android.gms.internal.firebase_auth.zzga;
import com.google.android.gms.internal.firebase_auth.zzgd;
import com.google.android.gms.internal.firebase_auth.zzge;
import com.google.android.gms.internal.firebase_auth.zzgg;
import com.google.android.gms.internal.firebase_auth.zzgj;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzaa;
import com.google.firebase.auth.zzc;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zza {
    private static final Logger zza = new Logger("FBAuthApiDispatcher", new String[0]);
    private final zzfn zzb;
    private final zzar zzc;

    public zza(zzfn zzfnVar, zzar zzarVar) {
        this.zzb = (zzfn) Preconditions.checkNotNull(zzfnVar);
        this.zzc = (zzar) Preconditions.checkNotNull(zzarVar);
    }

    public final void zza(String str, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzes(str), new zzc(this, zzegVar));
    }

    public final void zza(zzgd zzgdVar, zzeg zzegVar) {
        Preconditions.checkNotNull(zzgdVar);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(zzgdVar, new zzl(this, zzegVar));
    }

    public final void zza(Context context, zzfy zzfyVar, zzeg zzegVar) {
        Preconditions.checkNotNull(zzfyVar);
        Preconditions.checkNotNull(zzegVar);
        if (this.zzc.zza()) {
            zzfyVar.zzc(true);
        }
        this.zzb.zza((Context) null, zzfyVar, new zzx(this, zzegVar));
    }

    public final void zzb(String str, zzeg zzegVar) {
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzfu(str), new zzag(this, zzegVar));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzaf(this, userProfileChangeRequest, zzegVar));
    }

    public final void zza(String str, String str2, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzai(this, str2, zzegVar));
    }

    public final void zzb(String str, String str2, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzah(this, str2, zzegVar));
    }

    public final void zzc(String str, String str2, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        zzfs zzfsVar = new zzfs();
        zzfsVar.zzh(str);
        zzfsVar.zzi(str2);
        this.zzb.zza(zzfsVar, new zzak(this, zzegVar));
    }

    private final void zza(String str, zzfp<zzff> zzfpVar) {
        Preconditions.checkNotNull(zzfpVar);
        Preconditions.checkNotEmpty(str);
        zzff zzb = zzff.zzb(str);
        if (zzb.zzb()) {
            zzfpVar.zza((zzfp<zzff>) zzb);
            return;
        }
        this.zzb.zza(new zzes(zzb.zzc()), new zzaj(this, zzfpVar));
    }

    public final void zza(String str, String str2, String str3, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzfu(str, str2, null, str3), new zzb(this, zzegVar));
    }

    public final void zza(Context context, String str, String str2, String str3, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza((Context) null, new zzge(str, str2, str3), new zze(this, zzegVar));
    }

    public final void zza(EmailAuthCredential emailAuthCredential, zzeg zzegVar) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzegVar);
        if (emailAuthCredential.zzf()) {
            zza(emailAuthCredential.zze(), new zzd(this, emailAuthCredential, zzegVar));
        } else {
            zza(new zzer(emailAuthCredential, null), zzegVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzer zzerVar, zzeg zzegVar) {
        Preconditions.checkNotNull(zzerVar);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(zzerVar, new zzg(this, zzegVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzeg zzegVar, zzff zzffVar, zzfs zzfsVar, zzfq zzfqVar) {
        Preconditions.checkNotNull(zzegVar);
        Preconditions.checkNotNull(zzffVar);
        Preconditions.checkNotNull(zzfsVar);
        Preconditions.checkNotNull(zzfqVar);
        this.zzb.zza(new zzev(zzffVar.zzd()), new zzf(this, zzfqVar, zzegVar, zzffVar, zzfsVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzeg zzegVar, zzff zzffVar, zzew zzewVar, zzfs zzfsVar, zzfq zzfqVar) {
        Preconditions.checkNotNull(zzegVar);
        Preconditions.checkNotNull(zzffVar);
        Preconditions.checkNotNull(zzewVar);
        Preconditions.checkNotNull(zzfsVar);
        Preconditions.checkNotNull(zzfqVar);
        this.zzb.zza(zzfsVar, new zzi(this, zzfsVar, zzewVar, zzegVar, zzffVar, zzfqVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzff zza(zzff zzffVar, zzfv zzfvVar) {
        Preconditions.checkNotNull(zzffVar);
        Preconditions.checkNotNull(zzfvVar);
        String zzb = zzfvVar.zzb();
        String zzc = zzfvVar.zzc();
        if (TextUtils.isEmpty(zzb) || TextUtils.isEmpty(zzc)) {
            return zzffVar;
        }
        return new zzff(zzc, zzb, Long.valueOf(zzfvVar.zzd()), zzffVar.zzf());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzff zzffVar, String str, String str2, Boolean bool, zzc zzcVar, zzeg zzegVar, zzfq zzfqVar) {
        Preconditions.checkNotNull(zzffVar);
        Preconditions.checkNotNull(zzfqVar);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzev(zzffVar.zzd()), new zzh(this, zzfqVar, str2, str, bool, zzcVar, zzegVar, zzffVar));
    }

    public final void zzd(String str, String str2, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzen(str, str2), new zzk(this, zzegVar));
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, String str2, zzeg zzegVar) {
        zzfa zzfaVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        zzgj zza2 = zzgj.zza(actionCodeSettings.zzd());
        if (zza2 != null) {
            zzfaVar = new zzfa(zza2);
        } else {
            zzfaVar = new zzfa(zzgj.OOB_REQ_TYPE_UNSPECIFIED);
        }
        zzfaVar.zza(str);
        zzfaVar.zza(actionCodeSettings);
        zzfaVar.zzc(str2);
        this.zzb.zza(zzfaVar, new zzj(this, zzegVar));
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        zzfa zzfaVar = new zzfa(zzgj.VERIFY_EMAIL);
        zzfaVar.zzb(str);
        if (actionCodeSettings != null) {
            zzfaVar.zza(actionCodeSettings);
        }
        zzb(zzfaVar, zzegVar);
    }

    public final void zze(String str, String str2, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzfn(str, null, str2), new zzm(this, zzegVar));
    }

    public final void zzb(String str, String str2, String str3, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(new zzfn(str, str2, str3), new zzo(this, zzegVar));
    }

    public final void zza(zzfr zzfrVar, zzeg zzegVar) {
        Preconditions.checkNotEmpty(zzfrVar.zzb());
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(zzfrVar, new zzn(this, zzegVar));
    }

    public final void zza(Context context, zzgg zzggVar, zzeg zzegVar) {
        Preconditions.checkNotNull(zzggVar);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza((Context) null, zzggVar, new zzq(this, zzegVar));
    }

    public final void zzc(String str, String str2, String str3, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzegVar);
        zza(str3, new zzp(this, str, str2, zzegVar));
    }

    public final void zza(Context context, String str, zzgg zzggVar, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzggVar);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzs(this, zzggVar, null, zzegVar));
    }

    public final void zza(String str, zzfy zzfyVar, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfyVar);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzu(this, zzfyVar, zzegVar));
    }

    public final void zzc(String str, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzw(this, zzegVar));
    }

    public final void zzf(String str, String str2, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzegVar);
        zza(str2, new zzv(this, str, zzegVar));
    }

    public final void zza(zzfa zzfaVar, zzeg zzegVar) {
        zzb(zzfaVar, zzegVar);
    }

    public final void zzd(String str, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzaa(this, zzegVar));
    }

    public final void zze(String str, zzeg zzegVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzegVar);
        zza(str, new zzac(this, zzegVar));
    }

    public final void zzf(String str, zzeg zzegVar) {
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(str, new zzae(this, zzegVar));
    }

    private final void zzb(zzfa zzfaVar, zzeg zzegVar) {
        Preconditions.checkNotNull(zzfaVar);
        Preconditions.checkNotNull(zzegVar);
        this.zzb.zza(zzfaVar, new zzad(this, zzegVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzga zzgaVar, zzeg zzegVar, zzfq zzfqVar) {
        Status status;
        if (zzgaVar.zzk()) {
            zzc zzp = zzgaVar.zzp();
            String zzd = zzgaVar.zzd();
            String zzl = zzgaVar.zzl();
            if (zzgaVar.zzb()) {
                status = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                status = zzaa.zza(zzgaVar.zzj());
            }
            if (this.zzc.zza()) {
                zzegVar.zza(new zzeh(status, zzp, zzd, zzl));
            } else {
                zzegVar.zza(status);
            }
        } else {
            zza(new zzff(zzgaVar.zzg(), zzgaVar.zzc(), Long.valueOf(zzgaVar.zzh()), "Bearer"), zzgaVar.zzf(), zzgaVar.zze(), Boolean.valueOf(zzgaVar.zzi()), zzgaVar.zzp(), zzegVar, zzfqVar);
        }
    }
}
