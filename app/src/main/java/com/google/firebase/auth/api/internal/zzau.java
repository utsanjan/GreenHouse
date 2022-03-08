package com.google.firebase.auth.api.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzf;
import com.google.android.gms.internal.firebase_auth.zzfj;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzgj;
import com.google.android.gms.internal.firebase_auth.zzk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzad;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzat;
import com.google.firebase.auth.internal.zzba;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzn;
import com.google.firebase.auth.internal.zzp;
import com.google.firebase.auth.internal.zzy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzau extends zzam<zzeu> {
    private final Context zza;
    private final zzeu zzb;
    private final Future<zzal<zzeu>> zzc = zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzau(Context context, zzeu zzeuVar) {
        this.zza = context;
        this.zzb = zzeuVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzam
    final Future<zzal<zzeu>> zza() {
        Future<zzal<zzeu>> future = this.zzc;
        if (future != null) {
            return future;
        }
        return zzf.zza().zza(zzk.zza).submit(new zzed(this.zzb, this.zza));
    }

    public final Task<GetTokenResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzba zzbaVar) {
        zzbm zzbmVar = (zzbm) new zzbm(str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<GetTokenResult, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zza(zzbmVar), (zzap) zzbmVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, zza zzaVar) {
        zzcy zzcyVar = (zzcy) new zzcy(str, str2).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzcyVar), (zzap) zzcyVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, AuthCredential authCredential, String str, zza zzaVar) {
        zzcw zzcwVar = (zzcw) new zzcw(authCredential, str).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzcwVar), (zzap) zzcwVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzba zzbaVar) {
        zzbw zzbwVar = (zzbw) new zzbw(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzbwVar), (zzap) zzbwVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, String str, zzba zzbaVar) {
        zzby zzbyVar = (zzby) new zzby(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzbyVar), (zzap) zzbyVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, zza zzaVar, String str) {
        zzcu zzcuVar = (zzcu) new zzcu(str).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzcuVar), (zzap) zzcuVar);
    }

    public final void zza(FirebaseApp firebaseApp, zzfr zzfrVar, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        zzec zzecVar = (zzec) new zzec(zzfrVar).zza(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor);
        zza((Task) zzb(zzecVar), (zzap) zzecVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzba zzbaVar) {
        zzdw zzdwVar = (zzdw) new zzdw(userProfileChangeRequest).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzdwVar), (zzap) zzdwVar);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzba zzbaVar) {
        zzdq zzdqVar = (zzdq) new zzdq(str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzdqVar), (zzap) zzdqVar);
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzba zzbaVar) {
        zzds zzdsVar = (zzds) new zzds(str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzdsVar), (zzap) zzdsVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzba zzbaVar) {
        zzdu zzduVar = (zzdu) new zzdu(phoneAuthCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzduVar), (zzap) zzduVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, String str3, zza zzaVar) {
        zzbc zzbcVar = (zzbc) new zzbc(str, str2, str3).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzbcVar), (zzap) zzbcVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, String str, String str2, String str3, zza zzaVar) {
        zzda zzdaVar = (zzda) new zzda(str, str2, str3).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzdaVar), (zzap) zzdaVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, zza zzaVar) {
        zzdc zzdcVar = (zzdc) new zzdc(emailAuthCredential).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzdcVar), (zzap) zzdcVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzba zzbaVar) {
        zzce zzceVar = (zzce) new zzce(str, str2, str3).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzceVar), (zzap) zzceVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, zzba zzbaVar) {
        zzcg zzcgVar = (zzcg) new zzcg(str, str2, str3).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzcgVar), (zzap) zzcgVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzba zzbaVar) {
        zzca zzcaVar = (zzca) new zzca(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzcaVar), (zzap) zzcaVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, zzba zzbaVar) {
        zzcc zzccVar = (zzcc) new zzcc(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzccVar), (zzap) zzccVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, String str, zza zzaVar) {
        zzde zzdeVar = (zzde) new zzde(phoneAuthCredential, str).zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        return zza((Task) zzb(zzdeVar), (zzap) zzdeVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzba zzbaVar) {
        zzci zzciVar = (zzci) new zzci(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzciVar), (zzap) zzciVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, String str, zzba zzbaVar) {
        zzck zzckVar = (zzck) new zzck(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzckVar), (zzap) zzckVar);
    }

    public final Task<SignInMethodQueryResult> zza(FirebaseApp firebaseApp, String str, String str2) {
        zzbg zzbgVar = (zzbg) new zzbg(str, str2).zza(firebaseApp);
        return zza((Task) zza(zzbgVar), (zzap) zzbgVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zza(zzgj.PASSWORD_RESET);
        zzcq zzcqVar = (zzcq) new zzcq(str, actionCodeSettings, str2, "sendPasswordResetEmail").zza(firebaseApp);
        return zza((Task) zzb(zzcqVar), (zzap) zzcqVar);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, String str2) {
        actionCodeSettings.zza(zzgj.EMAIL_SIGNIN);
        zzcq zzcqVar = (zzcq) new zzcq(str, actionCodeSettings, str2, "sendSignInLinkToEmail").zza(firebaseApp);
        return zza((Task) zzb(zzcqVar), (zzap) zzcqVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, ActionCodeSettings actionCodeSettings, String str) {
        zzco zzcoVar = (zzco) new zzco(str, actionCodeSettings).zza(firebaseApp);
        return zza((Task) zzb(zzcoVar), (zzap) zzcoVar);
    }

    public final Task<ActionCodeResult> zzb(FirebaseApp firebaseApp, String str, String str2) {
        zzay zzayVar = (zzay) new zzay(str, str2).zza(firebaseApp);
        return zza((Task) zzb(zzayVar), (zzap) zzayVar);
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, String str, String str2) {
        zzaw zzawVar = (zzaw) new zzaw(str, str2).zza(firebaseApp);
        return zza((Task) zzb(zzawVar), (zzap) zzawVar);
    }

    public final Task<String> zzd(FirebaseApp firebaseApp, String str, String str2) {
        zzea zzeaVar = (zzea) new zzea(str, str2).zza(firebaseApp);
        return zza((Task) zzb(zzeaVar), (zzap) zzeaVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2, String str3) {
        zzba zzbaVar = (zzba) new zzba(str, str2, str3).zza(firebaseApp);
        return zza((Task) zzb(zzbaVar), (zzap) zzbaVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, zzba zzbaVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbaVar);
        List<String> zza = firebaseUser.zza();
        if (zza != null && zza.contains(authCredential.getProvider())) {
            return Tasks.forException(zzeh.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzg()) {
                zzbo zzboVar = (zzbo) new zzbo(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
                return zza((Task) zzb(zzboVar), (zzap) zzboVar);
            }
            zzbu zzbuVar = (zzbu) new zzbu(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
            return zza((Task) zzb(zzbuVar), (zzap) zzbuVar);
        } else if (authCredential instanceof PhoneAuthCredential) {
            zzbs zzbsVar = (zzbs) new zzbs((PhoneAuthCredential) authCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
            return zza((Task) zzb(zzbsVar), (zzap) zzbsVar);
        } else {
            Preconditions.checkNotNull(firebaseApp);
            Preconditions.checkNotNull(authCredential);
            Preconditions.checkNotNull(firebaseUser);
            Preconditions.checkNotNull(zzbaVar);
            zzbq zzbqVar = (zzbq) new zzbq(authCredential).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
            return zza((Task) zzb(zzbqVar), (zzap) zzbqVar);
        }
    }

    public final Task<AuthResult> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzba zzbaVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbaVar);
        List<String> zza = firebaseUser.zza();
        if ((zza != null && !zza.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzeh.zza(new Status(FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        char c = 65535;
        if (str.hashCode() == 1216985755 && str.equals("password")) {
            c = 0;
        }
        if (c != 0) {
            zzdo zzdoVar = (zzdo) new zzdo(str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
            return zza((Task) zzb(zzdoVar), (zzap) zzdoVar);
        }
        zzdm zzdmVar = (zzdm) new zzdm().zza(firebaseApp).zza(firebaseUser).zza((zzfe<AuthResult, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zzb(zzdmVar), (zzap) zzdmVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzba zzbaVar) {
        zzcm zzcmVar = (zzcm) new zzcm().zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar);
        return zza((Task) zza(zzcmVar), (zzap) zzcmVar);
    }

    public final Task<Void> zza(FirebaseUser firebaseUser, zzad zzadVar) {
        zzbe zzbeVar = (zzbe) new zzbe().zza(firebaseUser).zza((zzfe<Void, zzad>) zzadVar).zza((zzag) zzadVar);
        return zza((Task) zzb(zzbeVar), (zzap) zzbeVar);
    }

    public final Task<Void> zza(String str) {
        zzcs zzcsVar = new zzcs(str);
        return zza((Task) zzb(zzcsVar), (zzap) zzcsVar);
    }

    public final Task<Void> zza(zzy zzyVar, String str, String str2, long j, boolean z, boolean z2, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzdg zzdgVar = new zzdg(zzyVar, str, str2, j, z, z2);
        zzdgVar.zza(onVerificationStateChangedCallbacks, activity, executor);
        return zzb(zzdgVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, String str, zza zzaVar) {
        zzbi zzbiVar = new zzbi(phoneMultiFactorAssertion, firebaseUser.zzf(), str);
        zzbiVar.zza(firebaseApp).zza((zzfe<Void, zza>) zzaVar);
        return zzb(zzbiVar);
    }

    public final Task<Void> zza(zzy zzyVar, PhoneMultiFactorInfo phoneMultiFactorInfo, String str, long j, boolean z, boolean z2, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, Activity activity) {
        zzdi zzdiVar = new zzdi(phoneMultiFactorInfo, zzyVar.zzb(), str, j, z, z2);
        zzdiVar.zza(onVerificationStateChangedCallbacks, activity, executor);
        return zzb(zzdiVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zza zzaVar) {
        zzbk zzbkVar = new zzbk(phoneMultiFactorAssertion, str);
        zzbkVar.zza(firebaseApp).zza((zzfe<AuthResult, zza>) zzaVar);
        if (firebaseUser != null) {
            zzbkVar.zza(firebaseUser);
        }
        return zzb(zzbkVar);
    }

    public final Task<Void> zze(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzba zzbaVar) {
        return zzb((zzdk) new zzdk(firebaseUser.zzf(), str).zza(firebaseApp).zza(firebaseUser).zza((zzfe<Void, zza>) zzbaVar).zza((zzag) zzbaVar));
    }

    public final Task<Void> zza(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zza(zzgj.VERIFY_AND_CHANGE_EMAIL);
        return zzb(new zzdy(str, str2, actionCodeSettings));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzn zza(FirebaseApp firebaseApp, zzew zzewVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzewVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzj(zzewVar, FirebaseAuthProvider.PROVIDER_ID));
        List<zzfj> zzj = zzewVar.zzj();
        if (zzj != null && !zzj.isEmpty()) {
            for (int i = 0; i < zzj.size(); i++) {
                arrayList.add(new zzj(zzj.get(i)));
            }
        }
        zzn zznVar = new zzn(firebaseApp, arrayList);
        zznVar.zza(new zzp(zzewVar.zzh(), zzewVar.zzg()));
        zznVar.zza(zzewVar.zzi());
        zznVar.zza(zzewVar.zzl());
        zznVar.zzb(zzat.zza(zzewVar.zzm()));
        return zznVar;
    }

    private final <ResultT> Task<ResultT> zza(Task<ResultT> task, zzap<zzef, ResultT> zzapVar) {
        return (Task<ResultT>) task.continueWithTask(new zzat(this, zzapVar));
    }
}
