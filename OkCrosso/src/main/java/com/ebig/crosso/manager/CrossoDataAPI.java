package com.ebig.crosso.manager;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.ebig.crosso.bean.aop.AopCrashEntity;
import com.ebig.crosso.bean.aop.AopDeviceEntity;
import com.ebig.crosso.bean.aop.CmdDetails;
import com.ebig.crosso.bean.aop.ExceptionDetails;
import com.ebig.crosso.bean.aop.HttpDetails;
import com.ebig.crosso.bean.aop.MemoryDetails;
import com.ebig.crosso.bean.aop.StackDetails;
import com.ebig.crosso.bean.aop.AopClickEntity;
import com.ebig.crosso.manager.db.CrossoDb;
import com.ebig.crosso.manager.type.AopAmendType;
import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.manager.type.RecordType;
import com.ebig.crosso.utils.CrossoStackUtils;
import com.ebig.log.ELog;
import com.ebig.crosso.utils.CrossoRamUtils;
import com.ebig.crosso.utils.CroThread;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@Keep
public class CrossoDataAPI {
    private static Application application;
    private final String TAG = this.getClass().getSimpleName();
    public static final String SDK_VERSION = "1.0.0";
    private static CrossoDataAPI INSTANCE;
    private static final Object mLock = new Object();
    private static AopDeviceEntity mAopDeviceEntity;
    private String mDeviceId;
    @Keep
    @SuppressWarnings("UnusedReturnValue")
    public static CrossoDataAPI init(Application app) {
        application = app;
        synchronized (mLock) {
            if (null == INSTANCE) {
                INSTANCE = new CrossoDataAPI(app);
            }
            return INSTANCE;
        }
    }

    @Keep
    public static CrossoDataAPI getInstance() {
        return INSTANCE;
    }


    private CrossoDataAPI(Application application) {
        mDeviceId = SensorsDataPrivate.getAndroidID(application.getApplicationContext());
        mAopDeviceEntity = SensorsDataPrivate.getAopDeviceEntity(application.getApplicationContext());
    }

    /**
     * 忽略View
     *
     * @param view 要忽略的View
     */
    @SuppressWarnings("unused")
    public void ignoreView(View view) {
        if (view != null) {
            //   view.setTag(R.id.sensors_analytics_tag_view_ignored, "1");
        }
    }

    /**
     * 设置View属性
     *
     * @param view       要设置的View
     * @param properties 要设置的View的属性
     */
    public void setViewProperties(View view, JSONObject properties) {
        if (view == null || properties == null) {
            return;
        }

        // view.setTag(R.id.sensors_analytics_tag_view_properties, properties);
    }

    public static String getDate(long s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        return simpleDateFormat.format(date);

    }

