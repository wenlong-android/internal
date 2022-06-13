package com.ebig.crosso.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class CrossoStackUtils {
    public static String getInfo(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        // 得到错误信息
        String result = writer.toString();
        if (result.length()>65534){
              result=result.substring(0,65534);
        }
        stringBuilder.append(result);
        //ELog.print("获取堆栈信息：" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String getInfo(Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        // 得到错误信息
        String result = writer.toString();
        if (result.length()>65534){
            result=result.substring(0,65534);
        }
        stringBuilder.append(result);

        //ELog.print("获取堆栈信息：" + stringBuilder.toString());
        return stringBuilder.toString();
    }
    public static String getStackTrace() {
        Exception e = new Exception("获取系统堆栈信息");
        StringBuilder stringBuilder = new StringBuilder();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        // 得到错误信息
        String result = writer.toString();
        if (result.length()>65534){
            result=result.substring(0,65534);
        }
        stringBuilder.append(result);
       // ELog.print("获取堆栈信息：" + stringBuilder.toString());
        return stringBuilder.toString();
    }
}
