package com.google.firebase.database.android;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public abstract class AndroidAuthTokenProvider implements AuthTokenProvider {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.android.AndroidAuthTokenProvider$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements AuthTokenProvider {
        final /* synthetic */ InternalAuthProvider val$authProvider;

        AnonymousClass1(InternalAuthProvider internalAuthProvider) {
            this.val$authProvider = internalAuthProvider;
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider
        public void getToken(boolean forceRefresh, AuthTokenProvider.GetTokenCompletionListener listener) {
            Task<GetTokenResult> getTokenResult = this.val$authProvider.getAccessToken(forceRefresh);
            getTokenResult.addOnSuccessListener(AndroidAuthTokenProvider$1$$Lambda$1.lambdaFactory$(listener)).addOnFailureListener(AndroidAuthTokenProvider$1$$Lambda$2.lambdaFactory$(listener));
        }

        public static /* synthetic */ void lambda$getToken$0(AuthTokenProvider.GetTokenCompletionListener listener, GetTokenResult result) {
            listener.onSuccess(result.getToken());
        }

        public static /* synthetic */ void lambda$getToken$1(AuthTokenProvider.GetTokenCompletionListener listener, Exception e) {
            if (AndroidAuthTokenProvider.isUnauthenticatedUsage(e)) {
                listener.onSuccess(null);
            } else {
                listener.onError(e.getMessage());
            }
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider
        public void addTokenChangeListener(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenListener) {
            IdTokenListener idTokenListener = AndroidAuthTokenProvider$1$$Lambda$3.lambdaFactory$(executorService, tokenListener);
            this.val$authProvider.addIdTokenListener(idTokenListener);
        }

        public static /* synthetic */ void lambda$addTokenChangeListener$3(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenListener, InternalTokenResult tokenResult) {
            executorService.execute(AndroidAuthTokenProvider$1$$Lambda$4.lambdaFactory$(tokenListener, tokenResult));
        }

        public static /* synthetic */ void lambda$addTokenChangeListener$2(AuthTokenProvider.TokenChangeListener tokenListener, InternalTokenResult tokenResult) {
            tokenListener.onTokenChange(tokenResult.getToken());
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider
        public void removeTokenChangeListener(AuthTokenProvider.TokenChangeListener tokenListener) {
        }
    }

    public static AuthTokenProvider forAuthenticatedAccess(InternalAuthProvider authProvider) {
        return new AnonymousClass1(authProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.android.AndroidAuthTokenProvider$2 */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements AuthTokenProvider {
        AnonymousClass2() {
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider
        public void getToken(boolean forceRefresh, AuthTokenProvider.GetTokenCompletionListener listener) {
            listener.onSuccess(null);
        }

        public static /* synthetic */ void lambda$addTokenChangeListener$0(AuthTokenProvider.TokenChangeListener listener) {
            listener.onTokenChange(null);
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider
        public void addTokenChangeListener(ExecutorService executorService, AuthTokenProvider.TokenChangeListener listener) {
            executorService.execute(AndroidAuthTokenProvider$2$$Lambda$1.lambdaFactory$(listener));
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider
        public void removeTokenChangeListener(AuthTokenProvider.TokenChangeListener listener) {
        }
    }

    public static AuthTokenProvider forUnauthenticatedAccess() {
        return new AnonymousClass2();
    }

    public static boolean isUnauthenticatedUsage(Exception e) {
        if ((e instanceof FirebaseApiNotAvailableException) || (e instanceof FirebaseNoSignedInUserException)) {
            return true;
        }
        return false;
    }
}
