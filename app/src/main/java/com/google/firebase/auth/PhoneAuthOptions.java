package com.google.firebase.auth;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class PhoneAuthOptions {
    private final FirebaseAuth zza;
    private Long zzb;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks zzc;
    private Executor zzd;
    private String zze;
    private Activity zzf;
    private PhoneAuthProvider.ForceResendingToken zzg;
    private MultiFactorSession zzh;
    private PhoneMultiFactorInfo zzi;
    private String zzj;
    private boolean zzk;

    private PhoneAuthOptions(FirebaseAuth firebaseAuth, Long l, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, String str, Activity activity, PhoneAuthProvider.ForceResendingToken forceResendingToken, MultiFactorSession multiFactorSession, PhoneMultiFactorInfo phoneMultiFactorInfo, String str2, boolean z) {
        this.zza = firebaseAuth;
        this.zze = str;
        this.zzb = l;
        this.zzc = onVerificationStateChangedCallbacks;
        this.zzf = activity;
        this.zzd = executor;
        this.zzg = forceResendingToken;
        this.zzh = multiFactorSession;
        this.zzi = phoneMultiFactorInfo;
        this.zzj = str2;
        this.zzk = z;
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private final FirebaseAuth zza;
        private String zzb;
        private Long zzc;
        private PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd;
        private Executor zze;
        private Activity zzf;
        private PhoneAuthProvider.ForceResendingToken zzg;
        private MultiFactorSession zzh;
        private PhoneMultiFactorInfo zzi;
        private boolean zzj;

        public Builder(FirebaseAuth firebaseAuth) {
            this.zza = (FirebaseAuth) Preconditions.checkNotNull(firebaseAuth);
        }

        public final Builder setPhoneNumber(String str) {
            this.zzb = str;
            return this;
        }

        public final Builder setMultiFactorHint(PhoneMultiFactorInfo phoneMultiFactorInfo) {
            this.zzi = phoneMultiFactorInfo;
            return this;
        }

        public final Builder setTimeout(Long l, TimeUnit timeUnit) {
            this.zzc = Long.valueOf(TimeUnit.SECONDS.convert(l.longValue(), timeUnit));
            return this;
        }

        public final Builder setCallbacks(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
            this.zzd = onVerificationStateChangedCallbacks;
            return this;
        }

        public final Builder setActivity(Activity activity) {
            this.zzf = activity;
            return this;
        }

        public final Builder setExecutor(Executor executor) {
            this.zze = executor;
            return this;
        }

        public final Builder setForceResendingToken(PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            this.zzg = forceResendingToken;
            return this;
        }

        public final Builder setMultiFactorSession(MultiFactorSession multiFactorSession) {
            this.zzh = multiFactorSession;
            return this;
        }

        public final Builder requireSmsValidation(boolean z) {
            this.zzj = z;
            return this;
        }

        public final PhoneAuthOptions build() {
            Preconditions.checkNotNull(this.zza);
            Preconditions.checkNotNull(this.zzc);
            Preconditions.checkNotNull(this.zzd);
            if (this.zze == null) {
                this.zze = TaskExecutors.MAIN_THREAD;
            }
            if (this.zze != TaskExecutors.MAIN_THREAD && this.zzf != null) {
                throw new IllegalArgumentException("You cannot specify both an executor and an activity.");
            } else if (this.zzc.longValue() < 0 || this.zzc.longValue() > 120) {
                throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
            } else {
                boolean z = false;
                if (this.zzh == null) {
                    Preconditions.checkNotEmpty(this.zzb);
                    Preconditions.checkArgument(!this.zzj, "You cannot require sms validation without setting a multi-factor session.");
                    if (this.zzi == null) {
                        z = true;
                    }
                    Preconditions.checkArgument(z, "A phoneMultiFactorInfo must be set for second factor sign-in.");
                } else {
                    MultiFactorSession multiFactorSession = this.zzh;
                    if (multiFactorSession != null && ((zzy) multiFactorSession).zzc()) {
                        Preconditions.checkNotEmpty(this.zzb);
                        if (this.zzi == null) {
                            z = true;
                        }
                        Preconditions.checkArgument(z, "Invalid MultiFactorSession - use the getSession method in MultiFactorResolver to get a valid sign-in session.");
                    } else {
                        Preconditions.checkArgument(this.zzi != null, "A phoneMultiFactorInfo must be set for second factor sign-in.");
                        if (this.zzb == null) {
                            z = true;
                        }
                        Preconditions.checkArgument(z, "A phone number must not be set for MFA sign-in. A PhoneMultiFactorInfo should be set instead.");
                    }
                }
                return new PhoneAuthOptions(this.zza, this.zzc, this.zzd, this.zze, this.zzb, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj);
            }
        }
    }

    public final FirebaseAuth zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zze;
    }

    public final Long zzc() {
        return this.zzb;
    }

    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zzd() {
        return this.zzc;
    }

    public final Executor zze() {
        return this.zzd;
    }

    public final PhoneAuthProvider.ForceResendingToken zzf() {
        return this.zzg;
    }

    public final MultiFactorSession zzg() {
        return this.zzh;
    }

    public final String zzh() {
        return this.zzj;
    }

    public final boolean zzi() {
        return this.zzk;
    }

    public final Activity zzj() {
        return this.zzf;
    }

    public final PhoneMultiFactorInfo zzk() {
        return this.zzi;
    }

    public final boolean zzl() {
        return this.zzh != null;
    }

    public static Builder newBuilder() {
        return new Builder(FirebaseAuth.getInstance());
    }

    public static Builder newBuilder(FirebaseAuth firebaseAuth) {
        return new Builder(firebaseAuth);
    }
}
