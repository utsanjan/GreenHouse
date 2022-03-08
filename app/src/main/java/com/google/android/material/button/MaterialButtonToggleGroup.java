package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    private static final String LOG_TAG = MaterialButtonToggleGroup.class.getSimpleName();
    private int checkedId;
    private final CheckedStateTracker checkedStateTracker;
    private Integer[] childOrder;
    private final Comparator<MaterialButton> childOrderComparator;
    private final LinkedHashSet<OnButtonCheckedListener> onButtonCheckedListeners;
    private final List<CornerData> originalCornerData;
    private final PressedStateTracker pressedStateTracker;
    private boolean singleSelection;
    private boolean skipCheckedStateTracker;

    /* loaded from: classes.dex */
    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.materialButtonToggleGroupStyle);
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.originalCornerData = new ArrayList();
        this.checkedStateTracker = new CheckedStateTracker();
        this.pressedStateTracker = new PressedStateTracker();
        this.onButtonCheckedListeners = new LinkedHashSet<>();
        this.childOrderComparator = new Comparator<MaterialButton>() { // from class: com.google.android.material.button.MaterialButtonToggleGroup.1
            public int compare(MaterialButton v1, MaterialButton v2) {
                int checked = Boolean.valueOf(v1.isChecked()).compareTo(Boolean.valueOf(v2.isChecked()));
                if (checked != 0) {
                    return checked;
                }
                int stateful = Boolean.valueOf(v1.isPressed()).compareTo(Boolean.valueOf(v2.isPressed()));
                if (stateful != 0) {
                    return stateful;
                }
                return Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(v1)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(v2)));
            }
        };
        this.skipCheckedStateTracker = false;
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context, attrs, R.styleable.MaterialButtonToggleGroup, defStyleAttr, R.style.Widget_MaterialComponents_MaterialButtonToggleGroup, new int[0]);
        setSingleSelection(attributes.getBoolean(R.styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.checkedId = attributes.getResourceId(R.styleable.MaterialButtonToggleGroup_checkedButton, -1);
        setChildrenDrawingOrderEnabled(true);
        attributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = this.checkedId;
        if (i != -1) {
            checkForced(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        updateChildOrder();
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(child instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(child, index, params);
        MaterialButton buttonChild = (MaterialButton) child;
        setGeneratedIdIfNeeded(buttonChild);
        setupButtonChild(buttonChild);
        if (buttonChild.isChecked()) {
            updateCheckedStates(buttonChild.getId(), true);
            setCheckedId(buttonChild.getId());
        }
        ShapeAppearanceModel shapeAppearanceModel = buttonChild.getShapeAppearanceModel();
        this.originalCornerData.add(new CornerData(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel.getBottomRightCornerSize()));
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (child instanceof MaterialButton) {
            ((MaterialButton) child).removeOnCheckedChangeListener(this.checkedStateTracker);
            ((MaterialButton) child).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(child);
        if (indexOfChild >= 0) {
            this.originalCornerData.remove(indexOfChild);
        }
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        updateChildShapes();
        adjustChildMarginsAndUpdateLayout();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public void check(int id) {
        if (id != this.checkedId) {
            checkForced(id);
        }
    }

    public void uncheck(int id) {
        setCheckedStateForView(id, false);
        updateCheckedStates(id, false);
        this.checkedId = -1;
        dispatchOnButtonChecked(id, false);
    }

    public void clearChecked() {
        this.skipCheckedStateTracker = true;
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = getChildButton(i);
            child.setChecked(false);
            dispatchOnButtonChecked(child.getId(), false);
        }
        this.skipCheckedStateTracker = false;
        setCheckedId(-1);
    }

    public int getCheckedButtonId() {
        if (this.singleSelection) {
            return this.checkedId;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList<Integer> checkedIds = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = getChildButton(i);
            if (child.isChecked()) {
                checkedIds.add(Integer.valueOf(child.getId()));
            }
        }
        return checkedIds;
    }

    public void addOnButtonCheckedListener(OnButtonCheckedListener listener) {
        this.onButtonCheckedListeners.add(listener);
    }

    public void removeOnButtonCheckedListener(OnButtonCheckedListener listener) {
        this.onButtonCheckedListeners.remove(listener);
    }

    public void clearOnButtonCheckedListeners() {
        this.onButtonCheckedListeners.clear();
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        if (this.singleSelection != singleSelection) {
            this.singleSelection = singleSelection;
            clearChecked();
        }
    }

    public void setSingleSelection(int id) {
        setSingleSelection(getResources().getBoolean(id));
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView instanceof MaterialButton) {
            this.skipCheckedStateTracker = true;
            ((MaterialButton) checkedView).setChecked(checked);
            this.skipCheckedStateTracker = false;
        }
    }

    private void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
        dispatchOnButtonChecked(checkedId, true);
    }

    private void adjustChildMarginsAndUpdateLayout() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex != -1) {
            for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
                MaterialButton currentButton = getChildButton(i);
                MaterialButton previousButton = getChildButton(i - 1);
                int smallestStrokeWidth = Math.min(currentButton.getStrokeWidth(), previousButton.getStrokeWidth());
                LinearLayout.LayoutParams params = buildLayoutParams(currentButton);
                if (getOrientation() == 0) {
                    MarginLayoutParamsCompat.setMarginEnd(params, 0);
                    MarginLayoutParamsCompat.setMarginStart(params, -smallestStrokeWidth);
                } else {
                    params.bottomMargin = 0;
                    params.topMargin = -smallestStrokeWidth;
                }
                currentButton.setLayoutParams(params);
            }
            resetChildMargins(firstVisibleChildIndex);
        }
    }

    private MaterialButton getChildButton(int index) {
        return (MaterialButton) getChildAt(index);
    }

    private void resetChildMargins(int childIndex) {
        if (getChildCount() != 0 && childIndex != -1) {
            MaterialButton currentButton = getChildButton(childIndex);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) currentButton.getLayoutParams();
            if (getOrientation() == 1) {
                params.topMargin = 0;
                params.bottomMargin = 0;
                return;
            }
            MarginLayoutParamsCompat.setMarginEnd(params, 0);
            MarginLayoutParamsCompat.setMarginStart(params, 0);
            params.leftMargin = 0;
            params.rightMargin = 0;
        }
    }

    void updateChildShapes() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton button = getChildButton(i);
            if (button.getVisibility() != 8) {
                ShapeAppearanceModel.Builder builder = button.getShapeAppearanceModel().toBuilder();
                CornerData newCornerData = getNewCornerData(i, firstVisibleChildIndex, lastVisibleChildIndex);
                updateBuilderWithCornerData(builder, newCornerData);
                button.setShapeAppearanceModel(builder.build());
            }
        }
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isChildVisible(int i) {
        View child = getChildAt(i);
        return child.getVisibility() != 8;
    }

    private CornerData getNewCornerData(int index, int firstVisibleChildIndex, int lastVisibleChildIndex) {
        int childCount = getChildCount();
        CornerData cornerData = this.originalCornerData.get(index);
        boolean isHorizontal = true;
        if (childCount == 1) {
            return cornerData;
        }
        if (getOrientation() != 0) {
            isHorizontal = false;
        }
        if (index == firstVisibleChildIndex) {
            return isHorizontal ? CornerData.start(cornerData, this) : CornerData.top(cornerData);
        }
        if (index == lastVisibleChildIndex) {
            return isHorizontal ? CornerData.end(cornerData, this) : CornerData.bottom(cornerData);
        }
        return null;
    }

    private static void updateBuilderWithCornerData(ShapeAppearanceModel.Builder shapeAppearanceModelBuilder, CornerData cornerData) {
        if (cornerData == null) {
            shapeAppearanceModelBuilder.setAllCornerSizes(0.0f);
        } else {
            shapeAppearanceModelBuilder.setTopLeftCornerSize(cornerData.topLeft).setBottomLeftCornerSize(cornerData.bottomLeft).setTopRightCornerSize(cornerData.topRight).setBottomRightCornerSize(cornerData.bottomRight);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCheckedStates(int childId, boolean childIsChecked) {
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton button = getChildButton(i);
            if (button.isChecked() && this.singleSelection && childIsChecked && button.getId() != childId) {
                setCheckedStateForView(button.getId(), false);
                dispatchOnButtonChecked(button.getId(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnButtonChecked(int buttonId, boolean checked) {
        Iterator<OnButtonCheckedListener> it = this.onButtonCheckedListeners.iterator();
        while (it.hasNext()) {
            OnButtonCheckedListener listener = it.next();
            listener.onButtonChecked(this, buttonId, checked);
        }
    }

    private void checkForced(int checkedId) {
        setCheckedStateForView(checkedId, true);
        updateCheckedStates(checkedId, true);
        setCheckedId(checkedId);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton buttonChild) {
        buttonChild.setMaxLines(1);
        buttonChild.setEllipsize(TextUtils.TruncateAt.END);
        buttonChild.setCheckable(true);
        buttonChild.addOnCheckedChangeListener(this.checkedStateTracker);
        buttonChild.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        buttonChild.setShouldDrawSurfaceColorStroke(true);
    }

    private LinearLayout.LayoutParams buildLayoutParams(View child) {
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) layoutParams;
        }
        LinearLayout.LayoutParams newParams = new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
        return newParams;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int i) {
        Integer[] numArr = this.childOrder;
        if (numArr != null && i < numArr.length) {
            return numArr[i].intValue();
        }
        Log.w(LOG_TAG, "Child order wasn't updated");
        return i;
    }

    private void updateChildOrder() {
        SortedMap<MaterialButton, Integer> viewToIndexMap = new TreeMap<>(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewToIndexMap.put(getChildButton(i), Integer.valueOf(i));
        }
        this.childOrder = (Integer[]) viewToIndexMap.values().toArray(new Integer[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckedStateTracker implements MaterialButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnCheckedChangeListener
        public void onCheckedChanged(MaterialButton button, boolean isChecked) {
            if (!MaterialButtonToggleGroup.this.skipCheckedStateTracker) {
                if (MaterialButtonToggleGroup.this.singleSelection) {
                    MaterialButtonToggleGroup.this.checkedId = isChecked ? button.getId() : -1;
                }
                MaterialButtonToggleGroup.this.dispatchOnButtonChecked(button.getId(), isChecked);
                MaterialButtonToggleGroup.this.updateCheckedStates(button.getId(), isChecked);
                MaterialButtonToggleGroup.this.invalidate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        private PressedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void onPressedChanged(MaterialButton button, boolean isPressed) {
            MaterialButtonToggleGroup.this.updateCheckedStates(button.getId(), button.isChecked());
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CornerData {
        private static final CornerSize noCorner = new AbsoluteCornerSize(0.0f);
        CornerSize bottomLeft;
        CornerSize bottomRight;
        CornerSize topLeft;
        CornerSize topRight;

        CornerData(CornerSize topLeft, CornerSize bottomLeft, CornerSize topRight, CornerSize bottomRight) {
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomRight = bottomRight;
            this.bottomLeft = bottomLeft;
        }

        public static CornerData start(CornerData orig, View view) {
            return ViewUtils.isLayoutRtl(view) ? right(orig) : left(orig);
        }

        public static CornerData end(CornerData orig, View view) {
            return ViewUtils.isLayoutRtl(view) ? left(orig) : right(orig);
        }

        public static CornerData left(CornerData orig) {
            CornerSize cornerSize = orig.topLeft;
            CornerSize cornerSize2 = orig.bottomLeft;
            CornerSize cornerSize3 = noCorner;
            return new CornerData(cornerSize, cornerSize2, cornerSize3, cornerSize3);
        }

        public static CornerData right(CornerData orig) {
            CornerSize cornerSize = noCorner;
            return new CornerData(cornerSize, cornerSize, orig.topRight, orig.bottomRight);
        }

        public static CornerData top(CornerData orig) {
            CornerSize cornerSize = orig.topLeft;
            CornerSize cornerSize2 = noCorner;
            return new CornerData(cornerSize, cornerSize2, orig.topRight, cornerSize2);
        }

        public static CornerData bottom(CornerData orig) {
            CornerSize cornerSize = noCorner;
            return new CornerData(cornerSize, orig.bottomLeft, cornerSize, orig.bottomRight);
        }
    }
}
