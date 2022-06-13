package com.ebig.crosso.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;


import com.ebig.crosso.R;
import com.ebig.crosso.manager.type.RecordType;
import com.ebig.crosso.ui.FilterCall;
import com.ebig.log.ELog;
import com.ebig.crosso.utils.CroStrUtils;
import com.ebig.crosso.utils.CrossoTimeUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TypeView extends LinearLayout implements View.OnClickListener {
    private HashMap<Integer, String> cbList;
    private TextView tvStart;
    private TextView tvEnd;
    public TypeView(Context context) {
        super(context);
    }

    public TypeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_type, this, true);
        initView();
    }

    private void initView() {
        cbList = new HashMap<>();
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
        tvStart=(TextView)findViewById(R.id.tvStart);
        tvEnd=(TextView)findViewById(R.id.tvEnd);
        tvStart.setOnClickListener(this);
        tvEnd.setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);
        findViewById(R.id.btnOk).setOnClickListener(this);
        findViewById(R.id.selectAll).setOnClickListener(this);
        findViewById(R.id.selectNone).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnClose){
            if (filterCall!=null){
                filterCall.finish();
            }
        }else if (v.getId()==R.id.btnOk){
            List<String> list=getTypeArr();
            if (list.size() == 0) {
                Toast.makeText(getContext(), "信息类型不可为空", Toast.LENGTH_LONG).show();
            } else if (startTime >= endTime) {
                Toast.makeText(getContext(), "开始时间不能大于结束时间", Toast.LENGTH_LONG).show();
            } else if (filterCall!=null){
                filterCall.onSelectResult(startTime,endTime,list);
            }
        }else if (v.getId()==R.id.tvStart){
            if (filterCall!= null){
                filterCall.changeTime();
            }
//            DateTimepickerDialog datetimedialog = new DateTimepickerDialog(getContext(),System.currentTimeMillis());
//            datetimedialog.setTitle("设置开始时间");
//            datetimedialog.setOnDateTimeSetListener(new DateTimepickerDialog.DateTimeSetListener() {
//                public void onDateChange(DialogInterface dialog, String datetimestr) {
//                    ELog.print("OnDateTimeSet:"+datetimestr);
//                    startTime=CrossoTimeUtils.time2string(datetimestr);
//                    tvStart.setText(CrossoTimeUtils.getDateFormat(startTime));
//
//                }
//            });
//            datetimedialog.show();
        }else if (v.getId()==R.id.tvEnd){
            if (filterCall!= null){
                filterCall.changeTime();
            }
//            DateTimepickerDialog datetimedialog = new DateTimepickerDialog(getContext(),System.currentTimeMillis());
//            datetimedialog.setTitle("设置结束时间");
//            datetimedialog.setOnDateTimeSetListener(new DateTimepickerDialog.DateTimeSetListener() {
//                public void onDateChange(DialogInterface dialog, String datetimestr) {
//                    ELog.print("OnDateTimeSet:"+datetimestr);
//                    endTime=CrossoTimeUtils.time2string(datetimestr);
//                    tvEnd.setText(CrossoTimeUtils.getDateFormat(endTime));
//                }
//            });
//            datetimedialog.show();
        }else if (v.getId()==R.id.selectAll){
            for(int key:cbList.keySet()){
                ((CheckBox)findViewById(key)).setChecked(true);
            }
        }if (v.getId()==R.id.selectNone){
            for(int key:cbList.keySet()){
                ((CheckBox)findViewById(key)).setChecked(false);
            }
        }
    }

    private long startTime = 0;
    private long endTime = 0;
    public void setTime(long start,long end){
        this.startTime=start;
        this.endTime=end;
        tvStart.setText(CrossoTimeUtils.getDateFormat(startTime));
        tvEnd.setText(CrossoTimeUtils.getDateFormat(endTime));
    }
    public List<String> getTypeArr() {
        List<String> list = new ArrayList<>();
        for (int key : cbList.keySet()) {
            if (((AppCompatCheckBox) findViewById(key)).isChecked()) {
                list.add(cbList.get(key));
                ELog.print("开启了：" + CroStrUtils.getType(cbList.get(key)));
            }
        }
        return list;
    }
    private FilterCall filterCall;

    public void setFilterCall(FilterCall filterCall) {
        this.filterCall = filterCall;
    }
}
