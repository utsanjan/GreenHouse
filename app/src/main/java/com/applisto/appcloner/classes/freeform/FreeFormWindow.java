package com.applisto.appcloner.classes.freeform;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import com.applisto.appcloner.classes.AbstractActivityContentProvider;
import com.applisto.appcloner.classes.BuildConfig;

/* loaded from: classes2.dex */
public class FreeFormWindow extends AbstractActivityContentProvider {
    public static final String PREF_KEY_FREE_FORM_WINDOW_BOTTOM = "free_form_window_bottom";
    public static final String PREF_KEY_FREE_FORM_WINDOW_LEFT = "free_form_window_left";
    public static final String PREF_KEY_FREE_FORM_WINDOW_RIGHT = "free_form_window_right";
    public static final String PREF_KEY_FREE_FORM_WINDOW_TOP = "free_form_window_top";
    private static final String TAG = FreeFormWindow.class.getSimpleName();

    public FreeFormWindow() {
        onCreate();
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityPaused(Activity activity) {
        saveActivityBounds(activity);
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityStopped(Activity activity) {
        saveActivityBounds(activity);
    }

    private void saveActivityBounds(Activity activity) {
        Rect rect;
        String str = TAG;
        Log.i(str, "saveActivityBounds; activity: " + activity);
        try {
            String className = activity.getClass().getName();
            if (!className.startsWith(BuildConfig.APPLICATION_ID) && (rect = getActivityBounds(activity)) != null && !rect.isEmpty()) {
                String str2 = TAG;
                Log.i(str2, "saveActivityBounds; rect: " + rect);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                preferences.edit().putInt(PREF_KEY_FREE_FORM_WINDOW_LEFT, rect.left).putInt(PREF_KEY_FREE_FORM_WINDOW_TOP, rect.top).putInt(PREF_KEY_FREE_FORM_WINDOW_RIGHT, rect.right).putInt(PREF_KEY_FREE_FORM_WINDOW_BOTTOM, rect.bottom).apply();
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    private Rect getActivityBounds(Activity activity) {
        try {
            View decorView = activity.getWindow().getDecorView();
            int[] position = new int[2];
            decorView.getLocationOnScreen(position);
            int left = position[0];
            int top = position[1];
            int width = decorView.getWidth();
            int height = decorView.getHeight();
            return new Rect(left, top, left + width, top + height);
        } catch (Exception e) {
            Log.w(TAG, e);
            return null;
        }
    }
}
