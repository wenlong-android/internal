package com.ebig.crosso.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/*
 * 编写：wenlong on 2018/3/12 16:49
 * 企业QQ： 2853239883
 * 钉钉：13430330686
 */
public class CroThread {

    public static CroThread ins;
    public static Handler handler;

    public static CroThread getIns() {
        if (ins == null) {
            synchronized (CroThread.class) {
                if (ins == null) {
                    ins = new CroThread();
                }
            }
        }
        return ins;
    }

    //切换到主线程，只是切换到主线程，不做耗时操作
    public void runOnMainThread(Runnable runnable) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(runnable);
    }

    //切换到主线程,只是切换到主线程，不做耗时操作
    public void runOnMainThread(Runnable runnable, long delay) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.postDelayed(runnable, delay);
    }

    private static ExecutorService executorService = null;

    //创建单个线程时候使用，尽可能避免使用野线程
    public void runSingleThread(Runnable runnable) {
        int NUMBAR_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;//设置存活时间，确保空闲时线程能被释放
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.DAYS.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(NUMBAR_OF_CORES,
                    NUMBAR_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, taskQueue);
        }
        executorService.execute(runnable);
    }
    private static ExecutorService executorService2 = null;
    //创建单个线程时候使用，尽可能避免使用野线程
    public void runThread(Runnable runnable) {
        if (executorService2 == null) {
            executorService2=Executors.newSingleThreadExecutor();
        }
        executorService2.execute(runnable);
    }
    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
