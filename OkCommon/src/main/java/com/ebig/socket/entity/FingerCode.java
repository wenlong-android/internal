package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef({
                /*不支持的命令/空命令*/
                FingerCode.finger_success,/*指纹操作成功*/
                FingerCode.finger_failed,/*指纹操作失败*/
                /**检查手指*/
                FingerCode.finger_no_tip,/*只检测到指腹未检测到指尖*/
                FingerCode.finger_no_pulp,/*只检测到指尖未检测到指腹*/
                FingerCode.finger_no_finger, /*未检测到手指*/
                /**验证手指*/
                FingerCode.finger_no_id,  /*获取到ID*/
                FingerCode.finger_no_data,  /*id不存在*/
                FingerCode.finger_auth_failed, /*验证识别*/
                FingerCode.finger_tp_reset, /*生成的模板不合格,请重放手指 Template*/
                FingerCode.finger_caputer_timeout,   /*拍照超时,请重放手指*/
                FingerCode.finger_timeout,/*放置手指超时*/
                /**注册*/
                FingerCode.finger_regist_full,/* 注册：模板缓存满*/
                FingerCode.finger_regist_id_occupy,/* 注册：ID号已被占用0x21*/
                FingerCode.finger_regist_exist,/*注册：手指已注册过0x22*/
                FingerCode.finger_regist_start_1, /*注册：开始注册，请放入手指0x23*/
                FingerCode.finger_regist_success_1,/* 注册：第一次注册成功，请移开手指0x24*/
                FingerCode.finger_regist_start_2, /* 注册：第二次注册，请放入手指0x25*/
                FingerCode.finger_regist_success_2,/*注册：第二次注册成功，请移开手指0x26*/
                FingerCode.finger_regist_start_3, /*注册：第三次注册，请放入手指0x27*/
                FingerCode.finger_regist_success_3,/*注册：第三次注册成功，请移开手指0x28*/
                FingerCode.finger_regist_start_1_1, /*注册：开始注册，请放入手指0x29*/
                FingerCode.finger_regist_success_4,/*注册：开始注册，请放入手指0x2B*/
                FingerCode.finger_regist_start_1_2,  /*注册：开始注册，请放入手指0x2B*/
                FingerCode.finger_regist_success_5, /*注册：第五次注册成功，请移开手指0x2C*/
                FingerCode.finger_regist_success_verify,/*注册：注册完成，请放入手指6--验证0x2D*/
                FingerCode.finger_regist_finish,/*注册：注册完成0x2E*/
                FingerCode.finger_regist_outtime,/*注册：注册超时0x2F*/
                /**注册异常*/
                FingerCode.finger_template_re_regist,/*注册：注册时再次采集静脉特征差异过大,请重新注册0x30*/
                FingerCode.finger_template_fail,/*注册：注册时生成的模板不合格,请重新注册0x31*/
                FingerCode.finger_template_re_regist_2,/*注册：与前一次采集手指下发信息不同,请重新注册0x32*/
                FingerCode.finger_template_success,/*注册：发送结束注册命令返回结束注册成功0x33*/
                FingerCode.finger_template_outtime,/*注册：注册时拍照超时		0x34*/
                /**结束注册*/
                FingerCode.finger_regist_success,/* 注册：第一次注册成功，请移开手指0x24*/
                FingerCode.finger_regist_fail,/*结束失败：保存失败-模板数量小于3,0x36*/
                FingerCode.finger_regist_template_full,/*结束失败：保存失败-静脉模板空间满0x37*/
                FingerCode.finger_regist_save_fail,/*结束失败：保存失败-信息头空间满0x38*/
                FingerCode.finger_flash_fail,/*结束失败：保存失败-Flash操作失败0x39*/
                /**删除指纹*/
                FingerCode.finger_delete_success, /*删除：单个删除成功0x3A*/
                FingerCode.finger_delete_all_success,/*删除：全部删除成功0x3B*/
                FingerCode.finger_delete_fail,/*删除：单个删除失败或者无数据0x3C*/
                FingerCode.finger_delete_no_id, /*删除：无此ID0x3D*/
                /**模板状态码*/
                FingerCode.finger_tp_get_success,/*从指静脉获取模板成功0x40*/
                FingerCode.finger_tp_get_no_tp, /*从指静脉获取模板失败:无此模板0x41*/
                FingerCode.finger_tp_dwon_success, /*下载模板成功0x42*/
                FingerCode.finger_tp_dwon_fail, /*下载模板失败0x43*/
                FingerCode.finger_tp_dwon_head_full,/*下载模板失败:信息头空间满0x44*/
                FingerCode.finger_tp_dwon_full,/*下载模板失败:模板空间满0x45*/
                FingerCode.finger_tp_dwon_flash_fail,/*下载模板失败:Flash操作失败0x46*/
                /**设备状态*/
                FingerCode.finger_no_device_found,/*无设备0xFD*/
                /**通信类*/
                FingerCode.finger_tran_data,/*透传数据/原始数据内容传输0xFE*/
                FingerCode.finger_unkonw  /*未知错误0xFF*/
                })
