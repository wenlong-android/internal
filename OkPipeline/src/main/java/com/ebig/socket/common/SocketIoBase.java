package com.ebig.socket.common;

import com.ebig.socket.dispatch.CmdQueue;
import com.ebig.socket.dispatch.RquestCache;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.dispatchRead.Chain4Handler;
import com.ebig.socket.dispatchRead.handler.Handler4Answer;
import com.ebig.socket.dispatchRead.handler.Handler4BackLight;
import com.ebig.socket.dispatchRead.handler.Handler4CardReader;
import com.ebig.socket.dispatchRead.handler.Handler4ColorL;
import com.ebig.socket.dispatchRead.handler.Handler4Finger;
import com.ebig.socket.dispatchRead.handler.Handler4Idle;
import com.ebig.socket.dispatchRead.handler.Handler4Lcd;
import com.ebig.socket.dispatchRead.handler.Handler4Lock;
import com.ebig.socket.dispatchRead.handler.Handler4Scale;
import com.ebig.socket.dispatchRead.handler.Handler4Scander;
import com.ebig.socket.entity.TypeConstance;
import com.ebig.socket.dispatchRead.handler.Handler4TH;
import com.ebig.socket.dispatchRead.handler.Handler4Tail;

import java.util.Timer;
import java.util.TimerTask;

public abstract class SocketIoBase {
    protected static CmdQueue cmdQueue;
    private static Timer timer = null;


    /*创建定时器*/
    protected void createTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                               @Override
                               public void run() {
                                   onSchedule();
                               }
                           }, TypeConstance.QUEUE_TIMER_DELAY
                    , TypeConstance.QUEUE_TIMER_PERIOD);
        }
    }

    protected Chain4Handler makeDispatchHandleList() {
        Chain4Handler chain4Handler = new Chain4Handler();
        chain4Handler
                .addHandler(new Handler4Idle())//心跳
                .addHandler(new Handler4TH())//温湿度
                .addHandler(new Handler4Answer())//应答
                .addHandler(new Handler4Lock())//门锁
                .addHandler(new Handler4CardReader())//读卡器
                .addHandler(new Handler4ColorL())//三色灯
                .addHandler(new Handler4BackLight())//背光灯
                .addHandler(new Handler4Finger())//指静脉
                .addHandler(new Handler4Lcd())//LCD
                .addHandler(new Handler4Scale())//
                .addHandler(new Handler4Scander())//扫描头
                .addHandler(new Handler4Tail());//尾部

        return chain4Handler;
    }

    protected abstract void onSchedule();

    protected abstract void accept(String uuid, String host, String cmd);

    /**
     * 缓存请求task，待后期返回带流水号可对应回调
     * @param answerTask
     */
    protected void cacheAnswerTask(CmdRequestInfo answerTask) {
        RquestCache.addRequest(answerTask.hashCode() + "", answerTask);
    }

    protected abstract void addFormatCmd2TaskPipeline(CmdRequestInfo task);

    protected abstract void createCmdTaskChainAndHandleIt(CmdResultInfo rawInfo);
}
