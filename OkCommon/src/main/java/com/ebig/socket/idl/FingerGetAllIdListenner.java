package com.ebig.socket.idl;

import java.util.List;

public interface FingerGetAllIdListenner extends SenderListenner {
    void onResult(List<String> result);
}
