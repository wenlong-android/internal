package com.ebig.crosso.ui;

import java.util.List;

public class FliterResult {
    private static FilterCall call;
    private static class L{
        private static FliterResult result=new FliterResult();
    }
    public static FliterResult l(){
        return L.result;
    }

    public   void setCall(FilterCall c) {
        FliterResult.call = c;
    }
    public void post(long start, long end, List<String> list){
        if (call!=null){
            call.onSelectResult(start,end,list);
        }
    }
}
