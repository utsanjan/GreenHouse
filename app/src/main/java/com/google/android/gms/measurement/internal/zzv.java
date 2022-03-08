package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzv {
    String zza;
    int zzb;
    Boolean zzc;
    Boolean zzd;
    Long zze;
    Long zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(String str, int i) {
        this.zza = str;
        this.zzb = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzc();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Boolean zza(String str, zzbt.zzf zzfVar, zzeu zzeuVar) {
        String str2;
        List<String> list;
        String str3;
        Preconditions.checkNotNull(zzfVar);
        if (str == null || !zzfVar.zza() || zzfVar.zzb() == zzbt.zzf.zzb.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        if (zzfVar.zzb() == zzbt.zzf.zzb.IN_LIST) {
            if (zzfVar.zzh() == 0) {
                return null;
            }
        } else if (!zzfVar.zzc()) {
            return null;
        }
        zzbt.zzf.zzb zzb = zzfVar.zzb();
        boolean zzf = zzfVar.zzf();
        if (zzf || zzb == zzbt.zzf.zzb.REGEXP || zzb == zzbt.zzf.zzb.IN_LIST) {
            str2 = zzfVar.zzd();
        } else {
            str2 = zzfVar.zzd().toUpperCase(Locale.ENGLISH);
        }
        if (zzfVar.zzh() == 0) {
            list = null;
        } else {
            List<String> zzg = zzfVar.zzg();
            if (zzf) {
                list = zzg;
            } else {
                ArrayList arrayList = new ArrayList(zzg.size());
                for (String str4 : zzg) {
                    arrayList.add(str4.toUpperCase(Locale.ENGLISH));
                }
                list = Collections.unmodifiableList(arrayList);
            }
        }
        if (zzb == zzbt.zzf.zzb.REGEXP) {
            str3 = str2;
        } else {
            str3 = null;
        }
        return zza(str, zzb, zzf, str2, list, str3, zzeuVar);
    }

    private static Boolean zza(String str, zzbt.zzf.zzb zzbVar, boolean z, String str2, List<String> list, String str3, zzeu zzeuVar) {
        if (str == null) {
            return null;
        }
        if (zzbVar == zzbt.zzf.zzb.IN_LIST) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && zzbVar != zzbt.zzf.zzb.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zzr.zza[zzbVar.ordinal()]) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    if (zzeuVar != null) {
                        zzeuVar.zzi().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Boolean zza(long j, zzbt.zzd zzdVar) {
        try {
            return zza(new BigDecimal(j), zzdVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Boolean zza(double d, zzbt.zzd zzdVar) {
        try {
            return zza(new BigDecimal(d), zzdVar, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Boolean zza(String str, zzbt.zzd zzdVar) {
        if (!zzkn.zza(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzdVar, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x008c, code lost:
        if (r2 != null) goto L_0x008e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Boolean zza(java.math.BigDecimal r9, com.google.android.gms.internal.measurement.zzbt.zzd r10, double r11) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzv.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzbt$zzd, double):java.lang.Boolean");
    }
}
