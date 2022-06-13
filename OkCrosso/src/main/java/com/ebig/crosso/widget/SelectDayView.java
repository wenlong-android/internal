package com.ebig.crosso.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ebig.crosso.R;
import com.ebig.log.ELog;

public class SelectDayView extends LinearLayout implements View.OnClickListener {
    private DateTimepicker startPicker, endPicker;
    private Button cancle, ok;
    private long startTime, endTime;
    private Context context;

    public SelectDayView(Context context) {
        super(context);
    }

    public SelectDayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
         LayoutInflater.from(context).inflate(R.layout.view_select_day, this, true);
        initView();
        initListenner();
    }

    public void setTime(long start, long end) {
        startTime = start;
        endTime = end;
//        startPicker.initDate(startTime);
//        endPicker.initDate(endTime);
    }

    private void initView() {

        startPicker = (DateTimepicker) findViewById(R.id.startPicker);
        endPicker = (DateTimepicker) findViewById(R.id.endPicker);
        cancle = (Button) findViewById(R.id.cancle);
        ok = (Button) findViewById(R.id.ok);
        cancle.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    private void initListenner() {
        startPicker.setOnDateTimeChangedListener(new DateTimepicker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(DateTimepicker view, int year, int month, int day, int hour,
                                          int minute, int second, long timeStamp) {
                startTime = timeStamp;
                if (changerListenner != null) {
                    changerListenner.onStartChange(timeStamp);
                }
            }
        });
        endPicker.setOnDateTimeChangedListener(new DateTimepicker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(DateTimepicker view, int year, int month, int day, int hour, int minute, int second, long timeStamp) {
                endTime = timeStamp;
                if (changerListenner != null) {
                    changerListenner.onEndChange(timeStamp);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancle) {
            this.setVisibility(GONE);
        } else if (v.getId() == R.id.ok) {
            if (startTime >= endTime) {
                Toast.makeText(getContext(), "开始时间不能大于结束时间", Toast.LENGTH_LONG).show();
            } else {
                this.setVisibility(GONE);
            }
        }
    }

    private OnChangerListenner changerListenner;

    public interface OnChangerListenner {
        void onStartChange(long time);

        void onEndChange(long time);
    }

    public void setChangerListenner(OnChangerListenner changerListenner) {
        this.changerListenner = changerListenner;
    }
}
