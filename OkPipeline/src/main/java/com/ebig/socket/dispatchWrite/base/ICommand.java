package com.ebig.socket.dispatchWrite.base;
import com.ebig.idl.CommonCall;
import com.ebig.socket.dispatchWrite.backLight.BackLightAnSender;
import com.ebig.socket.dispatchWrite.colorLight.CLightParam;
import com.ebig.socket.dispatchWrite.colorLight.ColorLightAnSender;
import com.ebig.socket.dispatchWrite.finger.FingerParam;
import com.ebig.socket.dispatchWrite.finger.FingerAnSender;
import com.ebig.socket.dispatchWrite.lcd.LcdParam;
import com.ebig.socket.dispatchWrite.lcd.LcdAnSender;
import com.ebig.socket.dispatchWrite.lock.LockAnSender;
import com.ebig.socket.dispatchWrite.scale.ScaleAnSender;
import com.ebig.socket.dispatchWrite.scander.ScanAnSender;

public interface ICommand {
    /*选择门锁指令*/
    public LockAnSender withLock();
    /*打开前门*/
    public LockAnSender openFrontDoor();
    /*打开后门*/
    public LockAnSender openBackDoor();
    /*打开前后门*/
    public LockAnSender openBothDoor();
    /*操作指定位置锁 index锁地址 action 操作码*/
    public LockAnSender openOn(int index, String action);
    /*选择背光灯指令*/
    BackLightAnSender withBackLight();
    /*打开背光灯*/
    public BackLightAnSender on();
    /*关闭背光灯*/
    public BackLightAnSender off();
    /*选择扫描头指令*/
    ScanAnSender withScanner();
    /*开启扫描头*/
    public ScanAnSender scan(CommonCall<String> call);
    /*选择电子秤指令*/
    ScaleAnSender withScale();
    /*电子秤清零*/
    ScaleAnSender clear();
    /*电子秤标定*/
    ScaleAnSender write();
    /*电子秤读取数量*/
    ScaleAnSender read();//
    /*电子秤上传*/
    ScaleAnSender upload(boolean uoloadAll);
    /*选择LCD指令*/
    LcdAnSender withLcd();
    /*选择LCD指令*/
    LcdAnSender show(LcdParam param);
    /*选择指静脉指令*/
    FingerAnSender withFinger();
    /*注册手指*/
    FingerAnSender regist(FingerParam param);
    /*取消注册*/
    FingerAnSender cancle(FingerParam param);
    /*删除手指*/
    FingerAnSender delete(FingerParam param);
    /*删除所有手指*/
    FingerAnSender deleteAll(FingerParam param);
    /*获取指定手指ID*/
    FingerAnSender getFingerId(FingerParam param);
    /*选择LCD指令*/
    FingerAnSender getFingerInfoTemplates(FingerParam param);
    /*选择LCD指令*/
    FingerAnSender downloadInfoTemplates(FingerParam param);
    /*选择三色灯指令*/
    ColorLightAnSender withCloloLight();
    /*配置三色灯显示指令*/
    ColorLightAnSender config(CLightParam param);


}
