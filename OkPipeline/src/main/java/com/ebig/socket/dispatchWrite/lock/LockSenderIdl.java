package com.ebig.socket.dispatchWrite.lock;

public interface LockSenderIdl {

    public LockAnSender openFrontDoor();

    public LockAnSender openBackDoor();

    public LockAnSender openBothDoor();

    public LockAnSender openOn(int index, String action);
}
