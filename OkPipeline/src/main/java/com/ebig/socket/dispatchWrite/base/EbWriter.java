package com.ebig.socket.dispatchWrite.base;

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

public class EbWriter implements ICommand {

    //门锁
    private LockAnSender lockSender;
    //背光灯
    private BackLightAnSender backLightSender;
    //扫描头
    private ScanAnSender scanSender;
    //电子秤
    private ScaleAnSender scaleSender;
    //LCD显示
    private LcdAnSender lcdSender;
    //指静脉
    private FingerAnSender fingeerSender;
    //三色灯
    private ColorLightAnSender colorLightSender;

    public static class L{
        private static ICommand iebSender=new EbWriter();
    }
    public static ICommand l(){
        return L.iebSender;
    }

    @Override
    public LockAnSender withLock() {
        lockSender=new LockAnSender();
        return lockSender;
    }

    @Override
    public LockAnSender openFrontDoor() {
        return lockSender.openFrontDoor();
    }

    @Override
    public LockAnSender openBackDoor() {
        return lockSender.openBackDoor();
    }

    @Override
    public LockAnSender openBothDoor() {
        return lockSender.openBothDoor();
    }

    @Override
    public LockAnSender openOn(int index, String action) {
        return lockSender.openOn(index,action);
    }

    @Override
    public BackLightAnSender withBackLight() {
        backLightSender=new BackLightAnSender();
        return backLightSender;
    }

    @Override
    public BackLightAnSender on() {
        return backLightSender.on();
    }

    @Override
    public BackLightAnSender off() {
        return backLightSender.off();
    }

    @Override
    public ScanAnSender withScanner() {
        scanSender=new ScanAnSender();
        return scanSender;
    }

    @Override
    public ScanAnSender scan() {
        return scanSender.scan();
    }

    @Override
    public ScaleAnSender withScale() {
        scaleSender=new ScaleAnSender();
        return scaleSender;
    }

    @Override
    public ScaleAnSender clear() {
        return scaleSender.clear();
    }

    @Override
    public ScaleAnSender write() {
        return scaleSender.write();
    }

    @Override
    public ScaleAnSender read() {
        return scaleSender.read();
    }

    @Override
    public ScaleAnSender upload(boolean uploadAll) {
        return scaleSender.upload(uploadAll);

    }




    @Override
    public LcdAnSender withLcd() {
        lcdSender=new LcdAnSender();
        return lcdSender;
    }

    @Override
    public LcdAnSender show(LcdParam param) {
        lcdSender=new LcdAnSender();
        return lcdSender;
    }

    @Override
    public FingerAnSender withFinger() {
        fingeerSender=new FingerAnSender();
        return fingeerSender;
    }


    @Override
    public FingerAnSender regist(FingerParam param) {
        return fingeerSender.regist(param);
    }

    @Override
    public FingerAnSender cancle(FingerParam param) {
        return fingeerSender.cancle(param);
    }

    @Override
    public FingerAnSender delete(FingerParam param) {
        return fingeerSender.delete(param);
    }

    @Override
    public FingerAnSender deleteAll(FingerParam param) {
        return fingeerSender.deleteAll(param);
    }

    @Override
    public FingerAnSender getFingerId(FingerParam param) {
        return fingeerSender.getFingerId(param);
    }

    @Override
    public FingerAnSender getFingerInfoTemplates(FingerParam param) {
        return fingeerSender.getFingerInfoTemplates(param);
    }

    @Override
    public FingerAnSender downloadInfoTemplates(FingerParam param) {
        return fingeerSender.downloadInfoTemplates(param);
    }

    @Override
    public ColorLightAnSender withCloloLight() {
        colorLightSender =new ColorLightAnSender();
        return colorLightSender;
    }

    @Override
    public ColorLightAnSender config(CLightParam param) {
        return colorLightSender.config(param);
    }
}
