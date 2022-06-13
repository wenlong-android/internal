package com.ebig.socket.dispatchWrite.scander;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BaseScanApiBase;

public class BaseScanStratApiBase implements BaseScanApiBase {
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        return "027e";
    }
}
