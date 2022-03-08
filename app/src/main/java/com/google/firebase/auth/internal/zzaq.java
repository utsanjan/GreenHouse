package com.google.firebase.auth.internal;

import andhook.lib.xposed.ClassUtils;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.firebase_auth.zzau;
import com.google.android.gms.internal.firebase_auth.zzbl;
import com.google.firebase.auth.api.zza;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzaq {
    private static final Logger zza = new Logger("JSONParser", new String[0]);

    public static Map<String, Object> zza(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> zza2 = zzau.zza((char) ClassUtils.PACKAGE_SEPARATOR_CHAR).zza((CharSequence) str);
        if (zza2.size() < 2) {
            Logger logger = zza;
            String valueOf = String.valueOf(str);
            logger.e(valueOf.length() != 0 ? "Invalid idToken ".concat(valueOf) : new String("Invalid idToken "), new Object[0]);
            return zzbl.zza();
        }
        try {
            Map<String, Object> zzb = zzb(new String(Base64Utils.decodeUrlSafeNoPadding(zza2.get(1)), "UTF-8"));
            return zzb == null ? zzbl.zza() : zzb;
        } catch (UnsupportedEncodingException e) {
            zza.e("Unable to decode token", e, new Object[0]);
            return zzbl.zza();
        }
    }

    public static Map<String, Object> zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return zza(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Log.d("JSONParser", "Failed to parse JSONObject into Map.");
            throw new zza(e);
        }
    }

    private static Map<String, Object> zza(JSONObject jSONObject) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zza((JSONObject) obj);
            }
            arrayMap.put(next, obj);
        }
        return arrayMap;
    }

    private static List<Object> zza(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zza((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
