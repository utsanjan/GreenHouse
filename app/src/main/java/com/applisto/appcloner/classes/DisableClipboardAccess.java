package com.applisto.appcloner.classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.applisto.appcloner.classes.util.SimpleCrypt;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
class DisableClipboardAccess {
    private static final int CLEAR_CLIPBOARD_TIMEOUT_MILLIS = 30000;
    private String mClearingClipDataToString;
    private ClipData mClipData;
    private boolean mClipboardTimeout;
    private Context mContext;
    private boolean mDisableClipboardReadAccess;
    private boolean mDisableClipboardWriteAccess;
    private boolean mPersistentClipboard;
    private SharedPreferences mPreferences;
    private boolean mPrivateClipboard;
    private String mSetClipboardDataOnStart;
    private static final String TAG = DisableClipboardAccess.class.getSimpleName();
    private static final ClipData EMPTY_CLIP_DATA = ClipData.newPlainText("", "");
    private List<ClipboardManager.OnPrimaryClipChangedListener> mListeners = new CopyOnWriteArrayList();
    private Handler mTimeoutHandler = new Handler();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisableClipboardAccess(CloneSettings cloneSettings) {
        this.mPrivateClipboard = cloneSettings.getBoolean("privateClipboard", false).booleanValue();
        if (this.mPrivateClipboard) {
            this.mPersistentClipboard = cloneSettings.getBoolean("persistentClipboard", false).booleanValue();
        } else {
            this.mDisableClipboardReadAccess = cloneSettings.getBoolean("disableClipboardReadAccess", false).booleanValue();
            this.mDisableClipboardWriteAccess = cloneSettings.getBoolean("disableClipboardWriteAccess", false).booleanValue();
        }
        this.mClipboardTimeout = cloneSettings.getBoolean("clipboardTimeout", false).booleanValue();
        this.mSetClipboardDataOnStart = cloneSettings.getString("setClipboardDataOnStart", "");
        String str = TAG;
        Log.i(str, "DisableClipboardAccess; mPrivateClipboard: " + this.mPrivateClipboard + ", mPersistentClipboard: " + this.mPersistentClipboard + ", mDisableClipboardReadAccess: " + this.mDisableClipboardReadAccess + ", mDisableClipboardWriteAccess: " + this.mDisableClipboardWriteAccess + ", mClipboardTimeout: " + this.mClipboardTimeout);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(Context context) {
        if (this.mDisableClipboardReadAccess || this.mDisableClipboardWriteAccess || this.mPrivateClipboard || this.mClipboardTimeout) {
            this.mContext = context;
            this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            installClipboardManagerHook(context);
        }
        if (!TextUtils.isEmpty(this.mSetClipboardDataOnStart)) {
            try {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
                clipboardManager.setPrimaryClip(ClipData.newPlainText("", this.mSetClipboardDataOnStart));
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }

    private void installClipboardManagerHook(Context context) {
        final Object originalService;
        loadClipboardIfPersistent();
        try {
            final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            clipboardManager.hasText();
            try {
                Field field = clipboardManager.getClass().getDeclaredField("mService");
                field.setAccessible(true);
                originalService = field.get(clipboardManager);
            } catch (NoSuchFieldException e) {
                Field field2 = clipboardManager.getClass().getDeclaredField("sService");
                field2.setAccessible(true);
                originalService = field2.get(null);
            }
            InvocationHandler invocationHandler = new InvocationHandler() { // from class: com.applisto.appcloner.classes.DisableClipboardAccess.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    boolean z = false;
                    if (DisableClipboardAccess.this.mPrivateClipboard) {
                        if ("hasClipboardText".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; hasClipboardText; ");
                            if (DisableClipboardAccess.this.mClipData != null) {
                                z = true;
                            }
                            return Boolean.valueOf(z);
                        } else if ("hasPrimaryClip".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; hasPrimaryClip; ");
                            if (DisableClipboardAccess.this.mClipData != null) {
                                z = true;
                            }
                            return Boolean.valueOf(z);
                        } else if ("getPrimaryClipDescription".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; getPrimaryClipDescription; ");
                            if (DisableClipboardAccess.this.mClipData != null) {
                                return DisableClipboardAccess.cloneParcelable(DisableClipboardAccess.this.mClipData.getDescription());
                            }
                            return new ClipDescription("", new String[0]);
                        } else if ("getPrimaryClip".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; getPrimaryClip; ");
                            if (DisableClipboardAccess.this.mClipData != null) {
                                return DisableClipboardAccess.cloneParcelable(DisableClipboardAccess.this.mClipData);
                            }
                            return new ClipData("", new String[0], new ClipData.Item(""));
                        } else if ("addPrimaryClipChangedListener".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; addPrimaryClipChangedListener; ");
                            ClipboardManager.OnPrimaryClipChangedListener listener = (ClipboardManager.OnPrimaryClipChangedListener) args[0];
                            DisableClipboardAccess.this.mListeners.add(listener);
                            return null;
                        } else if ("removePrimaryClipChangedListener".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; removePrimaryClipChangedListener; ");
                            ClipboardManager.OnPrimaryClipChangedListener listener2 = (ClipboardManager.OnPrimaryClipChangedListener) args[0];
                            DisableClipboardAccess.this.mListeners.remove(listener2);
                            return null;
                        } else if ("setPrimaryClip".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; setPrimaryClip; ");
                            ClipData clipData = (ClipData) args[0];
                            DisableClipboardAccess.this.mClipData = (ClipData) DisableClipboardAccess.cloneParcelable(clipData);
                            DisableClipboardAccess.this.saveClipboardIfPersistent();
                            for (ClipboardManager.OnPrimaryClipChangedListener listener3 : DisableClipboardAccess.this.mListeners) {
                                try {
                                    listener3.onPrimaryClipChanged();
                                } catch (Exception e2) {
                                    Log.w(DisableClipboardAccess.TAG, e2);
                                }
                            }
                            DisableClipboardAccess.this.startClipboardTimeout(clipboardManager, clipData);
                            return null;
                        }
                    }
                    if (DisableClipboardAccess.this.mDisableClipboardReadAccess) {
                        if ("hasClipboardText".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; hasClipboardText; disabled clipboard read access");
                            return false;
                        } else if ("hasPrimaryClip".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; hasPrimaryClip; disabled clipboard read access");
                            return false;
                        } else if ("getPrimaryClipDescription".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; getPrimaryClipDescription; disabled clipboard read access");
                            return new ClipDescription("", new String[0]);
                        } else if ("getPrimaryClip".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; getPrimaryClip; disabled clipboard read access");
                            return null;
                        } else if ("addPrimaryClipChangedListener".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; addPrimaryClipChangedListener; disabled clipboard read access");
                            return null;
                        } else if ("removePrimaryClipChangedListener".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; removePrimaryClipChangedListener; disabled clipboard read access");
                            return null;
                        }
                    }
                    if (DisableClipboardAccess.this.mDisableClipboardWriteAccess) {
                        if ("setPrimaryClip".equals(method.getName())) {
                            Log.i(DisableClipboardAccess.TAG, "invoke; setPrimaryClip; disabled clipboard write access");
                            return null;
                        }
                    } else if (DisableClipboardAccess.this.mClipboardTimeout && "setPrimaryClip".equals(method.getName())) {
                        DisableClipboardAccess.this.startClipboardTimeout(clipboardManager, (ClipData) args[0]);
                    }
                    return method.invoke(originalService, args);
                }
            };
            Class<?> clazz = Class.forName("android.content.IClipboard");
            Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, invocationHandler);
            if (Build.VERSION.SDK_INT < 26 && !"O".equals(Build.VERSION.CODENAME)) {
                Field field3 = clipboardManager.getClass().getDeclaredField("sService");
                field3.setAccessible(true);
                field3.set(null, proxy);
                Log.i(TAG, "installClipboardManagerHook; installed proxy");
            }
            Field field4 = clipboardManager.getClass().getDeclaredField("mService");
            field4.setAccessible(true);
            field4.set(clipboardManager, proxy);
            Log.i(TAG, "installClipboardManagerHook; installed proxy");
        } catch (Exception e2) {
            Log.w(TAG, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startClipboardTimeout(final ClipboardManager clipboardManager, ClipData clipData) {
        try {
            if (EMPTY_CLIP_DATA.equals(clipData)) {
                Log.i(TAG, "startClipboardTimeout; empty clip data");
                return;
            }
            this.mTimeoutHandler.removeCallbacksAndMessages(null);
            this.mTimeoutHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.DisableClipboardAccess.2
                @Override // java.lang.Runnable
                public void run() {
                    DisableClipboardAccess disableClipboardAccess = DisableClipboardAccess.this;
                    disableClipboardAccess.mClearingClipDataToString = "" + clipboardManager.getPrimaryClip();
                    long when = System.currentTimeMillis() + 30000;
                    Intent i = new Intent();
                    i.setComponent(new ComponentName(DisableClipboardAccess.this.mContext, ClearClipboardReceiver.class));
                    i.setPackage(DisableClipboardAccess.this.mContext.getPackageName());
                    i.putExtra("expected_clip_data_to_string", DisableClipboardAccess.this.mClearingClipDataToString);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(DisableClipboardAccess.this.mContext, (int) when, i, 0);
                    AlarmManager alarmManager = (AlarmManager) DisableClipboardAccess.this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
                    if (Build.VERSION.SDK_INT >= 23) {
                        alarmManager.setAndAllowWhileIdle(0, when, pendingIntent);
                    } else if (Build.VERSION.SDK_INT >= 19) {
                        alarmManager.setExact(0, when, pendingIntent);
                    } else {
                        alarmManager.set(0, when, pendingIntent);
                    }
                }
            }, 100L);
            this.mTimeoutHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.DisableClipboardAccess.3
                @Override // java.lang.Runnable
                public void run() {
                    DisableClipboardAccess.clearClipboard(DisableClipboardAccess.this.mContext, DisableClipboardAccess.this.mClearingClipDataToString);
                }
            }, 30000L);
            Log.i(TAG, "startClipboardTimeout; started clipboard timeout");
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    /* loaded from: classes2.dex */
    public static class ClearClipboardReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = DisableClipboardAccess.TAG;
            Log.i(str, "onReceive; intent: " + intent);
            DisableClipboardAccess.clearClipboard(context, intent.getStringExtra("expected_clip_data_to_string"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void clearClipboard(Context context, String expectedClipDataToString) {
        Log.i(TAG, "clearClipboard; ");
        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            String currentClipDataToString = "" + clipboardManager.getPrimaryClip();
            if (currentClipDataToString.equals(expectedClipDataToString)) {
                Log.i(TAG, "clearClipboard; clearing clipboard");
                clipboardManager.setPrimaryClip(EMPTY_CLIP_DATA);
                Toast.makeText(context, "Clipboard cleared", 0).show();
            } else {
                Log.i(TAG, "clearClipboard; not clearing clipboard; found other clip data");
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private void loadClipboardIfPersistent() {
        if (this.mPersistentClipboard) {
            try {
                String s = this.mPreferences.getString("persistent_clip_data", null);
                if (!TextUtils.isEmpty(s)) {
                    byte[] bytes = Base64.decode(new SimpleCrypt("gvdshfjry8wehu43").decrypt(s), 0);
                    Parcel parcel = Parcel.obtain();
                    parcel.unmarshall(bytes, 0, bytes.length);
                    parcel.setDataPosition(0);
                    this.mClipData = (ClipData) ClipData.CREATOR.createFromParcel(parcel);
                    Log.i(TAG, "saveClipboardIfPersistent; clipboard loaded");
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveClipboardIfPersistent() {
        if (this.mPersistentClipboard && this.mClipData != null) {
            try {
                Parcel parcel = Parcel.obtain();
                this.mClipData.writeToParcel(parcel, 0);
                byte[] bytes = parcel.marshall();
                String s = Base64.encodeToString(bytes, 2);
                this.mPreferences.edit().putString("persistent_clip_data", new SimpleCrypt("gvdshfjry8wehu43").encrypt(s)).commit();
                Log.i(TAG, "saveClipboardIfPersistent; clipboard saved");
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends Parcelable> T cloneParcelable(T parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel p = null;
        try {
            p = Parcel.obtain();
            p.writeValue(parcelable);
            p.setDataPosition(0);
            return (T) ((Parcelable) p.readValue(ClipData.class.getClassLoader()));
        } finally {
            if (p != null) {
                p.recycle();
            }
        }
    }
}
