package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public enum zzgj implements zzih {
    OOB_REQ_TYPE_UNSPECIFIED(0),
    PASSWORD_RESET(1),
    OLD_EMAIL_AGREE(2),
    NEW_EMAIL_ACCEPT(3),
    VERIFY_EMAIL(4),
    RECOVER_EMAIL(5),
    EMAIL_SIGNIN(6),
    VERIFY_AND_CHANGE_EMAIL(7),
    REVERT_SECOND_FACTOR_ADDITION(8);
    
    private static final zzik<zzgj> zzj = new zzik<zzgj>() { // from class: com.google.android.gms.internal.firebase_auth.zzgl
    };
    private final int zzk;

    @Override // com.google.android.gms.internal.firebase_auth.zzih
    public final int zza() {
        return this.zzk;
    }

    public static zzgj zza(int i) {
        switch (i) {
            case 0:
                return OOB_REQ_TYPE_UNSPECIFIED;
            case 1:
                return PASSWORD_RESET;
            case 2:
                return OLD_EMAIL_AGREE;
            case 3:
                return NEW_EMAIL_ACCEPT;
            case 4:
                return VERIFY_EMAIL;
            case 5:
                return RECOVER_EMAIL;
            case 6:
                return EMAIL_SIGNIN;
            case 7:
                return VERIFY_AND_CHANGE_EMAIL;
            case 8:
                return REVERT_SECOND_FACTOR_ADDITION;
            default:
                return null;
        }
    }

    public static zzij zzb() {
        return zzgk.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzk + " name=" + name() + '>';
    }

    zzgj(int i) {
        this.zzk = i;
    }
}
