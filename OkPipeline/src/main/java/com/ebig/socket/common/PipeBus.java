package com.ebig.socket.common;



import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.idl.PipeCall;
import com.ebig.socket.idl.PipeReadAndWriteCall;
import com.ebig.socket.idl.PipeSocketMonitorCall;
import com.ebig.socket.idl.PipeIdelCall;
import com.ebig.socket.idl.PipeThCall;
import com.ebig.utils.StrUtils;

import java.util.HashMap;

/*事件和数据监听*/
public class PipeBus implements PipeCall, PipeThCall, PipeSocketMonitorCall, PipeReadAndWriteCall {

    private static Ipipeline pipeline;
    private HashMap<String, Object> listenner = new HashMap<>();

    private static class Home {
        private static PipeBus home = new PipeBus();
    }

    public static PipeBus l() {
        return Home.home;
    }

    public PipeBus with() {
        pipeline = new Pipeline.Cosmetic()
                .debug(true)
                .setReadFrequency(30)
                .setWriteFrequency(30)
                .addChatCall(this)
                .adThCall(this)
                .addConnectCall(this)
                .addIdelCall(this)
                .makeUp();
        return this;
    }



    /*事件和数据监听*/
    @Override
    public void send(CmdRequestInfo info) {
        if (listenner.containsKey(PipeReadAndWriteCall.class.getSimpleName())) {
            ((PipeReadAndWriteCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).send(info);
        }
    }

    /*事件和数据监听*/
    @Override
    public void receive(CmdResultInfo rawInfo) {
        if (listenner.containsKey(PipeReadAndWriteCall.class.getSimpleName())) {
            ((PipeReadAndWriteCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).receive(rawInfo);
        }
    }

    /*事件和数据监听*/
    @Override
    public void deviceConnect(String uuid, String ipHost) {
        ELog.print("deviceConnect:"+ipHost);
        if (listenner.containsKey(PipeSocketMonitorCall.class.getSimpleName())) {
            ((PipeSocketMonitorCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).deviceConnect(uuid, ipHost);
        }
    }

    /*事件和数据监听*/
    @Override
    public void deviceDisConnect(String uuid, String ipHost) {
        if (listenner.containsKey(PipeSocketMonitorCall.class.getSimpleName())) {
            ((PipeSocketMonitorCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).deviceDisConnect(uuid, ipHost);
        }
    }

    /*事件和数据监听*/
    @Override
    public void messageRead(String uuid, String ipHost, String cmd) {
        if (listenner.containsKey(PipeSocketMonitorCall.class.getSimpleName())) {
            ((PipeSocketMonitorCall) listenner.get(PipeSocketMonitorCall.class.getSimpleName())).messageRead(uuid, ipHost, cmd);
        }
    }

    /*事件和数据监听*/
    @Override
    public void writeOutTime(String uuid, String ipHost) {
        if (listenner.containsKey(PipeSocketMonitorCall.class.getSimpleName())) {
            ((PipeSocketMonitorCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).writeOutTime(uuid, ipHost);
        }
    }

    /*事件和数据监听*/
    @Override
    public void readOutTime(String uuid, String ipHost) {
        if (listenner.containsKey(PipeSocketMonitorCall.class.getSimpleName())) {
            ((PipeSocketMonitorCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).readOutTime(uuid, ipHost);
        }
    }

    /*事件和数据监听*/
    @Override
    public void outTime(String uuid, String ipHost) {
        if (listenner.containsKey(PipeSocketMonitorCall.class.getSimpleName())) {
            ((PipeSocketMonitorCall) listenner.get(PipeReadAndWriteCall.class.getSimpleName())).outTime(uuid, ipHost);
        }
    }

    @Override
    public void idel(long period) {
        if (listenner.containsKey(PipeIdelCall.class.getSimpleName())) {
            ((PipeIdelCall) listenner.get(PipeIdelCall.class.getSimpleName())).onIdelCall(period);
        }
    }

    /*事件和数据监听*/
    @Override
    public void onThCall(double temperature, double humidity, long internal) {
        if (listenner.containsKey(PipeThCall.class.getSimpleName())) {
            ((PipeThCall) listenner.get(PipeThCall.class.getSimpleName())).onThCall(temperature, humidity, internal);
        }
    }

    public void addIdelListenner(PipeIdelCall call) {
        addListenner(PipeIdelCall.class.getSimpleName(),call);
    }
    public void addThListnenner(PipeThCall call){
        addListenner(PipeThCall.class.getSimpleName(),call);
    }

    public void addPipeChatListenner(PipeReadAndWriteCall call){
        addListenner(PipeReadAndWriteCall.class.getSimpleName(),call);
    }

    public void addPipeConnectListenner(PipeSocketMonitorCall call){
        addListenner(PipeSocketMonitorCall.class.getSimpleName(),call);
    }

    /*事件和数据监听*/
    private void addListenner(String name,Object  clazz) {
        listenner.put(name, clazz);
    }

    public Ipipeline build() {

        return pipeline;
    }


}
