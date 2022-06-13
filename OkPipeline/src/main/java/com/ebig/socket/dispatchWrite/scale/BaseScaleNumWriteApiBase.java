package com.ebig.socket.dispatchWrite.scale;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BaseScaleApiBase;

/*电子秤-标定*/
public class BaseScaleNumWriteApiBase implements BaseScaleApiBase {
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        //0x09,0x32,0x00,0x00,0x00
        return "0932000000";
    }
}
