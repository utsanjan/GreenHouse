package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.applisto.appcloner.hooking.Hooking;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.SkipParamCheck;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class HostsBlocker {
    private static final String PREF_KEY_PREFIX = "com.applisto.appcloner.host_";
    private static boolean sAllowAllOtherHosts;
    private static String sAllowLabel;
    private static boolean sBlockByDefault;
    private static String sBlockLabel;
    private static String sContentText;
    private static String sContentTitle;
    private static Context sContext;
    private static String sHostBlockedMessage;
    private static String sHostsBlockerTitle;
    private static String sIgnoreLabel;
    private static NotificationManager sNotificationManager;
    private static SharedPreferences sPreferences;
    private static String sSocksProxyHost;
    private static final String TAG = HostsBlocker.class.getSimpleName();
    private static Map<String, Boolean> sHostsFileHosts = new HashMap();
    private static final Map<String, Boolean> sHosts = Collections.synchronizedMap(new HashMap());
    private static final Map<Integer, String> sNotifications = Collections.synchronizedMap(new HashMap());
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void install(Context context, boolean blockByDefault, String socksProxyHost, boolean allowAllOtherHosts) {
        sContext = context;
        sBlockByDefault = blockByDefault;
        sSocksProxyHost = socksProxyHost;
        sAllowAllOtherHosts = allowAllOtherHosts;
        sNotificationManager = (NotificationManager) context.getSystemService("notification");
        sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        synchronized (sHosts) {
            try {
                BufferedReader r = new BufferedReader(new InputStreamReader(context.getAssets().open(".blockedHostsFile")));
                try {
                    Pattern p = Pattern.compile("(0.0.0.0|127.0.0.1|# x.x.x.x)\\s+(\\S*)");
                    while (true) {
                        String line = r.readLine();
                        if (line != null) {
                            String line2 = line.trim();
                            if (line2.startsWith("# x.x.x.x") || !line2.startsWith("#")) {
                                Matcher m = p.matcher(line2);
                                if (m.find()) {
                                    String host = m.group(2);
                                    boolean allowedBlocked = "# x.x.x.x".equals(m.group(1));
                                    String str = TAG;
                                    Log.i(str, "install; host: " + host + ", allowedBlocked: " + allowedBlocked);
                                    sHostsFileHosts.put(host, Boolean.valueOf(allowedBlocked));
                                }
                            }
                        } else {
                            try {
                                break;
                            } catch (Exception e) {
                            }
                        }
                    }
                } finally {
                    try {
                        r.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (FileNotFoundException e3) {
            } catch (Exception e4) {
                Log.w(TAG, e4);
            }
            for (String key : sPreferences.getAll().keySet()) {
                if (key != null && key.startsWith(PREF_KEY_PREFIX)) {
                    boolean b = sPreferences.getBoolean(key, false);
                    sHosts.put(key.substring(PREF_KEY_PREFIX.length()), Boolean.valueOf(b));
                }
            }
            String str2 = TAG;
            Log.i(str2, "install; sHosts: " + sHosts);
        }
        Hooking.initHooking(context);
        Hooking.addHookClass(Hook.class);
        Log.i(TAG, "install; hooks installed");
        sHostsBlockerTitle = Utils.getAppClonerResourceText(sContext, "hosts_blocker_title", "Hosts blocker").toString();
        sContentTitle = Utils.getAppClonerResourceText(sContext, "hosts_blocker_app_is_accessing_title", "%s is accessing").toString();
        sContentText = Utils.getAppClonerResourceText(sContext, "hosts_blocker_touch_to_block_title", "Touch to block %s").toString();
        sAllowLabel = Utils.getAppClonerResourceText(sContext, "label_allow", "Allow").toString();
        sBlockLabel = Utils.getAppClonerResourceText(sContext, "label_block", "Block").toString();
        sIgnoreLabel = Utils.getAppClonerResourceText(sContext, "label_ignore", "Ignore").toString();
        sHostBlockedMessage = Utils.getAppClonerResourceText(sContext, "hosts_blocker_host_blocked_message", "Host %s blocked.").toString();
    }

    @HookReflectClass("java.net.PlainSocketImpl")
    /* loaded from: classes2.dex */
    public static class Hook {
        @HookMethodBackup("socketConnect")
        @SkipParamCheck
        static Method socketConnectBackup;

        @MethodParams({InetAddress.class, int.class, int.class})
        @HookMethod("socketConnect")
        public static void socketConnectHook(Object thiz, InetAddress address, int port, int timeout) throws Throwable {
            String str = HostsBlocker.TAG;
            Log.i(str, "socketConnectHook; address: " + address + ", port: " + port + ", timeout: " + timeout);
            HostsBlocker.checkHost(address);
            Hooking.callInstanceOrigin(socketConnectBackup, thiz, address, Integer.valueOf(port), Integer.valueOf(timeout));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkHost(InetAddress address) throws UnknownHostException {
        if (address != null) {
            checkHost(address.getHostName());
        }
    }

    private static void checkHost(final String host) throws UnknownHostException {
        if (host != null && host.length() > 0) {
            String str = sSocksProxyHost;
            if ((str == null || !str.equals(host)) && !"127.0.0.1".equals(host) && !"localhost".equals(host) && !host.startsWith("192.168.")) {
                List<String> list = splitHost(host);
                for (String key : list) {
                    if (sHosts.containsKey(key)) {
                        Boolean b = sHosts.get(key);
                        if (b == null || b.booleanValue()) {
                            String str2 = TAG;
                            Log.i(str2, "checkHost; ignored/allowed; host: " + host);
                            return;
                        }
                        String str3 = TAG;
                        Log.i(str3, "checkHost; blocked; host: " + host);
                        throw new UnknownHostException("Blocked");
                    } else if (sHostsFileHosts.containsKey(key)) {
                        boolean allowedBlocked = sHostsFileHosts.get(key).booleanValue();
                        if (allowedBlocked) {
                            addAllowedHost(host);
                            return;
                        }
                        addBlockedHost(host);
                        sHandler.post(new Runnable() { // from class: com.applisto.appcloner.classes.HostsBlocker.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(HostsBlocker.sContext, HostsBlocker.sHostBlockedMessage.replace("%s", host), 0).show();
                            }
                        });
                        String str4 = TAG;
                        Log.i(str4, "checkHost; blocked; host: " + host);
                        throw new UnknownHostException("Blocked");
                    }
                }
                if (!sAllowAllOtherHosts) {
                    notifyHost(host);
                    if (sBlockByDefault) {
                        throw new UnknownHostException("Blocked by default");
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> splitHost(String host) {
        List<String> list = new ArrayList<>();
        list.add(host);
        boolean alpha = false;
        int len = host.length();
        int n = 0;
        while (true) {
            if (n < len) {
                char c = host.charAt(n);
                if (c != '.' && !Character.isDigit(c)) {
                    alpha = true;
                    break;
                }
                n++;
            } else {
                break;
            }
        }
        if (alpha) {
            while (true) {
                int pos = host.indexOf(46);
                if (pos == -1) {
                    break;
                }
                host = host.substring(pos + 1);
                if (host.indexOf(46) == -1) {
                    break;
                }
                list.add("*." + host);
            }
        }
        return list;
    }

    private static void notifyHost(String host) {
        int notificationId = host.hashCode();
        synchronized (sNotifications) {
            if (!sNotifications.containsKey(Integer.valueOf(notificationId))) {
                sNotifications.put(Integer.valueOf(notificationId), host);
                Log.i(TAG, "notifyHost; host: " + host);
                int requestCode = host.hashCode();
                Context context = sContext;
                PendingIntent contentIntent = PendingIntent.getBroadcast(context, requestCode, new Intent(host, null, context, ContentReceiver.class), 134217728);
                Context context2 = sContext;
                PendingIntent allowIntent = PendingIntent.getBroadcast(context2, requestCode + 1, new Intent(host, null, context2, AllowReceiver.class), 1073741824);
                Context context3 = sContext;
                PendingIntent blockIntent = PendingIntent.getBroadcast(context3, requestCode + 2, new Intent(host, null, context3, BlockReceiver.class), 1073741824);
                Context context4 = sContext;
                PendingIntent ignoreIntent = PendingIntent.getBroadcast(context4, requestCode + 3, new Intent(host, null, context4, IgnoreReceiver.class), 1073741824);
                PendingIntent cancelIntent = PendingIntent.getBroadcast(sContext, requestCode + 4, new Intent(Integer.toString(notificationId), null, sContext, CancelReceiver.class), 1073741824);
                String contentTitle = sContentTitle.replace("%s", Utils.getAppName(sContext));
                Notification.Builder builder = new Notification.Builder(sContext).setSmallIcon(17301543).setContentTitle(contentTitle).setContentIntent(contentIntent).setDeleteIntent(cancelIntent).setAutoCancel(false);
                if (Build.VERSION.SDK_INT >= 16) {
                    builder.setContentText(host).addAction(requestCode, sAllowLabel, allowIntent).addAction(requestCode, sBlockLabel, blockIntent).addAction(requestCode, sIgnoreLabel, ignoreIntent);
                } else {
                    String contentText = sContentText.replace("%s", host);
                    builder.setContentText(contentText).setContentIntent(blockIntent);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    builder.setVisibility(-1);
                }
                Utils.setSmallNotificationIcon(builder);
                Notification notification = builder.getNotification();
                notification.sound = null;
                notification.defaults &= -2;
                sNotificationManager.notify(notificationId, notification);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addAllowedHost(String host) {
        String str = TAG;
        Log.i(str, "addAllowedHost; host: " + host);
        synchronized (sPreferences) {
            sHosts.put(host, true);
            SharedPreferences.Editor edit = sPreferences.edit();
            edit.putBoolean(PREF_KEY_PREFIX + host, true).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addBlockedHost(String host) {
        String str = TAG;
        Log.i(str, "addBlockedHost; host: " + host);
        synchronized (sPreferences) {
            sHosts.put(host, false);
            SharedPreferences.Editor edit = sPreferences.edit();
            edit.putBoolean(PREF_KEY_PREFIX + host, false).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addIgnoredHost(String host) {
        String str = TAG;
        Log.i(str, "addIgnoredHost; host: " + host);
        synchronized (sPreferences) {
            sHosts.put(host, null);
            SharedPreferences.Editor edit = sPreferences.edit();
            edit.remove(PREF_KEY_PREFIX + host).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void whois(Context context, String host) {
        Log.i(TAG, "whois; host: " + host);
        if (host.startsWith("*.")) {
            host = host.substring(2);
        }
        try {
            String url = "http://whois.domaintools.com/" + host;
            Intent i = new Intent("android.intent.action.VIEW");
            i.setFlags(268435456);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (Exception e) {
            Log.w(TAG, e);
            Toast.makeText(context, "Failed to open browser.", 1).show();
        }
    }

    public static Map<String, Boolean> getAllowedBlockedHosts() {
        if (sPreferences == null) {
            return null;
        }
        Map<String, Boolean> map = new HashMap<>();
        synchronized (sPreferences) {
            for (String key : sPreferences.getAll().keySet()) {
                if (key != null && key.startsWith(PREF_KEY_PREFIX)) {
                    String host = key.substring(PREF_KEY_PREFIX.length());
                    boolean allowedBlocked = sPreferences.getBoolean(key, false);
                    map.put(host, Boolean.valueOf(allowedBlocked));
                }
            }
        }
        return map;
    }

    public static void setAllowedBlockedHosts(Map<String, Boolean> allowedBlockedHosts) {
        for (String host : allowedBlockedHosts.keySet()) {
            Boolean allowedBlocked = allowedBlockedHosts.get(host);
            if (allowedBlocked == null) {
                addIgnoredHost(host);
            } else if (allowedBlocked.booleanValue()) {
                addAllowedHost(host);
            } else {
                addBlockedHost(host);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class HostReceiver extends BroadcastReceiver {
        protected abstract void handleHost(Context context, String str);

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String host = intent.getAction();
                if (!TextUtils.isEmpty(host)) {
                    handleHost(context, host);
                }
            } catch (Exception e) {
                Log.w(HostsBlocker.TAG, e);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ContentReceiver extends HostReceiver {
        @Override // com.applisto.appcloner.classes.HostsBlocker.HostReceiver
        protected void handleHost(Context context, String host) {
            String str = HostsBlocker.TAG;
            Log.i(str, "ContentReceiver; handleHost; host: " + host);
            Intent i = new Intent(context, HostsBlockerActivity.class);
            i.setFlags(268435456);
            i.putExtra("host", host);
            context.startActivity(i);
        }
    }

    /* loaded from: classes2.dex */
    public static class AllowReceiver extends HostReceiver {
        @Override // com.applisto.appcloner.classes.HostsBlocker.HostReceiver
        protected void handleHost(Context context, String host) {
            String str = HostsBlocker.TAG;
            Log.i(str, "AllowReceiver; handleHost; host: " + host);
            HostsBlocker.addAllowedHost(host);
            HostsBlocker.removeNotification(host);
        }
    }

    /* loaded from: classes2.dex */
    public static class BlockReceiver extends HostReceiver {
        @Override // com.applisto.appcloner.classes.HostsBlocker.HostReceiver
        protected void handleHost(Context context, String host) {
            String str = HostsBlocker.TAG;
            Log.i(str, "BlockReceiver; handleHost; host: " + host);
            HostsBlocker.addBlockedHost(host);
            HostsBlocker.removeNotification(host);
        }
    }

    /* loaded from: classes2.dex */
    public static class IgnoreReceiver extends HostReceiver {
        @Override // com.applisto.appcloner.classes.HostsBlocker.HostReceiver
        protected void handleHost(Context context, String host) {
            String str = HostsBlocker.TAG;
            Log.i(str, "IgnoreReceiver; handleHost; host: " + host);
            HostsBlocker.addIgnoredHost(host);
            HostsBlocker.removeNotification(host);
        }
    }

    /* loaded from: classes2.dex */
    public static class CancelReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String notificationId = intent.getAction();
                String str = HostsBlocker.TAG;
                Log.i(str, "CancelReceiver; onReceive; notificationId: " + notificationId);
                HostsBlocker.sNotifications.remove(Integer.valueOf(Integer.parseInt(notificationId)));
            } catch (Exception e) {
                Log.w(HostsBlocker.TAG, e);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class HostsBlockerActivity extends Activity {
        @Override // android.app.Activity
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            String host = getIntent().getStringExtra("host");
            String str = HostsBlocker.TAG;
            Log.i(str, "onCreate; host: " + host);
            List<String> list = HostsBlocker.splitHost(host);
            final String[] items = (String[]) list.toArray(new String[0]);
            final AtomicReference<String> ref = new AtomicReference<>(items[0]);
            AlertDialog.Builder builder = Utils.getDialogBuilder(this).setTitle(HostsBlocker.sHostsBlockerTitle).setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int which) {
                    String item = items[which];
                    ref.set(item);
                }
            });
            if (Build.VERSION.SDK_INT > 21) {
                builder.setNeutralButton("Whois", (DialogInterface.OnClickListener) null).setNegativeButton(HostsBlocker.sAllowLabel, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        HostsBlocker.addAllowedHost((String) ref.get());
                        HostsBlocker.removeNotification((String) ref.get());
                    }
                }).setPositiveButton(HostsBlocker.sBlockLabel, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        HostsBlocker.addBlockedHost((String) ref.get());
                        HostsBlocker.removeNotification((String) ref.get());
                    }
                });
            } else {
                builder.setNegativeButton("Whois", (DialogInterface.OnClickListener) null).setNeutralButton(HostsBlocker.sAllowLabel, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        HostsBlocker.addAllowedHost((String) ref.get());
                        HostsBlocker.removeNotification((String) ref.get());
                    }
                }).setPositiveButton(HostsBlocker.sBlockLabel, new DialogInterface.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialog, int which) {
                        HostsBlocker.addBlockedHost((String) ref.get());
                        HostsBlocker.removeNotification((String) ref.get());
                    }
                });
            }
            AlertDialog dialog = builder.create();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.6
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialog2) {
                    HostsBlockerActivity.this.finish();
                }
            });
            dialog.show();
            if (Build.VERSION.SDK_INT > 21) {
                dialog.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        HostsBlocker.whois(HostsBlockerActivity.this, (String) ref.get());
                    }
                });
            } else {
                dialog.getButton(-2).setOnClickListener(new View.OnClickListener() { // from class: com.applisto.appcloner.classes.HostsBlocker.HostsBlockerActivity.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        HostsBlocker.whois(HostsBlockerActivity.this, (String) ref.get());
                    }
                });
            }
            Utils.keepDialogOpenOnOrientationChange(dialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void removeNotification(String host) {
        String str = TAG;
        Log.i(str, "removeNotification; host: " + host);
        try {
            sNotificationManager.cancel(host.hashCode());
            if (host.startsWith("*.")) {
                host = host.substring(2);
            }
            synchronized (sNotifications) {
                for (Integer notificationId : sNotifications.keySet()) {
                    String otherHost = sNotifications.get(notificationId);
                    if (otherHost.endsWith(host)) {
                        sNotificationManager.cancel(notificationId.intValue());
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }
}
