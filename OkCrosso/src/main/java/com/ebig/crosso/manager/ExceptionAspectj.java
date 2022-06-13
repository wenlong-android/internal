package com.ebig.crosso.manager;


import com.ebig.crosso.bean.aop.ExceptionDetails;
import com.ebig.crosso.utils.CrossoStackUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@SuppressWarnings("unused")
public class ExceptionAspectj {
        @Before("handler(java.lang.Exception+)&&args(e)&&within(com.ebig..*)")
        public void handlerIllegalArgumentException(JoinPoint joinPoint,Exception e) throws Throwable {
            String msg="";
            if (joinPoint.getThis()!=null){
                msg=joinPoint.getThis().toString();
            }
            String threadName=Thread.currentThread().getName();
            CrossoDataAPI.getInstance().exception(threadName,new ExceptionDetails(threadName,msg,CrossoStackUtils.getInfo(e)));
        }
}
