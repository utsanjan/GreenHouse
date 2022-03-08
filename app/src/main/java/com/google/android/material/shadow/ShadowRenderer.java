package com.google.android.material.shadow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class ShadowRenderer {
    private static final int COLOR_ALPHA_END = 0;
    private static final int COLOR_ALPHA_MIDDLE = 20;
    private static final int COLOR_ALPHA_START = 68;
    private final Paint cornerShadowPaint;
    private final Paint edgeShadowPaint;
    private final Path scratch;
    private int shadowEndColor;
    private int shadowMiddleColor;
    private final Paint shadowPaint;
    private int shadowStartColor;
    private static final int[] edgeColors = new int[3];
    private static final float[] edgePositions = {0.0f, 0.5f, 1.0f};
    private static final int[] cornerColors = new int[4];
    private static final float[] cornerPositions = {0.0f, 0.0f, 0.5f, 1.0f};

    public ShadowRenderer() {
        this(ViewCompat.MEASURED_STATE_MASK);
    }

    public ShadowRenderer(int color) {
        this.scratch = new Path();
        setShadowColor(color);
        Paint paint = new Paint(4);
        this.cornerShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.shadowPaint = paint2;
        paint2.setColor(this.shadowStartColor);
        this.edgeShadowPaint = new Paint(this.cornerShadowPaint);
    }

    public void setShadowColor(int color) {
        this.shadowStartColor = ColorUtils.setAlphaComponent(color, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(color, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(color, 0);
    }

    public void drawEdgeShadow(Canvas canvas, Matrix transform, RectF bounds, int elevation) {
        bounds.bottom += elevation;
        bounds.offset(0.0f, -elevation);
        int[] iArr = edgeColors;
        iArr[0] = this.shadowEndColor;
        iArr[1] = this.shadowMiddleColor;
        iArr[2] = this.shadowStartColor;
        this.edgeShadowPaint.setShader(new LinearGradient(bounds.left, bounds.top, bounds.left, bounds.bottom, edgeColors, edgePositions, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(transform);
        canvas.drawRect(bounds, this.edgeShadowPaint);
        canvas.restore();
    }

    public void drawCornerShadow(Canvas canvas, Matrix matrix, RectF bounds, int elevation, float startAngle, float sweepAngle) {
        boolean drawShadowInsideBounds = sweepAngle < 0.0f;
        Path arcBounds = this.scratch;
        if (drawShadowInsideBounds) {
            int[] iArr = cornerColors;
            iArr[0] = 0;
            iArr[1] = this.shadowEndColor;
            iArr[2] = this.shadowMiddleColor;
            iArr[3] = this.shadowStartColor;
        } else {
            arcBounds.rewind();
            arcBounds.moveTo(bounds.centerX(), bounds.centerY());
            arcBounds.arcTo(bounds, startAngle, sweepAngle);
            arcBounds.close();
            bounds.inset(-elevation, -elevation);
            int[] iArr2 = cornerColors;
            iArr2[0] = 0;
            iArr2[1] = this.shadowStartColor;
            iArr2[2] = this.shadowMiddleColor;
            iArr2[3] = this.shadowEndColor;
        }
        float startRatio = 1.0f - (elevation / (bounds.width() / 2.0f));
        float midRatio = startRatio + ((1.0f - startRatio) / 2.0f);
        float[] fArr = cornerPositions;
        fArr[1] = startRatio;
        fArr[2] = midRatio;
        this.cornerShadowPaint.setShader(new RadialGradient(bounds.centerX(), bounds.centerY(), bounds.width() / 2.0f, cornerColors, cornerPositions, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!drawShadowInsideBounds) {
            canvas.clipPath(arcBounds, Region.Op.DIFFERENCE);
        }
        canvas.drawArc(bounds, startAngle, sweepAngle, true, this.cornerShadowPaint);
        canvas.restore();
    }

    public Paint getShadowPaint() {
        return this.shadowPaint;
    }
}
