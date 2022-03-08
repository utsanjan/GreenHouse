package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* loaded from: classes.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private float cradleVerticalOffset;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float fabMargin, float roundedCornerRadius, float cradleVerticalOffset) {
        this.fabMargin = fabMargin;
        this.roundedCornerRadius = roundedCornerRadius;
        this.cradleVerticalOffset = cradleVerticalOffset;
        if (cradleVerticalOffset >= 0.0f) {
            this.horizontalOffset = 0.0f;
            return;
        }
        throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float length, float center, float interpolation, ShapePath shapePath) {
        float f = this.fabDiameter;
        if (f == 0.0f) {
            shapePath.lineTo(length, 0.0f);
            return;
        }
        float cradleDiameter = (this.fabMargin * 2.0f) + f;
        float cradleRadius = cradleDiameter / 2.0f;
        float roundedCornerOffset = interpolation * this.roundedCornerRadius;
        float middle = center + this.horizontalOffset;
        float verticalOffset = (this.cradleVerticalOffset * interpolation) + ((1.0f - interpolation) * cradleRadius);
        float verticalOffsetRatio = verticalOffset / cradleRadius;
        if (verticalOffsetRatio >= 1.0f) {
            shapePath.lineTo(length, 0.0f);
            return;
        }
        float distanceBetweenCenters = cradleRadius + roundedCornerOffset;
        float distanceBetweenCentersSquared = distanceBetweenCenters * distanceBetweenCenters;
        float distanceY = verticalOffset + roundedCornerOffset;
        float distanceX = (float) Math.sqrt(distanceBetweenCentersSquared - (distanceY * distanceY));
        float leftRoundedCornerCircleX = middle - distanceX;
        float rightRoundedCornerCircleX = middle + distanceX;
        float cornerRadiusArcLength = (float) Math.toDegrees(Math.atan(distanceX / distanceY));
        float cutoutArcOffset = 90.0f - cornerRadiusArcLength;
        shapePath.lineTo(leftRoundedCornerCircleX, 0.0f);
        shapePath.addArc(leftRoundedCornerCircleX - roundedCornerOffset, 0.0f, leftRoundedCornerCircleX + roundedCornerOffset, roundedCornerOffset * 2.0f, 270.0f, cornerRadiusArcLength);
        shapePath.addArc(middle - cradleRadius, (-cradleRadius) - verticalOffset, middle + cradleRadius, cradleRadius - verticalOffset, 180.0f - cutoutArcOffset, (cutoutArcOffset * 2.0f) - 180.0f);
        shapePath.addArc(rightRoundedCornerCircleX - roundedCornerOffset, 0.0f, rightRoundedCornerCircleX + roundedCornerOffset, roundedCornerOffset * 2.0f, 270.0f - cornerRadiusArcLength, cornerRadiusArcLength);
        shapePath.lineTo(length, 0.0f);
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public void setFabDiameter(float fabDiameter) {
        this.fabDiameter = fabDiameter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setHorizontalOffset(float horizontalOffset) {
        this.horizontalOffset = horizontalOffset;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCradleVerticalOffset(float cradleVerticalOffset) {
        this.cradleVerticalOffset = cradleVerticalOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabCradleMargin(float fabMargin) {
        this.fabMargin = fabMargin;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFabCradleRoundedCornerRadius(float roundedCornerRadius) {
        this.roundedCornerRadius = roundedCornerRadius;
    }
}
