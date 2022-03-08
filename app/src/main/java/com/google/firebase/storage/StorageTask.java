package com.google.firebase.storage;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageTask.ProvideError;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public abstract class StorageTask<ResultT extends ProvideError> extends ControllableTask<ResultT> {
    static final int INTERNAL_STATE_CANCELED = 256;
    static final int INTERNAL_STATE_CANCELING = 32;
    static final int INTERNAL_STATE_FAILURE = 64;
    static final int INTERNAL_STATE_IN_PROGRESS = 4;
    static final int INTERNAL_STATE_NOT_STARTED = 1;
    static final int INTERNAL_STATE_PAUSED = 16;
    static final int INTERNAL_STATE_PAUSING = 8;
    static final int INTERNAL_STATE_QUEUED = 2;
    static final int INTERNAL_STATE_SUCCESS = 128;
    static final int STATES_CANCELED = 256;
    static final int STATES_COMPLETE = 448;
    static final int STATES_FAILURE = 64;
    static final int STATES_INPROGRESS = -465;
    static final int STATES_PAUSED = 16;
    static final int STATES_SUCCESS = 128;
    private static final String TAG = "StorageTask";
    private ResultT finalResult;
    private static final HashMap<Integer, HashSet<Integer>> ValidUserInitiatedStateChanges = new HashMap<>();
    private static final HashMap<Integer, HashSet<Integer>> ValidTaskInitiatedStateChanges = new HashMap<>();
    final TaskListenerImpl<OnProgressListener<? super ResultT>, ResultT> progressManager = new TaskListenerImpl<>(this, STATES_INPROGRESS, StorageTask$$Lambda$7.lambdaFactory$());
    final TaskListenerImpl<OnPausedListener<? super ResultT>, ResultT> pausedManager = new TaskListenerImpl<>(this, 16, StorageTask$$Lambda$8.lambdaFactory$());
    protected final Object syncObject = new Object();
    final TaskListenerImpl<OnSuccessListener<? super ResultT>, ResultT> successManager = new TaskListenerImpl<>(this, 128, StorageTask$$Lambda$1.lambdaFactory$(this));
    final TaskListenerImpl<OnFailureListener, ResultT> failureManager = new TaskListenerImpl<>(this, 64, StorageTask$$Lambda$4.lambdaFactory$(this));
    final TaskListenerImpl<OnCompleteListener<ResultT>, ResultT> completeListener = new TaskListenerImpl<>(this, STATES_COMPLETE, StorageTask$$Lambda$5.lambdaFactory$(this));
    final TaskListenerImpl<OnCanceledListener, ResultT> cancelManager = new TaskListenerImpl<>(this, 256, StorageTask$$Lambda$6.lambdaFactory$(this));
    private volatile int currentState = 1;

    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    public interface ProvideError {
        Exception getError();
    }

    public abstract StorageReference getStorage();

    abstract void run();

    abstract void schedule();

    abstract ResultT snapStateImpl();

    static {
        ValidUserInitiatedStateChanges.put(1, new HashSet<>(Arrays.asList(16, 256)));
        ValidUserInitiatedStateChanges.put(2, new HashSet<>(Arrays.asList(8, 32)));
        ValidUserInitiatedStateChanges.put(4, new HashSet<>(Arrays.asList(8, 32)));
        ValidUserInitiatedStateChanges.put(16, new HashSet<>(Arrays.asList(2, 256)));
        ValidUserInitiatedStateChanges.put(64, new HashSet<>(Arrays.asList(2, 256)));
        ValidTaskInitiatedStateChanges.put(1, new HashSet<>(Arrays.asList(2, 64)));
        ValidTaskInitiatedStateChanges.put(2, new HashSet<>(Arrays.asList(4, 64, 128)));
        ValidTaskInitiatedStateChanges.put(4, new HashSet<>(Arrays.asList(4, 64, 128)));
        ValidTaskInitiatedStateChanges.put(8, new HashSet<>(Arrays.asList(16, 64, 128)));
        ValidTaskInitiatedStateChanges.put(32, new HashSet<>(Arrays.asList(256, 64, 128)));
    }

    public static /* synthetic */ void lambda$new$0(StorageTask storageTask, OnSuccessListener listener, ProvideError snappedState) {
        StorageTaskManager.getInstance().unRegister(storageTask);
        listener.onSuccess(snappedState);
    }

    public static /* synthetic */ void lambda$new$1(StorageTask storageTask, OnFailureListener listener, ProvideError snappedState) {
        StorageTaskManager.getInstance().unRegister(storageTask);
        listener.onFailure(snappedState.getError());
    }

    public static /* synthetic */ void lambda$new$2(StorageTask storageTask, OnCompleteListener listener, ProvideError snappedState) {
        StorageTaskManager.getInstance().unRegister(storageTask);
        listener.onComplete(storageTask);
    }

    public static /* synthetic */ void lambda$new$3(StorageTask storageTask, OnCanceledListener listener, ProvideError snappedState) {
        StorageTaskManager.getInstance().unRegister(storageTask);
        listener.onCanceled();
    }

    public boolean queue() {
        if (!tryChangeState(2, false)) {
            return false;
        }
        schedule();
        return true;
    }

    void resetState() {
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean resume() {
        if (!tryChangeState(2, true)) {
            return false;
        }
        resetState();
        schedule();
        return true;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean pause() {
        return tryChangeState(new int[]{16, 8}, true);
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean cancel() {
        return tryChangeState(new int[]{256, 32}, true);
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isComplete() {
        return (getInternalState() & STATES_COMPLETE) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isSuccessful() {
        return (getInternalState() & 128) != 0;
    }

    @Override // com.google.firebase.storage.CancellableTask, com.google.android.gms.tasks.Task
    public boolean isCanceled() {
        return getInternalState() == 256;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public boolean isInProgress() {
        return (getInternalState() & STATES_INPROGRESS) != 0;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public boolean isPaused() {
        return (getInternalState() & 16) != 0;
    }

    @Override // com.google.android.gms.tasks.Task
    public ResultT getResult() {
        if (getFinalResult() != null) {
            Throwable t = getFinalResult().getError();
            if (t == null) {
                return getFinalResult();
            }
            throw new RuntimeExecutionException(t);
        }
        throw new IllegalStateException();
    }

    @Override // com.google.android.gms.tasks.Task
    public <X extends Throwable> ResultT getResult(Class<X> exceptionType) throws Throwable {
        if (getFinalResult() == null) {
            throw new IllegalStateException();
        } else if (!exceptionType.isInstance(getFinalResult().getError())) {
            Throwable t = getFinalResult().getError();
            if (t == null) {
                return getFinalResult();
            }
            throw new RuntimeExecutionException(t);
        } else {
            throw exceptionType.cast(getFinalResult().getError());
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public Exception getException() {
        if (getFinalResult() == null) {
            return null;
        }
        return getFinalResult().getError();
    }

    public ResultT getSnapshot() {
        return snapState();
    }

    public int getInternalState() {
        return this.currentState;
    }

    public Object getSyncObject() {
        return this.syncObject;
    }

    public ResultT snapState() {
        ResultT snapStateImpl;
        synchronized (this.syncObject) {
            snapStateImpl = snapStateImpl();
        }
        return snapStateImpl;
    }

    boolean tryChangeState(int[] requestedStates, boolean userInitiated) {
        HashMap<Integer, HashSet<Integer>> table = userInitiated ? ValidUserInitiatedStateChanges : ValidTaskInitiatedStateChanges;
        synchronized (this.syncObject) {
            for (int newState : requestedStates) {
                HashSet<Integer> validStates = table.get(Integer.valueOf(getInternalState()));
                if (validStates != null && validStates.contains(Integer.valueOf(newState))) {
                    this.currentState = newState;
                    int i = this.currentState;
                    if (i == 2) {
                        StorageTaskManager.getInstance().ensureRegistered(this);
                        onQueued();
                    } else if (i == 4) {
                        onProgress();
                    } else if (i == 16) {
                        onPaused();
                    } else if (i == 64) {
                        onFailure();
                    } else if (i == 128) {
                        onSuccess();
                    } else if (i == 256) {
                        onCanceled();
                    }
                    this.successManager.onInternalStateChanged();
                    this.failureManager.onInternalStateChanged();
                    this.cancelManager.onInternalStateChanged();
                    this.completeListener.onInternalStateChanged();
                    this.pausedManager.onInternalStateChanged();
                    this.progressManager.onInternalStateChanged();
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "changed internal state to: " + getStateString(newState) + " isUser: " + userInitiated + " from state:" + getStateString(this.currentState));
                    }
                    return true;
                }
            }
            Log.w(TAG, "unable to change internal state to: " + getStateString(requestedStates) + " isUser: " + userInitiated + " from state:" + getStateString(this.currentState));
            return false;
        }
    }

    public boolean tryChangeState(int newState, boolean userInitiated) {
        return tryChangeState(new int[]{newState}, userInitiated);
    }

    protected void onQueued() {
    }

    protected void onProgress() {
    }

    protected void onPaused() {
    }

    protected void onFailure() {
    }

    protected void onSuccess() {
    }

    public void onCanceled() {
    }

    private ResultT getFinalResult() {
        ResultT resultt = this.finalResult;
        if (resultt != null) {
            return resultt;
        }
        if (!isComplete()) {
            return null;
        }
        if (this.finalResult == null) {
            this.finalResult = snapState();
        }
        return this.finalResult;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(OnPausedListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.pausedManager.addListener(null, null, listener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(Executor executor, OnPausedListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(executor);
        this.pausedManager.addListener(null, executor, listener);
        return this;
    }

    @Override // com.google.firebase.storage.ControllableTask
    public StorageTask<ResultT> addOnPausedListener(Activity activity, OnPausedListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(activity);
        this.pausedManager.addListener(activity, null, listener);
        return this;
    }

    public StorageTask<ResultT> removeOnPausedListener(OnPausedListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.pausedManager.removeListener(listener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(OnProgressListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.progressManager.addListener(null, null, listener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(Executor executor, OnProgressListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(executor);
        this.progressManager.addListener(null, executor, listener);
        return this;
    }

    @Override // com.google.firebase.storage.CancellableTask
    public StorageTask<ResultT> addOnProgressListener(Activity activity, OnProgressListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(activity);
        this.progressManager.addListener(activity, null, listener);
        return this;
    }

    public StorageTask<ResultT> removeOnProgressListener(OnProgressListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.progressManager.removeListener(listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(OnSuccessListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.successManager.addListener(null, null, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(Executor executor, OnSuccessListener<? super ResultT> listener) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(listener);
        this.successManager.addListener(null, executor, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnSuccessListener(Activity activity, OnSuccessListener<? super ResultT> listener) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(listener);
        this.successManager.addListener(activity, null, listener);
        return this;
    }

    public StorageTask<ResultT> removeOnSuccessListener(OnSuccessListener<? super ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.successManager.removeListener(listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(OnFailureListener listener) {
        Preconditions.checkNotNull(listener);
        this.failureManager.addListener(null, null, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(Executor executor, OnFailureListener listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(executor);
        this.failureManager.addListener(null, executor, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnFailureListener(Activity activity, OnFailureListener listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(activity);
        this.failureManager.addListener(activity, null, listener);
        return this;
    }

    public StorageTask<ResultT> removeOnFailureListener(OnFailureListener listener) {
        Preconditions.checkNotNull(listener);
        this.failureManager.removeListener(listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(OnCompleteListener<ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.completeListener.addListener(null, null, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(Executor executor, OnCompleteListener<ResultT> listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(executor);
        this.completeListener.addListener(null, executor, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCompleteListener(Activity activity, OnCompleteListener<ResultT> listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(activity);
        this.completeListener.addListener(activity, null, listener);
        return this;
    }

    public StorageTask<ResultT> removeOnCompleteListener(OnCompleteListener<ResultT> listener) {
        Preconditions.checkNotNull(listener);
        this.completeListener.removeListener(listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(OnCanceledListener listener) {
        Preconditions.checkNotNull(listener);
        this.cancelManager.addListener(null, null, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(Executor executor, OnCanceledListener listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(executor);
        this.cancelManager.addListener(null, executor, listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public StorageTask<ResultT> addOnCanceledListener(Activity activity, OnCanceledListener listener) {
        Preconditions.checkNotNull(listener);
        Preconditions.checkNotNull(activity);
        this.cancelManager.addListener(activity, null, listener);
        return this;
    }

    public StorageTask<ResultT> removeOnCanceledListener(OnCanceledListener listener) {
        Preconditions.checkNotNull(listener);
        this.cancelManager.removeListener(listener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWith(Executor executor, Continuation<ResultT, ContinuationResultT> continuation) {
        return continueWithImpl(executor, continuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithImpl(Executor executor, Continuation<ResultT, ContinuationResultT> continuation) {
        TaskCompletionSource<ContinuationResultT> source = new TaskCompletionSource<>();
        this.completeListener.addListener(null, executor, StorageTask$$Lambda$9.lambdaFactory$(this, continuation, source));
        return source.getTask();
    }

    /* JADX WARN: Unknown type variable: ContinuationResultT in type: ContinuationResultT */
    public static /* synthetic */ void lambda$continueWithImpl$4(StorageTask storageTask, Continuation continuation, TaskCompletionSource source, Task task) {
        try {
            Object then = continuation.then(storageTask);
            if (!source.getTask().isComplete()) {
                source.setResult(then);
            }
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                source.setException((Exception) e.getCause());
            } else {
                source.setException(e);
            }
        } catch (Exception e2) {
            source.setException(e2);
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> continueWithTask(Executor executor, Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        return continueWithTaskImpl(executor, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(SuccessContinuation<ResultT, ContinuationResultT> continuation) {
        return successTaskImpl(null, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    public <ContinuationResultT> Task<ContinuationResultT> onSuccessTask(Executor executor, SuccessContinuation<ResultT, ContinuationResultT> continuation) {
        return successTaskImpl(executor, continuation);
    }

    private <ContinuationResultT> Task<ContinuationResultT> continueWithTaskImpl(Executor executor, Continuation<ResultT, Task<ContinuationResultT>> continuation) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CancellationToken cancellationToken = cancellationTokenSource.getToken();
        TaskCompletionSource<ContinuationResultT> source = new TaskCompletionSource<>(cancellationToken);
        this.completeListener.addListener(null, executor, StorageTask$$Lambda$10.lambdaFactory$(this, continuation, source, cancellationTokenSource));
        return source.getTask();
    }

    /* JADX WARN: Unknown type variable: ContinuationResultT in type: com.google.android.gms.tasks.Task<ContinuationResultT> */
    public static /* synthetic */ void lambda$continueWithTaskImpl$5(StorageTask storageTask, Continuation continuation, TaskCompletionSource source, CancellationTokenSource cancellationTokenSource, Task task) {
        try {
            Task task2 = (Task) continuation.then(storageTask);
            if (source.getTask().isComplete()) {
                return;
            }
            if (task2 == null) {
                source.setException(new NullPointerException("Continuation returned null"));
                return;
            }
            source.getClass();
            task2.addOnSuccessListener(StorageTask$$Lambda$16.lambdaFactory$(source));
            source.getClass();
            task2.addOnFailureListener(StorageTask$$Lambda$17.lambdaFactory$(source));
            cancellationTokenSource.getClass();
            task2.addOnCanceledListener(StorageTask$$Lambda$18.lambdaFactory$(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                source.setException((Exception) e.getCause());
            } else {
                source.setException(e);
            }
        } catch (Exception e2) {
            source.setException(e2);
        }
    }

    private <ContinuationResultT> Task<ContinuationResultT> successTaskImpl(Executor executor, SuccessContinuation<ResultT, ContinuationResultT> continuation) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        CancellationToken cancellationToken = cancellationTokenSource.getToken();
        TaskCompletionSource<ContinuationResultT> source = new TaskCompletionSource<>(cancellationToken);
        this.successManager.addListener(null, executor, StorageTask$$Lambda$11.lambdaFactory$(continuation, source, cancellationTokenSource));
        return source.getTask();
    }

    /* JADX WARN: Unknown type variable: ContinuationResultT in type: com.google.android.gms.tasks.Task<ContinuationResultT> */
    public static /* synthetic */ void lambda$successTaskImpl$6(SuccessContinuation continuation, TaskCompletionSource source, CancellationTokenSource cancellationTokenSource, ProvideError result) {
        try {
            Task then = continuation.then(result);
            source.getClass();
            then.addOnSuccessListener(StorageTask$$Lambda$13.lambdaFactory$(source));
            source.getClass();
            then.addOnFailureListener(StorageTask$$Lambda$14.lambdaFactory$(source));
            cancellationTokenSource.getClass();
            then.addOnCanceledListener(StorageTask$$Lambda$15.lambdaFactory$(cancellationTokenSource));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                source.setException((Exception) e.getCause());
            } else {
                source.setException(e);
            }
        } catch (Exception e2) {
            source.setException(e2);
        }
    }

    public Runnable getRunnable() {
        return StorageTask$$Lambda$12.lambdaFactory$(this);
    }

    public static /* synthetic */ void lambda$getRunnable$7(StorageTask storageTask) {
        try {
            storageTask.run();
        } finally {
            storageTask.ensureFinalState();
        }
    }

    private void ensureFinalState() {
        if (!isComplete() && !isPaused() && getInternalState() != 2 && !tryChangeState(256, false)) {
            tryChangeState(64, false);
        }
    }

    private String getStateString(int[] states) {
        if (states.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int state : states) {
            builder.append(getStateString(state));
            builder.append(", ");
        }
        return builder.substring(0, builder.length() - 2);
    }

    private String getStateString(int state) {
        if (state == 1) {
            return "INTERNAL_STATE_NOT_STARTED";
        }
        if (state == 2) {
            return "INTERNAL_STATE_QUEUED";
        }
        if (state == 4) {
            return "INTERNAL_STATE_IN_PROGRESS";
        }
        if (state == 8) {
            return "INTERNAL_STATE_PAUSING";
        }
        if (state == 16) {
            return "INTERNAL_STATE_PAUSED";
        }
        if (state == 32) {
            return "INTERNAL_STATE_CANCELING";
        }
        if (state == 64) {
            return "INTERNAL_STATE_FAILURE";
        }
        if (state == 128) {
            return "INTERNAL_STATE_SUCCESS";
        }
        if (state != 256) {
            return "Unknown Internal State!";
        }
        return "INTERNAL_STATE_CANCELED";
    }

    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    public class SnapshotBase implements ProvideError {
        private final Exception error;

        public SnapshotBase(Exception error) {
            StorageTask.this = this$0;
            if (error != null) {
                this.error = error;
            } else if (this$0.isCanceled()) {
                this.error = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
            } else if (this$0.getInternalState() == 64) {
                this.error = StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR);
            } else {
                this.error = null;
            }
        }

        public StorageTask<ResultT> getTask() {
            return StorageTask.this;
        }

        public StorageReference getStorage() {
            return getTask().getStorage();
        }

        @Override // com.google.firebase.storage.StorageTask.ProvideError
        public Exception getError() {
            return this.error;
        }
    }
}
