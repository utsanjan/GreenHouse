package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.applisto.appcloner.classes.PasswordActivity;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class PasswordActivity extends Activity {
    public static boolean sUnlocked;
    private EditText mEditText;
    private boolean mHidePasswordCharacters;
    private String mOriginalActivityName;
    private String mPassword;
    private boolean mPasswordProtectApp;
    private SharedPreferences mPreferences;
    private boolean mStealthMode;
    private boolean mStealthModeUseFingerprint;
    private static final String TAG = PasswordActivity.class.getSimpleName();
    public static final String PREF_KEY_PASSWORD_ENTERED = PasswordActivity.class.getName() + "_passwordEntered";
    private List<Dialog> mDialogs = new ArrayList();
    private Handler mHandler = new Handler();

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), 129);
            Bundle metaData = activityInfo.metaData;
            if (metaData != null) {
                this.mOriginalActivityName = metaData.getString("com.applisto.appcloner.originalActivityName");
                if (this.mOriginalActivityName != null && this.mOriginalActivityName.startsWith(".")) {
                    this.mOriginalActivityName = getPackageName() + this.mOriginalActivityName;
                }
                CloneSettings cloneSettings = CloneSettings.getInstance(this);
                this.mPasswordProtectApp = cloneSettings.getBoolean("passwordProtectApp", false).booleanValue();
                this.mPassword = cloneSettings.getString("appPassword", null);
                if (cloneSettings.has("stealthMode")) {
                    this.mStealthMode = cloneSettings.getBoolean("stealthMode", false).booleanValue();
                } else {
                    this.mStealthMode = cloneSettings.getBoolean("appPasswordStealthMode", false).booleanValue();
                }
                this.mStealthModeUseFingerprint = cloneSettings.getBoolean("stealthModeUseFingerprint", false).booleanValue();
                this.mHidePasswordCharacters = cloneSettings.getBoolean("hidePasswordCharacters", false).booleanValue();
                boolean askOnlyOnce = !TextUtils.isEmpty(this.mPassword) && cloneSettings.getBoolean("appPasswordAskOnlyOnce", false).booleanValue();
                if (askOnlyOnce) {
                    boolean passwordEntered = this.mPreferences.getBoolean(PREF_KEY_PASSWORD_ENTERED, false);
                    String str = TAG;
                    Log.i(str, "onCreate; passwordEntered: " + passwordEntered);
                    if (passwordEntered) {
                        startApp();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
            exit();
        }
        showDialog();
    }

    private void showDialog() {
        try {
            int i = 1;
            boolean material = Build.VERSION.SDK_INT >= 21;
            final Context context = material ? new ContextThemeWrapper(this, 16974123) : new ContextThemeWrapper(this, 16974120);
            int padding = Utils.dp2px(context, 24.0f);
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setPadding(padding, padding, padding, material ? 0 : padding);
            String positiveLabel = getString(17039370);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            if (this.mStealthMode) {
                TextView textView = new TextView(context);
                String str = "Unfortunately, " + Utils.getAppName(context) + " has stopped.";
                int aerrApplicationId = getResources().getIdentifier("aerr_application", "string", AbstractSpiCall.ANDROID_CLIENT_TYPE);
                String message = getString(aerrApplicationId, new Object[]{Utils.getAppName(context)});
                int aerrCloseId = getResources().getIdentifier("aerr_close", "string", AbstractSpiCall.ANDROID_CLIENT_TYPE);
                positiveLabel = getString(aerrCloseId);
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        int styleId = getResources().getIdentifier("aerr_list_item", "style", AbstractSpiCall.ANDROID_CLIENT_TYPE);
                        textView = new TextView(new ContextThemeWrapper(context, styleId));
                    } catch (Exception e) {
                        Log.w(TAG, e);
                    }
                    builder.setTitle(message);
                    int aerrRestartId = getResources().getIdentifier("aerr_restart", "string", AbstractSpiCall.ANDROID_CLIENT_TYPE);
                    message = getString(aerrRestartId);
                    positiveLabel = null;
                    int icRefreshId = getResources().getIdentifier("ic_refresh", "drawable", AbstractSpiCall.ANDROID_CLIENT_TYPE);
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(icRefreshId, 0, 0, 0);
                    textView.setCompoundDrawablePadding(Utils.dp2px(context, 32.0f));
                    textView.setPadding(0, 0, 0, Utils.dp2px(context, 20.0f));
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$T1MHt5AVzb_hjHwVrKkqOkxau-Q
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            PasswordActivity.this.lambda$showDialog$0$PasswordActivity(view);
                        }
                    });
                    textView.setBackground(null);
                }
                textView.setText(message);
                if (material) {
                    textView.setTextSize(16.0f);
                    textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                } else {
                    textView.setTextAppearance(context, 16973892);
                }
                frameLayout.addView(textView);
            } else if (TextUtils.isEmpty(this.mPassword)) {
                startApp();
                return;
            } else {
                this.mEditText = new EditText(context);
                EditText editText = this.mEditText;
                if (TextUtils.isDigitsOnly(this.mPassword)) {
                    i = 2;
                }
                editText.setInputType(524288 | i | 128);
                this.mEditText.setImeOptions(2);
                this.mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$Elda7giKmVJ9_K9qJYdjPpD3wy4
                    @Override // android.widget.TextView.OnEditorActionListener
                    public final boolean onEditorAction(TextView textView2, int i2, KeyEvent keyEvent) {
                        return PasswordActivity.this.lambda$showDialog$1$PasswordActivity(textView2, i2, keyEvent);
                    }
                });
                if (this.mHidePasswordCharacters) {
                    PasswordTransformationMethod passwordTransformationMethod = (PasswordTransformationMethod) Utils.getSecondaryClassLoader(context).loadClass("com.applisto.appcloner.classes.secondary.util.NoEchoPasswordTransformationMethod").newInstance();
                    this.mEditText.setTransformationMethod(passwordTransformationMethod);
                }
                frameLayout.addView(this.mEditText);
                builder.setTitle("Enter password").setNegativeButton(17039360, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$D6qdW30jvAfIY5pk1DvsC8h2R_0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        PasswordActivity.this.lambda$showDialog$2$PasswordActivity(dialogInterface, i2);
                    }
                });
            }
            if (!TextUtils.isEmpty(positiveLabel)) {
                builder.setPositiveButton(positiveLabel, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$zc7hd37mOHt0UzAhYJvzuezNGv8
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        PasswordActivity.this.lambda$showDialog$3$PasswordActivity(dialogInterface, i2);
                    }
                });
            }
            final AlertDialog alertDialog = builder.setView(frameLayout).setCancelable(false).create();
            this.mDialogs.add(alertDialog);
            if (this.mStealthMode) {
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.applisto.appcloner.classes.PasswordActivity.1
                    @Override // android.content.DialogInterface.OnShowListener
                    public void onShow(DialogInterface dialogInterface) {
                        final Runnable longPressRunnable = PasswordActivity.this.getLongPressRunnable();
                        try {
                            Window window = alertDialog.getWindow();
                            Window.Callback callback = window.getCallback();
                            window.setCallback(new WindowCallbackDelegate(callback) { // from class: com.applisto.appcloner.classes.PasswordActivity.1.1
                                GestureDetector gestureDetector;

                                {
                                    this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.applisto.appcloner.classes.PasswordActivity.1.1.1
                                        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                                        public void onLongPress(MotionEvent event) {
                                            try {
                                                longPressRunnable.run();
                                            } catch (Exception e2) {
                                                Log.w(PasswordActivity.TAG, e2);
                                            }
                                        }
                                    });
                                }

                                @Override // com.applisto.appcloner.classes.WindowCallbackDelegate, android.view.Window.Callback
                                public boolean dispatchTouchEvent(MotionEvent event) {
                                    this.gestureDetector.onTouchEvent(event);
                                    return super.dispatchTouchEvent(event);
                                }
                            });
                            if (PasswordActivity.this.mStealthModeUseFingerprint && Build.VERSION.SDK_INT >= 23) {
                                PasswordActivity.this.listenFingerprints(PasswordActivity.this);
                            }
                        } catch (Exception e2) {
                            Log.w(PasswordActivity.TAG, e2);
                        }
                    }
                });
            }
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$E8mOz44UXJfPECqeaAedmpy-m7c
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    PasswordActivity.this.lambda$showDialog$4$PasswordActivity(dialogInterface);
                }
            });
            try {
                alertDialog.getWindow().setDimAmount(1.0f);
                alertDialog.getWindow().setWindowAnimations(0);
                alertDialog.getWindow().setSoftInputMode(5);
                int targetSdkVersion = Utils.getTargetSdkVersion(context);
                if (targetSdkVersion < 23) {
                    alertDialog.getWindow().setType(2003);
                }
            } catch (Exception e2) {
                Log.w(TAG, e2);
            }
            alertDialog.show();
            Utils.keepDialogOpenOnOrientationChange(alertDialog);
        } catch (Throwable t) {
            Log.w(TAG, t);
            exit();
        }
    }

    public /* synthetic */ void lambda$showDialog$0$PasswordActivity(View v) {
        exit();
    }

    public /* synthetic */ boolean lambda$showDialog$1$PasswordActivity(TextView v, int actionId, KeyEvent event) {
        onOk();
        return false;
    }

    public /* synthetic */ void lambda$showDialog$2$PasswordActivity(DialogInterface dialog, int which) {
        exit();
    }

    public /* synthetic */ void lambda$showDialog$3$PasswordActivity(DialogInterface dialog, int which) {
        onOk();
    }

    public /* synthetic */ void lambda$showDialog$4$PasswordActivity(DialogInterface dialog) {
        if (!sUnlocked) {
            exit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void listenFingerprints(Context context) {
        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint");
        if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
            fingerprintManager.authenticate(null, null, 0, new AnonymousClass2(context), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.applisto.appcloner.classes.PasswordActivity$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends FingerprintManager.AuthenticationCallback {
        final /* synthetic */ Context val$context;

        AnonymousClass2(Context context) {
            this.val$context = context;
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            Log.i(PasswordActivity.TAG, "onAuthenticationSucceeded; ");
            try {
                PasswordActivity.this.onDoubleLongPress();
            } catch (Exception e) {
                Log.w(PasswordActivity.TAG, e);
            }
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            Log.i(PasswordActivity.TAG, "onAuthenticationFailed; ");
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            String str = PasswordActivity.TAG;
            Log.i(str, "onAuthenticationError; errorCode: " + errorCode + ", errString: " + ((Object) errString));
            Handler handler = PasswordActivity.this.mHandler;
            final Context context = this.val$context;
            handler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$2$W-9YASUACguDr_7MTu_lz-eCQhs
                @Override // java.lang.Runnable
                public final void run() {
                    PasswordActivity.AnonymousClass2.this.lambda$onAuthenticationError$0$PasswordActivity$2(context);
                }
            }, 1000L);
        }

        public /* synthetic */ void lambda$onAuthenticationError$0$PasswordActivity$2(Context context) {
            PasswordActivity.this.listenFingerprints(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.applisto.appcloner.classes.PasswordActivity$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 implements Runnable {
        private int mCount;
        private Handler mHandler = new Handler();

        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mCount++;
            if (this.mCount >= 2) {
                PasswordActivity.this.onDoubleLongPress();
            } else {
                this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.-$$Lambda$PasswordActivity$3$dtKTXKLiCIp97MWlz7ZSfdVOtDc
                    @Override // java.lang.Runnable
                    public final void run() {
                        PasswordActivity.AnonymousClass3.this.lambda$run$0$PasswordActivity$3();
                    }
                }, 3000L);
            }
        }

        public /* synthetic */ void lambda$run$0$PasswordActivity$3() {
            this.mCount = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable getLongPressRunnable() {
        return new AnonymousClass3();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDoubleLongPress() {
        if (!this.mPasswordProtectApp || TextUtils.isEmpty(this.mPassword)) {
            startApp();
            return;
        }
        this.mStealthMode = false;
        showDialog();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0025 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onOk() {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            android.widget.EditText r2 = r4.mEditText     // Catch: Exception -> 0x001d
            if (r2 == 0) goto L_0x001a
            java.lang.String r2 = r4.mPassword     // Catch: Exception -> 0x001d
            android.widget.EditText r3 = r4.mEditText     // Catch: Exception -> 0x001d
            android.text.Editable r3 = r3.getText()     // Catch: Exception -> 0x001d
            java.lang.String r3 = r3.toString()     // Catch: Exception -> 0x001d
            boolean r2 = r2.equals(r3)     // Catch: Exception -> 0x001d
            if (r2 == 0) goto L_0x001a
            r2 = 1
            goto L_0x001b
        L_0x001a:
            r2 = 0
        L_0x001b:
            r0 = r2
            goto L_0x0023
        L_0x001d:
            r2 = move-exception
            java.lang.String r3 = com.applisto.appcloner.classes.PasswordActivity.TAG
            android.util.Log.w(r3, r2)
        L_0x0023:
            if (r0 == 0) goto L_0x003f
            android.content.SharedPreferences r2 = r4.mPreferences     // Catch: Exception -> 0x0035
            android.content.SharedPreferences$Editor r2 = r2.edit()     // Catch: Exception -> 0x0035
            java.lang.String r3 = com.applisto.appcloner.classes.PasswordActivity.PREF_KEY_PASSWORD_ENTERED     // Catch: Exception -> 0x0035
            android.content.SharedPreferences$Editor r1 = r2.putBoolean(r3, r1)     // Catch: Exception -> 0x0035
            r1.apply()     // Catch: Exception -> 0x0035
            goto L_0x003b
        L_0x0035:
            r1 = move-exception
            java.lang.String r2 = com.applisto.appcloner.classes.PasswordActivity.TAG
            android.util.Log.w(r2, r1)
        L_0x003b:
            r4.startApp()
            goto L_0x0042
        L_0x003f:
            r4.exit()
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applisto.appcloner.classes.PasswordActivity.onOk():void");
    }

    private void startApp() {
        sUnlocked = true;
        try {
            for (Dialog dialog : this.mDialogs) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        try {
            Intent i = new Intent(getIntent());
            i.setComponent(new ComponentName(this, Class.forName(this.mOriginalActivityName)));
            i.setFlags(268435456);
            startActivity(i);
        } catch (Exception e2) {
            Log.w(TAG, e2);
        }
        finish();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.applisto.appcloner.classes.PasswordActivity$4] */
    private void exit() {
        if (Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask();
        } else {
            finish();
        }
        new Thread() { // from class: com.applisto.appcloner.classes.PasswordActivity.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(250L);
                } catch (InterruptedException e) {
                }
                System.exit(0);
            }
        }.start();
    }
}
