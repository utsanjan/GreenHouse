package com.google.android.gms.internal.firebase_auth;

import andhook.lib.xposed.ClassUtils;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzah extends zzai {
    private final char zza = ClassUtils.PACKAGE_SEPARATOR_CHAR;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzah(char c) {
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaf
    public final boolean zza(char c) {
        return c == this.zza;
    }

    public final String toString() {
        String zzc;
        zzc = zzaf.zzc(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(zzc).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(zzc);
        sb.append("')");
        return sb.toString();
    }
}
