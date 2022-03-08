package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    public void setSide(int side) {
        this.mSide = side;
    }

    public void setPropagationSpeed(float propagationSpeed) {
        if (propagationSpeed != 0.0f) {
            this.mPropagationSpeed = propagationSpeed;
            return;
        }
        throw new IllegalArgumentException("propagationSpeed may not be 0");
    }

    @Override // androidx.transition.TransitionPropagation
    public long getStartDelay(ViewGroup sceneRoot, Transition transition, TransitionValues startValues, TransitionValues endValues) {
        int directionMultiplier;
        TransitionValues positionValues;
        int epicenterY;
        int epicenterX;
        if (startValues == null && endValues == null) {
            return 0L;
        }
        Rect epicenter = transition.getEpicenter();
        if (endValues == null || getViewVisibility(startValues) == 0) {
            directionMultiplier = -1;
            positionValues = startValues;
        } else {
            directionMultiplier = 1;
            positionValues = endValues;
        }
        int viewCenterX = getViewX(positionValues);
        int viewCenterY = getViewY(positionValues);
        int[] loc = new int[2];
        sceneRoot.getLocationOnScreen(loc);
        int left = loc[0] + Math.round(sceneRoot.getTranslationX());
        int top = loc[1] + Math.round(sceneRoot.getTranslationY());
        int right = left + sceneRoot.getWidth();
        int bottom = top + sceneRoot.getHeight();
        if (epicenter != null) {
            int epicenterX2 = epicenter.centerX();
            epicenterX = epicenterX2;
            epicenterY = epicenter.centerY();
        } else {
            int epicenterY2 = left + right;
            int epicenterX3 = epicenterY2 / 2;
            epicenterY = (top + bottom) / 2;
            epicenterX = epicenterX3;
        }
        float distance = distance(sceneRoot, viewCenterX, viewCenterY, epicenterX, epicenterY, left, top, right, bottom);
        float maxDistance = getMaxDistance(sceneRoot);
        float distanceFraction = distance / maxDistance;
        long duration = transition.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return Math.round((((float) (directionMultiplier * duration)) / this.mPropagationSpeed) * distanceFraction);
    }

    private int distance(View sceneRoot, int viewX, int viewY, int epicenterX, int epicenterY, int left, int top, int right, int bottom) {
        int side;
        int i = this.mSide;
        boolean isRtl = false;
        if (i == 8388611) {
            if (ViewCompat.getLayoutDirection(sceneRoot) == 1) {
                isRtl = true;
            }
            side = isRtl ? 5 : 3;
        } else if (i == 8388613) {
            if (ViewCompat.getLayoutDirection(sceneRoot) == 1) {
                isRtl = true;
            }
            side = isRtl ? 3 : 5;
        } else {
            side = this.mSide;
        }
        if (side == 3) {
            int distance = (right - viewX) + Math.abs(epicenterY - viewY);
            return distance;
        } else if (side == 5) {
            int distance2 = (viewX - left) + Math.abs(epicenterY - viewY);
            return distance2;
        } else if (side == 48) {
            int distance3 = (bottom - viewY) + Math.abs(epicenterX - viewX);
            return distance3;
        } else if (side != 80) {
            return 0;
        } else {
            int distance4 = (viewY - top) + Math.abs(epicenterX - viewX);
            return distance4;
        }
    }

    private int getMaxDistance(ViewGroup sceneRoot) {
        int i = this.mSide;
        if (i == 3 || i == 5 || i == 8388611 || i == 8388613) {
            return sceneRoot.getWidth();
        }
        return sceneRoot.getHeight();
    }
}
