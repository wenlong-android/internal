package com.ebig.push;

import com.ebig.crosso.utils.CroThread;
import com.ebig.http.ApushEntity;
import com.ebig.idl.Common2Call;
import com.ebig.idl.CommonCall;

public class APush {
    public final String host;
    public final int prot;
    public final String userName;
    public final String passWord;
    public final String method;
    public CommonCall<String> exceptionListenner;
    public final String serverHost;

    APush(Params params) {
        MqManager.l().setHost(params.host);
        this.host = params.host;
        this.prot = params.prot;
        this.userName = params.userName;
        this.passWord = params.passWord;
        this.method = params.method;
        this.serverHost = params.serverHost;
    }

    public static class Params {
        public String host;
        public int prot;
        public String userName;
        public String passWord;
        public String method = "test";
        public String serverHost = "http://192.168.1.71:9999/";

        public Params(String host, int prot, String userName, String passWord) {
            this.host = host;
            this.prot = prot;
            this.userName = userName;
            this.passWord = passWord;
        }

        public Params method(String method) {
            this.method = method;
            return this;
        }

        public Params serverHost(String serverHost) {
            this.serverHost = method;
            return this;
        }

        public APush build() {
            return new APush(this);
        }
    }

    public void init(Common2Call<ApushEntity, String> call) {
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                MqManager.l().init(APush.this, call);
            }
        });

    }

    public static APush regist() {
        return new Params("192.168.1.71", 5672, "admin", "admin").method("test").build();
    }


    public static String getUpmsUrl() {
        return MqManager.l().getHost() + "upms" + MqManager.serverHostApan;
    }

    public static String getBaseUrl() {
        return MqManager.l().getHost() + "base" + MqManager.serverHostApan;
    }
    public static String getStorageUrl() {
        return MqManager.l().getHost() + "storage" + MqManager.serverHostApan;
    }
    public static String getMachineUrl() {
        return MqManager.l().getHost() + "upmmachines" + MqManager.serverHostApan;
    }
}
