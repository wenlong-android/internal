package com.ebig.socket.dispatchWrite.backLight;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BackLightApiBase;
import com.ebig.socket.entity.TypeConstance;

public class BackLightOffApiBase implements BackLightApiBase {
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        return TypeConstance.C_00;
    }
}
