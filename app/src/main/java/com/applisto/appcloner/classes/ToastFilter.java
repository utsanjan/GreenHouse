package com.applisto.appcloner.classes;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes2.dex */
public class ToastFilter {
    private static final String TAG = ToastFilter.class.getSimpleName();
    private final boolean mBlockAllToasts;
    private final String mToastFilter;

    public ToastFilter(CloneSettings cloneSettings) {
        this.mBlockAllToasts = cloneSettings.getBoolean("blockAllToasts", false).booleanValue();
        this.mToastFilter = cloneSettings.getString("toastFilter", null);
        String str = TAG;
        Log.i(str, "ToastFilter; mBlockAllToasts: " + this.mBlockAllToasts + ", mToastFilter: " + this.mToastFilter);
    }

    public void init(Context context) {
        Log.i(TAG, "init; ");
        if (this.mBlockAllToasts || !TextUtils.isEmpty(this.mToastFilter)) {
            try {
                final Set<String> toastFilterSet = new HashSet<>();
                if (!TextUtils.isEmpty(this.mToastFilter)) {
                    for (String s : this.mToastFilter.trim().split(",")) {
                        String s2 = s.trim();
                        if (!TextUtils.isEmpty(s2)) {
                            toastFilterSet.add(s2.toLowerCase(Locale.ENGLISH));
                        }
                    }
                }
                new Toast(context).cancel();
                Method m = Toast.class.getDeclaredMethod("getService", new Class[0]);
                m.setAccessible(true);
                m.invoke(null, new Object[0]);
                Field field = Toast.class.getDeclaredField("sService");
                field.setAccessible(true);
                final Object originalService = field.get(null);
                InvocationHandler invocationHandler = new InvocationHandler() { // from class: com.applisto.appcloner.classes.ToastFilter.1
                    @Override // java.lang.reflect.InvocationHandler
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("enqueueToast".equals(method.getName())) {
                            if (ToastFilter.this.mBlockAllToasts) {
                                Log.i(ToastFilter.TAG, "invoke; enqueueToast blocked");
                                return null;
                            }
                            String s3 = getToastText(args[1]).toLowerCase(Locale.ENGLISH);
                            for (String toastFilterString : toastFilterSet) {
                                if (s3.contains(toastFilterString)) {
                                    Log.i(ToastFilter.TAG, "invoke; enqueueToast blocked");
                                    return null;
                                }
                            }
                        }
                        return method.invoke(originalService, args);
                    }

                    private String getToastText(Object arg1) {
                        TextView textView;
                        CharSequence text;
                        try {
                            Field field2 = arg1.getClass().getDeclaredField("mNextView");
                            field2.setAccessible(true);
                            View view = (View) field2.get(arg1);
                            if (view == null || (textView = (TextView) view.findViewById(16908299)) == null || (text = textView.getText()) == null) {
                                return "";
                            }
                            return text.toString();
                        } catch (Exception e) {
                            Log.w(ToastFilter.TAG, e);
                            return "";
                        }
                    }
                };
                Class<?> clazz = Class.forName("android.app.INotificationManager");
                Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, invocationHandler);
                field.set(null, proxy);
                Log.i(TAG, "init; installed proxy; proxy: " + proxy);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }
}
