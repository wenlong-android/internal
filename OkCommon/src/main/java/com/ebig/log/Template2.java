package com.ebig.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Template2 {
    private static boolean isDebug = false;

    public static void setDebug(boolean debug) {
        Template2.isDebug = debug;
        init("ebig", false);
    }

    private static void init(final String tag, boolean showThreadInfo) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(showThreadInfo)  // (Optional) Whether to show thread info or not. Default true
                // .methodCount(0)         // (Optional) How many method line to show. Default 2
                // .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                //  .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(tag)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    public static void debug(String debug) {
        if (isDebug)
            Logger.d(debug);
    }

    public static void warning(String warning) {
        if (isDebug)
            Logger.w(warning);
    }

    public static void verbose(String verbose) {
        if (isDebug)
            Logger.v(verbose);
    }

    public static void print(String verbose) {
        if (isDebug)
            Logger.i(verbose);
    }

    public static void print(String tag, String verbose) {
        if (isDebug)
            Logger.i(tag + " " + verbose);
    }

    public static void error(String error) {
        if (isDebug)
            Logger.e(error);
    }

    public static void map(Map map) {
        if (isDebug)
            Logger.d(map);
    }

    public static void printSet(Set set) {
        if (isDebug)
            Logger.d(set);
    }

    public static void printList(List list) {
        if (isDebug)
            Logger.d(list);
    }

    public static void printArrays(int[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printArrays(String[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printArrays(long[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printArrays(double[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printArrays(float[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printArrays(Object[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printArrays(boolean[] arrays) {
        if (isDebug)
            Logger.d(arrays);
    }

    public static void printJson(String json) {
        if (isDebug)
            Logger.json(json);
    }

    public static void printXml(String xml) {
        if (isDebug)
            Logger.xml(xml);
    }
}
