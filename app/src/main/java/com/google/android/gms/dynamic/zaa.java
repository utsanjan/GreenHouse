package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
final class zaa implements OnDelegateCreatedListener<T> {
    private final /* synthetic */ DeferredLifecycleHelper zart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zart = deferredLifecycleHelper;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.zart.zaru = lifecycleDelegate;
        linkedList = this.zart.zarw;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            lifecycleDelegate2 = this.zart.zaru;
            ((DeferredLifecycleHelper.zaa) it.next()).zaa(lifecycleDelegate2);
        }
        linkedList2 = this.zart.zarw;
        linkedList2.clear();
        this.zart.zarv = null;
    }
}
