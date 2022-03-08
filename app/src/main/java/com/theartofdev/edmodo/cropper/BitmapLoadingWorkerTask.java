package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import com.theartofdev.edmodo.cropper.BitmapUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BitmapLoadingWorkerTask extends AsyncTask<Void, Void, Result> {
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final int mHeight;
    private final Uri mUri;
    private final int mWidth;

    public BitmapLoadingWorkerTask(CropImageView cropImageView, Uri uri) {
        this.mUri = uri;
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        DisplayMetrics metrics = cropImageView.getResources().getDisplayMetrics();
        double densityAdj = metrics.density > 1.0f ? 1.0f / metrics.density : 1.0d;
        double d = metrics.widthPixels;
        Double.isNaN(d);
        this.mWidth = (int) (d * densityAdj);
        double d2 = metrics.heightPixels;
        Double.isNaN(d2);
        this.mHeight = (int) (d2 * densityAdj);
    }

    public Uri getUri() {
        return this.mUri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Result doInBackground(Void... params) {
        try {
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.BitmapSampled decodeResult = BitmapUtils.decodeSampledBitmap(this.mContext, this.mUri, this.mWidth, this.mHeight);
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.RotateBitmapResult rotateResult = BitmapUtils.rotateBitmapByExif(decodeResult.bitmap, this.mContext, this.mUri);
            return new Result(this.mUri, rotateResult.bitmap, decodeResult.sampleSize, rotateResult.degrees);
        } catch (Exception e) {
            return new Result(this.mUri, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPostExecute(Result result) {
        CropImageView cropImageView;
        if (result != null) {
            boolean completeCalled = false;
            if (!isCancelled() && (cropImageView = this.mCropImageViewReference.get()) != null) {
                completeCalled = true;
                cropImageView.onSetImageUriAsyncComplete(result);
            }
            if (!completeCalled && result.bitmap != null) {
                result.bitmap.recycle();
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Result {
        public final Bitmap bitmap;
        public final int degreesRotated;
        public final Exception error;
        public final int loadSampleSize;
        public final Uri uri;

        Result(Uri uri, Bitmap bitmap, int loadSampleSize, int degreesRotated) {
            this.uri = uri;
            this.bitmap = bitmap;
            this.loadSampleSize = loadSampleSize;
            this.degreesRotated = degreesRotated;
            this.error = null;
        }

        Result(Uri uri, Exception error) {
            this.uri = uri;
            this.bitmap = null;
            this.loadSampleSize = 0;
            this.degreesRotated = 0;
            this.error = error;
        }
    }
}
