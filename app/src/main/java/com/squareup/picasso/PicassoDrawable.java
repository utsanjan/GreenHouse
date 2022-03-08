package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class PicassoDrawable extends BitmapDrawable {
    private static final Paint DEBUG_PAINT = new Paint();
    private static final float FADE_DURATION = 200.0f;
    int alpha = 255;
    boolean animating;
    private final boolean debugging;
    private final float density;
    private final Picasso.LoadedFrom loadedFrom;
    Drawable placeholder;
    long startTimeMillis;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setBitmap(ImageView target, Context context, Bitmap bitmap, Picasso.LoadedFrom loadedFrom, boolean noFade, boolean debugging) {
        Drawable placeholder = target.getDrawable();
        if (placeholder instanceof AnimationDrawable) {
            ((AnimationDrawable) placeholder).stop();
        }
        PicassoDrawable drawable = new PicassoDrawable(context, bitmap, placeholder, loadedFrom, noFade, debugging);
        target.setImageDrawable(drawable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setPlaceholder(ImageView target, Drawable placeholderDrawable) {
        target.setImageDrawable(placeholderDrawable);
        if (target.getDrawable() instanceof AnimationDrawable) {
            ((AnimationDrawable) target.getDrawable()).start();
        }
    }

    PicassoDrawable(Context context, Bitmap bitmap, Drawable placeholder, Picasso.LoadedFrom loadedFrom, boolean noFade, boolean debugging) {
        super(context.getResources(), bitmap);
        this.debugging = debugging;
        this.density = context.getResources().getDisplayMetrics().density;
        this.loadedFrom = loadedFrom;
        boolean fade = loadedFrom != Picasso.LoadedFrom.MEMORY && !noFade;
        if (fade) {
            this.placeholder = placeholder;
            this.animating = true;
            this.startTimeMillis = SystemClock.uptimeMillis();
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!this.animating) {
            super.draw(canvas);
        } else {
            float normalized = ((float) (SystemClock.uptimeMillis() - this.startTimeMillis)) / FADE_DURATION;
            if (normalized >= 1.0f) {
                this.animating = false;
                this.placeholder = null;
                super.draw(canvas);
            } else {
                Drawable drawable = this.placeholder;
                if (drawable != null) {
                    drawable.draw(canvas);
                }
                int partialAlpha = (int) (this.alpha * normalized);
                super.setAlpha(partialAlpha);
                super.draw(canvas);
                super.setAlpha(this.alpha);
                if (Build.VERSION.SDK_INT <= 10) {
                    invalidateSelf();
                }
            }
        }
        if (this.debugging) {
            drawDebugIndicator(canvas);
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        Drawable drawable = this.placeholder;
        if (drawable != null) {
            drawable.setAlpha(alpha);
        }
        super.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        Drawable drawable = this.placeholder;
        if (drawable != null) {
            drawable.setColorFilter(cf);
        }
        super.setColorFilter(cf);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Drawable drawable = this.placeholder;
        if (drawable != null) {
            drawable.setBounds(bounds);
        }
        super.onBoundsChange(bounds);
    }

    private void drawDebugIndicator(Canvas canvas) {
        DEBUG_PAINT.setColor(-1);
        Path path = getTrianglePath(new Point(0, 0), (int) (this.density * 16.0f));
        canvas.drawPath(path, DEBUG_PAINT);
        DEBUG_PAINT.setColor(this.loadedFrom.debugColor);
        Path path2 = getTrianglePath(new Point(0, 0), (int) (this.density * 15.0f));
        canvas.drawPath(path2, DEBUG_PAINT);
    }

    private static Path getTrianglePath(Point p1, int width) {
        Point p2 = new Point(p1.x + width, p1.y);
        Point p3 = new Point(p1.x, p1.y + width);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        return path;
    }
}
