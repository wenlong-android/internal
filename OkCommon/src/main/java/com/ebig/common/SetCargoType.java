package com.ebig.common;

import androidx.annotation.IntDef;

/*设置为主柜*/
@IntDef({
        SetCargoType.normal,/*普通柜体*/
        SetCargoType.mainCargo,/*主柜体*/
})

public @interface SetCargoType {
    final static int normal = 1;
    final static int mainCargo = 0;
}
