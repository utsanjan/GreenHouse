package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    private static final int[] CHECKABLE_STATE_SET = {16842911};
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_Button;
    public static final int ICON_GRAVITY_END = 3;
    public static final int ICON_GRAVITY_START = 1;
    public static final int ICON_GRAVITY_TEXT_END = 4;
    public static final int ICON_GRAVITY_TEXT_START = 2;
    private static final String LOG_TAG = "MaterialButton";
    private boolean broadcasting;
    private boolean checked;
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private final MaterialButtonHelper materialButtonHelper;
    private final LinkedHashSet<OnCheckedChangeListener> onCheckedChangeListeners;
    private OnPressedChangeListener onPressedChangeListenerInternal;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IconGravity {
    }

    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(MaterialButton materialButton, boolean z);
    }

    /* loaded from: classes.dex */
    interface OnPressedChangeListener {
        void onPressedChanged(MaterialButton materialButton, boolean z);
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    public MaterialButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.materialButtonStyle);
    }

    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(ThemeEnforcement.createThemedContext(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        this.onCheckedChangeListeners = new LinkedHashSet<>();
        boolean z = false;
        this.checked = false;
        this.broadcasting = false;
        Context context2 = getContext();
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context2, attrs, R.styleable.MaterialButton, defStyleAttr, DEF_STYLE_RES, new int[0]);
        this.iconPadding = attributes.getDimensionPixelSize(R.styleable.MaterialButton_iconPadding, 0);
        this.iconTintMode = ViewUtils.parseTintMode(attributes.getInt(R.styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.iconTint = MaterialResources.getColorStateList(getContext(), attributes, R.styleable.MaterialButton_iconTint);
        this.icon = MaterialResources.getDrawable(getContext(), attributes, R.styleable.MaterialButton_icon);
        this.iconGravity = attributes.getInteger(R.styleable.MaterialButton_iconGravity, 1);
        this.iconSize = attributes.getDimensionPixelSize(R.styleable.MaterialButton_iconSize, 0);
        ShapeAppearanceModel shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attrs, defStyleAttr, DEF_STYLE_RES).build();
        MaterialButtonHelper materialButtonHelper = new MaterialButtonHelper(this, shapeAppearanceModel);
        this.materialButtonHelper = materialButtonHelper;
        materialButtonHelper.loadFromAttributes(attributes);
        attributes.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        updateIcon(this.icon != null ? true : z);
    }

    private String getA11yClassName() {
        return (isCheckable() ? CompoundButton.class : Button.class).getName();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(getA11yClassName());
        info.setCheckable(isCheckable());
        info.setChecked(isChecked());
        info.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintList(ColorStateList tint) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(tint);
        } else {
            super.setSupportBackgroundTintList(tint);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public ColorStateList getSupportBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintList();
        }
        return super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintMode(PorterDuff.Mode tintMode) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(tintMode);
        } else {
            super.setSupportBackgroundTintMode(tintMode);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintMode();
        }
        return super.getSupportBackgroundTintMode();
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList tintList) {
        setSupportBackgroundTintList(tintList);
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode tintMode) {
        setSupportBackgroundTintMode(tintMode);
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(color);
        } else {
            super.setBackgroundColor(color);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {
        setBackgroundDrawable(background);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int backgroundResourceId) {
        Drawable background = null;
        if (backgroundResourceId != 0) {
            background = AppCompatResources.getDrawable(getContext(), backgroundResourceId);
        }
        setBackgroundDrawable(background);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable background) {
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(background);
        } else if (background != getBackground()) {
            Log.w(LOG_TAG, "Do not set the background; MaterialButton manages its own background drawable.");
            this.materialButtonHelper.setBackgroundOverwritten();
            super.setBackgroundDrawable(background);
        } else {
            getBackground().setState(background.getState());
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        MaterialButtonHelper materialButtonHelper;
        super.onLayout(changed, left, top, right, bottom);
        if (Build.VERSION.SDK_INT == 21 && (materialButtonHelper = this.materialButtonHelper) != null) {
            materialButtonHelper.updateMaskBounds(bottom - top, right - left);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        updateIconPosition();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        super.onTextChanged(charSequence, i, i1, i2);
        updateIconPosition();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable());
    }

    @Override // android.view.View
    public void setElevation(float elevation) {
        super.setElevation(elevation);
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.getMaterialShapeDrawable().setElevation(elevation);
        }
    }

    private void updateIconPosition() {
        if (this.icon != null && getLayout() != null) {
            int i = this.iconGravity;
            boolean z = true;
            if (i == 1 || i == 3) {
                this.iconLeft = 0;
                updateIcon(false);
                return;
            }
            Paint textPaint = getPaint();
            String buttonText = getText().toString();
            if (getTransformationMethod() != null) {
                buttonText = getTransformationMethod().getTransformation(buttonText, this).toString();
            }
            int textWidth = Math.min((int) textPaint.measureText(buttonText), getLayout().getEllipsizedWidth());
            int localIconSize = this.iconSize;
            if (localIconSize == 0) {
                localIconSize = this.icon.getIntrinsicWidth();
            }
            int newIconLeft = (((((getMeasuredWidth() - textWidth) - ViewCompat.getPaddingEnd(this)) - localIconSize) - this.iconPadding) - ViewCompat.getPaddingStart(this)) / 2;
            boolean isLayoutRTL = isLayoutRTL();
            if (this.iconGravity != 4) {
                z = false;
            }
            if (isLayoutRTL != z) {
                newIconLeft = -newIconLeft;
            }
            if (this.iconLeft != newIconLeft) {
                this.iconLeft = newIconLeft;
                updateIcon(false);
            }
        }
    }

    private boolean isLayoutRTL() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInternalBackground(Drawable background) {
        super.setBackgroundDrawable(background);
    }

    public void setIconPadding(int iconPadding) {
        if (this.iconPadding != iconPadding) {
            this.iconPadding = iconPadding;
            setCompoundDrawablePadding(iconPadding);
        }
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public void setIconSize(int iconSize) {
        if (iconSize < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        } else if (this.iconSize != iconSize) {
            this.iconSize = iconSize;
            updateIcon(true);
        }
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public void setIcon(Drawable icon) {
        if (this.icon != icon) {
            this.icon = icon;
            updateIcon(true);
        }
    }

    public void setIconResource(int iconResourceId) {
        Drawable icon = null;
        if (iconResourceId != 0) {
            icon = AppCompatResources.getDrawable(getContext(), iconResourceId);
        }
        setIcon(icon);
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIconTint(ColorStateList iconTint) {
        if (this.iconTint != iconTint) {
            this.iconTint = iconTint;
            updateIcon(false);
        }
    }

    public void setIconTintResource(int iconTintResourceId) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), iconTintResourceId));
    }

    public ColorStateList getIconTint() {
        return this.iconTint;
    }

    public void setIconTintMode(PorterDuff.Mode iconTintMode) {
        if (this.iconTintMode != iconTintMode) {
            this.iconTintMode = iconTintMode;
            updateIcon(false);
        }
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.iconTintMode;
    }

    private void updateIcon(boolean needsIconUpdate) {
        Drawable drawable = this.icon;
        boolean hasIconChanged = false;
        if (drawable != null) {
            Drawable mutate = DrawableCompat.wrap(drawable).mutate();
            this.icon = mutate;
            DrawableCompat.setTintList(mutate, this.iconTint);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                DrawableCompat.setTintMode(this.icon, mode);
            }
            int width = this.iconSize;
            if (width == 0) {
                width = this.icon.getIntrinsicWidth();
            }
            int height = this.iconSize;
            if (height == 0) {
                height = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i = this.iconLeft;
            drawable2.setBounds(i, 0, i + width, height);
        }
        int width2 = this.iconGravity;
        boolean isIconStart = width2 == 1 || width2 == 2;
        if (needsIconUpdate) {
            resetIconDrawable(isIconStart);
            return;
        }
        Drawable[] existingDrawables = TextViewCompat.getCompoundDrawablesRelative(this);
        Drawable drawableStart = existingDrawables[0];
        Drawable drawableEnd = existingDrawables[2];
        if ((isIconStart && drawableStart != this.icon) || (!isIconStart && drawableEnd != this.icon)) {
            hasIconChanged = true;
        }
        if (hasIconChanged) {
            resetIconDrawable(isIconStart);
        }
    }

    private void resetIconDrawable(boolean isIconStart) {
        if (isIconStart) {
            TextViewCompat.setCompoundDrawablesRelative(this, this.icon, null, null, null);
        } else {
            TextViewCompat.setCompoundDrawablesRelative(this, null, null, this.icon, null);
        }
    }

    public void setRippleColor(ColorStateList rippleColor) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setRippleColor(rippleColor);
        }
    }

    public void setRippleColorResource(int rippleColorResourceId) {
        if (isUsingOriginalBackground()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), rippleColorResourceId));
        }
    }

    public ColorStateList getRippleColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getRippleColor();
        }
        return null;
    }

    public void setStrokeColor(ColorStateList strokeColor) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeColor(strokeColor);
        }
    }

    public void setStrokeColorResource(int strokeColorResourceId) {
        if (isUsingOriginalBackground()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), strokeColorResourceId));
        }
    }

    public ColorStateList getStrokeColor() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeColor();
        }
        return null;
    }

    public void setStrokeWidth(int strokeWidth) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setStrokeWidth(strokeWidth);
        }
    }

    public void setStrokeWidthResource(int strokeWidthResourceId) {
        if (isUsingOriginalBackground()) {
            setStrokeWidth(getResources().getDimensionPixelSize(strokeWidthResourceId));
        }
    }

    public int getStrokeWidth() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getStrokeWidth();
        }
        return 0;
    }

    public void setCornerRadius(int cornerRadius) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCornerRadius(cornerRadius);
        }
    }

    public void setCornerRadiusResource(int cornerRadiusResourceId) {
        if (isUsingOriginalBackground()) {
            setCornerRadius(getResources().getDimensionPixelSize(cornerRadiusResourceId));
        }
    }

    public int getCornerRadius() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getCornerRadius();
        }
        return 0;
    }

    public int getIconGravity() {
        return this.iconGravity;
    }

    public void setIconGravity(int iconGravity) {
        if (this.iconGravity != iconGravity) {
            this.iconGravity = iconGravity;
            updateIconPosition();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 2);
        if (isCheckable()) {
            mergeDrawableStates(drawableState, CHECKABLE_STATE_SET);
        }
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public void addOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListeners.add(listener);
    }

    public void removeOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.onCheckedChangeListeners.remove(listener);
    }

    public void clearOnCheckedChangeListeners() {
        this.onCheckedChangeListeners.clear();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean checked) {
        if (isCheckable() && isEnabled() && this.checked != checked) {
            this.checked = checked;
            refreshDrawableState();
            if (!this.broadcasting) {
                this.broadcasting = true;
                Iterator<OnCheckedChangeListener> it = this.onCheckedChangeListeners.iterator();
                while (it.hasNext()) {
                    OnCheckedChangeListener listener = it.next();
                    listener.onCheckedChanged(this, this.checked);
                }
                this.broadcasting = false;
            }
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.checked);
    }

    @Override // android.view.View
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public boolean isCheckable() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return materialButtonHelper != null && materialButtonHelper.isCheckable();
    }

    public void setCheckable(boolean checkable) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setCheckable(checkable);
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShapeAppearanceModel(shapeAppearanceModel);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    @Override // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getShapeAppearanceModel();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnPressedChangeListenerInternal(OnPressedChangeListener listener) {
        this.onPressedChangeListenerInternal = listener;
    }

    @Override // android.view.View
    public void setPressed(boolean pressed) {
        OnPressedChangeListener onPressedChangeListener = this.onPressedChangeListenerInternal;
        if (onPressedChangeListener != null) {
            onPressedChangeListener.onPressedChanged(this, pressed);
        }
        super.setPressed(pressed);
    }

    private boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        return materialButtonHelper != null && !materialButtonHelper.isBackgroundOverwritten();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setShouldDrawSurfaceColorStroke(boolean shouldDrawSurfaceColorStroke) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShouldDrawSurfaceColorStroke(shouldDrawSurfaceColorStroke);
        }
    }
}
