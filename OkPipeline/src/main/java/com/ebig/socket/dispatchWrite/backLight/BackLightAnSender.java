package com.ebig.socket.dispatchWrite.backLight;
import com.ebig.socket.dispatchWrite.base.AnSender;
import com.ebig.socket.entity.TypeConstance;

public class BackLightAnSender extends AnSender implements BackLightSenderIdl {

    @Override
    public String getOrder() {
        return TypeConstance.T_BACK_L_SEND;
    }

    @Override
    public BackLightAnSender on() {
        return (BackLightAnSender) this.with(new BackLightOnApiBase());
    }

    @Override
    public BackLightAnSender off() {
        return (BackLightAnSender) this.with(new BackLightOffApiBase());
    }
}
