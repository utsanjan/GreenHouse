package com.applisto.appcloner.classes;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes2.dex */
public class FacebookMessengerProvider extends AbstractContentProvider {
    private static final String TAG = FacebookMessengerProvider.class.getSimpleName();

    @Override // com.applisto.appcloner.classes.AbstractContentProvider, android.content.ContentProvider
    public boolean onCreate() {
        try {
            AccountManager accountManager = AccountManager.get(getContext());
            Field field = accountManager.getClass().getDeclaredField("mService");
            field.setAccessible(true);
            final Object originalService = field.get(accountManager);
            InvocationHandler invocationHandler = new InvocationHandler() { // from class: com.applisto.appcloner.classes.FacebookMessengerProvider.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if ("getAccountsAsUser".equals(method.getName())) {
                        return new Account[0];
                    }
                    if ("addAccountExplicitly".equals(method.getName())) {
                        return false;
                    }
                    return method.invoke(originalService, args);
                }
            };
            Class<?> clazz = Class.forName("android.accounts.IAccountManager");
            Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, invocationHandler);
            field.set(accountManager, proxy);
            String str = TAG;
            Log.i(str, "onCreate; installed proxy; proxy: " + proxy);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return true;
    }
}
