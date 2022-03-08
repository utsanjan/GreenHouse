package com.google.android.material.textfield;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.appcompat.widget.AppCompatEditText;
import com.google.android.material.R;

/* loaded from: classes.dex */
public class TextInputEditText extends AppCompatEditText {
    public TextInputEditText(Context context) {
        this(context, null);
    }

    public TextInputEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public TextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout layout = getTextInputLayout();
        if (layout != null && layout.isProvidingHint() && super.getHint() == null && Build.MANUFACTURER.equalsIgnoreCase("Meizu")) {
            setHint("");
        }
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout layout = getTextInputLayout();
        if (layout == null || !layout.isProvidingHint()) {
            return super.getHint();
        }
        return layout.getHint();
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection ic = super.onCreateInputConnection(outAttrs);
        if (ic != null && outAttrs.hintText == null) {
            outAttrs.hintText = getHintFromLayout();
        }
        return ic;
    }

    private TextInputLayout getTextInputLayout() {
        for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    private CharSequence getHintFromLayout() {
        TextInputLayout layout = getTextInputLayout();
        if (layout != null) {
            return layout.getHint();
        }
        return null;
    }
}
