package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* loaded from: classes.dex */
public class ShapeAppearancePathProvider {
    private final ShapePath[] cornerPaths = new ShapePath[4];
    private final Matrix[] cornerTransforms = new Matrix[4];
    private final Matrix[] edgeTransforms = new Matrix[4];
    private final PointF pointF = new PointF();
    private final ShapePath shapePath = new ShapePath();
    private final float[] scratch = new float[2];
    private final float[] scratch2 = new float[2];

    /* loaded from: classes.dex */
    public interface PathListener {
        void onCornerPathCreated(ShapePath shapePath, Matrix matrix, int i);

        void onEdgePathCreated(ShapePath shapePath, Matrix matrix, int i);
    }

    public ShapeAppearancePathProvider() {
        for (int i = 0; i < 4; i++) {
            this.cornerPaths[i] = new ShapePath();
            this.cornerTransforms[i] = new Matrix();
            this.edgeTransforms[i] = new Matrix();
        }
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float interpolation, RectF bounds, Path path) {
        calculatePath(shapeAppearanceModel, interpolation, bounds, null, path);
    }

    public void calculatePath(ShapeAppearanceModel shapeAppearanceModel, float interpolation, RectF bounds, PathListener pathListener, Path path) {
        path.rewind();
        ShapeAppearancePathSpec spec = new ShapeAppearancePathSpec(shapeAppearanceModel, interpolation, bounds, pathListener, path);
        for (int index = 0; index < 4; index++) {
            setCornerPathAndTransform(spec, index);
            setEdgePathAndTransform(index);
        }
        for (int index2 = 0; index2 < 4; index2++) {
            appendCornerPath(spec, index2);
            appendEdgePath(spec, index2);
        }
        path.close();
    }

    private void setCornerPathAndTransform(ShapeAppearancePathSpec spec, int index) {
        CornerSize size = getCornerSizeForIndex(index, spec.shapeAppearanceModel);
        getCornerTreatmentForIndex(index, spec.shapeAppearanceModel).getCornerPath(this.cornerPaths[index], 90.0f, spec.interpolation, spec.bounds, size);
        float edgeAngle = angleOfEdge(index);
        this.cornerTransforms[index].reset();
        getCoordinatesOfCorner(index, spec.bounds, this.pointF);
        this.cornerTransforms[index].setTranslate(this.pointF.x, this.pointF.y);
        this.cornerTransforms[index].preRotate(edgeAngle);
    }

    private void setEdgePathAndTransform(int index) {
        this.scratch[0] = this.cornerPaths[index].getEndX();
        this.scratch[1] = this.cornerPaths[index].getEndY();
        this.cornerTransforms[index].mapPoints(this.scratch);
        float edgeAngle = angleOfEdge(index);
        this.edgeTransforms[index].reset();
        Matrix matrix = this.edgeTransforms[index];
        float[] fArr = this.scratch;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.edgeTransforms[index].preRotate(edgeAngle);
    }