    private static String getVersion() {
        try {
            final PackageManager manager = application.getPackageManager();
            final PackageInfo packageInfo = manager.getPackageInfo(application.getPackageName(), 0);

            return packageInfo.versionName;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return "获取版本号失败";
    }

    /**
     * Track 事件
     *
     * @param eventName String 事件名称
     */
    @Keep
    public void track(@NonNull final String eventName, @Nullable AopClickEntity details) {
       String activityStr= details.getActivity();
       //过滤查看日志界面的操作
       if (activityStr.contains("com.ebig.crosso.ui")){
           return;
       }
        String threadInfo=Thread.currentThread().getName();
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("event", eventName);
                    makeUpInfo(details);
                    details.setDeviceId(mDeviceId);
                    long nowTime = System.currentTimeMillis();
                    AopDbInfo info = new AopDbInfo(
                            nowTime,
                            getVersion(),
                            getDate(nowTime),
                            RecordType.aopUserClick,
                            new Gson().toJson(details),
                            threadInfo,
                            CrossoStackUtils.getStackTrace(),
                            CrossoRamUtils.displayMemory(application),
                            AopAmendType.unAmend
                    );
                 //   ELog.print(info.toString());
                    CrossoDb.with(application).getAop().insert(info);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });

    }

    private void makeUpInfo(AopClickEntity details) {
        details.setManufacturer(mAopDeviceEntity.getManufacturer());
        details.setModel(mAopDeviceEntity.getModel());
        details.setScreen(mAopDeviceEntity.get$screen());
    }

    public void crash(String threadInfo,AopCrashEntity crash) {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
             //   ELog.print("crash:" + crash);
                long nowTime = System.currentTimeMillis();
                AopDbInfo info = new AopDbInfo(
                        nowTime,
                        getVersion(),
                        getDate(nowTime),
                        RecordType.aopCrash,
                        new Gson().toJson(crash),
                        threadInfo,
                        CrossoStackUtils.getStackTrace(),
                        CrossoRamUtils.displayMemory(application),
                        AopAmendType.unAmend
                );
                //ELog.print("异常信息：" + info.toString());
                CrossoDb.with(application).getAop().insert(info);
            }
        });

    }

    public void saveStackTrace(String threadInfo,StackDetails details) {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {

                long nowTime = System.currentTimeMillis();
                AopDbInfo info = new AopDbInfo(
                        nowTime,
                        getVersion(),
                        getDate(nowTime),
                        RecordType.stack,
                        new Gson().toJson(details),
                        threadInfo,
                        CrossoStackUtils.getStackTrace(),
                        CrossoRamUtils.displayMemory(application),

                        AopAmendType.unAmend
                );
              //  ELog.print("异常信息：" + info.toString());
                CrossoDb.with(application).getAop().insert(info);
            }
        });
    }

    public void recordMemory(String threadInfo,MemoryDetails details) {
        details.setRamInfo(CrossoRamUtils.displayMemory(application));
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {

                long nowTime = System.currentTimeMillis();
                AopDbInfo info = new AopDbInfo(
                        nowTime,
                        getVersion(),
                        getDate(nowTime),
                        RecordType.ram,
                        new Gson().toJson(details),
                        threadInfo,
                        CrossoStackUtils.getStackTrace(),
                        CrossoRamUtils.displayMemory(application),

                        AopAmendType.unAmend
                );
              //  ELog.print("异常信息：" + info.toString());
                CrossoDb.with(application).getAop().insert(info);
            }
        });

    }

    public void exception(String threadInfo,ExceptionDetails exceptionDetails) {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                long nowTime = System.currentTimeMillis();
                AopDbInfo info = new AopDbInfo(
                        nowTime,
                        getVersion(),
                        getDate(nowTime),
                        RecordType.exception,
                        new Gson().toJson(exceptionDetails),
                        threadInfo,
                        CrossoStackUtils.getStackTrace(),
                        CrossoRamUtils.displayMemory(application),

                        AopAmendType.unAmend
                );
              //  ELog.print("异常信息：" + info.toString());
                CrossoDb.with(application).getAop().insert(info);
            }
        });
    }




    public void saveCmd(String threadInfo,CmdDetails details) {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                long nowTime = System.currentTimeMillis();
                AopDbInfo info = new AopDbInfo(
                        nowTime,
                        getVersion(),
                        getDate(nowTime),
                        RecordType.aopHardWare,
                        new Gson().toJson(details),
                        threadInfo,
                        CrossoStackUtils.getStackTrace(),
                        CrossoRamUtils.displayMemory(application),
                        AopAmendType.unAmend
                );
                //  ELog.print("异常信息：" + info.toString());
                CrossoDb.with(application).getAop().insert(info);
            }
        });
    }


    public void http(String threadInfo, HttpDetails httpDetails) {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                long nowTime = System.currentTimeMillis();
                AopDbInfo info = new AopDbInfo(
                        nowTime,
                        getVersion(),
                        getDate(nowTime),
                        RecordType.aopServer,
                        new Gson().toJson(httpDetails),
                        threadInfo,
                        CrossoStackUtils.getStackTrace(),
                        CrossoRamUtils.displayMemory(application),

                        AopAmendType.unAmend
                );
            ELog.print("http：" + httpDetails.toString());
                CrossoDb.with(application).getAop().insert(info);
            }
        });
    }
}
