package com.ebig.crosso.utils;

import com.ebig.crosso.bean.aop.AopClickEntity;
import com.ebig.crosso.bean.aop.CmdDetails;
import com.ebig.crosso.bean.aop.ExceptionDetails;
import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.manager.type.RecordType;
import com.ebig.socket.entity.CTypeRead;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdType;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class CroStrUtils {
    public static String getType(String type) {
        String str = "未匹配类型";
        switch (type) {
            case RecordType.aopUserClick:
                str = "行为日志";
                break;

            case RecordType.aopHardWare:
                str = "硬件函数";
                break;

            case RecordType.aopServer:
                str = "服务器函数";
                break;

            case RecordType.aopCrash:
                str = "奔溃日志";
                break;

            case RecordType.aopConsume:
                str = "耗时日志";
                break;

            case RecordType.exception:
                str = "exception日志";
                break;

            case RecordType.ram:
                str = "内存日志";
                break;
            case RecordType.leak:
                str = "内存泄漏";
                break;

            case RecordType.block:
                str = "卡顿检测";
                break;

            case RecordType.stack:
                str = "堆栈信息";
                break;
        }
        return str;
    }

    public static String getShortInfo(AopDbInfo info) {
        //行为日志
        if (info.getEvent().equals(RecordType.aopUserClick)) {
            AopClickEntity entity=new Gson().fromJson(info.getDetail(),AopClickEntity.class);
            return "[ 控件:"+entity.getViewContent()+" ]  [ 位置:"+entity.getActivity()+" ]";
        } else if (info.getEvent().equals(RecordType.aopHardWare)) {
            //硬件函数
            CmdDetails details=new Gson().fromJson(info.getDetail(),CmdDetails.class);
            if (details.isCmdSend()){
                CmdRequestInfo requestInfo=new Gson().fromJson(details.getContent(),CmdRequestInfo.class);
                return "[ 类型:"+ CmdType.get(requestInfo.order(),requestInfo.getData())+" ]  [ 指令:"+requestInfo.getCmd()+" ]  [ 数据:"+requestInfo.getData()+" ]";
            }else {
                CmdResultInfo reciveInfo=new Gson().fromJson(details.getContent(),CmdResultInfo.class);
                return "[ 类型:"+ CmdType.get(reciveInfo.getOrder(),reciveInfo.getData())+" ]  [ 指令:"+reciveInfo.getCmd()+" ]  [ 数据:"+reciveInfo.getData()+" ]";
            }
        } else if (info.getEvent().equals(RecordType.aopServer)) {
            //服务器函数
        } else if (info.getEvent().equals(RecordType.aopCrash)) {
            //异常日志

        } else if (info.getEvent().equals(RecordType.aopConsume)) {
            //耗时日志
        } else if (info.getEvent().equals(RecordType.exception)) {
            //exception日志
            ExceptionDetails details=new Gson().fromJson(info.getDetail(),ExceptionDetails.class);
            return "线程："+details.getThreadName()+"\n详情："+details.getStack();
        } else if (info.getEvent().equals(RecordType.ram)) {
            //内存日志
        } else if (info.getEvent().equals(RecordType.stack)) {
            //堆栈信息
            return "自定义堆栈信息，有未知异常可进入查看";
        }
        return info.getDetail();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    private static String getJsonObj(String detail, String key) {
        String str = "";
        JSONObject jsonObject = null;
        try {

            jsonObject = new JSONObject(detail);
            if (jsonObject.has(key))
                str = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getDetails(AopDbInfo info) {
        String str = "";
        switch (info.getEvent()) {
//            case RecordType.all:
//                str = "所有日志";
//                break;

            case RecordType.aopUserClick:
                str = readJSONObject(info.getDetail());
                break;

            case RecordType.aopHardWare:
                str = info.getDetail();
                break;

            case RecordType.aopServer:
                str = info.getDetail();
                break;

            case RecordType.aopCrash:
                str = info.getDetail();
                break;

            case RecordType.aopConsume:
                str = info.getDetail();
                break;

            case RecordType.exception:
                str = info.getDetail();
                break;

            case RecordType.ram:
                // str = "内存日志";
                str = info.getDetail();
                break;

            case RecordType.stack:
                str = info.getDetail();
                break;
        }
        return str;

    }

    public static String readJSONObject(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                stringBuilder.append(key + " = " + jsonObject.getString(key) + "\n");
            }
            return stringBuilder.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}



