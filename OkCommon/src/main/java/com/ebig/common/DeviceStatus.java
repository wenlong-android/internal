package com.ebig.common;

import androidx.annotation.IntDef;


@IntDef({
        DeviceStatus.enable,/*启用*/
        DeviceStatus.disable,/*禁用*/
})

public @interface DeviceStatus {
    final static int enable = 1;
    final static int disable = 0;
}
