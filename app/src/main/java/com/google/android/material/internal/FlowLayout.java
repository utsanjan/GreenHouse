package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;

/* loaded from: classes.dex */
public class FlowLayout extends ViewGroup {
    private int itemSpacing;
    private int lineSpacing;
    private boolean singleLine;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.singleLine = false;
        loadFromAttributes(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.singleLine = false;
        loadFromAttributes(context, attrs);
    }

    private void loadFromAttributes(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FlowLayout, 0, 0);
        this.lineSpacing = array.getDimensionPixelSize(R.styleable.FlowLayout_lineSpacing, 0);
        this.itemSpacing = array.getDimensionPixelSize(R.styleable.FlowLayout_itemSpacing, 0);
        array.recycle();
    }

    protected int getLineSpacing() {
        return this.lineSpacing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    protected int getItemSpacing() {
        return this.itemSpacing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setItemSpacing(int itemSpacing) {
        this.itemSpacing = itemSpacing;
    }

    public boolean isSingleLine() {
        return this.singleLine;
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth;
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int maxWidth2 = (widthMode == Integer.MIN_VALUE || widthMode == 1073741824) ? width : Integer.MAX_VALUE;
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        int childBottom = childTop;
        int maxChildRight = 0;
        int maxRight = maxWidth2 - getPaddingRight();
        int i = 0;
        while (i < getChildCount()) {
            View child = getChildAt(i);
            if (child.getVisibility() == 8) {
                maxWidth = maxWidth2;
            } else {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                maxWidth = maxWidth2;
                ViewGroup.LayoutParams lp = child.getLayoutParams();
                int leftMargin = 0;
                int rightMargin = 0;
                if (lp instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLp = (ViewGroup.MarginLayoutParams) lp;
                    leftMargin = 0 + marginLp.leftMargin;
                    rightMargin = 0 + marginLp.rightMargin;
                }
                if (childLeft + leftMargin + child.getMeasuredWidth() <= maxRight || isSingleLine()) {
                    childTop = childTop;
                } else {
                    childLeft = getPaddingLeft();
                    childTop = this.lineSpacing + childBottom;
                }
                int childRight = childLeft + leftMargin + child.getMeasuredWidth();
                int childBottom2 = child.getMeasuredHeight() + childTop;
                if (childRight > maxChildRight) {
                    maxChildRight = childRight;
                }
                int childBottom3 = this.itemSpacing;
                childLeft += leftMargin + rightMargin + child.getMeasuredWidth() + childBottom3;
                if (i == getChildCount() - 1) {
                    maxChildRight += rightMargin;
                    childBottom = childBottom2;
                } else {
                    childBottom = childBottom2;
                }
            }
            i++;
            maxWidth2 = maxWidth;
        }
        int maxChildRight2 = maxChildRight + getPaddingRight();
        int childBottom4 = childBottom + getPaddingBottom();
        int finalWidth = getMeasuredDimension(width, widthMode, maxChildRight2);
        int finalHeight = getMeasuredDimension(height, heightMode, childBottom4);
        setMeasuredDimension(finalWidth, finalHeight);
    }

    private static int getMeasuredDimension(int size, int mode, int childrenEdge) {
        if (mode == Integer.MIN_VALUE) {
            return Math.min(childrenEdge, size);
        }
        if (mode != 1073741824) {
            return childrenEdge;
        }
        return size;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean sizeChanged, int left, int top, int right, int bottom) {
        if (getChildCount() != 0) {
            boolean isRtl = true;
            if (ViewCompat.getLayoutDirection(this) != 1) {
                isRtl = false;
            }
            int paddingStart = isRtl ? getPaddingRight() : getPaddingLeft();
            int paddingEnd = isRtl ? getPaddingLeft() : getPaddingRight();
            int childStart = paddingStart;
            int childTop = getPaddingTop();
            int childBottom = childTop;
            int maxChildEnd = (right - left) - paddingEnd;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != 8) {
                    ViewGroup.LayoutParams lp = child.getLayoutParams();
                    int startMargin = 0;
                    int endMargin = 0;
                    if (lp instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLp = (ViewGroup.MarginLayoutParams) lp;
                        startMargin = MarginLayoutParamsCompat.getMarginStart(marginLp);
                        endMargin = MarginLayoutParamsCompat.getMarginEnd(marginLp);
                    }
                    int childEnd = childStart + startMargin + child.getMeasuredWidth();
                    if (!this.singleLine && childEnd > maxChildEnd) {
                        childStart = paddingStart;
                        childTop = childBottom + this.lineSpacing;
                    }
                    int childEnd2 = childStart + startMargin + child.getMeasuredWidth();
                    int childEnd3 = child.getMeasuredHeight();
                    int childBottom2 = childEnd3 + childTop;
                    if (isRtl) {
                        child.layout(maxChildEnd - childEnd2, childTop, (maxChildEnd - childStart) - startMargin, childBottom2);
                    } else {
                        child.layout(childStart + startMargin, childTop, childEnd2, childBottom2);
                    }
                    childStart += startMargin + endMargin + child.getMeasuredWidth() + this.itemSpacing;
                    childBottom = childBottom2;
                }
            }
        }
    }
}
