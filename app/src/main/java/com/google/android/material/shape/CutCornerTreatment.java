package com.google.android.material.shape;

/* loaded from: classes.dex */
public class CutCornerTreatment extends CornerTreatment {
    float size;

    public CutCornerTreatment() {
        this.size = -1.0f;
    }

    @Deprecated
    public CutCornerTreatment(float size) {
        this.size = -1.0f;
        this.size = size;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath shapePath, float angle, float interpolation, float radius) {
        shapePath.reset(0.0f, radius * interpolation, 180.0f, 180.0f - angle);
        double sin = Math.sin(Math.toRadians(angle));
        double d = radius;
        Double.isNaN(d);
        double d2 = sin * d;
        double d3 = interpolation;
        Double.isNaN(d3);
        double sin2 = Math.sin(Math.toRadians(90.0f - angle));
        double d4 = radius;
        Double.isNaN(d4);
        double d5 = sin2 * d4;
        double d6 = interpolation;
        Double.isNaN(d6);
        shapePath.lineTo((float) (d2 * d3), (float) (d5 * d6));
    }
}
