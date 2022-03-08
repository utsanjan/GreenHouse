package pl.droidsonroids.gif.transforms;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* loaded from: classes.dex */
public interface Transform {
    void onBoundsChange(Rect rect);

    void onDraw(Canvas canvas, Paint paint, Bitmap bitmap);
}
