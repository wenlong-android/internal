package com.ebig.medical.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ebig.log.ELog;
import com.ebig.service.CmdServiceManager;
import com.ebig.socket.common.HostSpCenter;
import com.ebig.utils.StrUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IpActivity extends AppCompatActivity {


    @BindView(R2.id.edit1)
    EditText edit1;
    @BindView(R2.id.edit2)
    EditText edit2;
    @BindView(R2.id.edit3)
    EditText edit3;
    @BindView(R2.id.edit4)
    EditText edit4;
    @BindView(R2.id.btnCancle)
    Button btnCancle;
    @BindView(R2.id.btnOK)
    Button btnOK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_ip);
        ButterKnife.bind(this);
        String host = HostSpCenter.getClientIp();
        if (StrUtils.notEmpty(host)) {
            String[] arr = host.split("\\.");
            ELog.printArrays(arr);
            if (arr != null && arr.length == 4) {
                edit1.setText(arr[0]);
                edit2.setText(arr[1]);
                edit3.setText(arr[2]);
                edit4.setText(arr[3]);
            }
        }
    }


    @OnClick({R2.id.btnCancle, R2.id.btnOK})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btnCancle) {
            finish();
        } else if (viewId == R.id.btnOK) {
            String t1 = edit1.getText().toString();
            String t2 = edit2.getText().toString();
            String t3 = edit3.getText().toString();
            String t4 = edit4.getText().toString();
            if (StrUtils.notEmpty(t1) &&
                    StrUtils.notEmpty(t2) &&
                    StrUtils.notEmpty(t3) &&
                    StrUtils.notEmpty(t4)) {
                HostSpCenter.saveClientIp(t1 + "." + t2 + "." + t3 + "." + t4);
                Toast.makeText(IpActivity.this, "即将重新启动", Toast.LENGTH_LONG).show();
                CmdServiceManager.l().release();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 2000);

            }
        }
    }
}
