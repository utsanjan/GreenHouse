package com.google.firebase.storage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class StorageTaskManager {
    private static final StorageTaskManager _instance = new StorageTaskManager();
    private final Map<String, WeakReference<StorageTask<?>>> inProgressTasks = new HashMap();
    private final Object syncObject = new Object();

    StorageTaskManager() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StorageTaskManager getInstance() {
        return _instance;
    }

    public List<UploadTask> getUploadTasksUnder(StorageReference parent) {
        List<UploadTask> unmodifiableList;
        synchronized (this.syncObject) {
            ArrayList<UploadTask> inProgressList = new ArrayList<>();
            String parentPath = parent.toString();
            for (Map.Entry<String, WeakReference<StorageTask<?>>> entry : this.inProgressTasks.entrySet()) {
                if (entry.getKey().startsWith(parentPath)) {
                    StorageTask<?> task = entry.getValue().get();
                    if (task instanceof UploadTask) {
                        inProgressList.add((UploadTask) task);
                    }
                }
            }
            unmodifiableList = Collections.unmodifiableList(inProgressList);
        }
        return unmodifiableList;
    }

    public List<FileDownloadTask> getDownloadTasksUnder(StorageReference parent) {
        List<FileDownloadTask> unmodifiableList;
        synchronized (this.syncObject) {
            ArrayList<FileDownloadTask> inProgressList = new ArrayList<>();
            String parentPath = parent.toString();
            for (Map.Entry<String, WeakReference<StorageTask<?>>> entry : this.inProgressTasks.entrySet()) {
                if (entry.getKey().startsWith(parentPath)) {
                    StorageTask<?> task = entry.getValue().get();
                    if (task instanceof FileDownloadTask) {
                        inProgressList.add((FileDownloadTask) task);
                    }
                }
            }
            unmodifiableList = Collections.unmodifiableList(inProgressList);
        }
        return unmodifiableList;
    }

    public void ensureRegistered(StorageTask<?> targetTask) {
        synchronized (this.syncObject) {
            this.inProgressTasks.put(targetTask.getStorage().toString(), new WeakReference<>(targetTask));
        }
    }

    public void unRegister(StorageTask<?> targetTask) {
        synchronized (this.syncObject) {
            String key = targetTask.getStorage().toString();
            WeakReference<StorageTask<?>> weakReference = this.inProgressTasks.get(key);
            StorageTask<?> task = weakReference != null ? weakReference.get() : null;
            if (task == null || task == targetTask) {
                this.inProgressTasks.remove(key);
            }
        }
    }
}
