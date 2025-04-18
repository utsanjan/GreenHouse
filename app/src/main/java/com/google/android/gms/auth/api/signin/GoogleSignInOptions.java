package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    private final int versionCode;
    private final boolean zaaa;
    private String zaab;
    private String zaac;
    private ArrayList<GoogleSignInOptionsExtensionParcelable> zaad;
    private String zaae;
    private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaaf;
    private final ArrayList<Scope> zaw;
    private Account zax;
    private boolean zay;
    private final boolean zaz;
    public static final Scope zar = new Scope(Scopes.PROFILE);
    public static final Scope zas = new Scope("email");
    public static final Scope zat = new Scope(Scopes.OPEN_ID);
    public static final Scope zau = new Scope(Scopes.GAMES_LITE);
    public static final Scope zav = new Scope(Scopes.GAMES);
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(zau, new Scope[0]).build();
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zad();
    private static Comparator<Scope> zaag = new zac();

    public static GoogleSignInOptions zab(String str) throws JSONException {
        Account account;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", null);
        if (!TextUtils.isEmpty(optString)) {
            account = new Account(optString, "com.google");
        } else {
            account = null;
        }
        return new GoogleSignInOptions(3, new ArrayList(hashSet), account, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap(), (String) null);
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private Set<Scope> mScopes;
        private boolean zaaa;
        private String zaab;
        private String zaac;
        private String zaae;
        private Map<Integer, GoogleSignInOptionsExtensionParcelable> zaah;
        private Account zax;
        private boolean zay;
        private boolean zaz;

        public Builder() {
            this.mScopes = new HashSet();
            this.zaah = new HashMap();
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            this.mScopes = new HashSet();
            this.zaah = new HashMap();
            Preconditions.checkNotNull(googleSignInOptions);
            this.mScopes = new HashSet(googleSignInOptions.zaw);
            this.zaz = googleSignInOptions.zaz;
            this.zaaa = googleSignInOptions.zaaa;
            this.zay = googleSignInOptions.zay;
            this.zaab = googleSignInOptions.zaab;
            this.zax = googleSignInOptions.zax;
            this.zaac = googleSignInOptions.zaac;
            this.zaah = GoogleSignInOptions.zaa(googleSignInOptions.zaad);
            this.zaae = googleSignInOptions.zaae;
        }

        public final Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.zat);
            return this;
        }

        public final Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.zas);
            return this;
        }

        public final Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.zar);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zay = true;
            this.zaab = zac(str);
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zaz = true;
            this.zaab = zac(str);
            this.zaaa = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zax = new Account(Preconditions.checkNotEmpty(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zaac = Preconditions.checkNotEmpty(str);
            return this;
        }

        public final Builder setLogSessionId(String str) {
            this.zaae = str;
            return this;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (!this.zaah.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                    this.mScopes.addAll(googleSignInOptionsExtension.getImpliedScopes());
                }
                this.zaah.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
                return this;
            }
            throw new IllegalStateException("Only one extension per type may be added");
        }

        public final GoogleSignInOptions build() {
            if (this.mScopes.contains(GoogleSignInOptions.zav) && this.mScopes.contains(GoogleSignInOptions.zau)) {
                this.mScopes.remove(GoogleSignInOptions.zau);
            }
            if (this.zay && (this.zax == null || !this.mScopes.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.mScopes), this.zax, this.zay, this.zaz, this.zaaa, this.zaab, this.zaac, this.zaah, this.zaae, null);
        }

        private final String zac(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zaab;
            Preconditions.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<GoogleSignInOptionsExtensionParcelable> arrayList2, String str3) {
        this(i, arrayList, account, z, z2, z3, str, str2, zaa(arrayList2), str3);
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, GoogleSignInOptionsExtensionParcelable> map, String str3) {
        this.versionCode = i;
        this.zaw = arrayList;
        this.zax = account;
        this.zay = z;
        this.zaz = z2;
        this.zaaa = z3;
        this.zaab = str;
        this.zaac = str2;
        this.zaad = new ArrayList<>(map.values());
        this.zaaf = map;
        this.zaae = str3;
    }

    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zaw);
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zaw;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    public Account getAccount() {
        return this.zax;
    }

    public boolean isIdTokenRequested() {
        return this.zay;
    }

    public boolean isServerAuthCodeRequested() {
        return this.zaz;
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zaaa;
    }

    public String getServerClientId() {
        return this.zaab;
    }

    public String getLogSessionId() {
        return this.zaae;
    }

    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zaad;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Integer, GoogleSignInOptionsExtensionParcelable> zaa(List<GoogleSignInOptionsExtensionParcelable> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable : list) {
            hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
        }
        return hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zaac, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.writeString(parcel, 10, getLogSessionId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r4.getAccount() == null) goto L_0x003f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
        if (r3.zaab.equals(r4.getServerClientId()) != false) goto L_0x006c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = (com.google.android.gms.auth.api.signin.GoogleSignInOptions) r4     // Catch: ClassCastException -> 0x0096
            java.util.ArrayList<com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> r1 = r3.zaad     // Catch: ClassCastException -> 0x0096
            int r1 = r1.size()     // Catch: ClassCastException -> 0x0096
            if (r1 > 0) goto L_0x0095
            java.util.ArrayList<com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> r1 = r4.zaad     // Catch: ClassCastException -> 0x0096
            int r1 = r1.size()     // Catch: ClassCastException -> 0x0096
            if (r1 <= 0) goto L_0x0018
            goto L_0x0095
        L_0x0018:
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r1 = r3.zaw     // Catch: ClassCastException -> 0x0096
            int r1 = r1.size()     // Catch: ClassCastException -> 0x0096
            java.util.ArrayList r2 = r4.getScopes()     // Catch: ClassCastException -> 0x0096
            int r2 = r2.size()     // Catch: ClassCastException -> 0x0096
            if (r1 != r2) goto L_0x0094
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r1 = r3.zaw     // Catch: ClassCastException -> 0x0096
            java.util.ArrayList r2 = r4.getScopes()     // Catch: ClassCastException -> 0x0096
            boolean r1 = r1.containsAll(r2)     // Catch: ClassCastException -> 0x0096
            if (r1 != 0) goto L_0x0035
            goto L_0x0094
        L_0x0035:
            android.accounts.Account r1 = r3.zax     // Catch: ClassCastException -> 0x0096
            if (r1 != 0) goto L_0x0040
            android.accounts.Account r1 = r4.getAccount()     // Catch: ClassCastException -> 0x0096
            if (r1 != 0) goto L_0x0092
        L_0x003f:
            goto L_0x004d
        L_0x0040:
            android.accounts.Account r1 = r3.zax     // Catch: ClassCastException -> 0x0096
            android.accounts.Account r2 = r4.getAccount()     // Catch: ClassCastException -> 0x0096
            boolean r1 = r1.equals(r2)     // Catch: ClassCastException -> 0x0096
            if (r1 == 0) goto L_0x0092
            goto L_0x003f
        L_0x004d:
            java.lang.String r1 = r3.zaab     // Catch: ClassCastException -> 0x0096
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: ClassCastException -> 0x0096
            if (r1 == 0) goto L_0x0060
            java.lang.String r1 = r4.getServerClientId()     // Catch: ClassCastException -> 0x0096
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: ClassCastException -> 0x0096
            if (r1 == 0) goto L_0x0092
            goto L_0x006c
        L_0x0060:
            java.lang.String r1 = r3.zaab     // Catch: ClassCastException -> 0x0096
            java.lang.String r2 = r4.getServerClientId()     // Catch: ClassCastException -> 0x0096
            boolean r1 = r1.equals(r2)     // Catch: ClassCastException -> 0x0096
            if (r1 == 0) goto L_0x0092
        L_0x006c:
            boolean r1 = r3.zaaa     // Catch: ClassCastException -> 0x0096
            boolean r2 = r4.isForceCodeForRefreshToken()     // Catch: ClassCastException -> 0x0096
            if (r1 != r2) goto L_0x0092
            boolean r1 = r3.zay     // Catch: ClassCastException -> 0x0096
            boolean r2 = r4.isIdTokenRequested()     // Catch: ClassCastException -> 0x0096
            if (r1 != r2) goto L_0x0092
            boolean r1 = r3.zaz     // Catch: ClassCastException -> 0x0096
            boolean r2 = r4.isServerAuthCodeRequested()     // Catch: ClassCastException -> 0x0096
            if (r1 != r2) goto L_0x0092
            java.lang.String r1 = r3.zaae     // Catch: ClassCastException -> 0x0096
            java.lang.String r4 = r4.getLogSessionId()     // Catch: ClassCastException -> 0x0096
            boolean r4 = android.text.TextUtils.equals(r1, r4)     // Catch: ClassCastException -> 0x0096
            if (r4 == 0) goto L_0x0092
            r4 = 1
            return r4
        L_0x0092:
            return r0
        L_0x0094:
            return r0
        L_0x0095:
            return r0
        L_0x0096:
            r4 = move-exception
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList<Scope> arrayList2 = this.zaw;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Scope scope = arrayList2.get(i);
            i++;
            arrayList.add(scope.getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.zax).addObject(this.zaab).zaa(this.zaaa).zaa(this.zay).zaa(this.zaz).addObject(this.zaae).hash();
    }

    public final String zae() {
        return zad().toString();
    }

    private final JSONObject zad() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zaw, zaag);
            ArrayList<Scope> arrayList = this.zaw;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Scope scope = arrayList.get(i);
                i++;
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zax != null) {
                jSONObject.put("accountName", this.zax.name);
            }
            jSONObject.put("idTokenRequested", this.zay);
            jSONObject.put("forceCodeForRefreshToken", this.zaaa);
            jSONObject.put("serverAuthRequested", this.zaz);
            if (!TextUtils.isEmpty(this.zaab)) {
                jSONObject.put("serverClientId", this.zaab);
            }
            if (!TextUtils.isEmpty(this.zaac)) {
                jSONObject.put("hostedDomain", this.zaac);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3, zac zacVar) {
        this(3, arrayList, account, z, z2, z3, str, str2, map, str3);
    }
}
