package com.squareup.picasso;

import android.graphics.Bitmap;
import com.squareup.picasso.Picasso;

/* loaded from: classes.dex */
class GetAction extends Action<Void> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public GetAction(Picasso picasso, Request data, int memoryPolicy, int networkPolicy, Object tag, String key) {
        super(picasso, null, data, memoryPolicy, networkPolicy, 0, null, key, tag, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.squareup.picasso.Action
    public void complete(Bitmap result, Picasso.LoadedFrom from) {
    }

    @Override // com.squareup.picasso.Action
    public void error() {
    }
}
