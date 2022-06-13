package com.ebig.socket.entity;

public class FingerCodeUtils {
    public static String match(String code) {
        String status="未知状态";
        switch (code) {
            case FingerCode.finger_success:
                status= "指纹操成功";
            break;
            case FingerCode.finger_failed:
                status= "指纹操作失败";
            break;
            case FingerCode.finger_no_tip:
                status= "只检测到指腹未检测到指尖";
            break;

            case FingerCode.finger_no_pulp:
                status= "只检测到指尖未检测到指腹";
            break;

            case FingerCode.finger_no_finger:
                status= "未检测到手指";
            break;

            case FingerCode.finger_no_id:
                status= "获取到ID";
            break;

            case FingerCode.finger_no_data:
                status= "id不存在";
            break;

            case FingerCode.finger_auth_failed:
                status= "验证失败";
            break;

            case FingerCode.finger_tp_reset:
                status= "生成的模板不合格,请重放手指 Template";
            break;

            case FingerCode.finger_caputer_timeout:
                status= "拍照超时,请重放手指";
            break;

            case FingerCode.finger_timeout:
                status= "放置手指超时";
            break;

            case FingerCode.finger_regist_full:
                status= "注册：模板缓存满";
            break;

            case FingerCode.finger_regist_id_occupy:
                status= "注册：ID号已被占用0x21";
            break;

            case FingerCode.finger_regist_exist:
                status= "注册：手指已注册过0x22";
            break;

            case FingerCode.finger_regist_start_1:
                status= "注册：开始注册，请放入手指0x23";
            break;

            case FingerCode.finger_regist_success_1:
                status= "注册：第一次注册成功，请移开手指0x24";
            break;

            case FingerCode.finger_regist_start_2:
                status= "注册：第二次注册，请放入手指0x25";
            break;

            case FingerCode.finger_regist_success_2:
                status= "注册：第二次注册成功，请移开手指0x26";
            break;

            case FingerCode.finger_regist_start_3:
                status= "注册：第三次注册，请放入手指0x27";
            break;

            case FingerCode.finger_regist_success_3:
                status= "注册：第三次注册成功，请移开手指0x28";
            break;

            case FingerCode.finger_regist_start_1_1:
                status= "注册：开始注册，请放入手指0x29";
            break;

            case FingerCode.finger_regist_success_4:
                status= "注册：开始注册，请放入手指0x2B";
            break;

            case FingerCode.finger_regist_start_1_2:
                status= "注册：开始注册，请放入手指0x2B";
            break;

            case FingerCode.finger_regist_success_5:
                status= "注册：第五次注册成功，请移开手指0x2C";
            break;

            case FingerCode.finger_regist_success_verify:
                status= "注册：注册完成，请放入手指6--验证0x2D";
            break;

            case FingerCode.finger_regist_finish:
                status= "注册：注册完成0x2E";
            break;

            case FingerCode.finger_regist_outtime:
                status= "注册：注册超时0x2F";
            break;

            case FingerCode.finger_template_re_regist:
                status= "注册：注册时再次采集静脉特征差异过大,请重新注册0x30";
            break;

            case FingerCode.finger_template_fail:
                status= "注册：注册时生成的模板不合格,请重新注册0x31";
            break;

            case FingerCode.finger_template_re_regist_2:
                status= "注册：与前一次采集手指下发信息不同,请重新注册0x32";
            break;

            case FingerCode.finger_template_success:
                status= "注册：发送结束注册命令返回结束注册成功0x33";
            break;

            case FingerCode.finger_template_outtime:
                status= "注册：注册时拍照超时";
            break;

            case FingerCode.finger_regist_success:
                status= "结束注册：结束成功0x35";
            break;

            case FingerCode.finger_regist_fail:
                status= "结束失败：保存失败-模板数量小于3,0x36";
            break;

            case FingerCode.finger_regist_template_full:
                status= "结束失败：保存失败-静脉模板空间满0x37";
            break;

            case FingerCode.finger_regist_save_fail:
                status= "结束失败：保存失败-信息头空间满0x38";
            break;

            case FingerCode.finger_flash_fail:
                status= "结束失败：保存失败-Flash操作失败0x39";
            break;

            case FingerCode.finger_delete_success:
                status= "删除：单个删除成功0x3A";
            break;

            case FingerCode.finger_delete_all_success:
                status= "删除：全部删除成功0x3B";
            break;

            case FingerCode.finger_delete_fail:
                status= "删除：单个删除失败或者无数据0x3C";
            break;

            case FingerCode.finger_delete_no_id:
                status= "删除：无此ID0x3D";
            break;

            case FingerCode.finger_tp_get_success:
                status= "从指静脉获取模板成功0x40";
            break;

            case FingerCode.finger_tp_get_no_tp:
                status= "从指静脉获取模板失败:无此模板0x41";
            break;

            case FingerCode.finger_tp_dwon_success:
                status= "下载模板成功0x42";
            break;

            case FingerCode.finger_tp_dwon_fail:
                status= "下载模板失败0x43";
            break;

            case FingerCode.finger_tp_dwon_head_full:
                status= "下载模板失败:信息头空间满0x44";
            break;

            case FingerCode.finger_tp_dwon_full:
                status= "下载模板失败:模板空间满0x45";
            break;

            case FingerCode.finger_tp_dwon_flash_fail:
                status= "下载模板失败:Flash操作失败0x46";
            break;

            case FingerCode.finger_no_device_found:
                status= "无设备0xFD";
            break;

            case FingerCode.finger_tran_data:
                status= "透传数据/原始数据内容传输0xFE";
            break;

            case FingerCode.finger_unkonw:
                status= "未知错误0xFF";
            break;


        }
        return status;
    }
}
