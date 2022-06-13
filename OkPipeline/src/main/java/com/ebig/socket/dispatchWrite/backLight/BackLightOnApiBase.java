package com.ebig.socket.dispatchWrite.backLight;
import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BackLightApiBase;
import com.ebig.socket.entity.TypeConstance;

public class BackLightOnApiBase implements BackLightApiBase {
    /**
     * ampLightOn 0x01
     * @return
     */
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        return TypeConstance.C_01;
    }
}
