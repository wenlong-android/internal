package com.ebig.socket.idl;

public interface ScannerListenner extends SenderListenner {
    void onScanResult(String result);
}
