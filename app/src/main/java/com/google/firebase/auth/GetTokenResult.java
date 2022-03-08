package com.google.firebase.auth;

import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth-interop@@19.0.0 */
/* loaded from: classes.dex */
public class GetTokenResult {
    private String zza;
    private Map<String, Object> zzb;

    public GetTokenResult(String str, Map<String, Object> map) {
        this.zza = str;
        this.zzb = map;
    }

    public String getToken() {
        return this.zza;
    }

    public long getExpirationTimestamp() {
        return zza("exp");
    }

    public long getAuthTimestamp() {
        return zza("auth_time");
    }

    public long getIssuedAtTimestamp() {
        return zza("iat");
    }

    public String getSignInProvider() {
        Map map = (Map) this.zzb.get(FirebaseAuthProvider.PROVIDER_ID);
        if (map != null) {
            return (String) map.get("sign_in_provider");
        }
        return null;
    }

    public String getSignInSecondFactor() {
        Map map = (Map) this.zzb.get(FirebaseAuthProvider.PROVIDER_ID);
        if (map != null) {
            return (String) map.get("sign_in_second_factor");
        }
        return null;
    }

    public Map<String, Object> getClaims() {
        return this.zzb;
    }

    private final long zza(String str) {
        Integer num = (Integer) this.zzb.get(str);
        if (num == null) {
            return 0L;
        }
        return num.longValue();
    }
}
