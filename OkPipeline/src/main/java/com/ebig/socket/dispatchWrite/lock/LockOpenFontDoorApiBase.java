package com.ebig.socket.dispatchWrite.lock;

import com.ebig.socket.dispatchWrite.base.BaseCmdApi;

/*打开前门*/
public class LockOpenFontDoorApiBase implements BaseCmdApi {
    //7E 06 00 03 FF FF FF 01 01 14 72 7F
    @Override
    public String getCmd() {
        return "0101";
    }
}
