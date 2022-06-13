package com.ebig.socket.dispatchWrite.lcd;

import com.ebig.socket.entity.TypeConstance;
import com.ebig.socket.dispatchWrite.base.AnSender;

public class LcdAnSender extends AnSender implements LcdSenderIdl{
    @Override
    public String getOrder() {
        return TypeConstance.T_LCD_SEND;
    }

    @Override
    public LcdAnSender show(LcdParam param) {
        return (LcdAnSender)this.with(new LcdShowApiBase(param));
    }
}
