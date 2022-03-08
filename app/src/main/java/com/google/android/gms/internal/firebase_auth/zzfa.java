package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfa implements zzfw<zzp.zzh> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private ActionCodeSettings zze;
    private String zzf;

    public zzfa(zzgj zzgjVar) {
        this.zza = zza(zzgjVar);
    }

    private zzfa(zzgj zzgjVar, ActionCodeSettings actionCodeSettings, String str, String str2, String str3, String str4) {
        this.zza = zza((zzgj) Preconditions.checkNotNull(zzgjVar));
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzb = null;
        this.zzc = str2;
        this.zzd = str3;
        this.zzf = null;
    }

    public static zzfa zza(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzfa(zzgj.VERIFY_AND_CHANGE_EMAIL, actionCodeSettings, null, str2, str, null);
    }

    public final zzfa zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfa zzb(String str) {
        this.zzd = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfa zza(ActionCodeSettings actionCodeSettings) {
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzfa zzc(String str) {
        this.zzf = str;
        return this;
    }

    public final ActionCodeSettings zzb() {
        return this.zze;
    }

    private static String zza(zzgj zzgjVar) {
        int i = zzfd.zza[zzgjVar.ordinal()];
        if (i == 1) {
            return "PASSWORD_RESET";
        }
        if (i == 2) {
            return "VERIFY_EMAIL";
        }
        if (i == 3) {
            return "EMAIL_SIGNIN";
        }
        if (i != 4) {
            return "REQUEST_TYPE_UNSET_ENUM_VALUE";
        }
        return "VERIFY_BEFORE_UPDATE_EMAIL";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzh zza() {
        char c;
        zzgj zzgjVar;
        zzp.zzh.zza zza = zzp.zzh.zza();
        String str = this.zza;
        switch (str.hashCode()) {
            case -1452371317:
                if (str.equals("PASSWORD_RESET")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1341836234:
                if (str.equals("VERIFY_EMAIL")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1288726400:
                if (str.equals("VERIFY_BEFORE_UPDATE_EMAIL")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 870738373:
                if (str.equals("EMAIL_SIGNIN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            zzgjVar = zzgj.PASSWORD_RESET;
        } else if (c == 1) {
            zzgjVar = zzgj.VERIFY_EMAIL;
        } else if (c == 2) {
            zzgjVar = zzgj.EMAIL_SIGNIN;
        } else if (c != 3) {
            zzgjVar = zzgj.OOB_REQ_TYPE_UNSPECIFIED;
        } else {
            zzgjVar = zzgj.VERIFY_AND_CHANGE_EMAIL;
        }
        zzp.zzh.zza zza2 = zza.zza(zzgjVar);
        String str2 = this.zzb;
        if (str2 != null) {
            zza2.zza(str2);
        }
        String str3 = this.zzc;
        if (str3 != null) {
            zza2.zzb(str3);
        }
        String str4 = this.zzd;
        if (str4 != null) {
            zza2.zzc(str4);
        }
        ActionCodeSettings actionCodeSettings = this.zze;
        if (actionCodeSettings != null) {
            zza2.zza(actionCodeSettings.getAndroidInstallApp()).zzb(this.zze.canHandleCodeInApp());
            if (this.zze.getUrl() != null) {
                zza2.zzd(this.zze.getUrl());
            }
            if (this.zze.getIOSBundle() != null) {
                zza2.zze(this.zze.getIOSBundle());
            }
            if (this.zze.zzb() != null) {
                zza2.zzf(this.zze.zzb());
            }
            if (this.zze.getAndroidPackageName() != null) {
                zza2.zzg(this.zze.getAndroidPackageName());
            }
            if (this.zze.getAndroidMinimumVersion() != null) {
                zza2.zzh(this.zze.getAndroidMinimumVersion());
            }
            if (this.zze.zze() != null) {
                zza2.zzj(this.zze.zze());
            }
        }
        String str5 = this.zzf;
        if (str5 != null) {
            zza2.zzi(str5);
        }
        return (zzp.zzh) ((zzif) zza2.zzg());
    }
}
