package com.ebig.socket.entity;

import com.ebig.utils.StrUtils;
import com.ebig.socket.entity.CTypeRead;
import com.ebig.socket.entity.CTypeWrite;

import java.util.HashMap;

public class CmdType {
    /*应答*/
    public static final String TYPE_ANSWER = "81";
    public static final String TYPE_ANSWER2 = "01";
    /*开锁*/
    public static final String TYPE_DOOR = "83";
    public static final String TYPE_DOOR2 = "03";
    /*读卡器*/
    public static final String TYPE_CARD_READER = "84";
    public static final String TYPE_CARD_READER2 = "04";
    /*上报状态*/
    public static final String TYPE_STATUS = "86";
    public static final String TYPE_STATUS2 = "06";
    /*背光*/
    public static final String TYPE_BACK_L = "88";
    public static final String TYPE_BACK_L2 = "08";
    /*LCD*/
    public static final String TYPE_LCD = "89";
    public static final String TYPE_LCD2 = "09";
    /*温湿度*/
    public static final String TYPE_TH = "8d";
    public static final String TYPE_TH2 = "0d";
    /*扫描头*/
    public static final String TYPE_SCANER = "8f";
    public static final String TYPE_SCANE2 = "0f";
    /*指静脉*/
    public static final String TYPE_FINGER = "90";
    public static final String TYPE_FINGER2 = "10";
    /*三色灯*/
    public static final String TYPE_COLOR_L = "15";
    public static final String TYPE_COLOR_L2 = "95";
    /*开锁*/
    public static final String TYPE_START = "7e";
    public static final String TYPE_END = "7f";
    public static final String TYPE_IDLE = "be";


    //开锁后门是否被拉开关闭
    /*******************************************/
    /*应答*/
    public static final String TYPE_ANSWER_STR = "应答上发";
    public static final String TYPE_ANSWER2_STR = "应答下发";
    /*开锁*/
    public static final String TYPE_DOOR_STR = "开锁上发";
    public static final String TYPE_DOOR2_STR = "开锁下发";
    /*读卡器*/
    public static final String TYPE_CARD_READER_STR = "读卡器上发";
    public static final String TYPE_CARD_READER2_STR = "读卡器下发";
    /*上报状态*/
    public static final String TYPE_STATUS_STR = "上报状态上发";
    public static final String TYPE_STATUS2_STR = "上报状态下发";
    /*背光*/
    public static final String TYPE_BACK_L_STR = "背光上发";
    public static final String TYPE_BACK_L2_STR = "背光下发";
    /*LCD*/
    public static final String TYPE_LCD_STR = "LCD上发";
    public static final String TYPE_LCD2_STR = "LCD下发";
    /*温湿度*/
    public static final String TYPE_TH_STR = "温湿度上发";
    public static final String TYPE_TH2_STR = "温湿度下发";
    /*扫描头*/
    public static final String TYPE_SCANER_STR = "扫描头上发";
    public static final String TYPE_SCANE2_STR = "扫描头下发";
    /*指静脉*/
    public static final String TYPE_FINGER_STR = "指静脉上发";
    public static final String TYPE_FINGER2_STR = "指静脉下发";
    /*三色灯*/
    public static final String TYPE_COLOR_L_STR = "三色灯下发";
    public static final String TYPE_COLOR_L2_STR = "三色灯下发";
    /*开锁*/
    public static final String TYPE_START_STR = "帧开头";
    public static final String TYPE_END_STR = "帧结尾";
    public static final String TYPE_IDLE_STR = "心跳";

    private static HashMap<String, String> hashMap = new HashMap<>();


    public static String getType(String type) {
        if (hashMap.size() == 0) {
            init();
        }
        if (!hashMap.containsKey(type)) {
            return "Type未设置";
        } else {
            return hashMap.get(type);
        }
    }

