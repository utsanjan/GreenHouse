package com.google.android.material.shape;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.internal.ViewUtils;

/* loaded from: classes.dex */
public class MaterialShapeUtils {
    private MaterialShapeUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CornerTreatment createCornerTreatment(int cornerFamily) {
        if (cornerFamily == 0) {
            return new RoundedCornerTreatment();
        }
        if (cornerFamily != 1) {
            return createDefaultCornerTreatment();
        }
        return new CutCornerTreatment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CornerTreatment createDefaultCornerTreatment() {
        return new RoundedCornerTreatment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EdgeTreatment createDefaultEdgeTreatment() {
        return new EdgeTreatment();
    }

    public static void setElevation(View view, float elevation) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).setElevation(elevation);
        }
    }

    public static void setParentAbsoluteElevation(View view) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            setParentAbsoluteElevation(view, (MaterialShapeDrawable) background);
        }
    }

    public static void setParentAbsoluteElevation(View view, MaterialShapeDrawable materialShapeDrawable) {
        if (materialShapeDrawable.isElevationOverlayEnabled()) {
            materialShapeDrawable.setParentAbsoluteElevation(ViewUtils.getParentAbsoluteElevation(view));
        }
    }
}
