package com.ebig.common;

import androidx.annotation.IntDef;

/*独立控制开门*/
@IntDef({
        SetOpenWay.sigleOpen,/*启用*/
        SetOpenWay.doubleOpen,/*禁用*/
})

public @interface SetOpenWay {
    final static int sigleOpen = 0;
    final static int doubleOpen = 1;
}
