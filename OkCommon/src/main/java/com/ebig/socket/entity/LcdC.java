package com.ebig.socket.entity;

import androidx.annotation.StringDef;

public class LcdC {
    /*不支持的命令/空命令*/
    @StringDef({
            name,/*商品名*/
            specification, /*规格参数*/
            number, /*数量参数(库存)*/
            nickName, /*别名*/
            vender, /*厂商名称*/
            barCode, /*商品编码*/
            unit, /*数量单位*/
            model, /*型号*/
            expand1,/*扩展1(上架/拣选数量)*/
            expand2,/*扩展2*/
            expand3, /*扩展3*/
            expand4, /*扩展4*/
            expand5, /*扩展5*/
            expand6,  /*扩展6*/
            expand7, /*扩展7*/
            expand8,/*扩展8*/
            expand9, /*扩展9*/
            identifier, /*识别码*/
            useTemplate,/*此条显示内容使用的模板*/
            mode  /*显示模式*/
    })
    public @interface D { }

    /*商品名*/
    public final static String name = TypeConstance.C_01;
    /*规格参数*/
    public final static String specification = TypeConstance.C_02;
    /*数量参数(库存)*/
    public final static String number = TypeConstance.C_03;
    /*别名*/
    public final static String nickName = TypeConstance.C_04;
    /*厂商名称*/
    public final static String vender = TypeConstance.C_05;
    /*商品编码*/
    public final static String barCode = TypeConstance.C_06;
    /*数量单位*/
    public final static String unit = TypeConstance.C_07;
    /*型号*/
    public final static String model = TypeConstance.C_08;
    /*扩展1(上架/拣选数量)*/
    public final static String expand1 = TypeConstance.C_09;
    /*扩展2*/
    public final static String expand2 = TypeConstance.C_0a;
    /*扩展3*/
    public final static String expand3 = TypeConstance.C_0b;
    /*扩展4*/
    public final static String expand4 = TypeConstance.C_0c;
    /*扩展5*/
    public final static String expand5 = TypeConstance.C_0d;
    /*扩展6*/
    public final static String expand6 = TypeConstance.C_0e;
    /*扩展7*/
    public final static String expand7 = TypeConstance.C_0f;
    /*扩展8*/
    public final static String expand8 = TypeConstance.C_10;
    /*扩展9*/
    public final static String expand9 = TypeConstance.C_11;
    /*识别码*/
    public final static String identifier = TypeConstance.C_12;
    /*此条显示内容使用的模板*/
    public final static String useTemplate = TypeConstance.C_fb;
    /*显示模式*/
    public final static String mode = TypeConstance.C_ff;
}
