package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class AutoPressButtons extends OnAppExitListener {
    private static final String TAG = AutoPressButtons.class.getSimpleName();
    private boolean mReady;
    private Handler mHandler = new Handler();
    private Map<String, Pair<String, AtomicBoolean>> mAutoPressButtons = new HashMap();

    public AutoPressButtons(CloneSettings cloneSettings) {
        List<CloneSettings> autoPressButtons = cloneSettings.forObjectArray("autoPressButtons");
        String str = TAG;
        Log.i(str, "AutoPressButtons; autoPressButtons: " + autoPressButtons);
        if (autoPressButtons != null) {
            for (CloneSettings autoPressButton : autoPressButtons) {
                AtomicBoolean atomicBoolean = null;
                String buttonLabel = autoPressButton.getString("buttonLabel", null);
                if (!TextUtils.isEmpty(buttonLabel)) {
                    String buttonLabel2 = buttonLabel.toLowerCase(Locale.ENGLISH);
                    String screenText = autoPressButton.getString("screenText", null);
                    screenText = screenText != null ? screenText.toLowerCase(Locale.ENGLISH) : screenText;
                    boolean pressOnceOnly = autoPressButton.getBoolean("pressOnceOnly", false).booleanValue();
                    this.mAutoPressButtons.put(buttonLabel2, new Pair<>(screenText, pressOnceOnly ? new AtomicBoolean(true) : atomicBoolean));
                }
            }
        }
        String str2 = TAG;
        Log.i(str2, "AutoPressButtons; mAutoPressButtons: " + this.mAutoPressButtons);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(Context context) {
        if (!this.mAutoPressButtons.isEmpty()) {
            onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.applisto.appcloner.classes.OnAppExitListener, com.applisto.appcloner.classes.AbstractActivityContentProvider
    public void onActivityCreated(final Activity activity) {
        super.onActivityCreated(activity);
        if (Build.VERSION.SDK_INT >= 18) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.AutoPressButtons.1
                @Override // java.lang.Runnable
                public void run() {
                    View rootView = activity.findViewById(16908290);
                    String str = AutoPressButtons.TAG;
                    Log.i(str, "run; rootView: " + rootView);
                    if (rootView != null) {
                        rootView.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.applisto.appcloner.classes.AutoPressButtons.1.1
                            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
                            public void onWindowFocusChanged(boolean hasFocus) {
                                Log.i(AutoPressButtons.TAG, "onWindowFocusChanged; ");
                                AutoPressButtons.this.checkForButtons();
                            }
                        });
                        AutoPressButtons.this.mReady = true;
                        Log.i(AutoPressButtons.TAG, "run; now ready");
                    }
                }
            }, 1000L);
        }
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected int getActivityTimerDelayMillis() {
        return 500;
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityTimer(Activity activity) {
        checkForButtons();
    }

    @Override // com.applisto.appcloner.classes.OnAppExitListener
    protected void onAppExit(Context context) {
        Log.i(TAG, "onAppExit; ");
        try {
            this.mReady = false;
            for (Pair<String, AtomicBoolean> p : this.mAutoPressButtons.values()) {
                if (p.second != null) {
                    ((AtomicBoolean) p.second).set(true);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkForButtons() {
        if (!this.mReady) {
            Log.i(TAG, "checkForButtons; not ready");
            return;
        }
        try {
            List<ViewParent> viewRoots = Utils.getViewRoots();
            for (ViewParent viewRoot : viewRoots) {
                try {
                    Field f = viewRoot.getClass().getDeclaredField("mView");
                    f.setAccessible(true);
                    View view = (View) f.get(viewRoot);
                    ButtonViewFinder buttonViewFinder = new ButtonViewFinder(view);
                    View button = buttonViewFinder.findView();
                    if (button != null) {
                        if (button.callOnClick()) {
                            Log.i(TAG, "checkForButtons; button clicked");
                        } else {
                            Log.w(TAG, "checkForButtons; button not clicked");
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public abstract class ViewFinder {
        View mRootView;

        abstract boolean matchesView(View view);

        ViewFinder(View rootView) {
            this.mRootView = rootView;
        }

        View findView() {
            return findView(this.mRootView);
        }

        View findView(View v) {
            try {
                if (matchesView(v)) {
                    return v;
                }
            } catch (Exception e) {
                Log.w(AutoPressButtons.TAG, e);
            }
            if (!(v instanceof ViewGroup)) {
                return null;
            }
            ViewGroup viewGroup = (ViewGroup) v;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = findView(viewGroup.getChildAt(i));
                if (view != null) {
                    return view;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ButtonViewFinder extends ViewFinder {
        ButtonViewFinder(View rootView) {
            super(rootView);
        }

        @Override // com.applisto.appcloner.classes.AutoPressButtons.ViewFinder
        boolean matchesView(View v) {
            CharSequence text;
            if (v instanceof TextView) {
                TextView textView = (TextView) v;
                if (textView.isClickable() && (text = textView.getText()) != null) {
                    String s = text.toString().toLowerCase(Locale.ENGLISH);
                    for (String buttonLabel : AutoPressButtons.this.mAutoPressButtons.keySet()) {
                        if (buttonLabel.equals(s)) {
                            Pair<String, AtomicBoolean> p = (Pair) AutoPressButtons.this.mAutoPressButtons.get(buttonLabel);
                            String screenText = (String) p.first;
                            AtomicBoolean enabled = (AtomicBoolean) p.second;
                            if (!(enabled == null || enabled.get()) || (!TextUtils.isEmpty(screenText) && new TextViewFinder(this.mRootView, screenText).findView() == null)) {
                                return false;
                            }
                            if (enabled == null) {
                                return true;
                            }
                            enabled.set(false);
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    /* loaded from: classes2.dex */
    private class TextViewFinder extends ViewFinder {
        String mScreenText;

        TextViewFinder(View rootView, String screenText) {
            super(rootView);
            this.mScreenText = screenText;
        }

        @Override // com.applisto.appcloner.classes.AutoPressButtons.ViewFinder
        boolean matchesView(View v) {
            if (!(v instanceof TextView)) {
                return false;
            }
            TextView textView = (TextView) v;
            CharSequence text = textView.getText();
            if (text == null) {
                return false;
            }
            String s = text.toString().toLowerCase(Locale.ENGLISH);
            if (s.contains(this.mScreenText)) {
                return true;
            }
            return false;
        }
    }
}
