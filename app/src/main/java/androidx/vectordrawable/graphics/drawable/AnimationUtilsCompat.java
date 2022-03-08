package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context context, int id) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, id);
        }
        XmlResourceParser parser = null;
        try {
            try {
                if (id == 17563663) {
                    return new FastOutLinearInInterpolator();
                }
                if (id == 17563661) {
                    FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
                    if (0 != 0) {
                        parser.close();
                    }
                    return fastOutSlowInInterpolator;
                } else if (id == 17563662) {
                    LinearOutSlowInInterpolator linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();
                    if (0 != 0) {
                        parser.close();
                    }
                    return linearOutSlowInInterpolator;
                } else {
                    XmlResourceParser parser2 = context.getResources().getAnimation(id);
                    Interpolator createInterpolatorFromXml = createInterpolatorFromXml(context, context.getResources(), context.getTheme(), parser2);
                    if (parser2 != null) {
                        parser2.close();
                    }
                    return createInterpolatorFromXml;
                }
            } catch (IOException ex) {
                Resources.NotFoundException rnf = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
                rnf.initCause(ex);
                throw rnf;
            } catch (XmlPullParserException ex2) {
                Resources.NotFoundException rnf2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
                rnf2.initCause(ex2);
                throw rnf2;
            }
        } finally {
            if (0 != 0) {
                parser.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d7, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.animation.Interpolator createInterpolatorFromXml(android.content.Context r8, android.content.res.Resources r9, android.content.res.Resources.Theme r10, org.xmlpull.v1.XmlPullParser r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r0 = 0
            int r1 = r11.getDepth()
        L_0x0005:
            int r2 = r11.next()
            r3 = r2
            r4 = 3
            if (r2 != r4) goto L_0x0013
            int r2 = r11.getDepth()
            if (r2 <= r1) goto L_0x00d7
        L_0x0013:
            r2 = 1
            if (r3 == r2) goto L_0x00d7
            r2 = 2
            if (r3 == r2) goto L_0x001a
            goto L_0x0005
        L_0x001a:
            android.util.AttributeSet r2 = android.util.Xml.asAttributeSet(r11)
            java.lang.String r4 = r11.getName()
            java.lang.String r5 = "linearInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0032
            android.view.animation.LinearInterpolator r5 = new android.view.animation.LinearInterpolator
            r5.<init>()
            r0 = r5
            goto L_0x00ba
        L_0x0032:
            java.lang.String r5 = "accelerateInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0042
            android.view.animation.AccelerateInterpolator r5 = new android.view.animation.AccelerateInterpolator
            r5.<init>(r8, r2)
            r0 = r5
            goto L_0x00ba
        L_0x0042:
            java.lang.String r5 = "decelerateInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0052
            android.view.animation.DecelerateInterpolator r5 = new android.view.animation.DecelerateInterpolator
            r5.<init>(r8, r2)
            r0 = r5
            goto L_0x00ba
        L_0x0052:
            java.lang.String r5 = "accelerateDecelerateInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0061
            android.view.animation.AccelerateDecelerateInterpolator r5 = new android.view.animation.AccelerateDecelerateInterpolator
            r5.<init>()
            r0 = r5
            goto L_0x00ba
        L_0x0061:
            java.lang.String r5 = "cycleInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0070
            android.view.animation.CycleInterpolator r5 = new android.view.animation.CycleInterpolator
            r5.<init>(r8, r2)
            r0 = r5
            goto L_0x00ba
        L_0x0070:
            java.lang.String r5 = "anticipateInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x007f
            android.view.animation.AnticipateInterpolator r5 = new android.view.animation.AnticipateInterpolator
            r5.<init>(r8, r2)
            r0 = r5
            goto L_0x00ba
        L_0x007f:
            java.lang.String r5 = "overshootInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x008e
            android.view.animation.OvershootInterpolator r5 = new android.view.animation.OvershootInterpolator
            r5.<init>(r8, r2)
            r0 = r5
            goto L_0x00ba
        L_0x008e:
            java.lang.String r5 = "anticipateOvershootInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x009d
            android.view.animation.AnticipateOvershootInterpolator r5 = new android.view.animation.AnticipateOvershootInterpolator
            r5.<init>(r8, r2)
            r0 = r5
            goto L_0x00ba
        L_0x009d:
            java.lang.String r5 = "bounceInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00ac
            android.view.animation.BounceInterpolator r5 = new android.view.animation.BounceInterpolator
            r5.<init>()
            r0 = r5
            goto L_0x00ba
        L_0x00ac:
            java.lang.String r5 = "pathInterpolator"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bc
            androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat r5 = new androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat
            r5.<init>(r8, r2, r11)
            r0 = r5
        L_0x00ba:
            goto L_0x0005
        L_0x00bc:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unknown interpolator name: "
            r6.append(r7)
            java.lang.String r7 = r11.getName()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x00d7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat.createInterpolatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser):android.view.animation.Interpolator");
    }

    private AnimationUtilsCompat() {
    }
}
