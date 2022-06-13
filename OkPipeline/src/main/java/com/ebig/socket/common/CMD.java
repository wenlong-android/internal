package com.ebig.socket.common;

public class CMD {
    //除数据区外的总长度 指令*1+地址*3
    public static final int EXTRA_FIXED_LEN=4;

    //7e开头
    public static final int START=0x7e;
    //7f结尾
    public static final int END=0x7f;
    //应答
    public static final int ORDER_ACK=0x01;
    //锁指令
    public static final int ORDER_LOCK=0x03;
    //读卡器
    public static final int ORDER_CARD_READER=0x04;
    //上报状态
    public static final int ORDER_STATUS=0x06;
    //背光灯控制
    public static final int ORDER_BK_LIGHT=0x08;
    //LCD内容控制
    public static final int ORDER_LCD=0x09;
    //温湿度
    public static final int ORDER_TH=0x0D;
    //电子秤（称重柜）
    public static final int ORDER_SCALE=0x0E;
    //扫描头
    public static final int ORDER_BAR_SCAN=0x0F;
    //指静脉
    public static final int ORDER_FINGER=0x10;
    //三色灯（冰箱）
    public static final int ORDER_WLED=0x15;

    //锁控状态
    //打开唯一的一把锁
    public static final int LOCK_OPEN_ONLY_DOOR=0x01;
    //打开前门
    public static final int LOCK_OPEN_FRONT_DOOR=0x01;
    //打开后门
    public static final int LOCK_OPEN_BACK_DOOR=0x02;
    //锁打开
    public static final int LOCK_STATUS_OPEN=0x00;
    //锁关上
    public static final int LOCK_STATUS_ON=0x01;
    //开锁失败
    public static final int LOCK_STATUS_OPEN_FAILED=0x02;
    //上锁失败（电机锁）
    public static final int LOCK_STATUS_CLOSE_FAILED=0x03;
    //开始关门（电机锁）
    public static final int LOCK_STATUS_CLOSE_START=0x04;
    //半锁
    public static final int LOCK_STATUS_HALF_LOCK=0xFF;

    //传感器状态
    //空、未知
    public static final int LOCK_SENSOR_EMPTY=0x00;
    //全开
    public static final int LOCK_SENSOR_OPEN=0x01;
    //半锁
    public static final int LOCK_SENSOR_HALF_CLOSE=0x02;
    //全锁
    public static final int LOCK_SENSOR_CLOSE=0x03;
    //左门打开
    public static final int LOCK_SENSOR_OPEN_LEFT=0x04;
    //右门打开
    public static final int LOCK_SENSOR_OPEN_RIGHT=0x05;

    //门锁数据区固定长度 门锁id+开门指令
    public static final int LOCK_DATA_LENGTH =2;


    //空
    public static final int DATA_0=0x00;
    //数据0x01
    public static final int DATA_1=0x01;
    //数据0x01
    public static final int DATA_2=0x02;
    //数据0x01
    public static final int DATA_3=0x03;
    //数据0x01
    public static final int DATA_4=0x04;
    //数据0x01
    public static final int DATA_5=0x05;
    //数据0x01
    public static final int DATA_6=0x06;
    //数据0x01
    public static final int DATA_7=0x07;
    //数据0x01
    public static final int DATA_8=0x08;
    //数据0x01
    public static final int DATA_9=0x09;

}
