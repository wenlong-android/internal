package com.ebig.socket.dispatchWrite.scale;
import com.ebig.socket.dispatchWrite.base.AnSender;
import com.ebig.socket.entity.TypeConstance;

public class ScaleAnSender extends AnSender implements ScaleSenderIdl{
    @Override
    public String getOrder() {
        return TypeConstance.T_SCALE_SEND;
    }

    @Override
    public ScaleAnSender clear() {
        return  (ScaleAnSender)this.with(new BaseScaleNumClearApiBase());
    }

    @Override
    public ScaleAnSender write() {
        return  (ScaleAnSender)this.with(new BaseScaleNumWriteApiBase());
    }

    @Override
    public ScaleAnSender read() {
        return (ScaleAnSender)this.with(new BaseScaleGetNumApiBase());
    }

    @Override
    public ScaleAnSender upload(boolean uoloadAll) {
        return  (ScaleAnSender)this.with(new BaseScaleUploadApiBase(uoloadAll));
    }


}
