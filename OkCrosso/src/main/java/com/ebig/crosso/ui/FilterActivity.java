package com.ebig.crosso.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.ebig.crosso.R;
import com.ebig.crosso.manager.type.RecordType;
import com.ebig.log.ELog;
import com.ebig.crosso.utils.CroStrUtils;
import com.ebig.crosso.utils.CrossoTimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvStart, tvEnd;
    private HashMap<Integer, String> cbList;
    private long start = 0;
    private long end = 0;
    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        setContentView(R.layout.dialog_filter);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = (int) (CroAppUtils.getScreenWidth() * 0.8);
//        lp.height = (int) (CroAppUtils.getScreenHight() * 0.5);
//        window.setAttributes(lp);
        initView();
    }

    @Override
    public void onClick(View v) {
//        for (int key : cbList.keySet()) {
//            if (key == v.getId()) {
//                defualtType = cbList.get(key);
//            }
//            ((AppCompatCheckBox) findViewById(key)).setChecked(false);
//        }
//        ((AppCompatCheckBox) v).setChecked(true);

        if (v.getId() == R.id.tvStart) {

        } else if (v.getId() == R.id.tvEnd) {

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView() {
        tvStart = (TextView) findViewById(R.id.tvStart);
        tvEnd = (TextView) findViewById(R.id.tvEnd);
        tvStart.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
        cbList = new HashMap<>();
        //所有
//        AppCompatCheckBox cb_all = (AppCompatCheckBox) findViewById(R.id.cb_all);
//        cbList.put(cb_all.getId(), RecordType.all);
        AppCompatCheckBox cb_click = (AppCompatCheckBox) findViewById(R.id.cb_click);
        cbList.put(cb_click.getId(), RecordType.aopUserClick);
        AppCompatCheckBox cb_hardware = (AppCompatCheckBox) findViewById(R.id.cb_hardware);
        cbList.put(cb_hardware.getId(), RecordType.aopHardWare);
        AppCompatCheckBox cb_api = (AppCompatCheckBox) findViewById(R.id.cb_api);
        cbList.put(cb_api.getId(), RecordType.aopServer);
        AppCompatCheckBox cb_crash = (AppCompatCheckBox) findViewById(R.id.cb_crash);
        cbList.put(cb_crash.getId(), RecordType.aopCrash);
        AppCompatCheckBox cb_comsue = (AppCompatCheckBox) findViewById(R.id.cb_comsue);
        cbList.put(cb_comsue.getId(), RecordType.aopConsume);
        AppCompatCheckBox cbException = (AppCompatCheckBox) findViewById(R.id.cbException);
        cbList.put(cbException.getId(), RecordType.exception);
        AppCompatCheckBox cb_ram = (AppCompatCheckBox) findViewById(R.id.cb_ram);
        cbList.put(cb_ram.getId(), RecordType.ram);
        AppCompatCheckBox cb_stack = (AppCompatCheckBox) findViewById(R.id.cb_stack);
        cbList.put(cb_stack.getId(), RecordType.stack);
        AppCompatCheckBox cbLeak = (AppCompatCheckBox) findViewById(R.id.cbLeak);
        cbList.put(cbLeak.getId(), RecordType.leak);
        AppCompatCheckBox cb_block = (AppCompatCheckBox) findViewById(R.id.cb_block);
        cbList.put(cb_block.getId(), RecordType.block);
        for (int key : cbList.keySet()) {
            ((AppCompatCheckBox) findViewById(key)).setOnClickListener(this);
        }

        start = CrossoTimeUtils.time2string(CrossoTimeUtils.getToday() + " 00:00:00");
        end = start + ONE_DAY_IN_MS;
        tvStart.setText(CrossoTimeUtils.getDateFormat(start));
        tvEnd.setText(CrossoTimeUtils.getDateFormat(end));
        initListenner();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initListenner() {
        findViewById(R.id.tvCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tvOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                for (int key : cbList.keySet()) {
                    if (((AppCompatCheckBox) findViewById(key)).isChecked()) {
                        list.add(cbList.get(key));
                        ELog.print("开启了：" + CroStrUtils.getType(cbList.get(key)));
                    }
                }
                if (list.size() == 0) {
                    Toast.makeText(FilterActivity.this, "信息类型不可为空", Toast.LENGTH_LONG).show();
                } else if (start >= end) {
                    Toast.makeText(FilterActivity.this, "开始时间不能大于结束时间", Toast.LENGTH_LONG).show();
                } else {
                    FliterResult.l().post(start, end, list);
                    finish();
                }

            }
        });

    }

//    public void setData(String date, @RecordType String type) {
//        this.defualtDate = date;
//        this.defualtType = type;
//        String[] time = date.split("-");
//        datepicker.init(Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2]), null);
//        for (int key : cbList.keySet()) {
//            if (cbList.get(key).equals(type)) {
//                ((AppCompatCheckBox) findViewById(key)).setChecked(true);
//            } else {
//                ((AppCompatCheckBox) findViewById(key)).setChecked(false);
//            }
//        }
//        datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                ELog.print("onDateChanged:" + year + "-" + monthOfYear + "-" + dayOfMonth);
//
//                defualtDate = year + "-"
//                        + (monthOfYear < 10 ? ("0" + monthOfYear) : (monthOfYear + "")) + "-"
//                        + (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth + ""));
//            }
//        });
//    }


}
