package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.google.android.material.R;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* loaded from: classes.dex */
abstract class DateFormatTextWatcher implements TextWatcher {
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String formatHint;
    private final String outOfRange;
    private final TextInputLayout textInputLayout;

    abstract void onValidDate(Long l);

    /* JADX INFO: Access modifiers changed from: package-private */
    public DateFormatTextWatcher(String formatHint, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints constraints) {
        this.formatHint = formatHint;
        this.dateFormat = dateFormat;
        this.textInputLayout = textInputLayout;
        this.constraints = constraints;
        this.outOfRange = textInputLayout.getContext().getString(R.string.mtrl_picker_out_of_range);
    }

    void onInvalidDate() {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            this.textInputLayout.setError(null);
            onValidDate(null);
            return;
        }
        try {
            Date date = this.dateFormat.parse(s.toString());
            this.textInputLayout.setError(null);
            long milliseconds = date.getTime();
            if (!this.constraints.getDateValidator().isValid(milliseconds) || !this.constraints.isWithinBounds(milliseconds)) {
                this.textInputLayout.setError(String.format(this.outOfRange, DateStrings.getDateString(milliseconds)));
                onInvalidDate();
            } else {
                onValidDate(Long.valueOf(date.getTime()));
            }
        } catch (ParseException e) {
            String invalidFormat = this.textInputLayout.getContext().getString(R.string.mtrl_picker_invalid_format);
            String useLine = String.format(this.textInputLayout.getContext().getString(R.string.mtrl_picker_invalid_format_use), this.formatHint);
            String exampleLine = String.format(this.textInputLayout.getContext().getString(R.string.mtrl_picker_invalid_format_example), this.dateFormat.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis())));
            TextInputLayout textInputLayout = this.textInputLayout;
            textInputLayout.setError(invalidFormat + "\n" + useLine + "\n" + exampleLine);
            onInvalidDate();
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
    }
}
