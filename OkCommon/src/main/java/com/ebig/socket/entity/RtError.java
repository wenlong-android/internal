package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef( {
        RtError.server_shutDown,
        RtError.client_lost,
        RtError.unKnown,
        RtError.resendLimit})
public @interface RtError {
    final static String server_shutDown= TypeConstance.serverShutDown;
    final static String client_lost= TypeConstance.clientLost;
    final static String unKnown= TypeConstance.unKnown;
    final static String resendLimit= TypeConstance.unKnown;

}
