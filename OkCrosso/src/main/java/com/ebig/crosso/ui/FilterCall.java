package com.ebig.crosso.ui;


import com.ebig.crosso.manager.type.RecordType;

import java.util.List;

public interface FilterCall {
    void onSelectResult(long start, long end, List<String> list );
    void changeTime();
    void finish();
}
