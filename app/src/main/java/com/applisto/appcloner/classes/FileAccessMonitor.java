package com.applisto.appcloner.classes;

import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class FileAccessMonitor {
    private static final int MAX_SIZE = 10000;
    private static final String TAG = FileAccessMonitor.class.getSimpleName();
    private static final AtomicLong sIndex = new AtomicLong();
    private static TreeMap<Long, String> sMap;

    public static void install() {
        Log.i(TAG, "install; ");
        try {
            sMap = new TreeMap<>();
            FilenameFilter filenameFilter = new FilenameFilter() { // from class: com.applisto.appcloner.classes.FileAccessMonitor.1
                private String mLastEntry;

                @Override // java.io.FilenameFilter
                public boolean accept(File dir, String name) {
                    long index = FileAccessMonitor.sIndex.incrementAndGet();
                    synchronized (FileAccessMonitor.sMap) {
                        String entry = System.currentTimeMillis() + " " + name;
                        if (!entry.equals(this.mLastEntry)) {
                            FileAccessMonitor.sMap.put(Long.valueOf(index), entry);
                            if (FileAccessMonitor.sMap.size() > 10000) {
                                FileAccessMonitor.sMap.remove(FileAccessMonitor.sMap.firstKey());
                            }
                            this.mLastEntry = entry;
                        }
                    }
                    return true;
                }
            };
            AppClonerNative.registerFilenameFilter(filenameFilter);
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    public static Map<Long, String> getFileAccessMonitorEntries(long afterIndex) {
        NavigableMap<Long, String> tailMap;
        TreeMap<Long, String> treeMap = sMap;
        if (treeMap == null) {
            return null;
        }
        synchronized (treeMap) {
            tailMap = sMap.tailMap(Long.valueOf(afterIndex), false);
        }
        return tailMap;
    }
}
