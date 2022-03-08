package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final String EXTRA_INT1 = "int1";
    private static final String EXTRA_INT2 = "int2";
    private static final String EXTRA_OBJ = "obj";
    private static final String EXTRA_TINT_LIST = "tint_list";
    private static final String EXTRA_TINT_MODE = "tint_mode";
    private static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_UNKNOWN = -1;
    public byte[] mData;
    public int mInt1;
    public int mInt2;
    Object mObj1;
    public Parcelable mParcelable;
    public ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    public String mTintModeStr;
    public int mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IconType {
    }

    public static IconCompat createWithResource(Context context, int resId) {
        if (context != null) {
            return createWithResource(context.getResources(), context.getPackageName(), resId);
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    public static IconCompat createWithResource(Resources r, String pkg, int resId) {
        if (pkg == null) {
            throw new IllegalArgumentException("Package must not be null.");
        } else if (resId != 0) {
            IconCompat rep = new IconCompat(2);
            rep.mInt1 = resId;
            if (r != null) {
                try {
                    rep.mObj1 = r.getResourceName(resId);
                } catch (Resources.NotFoundException e) {
                    throw new IllegalArgumentException("Icon resource cannot be found");
                }
            } else {
                rep.mObj1 = pkg;
            }
            return rep;
        } else {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
    }

    public static IconCompat createWithBitmap(Bitmap bits) {
        if (bits != null) {
            IconCompat rep = new IconCompat(1);
            rep.mObj1 = bits;
            return rep;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bits) {
        if (bits != null) {
            IconCompat rep = new IconCompat(5);
            rep.mObj1 = bits;
            return rep;
        }
        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    public static IconCompat createWithData(byte[] data, int offset, int length) {
        if (data != null) {
            IconCompat rep = new IconCompat(3);
            rep.mObj1 = data;
            rep.mInt1 = offset;
            rep.mInt2 = length;
            return rep;
        }
        throw new IllegalArgumentException("Data must not be null.");
    }

    public static IconCompat createWithContentUri(String uri) {
        if (uri != null) {
            IconCompat rep = new IconCompat(4);
            rep.mObj1 = uri;
            return rep;
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri != null) {
            return createWithContentUri(uri.toString());
        }
        throw new IllegalArgumentException("Uri must not be null.");
    }

    public IconCompat() {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
    }

    private IconCompat(int mType) {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
        this.mType = mType;
    }

    public int getType() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            return this.mType;
        }
        return getType((Icon) this.mObj1);
    }

    public String getResPackage() {
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResPackage((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return ((String) this.mObj1).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public int getResId() {
        if (this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return getResId((Icon) this.mObj1);
        }
        if (this.mType == 2) {
            return this.mInt1;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public Bitmap getBitmap() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            int i = this.mType;
            if (i == 1) {
                return (Bitmap) this.mObj1;
            }
            if (i == 5) {
                return createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
            }
            throw new IllegalStateException("called getBitmap() on " + this);
        }
        Object obj = this.mObj1;
        if (obj instanceof Bitmap) {
            return (Bitmap) obj;
        }
        return null;
    }

    public Uri getUri() {
        if (this.mType != -1 || Build.VERSION.SDK_INT < 23) {
            return Uri.parse((String) this.mObj1);
        }
        return getUri((Icon) this.mObj1);
    }

    public IconCompat setTint(int tint) {
        return setTintList(ColorStateList.valueOf(tint));
    }

    public IconCompat setTintList(ColorStateList tintList) {
        this.mTintList = tintList;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        return this;
    }

    public Icon toIcon() {
        Icon icon;
        int i = this.mType;
        if (i == -1) {
            return (Icon) this.mObj1;
        }
        if (i == 1) {
            icon = Icon.createWithBitmap((Bitmap) this.mObj1);
        } else if (i == 2) {
            icon = Icon.createWithResource(getResPackage(), this.mInt1);
        } else if (i == 3) {
            icon = Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
        } else if (i == 4) {
            icon = Icon.createWithContentUri((String) this.mObj1);
        } else if (i != 5) {
            throw new IllegalArgumentException("Unknown type");
        } else if (Build.VERSION.SDK_INT >= 26) {
            icon = Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1);
        } else {
            icon = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
        }
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            icon.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            icon.setTintMode(mode);
        }
        return icon;
    }

    public void checkResource(Context context) {
        if (this.mType == 2) {
            String resPackage = (String) this.mObj1;
            if (resPackage.contains(":")) {
                String resName = resPackage.split(":", -1)[1];
                String resType = resName.split("/", -1)[0];
                String resName2 = resName.split("/", -1)[1];
                String resPackage2 = resPackage.split(":", -1)[0];
                Resources res = getResources(context, resPackage2);
                int id = res.getIdentifier(resName2, resType, resPackage2);
                if (this.mInt1 != id) {
                    Log.i(TAG, "Id has changed for " + resPackage2 + "/" + resName2);
                    this.mInt1 = id;
                }
            }
        }
    }

    public Drawable loadDrawable(Context context) {
        checkResource(context);
        if (Build.VERSION.SDK_INT >= 23) {
            return toIcon().loadDrawable(context);
        }
        Drawable result = loadDrawableInner(context);
        if (!(result == null || (this.mTintList == null && this.mTintMode == DEFAULT_TINT_MODE))) {
            result.mutate();
            DrawableCompat.setTintList(result, this.mTintList);
            DrawableCompat.setTintMode(result, this.mTintMode);
        }
        return result;
    }

    private Drawable loadDrawableInner(Context context) {
        int i = this.mType;
        if (i == 1) {
            return new BitmapDrawable(context.getResources(), (Bitmap) this.mObj1);
        }
        if (i == 2) {
            String resPackage = getResPackage();
            if (TextUtils.isEmpty(resPackage)) {
                resPackage = context.getPackageName();
            }
            Resources res = getResources(context, resPackage);
            try {
                return ResourcesCompat.getDrawable(res, this.mInt1, context.getTheme());
            } catch (RuntimeException e) {
                Log.e(TAG, String.format("Unable to load resource 0x%08x from pkg=%s", Integer.valueOf(this.mInt1), this.mObj1), e);
                return null;
            }
        } else if (i == 3) {
            return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray((byte[]) this.mObj1, this.mInt1, this.mInt2));
        } else {
            if (i == 4) {
                Uri uri = Uri.parse((String) this.mObj1);
                String scheme = uri.getScheme();
                InputStream is = null;
                if (FirebaseAnalytics.Param.CONTENT.equals(scheme) || "file".equals(scheme)) {
                    try {
                        is = context.getContentResolver().openInputStream(uri);
                    } catch (Exception e2) {
                        Log.w(TAG, "Unable to load image from URI: " + uri, e2);
                    }
                } else {
                    try {
                        is = new FileInputStream(new File((String) this.mObj1));
                    } catch (FileNotFoundException e3) {
                        Log.w(TAG, "Unable to load image from path: " + uri, e3);
                    }
                }
                if (is != null) {
                    return new BitmapDrawable(context.getResources(), BitmapFactory.decodeStream(is));
                }
                return null;
            } else if (i != 5) {
                return null;
            } else {
                return new BitmapDrawable(context.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
            }
        }
    }

    private static Resources getResources(Context context, String resPackage) {
        if (AbstractSpiCall.ANDROID_CLIENT_TYPE.equals(resPackage)) {
            return Resources.getSystem();
        }
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(resPackage, 8192);
            if (ai != null) {
                return pm.getResourcesForApplication(ai);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, String.format("Unable to find pkg=%s for icon", resPackage), e);
            return null;
        }
    }

    public void addToShortcutIntent(Intent outIntent, Drawable badge, Context c) {
        Bitmap icon;
        Bitmap icon2;
        checkResource(c);
        int i = this.mType;
        if (i == 1) {
            icon = (Bitmap) this.mObj1;
            if (badge != null) {
                icon = icon.copy(icon.getConfig(), true);
            }
        } else if (i == 2) {
            try {
                Context context = c.createPackageContext(getResPackage(), 0);
                if (badge == null) {
                    outIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context, this.mInt1));
                    return;
                }
                Drawable dr = ContextCompat.getDrawable(context, this.mInt1);
                if (dr.getIntrinsicWidth() > 0 && dr.getIntrinsicHeight() > 0) {
                    icon2 = Bitmap.createBitmap(dr.getIntrinsicWidth(), dr.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    dr.setBounds(0, 0, icon2.getWidth(), icon2.getHeight());
                    dr.draw(new Canvas(icon2));
                    icon = icon2;
                }
                int size = ((ActivityManager) context.getSystemService("activity")).getLauncherLargeIconSize();
                icon2 = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                dr.setBounds(0, 0, icon2.getWidth(), icon2.getHeight());
                dr.draw(new Canvas(icon2));
                icon = icon2;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalArgumentException("Can't find package " + this.mObj1, e);
            }
        } else if (i == 5) {
            icon = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
        } else {
            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
        }
        if (badge != null) {
            int w = icon.getWidth();
            int h = icon.getHeight();
            badge.setBounds(w / 2, h / 2, w, h);
            badge.draw(new Canvas(icon));
        }
        outIntent.putExtra("android.intent.extra.shortcut.ICON", icon);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        bundle.putByteArray(EXTRA_OBJ, (byte[]) this.mObj1);
                    } else if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Invalid icon");
                        }
                    }
                }
                bundle.putString(EXTRA_OBJ, (String) this.mObj1);
            }
            bundle.putParcelable(EXTRA_OBJ, (Bitmap) this.mObj1);
        } else {
            bundle.putParcelable(EXTRA_OBJ, (Parcelable) this.mObj1);
        }
        bundle.putInt("type", this.mType);
        bundle.putInt(EXTRA_INT1, this.mInt1);
        bundle.putInt(EXTRA_INT2, this.mInt2);
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            bundle.putParcelable(EXTRA_TINT_LIST, colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            bundle.putString(EXTRA_TINT_MODE, mode.name());
        }
        return bundle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002c, code lost:
        if (r1 != 5) goto L_0x009c;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r4 = this;
            int r0 = r4.mType
            r1 = -1
            if (r0 != r1) goto L_0x000c
            java.lang.Object r0 = r4.mObj1
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        L_0x000c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r4.mType
            java.lang.String r1 = typeToString(r1)
            java.lang.StringBuilder r0 = r0.append(r1)
            int r1 = r4.mType
            r2 = 1
            if (r1 == r2) goto L_0x007b
            r3 = 2
            if (r1 == r3) goto L_0x0053
            r2 = 3
            if (r1 == r2) goto L_0x003a
            r2 = 4
            if (r1 == r2) goto L_0x002f
            r2 = 5
            if (r1 == r2) goto L_0x007b
            goto L_0x009c
        L_0x002f:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r4.mObj1
            r0.append(r1)
            goto L_0x009c
        L_0x003a:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r4.mInt1
            r0.append(r1)
            int r1 = r4.mInt2
            if (r1 == 0) goto L_0x009c
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r4.mInt2
            r0.append(r1)
            goto L_0x009c
        L_0x0053:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r4.getResPackage()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r2 = 0
            int r3 = r4.getResId()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "0x%08x"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.append(r1)
            goto L_0x009c
        L_0x007b:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r4.mObj1
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r4.mObj1
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
            r0.append(r1)
        L_0x009c:
            android.content.res.ColorStateList r1 = r4.mTintList
            if (r1 == 0) goto L_0x00aa
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r4.mTintList
            r0.append(r1)
        L_0x00aa:
            android.graphics.PorterDuff$Mode r1 = r4.mTintMode
            android.graphics.PorterDuff$Mode r2 = androidx.core.graphics.drawable.IconCompat.DEFAULT_TINT_MODE
            if (r1 == r2) goto L_0x00ba
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r1 = r4.mTintMode
            r0.append(r1)
        L_0x00ba:
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.toString():java.lang.String");
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPreParceling(boolean isStream) {
        this.mTintModeStr = this.mTintMode.name();
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i == 2) {
                    this.mData = ((String) this.mObj1).getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i == 3) {
                    this.mData = (byte[]) this.mObj1;
                    return;
                } else if (i == 4) {
                    this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i != 5) {
                    return;
                }
            }
            if (isStream) {
                Bitmap bitmap = (Bitmap) this.mObj1;
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, data);
                this.mData = data.toByteArray();
                return;
            }
            this.mParcelable = (Parcelable) this.mObj1;
        } else if (!isStream) {
            this.mParcelable = (Parcelable) this.mObj1;
        } else {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
    }

    @Override // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPostParceling() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.mObj1 = this.mData;
                        return;
                    } else if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                    }
                }
                this.mObj1 = new String(this.mData, Charset.forName("UTF-16"));
                return;
            }
            Parcelable parcelable = this.mParcelable;
            if (parcelable != null) {
                this.mObj1 = parcelable;
                return;
            }
            byte[] bArr = this.mData;
            this.mObj1 = bArr;
            this.mType = 3;
            this.mInt1 = 0;
            this.mInt2 = bArr.length;
            return;
        }
        Parcelable parcelable2 = this.mParcelable;
        if (parcelable2 != null) {
            this.mObj1 = parcelable2;
            return;
        }
        throw new IllegalArgumentException("Invalid icon");
    }

    private static String typeToString(int x) {
        if (x == 1) {
            return "BITMAP";
        }
        if (x == 2) {
            return "RESOURCE";
        }
        if (x == 3) {
            return "DATA";
        }
        if (x == 4) {
            return "URI";
        }
        if (x != 5) {
            return "UNKNOWN";
        }
        return "BITMAP_MASKABLE";
    }

    public static IconCompat createFromBundle(Bundle bundle) {
        int type = bundle.getInt("type");
        IconCompat icon = new IconCompat(type);
        icon.mInt1 = bundle.getInt(EXTRA_INT1);
        icon.mInt2 = bundle.getInt(EXTRA_INT2);
        if (bundle.containsKey(EXTRA_TINT_LIST)) {
            icon.mTintList = (ColorStateList) bundle.getParcelable(EXTRA_TINT_LIST);
        }
        if (bundle.containsKey(EXTRA_TINT_MODE)) {
            icon.mTintMode = PorterDuff.Mode.valueOf(bundle.getString(EXTRA_TINT_MODE));
        }
        if (!(type == -1 || type == 1)) {
            if (type != 2) {
                if (type == 3) {
                    icon.mObj1 = bundle.getByteArray(EXTRA_OBJ);
                    return icon;
                } else if (type != 4) {
                    if (type != 5) {
                        Log.w(TAG, "Unknown type " + type);
                        return null;
                    }
                }
            }
            icon.mObj1 = bundle.getString(EXTRA_OBJ);
            return icon;
        }
        icon.mObj1 = bundle.getParcelable(EXTRA_OBJ);
        return icon;
    }

    public static IconCompat createFromIcon(Context context, Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            String resPackage = getResPackage(icon);
            try {
                return createWithResource(getResources(context, resPackage), resPackage, getResId(icon));
            } catch (Resources.NotFoundException e) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else if (type == 4) {
            return createWithContentUri(getUri(icon));
        } else {
            IconCompat iconCompat = new IconCompat(-1);
            iconCompat.mObj1 = icon;
            return iconCompat;
        }
    }

    public static IconCompat createFromIcon(Icon icon) {
        Preconditions.checkNotNull(icon);
        int type = getType(icon);
        if (type == 2) {
            return createWithResource(null, getResPackage(icon), getResId(icon));
        }
        if (type == 4) {
            return createWithContentUri(getUri(icon));
        }
        IconCompat iconCompat = new IconCompat(-1);
        iconCompat.mObj1 = icon;
        return iconCompat;
    }

    private static int getType(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getType();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getType", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon type " + icon, e);
            return -1;
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "Unable to get icon type " + icon, e2);
            return -1;
        } catch (InvocationTargetException e3) {
            Log.e(TAG, "Unable to get icon type " + icon, e3);
            return -1;
        }
    }

    private static String getResPackage(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon package", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "Unable to get icon package", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e(TAG, "Unable to get icon package", e3);
            return null;
        }
    }

    private static int getResId(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon resource", e);
            return 0;
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "Unable to get icon resource", e2);
            return 0;
        } catch (InvocationTargetException e3) {
            Log.e(TAG, "Unable to get icon resource", e3);
            return 0;
        }
    }

    private static Uri getUri(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to get icon uri", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, "Unable to get icon uri", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e(TAG, "Unable to get icon uri", e3);
            return null;
        }
    }

    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap adaptiveIconBitmap, boolean addShadow) {
        int size = (int) (Math.min(adaptiveIconBitmap.getWidth(), adaptiveIconBitmap.getHeight()) * DEFAULT_VIEW_PORT_SCALE);
        Bitmap icon = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(icon);
        Paint paint = new Paint(3);
        float center = size * 0.5f;
        float radius = ICON_DIAMETER_FACTOR * center;
        if (addShadow) {
            float blur = size * BLUR_FACTOR;
            paint.setColor(0);
            paint.setShadowLayer(blur, 0.0f, size * KEY_SHADOW_OFFSET_FACTOR, 1023410176);
            canvas.drawCircle(center, center, radius, paint);
            paint.setShadowLayer(blur, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(center, center, radius, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        BitmapShader shader = new BitmapShader(adaptiveIconBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix shift = new Matrix();
        shift.setTranslate((-(adaptiveIconBitmap.getWidth() - size)) / 2, (-(adaptiveIconBitmap.getHeight() - size)) / 2);
        shader.setLocalMatrix(shift);
        paint.setShader(shader);
        canvas.drawCircle(center, center, radius, paint);
        canvas.setBitmap(null);
        return icon;
    }
}
