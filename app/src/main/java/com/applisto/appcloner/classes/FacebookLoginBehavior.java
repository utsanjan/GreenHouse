package com.applisto.appcloner.classes;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class FacebookLoginBehavior {
    private static final String TAG = FacebookLoginBehavior.class.getSimpleName();
    private final String mFacebookLoginBehavior;

    public FacebookLoginBehavior(CloneSettings cloneSettings) {
        this.mFacebookLoginBehavior = cloneSettings.getString("facebookLoginBehavior", null);
        String str = TAG;
        Log.i(str, "FacebookLoginBehavior; mFacebookLoginBehavior: " + this.mFacebookLoginBehavior);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void init(Context context) {
        if (!TextUtils.isEmpty(this.mFacebookLoginBehavior)) {
            try {
                Class<?> clazz = Class.forName("com.facebook.login.LoginBehavior");
                Object[] objects = clazz.getEnumConstants();
                for (Object object : objects) {
                    if ("WEB_ONLY".equals(this.mFacebookLoginBehavior) || "WEB_ONLY_ALT".equals(this.mFacebookLoginBehavior)) {
                        setField(object, "allowsGetTokenAuth", false);
                        setField(object, "allowsKatanaAuth", false);
                        setField(object, "allowsWebViewAuth", true);
                        setField(object, "allowsDeviceAuth", false);
                        setField(object, "allowsCustomTabAuth", true);
                        setField(object, "allowsFacebookLiteAuth", false);
                    }
                }
            } catch (ClassNotFoundException e) {
            } catch (Throwable t) {
                Log.w(TAG, t);
            }
        }
    }

    private static void setField(Object o, String fieldName, Object value) {
        try {
            Field f = o.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(o, value);
        } catch (Exception e) {
        }
    }
}