    private void appendCornerPath(ShapeAppearancePathSpec spec, int index) {
        this.scratch[0] = this.cornerPaths[index].getStartX();
        this.scratch[1] = this.cornerPaths[index].getStartY();
        this.cornerTransforms[index].mapPoints(this.scratch);
        if (index == 0) {
            Path path = spec.path;
            float[] fArr = this.scratch;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = spec.path;
            float[] fArr2 = this.scratch;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.cornerPaths[index].applyToPath(this.cornerTransforms[index], spec.path);
        if (spec.pathListener != null) {
            spec.pathListener.onCornerPathCreated(this.cornerPaths[index], this.cornerTransforms[index], index);
        }
    }

    private void appendEdgePath(ShapeAppearancePathSpec spec, int index) {
        int nextIndex = (index + 1) % 4;
        this.scratch[0] = this.cornerPaths[index].getEndX();
        this.scratch[1] = this.cornerPaths[index].getEndY();
        this.cornerTransforms[index].mapPoints(this.scratch);
        this.scratch2[0] = this.cornerPaths[nextIndex].getStartX();
        this.scratch2[1] = this.cornerPaths[nextIndex].getStartY();
        this.cornerTransforms[nextIndex].mapPoints(this.scratch2);
        float[] fArr = this.scratch;
        float f = fArr[0];
        float[] fArr2 = this.scratch2;
        float edgeLength = (float) Math.hypot(f - fArr2[0], fArr[1] - fArr2[1]);
        float edgeLength2 = Math.max(edgeLength - 0.001f, 0.0f);
        float center = getEdgeCenterForIndex(spec.bounds, index);
        this.shapePath.reset(0.0f, 0.0f);
        getEdgeTreatmentForIndex(index, spec.shapeAppearanceModel).getEdgePath(edgeLength2, center, spec.interpolation, this.shapePath);
        this.shapePath.applyToPath(this.edgeTransforms[index], spec.path);
        if (spec.pathListener != null) {
            spec.pathListener.onEdgePathCreated(this.shapePath, this.edgeTransforms[index], index);
        }
    }

    private float getEdgeCenterForIndex(RectF bounds, int index) {
        this.scratch[0] = this.cornerPaths[index].endX;
        this.scratch[1] = this.cornerPaths[index].endY;
        this.cornerTransforms[index].mapPoints(this.scratch);
        if (index == 1 || index == 3) {
            return Math.abs(bounds.centerX() - this.scratch[0]);
        }
        return Math.abs(bounds.centerY() - this.scratch[1]);
    }

    private CornerTreatment getCornerTreatmentForIndex(int index, ShapeAppearanceModel shapeAppearanceModel) {
        if (index == 1) {
            return shapeAppearanceModel.getBottomRightCorner();
        }
        if (index == 2) {
            return shapeAppearanceModel.getBottomLeftCorner();
        }
        if (index != 3) {
            return shapeAppearanceModel.getTopRightCorner();
        }
        return shapeAppearanceModel.getTopLeftCorner();
    }

    private CornerSize getCornerSizeForIndex(int index, ShapeAppearanceModel shapeAppearanceModel) {
        if (index == 1) {
            return shapeAppearanceModel.getBottomRightCornerSize();
        }
        if (index == 2) {
            return shapeAppearanceModel.getBottomLeftCornerSize();
        }
        if (index != 3) {
            return shapeAppearanceModel.getTopRightCornerSize();
        }
        return shapeAppearanceModel.getTopLeftCornerSize();
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int index, ShapeAppearanceModel shapeAppearanceModel) {
        if (index == 1) {
            return shapeAppearanceModel.getBottomEdge();
        }
        if (index == 2) {
            return shapeAppearanceModel.getLeftEdge();
        }
        if (index != 3) {
            return shapeAppearanceModel.getRightEdge();
        }
        return shapeAppearanceModel.getTopEdge();
    }

    private void getCoordinatesOfCorner(int index, RectF bounds, PointF pointF) {
        if (index == 1) {
            pointF.set(bounds.right, bounds.bottom);
        } else if (index == 2) {
            pointF.set(bounds.left, bounds.bottom);
        } else if (index != 3) {
            pointF.set(bounds.right, bounds.top);
        } else {
            pointF.set(bounds.left, bounds.top);
        }
    }

    private float angleOfEdge(int index) {
        return (index + 1) * 90;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ShapeAppearancePathSpec {
        public final RectF bounds;
        public final float interpolation;
        public final Path path;
        public final PathListener pathListener;
        public final ShapeAppearanceModel shapeAppearanceModel;

        ShapeAppearancePathSpec(ShapeAppearanceModel shapeAppearanceModel, float interpolation, RectF bounds, PathListener pathListener, Path path) {
            this.pathListener = pathListener;
            this.shapeAppearanceModel = shapeAppearanceModel;
            this.interpolation = interpolation;
            this.bounds = bounds;
            this.path = path;
        }
    }
}
