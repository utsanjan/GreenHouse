package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.google.android.material.R;
import com.google.android.material.internal.FlowLayout;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes.dex */
public class ChipGroup extends FlowLayout {
    private int checkedId;
    private final CheckedStateTracker checkedStateTracker;
    private int chipSpacingHorizontal;
    private int chipSpacingVertical;
    private OnCheckedChangeListener onCheckedChangeListener;
    private PassThroughHierarchyChangeListener passThroughListener;
    private boolean protectFromCheckedChange;
    private boolean singleSelection;

    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(ChipGroup chipGroup, int i);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    public ChipGroup(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.chipGroupStyle);
    }

    public ChipGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.checkedStateTracker = new CheckedStateTracker();
        this.passThroughListener = new PassThroughHierarchyChangeListener();
        this.checkedId = -1;
        this.protectFromCheckedChange = false;
        TypedArray a = ThemeEnforcement.obtainStyledAttributes(context, attrs, R.styleable.ChipGroup, defStyleAttr, R.style.Widget_MaterialComponents_ChipGroup, new int[0]);
        int chipSpacing = a.getDimensionPixelOffset(R.styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(a.getDimensionPixelOffset(R.styleable.ChipGroup_chipSpacingHorizontal, chipSpacing));
        setChipSpacingVertical(a.getDimensionPixelOffset(R.styleable.ChipGroup_chipSpacingVertical, chipSpacing));
        setSingleLine(a.getBoolean(R.styleable.ChipGroup_singleLine, false));
        setSingleSelection(a.getBoolean(R.styleable.ChipGroup_singleSelection, false));
        int checkedChip = a.getResourceId(R.styleable.ChipGroup_checkedChip, -1);
        if (checkedChip != -1) {
            this.checkedId = checkedChip;
        }
        a.recycle();
        super.setOnHierarchyChangeListener(this.passThroughListener);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParams(lp);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p) && (p instanceof LayoutParams);
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener listener) {
        this.passThroughListener.onHierarchyChangeListener = listener;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.checkedId;
        if (i != -1) {
            setCheckedStateForView(i, true);
            setCheckedId(this.checkedId);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof Chip) {
            Chip chip = (Chip) child;
            if (chip.isChecked()) {
                int i = this.checkedId;
                if (i != -1 && this.singleSelection) {
                    setCheckedStateForView(i, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(child, index, params);
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable divider) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable divider) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerHorizontal(int dividerMode) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int dividerMode) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int flexWrap) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void check(int id) {
        int i = this.checkedId;
        if (id != i) {
            if (i != -1 && this.singleSelection) {
                setCheckedStateForView(i, false);
            }
            if (id != -1) {
                setCheckedStateForView(id, true);
            }
            setCheckedId(id);
        }
    }

    public int getCheckedChipId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public void clearCheck() {
        this.protectFromCheckedChange = true;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof Chip) {
                ((Chip) child).setChecked(false);
            }
        }
        this.protectFromCheckedChange = false;
        setCheckedId(-1);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
        OnCheckedChangeListener onCheckedChangeListener = this.onCheckedChangeListener;
        if (onCheckedChangeListener != null && this.singleSelection) {
            onCheckedChangeListener.onCheckedChanged(this, checkedId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView instanceof Chip) {
            this.protectFromCheckedChange = true;
            ((Chip) checkedView).setChecked(checked);
            this.protectFromCheckedChange = false;
        }
    }

    public void setChipSpacing(int chipSpacing) {
        setChipSpacingHorizontal(chipSpacing);
        setChipSpacingVertical(chipSpacing);
    }

    public void setChipSpacingResource(int id) {
        setChipSpacing(getResources().getDimensionPixelOffset(id));
    }

    public int getChipSpacingHorizontal() {
        return this.chipSpacingHorizontal;
    }

    public void setChipSpacingHorizontal(int chipSpacingHorizontal) {
        if (this.chipSpacingHorizontal != chipSpacingHorizontal) {
            this.chipSpacingHorizontal = chipSpacingHorizontal;
            setItemSpacing(chipSpacingHorizontal);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int id) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(id));
    }

    public int getChipSpacingVertical() {
        return this.chipSpacingVertical;
    }

    public void setChipSpacingVertical(int chipSpacingVertical) {
        if (this.chipSpacingVertical != chipSpacingVertical) {
            this.chipSpacingVertical = chipSpacingVertical;
            setLineSpacing(chipSpacingVertical);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int id) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(id));
    }

    @Override // com.google.android.material.internal.FlowLayout
    public boolean isSingleLine() {
        return super.isSingleLine();
    }

    @Override // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean singleLine) {
        super.setSingleLine(singleLine);
    }

    public void setSingleLine(int id) {
        setSingleLine(getResources().getBoolean(id));
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        if (this.singleSelection != singleSelection) {
            this.singleSelection = singleSelection;
            clearCheck();
        }
    }

    public void setSingleSelection(int id) {
        setSingleSelection(getResources().getBoolean(id));
    }

    /* loaded from: classes.dex */
    private class CheckedStateTracker implements CompoundButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!ChipGroup.this.protectFromCheckedChange) {
                int id = buttonView.getId();
                if (isChecked) {
                    if (!(ChipGroup.this.checkedId == -1 || ChipGroup.this.checkedId == id || !ChipGroup.this.singleSelection)) {
                        ChipGroup chipGroup = ChipGroup.this;
                        chipGroup.setCheckedStateForView(chipGroup.checkedId, false);
                    }
                    ChipGroup.this.setCheckedId(id);
                } else if (ChipGroup.this.checkedId == id) {
                    ChipGroup.this.setCheckedId(-1);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener;

        private PassThroughHierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View parent, View child) {
            int id;
            if (parent == ChipGroup.this && (child instanceof Chip)) {
                int id2 = child.getId();
                if (id2 == -1) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        id = View.generateViewId();
                    } else {
                        id = child.hashCode();
                    }
                    child.setId(id);
                }
                ((Chip) child).setOnCheckedChangeListenerInternal(ChipGroup.this.checkedStateTracker);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(parent, child);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View parent, View child) {
            if (parent == ChipGroup.this && (child instanceof Chip)) {
                ((Chip) child).setOnCheckedChangeListenerInternal(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.onHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(parent, child);
            }
        }
    }
}
