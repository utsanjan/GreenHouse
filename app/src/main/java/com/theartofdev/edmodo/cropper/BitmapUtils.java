package com.theartofdev.edmodo.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BitmapUtils {
    private static int mMaxTextureSize;
    static Pair<String, WeakReference<Bitmap>> mStateBitmap;
    static final Rect EMPTY_RECT = new Rect();
    static final RectF EMPTY_RECT_F = new RectF();
    static final RectF RECT = new RectF();
    static final float[] POINTS = new float[6];
    static final float[] POINTS2 = new float[6];

    BitmapUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, Context context, Uri uri) {
        ExifInterface ei = null;
        try {
            InputStream is = context.getContentResolver().openInputStream(uri);
            if (is != null) {
                ei = new ExifInterface(is);
                is.close();
            }
        } catch (Exception e) {
        }
        return ei != null ? rotateBitmapByExif(bitmap, ei) : new RotateBitmapResult(bitmap, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, ExifInterface exif) {
        int degrees;
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (orientation == 3) {
            degrees = 180;
        } else if (orientation == 6) {
            degrees = 90;
        } else if (orientation != 8) {
            degrees = 0;
        } else {
            degrees = 270;
        }
        return new RotateBitmapResult(bitmap, degrees);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitmapSampled decodeSampledBitmap(Context context, Uri uri, int reqWidth, int reqHeight) {
        try {
            ContentResolver resolver = context.getContentResolver();
            BitmapFactory.Options options = decodeImageForOption(resolver, uri);
            if (options.outWidth == -1 && options.outHeight == -1) {
                throw new RuntimeException("File is not a picture");
            }
            options.inSampleSize = Math.max(calculateInSampleSizeByReqestedSize(options.outWidth, options.outHeight, reqWidth, reqHeight), calculateInSampleSizeByMaxTextureSize(options.outWidth, options.outHeight));
            Bitmap bitmap = decodeImage(resolver, uri, options);
            return new BitmapSampled(bitmap, options.inSampleSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitmapSampled cropBitmapObjectHandleOOM(Bitmap bitmap, float[] points, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, boolean flipHorizontally, boolean flipVertically) {
        int scale = 1;
        do {
            try {
                Bitmap cropBitmap = cropBitmapObjectWithScale(bitmap, points, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY, 1.0f / scale, flipHorizontally, flipVertically);
                return new BitmapSampled(cropBitmap, scale);
            } catch (OutOfMemoryError e) {
                scale *= 2;
                if (scale > 8) {
                    throw e;
                }
            }
        } while (scale > 8);
        throw e;
    }

    private static Bitmap cropBitmapObjectWithScale(Bitmap bitmap, float[] points, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, float scale, boolean flipHorizontally, boolean flipVertically) {
        Bitmap result;
        Rect rect = getRectFromPoints(points, bitmap.getWidth(), bitmap.getHeight(), fixAspectRatio, aspectRatioX, aspectRatioY);
        Matrix matrix = new Matrix();
        matrix.setRotate(degreesRotated, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        matrix.postScale(flipHorizontally ? -scale : scale, flipVertically ? -scale : scale);
        Bitmap result2 = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height(), matrix, true);
        if (result2 == bitmap) {
            result = bitmap.copy(bitmap.getConfig(), false);
        } else {
            result = result2;
        }
        if (degreesRotated % 90 != 0) {
            return cropForRotatedImage(result, points, rect, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitmapSampled cropBitmap(Context context, Uri loadedImageUri, float[] points, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight, boolean flipHorizontally, boolean flipVertically) {
        int sampleMulti = 1;
        do {
            try {
                return cropBitmap(context, loadedImageUri, points, degreesRotated, orgWidth, orgHeight, fixAspectRatio, aspectRatioX, aspectRatioY, reqWidth, reqHeight, flipHorizontally, flipVertically, sampleMulti);
            } catch (OutOfMemoryError e) {
                sampleMulti *= 2;
                if (sampleMulti > 16) {
                    throw new RuntimeException("Failed to handle OOM by sampling (" + sampleMulti + "): " + loadedImageUri + "\r\n" + e.getMessage(), e);
                }
            }
        } while (sampleMulti > 16);
        throw new RuntimeException("Failed to handle OOM by sampling (" + sampleMulti + "): " + loadedImageUri + "\r\n" + e.getMessage(), e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectLeft(float[] points) {
        return Math.min(Math.min(Math.min(points[0], points[2]), points[4]), points[6]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectTop(float[] points) {
        return Math.min(Math.min(Math.min(points[1], points[3]), points[5]), points[7]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectRight(float[] points) {
        return Math.max(Math.max(Math.max(points[0], points[2]), points[4]), points[6]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectBottom(float[] points) {
        return Math.max(Math.max(Math.max(points[1], points[3]), points[5]), points[7]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectWidth(float[] points) {
        return getRectRight(points) - getRectLeft(points);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectHeight(float[] points) {
        return getRectBottom(points) - getRectTop(points);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectCenterX(float[] points) {
        return (getRectRight(points) + getRectLeft(points)) / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float getRectCenterY(float[] points) {
        return (getRectBottom(points) + getRectTop(points)) / 2.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Rect getRectFromPoints(float[] points, int imageWidth, int imageHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        int left = Math.round(Math.max(0.0f, getRectLeft(points)));
        int top = Math.round(Math.max(0.0f, getRectTop(points)));
        int right = Math.round(Math.min(imageWidth, getRectRight(points)));
        int bottom = Math.round(Math.min(imageHeight, getRectBottom(points)));
        Rect rect = new Rect(left, top, right, bottom);
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY);
        }
        return rect;
    }

    private static void fixRectForAspectRatio(Rect rect, int aspectRatioX, int aspectRatioY) {
        if (aspectRatioX == aspectRatioY && rect.width() != rect.height()) {
            if (rect.height() > rect.width()) {
                rect.bottom -= rect.height() - rect.width();
            } else {
                rect.right -= rect.width() - rect.height();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Uri writeTempStateStoreBitmap(Context context, Bitmap bitmap, Uri uri) {
        boolean needSave = true;
        try {
            if (uri == null) {
                uri = Uri.fromFile(File.createTempFile("aic_state_store_temp", ".jpg", context.getCacheDir()));
            } else if (new File(uri.getPath()).exists()) {
                needSave = false;
            }
            if (needSave) {
                writeBitmapToUri(context, bitmap, uri, Bitmap.CompressFormat.JPEG, 95);
            }
            return uri;
        } catch (Exception e) {
            Log.w("AIC", "Failed to write bitmap to temp file for image-cropper save instance state", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeBitmapToUri(Context context, Bitmap bitmap, Uri uri, Bitmap.CompressFormat compressFormat, int compressQuality) throws FileNotFoundException {
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(compressFormat, compressQuality, outputStream);
        } finally {
            closeSafe(outputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bitmap resizeBitmap(Bitmap bitmap, int reqWidth, int reqHeight, CropImageView.RequestSizeOptions options) {
        if (reqWidth > 0 && reqHeight > 0) {
            try {
                if (options == CropImageView.RequestSizeOptions.RESIZE_FIT || options == CropImageView.RequestSizeOptions.RESIZE_INSIDE || options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    Bitmap resized = null;
                    if (options == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                        resized = Bitmap.createScaledBitmap(bitmap, reqWidth, reqHeight, false);
                    } else {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        float scale = Math.max(width / reqWidth, height / reqHeight);
                        if (scale > 1.0f || options == CropImageView.RequestSizeOptions.RESIZE_FIT) {
                            resized = Bitmap.createScaledBitmap(bitmap, (int) (width / scale), (int) (height / scale), false);
                        }
                    }
                    if (resized != null) {
                        if (resized != bitmap) {
                            bitmap.recycle();
                        }
                        return resized;
                    }
                }
            } catch (Exception e) {
                Log.w("AIC", "Failed to resize cropped image, return bitmap before resize", e);
            }
        }
        return bitmap;
    }

    private static BitmapSampled cropBitmap(Context context, Uri loadedImageUri, float[] points, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight, boolean flipHorizontally, boolean flipVertically, int sampleMulti) {
        Bitmap result;
        int sampleSize;
        OutOfMemoryError e;
        Bitmap result2;
        Rect rect = getRectFromPoints(points, orgWidth, orgHeight, fixAspectRatio, aspectRatioX, aspectRatioY);
        int width = reqWidth > 0 ? reqWidth : rect.width();
        int height = reqHeight > 0 ? reqHeight : rect.height();
        Bitmap result3 = null;
        try {
            BitmapSampled bitmapSampled = decodeSampledBitmapRegion(context, loadedImageUri, rect, width, height, sampleMulti);
            result3 = bitmapSampled.bitmap;
            int sampleSize2 = bitmapSampled.sampleSize;
            result = result3;
            sampleSize = sampleSize2;
        } catch (Exception e2) {
            result = result3;
            sampleSize = 1;
        }
        if (result == null) {
            return cropBitmap(context, loadedImageUri, points, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY, sampleMulti, rect, width, height, flipHorizontally, flipVertically);
        }
        try {
            Bitmap result4 = rotateAndFlipBitmapInt(result, degreesRotated, flipHorizontally, flipVertically);
            try {
                if (degreesRotated % 90 != 0) {
                    result2 = cropForRotatedImage(result4, points, rect, degreesRotated, fixAspectRatio, aspectRatioX, aspectRatioY);
                } else {
                    result2 = result4;
                }
                return new BitmapSampled(result2, sampleSize);
            } catch (OutOfMemoryError e3) {
                e = e3;
                result = result4;
                if (result != null) {
                    result.recycle();
                }
                throw e;
            }
        } catch (OutOfMemoryError e4) {
            e = e4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006d A[Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c, TryCatch #6 {Exception -> 0x007a, OutOfMemoryError -> 0x007c, blocks: (B:5:0x0018, B:19:0x0061, B:25:0x006d, B:26:0x0071), top: B:49:0x0018 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.theartofdev.edmodo.cropper.BitmapUtils.BitmapSampled cropBitmap(android.content.Context r18, android.net.Uri r19, float[] r20, int r21, boolean r22, int r23, int r24, int r25, android.graphics.Rect r26, int r27, int r28, boolean r29, boolean r30) {
        /*
            r1 = r19
            r2 = r20
            r3 = 0
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options     // Catch: Exception -> 0x007e, OutOfMemoryError -> 0x00a6
            r0.<init>()     // Catch: Exception -> 0x007e, OutOfMemoryError -> 0x00a6
            r4 = r0
            int r0 = r26.width()     // Catch: Exception -> 0x007e, OutOfMemoryError -> 0x00a6
            int r5 = r26.height()     // Catch: Exception -> 0x007e, OutOfMemoryError -> 0x00a6
            r6 = r27
            r7 = r28
            int r0 = calculateInSampleSizeByReqestedSize(r0, r5, r6, r7)     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
            int r0 = r0 * r25
            r5 = r0
            r4.inSampleSize = r0     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
            android.content.ContentResolver r0 = r18.getContentResolver()     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
            android.graphics.Bitmap r0 = decodeImage(r0, r1, r4)     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
            r15 = r0
            if (r15 == 0) goto L_0x0072
            int r0 = r2.length     // Catch: all -> 0x0069
            float[] r0 = new float[r0]     // Catch: all -> 0x0069
            int r8 = r2.length     // Catch: all -> 0x0069
            r9 = 0
            java.lang.System.arraycopy(r2, r9, r0, r9, r8)     // Catch: all -> 0x0069
            r8 = 0
        L_0x0035:
            int r9 = r0.length     // Catch: all -> 0x0069
            if (r8 >= r9) goto L_0x0046
            r9 = r0[r8]     // Catch: all -> 0x0043
            int r10 = r4.inSampleSize     // Catch: all -> 0x0043
            float r10 = (float) r10     // Catch: all -> 0x0043
            float r9 = r9 / r10
            r0[r8] = r9     // Catch: all -> 0x0043
            int r8 = r8 + 1
            goto L_0x0035
        L_0x0043:
            r0 = move-exception
            r8 = r15
            goto L_0x006b
        L_0x0046:
            r14 = 1065353216(0x3f800000, float:1.0)
            r8 = r15
            r9 = r0
            r10 = r21
            r11 = r22
            r12 = r23
            r13 = r24
            r17 = r15
            r15 = r29
            r16 = r30
            android.graphics.Bitmap r8 = cropBitmapObjectWithScale(r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch: all -> 0x0065
            r3 = r8
            r8 = r17
            if (r3 == r8) goto L_0x0073
            r8.recycle()     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
            goto L_0x0073
        L_0x0065:
            r0 = move-exception
            r8 = r17
            goto L_0x006b
        L_0x0069:
            r0 = move-exception
            r8 = r15
        L_0x006b:
            if (r3 == r8) goto L_0x0070
            r8.recycle()     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
        L_0x0070:
            throw r0     // Catch: Exception -> 0x007a, OutOfMemoryError -> 0x007c
        L_0x0072:
            r8 = r15
        L_0x0073:
            com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled r0 = new com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled
            r0.<init>(r3, r5)
            return r0
        L_0x007a:
            r0 = move-exception
            goto L_0x0083
        L_0x007c:
            r0 = move-exception
            goto L_0x00ab
        L_0x007e:
            r0 = move-exception
            r6 = r27
            r7 = r28
        L_0x0083:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "Failed to load sampled bitmap: "
            r5.append(r8)
            r5.append(r1)
            java.lang.String r8 = "\r\n"
            r5.append(r8)
            java.lang.String r8 = r0.getMessage()
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5, r0)
            throw r4
        L_0x00a6:
            r0 = move-exception
            r6 = r27
            r7 = r28
        L_0x00ab:
            if (r3 == 0) goto L_0x00b0
            r3.recycle()
        L_0x00b0:
            goto L_0x00b2
        L_0x00b1:
            throw r0
        L_0x00b2:
            goto L_0x00b1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.BitmapUtils.cropBitmap(android.content.Context, android.net.Uri, float[], int, boolean, int, int, int, android.graphics.Rect, int, int, boolean, boolean):com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled");
    }

    private static BitmapFactory.Options decodeImageForOption(ContentResolver resolver, Uri uri) throws FileNotFoundException {
        InputStream stream = null;
        try {
            stream = resolver.openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(stream, EMPTY_RECT, options);
            options.inJustDecodeBounds = false;
            return options;
        } finally {
            closeSafe(stream);
        }
    }

    /* JADX WARN: Finally extract failed */
    private static Bitmap decodeImage(ContentResolver resolver, Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        do {
            InputStream stream = null;
            try {
                stream = resolver.openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(stream, EMPTY_RECT, options);
                closeSafe(stream);
                return decodeStream;
            } catch (OutOfMemoryError e) {
                try {
                    options.inSampleSize *= 2;
                    closeSafe(stream);
                    if (options.inSampleSize > 512) {
                        throw new RuntimeException("Failed to decode image: " + uri);
                    }
                } catch (Throwable th) {
                    closeSafe(stream);
                    throw th;
                }
            }
        } while (options.inSampleSize > 512);
        throw new RuntimeException("Failed to decode image: " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.theartofdev.edmodo.cropper.BitmapUtils.BitmapSampled decodeSampledBitmapRegion(android.content.Context r6, android.net.Uri r7, android.graphics.Rect r8, int r9, int r10, int r11) {
        /*
            r0 = 0
            r1 = 0
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options     // Catch: all -> 0x0058, Exception -> 0x005a
            r2.<init>()     // Catch: all -> 0x0058, Exception -> 0x005a
            int r3 = r8.width()     // Catch: all -> 0x0058, Exception -> 0x005a
            int r4 = r8.height()     // Catch: all -> 0x0058, Exception -> 0x005a
            int r3 = calculateInSampleSizeByReqestedSize(r3, r4, r9, r10)     // Catch: all -> 0x0058, Exception -> 0x005a
            int r3 = r3 * r11
            r2.inSampleSize = r3     // Catch: all -> 0x0058, Exception -> 0x005a
            android.content.ContentResolver r3 = r6.getContentResolver()     // Catch: all -> 0x0058, Exception -> 0x005a
            java.io.InputStream r3 = r3.openInputStream(r7)     // Catch: all -> 0x0058, Exception -> 0x005a
            r0 = r3
            r3 = 0
            android.graphics.BitmapRegionDecoder r3 = android.graphics.BitmapRegionDecoder.newInstance(r0, r3)     // Catch: all -> 0x0058, Exception -> 0x005a
            r1 = r3
        L_0x0027:
            com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled r3 = new com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled     // Catch: OutOfMemoryError -> 0x003b, all -> 0x0058, Exception -> 0x005a
            android.graphics.Bitmap r4 = r1.decodeRegion(r8, r2)     // Catch: OutOfMemoryError -> 0x003b, all -> 0x0058, Exception -> 0x005a
            int r5 = r2.inSampleSize     // Catch: OutOfMemoryError -> 0x003b, all -> 0x0058, Exception -> 0x005a
            r3.<init>(r4, r5)     // Catch: OutOfMemoryError -> 0x003b, all -> 0x0058, Exception -> 0x005a
            closeSafe(r0)
            if (r1 == 0) goto L_0x003a
            r1.recycle()
        L_0x003a:
            return r3
        L_0x003b:
            r3 = move-exception
            int r4 = r2.inSampleSize     // Catch: all -> 0x0058, Exception -> 0x005a
            int r4 = r4 * 2
            r2.inSampleSize = r4     // Catch: all -> 0x0058, Exception -> 0x005a
            int r3 = r2.inSampleSize     // Catch: all -> 0x0058, Exception -> 0x005a
            r4 = 512(0x200, float:7.175E-43)
            if (r3 <= r4) goto L_0x0027
            closeSafe(r0)
            if (r1 == 0) goto L_0x0050
            r1.recycle()
        L_0x0050:
            com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled r2 = new com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled
            r3 = 0
            r4 = 1
            r2.<init>(r3, r4)
            return r2
        L_0x0058:
            r2 = move-exception
            goto L_0x007e
        L_0x005a:
            r2 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch: all -> 0x0058
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: all -> 0x0058
            r4.<init>()     // Catch: all -> 0x0058
            java.lang.String r5 = "Failed to load sampled bitmap: "
            r4.append(r5)     // Catch: all -> 0x0058
            r4.append(r7)     // Catch: all -> 0x0058
            java.lang.String r5 = "\r\n"
            r4.append(r5)     // Catch: all -> 0x0058
            java.lang.String r5 = r2.getMessage()     // Catch: all -> 0x0058
            r4.append(r5)     // Catch: all -> 0x0058
            java.lang.String r4 = r4.toString()     // Catch: all -> 0x0058
            r3.<init>(r4, r2)     // Catch: all -> 0x0058
            throw r3     // Catch: all -> 0x0058
        L_0x007e:
            closeSafe(r0)
            if (r1 == 0) goto L_0x0086
            r1.recycle()
        L_0x0086:
            goto L_0x0088
        L_0x0087:
            throw r2
        L_0x0088:
            goto L_0x0087
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.BitmapUtils.decodeSampledBitmapRegion(android.content.Context, android.net.Uri, android.graphics.Rect, int, int, int):com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled");
    }

    private static Bitmap cropForRotatedImage(Bitmap bitmap, float[] points, Rect rect, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY) {
        if (degreesRotated % 90 == 0) {
            return bitmap;
        }
        int adjLeft = 0;
        int adjTop = 0;
        int width = 0;
        int height = 0;
        double rads = Math.toRadians(degreesRotated);
        int compareTo = (degreesRotated < 90 || (degreesRotated > 180 && degreesRotated < 270)) ? rect.left : rect.right;
        int i = 0;
        while (true) {
            if (i < points.length) {
                if (points[i] >= compareTo - 1 && points[i] <= compareTo + 1) {
                    double sin = Math.sin(rads);
                    double d = rect.bottom - points[i + 1];
                    Double.isNaN(d);
                    adjLeft = (int) Math.abs(sin * d);
                    double cos = Math.cos(rads);
                    double d2 = points[i + 1] - rect.top;
                    Double.isNaN(d2);
                    adjTop = (int) Math.abs(cos * d2);
                    double d3 = points[i + 1] - rect.top;
                    double sin2 = Math.sin(rads);
                    Double.isNaN(d3);
                    width = (int) Math.abs(d3 / sin2);
                    double d4 = rect.bottom - points[i + 1];
                    double cos2 = Math.cos(rads);
                    Double.isNaN(d4);
                    height = (int) Math.abs(d4 / cos2);
                    break;
                }
                i += 2;
            } else {
                break;
            }
        }
        int i2 = adjLeft + width;
        rect.set(adjLeft, adjTop, i2, adjTop + height);
        if (fixAspectRatio) {
            fixRectForAspectRatio(rect, aspectRatioX, aspectRatioY);
        }
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
        if (bitmap == bitmap2) {
            return bitmap2;
        }
        bitmap.recycle();
        return bitmap2;
    }

    private static int calculateInSampleSizeByReqestedSize(int width, int height, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            while ((height / 2) / inSampleSize > reqHeight && (width / 2) / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static int calculateInSampleSizeByMaxTextureSize(int width, int height) {
        int inSampleSize = 1;
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = getMaxTextureSize();
        }
        if (mMaxTextureSize > 0) {
            while (true) {
                int i = height / inSampleSize;
                int i2 = mMaxTextureSize;
                if (i <= i2 && width / inSampleSize <= i2) {
                    break;
                }
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private static Bitmap rotateAndFlipBitmapInt(Bitmap bitmap, int degrees, boolean flipHorizontally, boolean flipVertically) {
        if (degrees <= 0 && !flipHorizontally && !flipVertically) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        float f = -1.0f;
        float f2 = flipHorizontally ? -1.0f : 1.0f;
        if (!flipVertically) {
            f = 1.0f;
        }
        matrix.postScale(f2, f);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (newBitmap != bitmap) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    private static int getMaxTextureSize() {
        try {
            EGL10 egl = (EGL10) EGLContext.getEGL();
            EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            int[] version = new int[2];
            egl.eglInitialize(display, version);
            int[] totalConfigurations = new int[1];
            egl.eglGetConfigs(display, null, 0, totalConfigurations);
            EGLConfig[] configurationsList = new EGLConfig[totalConfigurations[0]];
            egl.eglGetConfigs(display, configurationsList, totalConfigurations[0], totalConfigurations);
            int[] textureSize = new int[1];
            int maximumTextureSize = 0;
            for (int i = 0; i < totalConfigurations[0]; i++) {
                egl.eglGetConfigAttrib(display, configurationsList[i], 12332, textureSize);
                if (maximumTextureSize < textureSize[0]) {
                    maximumTextureSize = textureSize[0];
                }
            }
            egl.eglTerminate(display);
            return Math.max(maximumTextureSize, 2048);
        } catch (Exception e) {
            return 2048;
        }
    }

    private static void closeSafe(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class BitmapSampled {
        public final Bitmap bitmap;
        final int sampleSize;

        BitmapSampled(Bitmap bitmap, int sampleSize) {
            this.bitmap = bitmap;
            this.sampleSize = sampleSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class RotateBitmapResult {
        public final Bitmap bitmap;
        final int degrees;

        RotateBitmapResult(Bitmap bitmap, int degrees) {
            this.bitmap = bitmap;
            this.degrees = degrees;
        }
    }
}
