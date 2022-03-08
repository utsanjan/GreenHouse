package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public class LogcatViewer {
    private static final String ACTION_SWITCH_LOGCAT_LEVEL = "com.applisto.appcloner.action.SWITCH_LOGCAT_LEVEL";
    private static final String EXTRA_LEVEL = "level";
    private static final int MAX_LINES = 1000;
    private static final String NOTIFICATION_CHANNEL_ID = "logcat_viewer";
    private static final String NOTIFICATION_CHANNEL_NAME = "Logcat viewer";
    private Context mContext;
    private Handler mHandler = new Handler();
    private String mLevel = ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
    private final boolean mLogcatViewer;
    private Process mProcess;
    private static final List<String> sLines = Collections.synchronizedList(new ArrayList());
    private static boolean sAutoScroll = true;

    public LogcatViewer(CloneSettings cloneSettings) {
        this.mLogcatViewer = cloneSettings.getBoolean("logcatViewer", false).booleanValue();
        Log.i("ContentValues", "LogcatViewer; mLogcatViewer: " + this.mLogcatViewer);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.applisto.appcloner.classes.LogcatViewer$2] */
    public void init(Context context) {
        if (this.mLogcatViewer) {
            this.mContext = context;
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager != null) {
                        NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, 2);
                        notificationManager.createNotificationChannel(channel);
                    }
                } catch (Throwable t) {
                    Log.w("ContentValues", t);
                }
            }
            startReadLogs();
            context.registerReceiver(new BroadcastReceiver() { // from class: com.applisto.appcloner.classes.LogcatViewer.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    LogcatViewer.this.mLevel = intent.getStringExtra("level");
                    boolean unused = LogcatViewer.sAutoScroll = true;
                    LogcatViewer.this.startReadLogs();
                }
            }, new IntentFilter(ACTION_SWITCH_LOGCAT_LEVEL));
            new Thread() { // from class: com.applisto.appcloner.classes.LogcatViewer.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    while (true) {
                        try {
                            synchronized (LogcatViewer.sLines) {
                                LogcatViewer.sLines.wait();
                                LogcatViewer.this.showLogsDelayed();
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.applisto.appcloner.classes.LogcatViewer$3] */
    public void startReadLogs() {
        Process process = this.mProcess;
        if (process != null) {
            process.destroy();
            this.mProcess = null;
        }
        new Thread() { // from class: com.applisto.appcloner.classes.LogcatViewer.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                LogcatViewer.this.readLogs();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLogsDelayed() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.LogcatViewer.4
            @Override // java.lang.Runnable
            public void run() {
                LogcatViewer.this.showLogs();
            }
        }, 250L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLogs() {
        try {
            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
            String logs = getLogs(5);
            Intent i = new Intent(this.mContext, LogcatActivity.class);
            i.setPackage(this.mContext.getPackageName());
            PendingIntent pendingIntent = PendingIntent.getActivity(this.mContext, 0, i, 0);
            Intent intentVerbose = new Intent(ACTION_SWITCH_LOGCAT_LEVEL);
            intentVerbose.putExtra("level", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            intentVerbose.setPackage(this.mContext.getPackageName());
            PendingIntent pendingIntentVerbose = PendingIntent.getBroadcast(this.mContext, 1, intentVerbose, 0);
            Intent intentDebug = new Intent(ACTION_SWITCH_LOGCAT_LEVEL);
            intentDebug.putExtra("level", "D");
            intentDebug.setPackage(this.mContext.getPackageName());
            PendingIntent pendingIntentDebug = PendingIntent.getBroadcast(this.mContext, 2, intentDebug, 0);
            Intent intentInfo = new Intent(ACTION_SWITCH_LOGCAT_LEVEL);
            intentInfo.putExtra("level", "I");
            intentInfo.setPackage(this.mContext.getPackageName());
            PendingIntent pendingIntentInfo = PendingIntent.getBroadcast(this.mContext, 3, intentInfo, 0);
            Notification.Builder builder = new Notification.Builder(this.mContext).setContentIntent(pendingIntent).setStyle(new Notification.BigTextStyle().bigText(logs)).setPriority(2).setAutoCancel(false);
            Utils.setSmallNotificationIcon(builder);
            if (Build.VERSION.SDK_INT >= 26) {
                builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            }
            builder.addAction(new Notification.Action(0, "VERBOSE", pendingIntentVerbose));
            builder.addAction(new Notification.Action(0, "DEBUG", pendingIntentDebug));
            builder.addAction(new Notification.Action(0, "INFO", pendingIntentInfo));
            Notification notification = builder.getNotification();
            notification.sound = null;
            notification.defaults &= -2;
            notificationManager.notify(354432, notification);
        } catch (Exception e) {
            Log.w("ContentValues", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getLogs(int maxLines) {
        StringBuilder b = new StringBuilder();
        synchronized (sLines) {
            List<String> lines = sLines.subList(Math.max(sLines.size() - maxLines, 0), sLines.size());
            for (String line : lines) {
                if (b.length() > 0) {
                    b.append("\n");
                }
                b.append(line);
            }
        }
        return b.toString();
    }

    public void readLogs() {
        try {
            synchronized (sLines) {
                sLines.clear();
                sLines.notifyAll();
            }
            Runtime runtime = Runtime.getRuntime();
            this.mProcess = runtime.exec("logcat -v time *:" + this.mLevel);
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.mProcess.getInputStream()));
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    synchronized (sLines) {
                        if (!line.startsWith("--------- beginning of") && !line.contains("OverScrollerOptimization")) {
                            sLines.add(line);
                            if (sLines.size() > 1000) {
                                sLines.remove(0);
                            }
                            sLines.notifyAll();
                        }
                    }
                } else {
                    try {
                        reader.close();
                        return;
                    } catch (IOException e) {
                        return;
                    }
                }
            }
        } catch (Exception e2) {
        }
    }

    /* loaded from: classes2.dex */
    public static class LogcatActivity extends Activity {
        private Handler mHandler = new Handler();
        private boolean mPaused;
        private ScrollView mScrollView;
        private TextView mTextView;
        private Thread mThread;

        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setTitle("Logcat of " + Utils.getAppName(this));
            this.mTextView = new TextView(this);
            this.mTextView.setTypeface(Typeface.create("monospace", 0));
            this.mTextView.setTextSize(12.0f);
            this.mTextView.setTextColor(-657931);
            this.mTextView.setBackgroundColor(-15906911);
            int padding = Utils.dp2px(this, 16.0f);
            this.mTextView.setPadding(padding, 0, padding, 0);
            this.mTextView.setOnTouchListener(new View.OnTouchListener() { // from class: com.applisto.appcloner.classes.LogcatViewer.LogcatActivity.1
                private GestureDetector gestureDetector;

                {
                    this.gestureDetector = new GestureDetector(LogcatActivity.this, new GestureDetector.SimpleOnGestureListener() { // from class: com.applisto.appcloner.classes.LogcatViewer.LogcatActivity.1.1
                        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
                        public boolean onDoubleTap(MotionEvent e) {
                            LogcatActivity.this.mPaused = !LogcatActivity.this.mPaused;
                            if (LogcatActivity.this.mPaused) {
                                Toast.makeText(LogcatActivity.this, "Paused", 0).show();
                            } else {
                                Toast.makeText(LogcatActivity.this, "Resumed", 0).show();
                                LogcatActivity.this.showLogsDelayed();
                            }
                            return super.onDoubleTap(e);
                        }
                    });
                }

                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View v, MotionEvent event) {
                    this.gestureDetector.onTouchEvent(event);
                    return true;
                }
            });
            Toast.makeText(this, "Double-tap to pause", 1).show();
            this.mScrollView = new ScrollView(this) { // from class: com.applisto.appcloner.classes.LogcatViewer.LogcatActivity.2
                @Override // android.view.View
                protected void onScrollChanged(int l, int t, int oldl, int oldt) {
                    super.onScrollChanged(l, t, oldl, oldt);
                    boolean unused = LogcatViewer.sAutoScroll = false;
                }
            };
            this.mScrollView.setScrollbarFadingEnabled(false);
            this.mScrollView.addView(this.mTextView, new FrameLayout.LayoutParams(-1, -2));
            setContentView(this.mScrollView);
            showLogs();
            this.mThread = new Thread() { // from class: com.applisto.appcloner.classes.LogcatViewer.LogcatActivity.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    while (LogcatActivity.this.mThread != null) {
                        try {
                            synchronized (LogcatViewer.sLines) {
                                LogcatViewer.sLines.wait();
                                LogcatActivity.this.showLogsDelayed();
                            }
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            this.mThread.start();
        }

        @Override // android.app.Activity
        protected void onDestroy() {
            super.onDestroy();
            Thread thread = this.mThread;
            if (thread != null) {
                thread.interrupt();
                this.mThread = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showLogsDelayed() {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.LogcatViewer.LogcatActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    LogcatActivity.this.showLogs();
                }
            }, 100L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showLogs() {
            if (!this.mPaused) {
                String logs = LogcatViewer.getLogs(1000);
                this.mTextView.setText(logs);
                this.mTextView.requestLayout();
                if (LogcatViewer.sAutoScroll) {
                    this.mScrollView.postDelayed(new Runnable() { // from class: com.applisto.appcloner.classes.LogcatViewer.LogcatActivity.5
                        @Override // java.lang.Runnable
                        public void run() {
                            LogcatActivity.this.mScrollView.fullScroll(130);
                            Log.i("ContentValues", "run; scrolled");
                        }
                    }, 750L);
                }
            }
        }
    }
}
