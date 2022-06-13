package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef({
                CTypeRead.TypeIdel,/*心跳*/
                CTypeRead.TypeTH,/*温湿度*/
                CTypeRead.TypeLock,/*门锁*/
                CTypeRead.TypeCardReader,/*读卡器*/
                CTypeRead.TypeBackLight,/*背光灯*/
                CTypeRead.TypeLcd,/*LCD*/
                CTypeRead.TypeScale,/*电子秤*/
                CTypeRead.TypeScan,/*扫描头*/
                CTypeRead.TypeFinger,/*指静脉*/
                CTypeRead.TypeColorLight /*三色灯*/
})
public @interface CTypeRead {
    final static String TypeAnswer = TypeConstance.T_ANSWER;
    final static String TypeLock = TypeConstance.T_DOOR;
    final static String TypeCardReader = TypeConstance.T_CARD_READER;
    final static String TypeBackLight = TypeConstance.T_BACK_L;
    final static String TypeLcd = TypeConstance.T_LCD;
    final static String TypeScale = TypeConstance.T_SCALE;
    final static String TypeScan = TypeConstance.T_SCANER;
    final static String TypeFinger = TypeConstance.T_FINGER;
    final static String TypeColorLight = TypeConstance.T_COLOR_L;
    final static String TypeTH = TypeConstance.T_TH;
    final static String TypeIdel = TypeConstance.T_IDLE;
}
