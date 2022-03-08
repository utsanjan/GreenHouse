package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ShowableListMenu;

/* loaded from: classes.dex */
public abstract class ForwardingListener implements View.OnTouchListener, View.OnAttachStateChangeListener {
    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation = new int[2];
    private Runnable mTriggerLongPress;

    public abstract ShowableListMenu getPopup();

    public ForwardingListener(View src) {
        this.mSrc = src;
        src.setLongClickable(true);
        src.addOnAttachStateChangeListener(this);
        this.mScaledTouchSlop = ViewConfiguration.get(src.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.mTapTimeout = tapTimeout;
        this.mLongPressTimeout = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        boolean forwarding;
        boolean wasForwarding = this.mForwarding;
        if (wasForwarding) {
            forwarding = onTouchForwarded(event) || !onForwardingStopped();
        } else {
            forwarding = onTouchObserved(event) && onForwardingStarted();
            if (forwarding) {
                long now = SystemClock.uptimeMillis();
                MotionEvent e = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
                this.mSrc.onTouchEvent(e);
                e.recycle();
            }
        }
        this.mForwarding = forwarding;
        return forwarding || wasForwarding;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        this.mForwarding = false;
        this.mActivePointerId = -1;
        Runnable runnable = this.mDisallowIntercept;
        if (runnable != null) {
            this.mSrc.removeCallbacks(runnable);
        }
    }

    protected boolean onForwardingStarted() {
        ShowableListMenu popup = getPopup();
        if (popup == null || popup.isShowing()) {
            return true;
        }
        popup.show();
        return true;
    }

    protected boolean onForwardingStopped() {
        ShowableListMenu popup = getPopup();
        if (popup == null || !popup.isShowing()) {
            return true;
        }
        popup.dismiss();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r1 != 3) goto L_0x006f;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean onTouchObserved(android.view.MotionEvent r9) {
        /*
            r8 = this;
            android.view.View r0 = r8.mSrc
            boolean r1 = r0.isEnabled()
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            int r1 = r9.getActionMasked()
            if (r1 == 0) goto L_0x0042
            r3 = 1
            if (r1 == r3) goto L_0x003e
            r4 = 2
            if (r1 == r4) goto L_0x001a
            r3 = 3
            if (r1 == r3) goto L_0x003e
            goto L_0x006f
        L_0x001a:
            int r4 = r8.mActivePointerId
            int r4 = r9.findPointerIndex(r4)
            if (r4 < 0) goto L_0x006f
            float r5 = r9.getX(r4)
            float r6 = r9.getY(r4)
            float r7 = r8.mScaledTouchSlop
            boolean r7 = pointInView(r0, r5, r6, r7)
            if (r7 != 0) goto L_0x003d
            r8.clearCallbacks()
            android.view.ViewParent r2 = r0.getParent()
            r2.requestDisallowInterceptTouchEvent(r3)
            return r3
        L_0x003d:
            goto L_0x006f
        L_0x003e:
            r8.clearCallbacks()
            goto L_0x006f
        L_0x0042:
            int r3 = r9.getPointerId(r2)
            r8.mActivePointerId = r3
            java.lang.Runnable r3 = r8.mDisallowIntercept
            if (r3 != 0) goto L_0x0053
            androidx.appcompat.widget.ForwardingListener$DisallowIntercept r3 = new androidx.appcompat.widget.ForwardingListener$DisallowIntercept
            r3.<init>()
            r8.mDisallowIntercept = r3
        L_0x0053:
            java.lang.Runnable r3 = r8.mDisallowIntercept
            int r4 = r8.mTapTimeout
            long r4 = (long) r4
            r0.postDelayed(r3, r4)
            java.lang.Runnable r3 = r8.mTriggerLongPress
            if (r3 != 0) goto L_0x0066
            androidx.appcompat.widget.ForwardingListener$TriggerLongPress r3 = new androidx.appcompat.widget.ForwardingListener$TriggerLongPress
            r3.<init>()
            r8.mTriggerLongPress = r3
        L_0x0066:
            java.lang.Runnable r3 = r8.mTriggerLongPress
            int r4 = r8.mLongPressTimeout
            long r4 = (long) r4
            r0.postDelayed(r3, r4)
        L_0x006f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ForwardingListener.onTouchObserved(android.view.MotionEvent):boolean");
    }

    private void clearCallbacks() {
        Runnable runnable = this.mTriggerLongPress;
        if (runnable != null) {
            this.mSrc.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.mDisallowIntercept;
        if (runnable2 != null) {
            this.mSrc.removeCallbacks(runnable2);
        }
    }

    void onLongPress() {
        clearCallbacks();
        View src = this.mSrc;
        if (src.isEnabled() && !src.isLongClickable() && onForwardingStarted()) {
            src.getParent().requestDisallowInterceptTouchEvent(true);
            long now = SystemClock.uptimeMillis();
            MotionEvent e = MotionEvent.obtain(now, now, 3, 0.0f, 0.0f, 0);
            src.onTouchEvent(e);
            e.recycle();
            this.mForwarding = true;
        }
    }

    private boolean onTouchForwarded(MotionEvent srcEvent) {
        DropDownListView dst;
        View src = this.mSrc;
        ShowableListMenu popup = getPopup();
        if (popup == null || !popup.isShowing() || (dst = (DropDownListView) popup.getListView()) == null || !dst.isShown()) {
            return false;
        }
        MotionEvent dstEvent = MotionEvent.obtainNoHistory(srcEvent);
        toGlobalMotionEvent(src, dstEvent);
        toLocalMotionEvent(dst, dstEvent);
        boolean handled = dst.onForwardedEvent(dstEvent, this.mActivePointerId);
        dstEvent.recycle();
        int action = srcEvent.getActionMasked();
        boolean keepForwarding = (action == 1 || action == 3) ? false : true;
        return handled && keepForwarding;
    }

    private static boolean pointInView(View view, float localX, float localY, float slop) {
        return localX >= (-slop) && localY >= (-slop) && localX < ((float) (view.getRight() - view.getLeft())) + slop && localY < ((float) (view.getBottom() - view.getTop())) + slop;
    }

    private boolean toLocalMotionEvent(View view, MotionEvent event) {
        int[] loc = this.mTmpLocation;
        view.getLocationOnScreen(loc);
        event.offsetLocation(-loc[0], -loc[1]);
        return true;
    }

    private boolean toGlobalMotionEvent(View view, MotionEvent event) {
        int[] loc = this.mTmpLocation;
        view.getLocationOnScreen(loc);
        event.offsetLocation(loc[0], loc[1]);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DisallowIntercept implements Runnable {
        DisallowIntercept() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = ForwardingListener.this.mSrc.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TriggerLongPress implements Runnable {
        TriggerLongPress() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ForwardingListener.this.onLongPress();
        }
    }
}
