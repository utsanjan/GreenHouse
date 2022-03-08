package androidx.viewpager2.widget;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AnimateLayoutChangeDetector {
    private static final ViewGroup.MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS;
    private LinearLayoutManager mLayoutManager;

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        ZERO_MARGIN_LAYOUT_PARAMS = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimateLayoutChangeDetector(LinearLayoutManager llm) {
        this.mLayoutManager = llm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean mayHaveInterferingAnimations() {
        return (!arePagesLaidOutContiguously() || this.mLayoutManager.getChildCount() <= 1) && hasRunningChangingLayoutTransition();
    }

    private boolean arePagesLaidOutContiguously() {
        ViewGroup.MarginLayoutParams margin;
        int i;
        int i2;
        int i3;
        int childCount = this.mLayoutManager.getChildCount();
        if (childCount == 0) {
            return true;
        }
        boolean isHorizontal = this.mLayoutManager.getOrientation() == 0;
        int[][] bounds = (int[][]) Array.newInstance(int.class, childCount, 2);
        for (int i4 = 0; i4 < childCount; i4++) {
            View view = this.mLayoutManager.getChildAt(i4);
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    margin = (ViewGroup.MarginLayoutParams) layoutParams;
                } else {
                    margin = ZERO_MARGIN_LAYOUT_PARAMS;
                }
                int[] iArr = bounds[i4];
                if (isHorizontal) {
                    i = view.getLeft() - margin.leftMargin;
                } else {
                    i = view.getTop() - margin.topMargin;
                }
                iArr[0] = i;
                int[] iArr2 = bounds[i4];
                if (isHorizontal) {
                    i3 = view.getRight();
                    i2 = margin.rightMargin;
                } else {
                    i3 = view.getBottom();
                    i2 = margin.bottomMargin;
                }
                iArr2[1] = i3 + i2;
            } else {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
        }
        Arrays.sort(bounds, new Comparator<int[]>() { // from class: androidx.viewpager2.widget.AnimateLayoutChangeDetector.1
            public int compare(int[] lhs, int[] rhs) {
                return lhs[0] - rhs[0];
            }
        });
        for (int i5 = 1; i5 < childCount; i5++) {
            if (bounds[i5 - 1][1] != bounds[i5][0]) {
                return false;
            }
        }
        int pageSize = bounds[0][1] - bounds[0][0];
        return bounds[0][0] <= 0 && bounds[childCount + (-1)][1] >= pageSize;
    }

    private boolean hasRunningChangingLayoutTransition() {
        int childCount = this.mLayoutManager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (hasRunningChangingLayoutTransition(this.mLayoutManager.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRunningChangingLayoutTransition(View view) {
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
        if (layoutTransition != null && layoutTransition.isChangingLayout()) {
            return true;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (hasRunningChangingLayoutTransition(viewGroup.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }
}
