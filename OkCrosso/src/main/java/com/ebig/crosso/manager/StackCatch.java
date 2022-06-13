package com.ebig.crosso.manager;

import com.ebig.crosso.bean.aop.StackDetails;
import com.ebig.crosso.utils.CrossoStackUtils;

import java.util.Timer;
import java.util.TimerTask;

public class StackCatch {
    private Timer timer;
    public StackCatch() {
    }
    public void start(long stackTrackFrequency){
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CrossoDataAPI.getInstance().saveStackTrace(Thread.currentThread().getName(),new StackDetails(CrossoStackUtils.getStackTrace()));
            }
        },stackTrackFrequency,stackTrackFrequency);
    }
}
