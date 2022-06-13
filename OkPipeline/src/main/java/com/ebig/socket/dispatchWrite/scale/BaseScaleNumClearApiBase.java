package com.ebig.socket.dispatchWrite.scale;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BaseScaleApiBase;

/*电子秤-清零*/
public class BaseScaleNumClearApiBase implements BaseScaleApiBase {
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        //0x08,0x00,0x00,0x00,0x00
        return "0800000000";
    }
}
