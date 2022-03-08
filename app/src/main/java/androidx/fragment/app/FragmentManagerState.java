package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new Parcelable.Creator<FragmentManagerState>() { // from class: androidx.fragment.app.FragmentManagerState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState createFromParcel(Parcel in) {
            return new FragmentManagerState(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState[] newArray(int size) {
            return new FragmentManagerState[size];
        }
    };
    ArrayList<FragmentState> mActive;
    ArrayList<String> mAdded;
    BackStackState[] mBackStack;
    int mNextFragmentIndex;
    String mPrimaryNavActiveWho;

    public FragmentManagerState() {
        this.mPrimaryNavActiveWho = null;
    }

    public FragmentManagerState(Parcel in) {
        this.mPrimaryNavActiveWho = null;
        this.mActive = in.createTypedArrayList(FragmentState.CREATOR);
        this.mAdded = in.createStringArrayList();
        this.mBackStack = (BackStackState[]) in.createTypedArray(BackStackState.CREATOR);
        this.mPrimaryNavActiveWho = in.readString();
        this.mNextFragmentIndex = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mActive);
        dest.writeStringList(this.mAdded);
        dest.writeTypedArray(this.mBackStack, flags);
        dest.writeString(this.mPrimaryNavActiveWho);
        dest.writeInt(this.mNextFragmentIndex);
    }
}
