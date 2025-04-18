package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new zzb();
    private IBinder zzdl;

    public BinderWrapper() {
        this.zzdl = null;
    }

    public BinderWrapper(IBinder iBinder) {
        this.zzdl = null;
        this.zzdl = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzdl = null;
        this.zzdl = parcel.readStrongBinder();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzdl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ BinderWrapper(Parcel parcel, zzb zzbVar) {
        this(parcel);
    }
}
