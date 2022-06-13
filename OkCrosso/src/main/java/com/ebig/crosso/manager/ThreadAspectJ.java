package com.ebig.crosso.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ebig.annotation.ThreadComputation;
import com.ebig.annotation.ThreadIo;
import com.ebig.annotation.ThreadMain;
import com.ebig.annotation.ThreadNew;
import com.ebig.log.ELog;
import com.ebig.crosso.utils.CroThread;
import com.ebig.utils.StrUtils;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


@Aspect
public class ThreadAspectJ {
    @Pointcut("execution(@com.ebig.annotation.ThreadIo * *(..))")
    public void threadIoCheck() {
    }

    @Around("threadIoCheck()")
    public void threadIo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start=System.currentTimeMillis();
        String methodName="";
        String threadName="";
        long delay=0;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        ThreadIo newThread = signature.getMethod().getAnnotation(ThreadIo.class);
        if (newThread != null) {
            String user = newThread.user();
            String module = newThread.lib();
            delay=newThread.delay();
            methodName = signature.getName();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("user",user);
            jsonObject.put("module",module);
            jsonObject.put("methodName",methodName);
            threadName=jsonObject.toString();
        }
        String finalThreadName = threadName;
        Flowable.timer(delay, TimeUnit.MILLISECONDS).create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
             //   ELog.print("当前线程：" + Thread.currentThread().getName());
                flowableEmitter.onNext(finalThreadName);
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String threadName) {
                        try {
//                            ELog.print("转换后线程：" + Thread.currentThread().getName());
//                            ELog.print("是否UI线程：" + CroThread.isMainThread());
                            Thread.currentThread().setName(threadName);
                            proceedingJoinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }finally {
                        //    ELog.print(finalThreadName+" 耗时："+(System.currentTimeMillis()-start));
                        }
                    }
                });
    }


    @Pointcut("execution(@com.ebig.annotation.ThreadComputation * *(..))")
    public void threadComputationCheck() {
    }

    @Around("threadComputationCheck()")
    public void threadComputation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start=System.currentTimeMillis();
        String methodName="";
        String threadName="";
        long delay=0;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        ThreadComputation newThread = signature.getMethod().getAnnotation(ThreadComputation.class);
        if (newThread != null) {
            String user = newThread.user();
            String module = newThread.lib();
            delay=newThread.delay();
            methodName = signature.getName();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("user",user);
            jsonObject.put("module",module);
            jsonObject.put("methodName",methodName);
            threadName=jsonObject.toString();
        }
        String finalThreadName = threadName;
        Flowable.timer(delay, TimeUnit.MILLISECONDS).create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
               // ELog.print("当前线程：" + Thread.currentThread().getName());
                flowableEmitter.onNext(finalThreadName);
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.computation())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String threadName) {
                        try {
                      //      ELog.print("转换后线程：" + Thread.currentThread().getName());
                         //   ELog.print("是否UI线程：" + CroThread.isMainThread());
                            Thread.currentThread().setName(threadName);
                            proceedingJoinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }finally {
                         //   ELog.print(finalThreadName+" 耗时："+(System.currentTimeMillis()-start));
                        }
                    }
                });
    }



    @Pointcut("execution(@com.ebig.annotation.ThreadNew * *(..))")
    public void threadNewCheck() {
    }

    @Around("threadNewCheck()")
    public void threadNew(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start=System.currentTimeMillis();
        String methodName="";
        String threadName="";
        long delay=0;
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        ThreadNew newThread = signature.getMethod().getAnnotation(ThreadNew.class);
        if (newThread != null) {
            String user = newThread.user();
            String module = newThread.lib();
            delay=newThread.delay();
            methodName = signature.getName();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("user",user);
            jsonObject.put("module",module);
            jsonObject.put("methodName",methodName);
            threadName=jsonObject.toString();
        }
        String finalThreadName = threadName;
        Flowable.timer(delay, TimeUnit.MILLISECONDS).create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
                //ELog.print("当前线程：" + Thread.currentThread().getName());
                flowableEmitter.onNext(finalThreadName);
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String threadName) {
                        try {
                          //  ELog.print("转换后线程：" + Thread.currentThread().getName());
                          //  ELog.print("是否UI线程：" + CroThread.isMainThread());
                            Thread.currentThread().setName(threadName);
                            proceedingJoinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }finally {
                          //  ELog.print(finalThreadName+" 耗时："+(System.currentTimeMillis()-start));
                        }
                    }
                });
    }



    @Pointcut("execution(@com.ebig.annotation.ThreadMain * *(..))")
    public void threadMainCheck() {
    }

    @Around("threadMainCheck()")
    public void threadMain(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start=System.currentTimeMillis();
        String methodName="";
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        methodName=signature.getName();
        ThreadMain main = signature.getMethod().getAnnotation(ThreadMain.class);
        long delay=main.delay();
        String finalMethodName = methodName;
        Flowable.timer(delay, TimeUnit.MILLISECONDS).create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
             //   ELog.print("当前线程：" + Thread.currentThread().getName());
                flowableEmitter.onNext("");
                flowableEmitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        try {
                          //  ELog.print("是否UI线程：" + CroThread.isMainThread());
                            proceedingJoinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }finally {
                         //   ELog.print(finalMethodName +" 耗时："+(System.currentTimeMillis()-start));
                        }
                    }
                });
    }
}
