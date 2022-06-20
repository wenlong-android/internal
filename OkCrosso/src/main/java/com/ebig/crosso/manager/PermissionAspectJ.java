package com.ebig.crosso.manager;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.ebig.annotation.Permission;
import com.ebig.annotation.ThreadMain;
import com.ebig.crosso.bean.aop.AopCrashEntity;
import com.ebig.crosso.utils.CrossoPermission;
import com.ebig.crosso.utils.CrossoStackUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;
@Aspect
public class PermissionAspectJ {
    @Pointcut("within(@com.ebig.annotation.Permission *)")
    public void withinAnnotatedClass() {
    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    @Pointcut("execution(@com.ebig.annotation.Permission * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }  //方法切入点

    @Around("method() && @annotation(permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {
        CrossoPermission.permission(permission.value())
                .callback(new CrossoPermission.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
//                        for (int i = 0; i < permissionsGranted.size(); i++) {
//                            ELog.print("已经成功获取权限：" + permissionsGranted.get(i));
//                        }
                        try {
                            joinPoint.proceed();//获得权限，执行原方法
                           // ELog.print("已经成功获取权限：继续执行");
                        } catch (Throwable e) {
                            e.printStackTrace();
                            CrossoDataAPI.getInstance().crash(Thread.currentThread().getName(),new AopCrashEntity(CrossoStackUtils.getInfo(e)));
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                      //  ELog.print("权限申请被拒绝:" + listToString(permissionsDenied));
                        if (joinPoint.getThis() instanceof Activity) {
                            Activity activity = (Activity) joinPoint.getThis();
                            rejectMsg(activity);
                        } else if (joinPoint.getThis() instanceof Fragment) {
                            Fragment fragment = (Fragment) joinPoint.getThis();
                            rejectMsg(fragment.getActivity());
                        }

                    }
                })
                .request();
    }

    @ThreadMain
    private void rejectMsg(Context context) {
        Toast.makeText(context,"拒绝权限申请后无法正常使用功能",Toast.LENGTH_LONG).show();
    }


    public static String listToString(final List<String> list) {
        return listToString(list, ",");
    }

    public static String listToString(final List<String> list, final String separator) {
        if (list == null || list.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}


