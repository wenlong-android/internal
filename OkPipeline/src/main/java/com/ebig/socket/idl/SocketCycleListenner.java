package com.ebig.socket.idl;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class SocketCycleListenner implements LifecycleObserver {
 private SocketLifeActionCall actionCall;

    public SocketCycleListenner() {
    }

    public void setListenner(SocketLifeActionCall actionCall) {
        this.actionCall = actionCall;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        actionCall.onCreate();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(){
        actionCall.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestory(){
        actionCall.onDestory();
    }
}
