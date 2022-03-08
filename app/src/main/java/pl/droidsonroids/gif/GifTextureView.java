package pl.droidsonroids.gif;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.widget.ImageView;
import java.io.IOException;
import java.lang.ref.WeakReference;
import pl.droidsonroids.gif.GifViewUtils;
import pl.droidsonroids.gif.InputSource;

/* loaded from: classes.dex */
public class GifTextureView extends TextureView {
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private InputSource mInputSource;
    private RenderThread mRenderThread;
    private GifViewUtils.GifViewAttributes viewAttributes;
    private ImageView.ScaleType mScaleType = ImageView.ScaleType.FIT_CENTER;
    private final Matrix mTransform = new Matrix();
    private float mSpeedFactor = 1.0f;

    /* loaded from: classes.dex */
    public interface PlaceholderDrawListener {
        void onDrawPlaceholder(Canvas canvas);
    }

    public GifTextureView(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public GifTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    public GifTextureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (attrs != null) {
            int scaleTypeIndex = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "scaleType", -1);
            if (scaleTypeIndex >= 0) {
                ImageView.ScaleType[] scaleTypeArr = sScaleTypeArray;
                if (scaleTypeIndex < scaleTypeArr.length) {
                    this.mScaleType = scaleTypeArr[scaleTypeIndex];
                }
            }
            TypedArray textureViewAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.GifTextureView, defStyleAttr, defStyleRes);
            this.mInputSource = findSource(textureViewAttributes);
            super.setOpaque(textureViewAttributes.getBoolean(R.styleable.GifTextureView_isOpaque, false));
            textureViewAttributes.recycle();
            this.viewAttributes = new GifViewUtils.GifViewAttributes(this, attrs, defStyleAttr, defStyleRes);
        } else {
            super.setOpaque(false);
            this.viewAttributes = new GifViewUtils.GifViewAttributes();
        }
        if (!isInEditMode()) {
            RenderThread renderThread = new RenderThread(this);
            this.mRenderThread = renderThread;
            if (this.mInputSource != null) {
                renderThread.start();
            }
        }
    }

    @Override // android.view.TextureView
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener listener) {
        throw new UnsupportedOperationException("Changing SurfaceTextureListener is not supported");
    }

    @Override // android.view.TextureView
    public TextureView.SurfaceTextureListener getSurfaceTextureListener() {
        return null;
    }

    @Override // android.view.TextureView
    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        throw new UnsupportedOperationException("Changing SurfaceTexture is not supported");
    }

    private static InputSource findSource(TypedArray textureViewAttributes) {
        TypedValue value = new TypedValue();
        if (!textureViewAttributes.getValue(R.styleable.GifTextureView_gifSource, value)) {
            return null;
        }
        if (value.resourceId != 0) {
            String resourceTypeName = textureViewAttributes.getResources().getResourceTypeName(value.resourceId);
            if (GifViewUtils.SUPPORTED_RESOURCE_TYPE_NAMES.contains(resourceTypeName)) {
                return new InputSource.ResourcesSource(textureViewAttributes.getResources(), value.resourceId);
            }
            if (!"string".equals(resourceTypeName)) {
                throw new IllegalArgumentException("Expected string, drawable, mipmap or raw resource type. '" + resourceTypeName + "' is not supported");
            }
        }
        return new InputSource.AssetSource(textureViewAttributes.getResources().getAssets(), value.string.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class RenderThread extends Thread implements TextureView.SurfaceTextureListener {
        final ConditionVariable isSurfaceValid = new ConditionVariable();
        private GifInfoHandle mGifInfoHandle = new GifInfoHandle();
        private final WeakReference<GifTextureView> mGifTextureViewReference;
        private IOException mIOException;
        long[] mSavedState;

        RenderThread(GifTextureView gifTextureView) {
            super("GifRenderThread");
            this.mGifTextureViewReference = new WeakReference<>(gifTextureView);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                GifTextureView gifTextureView = this.mGifTextureViewReference.get();
                if (gifTextureView != null) {
                    GifInfoHandle open = gifTextureView.mInputSource.open();
                    this.mGifInfoHandle = open;
                    open.setOptions((char) 1, gifTextureView.isOpaque());
                    if (gifTextureView.viewAttributes.mLoopCount >= 0) {
                        this.mGifInfoHandle.setLoopCount(gifTextureView.viewAttributes.mLoopCount);
                    }
                    final GifTextureView gifTextureView2 = this.mGifTextureViewReference.get();
                    if (gifTextureView2 == null) {
                        this.mGifInfoHandle.recycle();
                        return;
                    }
                    gifTextureView2.setSuperSurfaceTextureListener(this);
                    boolean isSurfaceAvailable = gifTextureView2.isAvailable();
                    this.isSurfaceValid.set(isSurfaceAvailable);
                    if (isSurfaceAvailable) {
                        gifTextureView2.post(new Runnable() { // from class: pl.droidsonroids.gif.GifTextureView.RenderThread.1
                            @Override // java.lang.Runnable
                            public void run() {
                                gifTextureView2.updateTextureViewSize(RenderThread.this.mGifInfoHandle);
                            }
                        });
                    }
                    this.mGifInfoHandle.setSpeedFactor(gifTextureView2.mSpeedFactor);
                    while (!isInterrupted()) {
                        try {
                            this.isSurfaceValid.block();
                            GifTextureView currentGifTextureView = this.mGifTextureViewReference.get();
                            if (currentGifTextureView == null) {
                                break;
                            }
                            SurfaceTexture surfaceTexture = currentGifTextureView.getSurfaceTexture();
                            if (surfaceTexture != null) {
                                Surface surface = new Surface(surfaceTexture);
                                try {
                                    this.mGifInfoHandle.bindSurface(surface, this.mSavedState);
                                } finally {
                                    surface.release();
                                }
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    this.mGifInfoHandle.recycle();
                    this.mGifInfoHandle = new GifInfoHandle();
                }
            } catch (IOException ex) {
                this.mIOException = ex;
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            GifTextureView gifTextureView = this.mGifTextureViewReference.get();
            if (gifTextureView != null) {
                gifTextureView.updateTextureViewSize(this.mGifInfoHandle);
            }
            this.isSurfaceValid.open();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            this.isSurfaceValid.close();
            this.mGifInfoHandle.postUnbindSurface();
            interrupt();
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }

        void dispose(GifTextureView gifTextureView, PlaceholderDrawListener drawer) {
            this.isSurfaceValid.close();
            TextureView.SurfaceTextureListener listener = drawer != null ? new PlaceholderDrawingSurfaceTextureListener(drawer) : null;
            gifTextureView.setSuperSurfaceTextureListener(listener);
            this.mGifInfoHandle.postUnbindSurface();
            interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSuperSurfaceTextureListener(TextureView.SurfaceTextureListener listener) {
        super.setSurfaceTextureListener(listener);
    }

    @Override // android.view.TextureView
    public void setOpaque(boolean opaque) {
        if (opaque != isOpaque()) {
            super.setOpaque(opaque);
            setInputSource(this.mInputSource);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.mRenderThread.dispose(this, null);
        super.onDetachedFromWindow();
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }

    public synchronized void setInputSource(InputSource inputSource) {
        setInputSource(inputSource, null);
    }

    public synchronized void setInputSource(InputSource inputSource, PlaceholderDrawListener placeholderDrawListener) {
        this.mRenderThread.dispose(this, placeholderDrawListener);
        try {
            this.mRenderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mInputSource = inputSource;
        RenderThread renderThread = new RenderThread(this);
        this.mRenderThread = renderThread;
        if (inputSource != null) {
            renderThread.start();
        }
    }

    public void setSpeed(float factor) {
        this.mSpeedFactor = factor;
        this.mRenderThread.mGifInfoHandle.setSpeedFactor(factor);
    }

    public IOException getIOException() {
        return this.mRenderThread.mIOException != null ? this.mRenderThread.mIOException : GifIOException.fromCode(this.mRenderThread.mGifInfoHandle.getNativeErrorCode());
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        updateTextureViewSize(this.mRenderThread.mGifInfoHandle);
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTextureViewSize(GifInfoHandle gifInfoHandle) {
        float scaleRef;
        Matrix transform = new Matrix();
        float viewWidth = getWidth();
        float viewHeight = getHeight();
        float scaleX = gifInfoHandle.getWidth() / viewWidth;
        float scaleY = gifInfoHandle.getHeight() / viewHeight;
        RectF src = new RectF(0.0f, 0.0f, gifInfoHandle.getWidth(), gifInfoHandle.getHeight());
        RectF dst = new RectF(0.0f, 0.0f, viewWidth, viewHeight);
        switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()]) {
            case 1:
                transform.setScale(scaleX, scaleY, viewWidth / 2.0f, viewHeight / 2.0f);
                break;
            case 2:
                float scaleRef2 = 1.0f / Math.min(scaleX, scaleY);
                transform.setScale(scaleRef2 * scaleX, scaleRef2 * scaleY, viewWidth / 2.0f, viewHeight / 2.0f);
                break;
            case 3:
                if (gifInfoHandle.getWidth() > viewWidth || gifInfoHandle.getHeight() > viewHeight) {
                    scaleRef = Math.min(1.0f / scaleX, 1.0f / scaleY);
                } else {
                    scaleRef = 1.0f;
                }
                transform.setScale(scaleRef * scaleX, scaleRef * scaleY, viewWidth / 2.0f, viewHeight / 2.0f);
                break;
            case 4:
                transform.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
                transform.preScale(scaleX, scaleY);
                break;
            case 5:
                transform.setRectToRect(src, dst, Matrix.ScaleToFit.END);
                transform.preScale(scaleX, scaleY);
                break;
            case 6:
                transform.setRectToRect(src, dst, Matrix.ScaleToFit.START);
                transform.preScale(scaleX, scaleY);
                break;
            case 7:
                return;
            case 8:
                transform.set(this.mTransform);
                transform.preScale(scaleX, scaleY);
                break;
        }
        super.setTransform(transform);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: pl.droidsonroids.gif.GifTextureView$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            $SwitchMap$android$widget$ImageView$ScaleType = iArr;
            try {
                iArr[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public void setImageMatrix(Matrix matrix) {
        setTransform(matrix);
    }

    @Override // android.view.TextureView
    public void setTransform(Matrix transform) {
        this.mTransform.set(transform);
        updateTextureViewSize(this.mRenderThread.mGifInfoHandle);
    }

    @Override // android.view.TextureView
    public Matrix getTransform(Matrix transform) {
        if (transform == null) {
            transform = new Matrix();
        }
        transform.set(this.mTransform);
        return transform;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        RenderThread renderThread = this.mRenderThread;
        renderThread.mSavedState = renderThread.mGifInfoHandle.getSavedState();
        return new GifViewSavedState(super.onSaveInstanceState(), this.viewAttributes.freezesAnimation ? this.mRenderThread.mSavedState : null);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        GifViewSavedState ss = (GifViewSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.mRenderThread.mSavedState = ss.mStates[0];
    }

    public void setFreezesAnimation(boolean freezesAnimation) {
        this.viewAttributes.freezesAnimation = freezesAnimation;
    }
}
