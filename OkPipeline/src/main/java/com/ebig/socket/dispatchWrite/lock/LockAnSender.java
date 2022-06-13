package com.ebig.socket.dispatchWrite.lock;
import com.ebig.socket.dispatchWrite.base.AnSender;
import com.ebig.socket.entity.TypeConstance;

public class LockAnSender extends AnSender implements LockSenderIdl {
    @Override
    public String getOrder() {
        return TypeConstance.T_DOOR_SEND;
    }
  @Override
    public LockAnSender openFrontDoor() {
        return (LockAnSender) this.with(new LockOpenFontDoorApiBase());
    }

    @Override
    public LockAnSender openBackDoor() {
        return (LockAnSender)this.with(new BaseLockOpenBackDoorApiBase());
    }

    @Override
    public LockAnSender openBothDoor() {
        return (LockAnSender)this.with(new BaseLockOpenBothDoorApiBase());
    }

    @Override
    public LockAnSender openOn(int index, String action) {
        return (LockAnSender)this.with(new BaseLockOpenPostionDoorApiBase(index,action));
    }

}
