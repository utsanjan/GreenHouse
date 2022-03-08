package com.applisto.appcloner.classes;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.applisto.appcloner.classes.util.IPackageManagerHook;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.security.cert.X509Certificate;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Signatures {
    private static final String TAG = Signatures.class.getSimpleName();
    private final boolean mDisableShareActions;
    private final String mFacebookLoginBehavior;
    private final String mTwitterLoginBehavior;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Signatures(CloneSettings cloneSettings) {
        this.mFacebookLoginBehavior = cloneSettings.getString("facebookLoginBehavior", null);
        this.mTwitterLoginBehavior = cloneSettings.getString("twitterLoginBehavior", null);
        this.mDisableShareActions = cloneSettings.getBoolean("disableShareActions", false).booleanValue();
        String str = TAG;
        Log.i(str, "Signatures; mFacebookLoginBehavior: " + this.mFacebookLoginBehavior + ", mTwitterLoginBehavior: " + this.mTwitterLoginBehavior + ", mDisableShareActions: " + this.mDisableShareActions);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(Context context) {
        Throwable t;
        ApplicationInfo originalApplicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            final String packageName = context.getPackageName();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 128);
            final Integer uid = Integer.valueOf(applicationInfo.uid);
            Bundle metaData = applicationInfo.metaData;
            final int originalUid = 0;
            final String originalPackageName = new String(Base64.decode(metaData.getString("com.applisto.appcloner.originalPackageName"), 0));
            try {
                originalApplicationInfo = packageManager.getApplicationInfo(originalPackageName, 0);
            } catch (Exception e) {
                originalApplicationInfo = null;
            }
            if (originalApplicationInfo != null) {
                originalUid = originalApplicationInfo.uid;
            }
            String signaturesString = metaData.getString("com.applisto.appcloner.originalSignatures");
            Log.i(TAG, "init; signaturesString: " + signaturesString);
            final Signature[] originalSignatures = unmarshallSignatures(signaturesString);
            if (originalSignatures != null) {
                Log.i(TAG, "init; packageName: " + packageName + ", uid: " + uid + ", originalPackageName: " + originalPackageName + ", originalUid: " + originalUid + ", originalSignatures:\n" + signaturesToString(originalSignatures));
                try {
                    new IPackageManagerHook() { // from class: com.applisto.appcloner.classes.Signatures.1
                        @Override // com.applisto.appcloner.classes.util.IPackageManagerHook
                        protected InvocationHandler getInvocationHandler(final Object originalPackageManager) {
                            return new InvocationHandler() { // from class: com.applisto.appcloner.classes.Signatures.1.1
                                @Override // java.lang.reflect.InvocationHandler
                                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                    ResolveInfo resolveInfo;
                                    try {
                                        String name = method.getName();
                                        if ("checkSignatures".equals(name)) {
                                            if (packageName.equals(args[0])) {
                                                args[0] = originalPackageName;
                                            } else if (uid.equals(args[0])) {
                                                args[0] = Integer.valueOf(originalUid);
                                            }
                                            if (packageName.equals(args[1])) {
                                                args[1] = originalPackageName;
                                            } else if (uid.equals(args[1])) {
                                                args[1] = Integer.valueOf(originalUid);
                                            }
                                        } else if ("getInstallerPackageName".equals(name)) {
                                            return new String(Base64.decode("Y29tLmFuZHJvaWQudmVuZGluZw==", 0));
                                        }
                                    } catch (Exception e2) {
                                        Log.w(Signatures.TAG, e2);
                                    }
                                    Object res = method.invoke(originalPackageManager, args);
                                    try {
                                        String name2 = method.getName();
                                        if ("getPackageInfo".equals(name2)) {
                                            if (res != null) {
                                                PackageInfo packageInfo = (PackageInfo) res;
                                                if ("WEB_ONLY".equals(Signatures.this.mFacebookLoginBehavior) && ("com.facebook.katana".equals(packageInfo.packageName) || "com.facebook.lite".equals(packageInfo.packageName) || "com.facebook.services".equals(packageInfo.packageName))) {
                                                    Log.i(Signatures.TAG, "invoke; getPackageInfo; returning null for Facebook packages");
                                                    return null;
                                                } else if ("WEB_ONLY".equals(Signatures.this.mTwitterLoginBehavior) && "com.twitter.android".equals(packageInfo.packageName)) {
                                                    Log.i(Signatures.TAG, "invoke; getPackageInfo; returning null for Twitter package");
                                                    return null;
                                                } else if (packageName.equals(packageInfo.packageName) && packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                                                    packageInfo.signatures = originalSignatures;
                                                }
                                            }
                                        } else if ("getApplicationInfo".equals(name2)) {
                                            if (res != null) {
                                                ApplicationInfo applicationInfo2 = (ApplicationInfo) res;
                                                if ("WEB_ONLY".equals(Signatures.this.mFacebookLoginBehavior) && ("com.facebook.katana".equals(applicationInfo2.packageName) || "com.facebook.lite".equals(applicationInfo2.packageName) || "com.facebook.services".equals(applicationInfo2.packageName))) {
                                                    Log.i(Signatures.TAG, "invoke; getApplicationInfo; returning null for Facebook packages");
                                                    return null;
                                                } else if (!"WEB_ONLY".equals(Signatures.this.mTwitterLoginBehavior) || !"com.twitter.android".equals(applicationInfo2.packageName)) {
                                                    applicationInfo2.flags &= -3;
                                                } else {
                                                    Log.i(Signatures.TAG, "invoke; getApplicationInfo; returning null for Twitter package");
                                                    return null;
                                                }
                                            }
                                        } else if ("queryIntentActivities".equals(name2)) {
                                            if (Signatures.this.mDisableShareActions) {
                                                List<ResolveInfo> resolveInfos = getResolveInfos(res);
                                                resolveInfos.clear();
                                            } else if ("WEB_ONLY".equals(Signatures.this.mFacebookLoginBehavior) || "WEB_ONLY_ALT".equals(Signatures.this.mFacebookLoginBehavior)) {
                                                List<ResolveInfo> resolveInfos2 = getResolveInfos(res);
                                                Iterator<ResolveInfo> it = resolveInfos2.iterator();
                                                while (it.hasNext()) {
                                                    ResolveInfo resolveInfo2 = it.next();
                                                    if (resolveInfo2 != null && resolveInfo2.toString().contains("com.facebook.katana/.ProxyAuth")) {
                                                        it.remove();
                                                    }
                                                }
                                            }
                                        } else if ("resolveIntent".equals(name2) && (("WEB_ONLY".equals(Signatures.this.mFacebookLoginBehavior) || "WEB_ONLY_ALT".equals(Signatures.this.mFacebookLoginBehavior)) && (resolveInfo = (ResolveInfo) res) != null && resolveInfo.toString().contains("com.facebook.katana/.ProxyAuth"))) {
                                            Log.i(Signatures.TAG, "invoke; resolveIntent; returning no resolved intent for Facebook");
                                            return null;
                                        }
                                    } catch (Exception e3) {
                                        Log.w(Signatures.TAG, e3);
                                    }
                                    return res;
                                }

                                private List<ResolveInfo> getResolveInfos(Object res) {
                                    try {
                                        if ("android.content.pm.ParceledListSlice".equals(res.getClass().getName())) {
                                            Class<?> clazz = Build.VERSION.SDK_INT >= 26 ? res.getClass().getSuperclass() : res.getClass();
                                            Field f = clazz.getDeclaredField("mList");
                                            f.setAccessible(true);
                                            List<ResolveInfo> resolveInfos = (List) f.get(res);
                                            return resolveInfos;
                                        }
                                        List<ResolveInfo> resolveInfos2 = (List) res;
                                        return resolveInfos2;
                                    } catch (Exception e2) {
                                        Log.w(Signatures.TAG, e2);
                                        return Collections.EMPTY_LIST;
                                    }
                                }
                            };
                        }
                    }.install(context);
                } catch (Throwable th) {
                    t = th;
                    Log.w(TAG, t);
                }
            }
        } catch (Throwable th2) {
            t = th2;
        }
    }

    private static Signature[] unmarshallSignatures(String s) {
        try {
            byte[] bytes = Base64.decode(s, 2);
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Parcelable[] parcelables = parcel.readParcelableArray(Signatures.class.getClassLoader());
            Signature[] signatures = new Signature[parcelables.length];
            System.arraycopy(parcelables, 0, signatures, 0, parcelables.length);
            parcel.recycle();
            return signatures;
        } catch (Exception e) {
            Log.w(TAG, e);
            return null;
        }
    }

    private static String signaturesToString(Signature[] signatures) {
        List<String> list = new ArrayList<>();
        if (signatures != null) {
            for (Signature signature : signatures) {
                try {
                    X509Certificate certificate = X509Certificate.getInstance(signature.toByteArray());
                    list.add("  " + Base64.encodeToString(certificate.getEncoded(), 2));
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
            }
        }
        return TextUtils.join("\n", list);
    }
}
