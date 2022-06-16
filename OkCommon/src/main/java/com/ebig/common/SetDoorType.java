package com.ebig.common;

import androidx.annotation.IntDef;

/*开门方式*/
@IntDef({
        SetDoorType.sigleDoor,/*单开门*/
        SetDoorType.doubleDoor,/*双开门*/
})

public @interface SetDoorType {
    final static int sigleDoor = 0;
    final static int doubleDoor = 1;
}
