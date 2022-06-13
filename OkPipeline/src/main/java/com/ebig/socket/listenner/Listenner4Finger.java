package com.ebig.socket.listenner;

import com.ebig.idl.CommonCall;

import java.util.List;

public class Listenner4Finger {
    /**
     * 获取所有指静脉id
     */
    private static CommonCall<List<String>> getAllFingerId;
    public static void setGetAllFingerId(CommonCall<List<String>> call) {
         getAllFingerId = call;
    }
    public static void onGetAllFingerCall(List<String> list){
        if (getAllFingerId!=null){
            getAllFingerId.onCommonCall(list);
        }
    }

    /**
     * 取消注册
     */
    private static CommonCall<Boolean> registCancle;

    public static void addRegistCancle(CommonCall<Boolean> registCancle) {
        Listenner4Finger.registCancle = registCancle;
    }
    public static void onRegistCancle(boolean cancle){
        if (registCancle!=null){
            registCancle.onCommonCall(cancle);
        }
    }

    /**
     * 注册手指
     */
    private static IFingerRegistListenner registListenner;

    public static void setRegistListenner(IFingerRegistListenner registListenner) {
        Listenner4Finger.registListenner = registListenner;
    }


    public static void onStartAndPutFinger2Regiest(){
        if (registListenner!=null){
            registListenner.startAndPutFinger2Regiest();
        }
    }
    public static void firstRegistSuccess(){
        if (registListenner!=null){
            registListenner.firstRegistSuccess();
        }
    }

    public static void secondTimePutFinger2Regiest(){
        if (registListenner!=null){
            registListenner.secondTimePutFinger2Regiest();
        }
    }
    public static void secondRegistSuccess(){
        if (registListenner!=null){
            registListenner.secondRegistSuccess();
        }
    }


    public static void thirdAndPutFinger2Regiest(){
        if (registListenner!=null){
            registListenner.thirdAndPutFinger2Regiest();
        }
    }
    public static void thirdRegistSuccess(){
        if (registListenner!=null){
            registListenner.thirdRegistSuccess();
        }
    }
    public static void tooMuchDifferenceRegisterAgain(){
        if (registListenner!=null){
            registListenner.tooMuchDifferenceRegisterAgain();
        }
    }

    public static void registFinallySuccess(){
        if (registListenner!=null){
            registListenner.registFinallySuccess();
        }
    }


    /**
     * 删除指定id
     */
    private static CommonCall<Boolean> delete;

    public static void setDelete(CommonCall<Boolean> delete) {
        Listenner4Finger.delete = delete;
    }

    public static void ondelete(){
        if (delete!=null){
            delete.onCommonCall(true);
        }
    }


    /**
     * 删除指定id
     */
    private static CommonCall<Boolean> deleteAll;

    public static void setDeleteAll(CommonCall<Boolean> deleteAll) {
        Listenner4Finger.deleteAll = deleteAll;
    }

    public static void onDeleteAll(){
        if (deleteAll!=null){
            deleteAll.onCommonCall(true);
        }
    }
}
