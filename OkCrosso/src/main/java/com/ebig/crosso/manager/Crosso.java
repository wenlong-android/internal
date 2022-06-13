package com.ebig.crosso.manager;

import android.app.Application;
import android.content.Context;
import android.content.Intent;


import com.ebig.crosso.BuildConfig;
import com.ebig.crosso.ui.TimeBarActivity;
import com.ebig.log.ELog;

public class Crosso implements ICrosso {
    /**
     * Crosso克罗索-希腊神话神明，监察神和人的犯罪行为
     * all 监控所有
     * cleanFrequency 时间单位为天，默认30天，唯独奔溃日志不会被清理
     * userClick 用户行为日志
     * ram 内存信息
     * hardWare 硬件功能接口
     * server 服务器接口
     * consume 耗时接口
     * exception try catch 异常收集
     * userClick 用户行为日志
     * crash 奔溃日志
     * stackTrackFrequency 自动收集堆栈信息频率
     */
    private static Application context;
    private static long stackTrackFrequency = 30000;
    private static int cleanFrequency = 30;
    private static boolean boolUser = false;
    private static boolean boolRam = false;
    private static boolean boolHardWare = false;
    private static boolean boolServer = false;
    private static boolean boolComsume = false;
    private static boolean boolException = false;
    private static boolean boolCrash = false;
    private static boolean boolStack = false;
    private static boolean all = false;
    private static boolean debug = false;

    private static class L {
        private static Crosso detetion = new Crosso();
    }

    public static Crosso with(Application cox) {
        context = cox;
        return L.detetion;
    }

    @Override
    public Crosso defaultConfig() {
        all = true;
        return this;
    }

    @Override
    public Crosso stackTrace(long frequency) {
        stackTrackFrequency = frequency;
        return this;
    }


    @Override
    public Crosso cleanFrequency(int day) {
        this.cleanFrequency = day;
        return this;
    }

    @Override
    public Crosso userClick() {
        boolUser = true;
        return this;
    }

    @Override
    public Crosso crash() {
        boolCrash = true;
        return this;
    }

    @Override
    public Crosso ram() {
        boolRam = true;
        return this;
    }

    @Override
    public Crosso hardWare() {
        return this;
    }

    @Override
    public Crosso server() {
        return this;
    }


    @Override
    public Crosso consume() {
        return this;
    }

    @Override
    public Crosso debug(boolean isDebug) {
        debug = isDebug;
        return this;
    }

    @Override
    public Crosso exception() {
        boolException = true;
        return this;
    }


    @Override
    public void start() {
        if (all) {
            CrossoDataAPI.init(context);
            CrashHandler.getInstance().init(context);
            new LowMemoryDetect(context).start();
            new StackCatch().start(stackTrackFrequency);
        } else {
            if (boolUser) {
                CrossoDataAPI.init(context);
            }
            if (boolCrash) {
                CrashHandler.getInstance().init(context);
            }
            if (boolRam) {
                new LowMemoryDetect(context).start();
            }
            if (boolStack) {
                new StackCatch().start(stackTrackFrequency);
            }

        }
        new CleanTask(cleanFrequency).clean(context);
        new AnrHandler(context).watch();

    }



    public static void viewRecord(Context context) {
        context.startActivity(new Intent(context, TimeBarActivity.class));
    }

}

