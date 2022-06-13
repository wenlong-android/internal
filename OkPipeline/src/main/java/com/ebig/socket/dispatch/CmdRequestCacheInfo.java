package com.ebig.socket.dispatch;

import com.ebig.socket.entity.CmdRequestInfo;

public class CmdRequestCacheInfo {
    private long t;
    private CmdRequestInfo info;

    public CmdRequestCacheInfo(long t, CmdRequestInfo info) {
        this.t = t;
        this.info = info;
    }

    public long t() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public CmdRequestInfo get() {
        return info;
    }

    public void setInfo(CmdRequestInfo info) {
        this.info = info;
    }
}
