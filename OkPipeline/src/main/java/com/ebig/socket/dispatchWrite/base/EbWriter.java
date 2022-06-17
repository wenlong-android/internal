package com.ebig.socket.dispatchWrite.base;

import com.ebig.idl.Common3Call;
import com.ebig.idl.CommonCall;
import com.ebig.idl.CommonCall3;
import com.ebig.socket.dispatchWrite.backLight.BackLightAnSender;
import com.ebig.socket.dispatchWrite.cardread.CardReaderSender;
import com.ebig.socket.dispatchWrite.colorLight.CLightParam;
import com.ebig.socket.dispatchWrite.colorLight.ColorLightAnSender;
import com.ebig.socket.dispatchWrite.finger.FingerParam;
import com.ebig.socket.dispatchWrite.finger.FingerAnSender;
import com.ebig.socket.dispatchWrite.lcd.LcdParam;
import com.ebig.socket.dispatchWrite.lcd.LcdAnSender;
import com.ebig.socket.dispatchWrite.lock.LockAnSender;
import com.ebig.socket.dispatchWrite.scale.ScaleAnSender;
import com.ebig.socket.dispatchWrite.scander.ScanAnSender;
import com.ebig.socket.dispatchWrite.th.ThReceiver;
import com.ebig.socket.listenner.IFingerRegistListenner;

import java.util.List;

public  class EbWriter implements ICommand {
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
    public LockAnSender lock() {
        lockSender=new LockAnSender();
        return lockSender;
    }

    @Override
    public LockAnSender frontDoor() {
        return lockSender.openFrontDoor();
    }

    @Override
    public LockAnSender backDoor() {
        return lockSender.openBackDoor();
    }

    @Override
    public LockAnSender bothDoor() {
        return lockSender.openBothDoor();
    }

    @Override
    public LockAnSender openOn(int index, String action) {
        return lockSender.openOn(index,action);
    }

    @Override
    public BackLightAnSender backLight() {
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
    public ScanAnSender scanner() {
        scanSender=new ScanAnSender();
        return scanSender;
    }

    @Override
    public ScanAnSender scan(CommonCall<String> call) {
        return scanSender.scan(call);
    }

    @Override
    public ScaleAnSender scale() {
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
    public LcdAnSender lcd() {
        lcdSender=new LcdAnSender();
        return lcdSender;
    }

    @Override
    public LcdAnSender show(LcdParam param) {
        lcdSender=new LcdAnSender();
        return lcdSender;
    }

    @Override
    public FingerAnSender finger() {
        fingeerSender=new FingerAnSender();
        return fingeerSender;
    }


    @Override
    public FingerAnSender regist(FingerParam param, IFingerRegistListenner registListenner) {
        return fingeerSender.regist(param,registListenner);
    }

    @Override
    public FingerAnSender cancle(FingerParam param,CommonCall<Boolean> call) {
        return fingeerSender.cancle(param,call);
    }

    @Override
    public FingerAnSender delete(FingerParam param,CommonCall<Boolean> call) {
        return fingeerSender.delete(param,call);
    }

    @Override
    public FingerAnSender deleteAll(FingerParam param,CommonCall<Boolean> call) {
        return fingeerSender.deleteAll(param,call);
    }

    @Override
    public FingerAnSender getFingerId(FingerParam param, CommonCall<List<String>> commonCall) {
        return fingeerSender.getFingerId(param,commonCall);
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
    public ColorLightAnSender colorLight() {
        colorLightSender =new ColorLightAnSender();
        return colorLightSender;
    }

    @Override
    public ColorLightAnSender config(CLightParam param) {
        return colorLightSender.config(param);
    }

    @Override
    public CardReaderSender cardReadr(CommonCall<String> call) {
        CardReaderSender readerSender=new CardReaderSender();
        readerSender.setCall(call);
        return readerSender;
    }

    @Override
    public ThReceiver onThRecive(CommonCall3<Double, Double, Long> call) {
        ThReceiver receiver=new ThReceiver();
        receiver.addReciveCall(call);
        return receiver;
    }
}
