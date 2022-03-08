package com.applisto.appcloner.classes;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes2.dex */
public class WindowCallbackWrapper implements Window.Callback {
    protected Window.Callback mOriginalCallback;

    public WindowCallbackWrapper(Window.Callback originalCallback) {
        this.mOriginalCallback = originalCallback;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mOriginalCallback.dispatchKeyEvent(event);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return this.mOriginalCallback.dispatchKeyShortcutEvent(event);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent event) {
        return this.mOriginalCallback.dispatchTouchEvent(event);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return this.mOriginalCallback.dispatchTrackballEvent(event);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return this.mOriginalCallback.dispatchGenericMotionEvent(event);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return this.mOriginalCallback.dispatchPopulateAccessibilityEvent(event);
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int featureId) {
        return this.mOriginalCallback.onCreatePanelView(featureId);
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return this.mOriginalCallback.onCreatePanelMenu(featureId, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return this.mOriginalCallback.onPreparePanel(featureId, view, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int featureId, Menu menu) {
        return this.mOriginalCallback.onMenuOpened(featureId, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return this.mOriginalCallback.onMenuItemSelected(featureId, item);
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        this.mOriginalCallback.onWindowAttributesChanged(attrs);
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
        this.mOriginalCallback.onContentChanged();
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        this.mOriginalCallback.onWindowFocusChanged(hasFocus);
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        this.mOriginalCallback.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mOriginalCallback.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int featureId, Menu menu) {
        this.mOriginalCallback.onPanelClosed(featureId, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return this.mOriginalCallback.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.mOriginalCallback.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.mOriginalCallback.onWindowStartingActionMode(callback);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return this.mOriginalCallback.onWindowStartingActionMode(callback, type);
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode mode) {
        this.mOriginalCallback.onActionModeStarted(mode);
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode mode) {
        this.mOriginalCallback.onActionModeFinished(mode);
    }
}
