package pl.droidsonroids.gif;

/* loaded from: classes.dex */
public class GifOptions {
    boolean inIsOpaque;
    char inSampleSize;

    public GifOptions() {
        reset();
    }

    private void reset() {
        this.inSampleSize = (char) 1;
        this.inIsOpaque = false;
    }

    public void setInSampleSize(int inSampleSize) {
        if (inSampleSize < 1 || inSampleSize > 65535) {
            this.inSampleSize = (char) 1;
        } else {
            this.inSampleSize = (char) inSampleSize;
        }
    }

    public void setInIsOpaque(boolean inIsOpaque) {
        this.inIsOpaque = inIsOpaque;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFrom(GifOptions source) {
        if (source == null) {
            reset();
            return;
        }
        this.inIsOpaque = source.inIsOpaque;
        this.inSampleSize = source.inSampleSize;
    }
}
