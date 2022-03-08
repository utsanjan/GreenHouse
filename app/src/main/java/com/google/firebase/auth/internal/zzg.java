package com.google.firebase.auth.internal;

import com.google.android.gms.internal.firebase_auth.zzfm;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzg implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    public zzg(zzfm zzfmVar) {
        this.zzb = zzfmVar.zzg() ? zzfmVar.zzc() : zzfmVar.zzb();
        this.zzc = zzfmVar.zzb();
        ActionCodeInfo actionCodeInfo = null;
        if (!zzfmVar.zzh()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        String zzd = zzfmVar.zzd();
        char c = 65535;
        int i = 0;
        switch (zzd.hashCode()) {
            case -1874510116:
                if (zzd.equals("REVERT_SECOND_FACTOR_ADDITION")) {
                    c = 5;
                    break;
                }
                break;
            case -1452371317:
                if (zzd.equals("PASSWORD_RESET")) {
                    c = 0;
                    break;
                }
                break;
            case -1341836234:
                if (zzd.equals("VERIFY_EMAIL")) {
                    c = 1;
                    break;
                }
                break;
            case -1288726400:
                if (zzd.equals("VERIFY_BEFORE_UPDATE_EMAIL")) {
                    c = 3;
                    break;
                }
                break;
            case 870738373:
                if (zzd.equals("EMAIL_SIGNIN")) {
                    c = 2;
                    break;
                }
                break;
            case 970484929:
                if (zzd.equals("RECOVER_EMAIL")) {
                    c = 4;
                    break;
                }
                break;
        }
        if (c != 0) {
            if (c == 1) {
                i = 1;
            } else if (c == 2) {
                i = 4;
            } else if (c == 3) {
                i = 5;
            } else if (c == 4) {
                i = 2;
            } else if (c != 5) {
                i = 3;
            } else {
                i = 6;
            }
        }
        this.zza = i;
        if (i == 4 || i == 3) {
            this.zzd = null;
            return;
        }
        if (zzfmVar.zzi()) {
            actionCodeInfo = new zzd(zzfmVar.zzb(), zzat.zza(zzfmVar.zze()));
        } else if (zzfmVar.zzg()) {
            actionCodeInfo = new zzb(zzfmVar.zzc(), zzfmVar.zzb());
        } else if (zzfmVar.zzf()) {
            actionCodeInfo = new zze(zzfmVar.zzb());
        }
        this.zzd = actionCodeInfo;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final int getOperation() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        if (i == 0) {
            return this.zzb;
        }
        if (i != 1) {
            return null;
        }
        return this.zzc;
    }
}
