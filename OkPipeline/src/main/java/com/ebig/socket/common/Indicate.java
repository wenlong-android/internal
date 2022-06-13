package com.ebig.socket.common;
public class Indicate  {
    public static boolean writeOutTime = false;
    public static boolean readOutime = false;
    public static boolean rwOutTime = false;
    public static boolean clientConnect=false;

    private static class L {
        private static Indicate manager = new Indicate();
    }

    public static Indicate l() {
        return L.manager;
    }

     public void deviceConnect(String uuid, String ipHost) {
         if (ipHost.equals(PipeBus.l().getHost())) {
             clientConnect = true;
         }

    }


    public void deviceDisConnect(String uuid, String ipHost) {
        if (ipHost.equals(PipeBus.l().getHost())) {
            clientConnect = false;
        }
    }


    public void messageRead(String uuid, String ipHost, String cmd) {

    }
    public void messageWrite(String uuid, String ipHost, String cmd){

    }


    public void writeOutTime(String uuid, String ipHost,boolean being) {
        if (ipHost.equals(PipeBus.l().getHost())) {
            writeOutTime = being;
        }
    }


    public void readOutTime(String uuid, String ipHost,boolean being) {
        if (ipHost.equals(PipeBus.l().getHost())) {
            readOutime = being;
        }
    }

     public void outTime(String uuid, String ipHost,boolean being) {
        if (ipHost.equals(PipeBus.l().getHost())) {
            rwOutTime = being;
        }
    }

    public static boolean isWriteOutTime() {
        return writeOutTime;
    }

    public static boolean isReadOutime() {
        return readOutime;
    }

    public static boolean isRwOutTime() {
        return rwOutTime;
    }

    public static boolean isClientConnect() {
        return clientConnect;
    }
}
