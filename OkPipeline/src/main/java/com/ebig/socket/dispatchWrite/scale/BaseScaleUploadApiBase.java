package com.ebig.socket.dispatchWrite.scale;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BaseScaleApiBase;

public class BaseScaleUploadApiBase implements BaseScaleApiBase {
    /**
     * params uploadAll 全部上传，否则只上传有变换的数据
     */
    private boolean uploadAll = false;

    public BaseScaleUploadApiBase() {
    }

    public BaseScaleUploadApiBase(boolean uploadAll) {
        this.uploadAll = uploadAll;
    }
//0x21,0x00,0x00,0x00,0x00(0x00无效)
//0x21,0x01,0x00,0x00,0x00(0x01上传有变化的数据)
//0x21,0x02,0x00,0x00,0x00(0x02全部数据上传遍)

    /*电子秤-全部上传*/
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        if (uploadAll) {
            return "2102000000";
        }
        return "2101000000";
    }
}
