package com.theartofdev.edmodo.cropper;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class CropImageAnimation extends Animation implements Animation.AnimationListener {
    private final CropOverlayView mCropOverlayView;
    private final ImageView mImageView;
    private final float[] mStartBoundPoints = new float[8];
    private final float[] mEndBoundPoints = new float[8];
    private final RectF mStartCropWindowRect = new RectF();
    private final RectF mEndCropWindowRect = new RectF();
    private final float[] mStartImageMatrix = new float[9];
    private final float[] mEndImageMatrix = new float[9];
    private final RectF mAnimRect = new RectF();
    private final float[] mAnimPoints = new float[8];
    private final float[] mAnimMatrix = new float[9];

    public CropImageAnimation(ImageView cropImageView, CropOverlayView cropOverlayView) {
        this.mImageView = cropImageView;
        this.mCropOverlayView = cropOverlayView;
        setDuration(300L);
        setFillAfter(true);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
    }

    public void setStartState(float[] boundPoints, Matrix imageMatrix) {
        reset();
        System.arraycopy(boundPoints, 0, this.mStartBoundPoints, 0, 8);
        this.mStartCropWindowRect.set(this.mCropOverlayView.getCropWindowRect());
        imageMatrix.getValues(this.mStartImageMatrix);
    }

    public void setEndState(float[] boundPoints, Matrix imageMatrix) {
        System.arraycopy(boundPoints, 0, this.mEndBoundPoints, 0, 8);
        this.mEndCropWindowRect.set(this.mCropOverlayView.getCropWindowRect());
        imageMatrix.getValues(this.mEndImageMatrix);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float[] fArr;
        this.mAnimRect.left = this.mStartCropWindowRect.left + ((this.mEndCropWindowRect.left - this.mStartCropWindowRect.left) * interpolatedTime);
        this.mAnimRect.top = this.mStartCropWindowRect.top + ((this.mEndCropWindowRect.top - this.mStartCropWindowRect.top) * interpolatedTime);
        this.mAnimRect.right = this.mStartCropWindowRect.right + ((this.mEndCropWindowRect.right - this.mStartCropWindowRect.right) * interpolatedTime);
        this.mAnimRect.bottom = this.mStartCropWindowRect.bottom + ((this.mEndCropWindowRect.bottom - this.mStartCropWindowRect.bottom) * interpolatedTime);
        this.mCropOverlayView.setCropWindowRect(this.mAnimRect);
        int i = 0;
        while (true) {
            fArr = this.mAnimPoints;
            if (i >= fArr.length) {
                break;
            }
            float[] fArr2 = this.mStartBoundPoints;
            fArr[i] = fArr2[i] + ((this.mEndBoundPoints[i] - fArr2[i]) * interpolatedTime);
            i++;
        }
        this.mCropOverlayView.setBounds(fArr, this.mImageView.getWidth(), this.mImageView.getHeight());
        int i2 = 0;
        while (true) {
            float[] fArr3 = this.mAnimMatrix;
            if (i2 < fArr3.length) {
                float[] fArr4 = this.mStartImageMatrix;
                fArr3[i2] = fArr4[i2] + ((this.mEndImageMatrix[i2] - fArr4[i2]) * interpolatedTime);
                i2++;
            } else {
                Matrix m = this.mImageView.getImageMatrix();
                m.setValues(this.mAnimMatrix);
                this.mImageView.setImageMatrix(m);
                this.mImageView.invalidate();
                this.mCropOverlayView.invalidate();
                return;
            }
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.mImageView.clearAnimation();
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }
}
