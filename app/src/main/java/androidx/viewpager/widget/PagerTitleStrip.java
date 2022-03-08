package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.ViewPager;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
/* loaded from: classes.dex */
public class PagerTitleStrip extends ViewGroup {
    private static final float SIDE_ALPHA = 0.6f;
    private static final int TEXT_SPACING = 16;
    TextView mCurrText;
    private int mGravity;
    private int mLastKnownCurrentPage;
    float mLastKnownPositionOffset;
    TextView mNextText;
    private int mNonPrimaryAlpha;
    private final PageListener mPageListener;
    ViewPager mPager;
    TextView mPrevText;
    private int mScaledTextSpacing;
    int mTextColor;
    private boolean mUpdatingPositions;
    private boolean mUpdatingText;
    private WeakReference<PagerAdapter> mWatchingAdapter;
    private static final int[] ATTRS = {16842804, 16842901, 16842904, 16842927};
    private static final int[] TEXT_ATTRS = {16843660};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SingleLineAllCapsTransform extends SingleLineTransformationMethod {
        private Locale mLocale;

        SingleLineAllCapsTransform(Context context) {
            this.mLocale = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence source, View view) {
            CharSequence source2 = super.getTransformation(source, view);
            if (source2 != null) {
                return source2.toString().toUpperCase(this.mLocale);
            }
            return null;
        }
    }

