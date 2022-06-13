package com.ebig.crosso.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Debug;


import com.ebig.crosso.bean.aop.RamInfo;
import com.ebig.log.ELog;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CrossoRamUtils {
    private String TAG = "CrossoRamUtils";

//    public static void displayMemory(Application app) {
//        final ActivityManager activityManager = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
//        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
//        activityManager.getMemoryInfo(info);
//        //最大分配内存
//        int memory = activityManager.getMemoryClass();
//        ELog.print("memory: " + memory);
//        //最大分配内存获取方法2
//        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
//        //当前分配的总内存
//        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
//        //剩余内存
//        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
//        ELog.print("maxMemory: " + maxMemory);
//        ELog.print("totalMemory: " + totalMemory);
//        ELog.print("freeMemory: " + freeMemory);
//        float systemFree=(float) (info.availMem*1.0 / (1024 * 1024));
//        float systemThreshold=(float)(info.threshold *1.0/ (1024 * 1024));
//        ELog.print("系统剩余内存:" + systemFree + "M");
//        ELog.print("当系统剩余内存低于" + systemThreshold+ "M" + "时就看成低内存运行");
//        ELog.print("系统是否处于低内存运行：" + info.lowMemory);
//
//        ELog.print("系统已经分配的native内存：" + (Debug.getNativeHeapAllocatedSize() / (1024 * 1024)) + "M");
//        ELog.print("系统还剩余的native内存：" + (Debug.getNativeHeapFreeSize() / (1024 * 1024)) + "M");
//        ELog.print("系统的所有native内存大小：" + (Debug.getNativeHeapSize() / (1024 * 1024)) + "M");
//        getMemory();
//
//
//
//
//
//    }
    public static String displayMemory(Application app) {
        final ActivityManager activityManager = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        //最大分配内存
        int memory = activityManager.getMemoryClass();
        //最大分配内存获取方法2
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0 / (1024 * 1024));
        //当前分配的总内存
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0 / (1024 * 1024));
        //剩余内存
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0 / (1024 * 1024));
        float systemFree = (float) (info.availMem * 1.0 / (1024 * 1024));
        float systemThreshold = (float) (info.threshold * 1.0 / (1024 * 1024));
        float nativeExsit = (float) (Debug.getNativeHeapAllocatedSize() * 1.0 / (1024 * 1024));
        float nativeFree = (float) (Debug.getNativeHeapFreeSize() * 1.0 / (1024 * 1024));
        float nativeAll = (float) (Debug.getNativeHeapSize() * 1.0 / (1024 * 1024));

        RamInfo ramInfo = new RamInfo(
                maxMemory + "M",
                maxMemory + "M",
                (maxMemory - freeMemory)  + "M",
                totalMemory+ "M",
                systemFree + "M",
                systemThreshold + "M",
                info.lowMemory ? "是" : "否",
                nativeExsit + "M",
                nativeFree + "M",
                nativeAll + "M"
        );
        //ELog.print(ramInfo.toString());
        return new Gson().toJson(ramInfo);
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)

    public static int getMemory() {
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
// dalvikPrivateClean + nativePrivateClean + otherPrivateClean;
        int totalPrivateClean = memoryInfo.getTotalPrivateClean();
// dalvikPrivateDirty + nativePrivateDirty + otherPrivateDirty;
        int totalPrivateDirty = memoryInfo.getTotalPrivateDirty();
// dalvikPss + nativePss + otherPss;
        int totalPss = memoryInfo.getTotalPss();
// dalvikSharedClean + nativeSharedClean + otherSharedClean;
        int totalSharedClean = memoryInfo.getTotalSharedClean();
// dalvikSharedDirty + nativeSharedDirty + otherSharedDirty;
        int totalSharedDirty = memoryInfo.getTotalSharedDirty();
// dalvikSwappablePss + nativeSwappablePss + otherSwappablePss;
        int totalSwappablePss = memoryInfo.getTotalSwappablePss();
        ELog.print("totalPrivateClean: " + totalPrivateClean/1024);
        ELog.print("totalPrivateDirty: " + totalPrivateDirty/1024);
        ELog.print("totalPss: " + totalPss/1024);
        ELog.print("totalSharedClean: " + totalSharedClean/1024);
        ELog.print("totalSharedDirty: " + totalSharedDirty/1024);
        ELog.print("totalSwappablePss: " + totalSwappablePss/1024);
        int total = totalPrivateClean + totalPrivateDirty + totalPss + totalSharedClean + totalSharedDirty + totalSwappablePss;
        ELog.print("total: " + total/1024);
        // ELog.print("getCpuRate: " + getCpuRate());
        return total;

    }

    /**
     * 通过获取cpu一行的数据，即可进行CPU占用率的计算。我们会用到的数据有:
     * - user(21441),从系统启动开始累计到当前时刻，处于用户态的运行时间，不包含nice值为负的进程。
     * - nice(3634),从系统启动开始累计到当前时刻，nice值为负的进程所占用的CPU时间。
     * - system(13602),从系统启动开始累计到当前时刻，处于核心态的运行时间。
     * - idle(818350),从系统启动开始累计到当前时刻，除IO等待时间以外的其它等待时间。
     * - iowait(3535),从系统启动开始累计到当前时刻，IO等待时间。
     * - irq(2),从系统启动开始累计到当前时刻，硬中断时间。
     * - softirq(99),从系统启动开始累计到当前时刻，软中断时间。
     * 总的CPU占用率的计算方法为：采样两个足够短的时间间隔的CPU快照，
     * CPU占用率 = 100*((totalTime2-totalTime1)-(idle2-idle1))/(totalTime2-totalTime1)。
     */

