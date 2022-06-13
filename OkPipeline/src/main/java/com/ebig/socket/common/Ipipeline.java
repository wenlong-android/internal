package com.ebig.socket.common;


import com.ebig.socket.dispatchWrite.base.ICommand;
import com.ebig.socket.idl.PipeServerCall;

public interface Ipipeline {
    public ICommand commander();
    public void addListenner(Class clazz);
}
