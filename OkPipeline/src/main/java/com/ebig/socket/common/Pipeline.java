package com.ebig.socket.common;

import com.ebig.service.CmdServiceManager;
import com.ebig.socket.dispatchWrite.base.ICommand;
import com.ebig.socket.dispatchWrite.base.EbWriter;
import com.ebig.socket.idl.PipeReadAndWriteCall;
import com.ebig.socket.idl.PipeSocketMonitorCall;
import com.ebig.socket.idl.PipeCall;
import com.ebig.socket.idl.PipeThCall;
import com.ebig.socket.netty.PipeSocketServer;
import com.ebig.utils.AppGlobals;

public class Pipeline implements Ipipeline{
    private final long readFrequency;
    private final long writeFrequency;
    private final boolean debug;
    private final boolean autoReBoot;
    private final boolean logDbRecord;
    private final int serverPort;
    private final String clientHost;
    private final PipeCall idelCall;
    private final PipeThCall thCall;
    private final PipeSocketMonitorCall connectCall;
    private final PipeReadAndWriteCall chatCall;



    @Override
    public ICommand commander() {
        return EbWriter.l();
    }
    public Pipeline(Cosmetic cosmetic) {
        this.readFrequency=cosmetic.readFrequency;
        this.writeFrequency=cosmetic.writeFrequency;
        this.debug=cosmetic.debug;
        this.autoReBoot=cosmetic.autoReBoot;
        this.logDbRecord=cosmetic.logDbRecord;
        this.serverPort=cosmetic.serverPort;
        this.clientHost=cosmetic.clientHost;
        this.idelCall=cosmetic.idelCall;
        this.thCall=cosmetic.thCall;
        this.connectCall=cosmetic.connectCall;
        this.chatCall=cosmetic.chatCall;
    }

    public static class Cosmetic {
        private long readFrequency=30;
        private long writeFrequency=30;
        private boolean debug=true;
        private boolean autoReBoot=true;
        private boolean logDbRecord=true;
        private int serverPort=8080;
        private  String clientHost;

        private PipeCall idelCall;
        private PipeThCall thCall;
        private PipeSocketMonitorCall connectCall;
        private PipeReadAndWriteCall chatCall;

        public Cosmetic(String clientHost) {
            this.clientHost=clientHost;
        }
        public Cosmetic serverPort(int serverPort) {
            this.serverPort = serverPort;
            return this;
        }
        public Cosmetic setReadFrequency(long readFrequency) {
            this.readFrequency = readFrequency;
            return this;
        }

        public Cosmetic setWriteFrequency(long writeFrequency) {
            this.writeFrequency = writeFrequency;
            return this;
        }

        public Cosmetic debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Cosmetic autoReBoot(boolean autoReBoot) {
            this.autoReBoot = autoReBoot;
            return this;
        }

        public Cosmetic logDbRecord(boolean logDbRecord) {
            this.logDbRecord = logDbRecord;
            return this;
        }

        public Cosmetic addIdelCall(PipeCall idelCall) {
            this.idelCall = idelCall;
            return this;
        }

        public Cosmetic adThCall(PipeThCall thCall) {
            this.thCall = thCall;
            return this;
        }

        public Cosmetic addConnectCall(PipeSocketMonitorCall connectCall) {
            this.connectCall = connectCall;
            return this;
        }

        public Cosmetic addChatCall(PipeReadAndWriteCall chatCall) {
            this.chatCall = chatCall;
            return this;
        }
        public Pipeline makeUp(){
            CmdServiceManager.l().begin(AppGlobals.getApplication());
            return new Pipeline(this);
        }

    }




    @Override
    public void addListenner(Class clazz) {

    }

    public long getReadFrequency() {
        return readFrequency;
    }

    public long getWriteFrequency() {
        return writeFrequency;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isAutoReBoot() {
        return autoReBoot;
    }

    public boolean isLogDbRecord() {
        return logDbRecord;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getClientHost() {
        return clientHost;
    }

    public PipeCall getIdelCall() {
        return idelCall;
    }

    public PipeThCall getThCall() {
        return thCall;
    }

    public PipeSocketMonitorCall getConnectCall() {
        return connectCall;
    }

    public PipeReadAndWriteCall getChatCall() {
        return chatCall;
    }


}
