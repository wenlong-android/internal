package com.ebig.crosso.ui;

import android.content.Context;


import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.manager.type.RecordType;

import java.util.List;

public interface BasePresenter {
    void getDataByDate(Context context, long  start,long end, List<String> typeList, AopDataCall<List<AopDbInfo>> call);
    void addInfoListenner(AopDataCall<List<AopDbInfo>> listAopDataCall);
}
