package com.ebig.common;

import androidx.annotation.IntDef;

/*独立控制开门*/
@IntDef({
        SetDoorLevel.L0,/*0*/
        SetDoorLevel.L1,/*1*/
        SetDoorLevel.L2,/*2*/
        SetDoorLevel.L3,/*3*/
        SetDoorLevel.L4,/*4*/
        SetDoorLevel.L5,/*5*/
})

public @interface SetDoorLevel {
    final static int L0 = 0;
    final static int L1 = 1;
    final static int L2 = 2;
    final static int L3 = 3;
    final static int L4 = 4;
    final static int L5 = 5;
}
