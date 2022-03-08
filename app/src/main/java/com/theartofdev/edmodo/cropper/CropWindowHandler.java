package com.theartofdev.edmodo.cropper;

import android.graphics.RectF;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theartofdev.edmodo.cropper.CropWindowMoveHandler;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class CropWindowHandler {
    private float mMaxCropResultHeight;
    private float mMaxCropResultWidth;
    private float mMaxCropWindowHeight;
    private float mMaxCropWindowWidth;
    private float mMinCropResultHeight;
    private float mMinCropResultWidth;
    private float mMinCropWindowHeight;
    private float mMinCropWindowWidth;
    private final RectF mEdges = new RectF();
    private final RectF mGetEdges = new RectF();
    private float mScaleFactorWidth = 1.0f;
    private float mScaleFactorHeight = 1.0f;

    public RectF getRect() {
        this.mGetEdges.set(this.mEdges);
        return this.mGetEdges;
    }

    public float getMinCropWidth() {
        return Math.max(this.mMinCropWindowWidth, this.mMinCropResultWidth / this.mScaleFactorWidth);
    }

    public float getMinCropHeight() {
        return Math.max(this.mMinCropWindowHeight, this.mMinCropResultHeight / this.mScaleFactorHeight);
    }

    public float getMaxCropWidth() {
        return Math.min(this.mMaxCropWindowWidth, this.mMaxCropResultWidth / this.mScaleFactorWidth);
    }

    public float getMaxCropHeight() {
        return Math.min(this.mMaxCropWindowHeight, this.mMaxCropResultHeight / this.mScaleFactorHeight);
    }

    public float getScaleFactorWidth() {
        return this.mScaleFactorWidth;
    }

    public float getScaleFactorHeight() {
        return this.mScaleFactorHeight;
    }

    public void setMinCropResultSize(int minCropResultWidth, int minCropResultHeight) {
        this.mMinCropResultWidth = minCropResultWidth;
        this.mMinCropResultHeight = minCropResultHeight;
    }

    public void setMaxCropResultSize(int maxCropResultWidth, int maxCropResultHeight) {
        this.mMaxCropResultWidth = maxCropResultWidth;
        this.mMaxCropResultHeight = maxCropResultHeight;
    }

    public void setCropWindowLimits(float maxWidth, float maxHeight, float scaleFactorWidth, float scaleFactorHeight) {
        this.mMaxCropWindowWidth = maxWidth;
        this.mMaxCropWindowHeight = maxHeight;
        this.mScaleFactorWidth = scaleFactorWidth;
        this.mScaleFactorHeight = scaleFactorHeight;
    }

    public void setInitialAttributeValues(CropImageOptions options) {
        this.mMinCropWindowWidth = options.minCropWindowWidth;
        this.mMinCropWindowHeight = options.minCropWindowHeight;
        this.mMinCropResultWidth = options.minCropResultWidth;
        this.mMinCropResultHeight = options.minCropResultHeight;
        this.mMaxCropResultWidth = options.maxCropResultWidth;
        this.mMaxCropResultHeight = options.maxCropResultHeight;
    }

    public void setRect(RectF rect) {
        this.mEdges.set(rect);
    }

    public boolean showGuidelines() {
        return this.mEdges.width() >= 100.0f && this.mEdges.height() >= 100.0f;
    }

    public CropWindowMoveHandler getMoveHandler(float x, float y, float targetRadius, CropImageView.CropShape cropShape) {
        CropWindowMoveHandler.Type type;
        if (cropShape == CropImageView.CropShape.OVAL) {
            type = getOvalPressedMoveType(x, y);
        } else {
            type = getRectanglePressedMoveType(x, y, targetRadius);
        }
        if (type != null) {
            return new CropWindowMoveHandler(type, this, x, y);
        }
        return null;
    }

    private CropWindowMoveHandler.Type getRectanglePressedMoveType(float x, float y, float targetRadius) {
        if (isInCornerTargetZone(x, y, this.mEdges.left, this.mEdges.top, targetRadius)) {
            CropWindowMoveHandler.Type moveType = CropWindowMoveHandler.Type.TOP_LEFT;
            return moveType;
        } else if (isInCornerTargetZone(x, y, this.mEdges.right, this.mEdges.top, targetRadius)) {
            CropWindowMoveHandler.Type moveType2 = CropWindowMoveHandler.Type.TOP_RIGHT;
            return moveType2;
        } else if (isInCornerTargetZone(x, y, this.mEdges.left, this.mEdges.bottom, targetRadius)) {
            CropWindowMoveHandler.Type moveType3 = CropWindowMoveHandler.Type.BOTTOM_LEFT;
            return moveType3;
        } else if (isInCornerTargetZone(x, y, this.mEdges.right, this.mEdges.bottom, targetRadius)) {
            CropWindowMoveHandler.Type moveType4 = CropWindowMoveHandler.Type.BOTTOM_RIGHT;
            return moveType4;
        } else if (isInCenterTargetZone(x, y, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom) && focusCenter()) {
            CropWindowMoveHandler.Type moveType5 = CropWindowMoveHandler.Type.CENTER;
            return moveType5;
        } else if (isInHorizontalTargetZone(x, y, this.mEdges.left, this.mEdges.right, this.mEdges.top, targetRadius)) {
            CropWindowMoveHandler.Type moveType6 = CropWindowMoveHandler.Type.TOP;
            return moveType6;
        } else if (isInHorizontalTargetZone(x, y, this.mEdges.left, this.mEdges.right, this.mEdges.bottom, targetRadius)) {
            CropWindowMoveHandler.Type moveType7 = CropWindowMoveHandler.Type.BOTTOM;
            return moveType7;
        } else if (isInVerticalTargetZone(x, y, this.mEdges.left, this.mEdges.top, this.mEdges.bottom, targetRadius)) {
            CropWindowMoveHandler.Type moveType8 = CropWindowMoveHandler.Type.LEFT;
            return moveType8;
        } else if (isInVerticalTargetZone(x, y, this.mEdges.right, this.mEdges.top, this.mEdges.bottom, targetRadius)) {
            CropWindowMoveHandler.Type moveType9 = CropWindowMoveHandler.Type.RIGHT;
            return moveType9;
        } else if (!isInCenterTargetZone(x, y, this.mEdges.left, this.mEdges.top, this.mEdges.right, this.mEdges.bottom) || focusCenter()) {
            return null;
        } else {
            CropWindowMoveHandler.Type moveType10 = CropWindowMoveHandler.Type.CENTER;
            return moveType10;
        }
    }

    private CropWindowMoveHandler.Type getOvalPressedMoveType(float x, float y) {
        float cellLength = this.mEdges.width() / 6.0f;
        float leftCenter = this.mEdges.left + cellLength;
        float rightCenter = this.mEdges.left + (cellLength * 5.0f);
        float cellHeight = this.mEdges.height() / 6.0f;
        float topCenter = this.mEdges.top + cellHeight;
        float bottomCenter = this.mEdges.top + (5.0f * cellHeight);
        if (x < leftCenter) {
            if (y < topCenter) {
                CropWindowMoveHandler.Type moveType = CropWindowMoveHandler.Type.TOP_LEFT;
                return moveType;
            } else if (y < bottomCenter) {
                CropWindowMoveHandler.Type moveType2 = CropWindowMoveHandler.Type.LEFT;
                return moveType2;
            } else {
                CropWindowMoveHandler.Type moveType3 = CropWindowMoveHandler.Type.BOTTOM_LEFT;
                return moveType3;
            }
        } else if (x < rightCenter) {
            if (y < topCenter) {
                CropWindowMoveHandler.Type moveType4 = CropWindowMoveHandler.Type.TOP;
                return moveType4;
            } else if (y < bottomCenter) {
                CropWindowMoveHandler.Type moveType5 = CropWindowMoveHandler.Type.CENTER;
                return moveType5;
            } else {
                CropWindowMoveHandler.Type moveType6 = CropWindowMoveHandler.Type.BOTTOM;
                return moveType6;
            }
        } else if (y < topCenter) {
            CropWindowMoveHandler.Type moveType7 = CropWindowMoveHandler.Type.TOP_RIGHT;
            return moveType7;
        } else if (y < bottomCenter) {
            CropWindowMoveHandler.Type moveType8 = CropWindowMoveHandler.Type.RIGHT;
            return moveType8;
        } else {
            CropWindowMoveHandler.Type moveType9 = CropWindowMoveHandler.Type.BOTTOM_RIGHT;
            return moveType9;
        }
    }

    private static boolean isInCornerTargetZone(float x, float y, float handleX, float handleY, float targetRadius) {
        return Math.abs(x - handleX) <= targetRadius && Math.abs(y - handleY) <= targetRadius;
    }

    private static boolean isInHorizontalTargetZone(float x, float y, float handleXStart, float handleXEnd, float handleY, float targetRadius) {
        return x > handleXStart && x < handleXEnd && Math.abs(y - handleY) <= targetRadius;
    }

    private static boolean isInVerticalTargetZone(float x, float y, float handleX, float handleYStart, float handleYEnd, float targetRadius) {
        return Math.abs(x - handleX) <= targetRadius && y > handleYStart && y < handleYEnd;
    }

    private static boolean isInCenterTargetZone(float x, float y, float left, float top, float right, float bottom) {
        return x > left && x < right && y > top && y < bottom;
    }

    private boolean focusCenter() {
        return !showGuidelines();
    }
}
