package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MonthAdapter extends BaseAdapter {
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final Month month;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MonthAdapter(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.month = month;
        this.dateSelector = dateSelector;
        this.calendarConstraints = calendarConstraints;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.Adapter
    public Long getItem(int position) {
        if (position < this.month.daysFromStartOfWeekToFirstOfMonth() || position > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(position)));
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position / this.month.daysInWeek;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.month.daysInMonth + firstPositionInMonth();
    }

    @Override // android.widget.Adapter
    public TextView getView(int position, View convertView, ViewGroup parent) {
        initializeStyles(parent.getContext());
        TextView day = (TextView) convertView;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            day = (TextView) layoutInflater.inflate(R.layout.mtrl_calendar_day, parent, false);
        }
        int offsetPosition = position - firstPositionInMonth();
        if (offsetPosition < 0 || offsetPosition >= this.month.daysInMonth) {
            day.setVisibility(8);
            day.setEnabled(false);
        } else {
            int dayNumber = offsetPosition + 1;
            day.setTag(this.month);
            day.setText(String.valueOf(dayNumber));
            long dayInMillis = this.month.getDay(dayNumber);
            if (this.month.year == Month.today().year) {
                day.setContentDescription(DateStrings.getMonthDayOfWeekDay(dayInMillis));
            } else {
                day.setContentDescription(DateStrings.getYearMonthDayOfWeekDay(dayInMillis));
            }
            day.setVisibility(0);
            day.setEnabled(true);
        }
        Long date = getItem(position);
        if (date == null) {
            return day;
        }
        if (this.calendarConstraints.getDateValidator().isValid(date.longValue())) {
            day.setEnabled(true);
            for (Long l : this.dateSelector.getSelectedDays()) {
                long selectedDay = l.longValue();
                if (UtcDates.canonicalYearMonthDay(date.longValue()) == UtcDates.canonicalYearMonthDay(selectedDay)) {
                    this.calendarStyle.selectedDay.styleItem(day);
                    return day;
                }
            }
            if (UtcDates.getTodayCalendar().getTimeInMillis() == date.longValue()) {
                this.calendarStyle.todayDay.styleItem(day);
                return day;
            }
            this.calendarStyle.day.styleItem(day);
            return day;
        }
        day.setEnabled(false);
        this.calendarStyle.invalidDay.styleItem(day);
        return day;
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int lastPositionInMonth() {
        return (this.month.daysFromStartOfWeekToFirstOfMonth() + this.month.daysInMonth) - 1;
    }

    int positionToDay(int position) {
        return (position - this.month.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int dayToPosition(int day) {
        int offsetFromFirst = day - 1;
        return firstPositionInMonth() + offsetFromFirst;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean withinMonth(int position) {
        return position >= firstPositionInMonth() && position <= lastPositionInMonth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFirstInRow(int position) {
        return position % this.month.daysInWeek == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLastInRow(int position) {
        return (position + 1) % this.month.daysInWeek == 0;
    }
}
