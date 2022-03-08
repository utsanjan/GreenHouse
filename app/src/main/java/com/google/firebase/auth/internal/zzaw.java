package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.api.zza;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzaw {
    private Context zza;
    private String zzb;
    private SharedPreferences zzc;
    private Logger zzd = new Logger("StorageHelpers", new String[0]);

    public zzaw(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zza = context.getApplicationContext();
        this.zzc = this.zza.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", this.zzb), 0);
    }

    public final void zza(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String zzc = zzc(firebaseUser);
        if (!TextUtils.isEmpty(zzc)) {
            this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", zzc).apply();
        }
    }

    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zza(jSONObject);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public final void zza(FirebaseUser firebaseUser, zzff zzffVar) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzffVar);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), zzffVar.zzh()).apply();
    }

    public final zzff zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), null);
        if (string != null) {
            return zzff.zzb(string);
        }
        return null;
    }

    public final void zza(String str) {
        this.zzc.edit().remove(str).apply();
    }

    private final String zzc(FirebaseUser firebaseUser) {
        JSONObject jSONObject = new JSONObject();
        if (!zzn.class.isAssignableFrom(firebaseUser.getClass())) {
            return null;
        }
        zzn zznVar = (zzn) firebaseUser;
        try {
            jSONObject.put("cachedTokenState", zznVar.zzf());
            jSONObject.put("applicationName", zznVar.zzc().getName());
            jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
            if (zznVar.zzh() != null) {
                JSONArray jSONArray = new JSONArray();
                List<zzj> zzh = zznVar.zzh();
                for (int i = 0; i < zzh.size(); i++) {
                    jSONArray.put(zzh.get(i).zzb());
                }
                jSONObject.put("userInfos", jSONArray);
            }
            jSONObject.put("anonymous", zznVar.isAnonymous());
            jSONObject.put("version", ExifInterface.GPS_MEASUREMENT_2D);
            if (zznVar.getMetadata() != null) {
                jSONObject.put("userMetadata", ((zzp) zznVar.getMetadata()).zza());
            }
            List<MultiFactorInfo> enrolledFactors = ((zzr) zznVar.getMultiFactor()).getEnrolledFactors();
            if (enrolledFactors != null && !enrolledFactors.isEmpty()) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < enrolledFactors.size(); i2++) {
                    jSONArray2.put(enrolledFactors.get(i2).toJson());
                }
                jSONObject.put("userMultiFactorInfo", jSONArray2);
            }
            return jSONObject.toString();
        } catch (Exception e) {
            this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
            throw new zza(e);
        }
    }

    private final zzn zza(JSONObject jSONObject) {
        JSONArray jSONArray;
        PhoneMultiFactorInfo phoneMultiFactorInfo;
        zzp zza;
        try {
            String string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z = jSONObject.getBoolean("anonymous");
            String str = ExifInterface.GPS_MEASUREMENT_2D;
            String string3 = jSONObject.getString("version");
            if (string3 != null) {
                str = string3;
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("userInfos");
            int length = jSONArray2.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(zzj.zza(jSONArray2.getString(i)));
            }
            zzn zznVar = new zzn(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                zznVar.zza(zzff.zzb(string));
            }
            if (!z) {
                zznVar.zzb();
            }
            zznVar.zza(str);
            if (jSONObject.has("userMetadata") && (zza = zzp.zza(jSONObject.getJSONObject("userMetadata"))) != null) {
                zznVar.zza(zza);
            }
            if (jSONObject.has("userMultiFactorInfo") && (jSONArray = jSONObject.getJSONArray("userMultiFactorInfo")) != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject2 = new JSONObject(jSONArray.getString(i2));
                    if ("phone".equals(jSONObject2.optString(MultiFactorInfo.FACTOR_ID_KEY))) {
                        phoneMultiFactorInfo = PhoneMultiFactorInfo.zza(jSONObject2);
                    } else {
                        phoneMultiFactorInfo = null;
                    }
                    arrayList2.add(phoneMultiFactorInfo);
                }
                zznVar.zzb(arrayList2);
            }
            return zznVar;
        } catch (zza | ArrayIndexOutOfBoundsException | IllegalArgumentException | JSONException e) {
            this.zzd.wtf(e);
            return null;
        }
    }
}
