package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class Slashes {
    public static String preserveSlashEncode(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return slashize(Uri.encode(s));
    }

    public static String slashize(String s) {
        Preconditions.checkNotNull(s);
        return s.replace("%2F", "/");
    }

    public static String normalizeSlashes(String uriSegment) {
        String[] split;
        if (TextUtils.isEmpty(uriSegment)) {
            return "";
        }
        if (!(uriSegment.startsWith("/") || uriSegment.endsWith("/") || uriSegment.contains("//"))) {
            return uriSegment;
        }
        StringBuilder result = new StringBuilder();
        for (String stringSegment : uriSegment.split("/", -1)) {
            if (!TextUtils.isEmpty(stringSegment)) {
                if (result.length() > 0) {
                    result.append("/");
                    result.append(stringSegment);
                } else {
                    result.append(stringSegment);
                }
            }
        }
        return result.toString();
    }
}
