package com.ebig.medical.demo;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ebig.annotation.ThreadMain;
import com.ebig.crosso.utils.CroThread;
import com.ebig.log.ELog;
import com.ebig.temperature_humidity.ThClient;
import com.ebig.temperature_humidity.ThWritheReadListenner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThActivity extends AppCompatActivity implements ThWritheReadListenner {
    @BindView(R2.id.tvSend)
    TextView tvSend;
    @BindView(R2.id.tvReceive)
    TextView tvReceive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_th);
        ButterKnife.bind(this);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                ThClient client = new ThClient.Config("192.168.1.71", 9413).addListenner(ThActivity.this).build();
                client.start();
            }
        });

    }
    private int readCount=0,writeCount=0;
    @Override
    @ThreadMain
    public void onWrite(String json) {
        ELog.print("THACTIVITY onWrite: "+json);
        writeCount++;
        if (writeCount<=5){
            tvSend.append(json+"\n");
        }else {
            writeCount=0;
            tvSend.setText(json+"\n");
        }
    }

    @Override
    @ThreadMain
    public void onRead(String json) {
        ELog.print("THACTIVITY onRead: "+json);
        readCount++;
        if (readCount<=5){
            tvReceive.append(json+"\n");
        }else {
            readCount=0;
            tvReceive.setText(json+"\n");
        }
    }
}
