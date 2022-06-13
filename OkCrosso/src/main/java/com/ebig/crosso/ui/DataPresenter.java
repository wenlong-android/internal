package com.ebig.crosso.ui;

import android.content.Context;

import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.utils.CroThread;
import com.ebig.log.ELog;

import java.util.ArrayList;
import java.util.List;

public class DataPresenter implements BasePresenter {
    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;
    private List<AopDbInfo> AopDbInfoList;
    private List<AopDbInfo> resultList;
    private AopDataCall<List<AopDbInfo>> listAopDataCall;


    @Override
    public void getDataByDate(Context context, long start, long end, List<String> typeList, AopDataCall<List<AopDbInfo>> call) {

    }






    @Override
    public void addInfoListenner(AopDataCall<List<AopDbInfo>> listAopDataCall) {
        this.listAopDataCall = listAopDataCall;
    }
}
