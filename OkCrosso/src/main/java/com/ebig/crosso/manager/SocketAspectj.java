package com.ebig.crosso.manager;


import com.ebig.crosso.bean.aop.CmdDetails;
import com.ebig.crosso.bean.aop.ExceptionDetails;
import com.ebig.crosso.utils.CroThread;
import com.ebig.crosso.utils.CrossoStackUtils;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.log.ELog;
import com.google.gson.Gson;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@SuppressWarnings("unused")
public class SocketAspectj {
    @Around("execution(* com.ebig.socket.common.SocketIoManager.createCmdTaskChainAndHandleIt(com.ebig.socket.entity.CmdResultInfo))")
    public Object onCmdRecive(final ProceedingJoinPoint joinPoint) throws Throwable {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                CmdResultInfo info = (CmdResultInfo) joinPoint.getArgs()[0];
                if (!info.getOrder().equals("be")){
                    ELog.print("onCmdRecive:"+info.toString());
                }
                CrossoDataAPI.getInstance().saveCmd(Thread.currentThread().getName(),new CmdDetails(false,new Gson().toJson(info)));
            }
        });

        return joinPoint.proceed();

    }
    @Around("execution(* com.ebig.socket.common.SocketIoManager.addFormatCmd2TaskPipeline(com.ebig.socket.entity.CmdRequestInfo))")
    public Object onCmdSend(final ProceedingJoinPoint joinPoint) throws Throwable {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                CmdRequestInfo info = (CmdRequestInfo) joinPoint.getArgs()[0];
                ELog.print("onCmdSend:"+info.toString());
                CrossoDataAPI.getInstance().saveCmd(Thread.currentThread().getName(),new CmdDetails(true,new Gson().toJson(info)));
            }
        });

        return joinPoint.proceed();

    }

}
