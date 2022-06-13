package com.ebig.crosso.ui;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.ebig.crosso.R;
import com.ebig.crosso.manager.db.SqlHelper;
import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.utils.CroAppUtils;
import com.ebig.crosso.utils.CroThread;
import com.ebig.crosso.widget.SelectDayView;
import com.ebig.crosso.widget.TypeView;

import java.util.List;

public class TimeBarActivity extends AppCompatActivity {
    private InfoAdaper infoAdaper;
    private List<AopDbInfo> AopDbInfoList;
    private GridView grList;
    private RelativeLayout rl_nodata;
    private TypeView selectView;
    private SelectDayView selectTimeView;
    private RelativeLayout progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_timebar);
        initView();
        initData();
        initListenner();
        CroAppUtils.initParams(this);
    }

    private void initListenner() {
        selectView.setFilterCall(new FilterCall() {
            @Override
            public void onSelectResult(long start, long end, List<String> typeList) {
                startTime = start;
                endTime = end;
                Object[] arr = new Object[2 + typeList.size()];
                for (int i = 0; i < typeList.size(); i++) {
                    arr[i] = typeList.get(i);
                }
                arr[typeList.size()] = start;
                arr[typeList.size() + 1] = end;
                progress.setVisibility(View.VISIBLE);
                SqlHelper.getSql(TimeBarActivity.this, arr, new AopDataCall<List<AopDbInfo>>() {
                    @Override
                    public void onCall(List<AopDbInfo> AopDbInfos) {
                        showList(AopDbInfos);
                    }
                });
            }

            @Override
            public void changeTime() {

                selectTimeView.setVisibility(View.VISIBLE);
            }

            @Override
            public void finish() {
                TimeBarActivity.this.finish();
            }
        });

        selectTimeView.setChangerListenner(new SelectDayView.OnChangerListenner() {
            @Override
            public void onStartChange(long time) {
                startTime = time;
                selectView.setTime(startTime, endTime);
            }

            @Override
            public void onEndChange(long time) {
                endTime = time;
                selectView.setTime(startTime, endTime);
            }
        });
    }

    private long startTime = 0;
    private long endTime = 0;
    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;

    private void initData() {
        endTime = System.currentTimeMillis();
        startTime = endTime - ONE_DAY_IN_MS;

        Object[] arr = SqlHelper.getArr(startTime, endTime);
        progress.setVisibility(View.VISIBLE);
        SqlHelper.getSql(TimeBarActivity.this, arr, new AopDataCall<List<AopDbInfo>>() {
            @Override
            public void onCall(List<AopDbInfo> AopDbInfos) {
                showList(AopDbInfos);
            }
        });
        selectView.setTime(startTime, endTime);
        selectTimeView.setTime(startTime, endTime);
    }

    private void initView() {
        grList = (GridView) findViewById(R.id.grList);
        rl_nodata = (RelativeLayout) findViewById(R.id.rl_nodata);
        selectView = (TypeView) findViewById(R.id.selectView);
        selectTimeView = (SelectDayView) findViewById(R.id.selectTimeView);
        progress=(RelativeLayout)findViewById(R.id.progress);
    }


    private void showList(List<AopDbInfo> AopDbInfos) {
        CroThread.getIns().runOnMainThread(new Runnable() {
            @Override
            public void run() {
              CroThread.getIns().runOnMainThread(new Runnable() {
                  @Override
                  public void run() {
                      progress.setVisibility(View.GONE);
                  }
              },500);
                if (AopDbInfos == null || AopDbInfos.size() == 0) {
                    rl_nodata.setVisibility(View.VISIBLE);
                } else {
                    rl_nodata.setVisibility(View.GONE);
                    AopDbInfoList = AopDbInfos;
                    infoAdaper = new InfoAdaper(TimeBarActivity.this, AopDbInfoList);
                    grList.setAdapter(infoAdaper);
                 //   ELog.print("得到日志数量:" + AopDbInfos.size());
                    for (AopDbInfo info : AopDbInfos) {
                       // ELog.print("得到日志:" + info.getTimeStamp() + " ,id=" + info.getId());
                    }
                }
            }
        });
    }


}
