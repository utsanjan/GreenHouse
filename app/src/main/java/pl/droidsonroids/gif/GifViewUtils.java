package pl.droidsonroids.gif;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GifViewUtils {
    static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";
    static final List<String> SUPPORTED_RESOURCE_TYPE_NAMES = Arrays.asList("raw", "drawable", "mipmap");

    private GifViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifImageViewAttributes initImageView(ImageView view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (attrs == null || view.isInEditMode()) {
            return new GifImageViewAttributes();
        }
        GifImageViewAttributes viewAttributes = new GifImageViewAttributes(view, attrs, defStyleAttr, defStyleRes);
        int loopCount = viewAttributes.mLoopCount;
        if (loopCount >= 0) {
            applyLoopCount(loopCount, view.getDrawable());
            applyLoopCount(loopCount, view.getBackground());
        }
        return viewAttributes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyLoopCount(int loopCount, Drawable drawable) {
        if (drawable instanceof GifDrawable) {
            ((GifDrawable) drawable).setLoopCount(loopCount);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean setResource(ImageView view, boolean isSrc, int resId) {
        Resources res = view.getResources();
        if (res != null) {
            try {
                String resourceTypeName = res.getResourceTypeName(resId);
                if (!SUPPORTED_RESOURCE_TYPE_NAMES.contains(resourceTypeName)) {
                    return false;
                }
                GifDrawable d = new GifDrawable(res, resId);
                if (isSrc) {
                    view.setImageDrawable(d);
                    return true;
                }
                view.setBackground(d);
                return true;
            } catch (Resources.NotFoundException e) {
            } catch (IOException e2) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean setGifImageUri(ImageView imageView, Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            imageView.setImageDrawable(new GifDrawable(imageView.getContext().getContentResolver(), uri));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getDensityScale(Resources res, int id) {
        int density;
        TypedValue value = new TypedValue();
        res.getValue(id, value, true);
        int resourceDensity = value.density;
        if (resourceDensity == 0) {
            density = 160;
        } else if (resourceDensity != 65535) {
            density = resourceDensity;
        } else {
            density = 0;
        }
        int targetDensity = res.getDisplayMetrics().densityDpi;
        if (density <= 0 || targetDensity <= 0) {
            return 1.0f;
        }
        return targetDensity / density;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class GifViewAttributes {
        boolean freezesAnimation;
        final int mLoopCount;

        /* JADX INFO: Access modifiers changed from: package-private */
        public GifViewAttributes(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            TypedArray gifViewAttributes = view.getContext().obtainStyledAttributes(attrs, R.styleable.GifView, defStyleAttr, defStyleRes);
            this.freezesAnimation = gifViewAttributes.getBoolean(R.styleable.GifView_freezesAnimation, false);
            this.mLoopCount = gifViewAttributes.getInt(R.styleable.GifView_loopCount, -1);
            gifViewAttributes.recycle();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public GifViewAttributes() {
            this.freezesAnimation = false;
            this.mLoopCount = -1;
        }
    }

    /* loaded from: classes.dex */
    static class GifImageViewAttributes extends GifViewAttributes {
        final int mBackgroundResId;
        final int mSourceResId;

        GifImageViewAttributes(ImageView view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(view, attrs, defStyleAttr, defStyleRes);
            this.mSourceResId = getResourceId(view, attrs, true);
            this.mBackgroundResId = getResourceId(view, attrs, false);
        }

        GifImageViewAttributes() {
            this.mSourceResId = 0;
            this.mBackgroundResId = 0;
        }

        private static int getResourceId(ImageView view, AttributeSet attrs, boolean isSrc) {
            int resId = attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS, isSrc ? "src" : "background", 0);
            if (resId > 0) {
                String resourceTypeName = view.getResources().getResourceTypeName(resId);
                if (GifViewUtils.SUPPORTED_RESOURCE_TYPE_NAMES.contains(resourceTypeName) && !GifViewUtils.setResource(view, isSrc, resId)) {
                    return resId;
                }
            }
            return 0;
        }
    }
}
