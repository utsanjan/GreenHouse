package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CropOverlayView extends View {
    private boolean initializedCropWindow;
    private int mAspectRatioX;
    private int mAspectRatioY;
    private Paint mBackgroundPaint;
    private float mBorderCornerLength;
    private float mBorderCornerOffset;
    private Paint mBorderCornerPaint;
    private Paint mBorderPaint;
    private final float[] mBoundsPoints;
    private final RectF mCalcBounds;
    private CropImageView.CropShape mCropShape;
    private CropWindowChangeListener mCropWindowChangeListener;
    private final CropWindowHandler mCropWindowHandler;
    private final RectF mDrawRect;
    private boolean mFixAspectRatio;
    private Paint mGuidelinePaint;
    private CropImageView.Guidelines mGuidelines;
    private float mInitialCropWindowPaddingRatio;
    private final Rect mInitialCropWindowRect;
    private CropWindowMoveHandler mMoveHandler;
    private boolean mMultiTouchEnabled;
    private Integer mOriginalLayerType;
    private Path mPath;
    private ScaleGestureDetector mScaleDetector;
    private float mSnapRadius;
    private float mTargetAspectRatio;
    private float mTouchRadius;
    private int mViewHeight;
    private int mViewWidth;

    /* loaded from: classes.dex */
    public interface CropWindowChangeListener {
        void onCropWindowChanged(boolean z);
    }

    public CropOverlayView(Context context) {
        this(context, null);
    }

    public CropOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCropWindowHandler = new CropWindowHandler();
        this.mDrawRect = new RectF();
        this.mPath = new Path();
        this.mBoundsPoints = new float[8];
        this.mCalcBounds = new RectF();
        this.mTargetAspectRatio = this.mAspectRatioX / this.mAspectRatioY;
        this.mInitialCropWindowRect = new Rect();
    }

    public void setCropWindowChangeListener(CropWindowChangeListener listener) {
        this.mCropWindowChangeListener = listener;
    }

    public RectF getCropWindowRect() {
        return this.mCropWindowHandler.getRect();
    }

    public void setCropWindowRect(RectF rect) {
        this.mCropWindowHandler.setRect(rect);
    }

    public void fixCurrentCropWindowRect() {
        RectF rect = getCropWindowRect();
        fixCropWindowRectByRules(rect);
        this.mCropWindowHandler.setRect(rect);
    }

    public void setBounds(float[] boundsPoints, int viewWidth, int viewHeight) {
        if (boundsPoints == null || !Arrays.equals(this.mBoundsPoints, boundsPoints)) {
            if (boundsPoints == null) {
                Arrays.fill(this.mBoundsPoints, 0.0f);
            } else {
                System.arraycopy(boundsPoints, 0, this.mBoundsPoints, 0, boundsPoints.length);
            }
            this.mViewWidth = viewWidth;
            this.mViewHeight = viewHeight;
            RectF cropRect = this.mCropWindowHandler.getRect();
            if (cropRect.width() == 0.0f || cropRect.height() == 0.0f) {
                initCropWindow();
            }
        }
    }

    public void resetCropOverlayView() {
        if (this.initializedCropWindow) {
            setCropWindowRect(BitmapUtils.EMPTY_RECT_F);
            initCropWindow();
            invalidate();
        }
    }

    public CropImageView.CropShape getCropShape() {
        return this.mCropShape;
    }

    public void setCropShape(CropImageView.CropShape cropShape) {
        if (this.mCropShape != cropShape) {
            this.mCropShape = cropShape;
            if (Build.VERSION.SDK_INT <= 17) {
                if (this.mCropShape == CropImageView.CropShape.OVAL) {
                    Integer valueOf = Integer.valueOf(getLayerType());
                    this.mOriginalLayerType = valueOf;
                    if (valueOf.intValue() != 1) {
                        setLayerType(1, null);
                    } else {
                        this.mOriginalLayerType = null;
                    }
                } else {
                    Integer num = this.mOriginalLayerType;
                    if (num != null) {
                        setLayerType(num.intValue(), null);
                        this.mOriginalLayerType = null;
                    }
                }
            }
            invalidate();
        }
    }

    public CropImageView.Guidelines getGuidelines() {
        return this.mGuidelines;
    }

    public void setGuidelines(CropImageView.Guidelines guidelines) {
        if (this.mGuidelines != guidelines) {
            this.mGuidelines = guidelines;
            if (this.initializedCropWindow) {
                invalidate();
            }
        }
    }

    public boolean isFixAspectRatio() {
        return this.mFixAspectRatio;
    }

    public void setFixedAspectRatio(boolean fixAspectRatio) {
        if (this.mFixAspectRatio != fixAspectRatio) {
            this.mFixAspectRatio = fixAspectRatio;
            if (this.initializedCropWindow) {
                initCropWindow();
                invalidate();
            }
        }
    }

    public int getAspectRatioX() {
        return this.mAspectRatioX;
    }

    public void setAspectRatioX(int aspectRatioX) {
        if (aspectRatioX <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        } else if (this.mAspectRatioX != aspectRatioX) {
            this.mAspectRatioX = aspectRatioX;
            this.mTargetAspectRatio = aspectRatioX / this.mAspectRatioY;
            if (this.initializedCropWindow) {
                initCropWindow();
                invalidate();
            }
        }
    }

    public int getAspectRatioY() {
        return this.mAspectRatioY;
    }

    public void setAspectRatioY(int aspectRatioY) {
        if (aspectRatioY <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        } else if (this.mAspectRatioY != aspectRatioY) {
            this.mAspectRatioY = aspectRatioY;
            this.mTargetAspectRatio = this.mAspectRatioX / aspectRatioY;
            if (this.initializedCropWindow) {
                initCropWindow();
                invalidate();
            }
        }
    }

    public void setSnapRadius(float snapRadius) {
        this.mSnapRadius = snapRadius;
    }

    public boolean setMultiTouchEnabled(boolean multiTouchEnabled) {
        if (this.mMultiTouchEnabled == multiTouchEnabled) {
            return false;
        }
        this.mMultiTouchEnabled = multiTouchEnabled;
        if (!multiTouchEnabled || this.mScaleDetector != null) {
            return true;
        }
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        return true;
    }

    public void setMinCropResultSize(int minCropResultWidth, int minCropResultHeight) {
        this.mCropWindowHandler.setMinCropResultSize(minCropResultWidth, minCropResultHeight);
    }

    public void setMaxCropResultSize(int maxCropResultWidth, int maxCropResultHeight) {
        this.mCropWindowHandler.setMaxCropResultSize(maxCropResultWidth, maxCropResultHeight);
    }

    public void setCropWindowLimits(float maxWidth, float maxHeight, float scaleFactorWidth, float scaleFactorHeight) {
        this.mCropWindowHandler.setCropWindowLimits(maxWidth, maxHeight, scaleFactorWidth, scaleFactorHeight);
    }

    public Rect getInitialCropWindowRect() {
        return this.mInitialCropWindowRect;
    }

    public void setInitialCropWindowRect(Rect rect) {
        this.mInitialCropWindowRect.set(rect != null ? rect : BitmapUtils.EMPTY_RECT);
        if (this.initializedCropWindow) {
            initCropWindow();
            invalidate();
            callOnCropWindowChanged(false);
        }
    }

    public void resetCropWindowRect() {
        if (this.initializedCropWindow) {
            initCropWindow();
            invalidate();
            callOnCropWindowChanged(false);
        }
    }

    public void setInitialAttributeValues(CropImageOptions options) {
        this.mCropWindowHandler.setInitialAttributeValues(options);
        setCropShape(options.cropShape);
        setSnapRadius(options.snapRadius);
        setGuidelines(options.guidelines);
        setFixedAspectRatio(options.fixAspectRatio);
        setAspectRatioX(options.aspectRatioX);
        setAspectRatioY(options.aspectRatioY);
        setMultiTouchEnabled(options.multiTouchEnabled);
        this.mTouchRadius = options.touchRadius;
        this.mInitialCropWindowPaddingRatio = options.initialCropWindowPaddingRatio;
        this.mBorderPaint = getNewPaintOrNull(options.borderLineThickness, options.borderLineColor);
        this.mBorderCornerOffset = options.borderCornerOffset;
        this.mBorderCornerLength = options.borderCornerLength;
        this.mBorderCornerPaint = getNewPaintOrNull(options.borderCornerThickness, options.borderCornerColor);
        this.mGuidelinePaint = getNewPaintOrNull(options.guidelinesThickness, options.guidelinesColor);
        this.mBackgroundPaint = getNewPaint(options.backgroundColor);
    }

    private void initCropWindow() {
        float leftLimit = Math.max(BitmapUtils.getRectLeft(this.mBoundsPoints), 0.0f);
        float topLimit = Math.max(BitmapUtils.getRectTop(this.mBoundsPoints), 0.0f);
        float rightLimit = Math.min(BitmapUtils.getRectRight(this.mBoundsPoints), getWidth());
        float bottomLimit = Math.min(BitmapUtils.getRectBottom(this.mBoundsPoints), getHeight());
        if (rightLimit > leftLimit && bottomLimit > topLimit) {
            RectF rect = new RectF();
            this.initializedCropWindow = true;
            float f = this.mInitialCropWindowPaddingRatio;
            float horizontalPadding = (rightLimit - leftLimit) * f;
            float verticalPadding = f * (bottomLimit - topLimit);
            if (this.mInitialCropWindowRect.width() > 0 && this.mInitialCropWindowRect.height() > 0) {
                rect.left = (this.mInitialCropWindowRect.left / this.mCropWindowHandler.getScaleFactorWidth()) + leftLimit;
                rect.top = (this.mInitialCropWindowRect.top / this.mCropWindowHandler.getScaleFactorHeight()) + topLimit;
                rect.right = rect.left + (this.mInitialCropWindowRect.width() / this.mCropWindowHandler.getScaleFactorWidth());
                rect.bottom = rect.top + (this.mInitialCropWindowRect.height() / this.mCropWindowHandler.getScaleFactorHeight());
                rect.left = Math.max(leftLimit, rect.left);
                rect.top = Math.max(topLimit, rect.top);
                rect.right = Math.min(rightLimit, rect.right);
                rect.bottom = Math.min(bottomLimit, rect.bottom);
            } else if (!this.mFixAspectRatio || rightLimit <= leftLimit || bottomLimit <= topLimit) {
                rect.left = leftLimit + horizontalPadding;
                rect.top = topLimit + verticalPadding;
                rect.right = rightLimit - horizontalPadding;
                rect.bottom = bottomLimit - verticalPadding;
            } else {
                float bitmapAspectRatio = (rightLimit - leftLimit) / (bottomLimit - topLimit);
                if (bitmapAspectRatio > this.mTargetAspectRatio) {
                    rect.top = topLimit + verticalPadding;
                    rect.bottom = bottomLimit - verticalPadding;
                    float centerX = getWidth() / 2.0f;
                    this.mTargetAspectRatio = this.mAspectRatioX / this.mAspectRatioY;
                    float cropWidth = Math.max(this.mCropWindowHandler.getMinCropWidth(), rect.height() * this.mTargetAspectRatio);
                    float halfCropWidth = cropWidth / 2.0f;
                    rect.left = centerX - halfCropWidth;
                    rect.right = centerX + halfCropWidth;
                } else {
                    rect.left = leftLimit + horizontalPadding;
                    rect.right = rightLimit - horizontalPadding;
                    float centerY = getHeight() / 2.0f;
                    float cropHeight = Math.max(this.mCropWindowHandler.getMinCropHeight(), rect.width() / this.mTargetAspectRatio);
                    float halfCropHeight = cropHeight / 2.0f;
                    rect.top = centerY - halfCropHeight;
                    rect.bottom = centerY + halfCropHeight;
                }
            }
            fixCropWindowRectByRules(rect);
            this.mCropWindowHandler.setRect(rect);
        }
    }

    private void fixCropWindowRectByRules(RectF rect) {
        if (rect.width() < this.mCropWindowHandler.getMinCropWidth()) {
            float adj = (this.mCropWindowHandler.getMinCropWidth() - rect.width()) / 2.0f;
            rect.left -= adj;
            rect.right += adj;
        }
        float adj2 = rect.height();
        if (adj2 < this.mCropWindowHandler.getMinCropHeight()) {
            float adj3 = (this.mCropWindowHandler.getMinCropHeight() - rect.height()) / 2.0f;
            rect.top -= adj3;
            rect.bottom += adj3;
        }
        float adj4 = rect.width();
        if (adj4 > this.mCropWindowHandler.getMaxCropWidth()) {
            float adj5 = (rect.width() - this.mCropWindowHandler.getMaxCropWidth()) / 2.0f;
            rect.left += adj5;
            rect.right -= adj5;
        }
        float adj6 = rect.height();
        if (adj6 > this.mCropWindowHandler.getMaxCropHeight()) {
            float adj7 = (rect.height() - this.mCropWindowHandler.getMaxCropHeight()) / 2.0f;
            rect.top += adj7;
            rect.bottom -= adj7;
        }
        calculateBounds(rect);
        if (this.mCalcBounds.width() > 0.0f && this.mCalcBounds.height() > 0.0f) {
            float leftLimit = Math.max(this.mCalcBounds.left, 0.0f);
            float topLimit = Math.max(this.mCalcBounds.top, 0.0f);
            float rightLimit = Math.min(this.mCalcBounds.right, getWidth());
            float bottomLimit = Math.min(this.mCalcBounds.bottom, getHeight());
            if (rect.left < leftLimit) {
                rect.left = leftLimit;
            }
            if (rect.top < topLimit) {
                rect.top = topLimit;
            }
            if (rect.right > rightLimit) {
                rect.right = rightLimit;
            }
            if (rect.bottom > bottomLimit) {
                rect.bottom = bottomLimit;
            }
        }
        if (this.mFixAspectRatio && Math.abs(rect.width() - (rect.height() * this.mTargetAspectRatio)) > 0.1d) {
            if (rect.width() > rect.height() * this.mTargetAspectRatio) {
                float adj8 = Math.abs((rect.height() * this.mTargetAspectRatio) - rect.width()) / 2.0f;
                rect.left += adj8;
                rect.right -= adj8;
                return;
            }
            float adj9 = Math.abs((rect.width() / this.mTargetAspectRatio) - rect.height()) / 2.0f;
            rect.top += adj9;
            rect.bottom -= adj9;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        if (this.mCropWindowHandler.showGuidelines()) {
            if (this.mGuidelines == CropImageView.Guidelines.ON) {
                drawGuidelines(canvas);
            } else if (this.mGuidelines == CropImageView.Guidelines.ON_TOUCH && this.mMoveHandler != null) {
                drawGuidelines(canvas);
            }
        }
        drawBorders(canvas);
        drawCorners(canvas);
    }

    private void drawBackground(Canvas canvas) {
        RectF rect = this.mCropWindowHandler.getRect();
        float left = Math.max(BitmapUtils.getRectLeft(this.mBoundsPoints), 0.0f);
        float top = Math.max(BitmapUtils.getRectTop(this.mBoundsPoints), 0.0f);
        float right = Math.min(BitmapUtils.getRectRight(this.mBoundsPoints), getWidth());
        float bottom = Math.min(BitmapUtils.getRectBottom(this.mBoundsPoints), getHeight());
        if (this.mCropShape != CropImageView.CropShape.RECTANGLE) {
            this.mPath.reset();
            if (Build.VERSION.SDK_INT > 17 || this.mCropShape != CropImageView.CropShape.OVAL) {
                this.mDrawRect.set(rect.left, rect.top, rect.right, rect.bottom);
            } else {
                this.mDrawRect.set(rect.left + 2.0f, rect.top + 2.0f, rect.right - 2.0f, rect.bottom - 2.0f);
            }
            this.mPath.addOval(this.mDrawRect, Path.Direction.CW);
            canvas.save();
            if (Build.VERSION.SDK_INT >= 26) {
                canvas.clipOutPath(this.mPath);
            } else {
                canvas.clipPath(this.mPath, Region.Op.XOR);
            }
            canvas.drawRect(left, top, right, bottom, this.mBackgroundPaint);
            canvas.restore();
        } else if (!isNonStraightAngleRotated() || Build.VERSION.SDK_INT <= 17) {
            canvas.drawRect(left, top, right, rect.top, this.mBackgroundPaint);
            canvas.drawRect(left, rect.bottom, right, bottom, this.mBackgroundPaint);
            canvas.drawRect(left, rect.top, rect.left, rect.bottom, this.mBackgroundPaint);
            canvas.drawRect(rect.right, rect.top, right, rect.bottom, this.mBackgroundPaint);
        } else {
            this.mPath.reset();
            Path path = this.mPath;
            float[] fArr = this.mBoundsPoints;
            path.moveTo(fArr[0], fArr[1]);
            Path path2 = this.mPath;
            float[] fArr2 = this.mBoundsPoints;
            path2.lineTo(fArr2[2], fArr2[3]);
            Path path3 = this.mPath;
            float[] fArr3 = this.mBoundsPoints;
            path3.lineTo(fArr3[4], fArr3[5]);
            Path path4 = this.mPath;
            float[] fArr4 = this.mBoundsPoints;
            path4.lineTo(fArr4[6], fArr4[7]);
            this.mPath.close();
            canvas.save();
            if (Build.VERSION.SDK_INT >= 26) {
                canvas.clipOutPath(this.mPath);
            } else {
                canvas.clipPath(this.mPath, Region.Op.INTERSECT);
            }
            canvas.clipRect(rect, Region.Op.XOR);
            canvas.drawRect(left, top, right, bottom, this.mBackgroundPaint);
            canvas.restore();
        }
    }

    private void drawGuidelines(Canvas canvas) {
        if (this.mGuidelinePaint != null) {
            Paint paint = this.mBorderPaint;
            float sw = paint != null ? paint.getStrokeWidth() : 0.0f;
            RectF rect = this.mCropWindowHandler.getRect();
            rect.inset(sw, sw);
            float oneThirdCropWidth = rect.width() / 3.0f;
            float oneThirdCropHeight = rect.height() / 3.0f;
            if (this.mCropShape == CropImageView.CropShape.OVAL) {
                float w = (rect.width() / 2.0f) - sw;
                float h = (rect.height() / 2.0f) - sw;
                float x1 = rect.left + oneThirdCropWidth;
                float x2 = rect.right - oneThirdCropWidth;
                double d = h;
                double sin = Math.sin(Math.acos((w - oneThirdCropWidth) / w));
                Double.isNaN(d);
                float yv = (float) (d * sin);
                canvas.drawLine(x1, (rect.top + h) - yv, x1, (rect.bottom - h) + yv, this.mGuidelinePaint);
                canvas.drawLine(x2, (rect.top + h) - yv, x2, (rect.bottom - h) + yv, this.mGuidelinePaint);
                float y1 = rect.top + oneThirdCropHeight;
                float y2 = rect.bottom - oneThirdCropHeight;
                double d2 = w;
                double cos = Math.cos(Math.asin((h - oneThirdCropHeight) / h));
                Double.isNaN(d2);
                float xv = (float) (d2 * cos);
                canvas.drawLine((rect.left + w) - xv, y1, (rect.right - w) + xv, y1, this.mGuidelinePaint);
                canvas.drawLine((rect.left + w) - xv, y2, (rect.right - w) + xv, y2, this.mGuidelinePaint);
                return;
            }
            float x12 = rect.left + oneThirdCropWidth;
            float x22 = rect.right - oneThirdCropWidth;
            canvas.drawLine(x12, rect.top, x12, rect.bottom, this.mGuidelinePaint);
            canvas.drawLine(x22, rect.top, x22, rect.bottom, this.mGuidelinePaint);
            float y12 = rect.top + oneThirdCropHeight;
            float y22 = rect.bottom - oneThirdCropHeight;
            canvas.drawLine(rect.left, y12, rect.right, y12, this.mGuidelinePaint);
            canvas.drawLine(rect.left, y22, rect.right, y22, this.mGuidelinePaint);
        }
    }

    private void drawBorders(Canvas canvas) {
        Paint paint = this.mBorderPaint;
        if (paint != null) {
            float w = paint.getStrokeWidth();
            RectF rect = this.mCropWindowHandler.getRect();
            rect.inset(w / 2.0f, w / 2.0f);
            if (this.mCropShape == CropImageView.CropShape.RECTANGLE) {
                canvas.drawRect(rect, this.mBorderPaint);
            } else {
                canvas.drawOval(rect, this.mBorderPaint);
            }
        }
    }

    private void drawCorners(Canvas canvas) {
        if (this.mBorderCornerPaint != null) {
            Paint paint = this.mBorderPaint;
            float f = 0.0f;
            float lineWidth = paint != null ? paint.getStrokeWidth() : 0.0f;
            float cornerWidth = this.mBorderCornerPaint.getStrokeWidth();
            float f2 = cornerWidth / 2.0f;
            if (this.mCropShape == CropImageView.CropShape.RECTANGLE) {
                f = this.mBorderCornerOffset;
            }
            float w = f2 + f;
            RectF rect = this.mCropWindowHandler.getRect();
            rect.inset(w, w);
            float cornerOffset = (cornerWidth - lineWidth) / 2.0f;
            float cornerExtension = (cornerWidth / 2.0f) + cornerOffset;
            canvas.drawLine(rect.left - cornerOffset, rect.top - cornerExtension, rect.left - cornerOffset, rect.top + this.mBorderCornerLength, this.mBorderCornerPaint);
            canvas.drawLine(rect.left - cornerExtension, rect.top - cornerOffset, rect.left + this.mBorderCornerLength, rect.top - cornerOffset, this.mBorderCornerPaint);
            canvas.drawLine(rect.right + cornerOffset, rect.top - cornerExtension, rect.right + cornerOffset, rect.top + this.mBorderCornerLength, this.mBorderCornerPaint);
            canvas.drawLine(rect.right + cornerExtension, rect.top - cornerOffset, rect.right - this.mBorderCornerLength, rect.top - cornerOffset, this.mBorderCornerPaint);
            canvas.drawLine(rect.left - cornerOffset, rect.bottom + cornerExtension, rect.left - cornerOffset, rect.bottom - this.mBorderCornerLength, this.mBorderCornerPaint);
            canvas.drawLine(rect.left - cornerExtension, rect.bottom + cornerOffset, rect.left + this.mBorderCornerLength, rect.bottom + cornerOffset, this.mBorderCornerPaint);
            canvas.drawLine(rect.right + cornerOffset, rect.bottom + cornerExtension, rect.right + cornerOffset, rect.bottom - this.mBorderCornerLength, this.mBorderCornerPaint);
            canvas.drawLine(rect.right + cornerExtension, rect.bottom + cornerOffset, rect.right - this.mBorderCornerLength, rect.bottom + cornerOffset, this.mBorderCornerPaint);
        }
    }

    private static Paint getNewPaint(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        return paint;
    }

    private static Paint getNewPaintOrNull(float thickness, int color) {
        if (thickness <= 0.0f) {
            return null;
        }
        Paint borderPaint = new Paint();
        borderPaint.setColor(color);
        borderPaint.setStrokeWidth(thickness);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
        return borderPaint;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        if (this.mMultiTouchEnabled) {
            this.mScaleDetector.onTouchEvent(event);
        }
        int action = event.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    onActionMove(event.getX(), event.getY());
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                } else if (action != 3) {
                    return false;
                }
            }
            getParent().requestDisallowInterceptTouchEvent(false);
            onActionUp();
            return true;
        }
        onActionDown(event.getX(), event.getY());
        return true;
    }

    private void onActionDown(float x, float y) {
        CropWindowMoveHandler moveHandler = this.mCropWindowHandler.getMoveHandler(x, y, this.mTouchRadius, this.mCropShape);
        this.mMoveHandler = moveHandler;
        if (moveHandler != null) {
            invalidate();
        }
    }

    private void onActionUp() {
        if (this.mMoveHandler != null) {
            this.mMoveHandler = null;
            callOnCropWindowChanged(false);
            invalidate();
        }
    }

    private void onActionMove(float x, float y) {
        if (this.mMoveHandler != null) {
            float snapRadius = this.mSnapRadius;
            RectF rect = this.mCropWindowHandler.getRect();
            if (calculateBounds(rect)) {
                snapRadius = 0.0f;
            }
            this.mMoveHandler.move(rect, x, y, this.mCalcBounds, this.mViewWidth, this.mViewHeight, snapRadius, this.mFixAspectRatio, this.mTargetAspectRatio);
            this.mCropWindowHandler.setRect(rect);
            callOnCropWindowChanged(true);
            invalidate();
        }
    }

    private boolean calculateBounds(RectF rect) {
        float left = BitmapUtils.getRectLeft(this.mBoundsPoints);
        float top = BitmapUtils.getRectTop(this.mBoundsPoints);
        float right = BitmapUtils.getRectRight(this.mBoundsPoints);
        float bottom = BitmapUtils.getRectBottom(this.mBoundsPoints);
        if (!isNonStraightAngleRotated()) {
            this.mCalcBounds.set(left, top, right, bottom);
            return false;
        }
        float[] fArr = this.mBoundsPoints;
        float x0 = fArr[0];
        float y0 = fArr[1];
        float x2 = fArr[4];
        float y2 = fArr[5];
        float x3 = fArr[6];
        float y3 = fArr[7];
        if (fArr[7] < fArr[1]) {
            if (fArr[1] < fArr[3]) {
                x0 = fArr[6];
                y0 = fArr[7];
                x2 = fArr[2];
                y2 = fArr[3];
                x3 = fArr[4];
                y3 = fArr[5];
            } else {
                x0 = fArr[4];
                y0 = fArr[5];
                x2 = fArr[0];
                y2 = fArr[1];
                x3 = fArr[2];
                y3 = fArr[3];
            }
        } else if (fArr[1] > fArr[3]) {
            x0 = fArr[2];
            y0 = fArr[3];
            x2 = fArr[6];
            y2 = fArr[7];
            x3 = fArr[0];
            y3 = fArr[1];
        }
        float a0 = (y3 - y0) / (x3 - x0);
        float a1 = (-1.0f) / a0;
        float b0 = y0 - (a0 * x0);
        float b1 = y0 - (a1 * x0);
        float b2 = y2 - (a0 * x2);
        float b3 = y2 - (a1 * x2);
        float centerY = rect.centerY() - rect.top;
        float centerX = rect.centerX();
        float x02 = rect.left;
        float c0 = centerY / (centerX - x02);
        float c1 = -c0;
        float y02 = rect.top;
        float x22 = rect.left;
        float d0 = y02 - (x22 * c0);
        float f = rect.top;
        float y22 = rect.right;
        float d1 = f - (y22 * c1);
        float left2 = Math.max(left, (d0 - b0) / (a0 - c0) < rect.right ? (d0 - b0) / (a0 - c0) : left);
        float left3 = Math.max(left2, (d0 - b1) / (a1 - c0) < rect.right ? (d0 - b1) / (a1 - c0) : left2);
        float left4 = Math.max(left3, (d1 - b3) / (a1 - c1) < rect.right ? (d1 - b3) / (a1 - c1) : left3);
        float right2 = Math.min(right, (d1 - b1) / (a1 - c1) > rect.left ? (d1 - b1) / (a1 - c1) : right);
        float right3 = Math.min(right2, (d1 - b2) / (a0 - c1) > rect.left ? (d1 - b2) / (a0 - c1) : right2);
        float right4 = Math.min(right3, (d0 - b2) / (a0 - c0) > rect.left ? (d0 - b2) / (a0 - c0) : right3);
        float top2 = Math.max(top, Math.max((a0 * left4) + b0, (a1 * right4) + b1));
        float bottom2 = Math.min(bottom, Math.min((a1 * left4) + b3, (a0 * right4) + b2));
        this.mCalcBounds.left = left4;
        this.mCalcBounds.top = top2;
        this.mCalcBounds.right = right4;
        this.mCalcBounds.bottom = bottom2;
        return true;
    }

    private boolean isNonStraightAngleRotated() {
        float[] fArr = this.mBoundsPoints;
        return (fArr[0] == fArr[6] || fArr[1] == fArr[7]) ? false : true;
    }

    private void callOnCropWindowChanged(boolean inProgress) {
        try {
            if (this.mCropWindowChangeListener != null) {
                this.mCropWindowChangeListener.onCropWindowChanged(inProgress);
            }
        } catch (Exception e) {
            Log.e("AIC", "Exception in crop window changed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector detector) {
            RectF rect = CropOverlayView.this.mCropWindowHandler.getRect();
            float x = detector.getFocusX();
            float y = detector.getFocusY();
            float dY = detector.getCurrentSpanY() / 2.0f;
            float dX = detector.getCurrentSpanX() / 2.0f;
            float newTop = y - dY;
            float newLeft = x - dX;
            float newRight = x + dX;
            float newBottom = y + dY;
            if (newLeft >= newRight || newTop > newBottom || newLeft < 0.0f || newRight > CropOverlayView.this.mCropWindowHandler.getMaxCropWidth() || newTop < 0.0f || newBottom > CropOverlayView.this.mCropWindowHandler.getMaxCropHeight()) {
                return true;
            }
            rect.set(newLeft, newTop, newRight, newBottom);
            CropOverlayView.this.mCropWindowHandler.setRect(rect);
            CropOverlayView.this.invalidate();
            return true;
        }
    }
}
