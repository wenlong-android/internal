package com.ebig.common;

import androidx.annotation.IntDef;

/*独立控制开门*/
@IntDef({
        SetOpenAlone.enable,/*启用*/
        SetOpenAlone.disable,/*禁用*/
})

public @interface SetOpenAlone {
    final static int enable = 0;
    final static int disable = 1;
}
