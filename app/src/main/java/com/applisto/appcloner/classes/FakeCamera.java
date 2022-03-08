package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.ExifInterface;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;
import com.applisto.appcloner.hooking.Hooking;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import java.io.ByteArrayOutputStrean;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public class FakeCamera {
    private static final String ACTION_FAKE_CAMERA_ROTATE_ANTI_CLOCKWISE = "com.applisto.appcloner.action.FAKE_CAMERA_ROTATE_ANTI_CLOCKWISE";
    private static final String ACTION_FAKE_CAMERA_ROTATE_CLOCKWISE = "com.applisto.appcloner.action.FAKE_CAMERA_ROTATE_CLOCKWISE";
    private static final String ACTION_FAKE_CAMERA_SELECT_CAMERA_PICTURE = "com.applisto.appcloner.action.FAKE_CAMERA_SELECT_CAMERA_PICTURE";
    private static final int NOTIFICATION_ID = 556712456;
    private static Bitmap sBitmap;
    private static Context sContext;
    private static byte[] sJpegBytes;
    private static long sPictureTakenTimestamp;
    private static final String TAG = FakeCamera.class.getSimpleName();
    private static Handler sHandler = new Handler();

    public static void install(Context context) {
        Log.i(TAG, "install; ");
        sContext = context;
        Hooking.initHooking(context);
        Hooking.addHookClass(Hook1.class);
        Hooking.addHookClass(Hook2.class);
        Hooking.addHookClass(Hook3.class);
        Hooking.addHookClass(Hook4.class);
    }

    @HookReflectClass("android.hardware.Camera")
    /* loaded from: classes2.dex */
    public static class Hook1 {
        @HookMethodBackup("release")
        static Method releaseBackup;
        @HookMethodBackup("startPreview")
        static Method startPreviewBackup;
        @HookMethodBackup("takePicture")
        @MethodParams({Camera.ShutterCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class})
        static Method takePictureBackup;

        @HookMethod("startPreview")
        public static void startPreviewHook(Camera thiz) throws Throwable {
            Log.i(FakeCamera.TAG, "startPreviewHook; ");
            Hooking.callInstanceOrigin(startPreviewBackup, thiz, new Object[0]);
            FakeCamera.showNotificationDelayed();
        }

        @HookMethod("release")
        public static void releaseHook(Camera thiz) throws Throwable {
            Log.i(FakeCamera.TAG, "releaseHook; ");
            FakeCamera.sHandler.removeCallbacksAndMessages(null);
            FakeCamera.hideNotification();
            Hooking.callInstanceOrigin(releaseBackup, thiz, new Object[0]);
        }

        @MethodParams({Camera.ShutterCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class, Camera.PictureCallback.class})
        @HookMethod("takePicture")
        public static void takePictureHook(final Camera thiz, final Camera.ShutterCallback shutter, Camera.PictureCallback raw, Camera.PictureCallback postview, final Camera.PictureCallback jpeg) throws Throwable {
            if (FakeCamera.sBitmap != null) {
                Log.i(FakeCamera.TAG, "takePictureHook; providing selected picture...");
                FakeCamera.sHandler.post(new Runnable() { // from class: com.applisto.appcloner.classes.FakeCamera.Hook1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Camera.ShutterCallback shutterCallback = shutter;
                        if (shutterCallback != null) {
                            try {
                                shutterCallback.onShutter();
                            } catch (Exception e) {
                                Log.w(FakeCamera.TAG, e);
                            }
                        }
                        if (jpeg != null) {
                            if (FakeCamera.sJpegBytes != null) {
                                jpeg.onPictureTaken(FakeCamera.sJpegBytes, thiz);
                            } else {
                                ByteArrayOutputStrean baos = new ByteArrayOutputStrean();
                                FakeCamera.sBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                jpeg.onPictureTaken(baos.toByteArray(), thiz);
                            }
                        }
                        long unused = FakeCamera.sPictureTakenTimestamp = System.currentTimeMillis();
                    }
                });
                return;
            }
            Log.i(FakeCamera.TAG, "takePictureHook; calling backup...");
            Hooking.callInstanceOrigin(takePictureBackup, thiz, shutter, raw, postview, jpeg);
        }
    }

    @HookReflectClass("android.hardware.camera2.CameraManager")
    /* loaded from: classes2.dex */
    public static class Hook2 {
        @HookMethodBackup("openCameraForUid")
        @MethodParams({String.class, CameraDevice.StateCallback.class, Executor.class, int.class})
        static Method openCameraForUidBackup;

        @MethodParams({String.class, CameraDevice.StateCallback.class, Executor.class, int.class})
        @HookMethod("openCameraForUid")
        public static void openCameraForUidHook(CameraManager thiz, String cameraId, CameraDevice.StateCallback callback, Executor executor, int clientUid) throws Throwable {
            Log.i(FakeCamera.TAG, "openCameraForUidHook; ");
            Hooking.callInstanceOrigin(openCameraForUidBackup, thiz, cameraId, callback, executor, Integer.valueOf(clientUid));
            FakeCamera.showNotificationDelayed();
        }
    }

    @HookReflectClass("android.hardware.camera2.impl.CameraDeviceImpl")
    /* loaded from: classes2.dex */
    public static class Hook3 {
        @HookMethodBackup("close")
        static Method closeBackup;

        @HookMethod("close")
        public static void closeHook(Object thiz) throws Throwable {
            Log.i(FakeCamera.TAG, "closeHook; ");
            FakeCamera.sHandler.removeCallbacksAndMessages(null);
            FakeCamera.hideNotification();
            Hooking.callInstanceOrigin(closeBackup, thiz, new Object[0]);
        }
    }

    @HookReflectClass("android.media.ImageReader")
    /* loaded from: classes2.dex */
    public static class Hook4 {
        @HookMethodBackup("acquireLatestImage")
        static Method acquireLatestImageBackup;
        @HookMethodBackup("acquireNextImage")
        static Method acquireNextImageBackup;

        @HookMethod("acquireLatestImage")
        public static Image acquireLatestImageHook(ImageReader thiz) throws Throwable {
            Log.i(FakeCamera.TAG, "acquireLatestImageHook; ");
            if (FakeCamera.sBitmap != null) {
                return getImage();
            }
            return (Image) Hooking.callInstanceOrigin(acquireLatestImageBackup, thiz, new Object[0]);
        }

        @HookMethod("acquireNextImage")
        public static Image acquireNextImageHook(ImageReader thiz) throws Throwable {
            Log.i(FakeCamera.TAG, "acquireNextImageHook; ");
            if (FakeCamera.sBitmap != null) {
                return getImage();
            }
            return (Image) Hooking.callInstanceOrigin(acquireNextImageBackup, thiz, new Object[0]);
        }

        private static Image getImage() {
            long unused = FakeCamera.sPictureTakenTimestamp = System.currentTimeMillis();
            return new Image() { // from class: com.applisto.appcloner.classes.FakeCamera.Hook4.1
                @Override // android.media.Image
                public int getFormat() {
                    return 0;
                }

                @Override // android.media.Image
                public int getWidth() {
                    return FakeCamera.sBitmap.getWidth();
                }

                @Override // android.media.Image
                public int getHeight() {
                    return FakeCamera.sBitmap.getHeight();
                }

                @Override // android.media.Image
                public long getTimestamp() {
                    return FakeCamera.sPictureTakenTimestamp;
                }

                @Override // android.media.Image
                public int getTransform() {
                    return 0;
                }

                @Override // android.media.Image
                public int getScalingMode() {
                    return 0;
                }

                @Override // android.media.Image
                public Image.Plane[] getPlanes() {
                    return new Image.Plane[]{new Image.Plane() { // from class: com.applisto.appcloner.classes.FakeCamera.Hook4.1.1
                        @Override // android.media.Image.Plane
                        public int getRowStride() {
                            return 0;
                        }

                        @Override // android.media.Image.Plane
                        public int getPixelStride() {
                            return 0;
                        }

                        @Override // android.media.Image.Plane
                        public ByteBuffer getBuffer() {
                            Log.i(FakeCamera.TAG, "getBuffer; ");
                            if (FakeCamera.sJpegBytes != null) {
                                return ByteBuffer.wrap(FakeCamera.sJpegBytes);
                            }
                            ByteArrayOutputStrean baos = new ByteArrayOutputStrean();
                            FakeCamera.sBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            return ByteBuffer.wrap(baos.toByteArray());
                        }
                    }};
                }

                @Override // android.media.Image
                public void close() {
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showNotificationDelayed() {
        sHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.FakeCamera.1
            @Override // java.lang.Runnable
            public void run() {
                FakeCamera.showNotification();
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showNotification() {
        Log.i(TAG, "showNotification; ");
        hideNotification();
        try {
            NotificationManager notificationManager = (NotificationManager) sContext.getSystemService("notification");
            if (notificationManager != null) {
                Intent i = new Intent(ACTION_FAKE_CAMERA_SELECT_CAMERA_PICTURE);
                i.setPackage(sContext.getPackageName());
                boolean z = false;
                PendingIntent selectCameraPicturePendingIntent = PendingIntent.getBroadcast(sContext, 0, i, 0);
                String text = sBitmap != null ? "Tap the shutter button to use the selected picture." : "Touch to select picture.";
                Notification.Builder builder = new Notification.Builder(sContext).setContentTitle("Fake camera").setContentText(text).setContentIntent(selectCameraPicturePendingIntent).setAutoCancel(true).setWhen(0L);
                if (sBitmap != null && Build.VERSION.SDK_INT >= 16) {
                    builder.setStyle(new Notification.BigPictureStyle().setBigContentTitle("Fake camera").setSummaryText(text).bigPicture(sBitmap));
                    if (Build.VERSION.SDK_INT >= 21) {
                        Intent i2 = new Intent(ACTION_FAKE_CAMERA_ROTATE_CLOCKWISE);
                        i2.setPackage(sContext.getPackageName());
                        PendingIntent rotateClockwisePendingIntent = PendingIntent.getBroadcast(sContext, 0, i2, 0);
                        Intent i3 = new Intent(ACTION_FAKE_CAMERA_ROTATE_ANTI_CLOCKWISE);
                        i3.setPackage(sContext.getPackageName());
                        PendingIntent rotateAntiClockwisePendingIntent = PendingIntent.getBroadcast(sContext, 0, i3, 0);
                        builder.addAction(new Notification.Action(0, "Rotate ↻", rotateClockwisePendingIntent));
                        builder.addAction(new Notification.Action(0, "Rotate ↺", rotateAntiClockwisePendingIntent));
                    }
                }
                if (System.currentTimeMillis() - sPictureTakenTimestamp > 1000) {
                    z = true;
                }
                Utils.setSmallNotificationIcon(builder, z);
                notificationManager.notify(NOTIFICATION_ID, builder.getNotification());
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hideNotification() {
        Log.i(TAG, "hideNotification; ");
        try {
            NotificationManager notificationManager = (NotificationManager) sContext.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(NOTIFICATION_ID);
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    /* loaded from: classes2.dex */
    public static class FakeCameraReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = FakeCamera.TAG;
            Log.i(str, "onReceive; intent: " + intent);
            try {
                String action = intent.getAction();
                if (FakeCamera.ACTION_FAKE_CAMERA_SELECT_CAMERA_PICTURE.equals(action)) {
                    Intent i = new Intent(FakeCamera.sContext, FakeCameraActivity.class);
                    i.setFlags(268435456);
                    FakeCamera.sContext.startActivity(i);
                } else if (FakeCamera.ACTION_FAKE_CAMERA_ROTATE_CLOCKWISE.equals(action)) {
                    rotateBitmap(90);
                } else if (FakeCamera.ACTION_FAKE_CAMERA_ROTATE_ANTI_CLOCKWISE.equals(action)) {
                    rotateBitmap(-90);
                }
            } catch (Exception e) {
                Log.w(FakeCamera.TAG, e);
            }
        }

        private void rotateBitmap(int degrees) {
            String str = FakeCamera.TAG;
            Log.i(str, "rotateBitmap; degrees: " + degrees);
            if (FakeCamera.sBitmap != null) {
                Matrix matrix = new Matrix();
                matrix.postRotate(degrees);
                Bitmap unused = FakeCamera.sBitmap = Bitmap.createBitmap(FakeCamera.sBitmap, 0, 0, FakeCamera.sBitmap.getWidth(), FakeCamera.sBitmap.getHeight(), matrix, true);
                byte[] unused2 = FakeCamera.sJpegBytes = null;
                FakeCamera.showNotification();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class FakeCameraActivity extends Activity {
        private static final String PERMISSION = "android.permission.READ_EXTERNAL_STORAGE";
        private static final int REQUEST_CODE_SELECT_PICTURE = 1;

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (Build.VERSION.SDK_INT < 23 || checkSelfPermission(PERMISSION) == 0) {
                onSelectPicture();
            } else {
                requestPermissions(new String[]{PERMISSION}, 0);
            }
        }

        private void onSelectPicture() {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        }

        @Override // android.app.Activity
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            String str = FakeCamera.TAG;
            Log.i(str, "onRequestPermissionsResult; permissions: " + Arrays.toString(permissions) + ", grantResults: " + Arrays.toString(grantResults));
            if (permissions.length == 1 && grantResults.length == 1 && PERMISSION.equals(permissions[0]) && grantResults[0] == 0) {
                onSelectPicture();
                return;
            }
            Toast.makeText(this, "Permission not granted.", 1).show();
            finish();
        }

        /* JADX WARN: Type inference failed for: r2v5, types: [com.applisto.appcloner.classes.FakeCamera$FakeCameraActivity$1] */
        @Override // android.app.Activity
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            String str = FakeCamera.TAG;
            Log.i(str, "onActivityResult; requestCode: " + requestCode + ", resultCode: " + resultCode);
            if (requestCode == 1) {
                Bitmap unused = FakeCamera.sBitmap = null;
                byte[] unused2 = FakeCamera.sJpegBytes = null;
                if (resultCode == -1) {
                    try {
                        final String path = getPath(FakeCamera.sContext, data.getData());
                        String str2 = FakeCamera.TAG;
                        Log.i(str2, "onActivityResult; path: " + path);
                        new Thread() { // from class: com.applisto.appcloner.classes.FakeCamera.FakeCameraActivity.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                try {
                                    Bitmap unused3 = FakeCamera.sBitmap = BitmapFactory.decodeFile(path);
                                    byte[] bytes = Utils.readFully(new FileInputStream(path), true);
                                    if (FakeCameraActivity.isJpeg(bytes)) {
                                        Log.i(FakeCamera.TAG, "onActivityResult; JPEG detected");
                                        byte[] unused4 = FakeCamera.sJpegBytes = bytes;
                                        Bitmap unused5 = FakeCamera.sBitmap = FakeCameraActivity.normalizeImageOrientation(FakeCamera.sBitmap, path);
                                    }
                                    Thread.sleep(1000L);
                                    FakeCamera.showNotification();
                                } catch (Exception e) {
                                    Log.w(FakeCamera.TAG, e);
                                }
                            }
                        }.start();
                    } catch (Throwable t) {
                        Log.w(FakeCamera.TAG, t);
                        Bitmap unused3 = FakeCamera.sBitmap = null;
                        byte[] unused4 = FakeCamera.sJpegBytes = null;
                    }
                } else {
                    FakeCamera.showNotification();
                }
                finish();
            }
        }

        private static String getPath(Context context, Uri uri) {
            if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(context, uri)) {
                String docId = uri.getScheme();
                if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(docId)) {
                    if (isGooglePhotosUri(uri)) {
                        return uri.getLastPathSegment();
                    }
                    return getDataColumn(context, uri, null, null);
                } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                    return uri.getPath();
                }
            } else if (isExternalStorageDocument(uri)) {
                String docId2 = DocumentsContract.getDocumentId(uri);
                String[] split = docId2.split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id).longValue());
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                String docId3 = DocumentsContract.getDocumentId(uri);
                String[] split2 = docId3.split(":");
                String type = split2[0];
                Uri contentUri2 = null;
                if ("image".equals(type)) {
                    contentUri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String[] selectionArgs = {split2[1]};
                return getDataColumn(context, contentUri2, "_id=?", selectionArgs);
            }
            return null;
        }

        private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
            Cursor cursor = null;
            String[] projection = {"_data"};
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow("_data");
                    return cursor.getString(index);
                } else if (cursor == null) {
                    return null;
                } else {
                    cursor.close();
                    return null;
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        private static boolean isExternalStorageDocument(Uri uri) {
            return "com.android.externalstorage.documents".equals(uri.getAuthority());
        }

        private static boolean isDownloadsDocument(Uri uri) {
            return "com.android.providers.downloads.documents".equals(uri.getAuthority());
        }

        private static boolean isMediaDocument(Uri uri) {
            return "com.android.providers.media.documents".equals(uri.getAuthority());
        }

        private static boolean isGooglePhotosUri(Uri uri) {
            return "com.google.android.apps.photos.content".equals(uri.getAuthority());
        }

        public static boolean isJpeg(byte[] bytes) {
            return bytes[0] == -1 && bytes[1] == -40 && bytes[2] == -1;
        }

        public static Bitmap normalizeImageOrientation(Bitmap bitmap, String path) {
            int orientation = getImageOrientation(path);
            String str = FakeCamera.TAG;
            Log.i(str, "normalizeImageOrientation; orientation: " + orientation);
            if (orientation == 0) {
                return bitmap;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);
            try {
                return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            } finally {
                bitmap.recycle();
                System.gc();
            }
        }

        public static int getImageOrientation(String path) {
            try {
                int attributeInt = new ExifInterface(path).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
                if (attributeInt == 3) {
                    return 180;
                }
                if (attributeInt == 6) {
                    return 90;
                }
                if (attributeInt != 8) {
                    return 0;
                }
                return 270;
            } catch (IOException e) {
                Log.w(FakeCamera.TAG, e);
                return 0;
            }
        }
    }
}