    public static boolean isAnswerUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeAnswer);
    }

    public static boolean isAnswerDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeAnswer);
    }

    /*开锁*/
    public static boolean isDoorUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeLock);
    }

    public static boolean isDoorDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeLock);
    }

    /*读卡器*/
    public static boolean isCardReaderUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeCardReader);
    }

    public static boolean isCardReaderDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeCardReader);
    }


    /*上报状态*/
    public static boolean isStatusUp(String type) {
        return StrUtils.equel(type, TYPE_STATUS);
    }

    public static boolean isStatusDown(String type) {
        return StrUtils.equel(type, TYPE_STATUS2);
    }


    /*背光*/
    public static boolean isBackLUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeBackLight);
    }

    public static boolean isBackLDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeBackLight);
    }

    /*LCD*/
    public static boolean isLcdUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeLcd);
    }

    public static boolean isLcdDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeLcd);
    }


    /*温湿度*/
    public static boolean isTHUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeTH);
    }

    public static boolean isTHDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeTH);
    }


    /*扫描头*/
    public static boolean isScanUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeScan);
    }

    public static boolean isScanDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeScan);
    }

    /*电子秤*/
    public static boolean isScaleUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeScale);
    }
    /*电子秤*/
    public static boolean isScaleDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeScale);
    }

    /*指静脉*/
    public static boolean isFingerUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeFinger);
    }

    public static boolean isFingerDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeFinger);
    }

    /*三色灯*/
    public static boolean isColorLUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeColorLight);
    }

    public static boolean isColorLDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeColorLight);
    }

    /*开锁*/
    public static boolean isLockUp(String type) {
        return StrUtils.equel(type, CTypeRead.TypeLock);
    }

    public static boolean isLockDown(String type) {
        return StrUtils.equel(type, CTypeWrite.TypeLock);
    }

    public static boolean isIdel(String type) {
        return StrUtils.equel(type, CTypeRead.TypeIdel);
    }


    public static String get(String orderCode, String data) {
        String type = "CmdType未设置";
        switch (orderCode) {
            case TYPE_IDLE:
                type = TYPE_IDLE_STR;
                break;
            case TYPE_CARD_READER:
            case TYPE_CARD_READER2:
                type = "读卡器刷卡";
                break;
            case TYPE_TH:
            case TYPE_TH2:
                type = "温湿度";
                break;


            case TYPE_ANSWER:
            case TYPE_ANSWER2:
                type = "应答";
                break;
            case TYPE_BACK_L:
            case TYPE_BACK_L2:
                type = "背光";
                break;

            case TYPE_DOOR:
            case TYPE_DOOR2:
//                if (StrUtils.notEmpty(data) && data.length() == 12) {
//                    List<String> arr = MatchFactoryHelper.spilt(data);
//                    if (arr != null && arr.size() == 6) {
//                        int lockNum = HexUtils.hex2int(arr.get(3));
//                        String action = arr.get(4);
//                        //   type = getDoorAction(type, lockNum, action);
//                    }
//                } else {
//                    type = "锁状态返回异常";
//                }
                type = "锁状";
                break;
            case TYPE_FINGER:
            case TYPE_FINGER2:
                //data实际是40前面的状态码
                type = "指静脉";
                break;

            case TYPE_LCD:
            case TYPE_LCD2:
                //data实际是40前面的状态码
                type = "LCD";
                break;

            case TYPE_SCANER:
            case TYPE_SCANE2:
                type = "扫描头";
                break;
        }
        return type;
    }

    private static void init() {
        hashMap.put(TYPE_ANSWER, TYPE_ANSWER_STR);
        hashMap.put(TYPE_ANSWER2, TYPE_ANSWER2_STR);
        hashMap.put(TYPE_DOOR, TYPE_DOOR_STR);
        hashMap.put(TYPE_DOOR2, TYPE_DOOR2_STR);
        hashMap.put(TYPE_DOOR2, TYPE_DOOR2_STR);
        hashMap.put(TYPE_CARD_READER, TYPE_CARD_READER_STR);
        hashMap.put(TYPE_CARD_READER2, TYPE_CARD_READER2_STR);
        hashMap.put(TYPE_STATUS, TYPE_STATUS_STR);
        hashMap.put(TYPE_STATUS2, TYPE_STATUS2_STR);
        hashMap.put(TYPE_BACK_L, TYPE_BACK_L_STR);
        hashMap.put(TYPE_BACK_L2, TYPE_BACK_L2_STR);
        hashMap.put(TYPE_LCD, TYPE_LCD_STR);
        hashMap.put(TYPE_LCD2, TYPE_LCD2_STR);
        hashMap.put(TYPE_TH, TYPE_TH_STR);
        hashMap.put(TYPE_TH2, TYPE_TH2_STR);
        hashMap.put(TYPE_SCANER, TYPE_SCANER_STR);
        hashMap.put(TYPE_SCANE2, TYPE_SCANE2_STR);
        hashMap.put(TYPE_FINGER, TYPE_FINGER_STR);
        hashMap.put(TYPE_FINGER2, TYPE_FINGER2_STR);
        hashMap.put(TYPE_COLOR_L, TYPE_COLOR_L_STR);
        hashMap.put(TYPE_COLOR_L2, TYPE_COLOR_L2_STR);
        hashMap.put(TYPE_START, TYPE_START_STR);
        hashMap.put(TYPE_END, TYPE_END_STR);
        hashMap.put(TYPE_IDLE, TYPE_IDLE_STR);
    }
}
