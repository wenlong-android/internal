package com.ebig.crosso.manager.type;

import com.ebig.crosso.manager.db.CrossoDb;
import com.ebig.utils.AppGlobals;

import java.util.List;

public class AopInfoUtils {
    public static List<AopDbInfo> getAll(){
        return CrossoDb.with(AppGlobals.getApplication()).getAop().atAll("2022-06-20");
    }
}
