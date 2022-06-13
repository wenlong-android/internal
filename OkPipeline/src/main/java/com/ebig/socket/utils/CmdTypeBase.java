package com.ebig.socket.utils;

public class CmdTypeBase {
    /***************************************门锁*******************************************/

    //锁打开
    public static final String LOCK_OPEN = "00";
    //锁关闭
    public static final String LOCK_CLOSE = "01";
    //开锁失败
    public static final String LOCK_OPEN_FAILED = "02";
    //锁关闭失败
    public static final String LOCK_CLOSE_FAILED = "03";
    //开始开锁
    public static final String LOCK_START_OPEN = "04";
    //半锁状态
    public static final String LOCK_HALF_LOCK = "05";
    /***************************************指静脉*******************************************/

    /**
     * 操作响应
     */
//指纹操作成功
    public static final String FINGER_SUCCESS = "00";
//指纹操作失败
    /**
     * 检查手指
     */
    public static final String FINGER_FAILED = "01";
    //只检测到指腹未检测到指尖
    public static final String FINGER_NO_TIP = "02";
    //只检测到指尖未检测到指腹
    public static final String FINGER_NO_PULP = "03";
    //未检测到手指
    public static final String FINGER_NO_FINGER = "04";
    /**
     * 验证手指
     */
//获取到ID
    public static final String FINGER_NO_ID = "05";
    //id不存在
    public static final String FINGER_NO_DATA = "06";
    //验证识别
    public static final String FINGER_AUTH_FAILED = "07";
    //生成的模板不合格,请重放手指 Template
    public static final String FINGER_TP_RESET = "08";
    //拍照超时,请重放手指
    public static final String FINGER_CAPUTER_TIMEOUT = "09";
    //放置手指超时
    public static final String FINGER_TIMEOUT = "0A";

    /**
     * 注册
     */
// 注册：模板缓存满
    public static final String FINGER_REGIST_FULL = "20";
    // 注册：ID号已被占用0x21
    public static final String FINGER_REGIST_ID_OCCUPY = "21";
    //注册：手指已注册过0x22
    public static final String FINGER_REGIST_EXIST = "22";
    //注册：开始注册，请放入手指0x23
    public static final String FINGER_REGIST_START_1 = "23";
    // 注册：第一次注册成功，请移开手指0x24
    public static final String FINGER_REGIST_SUCCESS_1 = "24";
    // 注册：第二次注册，请放入手指0x25
    public static final String FINGER_REGIST_START_2 = "25";
    //注册：第二次注册成功，请移开手指0x26
    public static final String FINGER_REGIST_SUCCESS_2 = "26";
    //注册：第三次注册，请放入手指0x27
    public static final String FINGER_REGIST_START_3 = "27";
    //注册：第三次注册成功，请移开手指0x28
    public static final String FINGER_REGIST_SUCCESS_3 = "28";
    //注册：开始注册，请放入手指0x29
    public static final String FINGER_REGIST_START_1_1 = "29";
    //注册：第四次注册成功，请移开手指0x2A
    public static final String FINGER_REGIST_SUCCESS_4 = "2A";
    //注册：开始注册，请放入手指0x2B
    public static final String FINGER_REGIST_START_1_2 = "2B";
    //注册：第五次注册成功，请移开手指0x2C
    public static final String FINGER_REGIST_SUCCESS_5 = "2C";
    //注册：注册完成，请放入手指6--验证0x2D
    public static final String FINGER_REGIST_SUCCESS_VERIFY = "2D";
    //注册：注册完成0x2E
    public static final String FINGER_REGIST_FINISH = "2E";
    //注册：注册超时0x2F
    public static final String FINGER_REGIST_OUTTIME = "2F";

    /**
     * 注册异常
     */
    //注册：注册时再次采集静脉特征差异过大,请重新注册0x30
    public static final String FINGER_TEMPLATE_RE_REGIST = "30";
    //注册：注册时生成的模板不合格,请重新注册0x31
    public static final String FINGER_TEMPLATE_FAIL = "31";
    //注册：与前一次采集手指下发信息不同,请重新注册0x32
    public static final String FINGER_TEMPLATE_RE_REGIST_2 = "32";
    //注册：发送结束注册命令返回结束注册成功0x33
    public static final String FINGER_TEMPLATE_SUCCESS = "33";
    //注册：注册时拍照超时		0x34
    public static final String FINGER_TEMPLATE_OUTTIME = "34";


    /**
     * 结束注册
     */
    //结束注册：结束成功0x35
    public static final String FINGER_REGIST_SUCCESS = "35";
    //结束失败：保存失败-模板数量小于3,0x36
    public static final String FINGER_REGIST_FAIL = "36";
    //结束失败：保存失败-静脉模板空间满0x37
    public static final String FINGER_REGIST_TEMPLATE_FULL = "37";
    //结束失败：保存失败-信息头空间满0x38
    public static final String FINGER_REGIST_SAVE_FAIL = "38";
    //结束失败：保存失败-Flash操作失败0x39
    public static final String FINGER_FLASH_FAIL = "39";


    /**
     * 删除指纹
     */
   //删除：单个删除成功0x3A
    public static final String FINGER_DELETE_SUCCESS = "3A";
    //删除：全部删除成功0x3B
    public static final String FINGER_DELETE_ALL_SUCCESS = "3B";
    //删除：单个删除失败或者无数据0x3C
    public static final String FINGER_DELETE_FAIL = "3C";
    //删除：无此ID0x3D
    public static final String FINGER_DELETE_NO_ID = "3D";

    /**
     * 模板状态码
     */
//从指静脉获取模板成功0x40
    public static final String FINGER_TP_GET_SUCCESS = "40";
    //从指静脉获取模板失败:无此模板0x41
    public static final String FINGER_TP_GET_NO_TP = "41";
    //下载模板成功0x42
    public static final String FINGER_TP_DWON_SUCCESS = "42";
    //下载模板失败0x43
    public static final String FINGER_TP_DWON_FAIL = "43";
    //下载模板失败:信息头空间满0x44
    public static final String FINGER_TP_DWON_HEAD_FULL = "44";
    //下载模板失败:模板空间满0x45
    public static final String FINGER_TP_DWON_FULL = "45";
    //下载模板失败:Flash操作失败0x46
    public static final String FINGER_TP_DWON_FLASH_FAIL = "46";

    /**
     * 设备状态
     */
    //无设备0xFD
    public static final String FINGER_NO_DEVICE_FOUND = "FD";

    /**
     * 通信类
     */
    //透传数据/原始数据内容传输0xFE
    public static final String FINGER_TRAN_DATA = "FE";
    // 未知错误0xFF
    public static final String FINGER_UNKONW = "FF";


    /***************************************背光*******************************************/
    //背光亮起
    public static final String BACK_LIGHT_ON = "FF";
    protected static String getDoorAction(String type, int lockNum, String action) {

        if (action.equals(LOCK_OPEN)) {
            type = lockNum + "号锁 开锁成功";
        } else if (action.equals(LOCK_CLOSE)) {
            type = lockNum + "号锁 关锁成功";
        } else if (action.equals(LOCK_OPEN_FAILED)) {
            type = lockNum + "号锁 开锁识别";
        } else if (action.equals(LOCK_CLOSE_FAILED)) {
            type = lockNum + "号锁 关锁识别";
        } else if (action.equals(LOCK_START_OPEN)) {
            type = lockNum + "号锁 开始开锁";
        } else if (action.equals(LOCK_HALF_LOCK)) {
            type = lockNum + "号锁 半锁状态";
        }
        return type;
    }
}
