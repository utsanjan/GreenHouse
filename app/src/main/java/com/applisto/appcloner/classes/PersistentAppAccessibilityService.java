package com.applisto.appcloner.classes;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes2.dex */
public class PersistentAppAccessibilityService extends AccessibilityService {
    private static final String TAG = PersistentAppAccessibilityService.class.getSimpleName();

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        Log.i(TAG, "onServiceConnected; ");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }
}
