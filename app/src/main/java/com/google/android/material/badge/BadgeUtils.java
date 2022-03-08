package com.google.android.material.badge;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.ParcelableSparseArray;

/* loaded from: classes.dex */
public class BadgeUtils {
    public static final boolean USE_COMPAT_PARENT;

    static {
        USE_COMPAT_PARENT = Build.VERSION.SDK_INT < 18;
    }

    private BadgeUtils() {
    }

    public static void updateBadgeBounds(Rect rect, float centerX, float centerY, float halfWidth, float halfHeight) {
        rect.set((int) (centerX - halfWidth), (int) (centerY - halfHeight), (int) (centerX + halfWidth), (int) (centerY + halfHeight));
    }

    public static void attachBadgeDrawable(BadgeDrawable badgeDrawable, View anchor, FrameLayout compatBadgeParent) {
        setBadgeDrawableBounds(badgeDrawable, anchor, compatBadgeParent);
        if (USE_COMPAT_PARENT) {
            compatBadgeParent.setForeground(badgeDrawable);
        } else {
            anchor.getOverlay().add(badgeDrawable);
        }
    }

    public static void detachBadgeDrawable(BadgeDrawable badgeDrawable, View anchor, FrameLayout compatBadgeParent) {
        if (badgeDrawable != null) {
            if (USE_COMPAT_PARENT) {
                compatBadgeParent.setForeground(null);
            } else {
                anchor.getOverlay().remove(badgeDrawable);
            }
        }
    }

    public static void setBadgeDrawableBounds(BadgeDrawable badgeDrawable, View anchor, FrameLayout compatBadgeParent) {
        Rect badgeBounds = new Rect();
        View badgeParent = USE_COMPAT_PARENT ? compatBadgeParent : anchor;
        badgeParent.getDrawingRect(badgeBounds);
        badgeDrawable.setBounds(badgeBounds);
        badgeDrawable.updateBadgeCoordinates(anchor, compatBadgeParent);
    }

    public static ParcelableSparseArray createParcelableBadgeStates(SparseArray<BadgeDrawable> badgeDrawables) {
        ParcelableSparseArray badgeStates = new ParcelableSparseArray();
        for (int i = 0; i < badgeDrawables.size(); i++) {
            int key = badgeDrawables.keyAt(i);
            BadgeDrawable badgeDrawable = badgeDrawables.valueAt(i);
            if (badgeDrawable != null) {
                badgeStates.put(key, badgeDrawable.getSavedState());
            } else {
                throw new IllegalArgumentException("badgeDrawable cannot be null");
            }
        }
        return badgeStates;
    }

    public static SparseArray<BadgeDrawable> createBadgeDrawablesFromSavedStates(Context context, ParcelableSparseArray badgeStates) {
        SparseArray<BadgeDrawable> badgeDrawables = new SparseArray<>(badgeStates.size());
        for (int i = 0; i < badgeStates.size(); i++) {
            int key = badgeStates.keyAt(i);
            BadgeDrawable.SavedState savedState = (BadgeDrawable.SavedState) badgeStates.valueAt(i);
            if (savedState != null) {
                BadgeDrawable badgeDrawable = BadgeDrawable.createFromSavedState(context, savedState);
                badgeDrawables.put(key, badgeDrawable);
            } else {
                throw new IllegalArgumentException("BadgeDrawable's savedState cannot be null");
            }
        }
        return badgeDrawables;
    }
}
