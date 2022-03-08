package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ContentStreamRequestHandler extends RequestHandler {
    final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentStreamRequestHandler(Context context) {
        this.context = context;
    }

    @Override // com.squareup.picasso.RequestHandler
    public boolean canHandleRequest(Request data) {
        return FirebaseAnalytics.Param.CONTENT.equals(data.uri.getScheme());
    }

    @Override // com.squareup.picasso.RequestHandler
    public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
        return new RequestHandler.Result(getInputStream(request), Picasso.LoadedFrom.DISK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputStream getInputStream(Request request) throws FileNotFoundException {
        ContentResolver contentResolver = this.context.getContentResolver();
        return contentResolver.openInputStream(request.uri);
    }
}
