package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class zzin extends IOException {
    private zzjn zza = null;

    public zzin(String str) {
        super(str);
    }

    public final zzin zza(zzjn zzjnVar) {
        this.zza = zzjnVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zza() {
        return new zzin("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zzb() {
        return new zzin("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zzc() {
        return new zzin("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zzd() {
        return new zzin("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zze() {
        return new zzin("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zziq zzf() {
        return new zziq("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zzg() {
        return new zzin("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zzh() {
        return new zzin("Failed to parse the message.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzin zzi() {
        return new zzin("Protocol message had invalid UTF-8.");
    }
}
