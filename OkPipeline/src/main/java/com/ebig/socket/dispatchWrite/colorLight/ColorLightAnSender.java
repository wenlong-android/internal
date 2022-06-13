package com.ebig.socket.dispatchWrite.colorLight;

import com.ebig.socket.entity.TypeConstance;
import com.ebig.socket.dispatchWrite.base.AnSender;

public class ColorLightAnSender extends AnSender implements ColorLightIdl{
    @Override
    public ColorLightAnSender config(CLightParam param) {
        return (ColorLightAnSender)this.with(new ColorLConfigApiBase(param));
    }

    @Override
    public String getOrder() {
        return TypeConstance.T_COLOR_L_SEND;
    }
}