    private static void setSingleLineAllCaps(TextView text) {
        text.setTransformationMethod(new SingleLineAllCapsTransform(text.getContext()));
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    public PagerTitleStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLastKnownCurrentPage = -1;
        this.mLastKnownPositionOffset = -1.0f;
        this.mPageListener = new PageListener();
        TextView textView = new TextView(context);
        this.mPrevText = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.mCurrText = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.mNextText = textView3;
        addView(textView3);
        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);
        int textAppearance = a.getResourceId(0, 0);
        if (textAppearance != 0) {
            TextViewCompat.setTextAppearance(this.mPrevText, textAppearance);
            TextViewCompat.setTextAppearance(this.mCurrText, textAppearance);
            TextViewCompat.setTextAppearance(this.mNextText, textAppearance);
        }
        int textSize = a.getDimensionPixelSize(1, 0);
        if (textSize != 0) {
            setTextSize(0, textSize);
        }
        if (a.hasValue(2)) {
            int textColor = a.getColor(2, 0);
            this.mPrevText.setTextColor(textColor);
            this.mCurrText.setTextColor(textColor);
            this.mNextText.setTextColor(textColor);
        }
        this.mGravity = a.getInteger(3, 80);
        a.recycle();
        this.mTextColor = this.mCurrText.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(SIDE_ALPHA);
        this.mPrevText.setEllipsize(TextUtils.TruncateAt.END);
        this.mCurrText.setEllipsize(TextUtils.TruncateAt.END);
        this.mNextText.setEllipsize(TextUtils.TruncateAt.END);
        boolean allCaps = false;
        if (textAppearance != 0) {
            TypedArray ta = context.obtainStyledAttributes(textAppearance, TEXT_ATTRS);
            allCaps = ta.getBoolean(0, false);
            ta.recycle();
        }
        if (allCaps) {
            setSingleLineAllCaps(this.mPrevText);
            setSingleLineAllCaps(this.mCurrText);
            setSingleLineAllCaps(this.mNextText);
        } else {
            this.mPrevText.setSingleLine();
            this.mCurrText.setSingleLine();
            this.mNextText.setSingleLine();
        }
        float density = context.getResources().getDisplayMetrics().density;
        this.mScaledTextSpacing = (int) (16.0f * density);
    }

    public void setTextSpacing(int spacingPixels) {
        this.mScaledTextSpacing = spacingPixels;
        requestLayout();
    }

    public int getTextSpacing() {
        return this.mScaledTextSpacing;
    }

    public void setNonPrimaryAlpha(float alpha) {
        int i = ((int) (255.0f * alpha)) & 255;
        this.mNonPrimaryAlpha = i;
        int transparentColor = (i << 24) | (this.mTextColor & ViewCompat.MEASURED_SIZE_MASK);
        this.mPrevText.setTextColor(transparentColor);
        this.mNextText.setTextColor(transparentColor);
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
        this.mCurrText.setTextColor(color);
        int transparentColor = (this.mNonPrimaryAlpha << 24) | (this.mTextColor & ViewCompat.MEASURED_SIZE_MASK);
        this.mPrevText.setTextColor(transparentColor);
        this.mNextText.setTextColor(transparentColor);
    }

    public void setTextSize(int unit, float size) {
        this.mPrevText.setTextSize(unit, size);
        this.mCurrText.setTextSize(unit, size);
        this.mNextText.setTextSize(unit, size);
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof ViewPager) {
            ViewPager pager = (ViewPager) parent;
            PagerAdapter adapter = pager.getAdapter();
            pager.setInternalPageChangeListener(this.mPageListener);
            pager.addOnAdapterChangeListener(this.mPageListener);
            this.mPager = pager;
            WeakReference<PagerAdapter> weakReference = this.mWatchingAdapter;
            updateAdapter(weakReference != null ? weakReference.get() : null, adapter);
            return;
        }
        throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.mPager;
        if (viewPager != null) {
            updateAdapter(viewPager.getAdapter(), null);
            this.mPager.setInternalPageChangeListener(null);
            this.mPager.removeOnAdapterChangeListener(this.mPageListener);
            this.mPager = null;
        }
    }

    void updateText(int currentItem, PagerAdapter adapter) {
        int itemCount = adapter != null ? adapter.getCount() : 0;
        this.mUpdatingText = true;
        CharSequence text = null;
        if (currentItem >= 1 && adapter != null) {
            text = adapter.getPageTitle(currentItem - 1);
        }
        this.mPrevText.setText(text);
        this.mCurrText.setText((adapter == null || currentItem >= itemCount) ? null : adapter.getPageTitle(currentItem));
        CharSequence text2 = null;
        if (currentItem + 1 < itemCount && adapter != null) {
            text2 = adapter.getPageTitle(currentItem + 1);
        }
        this.mNextText.setText(text2);
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int maxWidth = Math.max(0, (int) (width * 0.8f));
        int childWidthSpec = View.MeasureSpec.makeMeasureSpec(maxWidth, Integer.MIN_VALUE);
        int childHeight = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int maxHeight = Math.max(0, childHeight);
        int childHeightSpec = View.MeasureSpec.makeMeasureSpec(maxHeight, Integer.MIN_VALUE);
        this.mPrevText.measure(childWidthSpec, childHeightSpec);
        this.mCurrText.measure(childWidthSpec, childHeightSpec);
        this.mNextText.measure(childWidthSpec, childHeightSpec);
        this.mLastKnownCurrentPage = currentItem;
        if (!this.mUpdatingPositions) {
            updateTextPositions(currentItem, this.mLastKnownPositionOffset, false);
        }
        this.mUpdatingText = false;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (!this.mUpdatingText) {
            super.requestLayout();
        }
    }

    void updateAdapter(PagerAdapter oldAdapter, PagerAdapter newAdapter) {
        if (oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = null;
        }
        if (newAdapter != null) {
            newAdapter.registerDataSetObserver(this.mPageListener);
            this.mWatchingAdapter = new WeakReference<>(newAdapter);
        }
        ViewPager viewPager = this.mPager;
        if (viewPager != null) {
            this.mLastKnownCurrentPage = -1;
            this.mLastKnownPositionOffset = -1.0f;
            updateText(viewPager.getCurrentItem(), newAdapter);
            requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateTextPositions(int position, float positionOffset, boolean force) {
        int currTop;
        int prevTop;
        int vgrav;
        if (position != this.mLastKnownCurrentPage) {
            updateText(position, this.mPager.getAdapter());
        } else if (!force && positionOffset == this.mLastKnownPositionOffset) {
            return;
        }
        this.mUpdatingPositions = true;
        int prevWidth = this.mPrevText.getMeasuredWidth();
        int currWidth = this.mCurrText.getMeasuredWidth();
        int nextWidth = this.mNextText.getMeasuredWidth();
        int halfCurrWidth = currWidth / 2;
        int stripWidth = getWidth();
        int stripHeight = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int textPaddedLeft = paddingLeft + halfCurrWidth;
        int textPaddedRight = paddingRight + halfCurrWidth;
        int contentWidth = (stripWidth - textPaddedLeft) - textPaddedRight;
        float currOffset = positionOffset + 0.5f;
        if (currOffset > 1.0f) {
            currOffset -= 1.0f;
        }
        int currCenter = (stripWidth - textPaddedRight) - ((int) (contentWidth * currOffset));
        int currLeft = currCenter - (currWidth / 2);
        int halfCurrWidth2 = currLeft + currWidth;
        int prevBaseline = this.mPrevText.getBaseline();
        int currBaseline = this.mCurrText.getBaseline();
        int nextBaseline = this.mNextText.getBaseline();
        int contentWidth2 = Math.max(prevBaseline, currBaseline);
        int maxBaseline = Math.max(contentWidth2, nextBaseline);
        int prevTopOffset = maxBaseline - prevBaseline;
        int currTopOffset = maxBaseline - currBaseline;
        int nextTopOffset = maxBaseline - nextBaseline;
        int alignedPrevHeight = prevTopOffset + this.mPrevText.getMeasuredHeight();
        int alignedCurrHeight = currTopOffset + this.mCurrText.getMeasuredHeight();
        int alignedNextHeight = nextTopOffset + this.mNextText.getMeasuredHeight();
        int maxTextHeight = Math.max(Math.max(alignedPrevHeight, alignedCurrHeight), alignedNextHeight);
        int alignedPrevHeight2 = this.mGravity;
        int vgrav2 = alignedPrevHeight2 & 112;
        if (vgrav2 == 16) {
            int paddedHeight = (stripHeight - paddingTop) - paddingBottom;
            int centeredTop = (paddedHeight - maxTextHeight) / 2;
            prevTop = centeredTop + prevTopOffset;
            int nextTop = centeredTop + nextTopOffset;
            currTop = centeredTop + currTopOffset;
            vgrav = nextTop;
        } else if (vgrav2 != 80) {
            prevTop = paddingTop + prevTopOffset;
            int nextTop2 = paddingTop + nextTopOffset;
            currTop = paddingTop + currTopOffset;
            vgrav = nextTop2;
        } else {
            int bottomGravTop = (stripHeight - paddingBottom) - maxTextHeight;
            prevTop = bottomGravTop + prevTopOffset;
            int nextTop3 = bottomGravTop + nextTopOffset;
            currTop = bottomGravTop + currTopOffset;
            vgrav = nextTop3;
        }
        TextView textView = this.mCurrText;
        textView.layout(currLeft, currTop, halfCurrWidth2, currTop + textView.getMeasuredHeight());
        int prevLeft = Math.min(paddingLeft, (currLeft - this.mScaledTextSpacing) - prevWidth);
        TextView textView2 = this.mPrevText;
        textView2.layout(prevLeft, prevTop, prevLeft + prevWidth, prevTop + textView2.getMeasuredHeight());
        int nextLeft = Math.max((stripWidth - paddingRight) - nextWidth, this.mScaledTextSpacing + halfCurrWidth2);
        TextView textView3 = this.mNextText;
        textView3.layout(nextLeft, vgrav, nextLeft + nextWidth, vgrav + textView3.getMeasuredHeight());
        this.mLastKnownPositionOffset = positionOffset;
        this.mUpdatingPositions = false;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int textHeight;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == 1073741824) {
            int heightPadding = getPaddingTop() + getPaddingBottom();
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, heightPadding, -2);
            int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
            int widthPadding = (int) (widthSize * 0.2f);
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, widthPadding, -2);
            this.mPrevText.measure(childWidthSpec, childHeightSpec);
            this.mCurrText.measure(childWidthSpec, childHeightSpec);
            this.mNextText.measure(childWidthSpec, childHeightSpec);
            int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
            if (heightMode == 1073741824) {
                textHeight = View.MeasureSpec.getSize(heightMeasureSpec);
            } else {
                int textHeight2 = this.mCurrText.getMeasuredHeight();
                int minHeight = getMinHeight();
                textHeight = Math.max(minHeight, textHeight2 + heightPadding);
            }
            int childState = this.mCurrText.getMeasuredState();
            int measuredHeight = View.resolveSizeAndState(textHeight, heightMeasureSpec, childState << 16);
            setMeasuredDimension(widthSize, measuredHeight);
            return;
        }
        throw new IllegalStateException("Must measure with an exact width");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (this.mPager != null) {
            float offset = this.mLastKnownPositionOffset;
            if (offset < 0.0f) {
                offset = 0.0f;
            }
            updateTextPositions(this.mLastKnownCurrentPage, offset, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinHeight() {
        Drawable bg = getBackground();
        if (bg == null) {
            return 0;
        }
        int minHeight = bg.getIntrinsicHeight();
        return minHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PageListener extends DataSetObserver implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
        private int mScrollState;

        PageListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset > 0.5f) {
                position++;
            }
            PagerTitleStrip.this.updateTextPositions(position, positionOffset, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            if (this.mScrollState == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.updateText(pagerTitleStrip.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
                float offset = 0.0f;
                if (PagerTitleStrip.this.mLastKnownPositionOffset >= 0.0f) {
                    offset = PagerTitleStrip.this.mLastKnownPositionOffset;
                }
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                pagerTitleStrip2.updateTextPositions(pagerTitleStrip2.mPager.getCurrentItem(), offset, true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
            this.mScrollState = state;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter oldAdapter, PagerAdapter newAdapter) {
            PagerTitleStrip.this.updateAdapter(oldAdapter, newAdapter);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.updateText(pagerTitleStrip.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
            float offset = 0.0f;
            if (PagerTitleStrip.this.mLastKnownPositionOffset >= 0.0f) {
                offset = PagerTitleStrip.this.mLastKnownPositionOffset;
            }
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            pagerTitleStrip2.updateTextPositions(pagerTitleStrip2.mPager.getCurrentItem(), offset, true);
        }
    }
}
