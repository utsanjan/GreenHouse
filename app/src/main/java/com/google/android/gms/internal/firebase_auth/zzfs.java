package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfs implements zzfw<zzp.zzn> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzi;
    private String zzj;
    private boolean zzh = true;
    private zzfw zzg = new zzfw();
    private zzfw zzf = new zzfw();

    public final boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzg.zza().contains(str);
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }

    public final String zze() {
        return this.zze;
    }

    public final zzfs zzb(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfs zzc(String str) {
        if (str == null) {
            this.zzg.zza().add("EMAIL");
        } else {
            this.zzb = str;
        }
        return this;
    }

    public final zzfs zzd(String str) {
        if (str == null) {
            this.zzg.zza().add("PASSWORD");
        } else {
            this.zzc = str;
        }
        return this;
    }

    public final zzfs zze(String str) {
        if (str == null) {
            this.zzg.zza().add("DISPLAY_NAME");
        } else {
            this.zzd = str;
        }
        return this;
    }

    public final zzfs zzf(String str) {
        if (str == null) {
            this.zzg.zza().add("PHOTO_URL");
        } else {
            this.zze = str;
        }
        return this;
    }

    public final zzfs zzg(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzf.zza().add(str);
        return this;
    }

    public final zzfs zzh(String str) {
        this.zzi = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfs zzi(String str) {
        this.zzj = str;
        return this;
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzn zza() {
        zzv zzvVar;
        zzp.zzn.zzb zzb = zzp.zzn.zza().zza(this.zzh).zzb(this.zzf.zza());
        List<String> zza = this.zzg.zza();
        zzv[] zzvVarArr = new zzv[zza.size()];
        for (int i = 0; i < zza.size(); i++) {
            String str = zza.get(i);
            char c = 65535;
            switch (str.hashCode()) {
                case -333046776:
                    if (str.equals("DISPLAY_NAME")) {
                        c = 1;
                        break;
                    }
                    break;
                case 66081660:
                    if (str.equals("EMAIL")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1939891618:
                    if (str.equals("PHOTO_URL")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1999612571:
                    if (str.equals("PASSWORD")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                zzvVar = zzv.EMAIL;
            } else if (c == 1) {
                zzvVar = zzv.DISPLAY_NAME;
            } else if (c == 2) {
                zzvVar = zzv.PASSWORD;
            } else if (c != 3) {
                zzvVar = zzv.USER_ATTRIBUTE_NAME_UNSPECIFIED;
            } else {
                zzvVar = zzv.PHOTO_URL;
            }
            zzvVarArr[i] = zzvVar;
        }
        zzp.zzn.zzb zza2 = zzb.zza(Arrays.asList(zzvVarArr));
        String str2 = this.zza;
        if (str2 != null) {
            zza2.zza(str2);
        }
        String str3 = this.zzb;
        if (str3 != null) {
            zza2.zzc(str3);
        }
        String str4 = this.zzc;
        if (str4 != null) {
            zza2.zzd(str4);
        }
        String str5 = this.zzd;
        if (str5 != null) {
            zza2.zzb(str5);
        }
        String str6 = this.zze;
        if (str6 != null) {
            zza2.zzf(str6);
        }
        String str7 = this.zzi;
        if (str7 != null) {
            zza2.zze(str7);
        }
        String str8 = this.zzj;
        if (str8 != null) {
            zza2.zzg(str8);
        }
        return (zzp.zzn) ((zzif) zza2.zzg());
    }
}
