package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class NotificationOptions extends OnAppExitListener {
    private static final String ACTION_SNOOZE_NOTIFICATION = "com.applisto.appcloner.action.SNOOZE_NOTIFICATION";
    private static final String EXTRA_SNOOZE_ACTION = "snooze_action";
    private static final String TAG = NotificationOptions.class.getSimpleName();
    private static boolean mAllowNotificationsWhenRunning;
    private static boolean mBlockAllNotifications;
    private static Integer mNotificationColor;
    private boolean mHeadsUpNotifications;
    private Icon mIcon;
    private boolean mLocalOnlyNotifications;
    private boolean mNoOngoingNotifications;
    private String mNotificationLightsColor;
    private String mNotificationLightsPattern;
    private String mNotificationPriority;
    private boolean mNotificationQuietTime;
    private int mNotificationQuietTimeEndHour;
    private int mNotificationQuietTimeEndMinute;
    private int mNotificationQuietTimeStartHour;
    private int mNotificationQuietTimeStartMinute;
    private int mNotificationSnoozeTimeout;
    private String mNotificationSound;
    private int mNotificationTimeout;
    private boolean mNotificationTintStatusBarIcon;
    private String mNotificationVibration;
    private String mNotificationVisibility;
    private boolean mRemoveNotificationActions;
    private boolean mRemoveNotificationIcon;
    private boolean mRemoveNotificationPeople;
    private boolean mReplaceNotificationIcon;
    private boolean mRunning;
    private boolean mShowNotificationTime;
    private boolean mSimpleNotifications;
    private String mSingleNotificationCategory;
    private List<Map<String, Object>> mNotificationCategories = new ArrayList();
    private List<Map<String, Object>> mNotificationTextReplacements = new ArrayList();
    private Handler mTimeoutHandler = new Handler();
    private Map<Integer, Runnable> mTimeoutRunnables = new HashMap();
    private Map<Integer, Notification> mSnoozeNotifications = new HashMap();
    private final Set<String> mNotificationFilterSet = new HashSet();

    public NotificationOptions(CloneSettings cloneSettings) {
        mBlockAllNotifications = cloneSettings.getBoolean("blockAllNotifications", false).booleanValue();
        mAllowNotificationsWhenRunning = cloneSettings.getBoolean("allowNotificationsWhenRunning", false).booleanValue();
        String notificationFilter = cloneSettings.getString("notificationFilter", null);
        if (!TextUtils.isEmpty(notificationFilter)) {
            for (String s : notificationFilter.trim().split(",")) {
                String s2 = s.trim();
                if (!TextUtils.isEmpty(s2)) {
                    this.mNotificationFilterSet.add(s2.toLowerCase(Locale.ENGLISH));
                }
            }
        }
        this.mNotificationQuietTime = cloneSettings.getBoolean("notificationQuietTime", false).booleanValue();
        try {
            String[] arr = cloneSettings.getString("notificationQuietTimeStart", "21:00").split(":");
            this.mNotificationQuietTimeStartHour = Integer.parseInt(arr[0]);
            this.mNotificationQuietTimeStartMinute = Integer.parseInt(arr[1]);
            String[] arr2 = cloneSettings.getString("notificationQuietTimeEnd", "07:00").split(":");
            this.mNotificationQuietTimeEndHour = Integer.parseInt(arr2[0]);
            this.mNotificationQuietTimeEndMinute = Integer.parseInt(arr2[1]);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        if (cloneSettings.getBoolean("notificationColorUseStatusBarColor", false).booleanValue()) {
            mNotificationColor = cloneSettings.getInteger("statusBarColor", null);
        } else {
            mNotificationColor = cloneSettings.getInteger("notificationColor", null);
        }
        this.mNotificationTintStatusBarIcon = cloneSettings.getBoolean("notificationTintStatusBarIcon", false).booleanValue();
        this.mNotificationSound = cloneSettings.getString("notificationSound", "NO_CHANGE");
        this.mNotificationVibration = cloneSettings.getString("notificationVibration", "NO_CHANGE");
        this.mNotificationTimeout = cloneSettings.getInteger("notificationTimeout", 0).intValue();
        this.mNotificationSnoozeTimeout = cloneSettings.getInteger("notificationSnoozeTimeout", 0).intValue();
        this.mHeadsUpNotifications = cloneSettings.getBoolean("headsUpNotifications", false).booleanValue();
        this.mLocalOnlyNotifications = cloneSettings.getBoolean("localOnlyNotifications", false).booleanValue();
        this.mNoOngoingNotifications = cloneSettings.getBoolean("noOngoingNotifications", false).booleanValue();
        this.mShowNotificationTime = cloneSettings.getBoolean("showNotificationTime", false).booleanValue();
        this.mNotificationLightsPattern = cloneSettings.forObject("defaultNotificationLights").getString("notificationLightsPattern", "NO_CHANGE");
        this.mNotificationLightsColor = cloneSettings.forObject("defaultNotificationLights").getString("notificationLightsColor", "NO_CHANGE");
        this.mNotificationVisibility = cloneSettings.getString("notificationVisibility", "NO_CHANGE");
        this.mNotificationPriority = cloneSettings.getString("notificationPriority", "NO_CHANGE");
        this.mReplaceNotificationIcon = cloneSettings.getBoolean("replaceNotificationIcon", false).booleanValue();
        this.mRemoveNotificationIcon = cloneSettings.getBoolean("removeNotificationIcon", false).booleanValue();
        this.mRemoveNotificationActions = cloneSettings.getBoolean("removeNotificationActions", false).booleanValue();
        this.mRemoveNotificationPeople = cloneSettings.getBoolean("removeNotificationPeople", false).booleanValue();
        this.mSimpleNotifications = cloneSettings.getBoolean("simpleNotifications", false).booleanValue();
        List<CloneSettings> notificationCategories = cloneSettings.forObjectArray("notificationCategories");
        if (notificationCategories != null) {
            for (CloneSettings notificationCategory : notificationCategories) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", notificationCategory.getString("name", ""));
                map.put("keywords", notificationCategory.getString("keywords", ""));
                map.put("ignoreCase", notificationCategory.getBoolean("ignoreCase", true));
                this.mNotificationCategories.add(map);
            }
        }
        this.mSingleNotificationCategory = cloneSettings.getString("singleNotificationCategory", null);
        List<CloneSettings> notificationTextReplacements = cloneSettings.forObjectArray("notificationTextReplacements");
        if (notificationTextReplacements != null) {
            for (CloneSettings notificationTextReplacement : notificationTextReplacements) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("replace", notificationTextReplacement.getString("replace", ""));
                map2.put("with", notificationTextReplacement.getString("with", ""));
                map2.put("ignoreCase", notificationTextReplacement.getBoolean("ignoreCase", true));
                map2.put("replaceInTitle", notificationTextReplacement.getBoolean("replaceInTitle", true));
                map2.put("replaceInContent", notificationTextReplacement.getBoolean("replaceInContent", true));
                map2.put("replaceInMessages", notificationTextReplacement.getBoolean("replaceInMessages", true));
                map2.put("replaceInActions", notificationTextReplacement.getBoolean("replaceInActions", true));
                this.mNotificationTextReplacements.add(map2);
            }
        }
        Log.i(TAG, "NotificationOptions; mBlockAllNotifications: " + mBlockAllNotifications);
        Log.i(TAG, "NotificationOptions; mAllowNotificationsWhenRunning: " + mAllowNotificationsWhenRunning);
        Log.i(TAG, "NotificationOptions; mNotificationFilterSet: " + this.mNotificationFilterSet);
        Log.i(TAG, "NotificationOptions; mNotificationQuietTime: " + this.mNotificationQuietTime);
        Log.i(TAG, "NotificationOptions; mNotificationQuietTimeStartHour: " + this.mNotificationQuietTimeStartHour);
        Log.i(TAG, "NotificationOptions; mNotificationQuietTimeStartMinute: " + this.mNotificationQuietTimeStartMinute);
        Log.i(TAG, "NotificationOptions; mNotificationQuietTimeEndHour: " + this.mNotificationQuietTimeEndHour);
        Log.i(TAG, "NotificationOptions; mNotificationQuietTimeEndMinute: " + this.mNotificationQuietTimeEndMinute);
        Log.i(TAG, "NotificationOptions; mNotificationColor: " + mNotificationColor);
        Log.i(TAG, "NotificationOptions; mNotificationTintStatusBarIcon: " + this.mNotificationTintStatusBarIcon);
        Log.i(TAG, "NotificationOptions; mNotificationSound: " + this.mNotificationSound);
        Log.i(TAG, "NotificationOptions; mNotificationVibration: " + this.mNotificationVibration);
        Log.i(TAG, "NotificationOptions; mNotificationTimeout: " + this.mNotificationTimeout);
        Log.i(TAG, "NotificationOptions; mNotificationSnoozeTimeout: " + this.mNotificationSnoozeTimeout);
        Log.i(TAG, "NotificationOptions; mHeadsUpNotifications: " + this.mHeadsUpNotifications);
        Log.i(TAG, "NotificationOptions; mLocalOnlyNotifications: " + this.mLocalOnlyNotifications);
        Log.i(TAG, "NotificationOptions; mNoOngoingNotifications: " + this.mNoOngoingNotifications);
        Log.i(TAG, "NotificationOptions; mShowNotificationTime: " + this.mShowNotificationTime);
        Log.i(TAG, "NotificationOptions; mNotificationLightsPattern: " + this.mNotificationLightsPattern);
        Log.i(TAG, "NotificationOptions; mNotificationLightsColor: " + this.mNotificationLightsColor);
        Log.i(TAG, "NotificationOptions; mNotificationVisibility: " + this.mNotificationVisibility);
        Log.i(TAG, "NotificationOptions; mNotificationPriority: " + this.mNotificationPriority);
        Log.i(TAG, "NotificationOptions; mReplaceNotificationIcon: " + this.mReplaceNotificationIcon);
        Log.i(TAG, "NotificationOptions; mRemoveNotificationIcon: " + this.mRemoveNotificationIcon);
        Log.i(TAG, "NotificationOptions; mRemoveNotificationActions: " + this.mRemoveNotificationActions);
        Log.i(TAG, "NotificationOptions; mRemoveNotificationPeople: " + this.mRemoveNotificationPeople);
        Log.i(TAG, "NotificationOptions; mSimpleNotifications: " + this.mSimpleNotifications);
        Log.i(TAG, "NotificationOptions; mNotificationCategories: " + this.mNotificationCategories);
        Log.i(TAG, "NotificationOptions; mSingleNotificationCategory: " + this.mSingleNotificationCategory);
        Log.i(TAG, "NotificationOptions; mNotificationTextReplacements: " + this.mNotificationTextReplacements);
    }

    public void init(Context context) {
        if (mNotificationColor != null || mBlockAllNotifications || !this.mNotificationFilterSet.isEmpty() || this.mNotificationQuietTime || !"NO_CHANGE".equals(this.mNotificationSound) || !"NO_CHANGE".equals(this.mNotificationVibration) || this.mNotificationTimeout != 0 || this.mNotificationSnoozeTimeout != 0 || this.mHeadsUpNotifications || this.mLocalOnlyNotifications || this.mNoOngoingNotifications || this.mShowNotificationTime || !"NO_CHANGE".equals(this.mNotificationLightsPattern) || !"NO_CHANGE".equals(this.mNotificationLightsColor) || !"NO_CHANGE".equals(this.mNotificationVisibility) || !"NO_CHANGE".equals(this.mNotificationPriority) || this.mReplaceNotificationIcon || this.mRemoveNotificationIcon || this.mRemoveNotificationActions || this.mRemoveNotificationPeople || this.mSimpleNotifications || !this.mNotificationCategories.isEmpty() || !TextUtils.isEmpty(this.mSingleNotificationCategory) || !this.mNotificationTextReplacements.isEmpty()) {
            onCreate();
            install(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(Activity activity) {
        super.onActivityCreated(activity);
        this.mRunning = true;
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        Log.i(TAG, "onAppExit; ");
        this.mRunning = false;
    }

    public void install(final Context context) {
        Log.i(TAG, "install; ");
        try {
            Field serviceField = NotificationManager.class.getDeclaredField("sService");
            serviceField.setAccessible(true);
            if (serviceField.get(null) != null) {
                Log.i(TAG, "onCreate; sService already initialized!!!");
            } else {
                Log.i(TAG, "onCreate; sService == null");
            }
            NotificationManager.class.getMethod("getService", new Class[0]).invoke(null, new Object[0]);
            final Object instance = serviceField.get(null);
            Class<?> inf = Class.forName("android.app.INotificationManager");
            Object proxy = Proxy.newProxyInstance(NotificationOptions.class.getClassLoader(), new Class[]{inf}, new InvocationHandler() { // from class: com.applisto.appcloner.classes.NotificationOptions.1
                /* JADX WARN: Removed duplicated region for block: B:250:0x098f  */
                /* JADX WARN: Removed duplicated region for block: B:263:0x09dd  */
                /* JADX WARN: Removed duplicated region for block: B:309:0x0ac4 A[Catch: Exception -> 0x0b32, TRY_LEAVE, TryCatch #7 {Exception -> 0x0b32, blocks: (B:306:0x0ab4, B:307:0x0abe, B:309:0x0ac4), top: B:352:0x0ab4 }] */
                /* JADX WARN: Removed duplicated region for block: B:348:0x0a0d A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // java.lang.reflect.InvocationHandler
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object invoke(java.lang.Object r28, java.lang.reflect.Method r29, java.lang.Object[] r30) throws java.lang.Throwable {
                    /*
                        Method dump skipped, instructions count: 2997
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.applisto.appcloner.classes.NotificationOptions.AnonymousClass1.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
                }
            });
            serviceField.set(null, proxy);
            if (this.mNotificationSnoozeTimeout > 0) {
                context.registerReceiver(new BroadcastReceiver() { // from class: com.applisto.appcloner.classes.NotificationOptions.2
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context2, Intent intent) {
                        try {
                            final int id = intent.getIntExtra("id", 0);
                            final Notification notification = (Notification) NotificationOptions.this.mSnoozeNotifications.remove(Integer.valueOf(id));
                            if (notification != null) {
                                final NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
                                notificationManager.cancel(id);
                                Runnable runnable = (Runnable) NotificationOptions.this.mTimeoutRunnables.get(Integer.valueOf(id));
                                if (runnable != null) {
                                    NotificationOptions.this.mTimeoutHandler.removeCallbacks(runnable);
                                }
                                Runnable runnable2 = new Runnable() { // from class: com.applisto.appcloner.classes.NotificationOptions.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        try {
                                            notificationManager.notify(id, notification);
                                        } catch (Throwable t) {
                                            Log.w(NotificationOptions.TAG, t);
                                        }
                                    }
                                };
                                NotificationOptions.this.mTimeoutHandler.postDelayed(runnable2, NotificationOptions.this.mNotificationSnoozeTimeout * 1000);
                                NotificationOptions.this.mTimeoutRunnables.put(Integer.valueOf(id), runnable2);
                            }
                        } catch (Throwable t) {
                            Log.w(NotificationOptions.TAG, t);
                        }
                    }
                }, new IntentFilter(ACTION_SNOOZE_NOTIFICATION));
            }
            if (this.mReplaceNotificationIcon && Build.VERSION.SDK_INT >= 23) {
                try {
                    byte[] bytes = Utils.readFully(context.getAssets().open(".notificationIconFile"), true);
                    this.mIcon = Icon.createWithData(bytes, 0, bytes.length);
                    String str = TAG;
                    Log.i(str, "install; mIcon: " + this.mIcon);
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAppClonerClassesNotification() {
        StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
        boolean notificationManager = false;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            String className = stackTraceElement.getClassName();
            if ("android.app.NotificationManager".equals(className)) {
                notificationManager = true;
            } else if (notificationManager) {
                return className.startsWith(DefaultProvider.PREF_KEY_PREFIX);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle getExtras(Notification notification) {
        Bundle extras = null;
        try {
            extras = notification.extras;
        } catch (Throwable th) {
            try {
                Field f = Notification.class.getDeclaredField("extras");
                f.setAccessible(true);
                extras = (Bundle) f.get(notification);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        if (extras != null) {
            return extras;
        }
        Bundle extras2 = new Bundle();
        return extras2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNotificationChannelId(Context context, String text) {
        String channelName = getNotificationChannelName(text);
        if (channelName == null) {
            return null;
        }
        String channelId = "app_cloner_" + Math.abs(channelName.hashCode());
        Log.i(TAG, "getNotificationChannelId; channelId: " + channelId + ", channelName: " + channelName);
        NotificationChannel channel = new NotificationChannel(channelId, channelName, 3);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }

    private String getNotificationChannelName(String text) {
        if (!TextUtils.isEmpty(this.mSingleNotificationCategory)) {
            Log.i(TAG, "getNotificationChannelName; returning mSingleNotificationCategory: " + this.mSingleNotificationCategory);
            return this.mSingleNotificationCategory;
        }
        for (Map<String, Object> notificationCategory : this.mNotificationCategories) {
            String name = (String) notificationCategory.get("name");
            if (!TextUtils.isEmpty(name)) {
                String keywords = (String) notificationCategory.get("keywords");
                if (TextUtils.isEmpty(keywords)) {
                    continue;
                } else {
                    boolean ignoreCase = ((Boolean) notificationCategory.get("ignoreCase")).booleanValue();
                    String matchText = ignoreCase ? text.toLowerCase(Locale.ENGLISH) : text;
                    Log.i(TAG, "getNotificationChannelName; name: " + name + ", keywords: " + keywords + ", ignoreCase: " + ignoreCase + ", matchText: " + matchText);
                    for (String keyword : keywords.split(",")) {
                        String keyword2 = keyword.trim();
                        if (ignoreCase) {
                            keyword2 = keyword2.toLowerCase(Locale.ENGLISH);
                        }
                        if (matchText.contains(keyword2)) {
                            Log.i(TAG, "getNotificationChannelName; found keyword; keyword: " + keyword2);
                            return name;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x02a0, code lost:
        if (r23.actions == null) goto L_0x02dc;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x02a2, code lost:
        r0 = new java.util.ArrayList<>();
        r1 = r23.actions;
        r2 = r1.length;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x02ab, code lost:
        if (r3 >= r2) goto L_0x02c5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x02ad, code lost:
        r4 = r1[r3];
        r4.title = replaceText(r4.title, r24, r25, r26);
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x02bd, code lost:
        if (android.text.TextUtils.isEmpty(r4.title) != false) goto L_0x02c2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x02bf, code lost:
        r0.add(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x02c2, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x02c9, code lost:
        if (r0.isEmpty() != false) goto L_0x02d7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x02cb, code lost:
        r23.actions = (android.app.Notification.Action[]) r0.toArray(new android.app.Notification.Action[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02d7, code lost:
        r23.actions = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03a3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:218:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void replaceNotificationText(android.app.Notification r23, java.lang.String r24, java.lang.String r25, boolean r26, boolean r27, boolean r28, boolean r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 1007
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applisto.appcloner.classes.NotificationOptions.replaceNotificationText(android.app.Notification, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean, boolean):void");
    }

    private CharSequence replaceText(CharSequence text, String replace, CharSequence with, boolean ignoreCase) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        if (TextUtils.isEmpty(replace)) {
            return with;
        }
        while (true) {
            try {
                CharSequence newText = replace(text, replace, with, ignoreCase);
                if (text.toString().equals(newText.toString())) {
                    break;
                }
                text = newText;
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        return text;
    }

    private static CharSequence replace(CharSequence text, String replace, CharSequence with, boolean ignoreCase) {
        int pos;
        SpannableStringBuilder b = new SpannableStringBuilder(text);
        if (ignoreCase) {
            pos = text.toString().toLowerCase(Locale.ENGLISH).indexOf(replace.toLowerCase(Locale.ENGLISH));
        } else {
            pos = text.toString().indexOf(replace);
        }
        if (pos == -1) {
            return text;
        }
        b.setSpan(replace, pos, replace.length() + pos, 33);
        int start = b.getSpanStart(replace);
        int end = b.getSpanEnd(replace);
        if (start != -1) {
            b.replace(start, end, with);
        }
        return b;
    }
}
