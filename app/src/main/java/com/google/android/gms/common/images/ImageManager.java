package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zaj;
import com.google.android.gms.internal.base.zan;
import com.google.android.gms.internal.base.zao;
import com.google.android.gms.internal.base.zar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public final class ImageManager {
    private static final Object zamj = new Object();
    private static HashSet<Uri> zamk = new HashSet<>();
    private static ImageManager zaml;
    private final Context mContext;
    private final Handler mHandler = new zar(Looper.getMainLooper());
    private final ExecutorService zamm = zan.zact().zaa(4, zao.zasg);
    private final zaa zamn = null;
    private final zaj zamo = new zaj();
    private final Map<zab, ImageReceiver> zamp = new HashMap();
    private final Map<Uri, ImageReceiver> zamq = new HashMap();
    private final Map<Uri, Long> zamr = new HashMap();

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    public static ImageManager create(Context context) {
        if (zaml == null) {
            zaml = new ImageManager(context, false);
        }
        return zaml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    public static final class zaa extends LruCache<zaa, Bitmap> {
        @Override // androidx.collection.LruCache
        protected final /* synthetic */ int sizeOf(zaa zaaVar, Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            return bitmap2.getHeight() * bitmap2.getRowBytes();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        public final /* synthetic */ void entryRemoved(boolean z, zaa zaaVar, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zaaVar, bitmap, bitmap2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    public final class zab implements Runnable {
        private final zab zamw;

        public zab(zab zabVar) {
            this.zamw = zabVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zamp.get(this.zamw);
            if (imageReceiver != null) {
                ImageManager.this.zamp.remove(this.zamw);
                imageReceiver.zac(this.zamw);
            }
            zaa zaaVar = this.zamw.zamz;
            if (zaaVar.uri == null) {
                this.zamw.zaa(ImageManager.this.mContext, ImageManager.this.zamo, true);
                return;
            }
            Bitmap zaa = ImageManager.this.zaa(zaaVar);
            if (zaa != null) {
                this.zamw.zaa(ImageManager.this.mContext, zaa, true);
                return;
            }
            Long l = (Long) ImageManager.this.zamr.get(zaaVar.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zamw.zaa(ImageManager.this.mContext, ImageManager.this.zamo, true);
                    return;
                }
                ImageManager.this.zamr.remove(zaaVar.uri);
            }
            this.zamw.zaa(ImageManager.this.mContext, ImageManager.this.zamo);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zamq.get(zaaVar.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zaaVar.uri);
                ImageManager.this.zamq.put(zaaVar.uri, imageReceiver2);
            }
            imageReceiver2.zab(this.zamw);
            if (!(this.zamw instanceof zac)) {
                ImageManager.this.zamp.put(this.zamw, imageReceiver2);
            }
            synchronized (ImageManager.zamj) {
                if (!ImageManager.zamk.contains(zaaVar.uri)) {
                    ImageManager.zamk.add(zaaVar.uri);
                    imageReceiver2.zacc();
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    private final class zac implements Runnable {
        private final Uri zamt;
        private final ParcelFileDescriptor zamx;

        public zac(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zamt = uri;
            this.zamx = parcelFileDescriptor;
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z;
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            ParcelFileDescriptor parcelFileDescriptor = this.zamx;
            boolean z2 = false;
            Bitmap bitmap = null;
            if (parcelFileDescriptor != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.zamt);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
                    sb.append("OOM while loading bitmap for uri: ");
                    sb.append(valueOf);
                    Log.e("ImageManager", sb.toString(), e);
                    z2 = true;
                }
                try {
                    this.zamx.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
                z = z2;
            } else {
                bitmap = null;
                z = false;
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zad(this.zamt, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.zamt);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 32);
                sb2.append("Latch interrupted while posting ");
                sb2.append(valueOf2);
                Log.w("ImageManager", sb2.toString());
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    private final class ImageReceiver extends ResultReceiver {
        private final Uri zamt;
        private final ArrayList<zab> zamu = new ArrayList<>();

        ImageReceiver(Uri uri) {
            super(new zar(Looper.getMainLooper()));
            this.zamt = uri;
        }

        public final void zab(zab zabVar) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zamu.add(zabVar);
        }

        public final void zac(zab zabVar) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zamu.remove(zabVar);
        }

        public final void zacc() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.putExtra(Constants.EXTRA_URI, this.zamt);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }

        @Override // android.os.ResultReceiver
        public final void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zamm.execute(new zac(this.zamt, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* loaded from: classes.dex */
    private final class zad implements Runnable {
        private final Bitmap mBitmap;
        private final CountDownLatch zads;
        private final Uri zamt;
        private boolean zamy;

        public zad(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zamt = uri;
            this.mBitmap = bitmap;
            this.zamy = z;
            this.zads = countDownLatch;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zamn != null) {
                if (this.zamy) {
                    ImageManager.this.zamn.evictAll();
                    System.gc();
                    this.zamy = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.zamn.put(new zaa(this.zamt), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zamq.remove(this.zamt);
            if (imageReceiver != null) {
                ArrayList arrayList = imageReceiver.zamu;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    zab zabVar = (zab) arrayList.get(i);
                    if (z) {
                        zabVar.zaa(ImageManager.this.mContext, this.mBitmap, false);
                    } else {
                        ImageManager.this.zamr.put(this.zamt, Long.valueOf(SystemClock.elapsedRealtime()));
                        zabVar.zaa(ImageManager.this.mContext, ImageManager.this.zamo, false);
                    }
                    if (!(zabVar instanceof zac)) {
                        ImageManager.this.zamp.remove(zabVar);
                    }
                }
            }
            this.zads.countDown();
            synchronized (ImageManager.zamj) {
                ImageManager.zamk.remove(this.zamt);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa(new zad(imageView, uri));
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa(new zad(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zad zadVar = new zad(imageView, uri);
        zadVar.zanb = i;
        zaa(zadVar);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa(new zac(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zac zacVar = new zac(onImageLoadedListener, uri);
        zacVar.zanb = i;
        zaa(zacVar);
    }

    private final void zaa(zab zabVar) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zab(zabVar).run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap zaa(zaa zaaVar) {
        zaa zaaVar2 = this.zamn;
        if (zaaVar2 == null) {
            return null;
        }
        return zaaVar2.get(zaaVar);
    }
}
