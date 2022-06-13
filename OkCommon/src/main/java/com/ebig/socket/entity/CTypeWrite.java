package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef( {CTypeWrite.TypeAnswer,CTypeWrite.TypeLock, CTypeWrite.TypeCardReader, CTypeWrite.TypeBackLight,
        CTypeWrite.TypeLcd, CTypeWrite.TypeScale, CTypeWrite.TypeScan, CTypeWrite.TypeFinger, CTypeWrite.TypeColorLight})
public @interface CTypeWrite {

    final static String TypeAnswer= TypeConstance.T_ANSWER_SEND;
    final static String TypeLock= TypeConstance.T_DOOR_SEND;
    final static String TypeCardReader= TypeConstance.T_CARD_READER_SEND;
    final static String TypeBackLight= TypeConstance.T_BACK_L_SEND;
    final static String TypeLcd= TypeConstance.T_LCD_SEND;
    final static String TypeScale= TypeConstance.T_SCALE_SEND;
    final static String TypeScan= TypeConstance.T_SCANE_SEND;
    final static String TypeFinger= TypeConstance.T_FINGER_SEND;
    final static String TypeColorLight= TypeConstance.T_COLOR_L_SEND;
    final static String TypeTH= TypeConstance.T_TH_SEND;
}
