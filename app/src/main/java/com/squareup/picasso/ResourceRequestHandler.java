package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ResourceRequestHandler extends RequestHandler {
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResourceRequestHandler(Context context) {
        this.context = context;
    }

    @Override // com.squareup.picasso.RequestHandler
    public boolean canHandleRequest(Request data) {
        if (data.resourceId != 0) {
            return true;
        }
        return "android.resource".equals(data.uri.getScheme());
    }

    @Override // com.squareup.picasso.RequestHandler
    public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
        Resources res = Utils.getResources(this.context, request);
        int id = Utils.getResourceId(res, request);
        return new RequestHandler.Result(decodeResource(res, id, request), Picasso.LoadedFrom.DISK);
    }

    private static Bitmap decodeResource(Resources resources, int id, Request data) {
        BitmapFactory.Options options = createBitmapOptions(data);
        if (requiresInSampleSize(options)) {
            BitmapFactory.decodeResource(resources, id, options);
            calculateInSampleSize(data.targetWidth, data.targetHeight, options, data);
        }
        return BitmapFactory.decodeResource(resources, id, options);
    }
}
