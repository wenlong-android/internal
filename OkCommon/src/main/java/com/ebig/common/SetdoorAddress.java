package com.ebig.common;

import androidx.annotation.IntDef;

/*独立控制开门*/
@IntDef({
        SetdoorAddress.defaultDoor,/*默认*/
        SetdoorAddress.leftDoor,/*左门*/
        SetdoorAddress.rightDoor,/*右门*/

})

public @interface SetdoorAddress {
    final static int defaultDoor = 0;
    final static int leftDoor = 1;
    final static int rightDoor = 2;
}
