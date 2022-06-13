package com.ebig.crosso.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.Formatter;
import android.widget.NumberPicker.OnValueChangeListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ebig.crosso.R;
import com.ebig.crosso.utils.CrossoTimeUtils;

import java.util.Calendar;
import java.util.Date;

public class DateTimepicker extends FrameLayout {

    private NumberPicker mYearSpinner;
    private NumberPicker mMonthSpinner;
    private NumberPicker mDaySpinner;
    private NumberPicker mHourSpinner;
    private NumberPicker mMinuteSpinner;
    private NumberPicker mSecondSpinner;
    private Calendar mDate;
    private int mYear, mMonth, mDay, mHour, mMinute, mSecond;
    private OnDateTimeChangedListener mOnDateTimeChangedListener;
    private boolean beforday = false;
    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;

    public DateTimepicker(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.date_picker, this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SelectDayStyle);
        beforday = a.getBoolean(R.styleable.SelectDayStyle_start, true);
        a.recycle();
        initDate();
    }

    public void initDate() {
//        CrossoNumPickerUtils.setNumberPickerTextColor(mYearSpinner,getResources().getColor(R.color.colorWhite));
//        CrossoNumPickerUtils.setNumberPickerDividerColor(mYearSpinner,getResources().getColor(R.color.colorWhite));
//
//        CrossoNumPickerUtils.setNumberPickerTextColor(mMonthSpinner,getResources().getColor(R.color.colorWhite));
//        CrossoNumPickerUtils.setNumberPickerDividerColor(mMonthSpinner,getResources().getColor(R.color.colorWhite));
//
//        CrossoNumPickerUtils.setNumberPickerTextColor(mDaySpinner,getResources().getColor(R.color.colorWhite));
//        CrossoNumPickerUtils.setNumberPickerDividerColor(mDaySpinner,getResources().getColor(R.color.colorWhite));
//
//
//        CrossoNumPickerUtils.setNumberPickerTextColor(mHourSpinner,getResources().getColor(R.color.colorWhite));
//        CrossoNumPickerUtils.setNumberPickerDividerColor(mHourSpinner,getResources().getColor(R.color.colorWhite));
//
//
//        CrossoNumPickerUtils.setNumberPickerTextColor(mMinuteSpinner,getResources().getColor(R.color.colorWhite));
//        CrossoNumPickerUtils.setNumberPickerDividerColor(mMinuteSpinner,getResources().getColor(R.color.colorWhite));
//
//
//        CrossoNumPickerUtils.setNumberPickerTextColor(mSecondSpinner,getResources().getColor(R.color.colorWhite));
//        CrossoNumPickerUtils.setNumberPickerDividerColor(mSecondSpinner,getResources().getColor(R.color.colorWhite));
        /*
         *
         */
        mDate = Calendar.getInstance();
        if (beforday) {
            long newTome = System.currentTimeMillis() - ONE_DAY_IN_MS;
            Date newDate = new Date(newTome);
            mDate.setTime(newDate);
        }else {
            mDate.setTime(new Date());
        }
        mYear = mDate.get(Calendar.YEAR);
        mMonth = mDate.get(Calendar.MONTH) + 1;
        mHour = mDate.get(Calendar.HOUR_OF_DAY);
        mMinute = mDate.get(Calendar.MINUTE);
        mSecond = mDate.get(Calendar.SECOND);


        mYearSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_year);
        mYearSpinner.setMaxValue(2100);
        mYearSpinner.setMinValue(1900);
        mMonthSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_month);
        mMonthSpinner.setMaxValue(12);
        mMonthSpinner.setMinValue(1);
        mDaySpinner = (NumberPicker) this.findViewById(R.id.np_datetime_day);
        mHourSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_hour);
        mHourSpinner.setMaxValue(23);
        mHourSpinner.setMinValue(0);
        mMinuteSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_minute);
        mMinuteSpinner.setMaxValue(59);
        mMinuteSpinner.setMinValue(0);
        mSecondSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_second);
        mSecondSpinner.setMaxValue(59);
        mSecondSpinner.setMinValue(0);


        mYearSpinner.setValue(mYear);
        mYearSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);//设置NumberPicker不可编辑
        mYearSpinner.setOnValueChangedListener(mOnYearChangedListener);//注册NumberPicker值变化时的监听事件


        mMonthSpinner.setValue(mMonth);
        mMonthSpinner.setFormatter(formatter);//格式化显示数字，个位数前添加0
        mMonthSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mMonthSpinner.setOnValueChangedListener(mOnMonthChangedListener);


        judgeMonth();//判断是否闰年，从而设置2月份的天数
        mDay = mDate.get(Calendar.DAY_OF_MONTH);
        mDaySpinner.setValue(mDay);
        mDaySpinner.setFormatter(formatter);
        mDaySpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mDaySpinner.setOnValueChangedListener(mOnDayChangedListener);


        mHourSpinner.setValue(mHour);
        mHourSpinner.setFormatter(formatter);
        mHourSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);


        mMinuteSpinner.setValue(mMinute);
        mMinuteSpinner.setFormatter(formatter);
        mMinuteSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mMinuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);


        mSecondSpinner.setValue(mSecond);
        mSecondSpinner.setFormatter(formatter);
        mSecondSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mSecondSpinner.setOnValueChangedListener(mOnSecondChangedListener);
    }

    public DateTimepicker(Context context) {
        super(context);

    }

    /**
     *
     */
    private NumberPicker.OnValueChangeListener mOnYearChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mYear = mYearSpinner.getValue();
            judgeYear();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnMonthChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mMonth = mMonthSpinner.getValue();
            judgeMonth();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnDayChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mDay = mDaySpinner.getValue();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mHour = mHourSpinner.getValue();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mMinute = mMinuteSpinner.getValue();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnSecondChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mSecond = mSecondSpinner.getValue();
            onDateTimeChanged();
        }
    };
    //数字格式化，<10的数字前自动加0
    private NumberPicker.Formatter formatter = new Formatter() {
        @Override
        public String format(int value) {
            String Str = String.valueOf(value);
            if (value < 10) {
                Str = "0" + Str;
            }
            return Str;
        }
    };

    /*
     *接口回调 参数是当前的View 年月日时分秒
     */
    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimepicker view, int year, int month, int day, int hour, int minute, int second, long timeStamp);
    }

    /*
     *对外的公开方法
     */
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;
    }

    private void onDateTimeChanged() {
        if (mOnDateTimeChangedListener != null) {
            String total = mYear + "-" + (mMonth < 10 ? "0" + mMonth : "" + mMonth) + "-" + (mDay < 10 ? "0" + mDay : "" + mDay)
                    + " " + (mHour < 10 ? "0" + mHour : "" + mHour) + ":" + (mMinute < 10 ? "0" + mMinute : "" + mMinute) + ":" + (mSecond < 10 ? "0" + mSecond : "" + mSecond);
            mOnDateTimeChangedListener.onDateTimeChanged(this, mYear, mMonth, mDay, mHour, mMinute, mSecond, CrossoTimeUtils.time2string(total));
        }
    }

    private void judgeYear() {
        if (mMonth == 2) {
            if (mYear % 4 == 0) {
                if (mDaySpinner.getMaxValue() != 29) {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(29);
                }
            } else {
                if (mDaySpinner.getMaxValue() != 28) {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(28);
                }
            }
        }
        mDay = mDaySpinner.getValue();
    }

    private void judgeMonth() {
        if (mMonth == 2) {
            if (mYear % 4 == 0) {
                if (mDaySpinner.getMaxValue() != 29) {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(29);
                }
            } else {
                if (mDaySpinner.getMaxValue() != 28) {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(28);
                }
            }
        } else {
            switch (mMonth) {
                case 4:
                case 6:
                case 9:
                case 11:
                    if (mDaySpinner.getMaxValue() != 30) {
                        mDaySpinner.setDisplayedValues(null);
                        mDaySpinner.setMinValue(1);
                        mDaySpinner.setMaxValue(30);
                    }
                    break;
                default:
                    if (mDaySpinner.getMaxValue() != 31) {
                        mDaySpinner.setDisplayedValues(null);
                        mDaySpinner.setMinValue(1);
                        mDaySpinner.setMaxValue(31);
                    }
            }
        }
        mDay = mDaySpinner.getValue();

    }

}
