package pl.droidsonroids.gif;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.widget.MediaController;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import pl.droidsonroids.gif.transforms.CornerRadiusTransform;
import pl.droidsonroids.gif.transforms.Transform;

/* loaded from: classes.dex */
public class GifDrawable extends Drawable implements Animatable, MediaController.MediaPlayerControl {
    final Bitmap mBuffer;
    private final Rect mDstRect;
    final ScheduledThreadPoolExecutor mExecutor;
    final InvalidationHandler mInvalidationHandler;
    final boolean mIsRenderingTriggeredOnDraw;
    volatile boolean mIsRunning;
    final ConcurrentLinkedQueue<AnimationListener> mListeners;
    final GifInfoHandle mNativeInfoHandle;
    long mNextFrameRenderTime;
    protected final Paint mPaint;
    private final RenderTask mRenderTask;
    ScheduledFuture<?> mRenderTaskSchedule;
    private int mScaledHeight;
    private int mScaledWidth;
    private final Rect mSrcRect;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff.Mode mTintMode;
    private Transform mTransform;

    public GifDrawable(Resources res, int id) throws Resources.NotFoundException, IOException {
        this(res.openRawResourceFd(id));
        float densityScale = GifViewUtils.getDensityScale(res, id);
        this.mScaledHeight = (int) (this.mNativeInfoHandle.getHeight() * densityScale);
        this.mScaledWidth = (int) (this.mNativeInfoHandle.getWidth() * densityScale);
    }

    public GifDrawable(AssetManager assets, String assetName) throws IOException {
        this(assets.openFd(assetName));
    }

    public GifDrawable(String filePath) throws IOException {
        this(new GifInfoHandle(filePath), null, null, true);
    }

    public GifDrawable(File file) throws IOException {
        this(file.getPath());
    }

    public GifDrawable(InputStream stream) throws IOException {
        this(new GifInfoHandle(stream), null, null, true);
    }

    public GifDrawable(AssetFileDescriptor afd) throws IOException {
        this(new GifInfoHandle(afd), null, null, true);
    }

    public GifDrawable(FileDescriptor fd) throws IOException {
        this(new GifInfoHandle(fd), null, null, true);
    }

    public GifDrawable(byte[] bytes) throws IOException {
        this(new GifInfoHandle(bytes), null, null, true);
    }

    public GifDrawable(ByteBuffer buffer) throws IOException {
        this(new GifInfoHandle(buffer), null, null, true);
    }

    public GifDrawable(ContentResolver resolver, Uri uri) throws IOException {
        this(GifInfoHandle.openUri(resolver, uri), null, null, true);
    }

