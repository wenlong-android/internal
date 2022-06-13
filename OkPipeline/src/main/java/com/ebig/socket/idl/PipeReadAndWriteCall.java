package com.ebig.socket.idl;

import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdRequestInfo;

public interface PipeReadAndWriteCall extends PipeBase{
    void send(CmdRequestInfo info);
    void receive(CmdResultInfo rawInfo);
}