//获取cpu使用率
    public static float getCpuRate() {
        //采样第一次cpu信息快照
        Map<String, String> map1 = getMap();
        //总的CPU时间totalTime = user+nice+system+idle+iowait+irq+softirq
        long totalTime1 = getTime(map1);
        System.out.println(totalTime1 + "...........................totalTime1.");
        //获取idleTime1
        long idleTime1 = Long.parseLong(map1.get("idle"));
        System.out.println(idleTime1 + "...................idleTime1");
        //间隔360ms
        try {
            Thread.sleep(360);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //采样第二次cpu信息快照
        Map<String, String> map2 = getMap();
        long totalTime2 = getTime(map2);
        System.out.println(totalTime2 + "............................totalTime2");
        //获取idleTime1
        long idleTime2 = Long.parseLong(map2.get("idle"));
        System.out.println(idleTime2 + "................idleTime2");

        //得到cpu的使用率
        float cpuRate = 100 * ((totalTime2 - totalTime1) - (idleTime2 - idleTime1)) / (totalTime2 - totalTime1);

        return cpuRate;
    }

    //得到cpu信息
    public static long getTime(Map<String, String> map) {
        long totalTime = Long.parseLong(map.get("user")) + Long.parseLong(map.get("nice"))
                + Long.parseLong(map.get("system")) + Long.parseLong(map.get("idle"))
                + Long.parseLong(map.get("iowait")) + Long.parseLong(map.get("irq"))
                + Long.parseLong(map.get("softirq"));
        return totalTime;
    }

    //采样CPU信息快照的函数，返回Map类型
    public static Map<String, String> getMap() {
        String[] cpuInfos = null;
        //读取cpu信息文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")));
            String load = br.readLine();
            br.close();
            cpuInfos = load.split(" ");
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("线程异常");
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("user", cpuInfos[2]);
        map.put("nice", cpuInfos[3]);
        map.put("system", cpuInfos[4]);
        map.put("idle", cpuInfos[5]);
        map.put("iowait", cpuInfos[6]);
        map.put("irq", cpuInfos[7]);
        map.put("softirq", cpuInfos[8]);
        return map;
    }

}