public @interface FingerCode {
    /*指纹操作成功*/
    public static final String finger_success = TypeConstance.C_00;
    /*指纹操作失败*/
    public static final String finger_failed = TypeConstance.C_01;
    /**检查手指*/
    /*只检测到指腹未检测到指尖*/
    public static final String finger_no_tip = TypeConstance.C_02;
    /*只检测到指尖未检测到指腹*/
    public static final String finger_no_pulp = TypeConstance.C_03;
    /*未检测到手指*/
    public static final String finger_no_finger = TypeConstance.C_04;
    /**验证手指*/
    /*获取到ID*/
    public static final String finger_no_id = TypeConstance.C_05;
    /*id不存在*/
    public static final String finger_no_data = TypeConstance.C_06;
    /*验证识别*/
    public static final String finger_auth_failed = TypeConstance.C_07;
    /*生成的模板不合格,请重放手指 Template*/
    public static final String finger_tp_reset = TypeConstance.C_08;
    /*拍照超时,请重放手指*/
    public static final String finger_caputer_timeout = TypeConstance.C_09;
    /*放置手指超时*/
    public static final String finger_timeout = TypeConstance.C_0a;
    /** 注册*/
    /* 注册：模板缓存满*/
    public static final String finger_regist_full = TypeConstance.C_20;
    /* 注册：ID号已被占用0x21*/
    public static final String finger_regist_id_occupy = TypeConstance.C_21;
    /*注册：手指已注册过0x22*/
    public static final String finger_regist_exist = TypeConstance.C_22;
    /*注册：开始注册，请放入手指0x23*/
    public static final String finger_regist_start_1 = TypeConstance.C_23;
    /* 注册：第一次注册成功，请移开手指0x24*/
    public static final String finger_regist_success_1 = TypeConstance.C_24;
    /* 注册：第二次注册，请放入手指0x25*/
    public static final String finger_regist_start_2 = TypeConstance.C_25;
    /*注册：第二次注册成功，请移开手指0x26*/
    public static final String finger_regist_success_2 = TypeConstance.C_26;
    /*注册：第三次注册，请放入手指0x27*/
    public static final String finger_regist_start_3 = TypeConstance.C_27;
    /*注册：第三次注册成功，请移开手指0x28*/
    public static final String finger_regist_success_3 = TypeConstance.C_28;
    /*注册：开始注册，请放入手指0x29*/
    public static final String finger_regist_start_1_1 = TypeConstance.C_29;
    /*注册：第四次注册成功，请移开手指0x2Aa*/
    public static final String finger_regist_success_4 = TypeConstance.C_2a;
    /*注册：开始注册，请放入手指0x2B*/
    public static final String finger_regist_start_1_2 = TypeConstance.C_2b;
    /*注册：第五次注册成功，请移开手指0x2C*/
    public static final String finger_regist_success_5 = TypeConstance.C_2c;
    /*注册：注册完成，请放入手指6--验证0x2D*/
    public static final String finger_regist_success_verify = TypeConstance.C_2d;
    /*注册：注册完成0x2E*/
    public static final String finger_regist_finish = TypeConstance.C_2e;
    /*注册：注册超时0x2F*/
    public static final String finger_regist_outtime = TypeConstance.C_2f;
    /** 注册异常*/
    /*注册：注册时再次采集静脉特征差异过大,请重新注册0x30*/
    public static final String finger_template_re_regist = TypeConstance.C_30;
    /*注册：注册时生成的模板不合格,请重新注册0x31*/
    public static final String finger_template_fail = TypeConstance.C_31;
    /*注册：与前一次采集手指下发信息不同,请重新注册0x32*/
    public static final String finger_template_re_regist_2 = TypeConstance.C_32;
    /*注册：发送结束注册命令返回结束注册成功0x33*/
    public static final String finger_template_success = TypeConstance.C_33;
    /*注册：注册时拍照超时		0x34*/
    public static final String finger_template_outtime = TypeConstance.C_34;
    /** 结束注册*/
    /*结束注册：结束成功0x35*/
    public static final String finger_regist_success = TypeConstance.C_35;
    /*结束失败：保存失败-模板数量小于3,0x36*/
    public static final String finger_regist_fail = TypeConstance.C_36;
    /*结束失败：保存失败-静脉模板空间满0x37*/
    public static final String finger_regist_template_full = TypeConstance.C_37;
    /*结束失败：保存失败-信息头空间满0x38*/
    public static final String finger_regist_save_fail = TypeConstance.C_38;
    /*结束失败：保存失败-Flash操作失败0x39*/
    public static final String finger_flash_fail = TypeConstance.C_39;
    /** 删除指纹*/
    /*删除：单个删除成功0x3A*/
    public static final String finger_delete_success = TypeConstance.C_3a;
    /*删除：全部删除成功0x3B*/
    public static final String finger_delete_all_success = TypeConstance.C_3b;
    /*删除：单个删除失败或者无数据0x3C*/
    public static final String finger_delete_fail = TypeConstance.C_3c;
    /*删除：无此ID0x3D*/
    public static final String finger_delete_no_id = TypeConstance.C_3d;
    /**模板状态码*/
    /*从指静脉获取模板成功0x40*/
    public static final String finger_tp_get_success = TypeConstance.C_40;
    /*从指静脉获取模板失败:无此模板0x41*/
    public static final String finger_tp_get_no_tp = TypeConstance.C_41;
    /*下载模板成功0x42*/
    public static final String finger_tp_dwon_success = TypeConstance.C_42;
    /*下载模板失败0x43*/
    public static final String finger_tp_dwon_fail = TypeConstance.C_43;
    /*下载模板失败:信息头空间满0x44*/
    public static final String finger_tp_dwon_head_full = TypeConstance.C_44;
    /*下载模板失败:模板空间满0x45*/
    public static final String finger_tp_dwon_full = TypeConstance.C_45;
    /*下载模板失败:Flash操作失败0x46*/
    public static final String finger_tp_dwon_flash_fail = TypeConstance.C_46;
    /** 设备状态*/
    /*无设备0xFD*/
    public static final String finger_no_device_found = TypeConstance.C_fb;
    /** 通信类*/
    /*透传数据/原始数据内容传输0xFE*/
    public static final String finger_tran_data = TypeConstance.C_fe;
    /* 未知错误0xFF*/
    public static final String finger_unkonw = TypeConstance.C_ff;
}
