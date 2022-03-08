package com.applisto.appcloner.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class PictureInPicture extends AbstractActivityContentProvider {
    private static final String TAG = PictureInPicture.class.getSimpleName();
    static Activity sActivity;
    private Handler mHandler = new Handler();
    private int mPictureInPictureKeyCode;
    private boolean mPictureInPictureLongPress;
    private boolean mPictureInPictureSupport;

    public PictureInPicture(CloneSettings cloneSettings) {
        this.mPictureInPictureSupport = cloneSettings.getBoolean("pictureInPictureSupport", false).booleanValue();
        this.mPictureInPictureKeyCode = cloneSettings.getInteger("pictureInPictureKeyCode", 0).intValue();
        this.mPictureInPictureLongPress = cloneSettings.getBoolean("pictureInPictureLongPress", false).booleanValue();
        String str = TAG;
        Log.i(str, "PictureInPicture; mPictureInPictureSupport: " + this.mPictureInPictureSupport + ", mPictureInPictureKeyCode: " + this.mPictureInPictureKeyCode + ", mPictureInPictureLongPress: " + this.mPictureInPictureLongPress);
    }

    public void init(Context context) {
        if (this.mPictureInPictureSupport) {
            onCreate();
            Log.i(TAG, "init; created");
        }
    }

    @Override // com.applisto.appcloner.classes.AbstractActivityContentProvider
    protected void onActivityCreated(final Activity activity) {
        String str = TAG;
        Log.i(str, "onActivityCreated; activity: " + activity);
        if (this.mPictureInPictureSupport) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.PictureInPicture.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Window window = activity.getWindow();
                        window.setCallback(new WindowCallbackWrapper(window.getCallback()) { // from class: com.applisto.appcloner.classes.PictureInPicture.1.1
                            @Override // com.applisto.appcloner.classes.WindowCallbackWrapper, android.view.Window.Callback
                            public boolean dispatchKeyEvent(KeyEvent event) {
                                int keyCode = event.getKeyCode();
                                boolean longPress = event.isLongPress() || event.getRepeatCount() == 10;
                                String str2 = PictureInPicture.TAG;
                                Log.i(str2, "dispatchKeyEvent; keyCode: " + keyCode + ", longPress: " + longPress + ", event: " + event);
                                if (keyCode == PictureInPicture.this.mPictureInPictureKeyCode) {
                                    int action = event.getAction();
                                    if (action == 0 && (!PictureInPicture.this.mPictureInPictureLongPress || longPress)) {
                                        PictureInPicture.this.startPictureInPictureDelayed(activity);
                                        return true;
                                    }
                                }
                                return super.dispatchKeyEvent(event);
                            }
                        });
                        Log.i(PictureInPicture.TAG, "run; window callback installed");
                    } catch (Exception e) {
                        Log.w(PictureInPicture.TAG, e);
                    }
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPictureInPictureDelayed(final Activity activity) {
        String str = TAG;
        Log.i(str, "startPictureInPictureDelayed; activity: " + activity);
        this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.PictureInPicture.2
            @Override // java.lang.Runnable
            public void run() {
                PictureInPicture.this.startPictureInPicture(activity);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPictureInPicture(Activity activity) {
        String str = TAG;
        Log.i(str, "startPictureInPicture; activity: " + activity);
        try {
            Method method = activity.getClass().getMethod("enterPictureInPictureMode", new Class[0]);
            method.invoke(activity, new Object[0]);
        } catch (Throwable t) {
            Log.w(TAG, t);
            Toast.makeText(activity, "Failed to start picture-in-picture mode.", 1).show();
        }
    }
}
