package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.Collection;

/* loaded from: classes2.dex */
public class ApplicationWrapper extends Application {
    private static final String TAG = ApplicationWrapper.class.getSimpleName();
    private Application mBaseApp;

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        Application application = this.mBaseApp;
        return application != null ? application : this;
    }

    @Override // android.app.Application
    public void onCreate() {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 128);
            Bundle metaData = applicationInfo.metaData;
            boolean sandboxExternalStorage = metaData.getBoolean("com.applisto.appcloner.sandboxExternalStorage");
            String str = TAG;
            Log.i(str, "onCreate; sandboxExternalStorage: " + sandboxExternalStorage);
            if (sandboxExternalStorage) {
                Object o = DefaultProvider.ni(this, "com.applisto.appcloner.classes.secondary.SandboxExternalStorage");
                o.getClass().getMethod("init", Context.class).invoke(o, this);
            }
            this.mBaseApp = createApplication(applicationInfo);
            final Field baseField = ContextWrapper.class.getDeclaredField("mBase");
            baseField.setAccessible(true);
            final Context baseContext = new ContextWrapper(getBaseContext()) { // from class: com.applisto.appcloner.classes.ApplicationWrapper.1
                @Override // android.content.ContextWrapper, android.content.Context
                public Context getApplicationContext() {
                    return ApplicationWrapper.this.mBaseApp;
                }
            };
            baseField.set(this.mBaseApp, baseContext);
            super.onCreate();
            final Field componentCallbacksField = Application.class.getDeclaredField("mComponentCallbacks");
            componentCallbacksField.setAccessible(true);
            registerComponentCallbacks(new ComponentCallbacks() { // from class: com.applisto.appcloner.classes.ApplicationWrapper.2
                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration newConfig) {
                    try {
                        Collection<ComponentCallbacks> collection = (Collection) componentCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (ComponentCallbacks componentCallbacks : collection) {
                            componentCallbacks.onConfigurationChanged(newConfig);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                    try {
                        Collection<ComponentCallbacks> collection = (Collection) componentCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (ComponentCallbacks callbacks : collection) {
                            callbacks.onLowMemory();
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }
            });
            final Field activityLifecycleCallbacksField = Application.class.getDeclaredField("mActivityLifecycleCallbacks");
            activityLifecycleCallbacksField.setAccessible(true);
            registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.applisto.appcloner.classes.ApplicationWrapper.3
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivityCreated(activity, savedInstanceState);
                        }
                        baseField.set(activity, baseContext);
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivityStarted(activity);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivityResumed(activity);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivityPaused(activity);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivityStopped(activity);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivitySaveInstanceState(activity, outState);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    try {
                        Collection<Application.ActivityLifecycleCallbacks> collection = (Collection) activityLifecycleCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                        for (Application.ActivityLifecycleCallbacks callbacks : collection) {
                            callbacks.onActivityDestroyed(activity);
                        }
                    } catch (Exception e) {
                        Log.w(ApplicationWrapper.TAG, e);
                    }
                }
            });
            if (Build.VERSION.SDK_INT >= 18) {
                final Field assistCallbacksField = Application.class.getDeclaredField("mAssistCallbacks");
                assistCallbacksField.setAccessible(true);
                registerOnProvideAssistDataListener(new Application.OnProvideAssistDataListener() { // from class: com.applisto.appcloner.classes.ApplicationWrapper.4
                    @Override // android.app.Application.OnProvideAssistDataListener
                    public void onProvideAssistData(Activity activity, Bundle data) {
                        try {
                            Collection<Application.OnProvideAssistDataListener> collection = (Collection) assistCallbacksField.get(ApplicationWrapper.this.mBaseApp);
                            for (Application.OnProvideAssistDataListener listener : collection) {
                                listener.onProvideAssistData(activity, data);
                            }
                        } catch (Exception e) {
                            Log.w(ApplicationWrapper.TAG, e);
                        }
                    }
                });
            }
            this.mBaseApp.onCreate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Application createApplication(ApplicationInfo applicationInfo) throws Exception {
        Bundle metaData = applicationInfo.metaData;
        String applicationClassName = metaData.getString("com.applisto.appcloner.applicationClassName", "android.app.Application");
        String str = TAG;
        Log.i(str, "createApplication; applicationClassName: " + applicationClassName);
        Class<?> clazz = Class.forName(applicationClassName);
        return (Application) clazz.newInstance();
    }

    @Override // android.app.Application
    public void onTerminate() {
        Log.i(TAG, "onTerminate; ");
        super.onTerminate();
        this.mBaseApp.onTerminate();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        String str = TAG;
        Log.i(str, "onConfigurationChanged; newConfig.locale: " + newConfig.locale);
        super.onConfigurationChanged(newConfig);
        this.mBaseApp.onConfigurationChanged(newConfig);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        Log.i(TAG, "onLowMemory; ");
        super.onLowMemory();
        this.mBaseApp.onLowMemory();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        String str = TAG;
        Log.i(str, "onTrimMemory; level: " + level);
        super.onTrimMemory(level);
        this.mBaseApp.onTrimMemory(level);
    }
}
