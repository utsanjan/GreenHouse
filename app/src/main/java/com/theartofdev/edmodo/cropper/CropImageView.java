package com.theartofdev.edmodo.cropper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.exifinterface.media.ExifInterface;
import com.theartofdev.edmodo.cropper.BitmapCroppingWorkerTask;
import com.theartofdev.edmodo.cropper.BitmapLoadingWorkerTask;
import com.theartofdev.edmodo.cropper.BitmapUtils;
import com.theartofdev.edmodo.cropper.CropOverlayView;
import java.lang.ref.WeakReference;
import java.util.UUID;

/* loaded from: classes.dex */
public class CropImageView extends FrameLayout {
    private CropImageAnimation mAnimation;
    private boolean mAutoZoomEnabled;
    private Bitmap mBitmap;
    private WeakReference<BitmapCroppingWorkerTask> mBitmapCroppingWorkerTask;
    private WeakReference<BitmapLoadingWorkerTask> mBitmapLoadingWorkerTask;
    private final CropOverlayView mCropOverlayView;
    private int mDegreesRotated;
    private boolean mFlipHorizontally;
    private boolean mFlipVertically;
    private final Matrix mImageInverseMatrix;
    private final Matrix mImageMatrix;
    private final float[] mImagePoints;
    private int mImageResource;
    private final ImageView mImageView;
    private int mInitialDegreesRotated;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private Uri mLoadedImageUri;
    private int mLoadedSampleSize;
    private int mMaxZoom;
    private OnCropImageCompleteListener mOnCropImageCompleteListener;
    private OnSetCropOverlayReleasedListener mOnCropOverlayReleasedListener;
    private OnSetCropOverlayMovedListener mOnSetCropOverlayMovedListener;
    private OnSetCropWindowChangeListener mOnSetCropWindowChangeListener;
    private OnSetImageUriCompleteListener mOnSetImageUriCompleteListener;
    private final ProgressBar mProgressBar;
    private RectF mRestoreCropWindowRect;
    private int mRestoreDegreesRotated;
    private boolean mSaveBitmapToInstanceState;
    private Uri mSaveInstanceStateBitmapUri;
    private final float[] mScaleImagePoints;
    private ScaleType mScaleType;
    private boolean mShowCropOverlay;
    private boolean mShowProgressBar;
    private boolean mSizeChanged;
    private float mZoom;
    private float mZoomOffsetX;
    private float mZoomOffsetY;

    /* loaded from: classes.dex */
    public enum CropShape {
        RECTANGLE,
        OVAL
    }

    /* loaded from: classes.dex */
    public enum Guidelines {
        OFF,
        ON_TOUCH,
        ON
    }

    /* loaded from: classes.dex */
    public interface OnCropImageCompleteListener {
        void onCropImageComplete(CropImageView cropImageView, CropResult cropResult);
    }

    /* loaded from: classes.dex */
    public interface OnSetCropOverlayMovedListener {
        void onCropOverlayMoved(Rect rect);
    }

    /* loaded from: classes.dex */
    public interface OnSetCropOverlayReleasedListener {
        void onCropOverlayReleased(Rect rect);
    }

    /* loaded from: classes.dex */
    public interface OnSetCropWindowChangeListener {
        void onCropWindowChanged();
    }

    /* loaded from: classes.dex */
    public interface OnSetImageUriCompleteListener {
        void onSetImageUriComplete(CropImageView cropImageView, Uri uri, Exception exc);
    }

    /* loaded from: classes.dex */
    public enum RequestSizeOptions {
        NONE,
        SAMPLING,
        RESIZE_INSIDE,
        RESIZE_FIT,
        RESIZE_EXACT
    }

