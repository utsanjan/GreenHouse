package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class SplashScreenActivity extends Activity {
    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    private String mOriginalActivityName;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        View contentView;
        super.onCreate(savedInstanceState);
        try {
            CloneSettings cloneSettings = CloneSettings.getInstance(this);
            int splashScreenDuration = cloneSettings.getInteger("splashScreenDuration", 3).intValue();
            try {
                float splashScreenMargin = cloneSettings.getFloat("splashScreenMargin", Float.valueOf(0.3f)).floatValue() / 2.0f;
                Point realScreenSize = Utils.getRealScreenSize(getWindowManager().getDefaultDisplay());
                int horizontalMargin = Math.round(realScreenSize.x * splashScreenMargin);
                int verticalMargin = Math.round(realScreenSize.y * splashScreenMargin);
                byte[] bytes = Utils.readFully(getAssets().open(".splashScreenFile"), true);
                boolean gif = isGif(bytes);
                if (gif) {
                    CustomGifView gifView = new CustomGifView(this, new ByteArrayInputStream(bytes));
                    FrameLayout frameLayout = new FrameLayout(this);
                    frameLayout.addView(gifView, new FrameLayout.LayoutParams(-1, -1, 17));
                    contentView = frameLayout;
                } else {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageBitmap(bitmap);
                    contentView = imageView;
                }
                contentView.setPadding(horizontalMargin, verticalMargin, horizontalMargin, verticalMargin);
                setContentView(contentView);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), 129);
            Bundle metaData = activityInfo.metaData;
            if (metaData != null) {
                this.mOriginalActivityName = metaData.getString("com.applisto.appcloner.originalActivityName");
                if (this.mOriginalActivityName != null) {
                    if (this.mOriginalActivityName.startsWith(".")) {
                        this.mOriginalActivityName = getPackageName() + this.mOriginalActivityName;
                    }
                    Intent i = new Intent(getIntent());
                    i.setComponent(new ComponentName(this, Class.forName(this.mOriginalActivityName)));
                    i.setFlags(268435456);
                    new Handler().postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.-$$Lambda$SplashScreenActivity$yXLk1id2mEUWEr-fgdpI8_Zunbs
                        @Override // java.lang.Runnable
                        public final void run() {
                            SplashScreenActivity.this.startApp();
                        }
                    }, splashScreenDuration * 1000);
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, e2);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startApp() {
        Log.i(TAG, "startApp; ");
        try {
            Intent i = new Intent(getIntent());
            i.setComponent(new ComponentName(this, Class.forName(this.mOriginalActivityName)));
            i.setFlags(268435456);
            startActivity(i);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        finish();
    }

    public static boolean isGif(byte[] data) {
        return data[0] == 71 && data[1] == 73 && data[2] == 70;
    }

    /* loaded from: classes2.dex */
    public static class CustomGifView extends View {
        private Movie mGifMovie;
        private float mMovieHeight;
        private long mMovieStart;
        private float mMovieWidth;

        public CustomGifView(Context context, InputStream gifInputStream) {
            super(context);
            this.mGifMovie = Movie.decodeStream(gifInputStream);
            this.mMovieWidth = this.mGifMovie.width();
            this.mMovieHeight = this.mGifMovie.height();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            long now = SystemClock.uptimeMillis();
            if (this.mMovieStart == 0) {
                this.mMovieStart = now;
            }
            Movie movie = this.mGifMovie;
            if (movie != null) {
                int duration = movie.duration();
                if (duration == 0) {
                    duration = 1000;
                }
                int time = (int) ((now - this.mMovieStart) % duration);
                this.mGifMovie.setTime(time);
                float w = getWidth();
                float h = getHeight();
                float sx = w / this.mMovieWidth;
                float sy = h / this.mMovieHeight;
                float s = Math.min(sx, sy);
                float iw = this.mMovieWidth * s;
                float ih = this.mMovieHeight * s;
                canvas.save();
                canvas.translate((w - iw) / 2.0f, (h - ih) / 2.0f);
                canvas.scale(s, s);
                this.mGifMovie.draw(canvas, 0.0f, 0.0f);
                canvas.restore();
                invalidate();
            }
        }
    }
}
