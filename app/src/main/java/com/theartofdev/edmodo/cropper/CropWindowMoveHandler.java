package com.theartofdev.edmodo.cropper;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

/* loaded from: classes.dex */
final class CropWindowMoveHandler {
    private static final Matrix MATRIX = new Matrix();
    private final float mMaxCropHeight;
    private final float mMaxCropWidth;
    private final float mMinCropHeight;
    private final float mMinCropWidth;
    private final PointF mTouchOffset = new PointF();
    private final Type mType;

    /* loaded from: classes.dex */
    public enum Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }

    public CropWindowMoveHandler(Type type, CropWindowHandler cropWindowHandler, float touchX, float touchY) {
        this.mType = type;
        this.mMinCropWidth = cropWindowHandler.getMinCropWidth();
        this.mMinCropHeight = cropWindowHandler.getMinCropHeight();
        this.mMaxCropWidth = cropWindowHandler.getMaxCropWidth();
        this.mMaxCropHeight = cropWindowHandler.getMaxCropHeight();
        calculateTouchOffset(cropWindowHandler.getRect(), touchX, touchY);
    }

    public void move(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapMargin, boolean fixedAspectRatio, float aspectRatio) {
        float adjX = x + this.mTouchOffset.x;
        float adjY = y + this.mTouchOffset.y;
        if (this.mType == Type.CENTER) {
            moveCenter(rect, adjX, adjY, bounds, viewWidth, viewHeight, snapMargin);
        } else if (fixedAspectRatio) {
            moveSizeWithFixedAspectRatio(rect, adjX, adjY, bounds, viewWidth, viewHeight, snapMargin, aspectRatio);
        } else {
            moveSizeWithFreeAspectRatio(rect, adjX, adjY, bounds, viewWidth, viewHeight, snapMargin);
        }
    }

    private void calculateTouchOffset(RectF rect, float touchX, float touchY) {
        float touchOffsetX = 0.0f;
        float touchOffsetY = 0.0f;
        switch (AnonymousClass1.$SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                touchOffsetX = rect.left - touchX;
                touchOffsetY = rect.top - touchY;
                break;
            case 2:
                touchOffsetX = rect.right - touchX;
                touchOffsetY = rect.top - touchY;
                break;
            case 3:
                touchOffsetX = rect.left - touchX;
                touchOffsetY = rect.bottom - touchY;
                break;
            case 4:
                touchOffsetX = rect.right - touchX;
                touchOffsetY = rect.bottom - touchY;
                break;
            case 5:
                touchOffsetX = rect.left - touchX;
                touchOffsetY = 0.0f;
                break;
            case 6:
                touchOffsetX = 0.0f;
                touchOffsetY = rect.top - touchY;
                break;
            case 7:
                touchOffsetX = rect.right - touchX;
                touchOffsetY = 0.0f;
                break;
            case 8:
                touchOffsetX = 0.0f;
                touchOffsetY = rect.bottom - touchY;
                break;
            case 9:
                touchOffsetX = rect.centerX() - touchX;
                touchOffsetY = rect.centerY() - touchY;
                break;
        }
        this.mTouchOffset.x = touchOffsetX;
        this.mTouchOffset.y = touchOffsetY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.theartofdev.edmodo.cropper.CropWindowMoveHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type = iArr;
            try {
                iArr[Type.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.TOP_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.BOTTOM_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.BOTTOM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[Type.CENTER.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    private void moveCenter(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapRadius) {
        float dx = x - rect.centerX();
        float dy = y - rect.centerY();
        if (rect.left + dx < 0.0f || rect.right + dx > viewWidth || rect.left + dx < bounds.left || rect.right + dx > bounds.right) {
            dx /= 1.05f;
            this.mTouchOffset.x -= dx / 2.0f;
        }
        if (rect.top + dy < 0.0f || rect.bottom + dy > viewHeight || rect.top + dy < bounds.top || rect.bottom + dy > bounds.bottom) {
            dy /= 1.05f;
            this.mTouchOffset.y -= dy / 2.0f;
        }
        rect.offset(dx, dy);
        snapEdgesToBounds(rect, bounds, snapRadius);
    }

    private void moveSizeWithFreeAspectRatio(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapMargin) {
        switch (AnonymousClass1.$SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                adjustTop(rect, y, bounds, snapMargin, 0.0f, false, false);
                adjustLeft(rect, x, bounds, snapMargin, 0.0f, false, false);
                return;
            case 2:
                adjustTop(rect, y, bounds, snapMargin, 0.0f, false, false);
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0.0f, false, false);
                return;
            case 3:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0.0f, false, false);
                adjustLeft(rect, x, bounds, snapMargin, 0.0f, false, false);
                return;
            case 4:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0.0f, false, false);
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0.0f, false, false);
                return;
            case 5:
                adjustLeft(rect, x, bounds, snapMargin, 0.0f, false, false);
                return;
            case 6:
                adjustTop(rect, y, bounds, snapMargin, 0.0f, false, false);
                return;
            case 7:
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0.0f, false, false);
                return;
            case 8:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0.0f, false, false);
                return;
            default:
                return;
        }
    }

    private void moveSizeWithFixedAspectRatio(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapMargin, float aspectRatio) {
        switch (AnonymousClass1.$SwitchMap$com$theartofdev$edmodo$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                if (calculateAspectRatio(x, y, rect.right, rect.bottom) < aspectRatio) {
                    adjustTop(rect, y, bounds, snapMargin, aspectRatio, true, false);
                    adjustLeftByAspectRatio(rect, aspectRatio);
                    return;
                }
                adjustLeft(rect, x, bounds, snapMargin, aspectRatio, true, false);
                adjustTopByAspectRatio(rect, aspectRatio);
                return;
            case 2:
                if (calculateAspectRatio(rect.left, y, x, rect.bottom) < aspectRatio) {
                    adjustTop(rect, y, bounds, snapMargin, aspectRatio, false, true);
                    adjustRightByAspectRatio(rect, aspectRatio);
                    return;
                }
                adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, true, false);
                adjustTopByAspectRatio(rect, aspectRatio);
                return;
            case 3:
                if (calculateAspectRatio(x, rect.top, rect.right, y) < aspectRatio) {
                    adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, true, false);
                    adjustLeftByAspectRatio(rect, aspectRatio);
                    return;
                }
                adjustLeft(rect, x, bounds, snapMargin, aspectRatio, false, true);
                adjustBottomByAspectRatio(rect, aspectRatio);
                return;
            case 4:
                if (calculateAspectRatio(rect.left, rect.top, x, y) < aspectRatio) {
                    adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, false, true);
                    adjustRightByAspectRatio(rect, aspectRatio);
                    return;
                }
                adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, false, true);
                adjustBottomByAspectRatio(rect, aspectRatio);
                return;
            case 5:
                adjustLeft(rect, x, bounds, snapMargin, aspectRatio, true, true);
                adjustTopBottomByAspectRatio(rect, bounds, aspectRatio);
                return;
            case 6:
                adjustTop(rect, y, bounds, snapMargin, aspectRatio, true, true);
                adjustLeftRightByAspectRatio(rect, bounds, aspectRatio);
                return;
            case 7:
                adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, true, true);
                adjustTopBottomByAspectRatio(rect, bounds, aspectRatio);
                return;
            case 8:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, true, true);
                adjustLeftRightByAspectRatio(rect, bounds, aspectRatio);
                return;
            default:
                return;
        }
    }

    private void snapEdgesToBounds(RectF edges, RectF bounds, float margin) {
        if (edges.left < bounds.left + margin) {
            edges.offset(bounds.left - edges.left, 0.0f);
        }
        if (edges.top < bounds.top + margin) {
            edges.offset(0.0f, bounds.top - edges.top);
        }
        if (edges.right > bounds.right - margin) {
            edges.offset(bounds.right - edges.right, 0.0f);
        }
        if (edges.bottom > bounds.bottom - margin) {
            edges.offset(0.0f, bounds.bottom - edges.bottom);
        }
    }

    private void adjustLeft(RectF rect, float left, RectF bounds, float snapMargin, float aspectRatio, boolean topMoves, boolean bottomMoves) {
        float newLeft = left;
        if (newLeft < 0.0f) {
            newLeft /= 1.05f;
            this.mTouchOffset.x -= newLeft / 1.1f;
        }
        if (newLeft < bounds.left) {
            this.mTouchOffset.x -= (newLeft - bounds.left) / 2.0f;
        }
        if (newLeft - bounds.left < snapMargin) {
            newLeft = bounds.left;
        }
        if (rect.right - newLeft < this.mMinCropWidth) {
            newLeft = rect.right - this.mMinCropWidth;
        }
        if (rect.right - newLeft > this.mMaxCropWidth) {
            newLeft = rect.right - this.mMaxCropWidth;
        }
        if (newLeft - bounds.left < snapMargin) {
            newLeft = bounds.left;
        }
        if (aspectRatio > 0.0f) {
            float newHeight = (rect.right - newLeft) / aspectRatio;
            if (newHeight < this.mMinCropHeight) {
                newLeft = Math.max(bounds.left, rect.right - (this.mMinCropHeight * aspectRatio));
                newHeight = (rect.right - newLeft) / aspectRatio;
            }
            if (newHeight > this.mMaxCropHeight) {
                newLeft = Math.max(bounds.left, rect.right - (this.mMaxCropHeight * aspectRatio));
                newHeight = (rect.right - newLeft) / aspectRatio;
            }
            if (!topMoves || !bottomMoves) {
                if (topMoves && rect.bottom - newHeight < bounds.top) {
                    newLeft = Math.max(bounds.left, rect.right - ((rect.bottom - bounds.top) * aspectRatio));
                    newHeight = (rect.right - newLeft) / aspectRatio;
                }
                if (bottomMoves && rect.top + newHeight > bounds.bottom) {
                    newLeft = Math.max(newLeft, Math.max(bounds.left, rect.right - ((bounds.bottom - rect.top) * aspectRatio)));
                }
            } else {
                newLeft = Math.max(newLeft, Math.max(bounds.left, rect.right - (bounds.height() * aspectRatio)));
            }
        }
        rect.left = newLeft;
    }

    private void adjustRight(RectF rect, float right, RectF bounds, int viewWidth, float snapMargin, float aspectRatio, boolean topMoves, boolean bottomMoves) {
        float newRight = right;
        if (newRight > viewWidth) {
            newRight = viewWidth + ((newRight - viewWidth) / 1.05f);
            this.mTouchOffset.x -= (newRight - viewWidth) / 1.1f;
        }
        if (newRight > bounds.right) {
            this.mTouchOffset.x -= (newRight - bounds.right) / 2.0f;
        }
        if (bounds.right - newRight < snapMargin) {
            newRight = bounds.right;
        }
        if (newRight - rect.left < this.mMinCropWidth) {
            newRight = rect.left + this.mMinCropWidth;
        }
        if (newRight - rect.left > this.mMaxCropWidth) {
            newRight = rect.left + this.mMaxCropWidth;
        }
        if (bounds.right - newRight < snapMargin) {
            newRight = bounds.right;
        }
        if (aspectRatio > 0.0f) {
            float newHeight = (newRight - rect.left) / aspectRatio;
            if (newHeight < this.mMinCropHeight) {
                newRight = Math.min(bounds.right, rect.left + (this.mMinCropHeight * aspectRatio));
                newHeight = (newRight - rect.left) / aspectRatio;
            }
            if (newHeight > this.mMaxCropHeight) {
                newRight = Math.min(bounds.right, rect.left + (this.mMaxCropHeight * aspectRatio));
                newHeight = (newRight - rect.left) / aspectRatio;
            }
            if (!topMoves || !bottomMoves) {
                if (topMoves && rect.bottom - newHeight < bounds.top) {
                    newRight = Math.min(bounds.right, rect.left + ((rect.bottom - bounds.top) * aspectRatio));
                    newHeight = (newRight - rect.left) / aspectRatio;
                }
                if (bottomMoves && rect.top + newHeight > bounds.bottom) {
                    newRight = Math.min(newRight, Math.min(bounds.right, rect.left + ((bounds.bottom - rect.top) * aspectRatio)));
                }
            } else {
                newRight = Math.min(newRight, Math.min(bounds.right, rect.left + (bounds.height() * aspectRatio)));
            }
        }
        rect.right = newRight;
    }

    private void adjustTop(RectF rect, float top, RectF bounds, float snapMargin, float aspectRatio, boolean leftMoves, boolean rightMoves) {
        float newTop = top;
        if (newTop < 0.0f) {
            newTop /= 1.05f;
            this.mTouchOffset.y -= newTop / 1.1f;
        }
        if (newTop < bounds.top) {
            this.mTouchOffset.y -= (newTop - bounds.top) / 2.0f;
        }
        if (newTop - bounds.top < snapMargin) {
            newTop = bounds.top;
        }
        if (rect.bottom - newTop < this.mMinCropHeight) {
            newTop = rect.bottom - this.mMinCropHeight;
        }
        if (rect.bottom - newTop > this.mMaxCropHeight) {
            newTop = rect.bottom - this.mMaxCropHeight;
        }
        if (newTop - bounds.top < snapMargin) {
            newTop = bounds.top;
        }
        if (aspectRatio > 0.0f) {
            float newWidth = (rect.bottom - newTop) * aspectRatio;
            if (newWidth < this.mMinCropWidth) {
                newTop = Math.max(bounds.top, rect.bottom - (this.mMinCropWidth / aspectRatio));
                newWidth = (rect.bottom - newTop) * aspectRatio;
            }
            if (newWidth > this.mMaxCropWidth) {
                newTop = Math.max(bounds.top, rect.bottom - (this.mMaxCropWidth / aspectRatio));
                newWidth = (rect.bottom - newTop) * aspectRatio;
            }
            if (!leftMoves || !rightMoves) {
                if (leftMoves && rect.right - newWidth < bounds.left) {
                    newTop = Math.max(bounds.top, rect.bottom - ((rect.right - bounds.left) / aspectRatio));
                    newWidth = (rect.bottom - newTop) * aspectRatio;
                }
                if (rightMoves && rect.left + newWidth > bounds.right) {
                    newTop = Math.max(newTop, Math.max(bounds.top, rect.bottom - ((bounds.right - rect.left) / aspectRatio)));
                }
            } else {
                newTop = Math.max(newTop, Math.max(bounds.top, rect.bottom - (bounds.width() / aspectRatio)));
            }
        }
        rect.top = newTop;
    }

    private void adjustBottom(RectF rect, float bottom, RectF bounds, int viewHeight, float snapMargin, float aspectRatio, boolean leftMoves, boolean rightMoves) {
        float newBottom = bottom;
        if (newBottom > viewHeight) {
            newBottom = viewHeight + ((newBottom - viewHeight) / 1.05f);
            this.mTouchOffset.y -= (newBottom - viewHeight) / 1.1f;
        }
        if (newBottom > bounds.bottom) {
            this.mTouchOffset.y -= (newBottom - bounds.bottom) / 2.0f;
        }
        if (bounds.bottom - newBottom < snapMargin) {
            newBottom = bounds.bottom;
        }
        if (newBottom - rect.top < this.mMinCropHeight) {
            newBottom = rect.top + this.mMinCropHeight;
        }
        if (newBottom - rect.top > this.mMaxCropHeight) {
            newBottom = rect.top + this.mMaxCropHeight;
        }
        if (bounds.bottom - newBottom < snapMargin) {
            newBottom = bounds.bottom;
        }
        if (aspectRatio > 0.0f) {
            float newWidth = (newBottom - rect.top) * aspectRatio;
            if (newWidth < this.mMinCropWidth) {
                newBottom = Math.min(bounds.bottom, rect.top + (this.mMinCropWidth / aspectRatio));
                newWidth = (newBottom - rect.top) * aspectRatio;
            }
            if (newWidth > this.mMaxCropWidth) {
                newBottom = Math.min(bounds.bottom, rect.top + (this.mMaxCropWidth / aspectRatio));
                newWidth = (newBottom - rect.top) * aspectRatio;
            }
            if (!leftMoves || !rightMoves) {
                if (leftMoves && rect.right - newWidth < bounds.left) {
                    newBottom = Math.min(bounds.bottom, rect.top + ((rect.right - bounds.left) / aspectRatio));
                    newWidth = (newBottom - rect.top) * aspectRatio;
                }
                if (rightMoves && rect.left + newWidth > bounds.right) {
                    newBottom = Math.min(newBottom, Math.min(bounds.bottom, rect.top + ((bounds.right - rect.left) / aspectRatio)));
                }
            } else {
                newBottom = Math.min(newBottom, Math.min(bounds.bottom, rect.top + (bounds.width() / aspectRatio)));
            }
        }
        rect.bottom = newBottom;
    }

    private void adjustLeftByAspectRatio(RectF rect, float aspectRatio) {
        rect.left = rect.right - (rect.height() * aspectRatio);
    }

    private void adjustTopByAspectRatio(RectF rect, float aspectRatio) {
        rect.top = rect.bottom - (rect.width() / aspectRatio);
    }

    private void adjustRightByAspectRatio(RectF rect, float aspectRatio) {
        rect.right = rect.left + (rect.height() * aspectRatio);
    }

    private void adjustBottomByAspectRatio(RectF rect, float aspectRatio) {
        rect.bottom = rect.top + (rect.width() / aspectRatio);
    }

    private void adjustLeftRightByAspectRatio(RectF rect, RectF bounds, float aspectRatio) {
        rect.inset((rect.width() - (rect.height() * aspectRatio)) / 2.0f, 0.0f);
        if (rect.left < bounds.left) {
            rect.offset(bounds.left - rect.left, 0.0f);
        }
        if (rect.right > bounds.right) {
            rect.offset(bounds.right - rect.right, 0.0f);
        }
    }

    private void adjustTopBottomByAspectRatio(RectF rect, RectF bounds, float aspectRatio) {
        rect.inset(0.0f, (rect.height() - (rect.width() / aspectRatio)) / 2.0f);
        if (rect.top < bounds.top) {
            rect.offset(0.0f, bounds.top - rect.top);
        }
        if (rect.bottom > bounds.bottom) {
            rect.offset(0.0f, bounds.bottom - rect.bottom);
        }
    }

    private static float calculateAspectRatio(float left, float top, float right, float bottom) {
        return (right - left) / (bottom - top);
    }
}
