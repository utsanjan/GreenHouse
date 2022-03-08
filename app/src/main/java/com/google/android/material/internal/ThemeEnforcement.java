package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;

/* loaded from: classes.dex */
public final class ThemeEnforcement {
    private static final String APPCOMPAT_THEME_NAME = "Theme.AppCompat";
    private static final String MATERIAL_THEME_NAME = "Theme.MaterialComponents";
    private static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};
    private static final int[] MATERIAL_CHECK_ATTRS = {R.attr.colorPrimaryVariant};
    private static final int[] ANDROID_THEME_OVERLAY_ATTRS = {16842752, R.attr.theme};
    private static final int[] MATERIAL_THEME_OVERLAY_ATTR = {R.attr.materialThemeOverlay};

    private ThemeEnforcement() {
    }

    public static TypedArray obtainStyledAttributes(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        checkCompatibleTheme(context, set, defStyleAttr, defStyleRes);
        checkTextAppearance(context, set, attrs, defStyleAttr, defStyleRes, textAppearanceResIndices);
        return context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
    }

    public static TintTypedArray obtainTintedStyledAttributes(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        checkCompatibleTheme(context, set, defStyleAttr, defStyleRes);
        checkTextAppearance(context, set, attrs, defStyleAttr, defStyleRes, textAppearanceResIndices);
        return TintTypedArray.obtainStyledAttributes(context, set, attrs, defStyleAttr, defStyleRes);
    }

    private static void checkCompatibleTheme(Context context, AttributeSet set, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(set, R.styleable.ThemeEnforcement, defStyleAttr, defStyleRes);
        boolean enforceMaterialTheme = a.getBoolean(R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
        a.recycle();
        if (enforceMaterialTheme) {
            TypedValue isMaterialTheme = new TypedValue();
            boolean resolvedValue = context.getTheme().resolveAttribute(R.attr.isMaterialTheme, isMaterialTheme, true);
            if (!resolvedValue || (isMaterialTheme.type == 18 && isMaterialTheme.data == 0)) {
                checkMaterialTheme(context);
            }
        }
        checkAppCompatTheme(context);
    }

    private static void checkTextAppearance(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        TypedArray themeEnforcementAttrs = context.obtainStyledAttributes(set, R.styleable.ThemeEnforcement, defStyleAttr, defStyleRes);
        boolean validTextAppearance = false;
        boolean enforceTextAppearance = themeEnforcementAttrs.getBoolean(R.styleable.ThemeEnforcement_enforceTextAppearance, false);
        if (!enforceTextAppearance) {
            themeEnforcementAttrs.recycle();
            return;
        }
        if (textAppearanceResIndices != null && textAppearanceResIndices.length != 0) {
            validTextAppearance = isCustomTextAppearanceValid(context, set, attrs, defStyleAttr, defStyleRes, textAppearanceResIndices);
        } else if (themeEnforcementAttrs.getResourceId(R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1) {
            validTextAppearance = true;
        }
        themeEnforcementAttrs.recycle();
        if (!validTextAppearance) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
    }

    private static boolean isCustomTextAppearanceValid(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        TypedArray componentAttrs = context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
        for (int customTextAppearanceIndex : textAppearanceResIndices) {
            if (componentAttrs.getResourceId(customTextAppearanceIndex, -1) == -1) {
                componentAttrs.recycle();
                return false;
            }
        }
        componentAttrs.recycle();
        return true;
    }

    public static void checkAppCompatTheme(Context context) {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, APPCOMPAT_THEME_NAME);
    }

    public static void checkMaterialTheme(Context context) {
        checkTheme(context, MATERIAL_CHECK_ATTRS, MATERIAL_THEME_NAME);
    }

    public static boolean isAppCompatTheme(Context context) {
        return isTheme(context, APPCOMPAT_CHECK_ATTRS);
    }

    public static boolean isMaterialTheme(Context context) {
        return isTheme(context, MATERIAL_CHECK_ATTRS);
    }

    private static boolean isTheme(Context context, int[] themeAttributes) {
        TypedArray a = context.obtainStyledAttributes(themeAttributes);
        for (int i = 0; i < themeAttributes.length; i++) {
            if (!a.hasValue(i)) {
                a.recycle();
                return false;
            }
        }
        a.recycle();
        return true;
    }

    private static void checkTheme(Context context, int[] themeAttributes, String themeName) {
        if (!isTheme(context, themeAttributes)) {
            throw new IllegalArgumentException("The style on this component requires your app theme to be " + themeName + " (or a descendant).");
        }
    }

    public static Context createThemedContext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int materialThemeOverlayId = obtainMaterialThemeOverlayId(context, attrs, defStyleAttr, defStyleRes);
        if (materialThemeOverlayId == 0) {
            return context;
        }
        if ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == materialThemeOverlayId) {
            return context;
        }
        Context context2 = new ContextThemeWrapper(context, materialThemeOverlayId);
        int androidThemeOverlayId = obtainAndroidThemeOverlayId(context2, attrs);
        return androidThemeOverlayId != 0 ? new ContextThemeWrapper(context2, androidThemeOverlayId) : context2;
    }

    private static int obtainAndroidThemeOverlayId(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, ANDROID_THEME_OVERLAY_ATTRS);
        int androidThemeId = a.getResourceId(0, 0);
        int appThemeId = a.getResourceId(1, 0);
        a.recycle();
        if (androidThemeId != 0) {
            return androidThemeId;
        }
        return appThemeId;
    }

    private static int obtainMaterialThemeOverlayId(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = context.obtainStyledAttributes(attrs, MATERIAL_THEME_OVERLAY_ATTR, defStyleAttr, defStyleRes);
        int materialThemeOverlayId = a.getResourceId(0, 0);
        a.recycle();
        return materialThemeOverlayId;
    }
}
