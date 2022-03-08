package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;
import com.applisto.appcloner.classes.util.IPackageManagerHook;
import com.applisto.appcloner.hooking.Hooking;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes2.dex */
public class GmailSupport extends AbstractActivityContentProvider {
    private static final String TAG = GmailSupport.class.getSimpleName();
    private String mAppName;
    private String mErrorMessage;
    private Handler mHandler = new Handler();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void install(Context context) {
        Log.i(TAG, "install; ");
        try {
            onCreate();
            installPackageManagerHook(context);
            installMailIntentReceiverHook(context);
            this.mAppName = Utils.getAppName(context);
            String str = TAG;
            Log.i(str, "install; mAppName: " + this.mAppName);
            this.mErrorMessage = context.getString(context.getResources().getIdentifier("common_google_play_services_unknown_issue", "string", context.getPackageName()), this.mAppName);
            this.mErrorMessage = this.mErrorMessage.trim();
            String str2 = TAG;
            Log.i(str2, "install; mErrorMessage: " + this.mErrorMessage);
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityCreated(Activity activity) {
        Window window = activity.getWindow();
        window.setCallback(new WindowCallbackWrapper(window.getCallback()) { // from class: com.applisto.appcloner.classes.GmailSupport.1
            @Override // com.applisto.appcloner.classes.WindowCallbackWrapper, android.view.Window.Callback
            public void onContentChanged() {
                Log.i(GmailSupport.TAG, "onContentChanged; ");
                super.onContentChanged();
                GmailSupport.this.checkForDialog();
                GmailSupport.this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.GmailSupport.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GmailSupport.this.checkForDialog();
                    }
                }, 300L);
                GmailSupport.this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.GmailSupport.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GmailSupport.this.checkForDialog();
                    }
                }, 500L);
                GmailSupport.this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.GmailSupport.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        GmailSupport.this.checkForDialog();
                    }
                }, 750L);
            }
        });
        installContextHook(activity);
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected int getActivityTimerDelayMillis() {
        return 500;
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityTimer(Activity activity) {
        checkForDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkForDialog() {
        TextView messageView;
        CharSequence text;
        try {
            List<ViewParent> viewRoots = Utils.getViewRoots();
            for (ViewParent viewRoot : viewRoots) {
                try {
                    Field f = viewRoot.getClass().getDeclaredField("mView");
                    f.setAccessible(true);
                    View view = (View) f.get(viewRoot);
                    if (!(view.getVisibility() == 8 || (messageView = (TextView) view.findViewById(16908299)) == null || (text = messageView.getText()) == null)) {
                        String s = text.toString();
                        if (!TextUtils.isEmpty(s) && (s.contains(this.mErrorMessage) || (s.contains(this.mAppName) && s.contains("Google Play")))) {
                            view.setVisibility(8);
                            String str = TAG;
                            Log.i(str, "checkForDialog; view hidden; viewRoot: " + viewRoot + ", view: " + view);
                        }
                    }
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, e2);
        }
    }

    private void installPackageManagerHook(final Context context) {
        String str = TAG;
        Log.i(str, "installPackageManagerHook; context: " + context);
        try {
            new IPackageManagerHook() { // from class: com.applisto.appcloner.classes.GmailSupport.2
                @Override // com.applisto.appcloner.classes.util.IPackageManagerHook
                protected InvocationHandler getInvocationHandler(final Object originalPackageManager) {
                    return new InvocationHandler() { // from class: com.applisto.appcloner.classes.GmailSupport.2.1
                        @Override // java.lang.reflect.InvocationHandler
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            try {
                                String name = method.getName();
                                if ("getPackageInfo".equals(name)) {
                                    if ((context.getPackageName() + "s").equals(args[0])) {
                                        String str2 = GmailSupport.TAG;
                                        Log.i(str2, "invoke; getPackageInfo; fixing args[0]: " + args[0]);
                                        args[0] = "com.google.android.gms";
                                    }
                                } else if ("getApplicationInfo".equals(name)) {
                                    if ((context.getPackageName() + "s").equals(args[0])) {
                                        String str3 = GmailSupport.TAG;
                                        Log.i(str3, "invoke; getApplicationInfo; fixing args[0]: " + args[0]);
                                        args[0] = "com.google.android.gms";
                                    }
                                }
                            } catch (Exception e) {
                                Log.w(GmailSupport.TAG, e);
                            }
                            return method.invoke(originalPackageManager, args);
                        }
                    };
                }
            }.install(context);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private void installContextHook(Context context) {
        String str = TAG;
        Log.i(str, "installContextHook; context: " + context);
        try {
            Field baseField = ContextWrapper.class.getDeclaredField("mBase");
            baseField.setAccessible(true);
            Context oldContext = context;
            while (context instanceof ContextWrapper) {
                oldContext = context;
                context = (Context) baseField.get(context);
            }
            String myPackageName = context.getPackageName();
            ContextWrapper contextWrapper = new AnonymousClass3(context, myPackageName);
            baseField.set(oldContext, contextWrapper);
            Log.i(TAG, "installed; installed context wrapper");
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.applisto.appcloner.classes.GmailSupport$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 extends ContextWrapper {
        final /* synthetic */ String val$myPackageName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Context x0, String str) {
            super(x0);
            this.val$myPackageName = str;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
            String str = GmailSupport.TAG;
            Log.i(str, "createPackageContext; packageName: " + packageName);
            if (!(this.val$myPackageName + "s").equals(packageName)) {
                return super.createPackageContext(packageName, flags);
            }
            String str2 = GmailSupport.TAG;
            Log.i(str2, "createPackageContext; replacing remote context; packageName: " + packageName);
            return new ContextWrapper(super.createPackageContext("com.google.android.gms", flags)) { // from class: com.applisto.appcloner.classes.GmailSupport.3.1
                private ClassLoader mClassLoader;

                @Override // android.content.ContextWrapper, android.content.Context
                public ClassLoader getClassLoader() {
                    if (this.mClassLoader == null) {
                        this.mClassLoader = new ClassLoader(super.getClassLoader()) { // from class: com.applisto.appcloner.classes.GmailSupport.3.1.1
                            @Override // java.lang.ClassLoader
                            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                                String str3 = GmailSupport.TAG;
                                Log.i(str3, "loadClass; name: " + name + ", resolve: " + resolve);
                                if ((AnonymousClass3.this.val$myPackageName + "s.common.security.ProviderInstallerImpl").equals(name)) {
                                    String str4 = GmailSupport.TAG;
                                    Log.i(str4, "loadClass; replacing class name: " + name);
                                    name = "com.google.android.gms.common.security.ProviderInstallerImpl";
                                }
                                return super.loadClass(name, resolve);
                            }
                        };
                    }
                    return this.mClassLoader;
                }
            };
        }
    }

    private void installMailIntentReceiverHook(Context context) {
        Log.i(TAG, "installMailIntentReceiverHook; ");
        Hooking.initHooking(context);
        Hooking.addHookClass(Hook.class);
        Log.i(TAG, "installMailIntentReceiverHook; hooks installed");
    }

    @HookReflectClass(".MailIntentReceiver")
    /* loaded from: classes2.dex */
    public static class Hook {
        @MethodParams({Context.class, Intent.class})
        @HookMethod("onReceive")
        public static void onReceiveHook(Object thiz, Context context, Intent intent) {
            Log.i(GmailSupport.TAG, "onReceiveHook; ");
        }
    }
}