    protected GifDrawable(InputSource inputSource, GifDrawable oldDrawable, ScheduledThreadPoolExecutor executor, boolean isRenderingTriggeredOnDraw, GifOptions options) throws IOException {
        this(inputSource.createHandleWith(options), oldDrawable, executor, isRenderingTriggeredOnDraw);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifDrawable(GifInfoHandle gifInfoHandle, GifDrawable oldDrawable, ScheduledThreadPoolExecutor executor, boolean isRenderingTriggeredOnDraw) {
        this.mIsRunning = true;
        this.mNextFrameRenderTime = Long.MIN_VALUE;
        this.mDstRect = new Rect();
        this.mPaint = new Paint(6);
        this.mListeners = new ConcurrentLinkedQueue<>();
        this.mRenderTask = new RenderTask(this);
        this.mIsRenderingTriggeredOnDraw = isRenderingTriggeredOnDraw;
        this.mExecutor = executor != null ? executor : GifRenderingExecutor.getInstance();
        this.mNativeInfoHandle = gifInfoHandle;
        Bitmap oldBitmap = null;
        if (oldDrawable != null) {
            synchronized (oldDrawable.mNativeInfoHandle) {
                if (!oldDrawable.mNativeInfoHandle.isRecycled() && oldDrawable.mNativeInfoHandle.getHeight() >= this.mNativeInfoHandle.getHeight() && oldDrawable.mNativeInfoHandle.getWidth() >= this.mNativeInfoHandle.getWidth()) {
                    oldDrawable.shutdown();
                    oldBitmap = oldDrawable.mBuffer;
                    oldBitmap.eraseColor(0);
                }
            }
        }
        if (oldBitmap == null) {
            this.mBuffer = Bitmap.createBitmap(this.mNativeInfoHandle.getWidth(), this.mNativeInfoHandle.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            this.mBuffer = oldBitmap;
        }
        this.mBuffer.setHasAlpha(true ^ gifInfoHandle.isOpaque());
        this.mSrcRect = new Rect(0, 0, this.mNativeInfoHandle.getWidth(), this.mNativeInfoHandle.getHeight());
        this.mInvalidationHandler = new InvalidationHandler(this);
        this.mRenderTask.doWork();
        this.mScaledWidth = this.mNativeInfoHandle.getWidth();
        this.mScaledHeight = this.mNativeInfoHandle.getHeight();
    }

    public void recycle() {
        shutdown();
        this.mBuffer.recycle();
    }

    private void shutdown() {
        this.mIsRunning = false;
        this.mInvalidationHandler.removeMessages(-1);
        this.mNativeInfoHandle.recycle();
    }

    public boolean isRecycled() {
        return this.mNativeInfoHandle.isRecycled();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        scheduleNextRender();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mScaledHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mScaledWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (!this.mNativeInfoHandle.isOpaque() || this.mPaint.getAlpha() < 255) {
            return -2;
        }
        return -1;
    }

    @Override // android.graphics.drawable.Animatable, android.widget.MediaController.MediaPlayerControl
    public void start() {
        synchronized (this) {
            if (!this.mIsRunning) {
                this.mIsRunning = true;
                long lastFrameRemainder = this.mNativeInfoHandle.restoreRemainder();
                startAnimation(lastFrameRemainder);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startAnimation(long lastFrameRemainder) {
        if (this.mIsRenderingTriggeredOnDraw) {
            this.mNextFrameRenderTime = 0L;
            this.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
            return;
        }
        cancelPendingRenderTask();
        this.mRenderTaskSchedule = this.mExecutor.schedule(this.mRenderTask, Math.max(lastFrameRemainder, 0L), TimeUnit.MILLISECONDS);
    }

    public void reset() {
        this.mExecutor.execute(new SafeRunnable(this) { // from class: pl.droidsonroids.gif.GifDrawable.1
            @Override // pl.droidsonroids.gif.SafeRunnable
            public void doWork() {
                if (GifDrawable.this.mNativeInfoHandle.reset()) {
                    GifDrawable.this.start();
                }
            }
        });
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        synchronized (this) {
            if (this.mIsRunning) {
                this.mIsRunning = false;
                cancelPendingRenderTask();
                this.mNativeInfoHandle.saveRemainder();
            }
        }
    }

    private void cancelPendingRenderTask() {
        ScheduledFuture<?> scheduledFuture = this.mRenderTaskSchedule;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.mInvalidationHandler.removeMessages(-1);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mIsRunning;
    }

    public String getComment() {
        return this.mNativeInfoHandle.getComment();
    }

    public int getLoopCount() {
        return this.mNativeInfoHandle.getLoopCount();
    }

    public void setLoopCount(int loopCount) {
        this.mNativeInfoHandle.setLoopCount(loopCount);
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "GIF: size: %dx%d, frames: %d, error: %d", Integer.valueOf(this.mNativeInfoHandle.getWidth()), Integer.valueOf(this.mNativeInfoHandle.getHeight()), Integer.valueOf(this.mNativeInfoHandle.getNumberOfFrames()), Integer.valueOf(this.mNativeInfoHandle.getNativeErrorCode()));
    }

    public int getNumberOfFrames() {
        return this.mNativeInfoHandle.getNumberOfFrames();
    }

    public GifError getError() {
        return GifError.fromCode(this.mNativeInfoHandle.getNativeErrorCode());
    }

    public static GifDrawable createFromResource(Resources res, int resourceId) {
        try {
            return new GifDrawable(res, resourceId);
        } catch (IOException e) {
            return null;
        }
    }

    public void setSpeed(float factor) {
        this.mNativeInfoHandle.setSpeedFactor(factor);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        stop();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        return this.mNativeInfoHandle.getDuration();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        return this.mNativeInfoHandle.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(final int position) {
        if (position >= 0) {
            this.mExecutor.execute(new SafeRunnable(this) { // from class: pl.droidsonroids.gif.GifDrawable.2
                @Override // pl.droidsonroids.gif.SafeRunnable
                public void doWork() {
                    GifDrawable.this.mNativeInfoHandle.seekToTime(position, GifDrawable.this.mBuffer);
                    this.mGifDrawable.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
                }
            });
            return;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    public void seekToBlocking(int position) {
        if (position >= 0) {
            synchronized (this.mNativeInfoHandle) {
                this.mNativeInfoHandle.seekToTime(position, this.mBuffer);
            }
            this.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
            return;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    public void seekToFrame(final int frameIndex) {
        if (frameIndex >= 0) {
            this.mExecutor.execute(new SafeRunnable(this) { // from class: pl.droidsonroids.gif.GifDrawable.3
                @Override // pl.droidsonroids.gif.SafeRunnable
                public void doWork() {
                    GifDrawable.this.mNativeInfoHandle.seekToFrame(frameIndex, GifDrawable.this.mBuffer);
                    GifDrawable.this.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
                }
            });
            return;
        }
        throw new IndexOutOfBoundsException("Frame index is not positive");
    }

    public Bitmap seekToFrameAndGet(int frameIndex) {
        Bitmap bitmap;
        if (frameIndex >= 0) {
            synchronized (this.mNativeInfoHandle) {
                this.mNativeInfoHandle.seekToFrame(frameIndex, this.mBuffer);
                bitmap = getCurrentFrame();
            }
            this.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
            return bitmap;
        }
        throw new IndexOutOfBoundsException("Frame index is not positive");
    }

    public Bitmap seekToPositionAndGet(int position) {
        Bitmap bitmap;
        if (position >= 0) {
            synchronized (this.mNativeInfoHandle) {
                this.mNativeInfoHandle.seekToTime(position, this.mBuffer);
                bitmap = getCurrentFrame();
            }
            this.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0L);
            return bitmap;
        }
        throw new IllegalArgumentException("Position is not positive");
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.mIsRunning;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        return 100;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return getNumberOfFrames() > 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return getNumberOfFrames() > 1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public int getFrameByteCount() {
        return this.mBuffer.getRowBytes() * this.mBuffer.getHeight();
    }

    public long getAllocationByteCount() {
        long byteCount = this.mNativeInfoHandle.getAllocationByteCount();
        if (Build.VERSION.SDK_INT >= 19) {
            return byteCount + this.mBuffer.getAllocationByteCount();
        }
        return byteCount + getFrameByteCount();
    }

    public long getMetadataAllocationByteCount() {
        return this.mNativeInfoHandle.getMetadataByteCount();
    }

    public long getInputSourceByteCount() {
        return this.mNativeInfoHandle.getSourceLength();
    }

    public void getPixels(int[] pixels) {
        this.mBuffer.getPixels(pixels, 0, this.mNativeInfoHandle.getWidth(), 0, 0, this.mNativeInfoHandle.getWidth(), this.mNativeInfoHandle.getHeight());
    }

    public int getPixel(int x, int y) {
        if (x >= this.mNativeInfoHandle.getWidth()) {
            throw new IllegalArgumentException("x must be < width");
        } else if (y < this.mNativeInfoHandle.getHeight()) {
            return this.mBuffer.getPixel(x, y);
        } else {
            throw new IllegalArgumentException("y must be < height");
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        this.mDstRect.set(bounds);
        Transform transform = this.mTransform;
        if (transform != null) {
            transform.onBoundsChange(bounds);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean clearColorFilter;
        if (this.mTintFilter == null || this.mPaint.getColorFilter() != null) {
            clearColorFilter = false;
        } else {
            this.mPaint.setColorFilter(this.mTintFilter);
            clearColorFilter = true;
        }
        Transform transform = this.mTransform;
        if (transform == null) {
            canvas.drawBitmap(this.mBuffer, this.mSrcRect, this.mDstRect, this.mPaint);
        } else {
            transform.onDraw(canvas, this.mPaint, this.mBuffer);
        }
        if (clearColorFilter) {
            this.mPaint.setColorFilter(null);
        }
    }

    private void scheduleNextRender() {
        if (this.mIsRenderingTriggeredOnDraw && this.mIsRunning) {
            long j = this.mNextFrameRenderTime;
            if (j != Long.MIN_VALUE) {
                long renderDelay = Math.max(0L, j - SystemClock.uptimeMillis());
                this.mNextFrameRenderTime = Long.MIN_VALUE;
                this.mExecutor.remove(this.mRenderTask);
                this.mRenderTaskSchedule = this.mExecutor.schedule(this.mRenderTask, renderDelay, TimeUnit.MILLISECONDS);
            }
        }
    }

    public final Paint getPaint() {
        return this.mPaint;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean filter) {
        this.mPaint.setFilterBitmap(filter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public void setDither(boolean dither) {
        this.mPaint.setDither(dither);
        invalidateSelf();
    }

    public void addAnimationListener(AnimationListener listener) {
        this.mListeners.add(listener);
    }

    public boolean removeAnimationListener(AnimationListener listener) {
        return this.mListeners.remove(listener);
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public Bitmap getCurrentFrame() {
        Bitmap bitmap = this.mBuffer;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), this.mBuffer.isMutable());
        copy.setHasAlpha(this.mBuffer.hasAlpha());
        return copy;
    }

    private PorterDuffColorFilter updateTintFilter(ColorStateList tint, PorterDuff.Mode tintMode) {
        if (tint == null || tintMode == null) {
            return null;
        }
        int color = tint.getColorForState(getState(), 0);
        return new PorterDuffColorFilter(color, tintMode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList tint) {
        this.mTint = tint;
        this.mTintFilter = updateTintFilter(tint, this.mTintMode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode tintMode) {
        this.mTintMode = tintMode;
        this.mTintFilter = updateTintFilter(this.mTint, tintMode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] stateSet) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.mTint;
        if (colorStateList == null || (mode = this.mTintMode) == null) {
            return false;
        }
        this.mTintFilter = updateTintFilter(colorStateList, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        return super.isStateful() || ((colorStateList = this.mTint) != null && colorStateList.isStateful());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean visible, boolean restart) {
        boolean changed = super.setVisible(visible, restart);
        if (!this.mIsRenderingTriggeredOnDraw) {
            if (visible) {
                if (restart) {
                    reset();
                }
                if (changed) {
                    start();
                }
            } else if (changed) {
                stop();
            }
        }
        return changed;
    }

    public int getCurrentFrameIndex() {
        return this.mNativeInfoHandle.getCurrentFrameIndex();
    }

    public int getCurrentLoop() {
        int currentLoop = this.mNativeInfoHandle.getCurrentLoop();
        if (currentLoop == 0 || currentLoop < this.mNativeInfoHandle.getLoopCount()) {
            return currentLoop;
        }
        return currentLoop - 1;
    }

    public boolean isAnimationCompleted() {
        return this.mNativeInfoHandle.isAnimationCompleted();
    }

    public int getFrameDuration(int index) {
        return this.mNativeInfoHandle.getFrameDuration(index);
    }

    public void setCornerRadius(float cornerRadius) {
        CornerRadiusTransform cornerRadiusTransform = new CornerRadiusTransform(cornerRadius);
        this.mTransform = cornerRadiusTransform;
        cornerRadiusTransform.onBoundsChange(this.mDstRect);
    }

    public float getCornerRadius() {
        Transform transform = this.mTransform;
        if (transform instanceof CornerRadiusTransform) {
            return ((CornerRadiusTransform) transform).getCornerRadius();
        }
        return 0.0f;
    }

    public void setTransform(Transform transform) {
        this.mTransform = transform;
        if (transform != null) {
            transform.onBoundsChange(this.mDstRect);
        }
    }

    public Transform getTransform() {
        return this.mTransform;
    }
}
