package pl.droidsonroids.gif;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class GifViewSavedState extends View.BaseSavedState {
    public static final Parcelable.Creator<GifViewSavedState> CREATOR = new Parcelable.Creator<GifViewSavedState>() { // from class: pl.droidsonroids.gif.GifViewSavedState.1
        @Override // android.os.Parcelable.Creator
        public GifViewSavedState createFromParcel(Parcel in) {
            return new GifViewSavedState(in);
        }

        @Override // android.os.Parcelable.Creator
        public GifViewSavedState[] newArray(int size) {
            return new GifViewSavedState[size];
        }
    };
    final long[][] mStates;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifViewSavedState(Parcelable superState, Drawable... drawables) {
        super(superState);
        this.mStates = new long[drawables.length];
        for (int i = 0; i < drawables.length; i++) {
            Drawable drawable = drawables[i];
            if (drawable instanceof GifDrawable) {
                this.mStates[i] = ((GifDrawable) drawable).mNativeInfoHandle.getSavedState();
            } else {
                this.mStates[i] = null;
            }
        }
    }

    private GifViewSavedState(Parcel in) {
        super(in);
        this.mStates = new long[in.readInt()];
        int i = 0;
        while (true) {
            long[][] jArr = this.mStates;
            if (i < jArr.length) {
                jArr[i] = in.createLongArray();
                i++;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifViewSavedState(Parcelable superState, long[] savedState) {
        super(superState);
        this.mStates = r0;
        long[][] jArr = {savedState};
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        long[][] jArr;
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mStates.length);
        for (long[] mState : this.mStates) {
            dest.writeLongArray(mState);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restoreState(Drawable drawable, int i) {
        if (this.mStates[i] != null && (drawable instanceof GifDrawable)) {
            GifDrawable gifDrawable = (GifDrawable) drawable;
            gifDrawable.startAnimation(gifDrawable.mNativeInfoHandle.restoreSavedState(this.mStates[i], gifDrawable.mBuffer));
        }
    }
}
