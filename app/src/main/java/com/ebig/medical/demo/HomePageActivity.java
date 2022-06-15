package com.ebig.medical.demo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ebig.annotation.ThreadIo;
import com.ebig.annotation.ThreadMain;
import com.ebig.crosso.manager.Crosso;
import com.ebig.crosso.manager.db.upload.ThDbInfo;
import com.ebig.crosso.manager.db.upload.UploaManager;
import com.ebig.crosso.utils.PermissionConsts;
import com.ebig.http.ApushEntity;
import com.ebig.idl.Common2Call;
import com.ebig.log.ELog;
import com.ebig.medical.demo.fragment.BackLightFragment;
import com.ebig.medical.demo.fragment.ColorLightFragment;
import com.ebig.medical.demo.fragment.FingerFragment;
import com.ebig.medical.demo.fragment.LedFragment;
import com.ebig.medical.demo.fragment.LockFragment;
import com.ebig.medical.demo.fragment.ScaleFragment;
import com.ebig.medical.demo.fragment.ScannerFragment;
import com.ebig.push.APush;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.idl.PipeReadAndWriteCall;
import com.ebig.utils.FactoryCodeIns;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.Ipipeline;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.idl.PipeSocketMonitorCall;
import com.ebig.socket.idl.PipeIdelCall;
import com.ebig.socket.idl.PipeThCall;
import com.ebig.socket.entity.CmdType;
import com.ebig.socket.utils.IpUtils;
import com.ebig.socket.utils.PipeStringUtils;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener, PipeSocketMonitorCall, PipeThCall, PipeIdelCall, PipeReadAndWriteCall {

    private Button mServerBtn, client;

    private TextView mIPTv;
    private int mPort = 8080;
    private TextView status;
    private TextView tv_receive, tv_send;
    private ScrollView scrollview;
    private TabLayout tabLayout;
    private TextView tv_select;
    private ViewPager viewPager;
    private TextView btn_heartbeat, btn_th;
    private Ipipeline pipeline;

    private Button btnDatabase;
    private String factoryCode = "a00005061-00000001";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        pipeline = PipeBus.l().build();
        FactoryCodeIns.save("a00005061-00000001");
        initView();
        initData();
        initListenner();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.client) {
//            TextView textView = null;
//            textView.setText("");
            startActivity(new Intent(HomePageActivity.this, IpActivity.class));
            // print();
        } else if (v.getId() == R.id.launch) {

        } else if (v.getId() == R.id.btnDatabase) {
             Crosso.viewRecord(HomePageActivity.this);
            // THServiceManager.l().begin("192.168.10.88",9413);

        } else if (v.getId() == R.id.btnThOnly) {
             startActivity(new Intent(HomePageActivity.this, ThActivity.class));

//            Api.deFault().getTenantList().request(new ApiCall<NetResult>() {
//                @Override
//                public void onResult(NetResult netResult) {
//                    ELog.print("getTenantList NetResult onResult:"+ GsonUtils.toJson(netResult));
//                }
//
//                @Override
//                public void onFail(int code, String error) {
//                    ELog.print("getTenantList NetResult onFail:"+error);
//                }
//            });
//            long nowTome = System.currentTimeMillis();
//            long start = TimeUtils.time2string("2021-05-30 00:00:00");
            //System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 365;

//            ApiParamsAll params = new ApiParamsAll(1, 1000, "deviceBaseUser", start, nowTome);
//            Api.deFault().deviceBaseDept(params)
//                    .request(new ApiCall<NetResult>() {
//                        @Override
//                        public void onResult(NetResult netResult) {
//                            ELog.print("getTenantList NetResult onResult:" + GsonUtils.toJson(netResult));
//                        }
//
//                        @Override
//                        public void onFail(int code, String error) {
//                            ELog.print("getTenantList NetResult onFail:" + error);
//                        }
//                    });
//            Api.deFault().registerDevice(FactoryCodeIns.getCode(),"1","智能冰箱").request(new ApiCall<NetResult>() {
//                @Override
//                public void onResult(NetResult netResult) {
//                 ELog.print("registerDevice:"+netResult.toString());
//                }
//
//                @Override
//                public void onFail(int code, String error) {
//                    ELog.print("registerDevice onFail:"+error);
//                }
//            });
        }
    }

    @ThreadIo
    private void print() {
        List<ThDbInfo> list = UploaManager.getList();
        for (int i = 0; i < list.size(); i++) {
            ELog.print(list.get(i).toString());
        }
    }

    private void autoStart(boolean isSuccess) {
        ELog.print("服务：" + isSuccess);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                status.setText("状态：" + (isSuccess ? "连通" : "断开"));
            }
        });
    }

    private void initListenner() {
        client.setOnClickListener(this);
        mServerBtn.setOnClickListener(this);
        PipeBus.l().addThListnenner(HomePageActivity.this);
        PipeBus.l().addIdelListenner(HomePageActivity.this);
        PipeBus.l().addThListnenner(HomePageActivity.this);
        PipeBus.l().addPipeChatListenner(HomePageActivity.this);
        PipeBus.l().addPipeConnectListenner(HomePageActivity.this);
        btnDatabase.setOnClickListener(this);
        findViewById(R.id.btnThOnly).setOnClickListener(this);

    }


    private void initData() {
        mIPTv.setText("本机:" + IpUtils.getIPAddress(HomePageActivity.this));
        client.setText("客户端:192.168.1.189"  );
        initTag();

//
//        UserBinding  user=new UserBinding();
//        user.setUser_id(1001);
//        user.setUserName("chenjiaming");
//        user.setBindType("1");
//        user.setBindLink("http://abc.png");
//        user.setCreateTime("2022-06-01 12:52:00");
//        user.setId(100001);
//        user.setExt1("null");
//        user.setExt2("null");
//        user.setExt3("null");
//        ELog.print(new Gson().toJson(user));;


    }

    @Override
    public void deviceConnect(String uuid, String ipHost) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    status.setText("连接状态：已连接");
                }
            });

    }

    @Override
    public void deviceDisConnect(String uuid, String ipHost) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    status.setText("连接状态：已断开");
                }
            });

    }

    @Override
    public void messageRead(String uuid, String ipHost, String cmd) {
        tv_send.append("\n" + ipHost + " " + cmd);
    }

    @Override
    public void writeOutTime(String uuid, String ipHost) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    status.setText("连接状态：发送超时");
                }
            });

    }

    @Override
    public void readOutTime(String uuid, String ipHost) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    status.setText("连接状态：读取超时");
                }
            });

    }

    @Override
    public void outTime(String uuid, String ipHost) {

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    status.setText("连接状态：读写超时");
                }
            });

    }

    private void initView() {
        status = findViewById(R.id.status);
        tv_receive = findViewById(R.id.tv_receive);
        mServerBtn = findViewById(R.id.launch);
        mIPTv = findViewById(R.id.ip);
        scrollview = findViewById(R.id.scrollview);
        tabLayout = findViewById(R.id.tab1);
        tv_select = findViewById(R.id.tv_select);
        viewPager = findViewById(R.id.vp1);
        btn_heartbeat = findViewById(R.id.btn_heartbeat);
        btn_th = findViewById(R.id.btn_th);
        tv_send = (TextView) findViewById(R.id.tv_send);
        client = (Button) findViewById(R.id.client);
        btnDatabase = (Button) findViewById(R.id.btnDatabase);
        APush.regist().init(new Common2Call<ApushEntity, String>() {
            @Override
            public void onCommonCall(ApushEntity apushEntity) {

            }

            @Override
            public void onOpposite(String error) {

            }
        });
    }

    private void initTag() {
        List<String> title = new ArrayList<>();
        title.add("开锁");
        title.add("背光灯控制");
        title.add("三色灯");
        title.add("LED显示");
        title.add("指静脉");
        title.add("电子秤");
        title.add("扫描头");
        for (int i = 0; i < title.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tv_select.setText("已选择：" + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tv_select.setText("已选择：" + tab.getText());
            }
        });
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LockFragment());
        fragments.add(new BackLightFragment());
        fragments.add(new ColorLightFragment());
        fragments.add(new LedFragment());
        fragments.add(new FingerFragment());
        fragments.add(new ScaleFragment());
        fragments.add(new ScannerFragment());


        EbFragmentAdapter fragmentAdapter = new EbFragmentAdapter(getSupportFragmentManager(), fragments, title);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                excetionTest();
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private int receiveCount = 0;
    private long IdertTime = 0;

    @ThreadMain
    @Override
    public void onIdelCall(long interval) {
        if (interval > 1000000) {
            btn_heartbeat.setText("*心跳* 时间间隔:0000");
        } else {
            btn_heartbeat.setText("*心跳* 时间间隔:" + interval);
        }
        propertyValuesHolderDown(btn_heartbeat);
    }


    @Override
    public void onThCall(double temperature, double humidity, long internal) {
        //ELog.print("ThClient onThCall temperature:"+temperature+" ,humidity:"+humidity);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                String gap = (internal > 1000000) ? "0000" : internal + "";
                btn_th.setText("温度:" + PipeStringUtils.getDouble2(temperature) + " ,湿度:" + PipeStringUtils.getDouble2(humidity) + " 间隔：" + gap);
                propertyValuesHolderDown(btn_th);
            }
        });

    }

    @Override
    public void send(CmdRequestInfo info) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                tv_receive.setText("");
                sb = null;
                tv_send.setText(info.getCmd() + "\n类型:" + CmdType.get(info.order(), info.getData()) + " 数据：" + info.getData());
            }
        });

    }

    private StringBuilder sb = null;


    @PermissionConsts.Permissions
    @Override
    public void receive(CmdResultInfo rawInfo) {
        ELog.print("============" + rawInfo.toString());
        if (sb == null) {
            sb = new StringBuilder();
            sb.append(rawInfo.getCmd() + "\n类型:" + CmdType.get(rawInfo.getOrder(), rawInfo.getData()) + " 数据：" + rawInfo.getData() + "\n");
        } else {
            sb.append("\n" + rawInfo.getCmd() + "\n类型:" + CmdType.get(rawInfo.getOrder(), rawInfo.getData()) + " 数据：" + rawInfo.getData() + "\n");
        }

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                tv_receive.setText(sb.toString());
            }
        });
    }

    public static void propertyValuesHolderDown(final View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0.9f, 0.9f, 0.91f, 0.92f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.89f, 0.90f, 0.91f, 0.92f, 0.94f, 0.95f, 0.96f, 0.97f, 0.98f, 0.99f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.9f, 0.9f, 0.91f, 0.92f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.89f, 0.90f, 0.91f, 0.92f, 0.94f, 0.95f, 0.96f, 0.97f, 0.98f, 0.99f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.9f, 0.9f, 0.91f, 0.92f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.89f, 0.90f, 0.91f, 0.92f, 0.94f, 0.95f, 0.96f, 0.97f, 0.98f, 0.99f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(200).start();
    }

    @ThreadIo(user = "wwl", lib = "mainLib", delay = 6000)
    private void excetionTest() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

