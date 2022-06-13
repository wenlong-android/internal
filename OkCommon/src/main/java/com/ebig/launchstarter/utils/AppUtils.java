package com.ebig.launchstarter.utils;

import android.app.Activity;
import android.content.Context;

import com.ebig.utils.AppGlobals;


public class AppUtils {
    public static Context getContext(){
        return AppGlobals.getApplication();
    }
    private static int screenWidth=0;
    private static int screenHight=0;

    public static void initParams(Activity activity){
        getScreenWidth(activity);
    }
    private static void getScreenWidth(Activity context){
        screenWidth= (int) (context.getWindowManager().getDefaultDisplay().getWidth());
        screenHight=(int) (context.getWindowManager().getDefaultDisplay().getHeight());
    }

    public static int getScreenWidth() {
        return (int)screenWidth;
    }

    public static int getScreenHight() {
        return (int)screenHight;
    }
}