    /* loaded from: classes.dex */
    public enum ScaleType {
        FIT_CENTER,
        CENTER,
        CENTER_CROP,
        CENTER_INSIDE
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Bundle bundle;
        this.mImageMatrix = new Matrix();
        this.mImageInverseMatrix = new Matrix();
        this.mImagePoints = new float[8];
        this.mScaleImagePoints = new float[8];
        this.mSaveBitmapToInstanceState = false;
        this.mShowCropOverlay = true;
        this.mShowProgressBar = true;
        this.mAutoZoomEnabled = true;
        this.mLoadedSampleSize = 1;
        this.mZoom = 1.0f;
        CropImageOptions options = null;
        Intent intent = context instanceof Activity ? ((Activity) context).getIntent() : null;
        if (!(intent == null || (bundle = intent.getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE)) == null)) {
            options = (CropImageOptions) bundle.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        }
        if (options == null) {
            options = new CropImageOptions();
            if (attrs != null) {
                TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CropImageView, 0, 0);
                try {
                    options.fixAspectRatio = ta.getBoolean(R.styleable.CropImageView_cropFixAspectRatio, options.fixAspectRatio);
                    options.aspectRatioX = ta.getInteger(R.styleable.CropImageView_cropAspectRatioX, options.aspectRatioX);
                    options.aspectRatioY = ta.getInteger(R.styleable.CropImageView_cropAspectRatioY, options.aspectRatioY);
                    options.scaleType = ScaleType.values()[ta.getInt(R.styleable.CropImageView_cropScaleType, options.scaleType.ordinal())];
                    options.autoZoomEnabled = ta.getBoolean(R.styleable.CropImageView_cropAutoZoomEnabled, options.autoZoomEnabled);
                    options.multiTouchEnabled = ta.getBoolean(R.styleable.CropImageView_cropMultiTouchEnabled, options.multiTouchEnabled);
                    options.maxZoom = ta.getInteger(R.styleable.CropImageView_cropMaxZoom, options.maxZoom);
                    options.cropShape = CropShape.values()[ta.getInt(R.styleable.CropImageView_cropShape, options.cropShape.ordinal())];
                    options.guidelines = Guidelines.values()[ta.getInt(R.styleable.CropImageView_cropGuidelines, options.guidelines.ordinal())];
                    options.snapRadius = ta.getDimension(R.styleable.CropImageView_cropSnapRadius, options.snapRadius);
                    options.touchRadius = ta.getDimension(R.styleable.CropImageView_cropTouchRadius, options.touchRadius);
                    options.initialCropWindowPaddingRatio = ta.getFloat(R.styleable.CropImageView_cropInitialCropWindowPaddingRatio, options.initialCropWindowPaddingRatio);
                    options.borderLineThickness = ta.getDimension(R.styleable.CropImageView_cropBorderLineThickness, options.borderLineThickness);
                    options.borderLineColor = ta.getInteger(R.styleable.CropImageView_cropBorderLineColor, options.borderLineColor);
                    options.borderCornerThickness = ta.getDimension(R.styleable.CropImageView_cropBorderCornerThickness, options.borderCornerThickness);
                    options.borderCornerOffset = ta.getDimension(R.styleable.CropImageView_cropBorderCornerOffset, options.borderCornerOffset);
                    options.borderCornerLength = ta.getDimension(R.styleable.CropImageView_cropBorderCornerLength, options.borderCornerLength);
                    options.borderCornerColor = ta.getInteger(R.styleable.CropImageView_cropBorderCornerColor, options.borderCornerColor);
                    options.guidelinesThickness = ta.getDimension(R.styleable.CropImageView_cropGuidelinesThickness, options.guidelinesThickness);
                    options.guidelinesColor = ta.getInteger(R.styleable.CropImageView_cropGuidelinesColor, options.guidelinesColor);
                    options.backgroundColor = ta.getInteger(R.styleable.CropImageView_cropBackgroundColor, options.backgroundColor);
                    options.showCropOverlay = ta.getBoolean(R.styleable.CropImageView_cropShowCropOverlay, this.mShowCropOverlay);
                    options.showProgressBar = ta.getBoolean(R.styleable.CropImageView_cropShowProgressBar, this.mShowProgressBar);
                    options.borderCornerThickness = ta.getDimension(R.styleable.CropImageView_cropBorderCornerThickness, options.borderCornerThickness);
                    options.minCropWindowWidth = (int) ta.getDimension(R.styleable.CropImageView_cropMinCropWindowWidth, options.minCropWindowWidth);
                    options.minCropWindowHeight = (int) ta.getDimension(R.styleable.CropImageView_cropMinCropWindowHeight, options.minCropWindowHeight);
                    options.minCropResultWidth = (int) ta.getFloat(R.styleable.CropImageView_cropMinCropResultWidthPX, options.minCropResultWidth);
                    options.minCropResultHeight = (int) ta.getFloat(R.styleable.CropImageView_cropMinCropResultHeightPX, options.minCropResultHeight);
                    options.maxCropResultWidth = (int) ta.getFloat(R.styleable.CropImageView_cropMaxCropResultWidthPX, options.maxCropResultWidth);
                    options.maxCropResultHeight = (int) ta.getFloat(R.styleable.CropImageView_cropMaxCropResultHeightPX, options.maxCropResultHeight);
                    options.flipHorizontally = ta.getBoolean(R.styleable.CropImageView_cropFlipHorizontally, options.flipHorizontally);
                    options.flipVertically = ta.getBoolean(R.styleable.CropImageView_cropFlipHorizontally, options.flipVertically);
                    this.mSaveBitmapToInstanceState = ta.getBoolean(R.styleable.CropImageView_cropSaveBitmapToInstanceState, this.mSaveBitmapToInstanceState);
                    if (ta.hasValue(R.styleable.CropImageView_cropAspectRatioX) && ta.hasValue(R.styleable.CropImageView_cropAspectRatioX) && !ta.hasValue(R.styleable.CropImageView_cropFixAspectRatio)) {
                        options.fixAspectRatio = true;
                    }
                } finally {
                    ta.recycle();
                }
            }
        }
        options.validate();
        this.mScaleType = options.scaleType;
        this.mAutoZoomEnabled = options.autoZoomEnabled;
        this.mMaxZoom = options.maxZoom;
        this.mShowCropOverlay = options.showCropOverlay;
        this.mShowProgressBar = options.showProgressBar;
        this.mFlipHorizontally = options.flipHorizontally;
        this.mFlipVertically = options.flipVertically;
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.crop_image_view, (ViewGroup) this, true);
        ImageView imageView = (ImageView) v.findViewById(R.id.ImageView_image);
        this.mImageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        CropOverlayView cropOverlayView = (CropOverlayView) v.findViewById(R.id.CropOverlayView);
        this.mCropOverlayView = cropOverlayView;
        cropOverlayView.setCropWindowChangeListener(new CropOverlayView.CropWindowChangeListener() { // from class: com.theartofdev.edmodo.cropper.CropImageView.1
            @Override // com.theartofdev.edmodo.cropper.CropOverlayView.CropWindowChangeListener
            public void onCropWindowChanged(boolean inProgress) {
                CropImageView.this.handleCropWindowChanged(inProgress, true);
                OnSetCropOverlayReleasedListener listener = CropImageView.this.mOnCropOverlayReleasedListener;
                if (listener != null && !inProgress) {
                    listener.onCropOverlayReleased(CropImageView.this.getCropRect());
                }
                OnSetCropOverlayMovedListener movedListener = CropImageView.this.mOnSetCropOverlayMovedListener;
                if (movedListener != null && inProgress) {
                    movedListener.onCropOverlayMoved(CropImageView.this.getCropRect());
                }
            }
        });
        this.mCropOverlayView.setInitialAttributeValues(options);
        this.mProgressBar = (ProgressBar) v.findViewById(R.id.CropProgressBar);
        setProgressBarVisibility();
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            this.mZoom = 1.0f;
            this.mZoomOffsetY = 0.0f;
            this.mZoomOffsetX = 0.0f;
            this.mCropOverlayView.resetCropOverlayView();
            requestLayout();
        }
    }

    public CropShape getCropShape() {
        return this.mCropOverlayView.getCropShape();
    }

    public void setCropShape(CropShape cropShape) {
        this.mCropOverlayView.setCropShape(cropShape);
    }

    public boolean isAutoZoomEnabled() {
        return this.mAutoZoomEnabled;
    }

    public void setAutoZoomEnabled(boolean autoZoomEnabled) {
        if (this.mAutoZoomEnabled != autoZoomEnabled) {
            this.mAutoZoomEnabled = autoZoomEnabled;
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public void setMultiTouchEnabled(boolean multiTouchEnabled) {
        if (this.mCropOverlayView.setMultiTouchEnabled(multiTouchEnabled)) {
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public int getMaxZoom() {
        return this.mMaxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        if (this.mMaxZoom != maxZoom && maxZoom > 0) {
            this.mMaxZoom = maxZoom;
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public void setMinCropResultSize(int minCropResultWidth, int minCropResultHeight) {
        this.mCropOverlayView.setMinCropResultSize(minCropResultWidth, minCropResultHeight);
    }

    public void setMaxCropResultSize(int maxCropResultWidth, int maxCropResultHeight) {
        this.mCropOverlayView.setMaxCropResultSize(maxCropResultWidth, maxCropResultHeight);
    }

    public int getRotatedDegrees() {
        return this.mDegreesRotated;
    }

    public void setRotatedDegrees(int degrees) {
        int i = this.mDegreesRotated;
        if (i != degrees) {
            rotateImage(degrees - i);
        }
    }

    public boolean isFixAspectRatio() {
        return this.mCropOverlayView.isFixAspectRatio();
    }

    public void setFixedAspectRatio(boolean fixAspectRatio) {
        this.mCropOverlayView.setFixedAspectRatio(fixAspectRatio);
    }

    public boolean isFlippedHorizontally() {
        return this.mFlipHorizontally;
    }

    public void setFlippedHorizontally(boolean flipHorizontally) {
        if (this.mFlipHorizontally != flipHorizontally) {
            this.mFlipHorizontally = flipHorizontally;
            applyImageMatrix(getWidth(), getHeight(), true, false);
        }
    }

    public boolean isFlippedVertically() {
        return this.mFlipVertically;
    }

    public void setFlippedVertically(boolean flipVertically) {
        if (this.mFlipVertically != flipVertically) {
            this.mFlipVertically = flipVertically;
            applyImageMatrix(getWidth(), getHeight(), true, false);
        }
    }

    public Guidelines getGuidelines() {
        return this.mCropOverlayView.getGuidelines();
    }

    public void setGuidelines(Guidelines guidelines) {
        this.mCropOverlayView.setGuidelines(guidelines);
    }

    public Pair<Integer, Integer> getAspectRatio() {
        return new Pair<>(Integer.valueOf(this.mCropOverlayView.getAspectRatioX()), Integer.valueOf(this.mCropOverlayView.getAspectRatioY()));
    }

    public void setAspectRatio(int aspectRatioX, int aspectRatioY) {
        this.mCropOverlayView.setAspectRatioX(aspectRatioX);
        this.mCropOverlayView.setAspectRatioY(aspectRatioY);
        setFixedAspectRatio(true);
    }

    public void clearAspectRatio() {
        this.mCropOverlayView.setAspectRatioX(1);
        this.mCropOverlayView.setAspectRatioY(1);
        setFixedAspectRatio(false);
    }

    public void setSnapRadius(float snapRadius) {
        if (snapRadius >= 0.0f) {
            this.mCropOverlayView.setSnapRadius(snapRadius);
        }
    }

    public boolean isShowProgressBar() {
        return this.mShowProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        if (this.mShowProgressBar != showProgressBar) {
            this.mShowProgressBar = showProgressBar;
            setProgressBarVisibility();
        }
    }

    public boolean isShowCropOverlay() {
        return this.mShowCropOverlay;
    }

    public void setShowCropOverlay(boolean showCropOverlay) {
        if (this.mShowCropOverlay != showCropOverlay) {
            this.mShowCropOverlay = showCropOverlay;
            setCropOverlayVisibility();
        }
    }

    public boolean isSaveBitmapToInstanceState() {
        return this.mSaveBitmapToInstanceState;
    }

    public void setSaveBitmapToInstanceState(boolean saveBitmapToInstanceState) {
        this.mSaveBitmapToInstanceState = saveBitmapToInstanceState;
    }

    public int getImageResource() {
        return this.mImageResource;
    }

    public Uri getImageUri() {
        return this.mLoadedImageUri;
    }

    public Rect getWholeImageRect() {
        int loadedSampleSize = this.mLoadedSampleSize;
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null) {
            return null;
        }
        int orgWidth = bitmap.getWidth() * loadedSampleSize;
        int orgHeight = bitmap.getHeight() * loadedSampleSize;
        return new Rect(0, 0, orgWidth, orgHeight);
    }

    public Rect getCropRect() {
        int loadedSampleSize = this.mLoadedSampleSize;
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null) {
            return null;
        }
        float[] points = getCropPoints();
        int orgWidth = bitmap.getWidth() * loadedSampleSize;
        int orgHeight = bitmap.getHeight() * loadedSampleSize;
        return BitmapUtils.getRectFromPoints(points, orgWidth, orgHeight, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY());
    }

    public RectF getCropWindowRect() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView == null) {
            return null;
        }
        return cropOverlayView.getCropWindowRect();
    }

    public float[] getCropPoints() {
        RectF cropWindowRect = this.mCropOverlayView.getCropWindowRect();
        float[] points = new float[8];
        points[0] = cropWindowRect.left;
        points[1] = cropWindowRect.top;
        points[2] = cropWindowRect.right;
        points[3] = cropWindowRect.top;
        points[4] = cropWindowRect.right;
        points[5] = cropWindowRect.bottom;
        points[6] = cropWindowRect.left;
        points[7] = cropWindowRect.bottom;
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapPoints(points);
        for (int i = 0; i < points.length; i++) {
            points[i] = points[i] * this.mLoadedSampleSize;
        }
        return points;
    }

    public void setCropRect(Rect rect) {
        this.mCropOverlayView.setInitialCropWindowRect(rect);
    }

    public void resetCropRect() {
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mDegreesRotated = this.mInitialDegreesRotated;
        this.mFlipHorizontally = false;
        this.mFlipVertically = false;
        applyImageMatrix(getWidth(), getHeight(), false, false);
        this.mCropOverlayView.resetCropWindowRect();
    }

    public Bitmap getCroppedImage() {
        return getCroppedImage(0, 0, RequestSizeOptions.NONE);
    }

    public Bitmap getCroppedImage(int reqWidth, int reqHeight) {
        return getCroppedImage(reqWidth, reqHeight, RequestSizeOptions.RESIZE_INSIDE);
    }

    public Bitmap getCroppedImage(int reqWidth, int reqHeight, RequestSizeOptions options) {
        Bitmap croppedBitmap;
        if (this.mBitmap == null) {
            return null;
        }
        this.mImageView.clearAnimation();
        int reqHeight2 = 0;
        int reqWidth2 = options != RequestSizeOptions.NONE ? reqWidth : 0;
        if (options != RequestSizeOptions.NONE) {
            reqHeight2 = reqHeight;
        }
        if (this.mLoadedImageUri == null || (this.mLoadedSampleSize <= 1 && options != RequestSizeOptions.SAMPLING)) {
            croppedBitmap = BitmapUtils.cropBitmapObjectHandleOOM(this.mBitmap, getCropPoints(), this.mDegreesRotated, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), this.mFlipHorizontally, this.mFlipVertically).bitmap;
        } else {
            int orgWidth = this.mBitmap.getWidth() * this.mLoadedSampleSize;
            int orgHeight = this.mBitmap.getHeight() * this.mLoadedSampleSize;
            BitmapUtils.BitmapSampled bitmapSampled = BitmapUtils.cropBitmap(getContext(), this.mLoadedImageUri, getCropPoints(), this.mDegreesRotated, orgWidth, orgHeight, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), reqWidth2, reqHeight2, this.mFlipHorizontally, this.mFlipVertically);
            croppedBitmap = bitmapSampled.bitmap;
        }
        return BitmapUtils.resizeBitmap(croppedBitmap, reqWidth2, reqHeight2, options);
    }

    public void getCroppedImageAsync() {
        getCroppedImageAsync(0, 0, RequestSizeOptions.NONE);
    }

    public void getCroppedImageAsync(int reqWidth, int reqHeight) {
        getCroppedImageAsync(reqWidth, reqHeight, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void getCroppedImageAsync(int reqWidth, int reqHeight, RequestSizeOptions options) {
        if (this.mOnCropImageCompleteListener != null) {
            startCropWorkerTask(reqWidth, reqHeight, options, null, null, 0);
            return;
        }
        throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
    }

    public void saveCroppedImageAsync(Uri saveUri) {
        saveCroppedImageAsync(saveUri, Bitmap.CompressFormat.JPEG, 90, 0, 0, RequestSizeOptions.NONE);
    }

    public void saveCroppedImageAsync(Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality) {
        saveCroppedImageAsync(saveUri, saveCompressFormat, saveCompressQuality, 0, 0, RequestSizeOptions.NONE);
    }

    public void saveCroppedImageAsync(Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality, int reqWidth, int reqHeight) {
        saveCroppedImageAsync(saveUri, saveCompressFormat, saveCompressQuality, reqWidth, reqHeight, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void saveCroppedImageAsync(Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality, int reqWidth, int reqHeight, RequestSizeOptions options) {
        if (this.mOnCropImageCompleteListener != null) {
            startCropWorkerTask(reqWidth, reqHeight, options, saveUri, saveCompressFormat, saveCompressQuality);
            return;
        }
        throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
    }

    public void setOnSetCropOverlayReleasedListener(OnSetCropOverlayReleasedListener listener) {
        this.mOnCropOverlayReleasedListener = listener;
    }

    public void setOnSetCropOverlayMovedListener(OnSetCropOverlayMovedListener listener) {
        this.mOnSetCropOverlayMovedListener = listener;
    }

    public void setOnCropWindowChangedListener(OnSetCropWindowChangeListener listener) {
        this.mOnSetCropWindowChangeListener = listener;
    }

    public void setOnSetImageUriCompleteListener(OnSetImageUriCompleteListener listener) {
        this.mOnSetImageUriCompleteListener = listener;
    }

    public void setOnCropImageCompleteListener(OnCropImageCompleteListener listener) {
        this.mOnCropImageCompleteListener = listener;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mCropOverlayView.setInitialCropWindowRect(null);
        setBitmap(bitmap, 0, null, 1, 0);
    }

    public void setImageBitmap(Bitmap bitmap, ExifInterface exif) {
        Bitmap setBitmap;
        int degreesRotated = 0;
        if (bitmap == null || exif == null) {
            setBitmap = bitmap;
        } else {
            BitmapUtils.RotateBitmapResult result = BitmapUtils.rotateBitmapByExif(bitmap, exif);
            setBitmap = result.bitmap;
            degreesRotated = result.degrees;
            this.mInitialDegreesRotated = result.degrees;
        }
        this.mCropOverlayView.setInitialCropWindowRect(null);
        setBitmap(setBitmap, 0, null, 1, degreesRotated);
    }

    public void setImageResource(int resId) {
        if (resId != 0) {
            this.mCropOverlayView.setInitialCropWindowRect(null);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
            setBitmap(bitmap, resId, null, 1, 0);
        }
    }

    public void setImageUriAsync(Uri uri) {
        if (uri != null) {
            WeakReference<BitmapLoadingWorkerTask> weakReference = this.mBitmapLoadingWorkerTask;
            BitmapLoadingWorkerTask currentTask = weakReference != null ? weakReference.get() : null;
            if (currentTask != null) {
                currentTask.cancel(true);
            }
            clearImageInt();
            this.mRestoreCropWindowRect = null;
            this.mRestoreDegreesRotated = 0;
            this.mCropOverlayView.setInitialCropWindowRect(null);
            WeakReference<BitmapLoadingWorkerTask> weakReference2 = new WeakReference<>(new BitmapLoadingWorkerTask(this, uri));
            this.mBitmapLoadingWorkerTask = weakReference2;
            weakReference2.get().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            setProgressBarVisibility();
        }
    }

    public void clearImage() {
        clearImageInt();
        this.mCropOverlayView.setInitialCropWindowRect(null);
    }

    public void rotateImage(int degrees) {
        int degrees2;
        if (this.mBitmap != null) {
            if (degrees < 0) {
                degrees2 = (degrees % 360) + 360;
            } else {
                degrees2 = degrees % 360;
            }
            boolean flipAxes = !this.mCropOverlayView.isFixAspectRatio() && ((degrees2 > 45 && degrees2 < 135) || (degrees2 > 215 && degrees2 < 305));
            BitmapUtils.RECT.set(this.mCropOverlayView.getCropWindowRect());
            RectF rectF = BitmapUtils.RECT;
            float halfWidth = (flipAxes ? rectF.height() : rectF.width()) / 2.0f;
            RectF rectF2 = BitmapUtils.RECT;
            float halfHeight = (flipAxes ? rectF2.width() : rectF2.height()) / 2.0f;
            if (flipAxes) {
                boolean isFlippedHorizontally = this.mFlipHorizontally;
                this.mFlipHorizontally = this.mFlipVertically;
                this.mFlipVertically = isFlippedHorizontally;
            }
            this.mImageMatrix.invert(this.mImageInverseMatrix);
            BitmapUtils.POINTS[0] = BitmapUtils.RECT.centerX();
            BitmapUtils.POINTS[1] = BitmapUtils.RECT.centerY();
            BitmapUtils.POINTS[2] = 0.0f;
            BitmapUtils.POINTS[3] = 0.0f;
            BitmapUtils.POINTS[4] = 1.0f;
            BitmapUtils.POINTS[5] = 0.0f;
            this.mImageInverseMatrix.mapPoints(BitmapUtils.POINTS);
            this.mDegreesRotated = (this.mDegreesRotated + degrees2) % 360;
            applyImageMatrix(getWidth(), getHeight(), true, false);
            this.mImageMatrix.mapPoints(BitmapUtils.POINTS2, BitmapUtils.POINTS);
            double d = this.mZoom;
            double sqrt = Math.sqrt(Math.pow(BitmapUtils.POINTS2[4] - BitmapUtils.POINTS2[2], 2.0d) + Math.pow(BitmapUtils.POINTS2[5] - BitmapUtils.POINTS2[3], 2.0d));
            Double.isNaN(d);
            float f = (float) (d / sqrt);
            this.mZoom = f;
            this.mZoom = Math.max(f, 1.0f);
            applyImageMatrix(getWidth(), getHeight(), true, false);
            this.mImageMatrix.mapPoints(BitmapUtils.POINTS2, BitmapUtils.POINTS);
            double change = Math.sqrt(Math.pow(BitmapUtils.POINTS2[4] - BitmapUtils.POINTS2[2], 2.0d) + Math.pow(BitmapUtils.POINTS2[5] - BitmapUtils.POINTS2[3], 2.0d));
            double d2 = halfWidth;
            Double.isNaN(d2);
            float halfWidth2 = (float) (d2 * change);
            double d3 = halfHeight;
            Double.isNaN(d3);
            float halfHeight2 = (float) (d3 * change);
            BitmapUtils.RECT.set(BitmapUtils.POINTS2[0] - halfWidth2, BitmapUtils.POINTS2[1] - halfHeight2, BitmapUtils.POINTS2[0] + halfWidth2, BitmapUtils.POINTS2[1] + halfHeight2);
            this.mCropOverlayView.resetCropOverlayView();
            this.mCropOverlayView.setCropWindowRect(BitmapUtils.RECT);
            applyImageMatrix(getWidth(), getHeight(), true, false);
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.fixCurrentCropWindowRect();
        }
    }

    public void flipImageHorizontally() {
        this.mFlipHorizontally = !this.mFlipHorizontally;
        applyImageMatrix(getWidth(), getHeight(), true, false);
    }

    public void flipImageVertically() {
        this.mFlipVertically = !this.mFlipVertically;
        applyImageMatrix(getWidth(), getHeight(), true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSetImageUriAsyncComplete(BitmapLoadingWorkerTask.Result result) {
        this.mBitmapLoadingWorkerTask = null;
        setProgressBarVisibility();
        if (result.error == null) {
            this.mInitialDegreesRotated = result.degreesRotated;
            setBitmap(result.bitmap, 0, result.uri, result.loadSampleSize, result.degreesRotated);
        }
        OnSetImageUriCompleteListener listener = this.mOnSetImageUriCompleteListener;
        if (listener != null) {
            listener.onSetImageUriComplete(this, result.uri, result.error);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onImageCroppingAsyncComplete(BitmapCroppingWorkerTask.Result result) {
        this.mBitmapCroppingWorkerTask = null;
        setProgressBarVisibility();
        OnCropImageCompleteListener listener = this.mOnCropImageCompleteListener;
        if (listener != null) {
            CropResult cropResult = new CropResult(this.mBitmap, this.mLoadedImageUri, result.bitmap, result.uri, result.error, getCropPoints(), getCropRect(), getWholeImageRect(), getRotatedDegrees(), result.sampleSize);
            listener.onCropImageComplete(this, cropResult);
        }
    }

    private void setBitmap(Bitmap bitmap, int imageResource, Uri imageUri, int loadSampleSize, int degreesRotated) {
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 == null || !bitmap2.equals(bitmap)) {
            this.mImageView.clearAnimation();
            clearImageInt();
            this.mBitmap = bitmap;
            this.mImageView.setImageBitmap(bitmap);
            this.mLoadedImageUri = imageUri;
            this.mImageResource = imageResource;
            this.mLoadedSampleSize = loadSampleSize;
            this.mDegreesRotated = degreesRotated;
            applyImageMatrix(getWidth(), getHeight(), true, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
                setCropOverlayVisibility();
            }
        }
    }

    private void clearImageInt() {
        if (this.mBitmap != null && (this.mImageResource > 0 || this.mLoadedImageUri != null)) {
            this.mBitmap.recycle();
        }
        this.mBitmap = null;
        this.mImageResource = 0;
        this.mLoadedImageUri = null;
        this.mLoadedSampleSize = 1;
        this.mDegreesRotated = 0;
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mImageMatrix.reset();
        this.mSaveInstanceStateBitmapUri = null;
        this.mImageView.setImageBitmap(null);
        setCropOverlayVisibility();
    }

    public void startCropWorkerTask(int reqWidth, int reqHeight, RequestSizeOptions options, Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality) {
        CropImageView cropImageView;
        Bitmap bitmap;
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 != null) {
            this.mImageView.clearAnimation();
            WeakReference<BitmapCroppingWorkerTask> weakReference = this.mBitmapCroppingWorkerTask;
            BitmapCroppingWorkerTask currentTask = weakReference != null ? weakReference.get() : null;
            if (currentTask != null) {
                currentTask.cancel(true);
            }
            int reqWidth2 = options != RequestSizeOptions.NONE ? reqWidth : 0;
            int reqHeight2 = options != RequestSizeOptions.NONE ? reqHeight : 0;
            int orgWidth = bitmap2.getWidth() * this.mLoadedSampleSize;
            int height = bitmap2.getHeight();
            int i = this.mLoadedSampleSize;
            int orgHeight = height * i;
            if (this.mLoadedImageUri == null) {
                bitmap = bitmap2;
                cropImageView = this;
            } else if (i > 1 || options == RequestSizeOptions.SAMPLING) {
                cropImageView = this;
                cropImageView.mBitmapCroppingWorkerTask = new WeakReference<>(new BitmapCroppingWorkerTask(this, this.mLoadedImageUri, getCropPoints(), this.mDegreesRotated, orgWidth, orgHeight, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), reqWidth2, reqHeight2, this.mFlipHorizontally, this.mFlipVertically, options, saveUri, saveCompressFormat, saveCompressQuality));
                cropImageView.mBitmapCroppingWorkerTask.get().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                setProgressBarVisibility();
            } else {
                bitmap = bitmap2;
                cropImageView = this;
            }
            cropImageView.mBitmapCroppingWorkerTask = new WeakReference<>(new BitmapCroppingWorkerTask(this, bitmap, getCropPoints(), cropImageView.mDegreesRotated, cropImageView.mCropOverlayView.isFixAspectRatio(), cropImageView.mCropOverlayView.getAspectRatioX(), cropImageView.mCropOverlayView.getAspectRatioY(), reqWidth2, reqHeight2, cropImageView.mFlipHorizontally, cropImageView.mFlipVertically, options, saveUri, saveCompressFormat, saveCompressQuality));
            cropImageView.mBitmapCroppingWorkerTask.get().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            setProgressBarVisibility();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        BitmapLoadingWorkerTask task;
        if (this.mLoadedImageUri == null && this.mBitmap == null && this.mImageResource < 1) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        Uri imageUri = this.mLoadedImageUri;
        if (this.mSaveBitmapToInstanceState && imageUri == null && this.mImageResource < 1) {
            Uri writeTempStateStoreBitmap = BitmapUtils.writeTempStateStoreBitmap(getContext(), this.mBitmap, this.mSaveInstanceStateBitmapUri);
            imageUri = writeTempStateStoreBitmap;
            this.mSaveInstanceStateBitmapUri = writeTempStateStoreBitmap;
        }
        if (!(imageUri == null || this.mBitmap == null)) {
            String key = UUID.randomUUID().toString();
            BitmapUtils.mStateBitmap = new Pair<>(key, new WeakReference(this.mBitmap));
            bundle.putString("LOADED_IMAGE_STATE_BITMAP_KEY", key);
        }
        WeakReference<BitmapLoadingWorkerTask> weakReference = this.mBitmapLoadingWorkerTask;
        if (!(weakReference == null || (task = weakReference.get()) == null)) {
            bundle.putParcelable("LOADING_IMAGE_URI", task.getUri());
        }
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putParcelable("LOADED_IMAGE_URI", imageUri);
        bundle.putInt("LOADED_IMAGE_RESOURCE", this.mImageResource);
        bundle.putInt("LOADED_SAMPLE_SIZE", this.mLoadedSampleSize);
        bundle.putInt("DEGREES_ROTATED", this.mDegreesRotated);
        bundle.putParcelable("INITIAL_CROP_RECT", this.mCropOverlayView.getInitialCropWindowRect());
        BitmapUtils.RECT.set(this.mCropOverlayView.getCropWindowRect());
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapRect(BitmapUtils.RECT);
        bundle.putParcelable("CROP_WINDOW_RECT", BitmapUtils.RECT);
        bundle.putString("CROP_SHAPE", this.mCropOverlayView.getCropShape().name());
        bundle.putBoolean("CROP_AUTO_ZOOM_ENABLED", this.mAutoZoomEnabled);
        bundle.putInt("CROP_MAX_ZOOM", this.mMaxZoom);
        bundle.putBoolean("CROP_FLIP_HORIZONTALLY", this.mFlipHorizontally);
        bundle.putBoolean("CROP_FLIP_VERTICALLY", this.mFlipVertically);
        return bundle;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            if (this.mBitmapLoadingWorkerTask == null && this.mLoadedImageUri == null && this.mBitmap == null && this.mImageResource == 0) {
                Uri uri = (Uri) bundle.getParcelable("LOADED_IMAGE_URI");
                if (uri != null) {
                    String key = bundle.getString("LOADED_IMAGE_STATE_BITMAP_KEY");
                    if (key != null) {
                        Bitmap stateBitmap = (BitmapUtils.mStateBitmap == null || !((String) BitmapUtils.mStateBitmap.first).equals(key)) ? null : (Bitmap) ((WeakReference) BitmapUtils.mStateBitmap.second).get();
                        BitmapUtils.mStateBitmap = null;
                        if (stateBitmap != null && !stateBitmap.isRecycled()) {
                            setBitmap(stateBitmap, 0, uri, bundle.getInt("LOADED_SAMPLE_SIZE"), 0);
                        }
                    }
                    if (this.mLoadedImageUri == null) {
                        setImageUriAsync(uri);
                    }
                } else {
                    int resId = bundle.getInt("LOADED_IMAGE_RESOURCE");
                    if (resId > 0) {
                        setImageResource(resId);
                    } else {
                        Uri uri2 = (Uri) bundle.getParcelable("LOADING_IMAGE_URI");
                        if (uri2 != null) {
                            setImageUriAsync(uri2);
                        }
                    }
                }
                int i = bundle.getInt("DEGREES_ROTATED");
                this.mRestoreDegreesRotated = i;
                this.mDegreesRotated = i;
                Rect initialCropRect = (Rect) bundle.getParcelable("INITIAL_CROP_RECT");
                if (initialCropRect != null && (initialCropRect.width() > 0 || initialCropRect.height() > 0)) {
                    this.mCropOverlayView.setInitialCropWindowRect(initialCropRect);
                }
                RectF cropWindowRect = (RectF) bundle.getParcelable("CROP_WINDOW_RECT");
                if (cropWindowRect != null && (cropWindowRect.width() > 0.0f || cropWindowRect.height() > 0.0f)) {
                    this.mRestoreCropWindowRect = cropWindowRect;
                }
                this.mCropOverlayView.setCropShape(CropShape.valueOf(bundle.getString("CROP_SHAPE")));
                this.mAutoZoomEnabled = bundle.getBoolean("CROP_AUTO_ZOOM_ENABLED");
                this.mMaxZoom = bundle.getInt("CROP_MAX_ZOOM");
                this.mFlipHorizontally = bundle.getBoolean("CROP_FLIP_HORIZONTALLY");
                this.mFlipVertically = bundle.getBoolean("CROP_FLIP_VERTICALLY");
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredHeight;
        int desiredWidth;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            if (heightSize == 0) {
                heightSize = bitmap.getHeight();
            }
            double viewToBitmapWidthRatio = Double.POSITIVE_INFINITY;
            double viewToBitmapHeightRatio = Double.POSITIVE_INFINITY;
            if (widthSize < this.mBitmap.getWidth()) {
                double d = widthSize;
                double width = this.mBitmap.getWidth();
                Double.isNaN(d);
                Double.isNaN(width);
                viewToBitmapWidthRatio = d / width;
            }
            if (heightSize < this.mBitmap.getHeight()) {
                double d2 = heightSize;
                double height = this.mBitmap.getHeight();
                Double.isNaN(d2);
                Double.isNaN(height);
                viewToBitmapHeightRatio = d2 / height;
            }
            if (viewToBitmapWidthRatio == Double.POSITIVE_INFINITY && viewToBitmapHeightRatio == Double.POSITIVE_INFINITY) {
                desiredWidth = this.mBitmap.getWidth();
                desiredHeight = this.mBitmap.getHeight();
            } else {
                int desiredWidth2 = (viewToBitmapWidthRatio > viewToBitmapHeightRatio ? 1 : (viewToBitmapWidthRatio == viewToBitmapHeightRatio ? 0 : -1));
                if (desiredWidth2 <= 0) {
                    desiredWidth = widthSize;
                    double height2 = this.mBitmap.getHeight();
                    Double.isNaN(height2);
                    desiredHeight = (int) (height2 * viewToBitmapWidthRatio);
                } else {
                    desiredHeight = heightSize;
                    double width2 = this.mBitmap.getWidth();
                    Double.isNaN(width2);
                    desiredWidth = (int) (width2 * viewToBitmapHeightRatio);
                }
            }
            int width3 = getOnMeasureSpec(widthMode, widthSize, desiredWidth);
            int height3 = getOnMeasureSpec(heightMode, heightSize, desiredHeight);
            this.mLayoutWidth = width3;
            this.mLayoutHeight = height3;
            setMeasuredDimension(width3, height3);
            return;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mLayoutWidth <= 0 || this.mLayoutHeight <= 0) {
            updateImageBounds(true);
            return;
        }
        ViewGroup.LayoutParams origParams = getLayoutParams();
        origParams.width = this.mLayoutWidth;
        origParams.height = this.mLayoutHeight;
        setLayoutParams(origParams);
        if (this.mBitmap != null) {
            applyImageMatrix(r - l, b - t, true, false);
            if (this.mRestoreCropWindowRect != null) {
                int i = this.mRestoreDegreesRotated;
                if (i != this.mInitialDegreesRotated) {
                    this.mDegreesRotated = i;
                    applyImageMatrix(r - l, b - t, true, false);
                }
                this.mImageMatrix.mapRect(this.mRestoreCropWindowRect);
                this.mCropOverlayView.setCropWindowRect(this.mRestoreCropWindowRect);
                handleCropWindowChanged(false, false);
                this.mCropOverlayView.fixCurrentCropWindowRect();
                this.mRestoreCropWindowRect = null;
            } else if (this.mSizeChanged) {
                this.mSizeChanged = false;
                handleCropWindowChanged(false, false);
            }
        } else {
            updateImageBounds(true);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mSizeChanged = oldw > 0 && oldh > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCropWindowChanged(boolean inProgress, boolean animate) {
        int width = getWidth();
        int height = getHeight();
        if (this.mBitmap != null && width > 0 && height > 0) {
            RectF cropRect = this.mCropOverlayView.getCropWindowRect();
            if (inProgress) {
                if (cropRect.left < 0.0f || cropRect.top < 0.0f || cropRect.right > width || cropRect.bottom > height) {
                    applyImageMatrix(width, height, false, false);
                }
            } else if (this.mAutoZoomEnabled || this.mZoom > 1.0f) {
                float newZoom = 0.0f;
                if (this.mZoom < this.mMaxZoom && cropRect.width() < width * 0.5f && cropRect.height() < height * 0.5f) {
                    newZoom = Math.min(this.mMaxZoom, Math.min(width / ((cropRect.width() / this.mZoom) / 0.64f), height / ((cropRect.height() / this.mZoom) / 0.64f)));
                }
                if (this.mZoom > 1.0f && (cropRect.width() > width * 0.65f || cropRect.height() > height * 0.65f)) {
                    newZoom = Math.max(1.0f, Math.min(width / ((cropRect.width() / this.mZoom) / 0.51f), height / ((cropRect.height() / this.mZoom) / 0.51f)));
                }
                if (!this.mAutoZoomEnabled) {
                    newZoom = 1.0f;
                }
                if (newZoom > 0.0f && newZoom != this.mZoom) {
                    if (animate) {
                        if (this.mAnimation == null) {
                            this.mAnimation = new CropImageAnimation(this.mImageView, this.mCropOverlayView);
                        }
                        this.mAnimation.setStartState(this.mImagePoints, this.mImageMatrix);
                    }
                    this.mZoom = newZoom;
                    applyImageMatrix(width, height, true, animate);
                }
            }
            OnSetCropWindowChangeListener onSetCropWindowChangeListener = this.mOnSetCropWindowChangeListener;
            if (onSetCropWindowChangeListener != null && !inProgress) {
                onSetCropWindowChangeListener.onCropWindowChanged();
            }
        }
    }

    private void applyImageMatrix(float width, float height, boolean center, boolean animate) {
        if (this.mBitmap != null) {
            float f = 0.0f;
            if (width > 0.0f && height > 0.0f) {
                this.mImageMatrix.invert(this.mImageInverseMatrix);
                RectF cropRect = this.mCropOverlayView.getCropWindowRect();
                this.mImageInverseMatrix.mapRect(cropRect);
                this.mImageMatrix.reset();
                this.mImageMatrix.postTranslate((width - this.mBitmap.getWidth()) / 2.0f, (height - this.mBitmap.getHeight()) / 2.0f);
                mapImagePointsByImageMatrix();
                int i = this.mDegreesRotated;
                if (i > 0) {
                    this.mImageMatrix.postRotate(i, BitmapUtils.getRectCenterX(this.mImagePoints), BitmapUtils.getRectCenterY(this.mImagePoints));
                    mapImagePointsByImageMatrix();
                }
                float scale = Math.min(width / BitmapUtils.getRectWidth(this.mImagePoints), height / BitmapUtils.getRectHeight(this.mImagePoints));
                if (this.mScaleType == ScaleType.FIT_CENTER || ((this.mScaleType == ScaleType.CENTER_INSIDE && scale < 1.0f) || (scale > 1.0f && this.mAutoZoomEnabled))) {
                    this.mImageMatrix.postScale(scale, scale, BitmapUtils.getRectCenterX(this.mImagePoints), BitmapUtils.getRectCenterY(this.mImagePoints));
                    mapImagePointsByImageMatrix();
                }
                float scaleX = this.mFlipHorizontally ? -this.mZoom : this.mZoom;
                float scaleY = this.mFlipVertically ? -this.mZoom : this.mZoom;
                this.mImageMatrix.postScale(scaleX, scaleY, BitmapUtils.getRectCenterX(this.mImagePoints), BitmapUtils.getRectCenterY(this.mImagePoints));
                mapImagePointsByImageMatrix();
                this.mImageMatrix.mapRect(cropRect);
                if (center) {
                    this.mZoomOffsetX = width > BitmapUtils.getRectWidth(this.mImagePoints) ? 0.0f : Math.max(Math.min((width / 2.0f) - cropRect.centerX(), -BitmapUtils.getRectLeft(this.mImagePoints)), getWidth() - BitmapUtils.getRectRight(this.mImagePoints)) / scaleX;
                    if (height <= BitmapUtils.getRectHeight(this.mImagePoints)) {
                        f = Math.max(Math.min((height / 2.0f) - cropRect.centerY(), -BitmapUtils.getRectTop(this.mImagePoints)), getHeight() - BitmapUtils.getRectBottom(this.mImagePoints)) / scaleY;
                    }
                    this.mZoomOffsetY = f;
                } else {
                    this.mZoomOffsetX = Math.min(Math.max(this.mZoomOffsetX * scaleX, -cropRect.left), (-cropRect.right) + width) / scaleX;
                    this.mZoomOffsetY = Math.min(Math.max(this.mZoomOffsetY * scaleY, -cropRect.top), (-cropRect.bottom) + height) / scaleY;
                }
                this.mImageMatrix.postTranslate(this.mZoomOffsetX * scaleX, this.mZoomOffsetY * scaleY);
                cropRect.offset(this.mZoomOffsetX * scaleX, this.mZoomOffsetY * scaleY);
                this.mCropOverlayView.setCropWindowRect(cropRect);
                mapImagePointsByImageMatrix();
                this.mCropOverlayView.invalidate();
                if (animate) {
                    this.mAnimation.setEndState(this.mImagePoints, this.mImageMatrix);
                    this.mImageView.startAnimation(this.mAnimation);
                } else {
                    this.mImageView.setImageMatrix(this.mImageMatrix);
                }
                updateImageBounds(false);
            }
        }
    }

    private void mapImagePointsByImageMatrix() {
        float[] fArr = this.mImagePoints;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = this.mBitmap.getWidth();
        float[] fArr2 = this.mImagePoints;
        fArr2[3] = 0.0f;
        fArr2[4] = this.mBitmap.getWidth();
        this.mImagePoints[5] = this.mBitmap.getHeight();
        float[] fArr3 = this.mImagePoints;
        fArr3[6] = 0.0f;
        fArr3[7] = this.mBitmap.getHeight();
        this.mImageMatrix.mapPoints(this.mImagePoints);
        float[] fArr4 = this.mScaleImagePoints;
        fArr4[0] = 0.0f;
        fArr4[1] = 0.0f;
        fArr4[2] = 100.0f;
        fArr4[3] = 0.0f;
        fArr4[4] = 100.0f;
        fArr4[5] = 100.0f;
        fArr4[6] = 0.0f;
        fArr4[7] = 100.0f;
        this.mImageMatrix.mapPoints(fArr4);
    }

    private static int getOnMeasureSpec(int measureSpecMode, int measureSpecSize, int desiredSize) {
        if (measureSpecMode == 1073741824) {
            return measureSpecSize;
        }
        if (measureSpecMode != Integer.MIN_VALUE) {
            return desiredSize;
        }
        int spec = Math.min(desiredSize, measureSpecSize);
        return spec;
    }

    private void setCropOverlayVisibility() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setVisibility((!this.mShowCropOverlay || this.mBitmap == null) ? 4 : 0);
        }
    }

    private void setProgressBarVisibility() {
        int i = 0;
        boolean visible = this.mShowProgressBar && ((this.mBitmap == null && this.mBitmapLoadingWorkerTask != null) || this.mBitmapCroppingWorkerTask != null);
        ProgressBar progressBar = this.mProgressBar;
        if (!visible) {
            i = 4;
        }
        progressBar.setVisibility(i);
    }

    private void updateImageBounds(boolean clear) {
        if (this.mBitmap != null && !clear) {
            float scaleFactorWidth = (this.mLoadedSampleSize * 100.0f) / BitmapUtils.getRectWidth(this.mScaleImagePoints);
            float scaleFactorHeight = (this.mLoadedSampleSize * 100.0f) / BitmapUtils.getRectHeight(this.mScaleImagePoints);
            this.mCropOverlayView.setCropWindowLimits(getWidth(), getHeight(), scaleFactorWidth, scaleFactorHeight);
        }
        this.mCropOverlayView.setBounds(clear ? null : this.mImagePoints, getWidth(), getHeight());
    }

    /* loaded from: classes.dex */
    public static class CropResult {
        private final Bitmap mBitmap;
        private final float[] mCropPoints;
        private final Rect mCropRect;
        private final Exception mError;
        private final Bitmap mOriginalBitmap;
        private final Uri mOriginalUri;
        private final int mRotation;
        private final int mSampleSize;
        private final Uri mUri;
        private final Rect mWholeImageRect;

        /* JADX INFO: Access modifiers changed from: package-private */
        public CropResult(Bitmap originalBitmap, Uri originalUri, Bitmap bitmap, Uri uri, Exception error, float[] cropPoints, Rect cropRect, Rect wholeImageRect, int rotation, int sampleSize) {
            this.mOriginalBitmap = originalBitmap;
            this.mOriginalUri = originalUri;
            this.mBitmap = bitmap;
            this.mUri = uri;
            this.mError = error;
            this.mCropPoints = cropPoints;
            this.mCropRect = cropRect;
            this.mWholeImageRect = wholeImageRect;
            this.mRotation = rotation;
            this.mSampleSize = sampleSize;
        }

        public Bitmap getOriginalBitmap() {
            return this.mOriginalBitmap;
        }

        public Uri getOriginalUri() {
            return this.mOriginalUri;
        }

        public boolean isSuccessful() {
            return this.mError == null;
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public Exception getError() {
            return this.mError;
        }

        public float[] getCropPoints() {
            return this.mCropPoints;
        }

        public Rect getCropRect() {
            return this.mCropRect;
        }

        public Rect getWholeImageRect() {
            return this.mWholeImageRect;
        }

        public int getRotation() {
            return this.mRotation;
        }

        public int getSampleSize() {
            return this.mSampleSize;
        }
    }
}
